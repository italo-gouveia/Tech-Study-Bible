# Java GC & Memory Management - Study Track

## Objectives
- Understand JVM memory model and garbage collection
- Master different GC algorithms and their trade-offs
- Identify and fix memory leaks
- Optimize memory usage and GC performance

## Study Path

### 1. JVM Memory Model
- **Heap regions**: Young Generation (Eden, S0, S1), Old Generation
- **Non-heap memory**: Metaspace, Code Cache, Direct Memory
- **Stack memory**: method frames, local variables
- **Memory layout**: object headers, alignment

### 2. Garbage Collection Basics
- **GC roots**: stack variables, static fields, JNI references
- **Reachability**: strong, soft, weak, phantom references
- **GC phases**: mark, sweep, compact, copy
- **Stop-the-world vs concurrent**: impact on application

### 3. GC Algorithms
- **Serial GC**: single-threaded, small applications
- **Parallel GC**: multi-threaded, throughput-focused
- **G1 GC**: low-latency, large heaps
- **ZGC/Shenandoah**: ultra-low-latency, concurrent
- **CMS**: deprecated, but good for understanding

### 4. Memory Leaks
- **Common causes**: static collections, unclosed resources, listeners
- **Detection tools**: heap dumps, profilers, monitoring
- **Prevention patterns**: try-with-resources, weak references
- **ClassLoader leaks**: common in application servers

### 5. Performance Tuning
- **GC tuning parameters**: heap size, GC algorithm selection
- **Monitoring**: GC logs, metrics, profiling
- **Application optimization**: object pooling, lazy initialization

## Hands-on Projects

### 1. Memory Leak Detection
```java
// Create intentional memory leaks:
// - Growing static collection
// - Unclosed file streams
// - Event listeners not removed
// Use tools to detect and fix
```

### 2. GC Performance Analysis
```java
// Compare GC algorithms:
// - Serial vs Parallel vs G1
// - Measure pause times and throughput
// - Analyze GC logs
```

### 3. Object Lifecycle Management
```java
// Implement patterns:
// - Object pooling
// - Weak references for caches
// - Phantom references for cleanup
```

### 4. Memory Profiling
```java
// Use tools:
// - JVisualVM
// - JProfiler
// - Eclipse MAT
// - Flight Recorder
```

## Common Memory Leaks

### 1. Static Collections
```java
// BAD: Static collection grows forever
public class MemoryLeak {
    private static List<Object> cache = new ArrayList<>();
    
    public void addToCache(Object obj) {
        cache.add(obj); // Never removed!
    }
}

// GOOD: Use WeakHashMap or size limits
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

### 2. Unclosed Resources
```java
// BAD: Resource not closed
public void readFile(String filename) {
    FileInputStream fis = new FileInputStream(filename);
    // Read file but never close fis
}

// GOOD: Try-with-resources
public void readFile(String filename) {
    try (FileInputStream fis = new FileInputStream(filename)) {
        // Read file - automatically closed
    }
}
```

### 3. Event Listeners
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

### 4. ThreadLocal Variables
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

## GC Tuning Parameters

### Common JVM Flags
```bash
# Heap size
-Xms2g -Xmx4g

# GC algorithm
-XX:+UseG1GC
-XX:+UseParallelGC
-XX:+UseZGC

# GC logging
-Xlog:gc*:gc.log:time
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps

# GC tuning
-XX:MaxGCPauseMillis=200
-XX:G1HeapRegionSize=16m
-XX:NewRatio=2
```

### GC Algorithm Selection Guide
- **Serial GC**: Small applications (< 100MB heap)
- **Parallel GC**: Throughput-focused applications
- **G1 GC**: Low-latency requirements, large heaps
- **ZGC**: Ultra-low-latency, very large heaps
- **Shenandoah**: Similar to ZGC, different implementation

## Monitoring and Profiling

### 1. GC Logs Analysis
```bash
# Enable detailed GC logging
-Xlog:gc*:gc.log:time

# Analyze with tools:
# - GCViewer
# - GCPlot
# - Custom scripts
```

### 2. Heap Dump Analysis
```bash
# Generate heap dump
jmap -dump:format=b,file=heap.hprof <pid>

# Analyze with:
# - Eclipse MAT
# - JVisualVM
# - JProfiler
```

### 3. Runtime Monitoring
```java
// Programmatic monitoring
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

// GC monitoring
List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
for (GarbageCollectorMXBean gcBean : gcBeans) {
    System.out.println("GC: " + gcBean.getName() + 
                      ", Collections: " + gcBean.getCollectionCount() +
                      ", Time: " + gcBean.getCollectionTime());
}
```

## Integration Points

### Projects
- `projects/java/memory-labs/` - Memory leak examples and fixes
- `projects/java/gc-performance/` - GC algorithm comparisons

### Notes
- `notes/java/memory/` - JVM memory model and GC theory
- `notes/java/performance/` - Memory optimization techniques

### Q&A
- `_staging/study-qa/java/memory-gc.md` - Interview questions

## Practice Exercises

### Basic
1. Create and fix a static collection memory leak
2. Implement proper resource management with try-with-resources
3. Use WeakHashMap for a cache that doesn't leak

### Intermediate
1. Compare GC algorithms with different workloads
2. Implement object pooling for frequently created objects
3. Create a memory-efficient data structure

### Advanced
1. Implement a custom memory allocator
2. Build a memory leak detection tool
3. Optimize GC performance for a specific application

## Resources
- Java Performance: The Definitive Guide
- Oracle GC Tuning Guide
- JVM Internals (blog series)
- Memory Analyzer Tool (Eclipse MAT)
