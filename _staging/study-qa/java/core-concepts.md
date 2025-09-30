# Java Core Concepts - Study Q&A

## Object-Oriented Programming

### Q: What are the four pillars of OOP in Java?
**A:** The four pillars are:
1. **Encapsulation**: Bundling data and methods together, controlling access through access modifiers
2. **Inheritance**: Creating new classes based on existing classes (extends keyword)
3. **Polymorphism**: Same interface, different implementations (method overriding, interfaces)
4. **Abstraction**: Hiding complex implementation details (abstract classes, interfaces)

### Q: What's the difference between abstract class and interface?
**A:** 
- **Abstract Class**: Can have concrete methods, constructors, instance variables. Single inheritance.
- **Interface**: Only method signatures (default/static methods since Java 8). Multiple inheritance.
- Interfaces define contracts; abstract classes share partial behavior/state.
- Prefer interface + composition over inheritance when you only need a contract.
- Use generics to keep APIs flexible and type-safe; add bounds (<T extends Number>), and wildcards (PECS: Producer Extends, Consumer Super).
- Show at least one design pattern where interface is central: Strategy, Template Method, Factory, Repository.

```java
// Abstract class
abstract class Animal {
    protected String name; // can have instance variables
    public Animal(String name) { this.name = name; } // can have constructor
    public abstract void makeSound(); // abstract method
    public void sleep() { System.out.println("Sleeping..."); } // concrete method
}

// Interface
interface Flyable {
    void fly(); // abstract method
    default void land() { System.out.println("Landing..."); } // default method (Java 8+)
    static void takeOff() { System.out.println("Taking off..."); } // static method (Java 8+)
}
```

### Q: What are access modifiers in Java?
**A:** Four levels of access control:
- **private**: Only within the same class
- **default (package-private)**: Within the same package
- **protected**: Within the same package or subclasses
- **public**: Accessible from anywhere

## Collections Framework

### Q: What's the difference between ArrayList and LinkedList?
**A:**
- **ArrayList**: Dynamic array, O(1) random access, O(n) insertion/deletion in middle
- **LinkedList**: Doubly linked list, O(n) random access, O(1) insertion/deletion at ends

```java
List<String> arrayList = new ArrayList<>(); // better for frequent random access
List<String> linkedList = new LinkedList<>(); // better for frequent insertions/deletions
```

### Q: When to use HashMap vs TreeMap vs LinkedHashMap?
**A:**
- **HashMap**: O(1) average, no ordering, allows one null key
- **TreeMap**: O(log n), sorted by keys (natural or custom comparator)
- **LinkedHashMap**: O(1), maintains insertion order

```java
Map<String, Integer> hashMap = new HashMap<>(); // fastest, no order
Map<String, Integer> treeMap = new TreeMap<>(); // sorted by key
Map<String, Integer> linkedMap = new LinkedHashMap<>(); // insertion order
```

## Exception Handling

### Q: What's the difference between checked and unchecked exceptions?
**A:**
- **Checked Exceptions**: Must be handled (try-catch) or declared (throws). Compile-time checking.
- **Unchecked Exceptions**: Runtime exceptions, don't need explicit handling.

```java
// Checked exception - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (FileNotFoundException e) {
    e.printStackTrace();
}

// Unchecked exception - optional handling
String str = null;
int length = str.length(); // NullPointerException - unchecked
```

### Q: What's the proper way to handle exceptions?
**A:** Follow these principles:
1. **Catch specific exceptions** rather than generic Exception
2. **Don't ignore exceptions** - log or handle appropriately
3. **Use try-with-resources** for auto-closable resources
4. **Don't catch and rethrow** without adding value

```java
// Good: Specific exception handling
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // use resource
} catch (FileNotFoundException e) {
    logger.error("File not found: " + e.getMessage());
    throw new CustomException("Unable to process file", e);
}
```

## Generics

### Q: What are generics and why use them?
**A:** Generics provide type safety and eliminate casting:
- **Type Safety**: Compile-time type checking
- **Code Reuse**: Write once, use with different types
- **No Casting**: Eliminate explicit type casting

```java
// Without generics - unsafe
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // casting required

// With generics - type safe
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0); // no casting needed
```

### Q: What's the difference between `? extends T` and `? super T`?
**A:**
- **`? extends T`**: Upper bound - accepts T and its subtypes (producer)
- **`? super T`**: Lower bound - accepts T and its supertypes (consumer)

```java
// Producer - can read T and subtypes
void processNumbers(List<? extends Number> numbers) {
    for (Number n : numbers) {
        System.out.println(n.doubleValue());
    }
}

// Consumer - can write T and subtypes
void addNumbers(List<? super Integer> numbers) {
    numbers.add(42);
    numbers.add(100);
}
```

## Memory Management

### Q: How does garbage collection work in Java?
**A:** JVM automatically manages memory:
1. **Mark**: Identify reachable objects
2. **Sweep**: Remove unreachable objects
3. **Compact**: Defragment memory (optional)

**GC Types:**
- **Serial GC**: Single-threaded, good for small applications
- **Parallel GC**: Multi-threaded, good for throughput
- **G1 GC**: Low-latency, good for large heaps
- **ZGC/Shenandoah**: Ultra-low-latency

### Q: What causes memory leaks in Java?
**A:** Common causes:
1. **Static collections** that grow indefinitely
2. **Unclosed resources** (files, connections)
3. **Listeners** not removed
4. **ThreadLocal** variables not cleaned up
5. **ClassLoader** issues

```java
// Memory leak example
public class MemoryLeak {
    private static List<Object> cache = new ArrayList<>(); // static - never cleared
    
    public void addToCache(Object obj) {
        cache.add(obj); // grows forever
    }
}
```
