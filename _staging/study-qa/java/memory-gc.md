# Java Memory & GC - Study Q&A

## JVM Memory Model

### Q: What are the different memory areas in JVM?
**A:** JVM memory is divided into several areas:

**Heap Memory:**
- **Young Generation**: Eden, Survivor 0, Survivor 1
- **Old Generation**: Tenured space for long-lived objects

**Non-Heap Memory:**
- **Metaspace**: Class metadata (replaced PermGen in Java 8)
- **Code Cache**: Compiled native code
- **Direct Memory**: Off-heap memory (NIO)

**Stack Memory:**
- **Method Stack**: Local variables, method parameters
- **Native Method Stack**: For native method calls

```java
// Memory usage example
public class MemoryExample {
    private static String staticField = "static"; // Metaspace
    private String instanceField = "instance";    // Heap
    
    public void method() {
        String localVar = "local";               // Stack
        byte[] array = new byte[1024];           // Heap
    }
}
```

### Q: What's the difference between heap and stack memory?
**A:**

| Aspect | Heap | Stack |
|--------|------|-------|
| **Size** | Large, configurable | Small, fixed per thread |
| **Access** | Slower | Faster |
| **Lifecycle** | Managed by GC | Automatic (method exit) |
| **Thread Safety** | Shared, needs synchronization | Thread-local |
| **Storage** | Objects, arrays | Local variables, method calls |

```java
public void memoryExample() {
    // Stack: local variables
    int localInt = 42;
    String localString = "hello";
    
    // Heap: objects created with 'new'
    List<String> list = new ArrayList<>();
    Object obj = new Object();
    
    // Method parameters also on stack
    processObject(obj);
}
```

## Garbage Collection

### Q: How does garbage collection work?
**A:** GC process involves several phases:

**1. Mark Phase:**
- Identify all reachable objects starting from GC roots
- GC roots: stack variables, static fields, JNI references

**2. Sweep Phase:**
- Remove unreachable objects
- Free memory for allocation

**3. Compact Phase (optional):**
- Defragment memory to reduce fragmentation
- Move objects to contiguous memory

```java
// GC roots example
public class GCRootsExample {
    private static Object staticRoot; // GC root
    
    public void method() {
        Object localRoot = new Object(); // GC root (while method active)
        
        // Object becomes unreachable when method exits
        Object unreachable = new Object();
        unreachable = null; // Explicitly unreachable
    }
}
```

### Q: What are the different GC algorithms?
**A:** Common GC algorithms:

**Serial GC:**
- Single-threaded
- Good for small applications
- `-XX:+UseSerialGC`

**Parallel GC:**
- Multi-threaded
- Good for throughput
- `-XX:+UseParallelGC`

**G1 GC:**
- Low-latency
- Good for large heaps
- `-XX:+UseG1GC`

**ZGC/Shenandoah:**
- Ultra-low-latency
- Concurrent collection
- `-XX:+UseZGC` or `-XX:+UseShenandoahGC`

```bash
# GC algorithm selection
java -XX:+UseG1GC -Xmx4g -Xms2g MyApp
java -XX:+UseZGC -Xmx8g MyApp
```

## Memory Leaks

### Q: What are common causes of memory leaks in Java?
**A:** Common memory leak causes:

**1. Static Collections:**
```java
// BAD: Static collection grows forever
public class MemoryLeak {
    private static List<Object> cache = new ArrayList<>();
    
    public void addToCache(Object obj) {
        cache.add(obj); // Never removed!
    }
}

// GOOD: Use size limits or WeakHashMap
public class FixedMemoryLeak {
    private static final int MAX_SIZE = 1000;
    private static List<Object> cache = new ArrayList<>();
    
    public void addToCache(Object obj) {
        if (cache.size() >= MAX_SIZE) {
            cache.remove(0); // Remove oldest
        }
        cache.add(obj);
    }
}
```

**2. Unclosed Resources:**
```java
// BAD: Resource not closed
public void readFile(String filename) throws IOException {
    FileInputStream fis = new FileInputStream(filename);
    // Read file but never close fis
}

// GOOD: Try-with-resources
public void readFile(String filename) throws IOException {
    try (FileInputStream fis = new FileInputStream(filename)) {
        // Read file - automatically closed
    }
}
```

**3. Event Listeners:**
```java
// BAD: Listener never removed
public class Component {
    public void addListener() {
        someService.addListener(this); // Never removed
    }
}

// GOOD: Remove listener
public class Component {
    public void addListener() {
        someService.addListener(this);
    }
    
    public void cleanup() {
        someService.removeListener(this);
    }
}
```

**4. ThreadLocal Variables:**
```java
// BAD: ThreadLocal not cleaned up
public class ThreadLocalLeak {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    
    public void setValue(Object value) {
        threadLocal.set(value);
        // Never removed - causes memory leak in thread pools
    }
}

// GOOD: Clean up ThreadLocal
public class FixedThreadLocal {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    
    public void setValue(Object value) {
        threadLocal.set(value);
    }
    
    public void cleanup() {
        threadLocal.remove(); // Important for thread pools
    }
}
```

### Q: How to detect memory leaks?
**A:** Several approaches to detect memory leaks:

**1. Heap Dump Analysis:**
```bash
# Generate heap dump
jmap -dump:format=b,file=heap.hprof <pid>

# Analyze with Eclipse MAT or JVisualVM
```

**2. Memory Monitoring:**
```java
// Programmatic monitoring
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

System.out.println("Used: " + heapUsage.getUsed());
System.out.println("Max: " + heapUsage.getMax());
System.out.println("Usage: " + (heapUsage.getUsed() * 100 / heapUsage.getMax()) + "%");
```

**3. GC Logs:**
```bash
# Enable GC logging
java -Xlog:gc*:gc.log:time MyApp

# Analyze with GCViewer or custom scripts
```

## GC Tuning

### Q: How to tune GC performance?
**A:** GC tuning involves several parameters:

**Heap Size:**
```bash
# Set initial and maximum heap size
-Xms2g -Xmx4g

# Ratio of old to young generation
-XX:NewRatio=2  # Old:Young = 2:1
```

**G1 GC Tuning:**
```bash
# G1 specific parameters
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:G1HeapRegionSize=16m
-XX:G1NewSizePercent=30
-XX:G1MaxNewSizePercent=40
```

**GC Logging:**
```bash
# Detailed GC logging
-Xlog:gc*:gc.log:time
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
```

### Q: How to choose the right GC algorithm?
**A:** GC algorithm selection depends on requirements:

**Throughput-focused (Parallel GC):**
- Batch processing applications
- Can tolerate longer pause times
- `-XX:+UseParallelGC`

**Latency-focused (G1 GC):**
- Interactive applications
- Need predictable pause times
- `-XX:+UseG1GC`

**Ultra-low-latency (ZGC/Shenandoah):**
- Real-time applications
- Very large heaps
- `-XX:+UseZGC`

## Object Lifecycle

### Q: How do objects become eligible for GC?
**A:** Objects become eligible for GC when:

**1. No references point to them:**
```java
Object obj = new Object();
obj = null; // Object becomes eligible for GC
```

**2. References go out of scope:**
```java
public void method() {
    Object obj = new Object();
    // obj becomes eligible when method exits
}
```

**3. Weak references:**
```java
WeakReference<Object> weakRef = new WeakReference<>(new Object());
// Object can be GC'd even if weakRef exists
```

### Q: What are different types of references?
**A:** Java has four types of references:

**Strong Reference:**
```java
Object obj = new Object(); // Strong reference
// Object won't be GC'd as long as obj exists
```

**Soft Reference:**
```java
SoftReference<Object> softRef = new SoftReference<>(new Object());
// Object GC'd only when memory is low
```

**Weak Reference:**
```java
WeakReference<Object> weakRef = new WeakReference<>(new Object());
// Object can be GC'd anytime
```

**Phantom Reference:**
```java
PhantomReference<Object> phantomRef = new PhantomReference<>(new Object(), queue);
// Used for cleanup actions before GC
```

## Performance Optimization

### Q: How to optimize memory usage?
**A:** Memory optimization techniques:

**1. Object Pooling:**
```java
public class ObjectPool<T> {
    private final Queue<T> pool = new ConcurrentLinkedQueue<>();
    private final Supplier<T> factory;
    
    public ObjectPool(Supplier<T> factory) {
        this.factory = factory;
    }
    
    public T borrow() {
        T obj = pool.poll();
        return obj != null ? obj : factory.get();
    }
    
    public void returnObject(T obj) {
        pool.offer(obj);
    }
}
```

**2. Lazy Initialization:**
```java
public class LazyInit {
    private volatile ExpensiveObject expensive;
    
    public ExpensiveObject getExpensive() {
        if (expensive == null) {
            synchronized(this) {
                if (expensive == null) {
                    expensive = new ExpensiveObject();
                }
            }
        }
        return expensive;
    }
}
```

**3. String Optimization:**
```java
// BAD: Creates many String objects
String result = "";
for (String item : items) {
    result += item; // Creates new String each time
}

// GOOD: Use StringBuilder
StringBuilder sb = new StringBuilder();
for (String item : items) {
    sb.append(item);
}
String result = sb.toString();
```

## Best Practices

### Q: What are memory management best practices?
**A:**

**1. Use try-with-resources:**
```java
// Good
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // use resource
} // automatically closed
```

**2. Avoid memory leaks:**
```java
// Good: Remove listeners
public void cleanup() {
    eventSource.removeListener(this);
    threadLocal.remove();
}
```

**3. Monitor memory usage:**
```java
// Good: Monitor memory
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
if (memoryBean.getHeapMemoryUsage().getUsed() > threshold) {
    // Take action
}
```

**4. Use appropriate data structures:**
```java
// Good: Use appropriate collections
Map<String, String> map = new ConcurrentHashMap<>(); // Thread-safe
List<String> list = new ArrayList<>(); // Good for random access
```
