# Java Concurrency - Study Track

## Objectives
- Master thread lifecycle, synchronization, and coordination
- Understand concurrent collections and atomic operations
- Practice thread-safe design patterns and common pitfalls
- Learn modern concurrency (CompletableFuture, virtual threads)

## Study Path

### 1. Thread Fundamentals
- Thread lifecycle: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
- Thread creation: extends Thread vs implements Runnable
- Thread scheduling and priority
- Daemon vs user threads

### 2. Synchronization
- **synchronized keyword**: methods, blocks, static methods
- **volatile keyword**: visibility guarantees, happens-before
- **wait/notify/notifyAll**: inter-thread communication
- **ReentrantLock**: advanced locking with conditions
- **ReadWriteLock**: multiple readers, single writer

### 3. Atomic Operations
- **AtomicInteger, AtomicLong, AtomicReference**
- **AtomicArray**: atomic array operations
- **CompareAndSwap (CAS)**: lock-free programming
- **ABA problem**: understanding and solutions

### 4. Concurrent Collections
- **ConcurrentHashMap**: thread-safe HashMap
- **CopyOnWriteArrayList**: thread-safe ArrayList
- **BlockingQueue**: producer-consumer patterns
- **ConcurrentLinkedQueue**: lock-free queue

### 5. Thread Pools and Executors
- **ExecutorService**: managing thread pools
- **ThreadPoolExecutor**: custom thread pool configuration
- **ForkJoinPool**: work-stealing for parallel tasks
- **ScheduledExecutorService**: delayed and periodic tasks

### 6. Modern Concurrency (Java 8+)
- **CompletableFuture**: asynchronous programming
- **Stream parallel processing**: when and how to use
- **Virtual Threads (Project Loom)**: lightweight concurrency

## Hands-on Projects

### 1. Thread-Safe Counter
```java
// Implement multiple approaches:
// - synchronized method
// - ReentrantLock
// - AtomicInteger
// - Compare performance
```

### 2. Producer-Consumer Pattern
```java
// Implement with:
// - wait/notify
// - BlockingQueue
// - Semaphore
```

### 3. Thread Pool Manager
```java
// Custom ThreadPoolExecutor with:
// - Custom rejection policy
// - Monitoring and metrics
// - Graceful shutdown
```

### 4. CompletableFuture Chain
```java
// Async service calls with:
// - Error handling
// - Timeout management
// - Result combination
```

## Common Pitfalls to Study

### 1. Race Conditions
```java
// Example: Non-atomic compound operations
private int count = 0;
public void increment() {
    count++; // Not thread-safe!
}
```

### 2. Deadlocks
```java
// Example: Circular wait
// Thread 1: lock A, then B
// Thread 2: lock B, then A
```

### 3. Livelocks
```java
// Example: Polite threads that keep yielding
```

### 4. Starvation
```java
// Example: Low-priority threads never get CPU
```

## Integration Points

### Projects
- `projects/java/concurrency-labs/` - Hands-on implementations
- `projects/design-patterns/java/` - Thread-safe design patterns

### Notes
- `notes/java/concurrency/` - Theory and examples
- `notes/java/performance/` - Concurrency performance analysis

### Q&A
- `_staging/study-qa/java/concurrency.md` - Interview questions

## Practice Problems

### Basic
1. Implement thread-safe singleton (multiple approaches)
2. Create a thread-safe LRU cache
3. Build a rate limiter using Semaphore

### Intermediate
1. Implement a custom ThreadPoolExecutor
2. Create a producer-consumer with multiple consumers
3. Build a thread-safe object pool

### Advanced
1. Implement a lock-free data structure
2. Create a distributed lock simulation
3. Build a concurrent cache with eviction policies

## Resources
- Java Concurrency in Practice (book)
- Oracle Java Concurrency Tutorial
- JEP 444: Virtual Threads
- Java Memory Model specification
