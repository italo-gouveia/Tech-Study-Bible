# Generics and PECS - Study Q&A

## Generics Fundamentals

### Q: What are generics and why use them?
**A:** Generics provide type safety and eliminate casting:

**Benefits:**
- **Type Safety**: Compile-time type checking
- **Code Reuse**: Write once, use with different types
- **No Casting**: Eliminate explicit type casting
- **Better Performance**: No runtime type checking

```java
// Without generics - unsafe
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // casting required, runtime error possible

// With generics - type safe
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0); // no casting needed, compile-time safety
```

### Q: What are bounded types in generics?
**A:** Bounded types restrict the types that can be used:

**Upper Bounds (`extends`):**
- Restricts to a type and its subtypes
- Used for reading/accessing data (covariance)

**Lower Bounds (`super`):**
- Restricts to a type and its supertypes
- Used for writing/adding data (contravariance)

```java
// Upper bound - can read T and subtypes
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0.0;
    for (T num : numbers) {
        total += num.doubleValue(); // can call Number methods
    }
    return total;
}

// Lower bound - can write T and subtypes
public static <T> void addNumbers(List<? super Integer> numbers) {
    numbers.add(42);
    numbers.add(100);
    // Can add Integer and its subtypes
}
```

## PECS Principle

### Q: What is PECS and how does it work?
**A:** PECS stands for **Producer Extends, Consumer Super**:

- **Producer Extends** (`? extends T`): For reading data (covariance)
- **Consumer Super** (`? super T`): For writing data (contravariance)

**When to use each:**
- Use `extends` when you need to read from a collection
- Use `super` when you need to write to a collection

```java
// Producer Extends - reading from collection
public static <T> List<T> copyOf(Collection<? extends T> source) {
    List<T> result = new ArrayList<>();
    for (T item : source) { // can read T and subtypes
        result.add(item);
    }
    return result;
}

// Consumer Super - writing to collection
public static <T> void addAll(Collection<? super T> destination, 
                             Collection<? extends T> source) {
    for (T item : source) {
        destination.add(item); // can add T and subtypes to destination
    }
}

// Usage
List<Number> numbers = new ArrayList<>();
List<Integer> integers = Arrays.asList(1, 2, 3);
addAll(numbers, integers); // works: Integer extends Number
```

### Q: Why can't we use `List<Number>` for both reading and writing?
**A:** Type safety prevents it:

```java
// This won't compile - type safety violation
List<Number> numbers = new ArrayList<>();
List<Integer> integers = Arrays.asList(1, 2, 3);
numbers = integers; // ERROR: List<Integer> is not List<Number>

// Even if it compiled, this would be unsafe:
numbers.add(3.14); // Adding Double to List<Integer>!
Integer i = integers.get(0); // ClassCastException at runtime
```

**PECS solves this:**
```java
// Safe with PECS
public static void processNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) { // can read safely
        System.out.println(num.doubleValue());
    }
    // numbers.add(42); // ERROR: can't add to ? extends Number
}

public static void addNumbers(List<? super Integer> numbers) {
    numbers.add(42); // can add safely
    // Number num = numbers.get(0); // ERROR: can't read from ? super Integer
}
```

## Wildcards in Practice

### Q: How to use wildcards effectively?
**A:** Common patterns:

**1. Method parameters:**
```java
// Read-only method
public static void printList(List<? extends Number> list) {
    for (Number num : list) {
        System.out.println(num);
    }
}

// Write-only method
public static void addIntegers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

// Both read and write (use type parameter)
public static <T> void copy(List<? extends T> source, List<? super T> destination) {
    for (T item : source) {
        destination.add(item);
    }
}
```

**2. Return types:**
```java
// Return a collection that can be read from
public static List<? extends Number> getNumbers() {
    return Arrays.asList(1, 2, 3); // List<Integer>
}

// Return a collection that can be written to
public static List<? super Integer> getNumberList() {
    return new ArrayList<Number>();
}
```

## Common Generic Patterns

### Q: What are common generic utility patterns?
**A:**

**1. Generic Builder Pattern:**
```java
public class Builder<T> {
    private T instance;
    
    public Builder(Class<T> clazz) {
        try {
            this.instance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Builder<T> with(Consumer<T> setter) {
        setter.accept(instance);
        return this;
    }
    
    public T build() {
        return instance;
    }
}

// Usage
Person person = new Builder<>(Person.class)
    .with(p -> p.setName("John"))
    .with(p -> p.setAge(30))
    .build();
```

**2. Generic Comparator:**
```java
public class GenericComparator<T> implements Comparator<T> {
    private final Function<T, Comparable<?>> keyExtractor;
    
    public GenericComparator(Function<T, Comparable<?>> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }
    
    @Override
    public int compare(T a, T b) {
        return keyExtractor.apply(a).compareTo(keyExtractor.apply(b));
    }
}

// Usage
List<Person> people = Arrays.asList(
    new Person("Alice", 25),
    new Person("Bob", 30)
);
people.sort(new GenericComparator<>(Person::getName));
```

## Type Erasure

### Q: What is type erasure and its implications?
**A:** Type erasure removes generic type information at runtime:

**What happens:**
- Generic types become their raw types
- Type parameters are replaced with Object or bounds
- Casts are inserted automatically

**Implications:**
- Can't use `instanceof` with generic types
- Can't create arrays of generic types
- Can't catch generic exceptions

```java
// Type erasure example
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// At runtime, both become List<Object>
System.out.println(stringList.getClass() == intList.getClass()); // true

// Can't do this:
// if (obj instanceof List<String>) { } // ERROR

// Can do this:
if (obj instanceof List) {
    List<?> list = (List<?>) obj;
    // work with wildcard
}
```

## Best Practices

### Q: What are generics best practices?
**A:**

**1. Use generic types consistently:**
```java
// Good
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();

// Avoid raw types
List list = new ArrayList(); // raw type - avoid
```

**2. Use bounded wildcards for method parameters:**
```java
// Good - flexible and safe
public static void process(List<? extends Number> numbers) { }

// Less flexible
public static void process(List<Number> numbers) { }
```

**3. Use type parameters for return types:**
```java
// Good - precise return type
public static <T> List<T> createList(T item) {
    return Arrays.asList(item);
}

// Less precise
public static List<?> createList(Object item) {
    return Arrays.asList(item);
}
```

**4. Avoid generic arrays:**
```java
// Don't do this
T[] array = new T[10]; // ERROR

// Do this instead
List<T> list = new ArrayList<>();
```

## Common Mistakes

### Q: What are common generics mistakes?
**A:**

**1. Using raw types:**
```java
// BAD
List list = new ArrayList();
list.add("string");
Integer i = (Integer) list.get(0); // ClassCastException

// GOOD
List<String> list = new ArrayList<>();
list.add("string");
String s = list.get(0); // type safe
```

**2. Confusing extends and super:**
```java
// BAD - can't add to ? extends T
List<? extends Number> numbers = new ArrayList<Integer>();
numbers.add(42); // ERROR

// GOOD - use ? super T for adding
List<? super Integer> numbers = new ArrayList<Number>();
numbers.add(42); // OK
```

**3. Ignoring type erasure:**
```java
// BAD - won't work at runtime
if (obj instanceof List<String>) { }

// GOOD - use wildcards
if (obj instanceof List) {
    List<?> list = (List<?>) obj;
}
```
