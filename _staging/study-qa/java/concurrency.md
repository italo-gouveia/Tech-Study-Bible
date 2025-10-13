# Java Concurrency - Study Q&A

## Thread Fundamentals

### Q: What's the difference between Thread and Runnable?
**A:** 
- **Thread**: Class that represents a thread, extends Thread
- **Runnable**: Interface that defines a task to be executed

```java
// Extending Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

// Implementing Runnable (preferred)
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}

// Usage
new MyThread().start();
new Thread(new MyTask()).start();
```

**Why Runnable is preferred:**
- Java doesn't support multiple inheritance
- Better separation of concerns (task vs thread)
- Can be used with ExecutorService

### Q: What are the different thread states?
**A:** Thread states in Java:

1. **NEW**: Thread created but not started
2. **RUNNABLE**: Thread is executing or ready to execute
3. **BLOCKED**: Thread waiting for monitor lock
4. **WAITING**: Thread waiting indefinitely for another thread
5. **TIMED_WAITING**: Thread waiting for specified time
6. **TERMINATED**: Thread has completed execution

```java
Thread thread = new Thread(() -> {
    try {
        Thread.sleep(1000); // TIMED_WAITING
        synchronized(this) { // BLOCKED if lock unavailable
            // critical section
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});

System.out.println(thread.getState()); // NEW
thread.start();
System.out.println(thread.getState()); // RUNNABLE
```

## Synchronization

### Q: What's the difference between synchronized and volatile?
**A:**

**synchronized:**
- Provides mutual exclusion (only one thread at a time)
- Ensures visibility of changes to all threads
- Provides atomicity for compound operations

**volatile:**
- Ensures visibility of changes to all threads
- Does NOT provide mutual exclusion
- Does NOT provide atomicity for compound operations

```java
class Counter {
    private int count = 0;
    
    // synchronized - thread-safe
    public synchronized void increment() {
        count++; // atomic operation
    }
    
    // volatile - NOT thread-safe for compound operations
    private volatile int volatileCount = 0;
    
    public void incrementVolatile() {
        volatileCount++; // NOT atomic! Race condition possible
    }
}
```

### Q: How does wait/notify work?
**A:** wait/notify provides inter-thread communication:

```java
class ProducerConsumer {
    private final Object lock = new Object();
    private boolean dataReady = false;
    private String data;
    
    public void produce(String data) {
        synchronized(lock) {
            this.data = data;
            dataReady = true;
            lock.notify(); // wake up waiting thread
        }
    }
    
    public String consume() throws InterruptedException {
        synchronized(lock) {
            while (!dataReady) {
                lock.wait(); // release lock and wait
            }
            dataReady = false;
            return data;
        }
    }
}
```

**Key points:**
- Must be called within synchronized block
- wait() releases the lock
- notify() wakes up one waiting thread
- notifyAll() wakes up all waiting threads
- Always use wait() in a loop to handle spurious wakeups

## Thread-Safe Collections

### Q: ArrayDeque vs Stack vs ConcurrentLinkedDeque - Thread Safety
**A:** Different approaches to thread-safe collections:

**ArrayDeque (Non-thread-safe):**
```java
ArrayDeque<Character> deque = new ArrayDeque<>();
// ❌ PERIGOSO em ambiente multi-threaded
deque.push('A');  // Race condition possible
char c = deque.pop();  // May throw exceptions
```

**Stack (Thread-safe but deprecated):**
```java
Stack<Character> stack = new Stack<>();
// ✅ Thread-safe (synchronized)
stack.push('A');  // Safe but slow
char c = stack.pop();  // Safe but slow
```

**ConcurrentLinkedDeque (Modern thread-safe):**
```java
ConcurrentLinkedDeque<Character> deque = new ConcurrentLinkedDeque<>();
// ✅ Thread-safe (lock-free)
deque.push('A');  // Fast and safe
char c = deque.poll();  // Fast and safe
```

**Collections.synchronizedDeque() (Wrapper):**
```java
Deque<Character> deque = Collections.synchronizedDeque(new ArrayDeque<>());
// ✅ Thread-safe (synchronized wrapper)
deque.push('A');  // Safe but slower than ConcurrentLinkedDeque
char c = deque.pop();  // Safe but slower
```

**Performance Comparison:**
- ArrayDeque: ~430 ns/op (fastest, not thread-safe)
- ConcurrentLinkedDeque: ~450 ns/op (fast, thread-safe)
- Collections.synchronizedDeque: ~500 ns/op (slower, thread-safe)
- Stack: ~600 ns/op (slowest, thread-safe, deprecated)

**When to use each:**
- **Single-thread**: ArrayDeque (fastest)
- **Multi-thread high concurrency**: ConcurrentLinkedDeque (lock-free)
- **Multi-thread low concurrency**: Collections.synchronizedDeque (simpler)
- **Legacy code**: Stack (deprecated but functional)

**Problems with non-thread-safe collections:**
- Race conditions
- Data corruption
- Infinite loops
- Unexpected exceptions
- Inconsistent results

### Q: ConcurrentLinkedDeque vs Collections.synchronizedDeque() - Differences
**A:** Two different approaches to thread safety:

**ConcurrentLinkedDeque (Lock-free):**
```java
ConcurrentLinkedDeque<Character> deque = new ConcurrentLinkedDeque<>();

// Thread 1
deque.push('A');
deque.push('B');

// Thread 2 (executando simultaneamente)
char c = deque.poll();  // Pode pegar 'A' ou 'B' - imprevisível!
```

**Collections.synchronizedDeque() (Lock-based):**
```java
Deque<Character> deque = Collections.synchronizedDeque(new ArrayDeque<>());

// Thread 1
deque.push('A');
deque.push('B');

// Thread 2 (executando simultaneamente)
char c = deque.poll();  // Espera Thread 1 terminar
```

**Key Differences:**
- **Locking**: ConcurrentLinkedDeque uses CAS (Compare-And-Swap), synchronized uses locks
- **Performance**: ConcurrentLinkedDeque ~200ms, synchronized ~350ms (1M operations, 4 threads)
- **Concurrency**: ConcurrentLinkedDeque allows parallel operations, synchronized blocks operations
- **Deadlock Risk**: ConcurrentLinkedDeque has no deadlock risk, synchronized can deadlock

**When to use each:**
- **ConcurrentLinkedDeque**: High concurrency, performance critical, simple operations
- **Collections.synchronizedDeque**: Low concurrency, need compound operations, simpler code

## Atomic Operations

### Q: What are atomic operations and when to use them?
**A:** Atomic operations are thread-safe without synchronization:

```java
import java.util.concurrent.atomic.*;

class AtomicExample {
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicReference<String> value = new AtomicReference<>("initial");
    
    public void increment() {
        count.incrementAndGet(); // thread-safe increment
    }
    
    public void updateValue(String newValue) {
        // Compare and swap
        String oldValue = value.get();
        while (!value.compareAndSet(oldValue, newValue)) {
            oldValue = value.get(); // retry if CAS failed
        }
    }
    
    public int getCount() {
        return count.get();
    }
}
```

**When to use:**
- Simple operations (increment, compare-and-swap)
- High-performance requirements
- Lock-free programming
- Building higher-level synchronization primitives

## Concurrent Collections

### Q: How does ConcurrentHashMap work?
**A:** ConcurrentHashMap uses segment-based locking (Java 7) or CAS + synchronized (Java 8+):

```java
// Thread-safe HashMap
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// All operations are thread-safe
map.put("key", 1);
map.compute("key", (k, v) -> v == null ? 1 : v + 1);
map.merge("key", 1, Integer::sum);

// Atomic operations
map.computeIfAbsent("key", k -> expensiveComputation());
map.computeIfPresent("key", (k, v) -> v > 0 ? v - 1 : null);
```

**Key features:**
- Thread-safe without external synchronization
- Better performance than synchronized HashMap
- Atomic operations for compound updates
- No locking for read operations

### Q: What's the difference between BlockingQueue implementations?
**A:** Different BlockingQueue implementations:

```java
// ArrayBlockingQueue - bounded, array-based
BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(10);

// LinkedBlockingQueue - optionally bounded, linked-list based
BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(10);

// PriorityBlockingQueue - unbounded, priority-based
BlockingQueue<String> priorityQueue = new PriorityBlockingQueue<>();

// SynchronousQueue - no capacity, direct handoff
BlockingQueue<String> syncQueue = new SynchronousQueue<>();

// Producer
arrayQueue.put("item"); // blocks if full

// Consumer
String item = arrayQueue.take(); // blocks if empty
```

## Thread Pools

### Q: How do you choose the right thread pool size?
**A:** Thread pool sizing depends on the task type:

```java
// CPU-bound tasks: number of cores
int cpuCount = Runtime.getRuntime().availableProcessors();
ExecutorService cpuPool = Executors.newFixedThreadPool(cpuCount);

// I/O-bound tasks: higher number (2-4x CPU cores)
ExecutorService ioPool = Executors.newFixedThreadPool(cpuCount * 2);

// Mixed workload: use ThreadPoolExecutor with custom settings
ThreadPoolExecutor mixedPool = new ThreadPoolExecutor(
    cpuCount,                    // core pool size
    cpuCount * 2,               // maximum pool size
    60L, TimeUnit.SECONDS,      // keep-alive time
    new LinkedBlockingQueue<>(), // work queue
    new ThreadFactory() {       // custom thread factory
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Worker-" + t.getId());
            return t;
        }
    }
);
```

## CompletableFuture

### Q: How to use CompletableFuture for asynchronous programming?
**A:** CompletableFuture provides a fluent API for async programming:

```java
// Basic async execution
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return "Hello";
});

// Chain operations
CompletableFuture<String> result = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase);

// Handle exceptions
CompletableFuture<String> safeFuture = CompletableFuture
    .supplyAsync(() -> {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Random error");
        }
        return "Success";
    })
    .handle((result, throwable) -> {
        if (throwable != null) {
            return "Error: " + throwable.getMessage();
        }
        return result;
    });

// Combine multiple futures
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

CompletableFuture<String> combined = future1
    .thenCombine(future2, (s1, s2) -> s1 + " " + s2);

// Wait for completion
String result = combined.get(); // blocking
String result2 = combined.get(1, TimeUnit.SECONDS); // with timeout
```

## Common Concurrency Problems

### Q: What is a deadlock and how to prevent it?
**A:** Deadlock occurs when threads wait for each other indefinitely:

```java
// Deadlock example
class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // critical section
            }
        }
    }
    
    public void method2() {
        synchronized(lock2) {
            synchronized(lock1) { // DEADLOCK!
                // critical section
            }
        }
    }
}

// Prevention: always acquire locks in same order
class FixedDeadlock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                // critical section
            }
        }
    }
    
    public void method2() {
        synchronized(lock1) { // Same order as method1
            synchronized(lock2) {
                // critical section
            }
        }
    }
}
```

### Q: What is a race condition?
**A:** Race condition occurs when the outcome depends on timing:

```java
// Race condition example
class RaceCondition {
    private int count = 0;
    
    public void increment() {
        count++; // Not atomic! Race condition possible
    }
    
    public int getCount() {
        return count;
    }
}

// Fix with synchronization
class FixedRaceCondition {
    private int count = 0;
    
    public synchronized void increment() {
        count++; // Now atomic
    }
    
    public synchronized int getCount() {
        return count;
    }
}

// Or with atomic operations
class AtomicRaceCondition {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet(); // Atomic operation
    }
    
    public int getCount() {
        return count.get();
    }
}
```

## Best Practices

### Q: What are concurrency best practices?
**A:**

**1. Prefer immutable objects:**
```java
// Good - immutable
public final class ImmutablePoint {
    private final int x, y;
    
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
}
```

**2. Use thread-safe collections:**
```java
// Good
List<String> list = Collections.synchronizedList(new ArrayList<>());
Map<String, String> map = new ConcurrentHashMap<>();
```

**3. Avoid sharing mutable state:**
```java
// Bad - shared mutable state
private static int counter = 0;

// Good - thread-local or atomic
private static AtomicInteger counter = new AtomicInteger(0);
```

**4. Use proper exception handling:**
```java
// Good - handle InterruptedException
public void run() {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restore interrupt status
        return; // Exit gracefully
    }
}
```
