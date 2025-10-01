# Interfaces and Abstraction - Study Q&A

## Interface vs Abstract Class

### Q: What's the difference between interface and abstract class?
**A:** Key differences:

| Feature | Interface | Abstract Class |
|---------|-----------|----------------|
| **Inheritance** | Multiple inheritance | Single inheritance |
| **Methods** | All methods public | Can have any access modifier |
| **Variables** | Only public static final | Any access modifier |
| **Constructors** | No constructors | Can have constructors |
| **Instance variables** | No instance variables | Can have instance variables |
| **Default methods** | Since Java 8 | Always supported |
| **Static methods** | Since Java 8 | Always supported |

```java
// Interface - pure contract
interface PaymentProcessor {
    void processPayment(double amount);
    default void validateAmount(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
    }
}

// Abstract class - shared implementation
abstract class Animal {
    protected String name; // instance variable
    protected int age;
    
    public Animal(String name, int age) { // constructor
        this.name = name;
        this.age = age;
    }
    
    public abstract void makeSound(); // abstract method
    
    public void sleep() { // concrete method
        System.out.println(name + " is sleeping");
    }
}
```

### Q: When to use interface vs abstract class?
**A:** 

**Use Interface when:**
- You need multiple inheritance
- You want to define a pure contract
- You don't need shared state
- You want to swap implementations easily

**Use Abstract Class when:**
- You need shared state or behavior
- You want to provide partial implementation
- You need constructors
- You want to control access to methods

**Interview line**: *"Interface for a pure contract or multiple behaviors; abstract class when you need shared state or partial implementation. If I only need to swap algorithms, I prefer an interface plus composition (e.g., Hasher, PaymentGateway). If implementations share non-trivial code or invariants, an abstract base is pragmatic."*

## Interface Design Principles

### Q: How to design good interfaces?
**A:** Follow these principles:

**1. Single Responsibility Principle:**
```java
// Good - focused interface
interface Hasher<K> {
    int hash(K key);
}

// Bad - too many responsibilities
interface DataProcessor<K, V> {
    int hash(K key);
    V process(V value);
    void save(V value);
    void delete(K key);
}
```

**2. Interface Segregation Principle:**
```java
// Good - separate interfaces
interface Readable {
    String read();
}

interface Writable {
    void write(String data);
}

// Bad - fat interface
interface ReadWrite {
    String read();
    void write(String data);
    void delete();
    void backup();
}
```

**3. Dependency Inversion Principle:**
```java
// Good - depend on abstraction
class OrderService {
    private final PaymentProcessor processor;
    
    public OrderService(PaymentProcessor processor) {
        this.processor = processor;
    }
    
    public void processOrder(double amount) {
        processor.processPayment(amount);
    }
}

// Bad - depend on concrete class
class OrderService {
    private final CreditCardProcessor processor; // concrete dependency
    
    public OrderService() {
        this.processor = new CreditCardProcessor();
    }
}
```

## Strategy Pattern with Interfaces

### Q: How to implement Strategy pattern with interfaces?
**A:** Strategy pattern allows runtime algorithm selection:

```java
// Strategy interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete strategies
class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // bubble sort implementation
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Context class
class Sorter {
    private SortingStrategy strategy;
    
    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sort(int[] array) {
        strategy.sort(array);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        
        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sort(array);
        
        sorter.setStrategy(new QuickSort());
        sorter.sort(array);
    }
}
```

## Template Method Pattern

### Q: How to implement Template Method pattern?
**A:** Template Method defines algorithm skeleton with customizable steps:

```java
// Abstract template class
abstract class DataProcessor {
    // Template method - defines algorithm structure
    public final void processData(String input) {
        String validated = validateInput(input);
        String processed = processInput(validated);
        String formatted = formatOutput(processed);
        saveResult(formatted);
    }
    
    // Concrete methods
    protected String validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return input.trim();
    }
    
    protected void saveResult(String result) {
        System.out.println("Saving result: " + result);
    }
    
    // Abstract methods - must be implemented by subclasses
    protected abstract String processInput(String input);
    protected abstract String formatOutput(String processed);
}

// Concrete implementations
class UppercaseProcessor extends DataProcessor {
    @Override
    protected String processInput(String input) {
        return input.toUpperCase();
    }
    
    @Override
    protected String formatOutput(String processed) {
        return "UPPERCASE: " + processed;
    }
}

class LowercaseProcessor extends DataProcessor {
    @Override
    protected String processInput(String input) {
        return input.toLowerCase();
    }
    
    @Override
    protected String formatOutput(String processed) {
        return "lowercase: " + processed;
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        DataProcessor processor1 = new UppercaseProcessor();
        processor1.processData("Hello World");
        
        DataProcessor processor2 = new LowercaseProcessor();
        processor2.processData("Hello World");
    }
}
```

## Functional Interfaces

### Q: What are functional interfaces?
**A:** Functional interfaces have exactly one abstract method:

```java
// Built-in functional interfaces
@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}

@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}

@FunctionalInterface
interface Supplier<T> {
    T get();
}

// Custom functional interface
@FunctionalInterface
interface Hasher<K> {
    int hash(K key);
}

// Usage with lambda expressions
public class Main {
    public static void main(String[] args) {
        // Predicate
        Predicate<String> isEmpty = s -> s == null || s.trim().isEmpty();
        
        // Function
        Function<String, Integer> stringLength = String::length;
        
        // Consumer
        Consumer<String> printer = System.out::println;
        
        // Supplier
        Supplier<String> stringSupplier = () -> "Hello World";
        
        // Custom functional interface
        Hasher<String> stringHasher = String::hashCode;
    }
}
```

## Interface Composition

### Q: How to compose interfaces effectively?
**A:** Use interface composition to build complex behaviors:

```java
// Base interfaces
interface Readable {
    String read();
}

interface Writable {
    void write(String data);
}

interface Closable {
    void close();
}

// Composed interface
interface ReadWriteClosable extends Readable, Writable, Closable {
    // Can add additional methods
    boolean isOpen();
}

// Implementation
class FileHandler implements ReadWriteClosable {
    private boolean open = false;
    
    @Override
    public String read() {
        if (!open) throw new IllegalStateException("File not open");
        return "file content";
    }
    
    @Override
    public void write(String data) {
        if (!open) throw new IllegalStateException("File not open");
        System.out.println("Writing: " + data);
    }
    
    @Override
    public void close() {
        open = false;
        System.out.println("File closed");
    }
    
    @Override
    public boolean isOpen() {
        return open;
    }
    
    public void open() {
        open = true;
        System.out.println("File opened");
    }
}
```

## Best Practices

### Q: What are interface design best practices?
**A:**

**1. Keep interfaces focused:**
```java
// Good - single responsibility
interface Cache<K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key);
    int size();
}

// Bad - too many responsibilities
interface DataStore<K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key);
    void backup();
    void restore();
    void compress();
    void encrypt();
}
```

**2. Use default methods judiciously:**
```java
interface PaymentProcessor {
    void processPayment(double amount);
    
    // Good use of default method
    default void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    // Bad - too much implementation in interface
    default void processPaymentWithValidation(double amount) {
        validateAmount(amount);
        // complex payment logic here
    }
}
```

**3. Prefer composition over inheritance:**
```java
// Good - composition
class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;
    
    public OrderService(PaymentProcessor paymentProcessor, 
                       NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }
}

// Bad - inheritance
class OrderService extends PaymentProcessor {
    // tight coupling
}
```
