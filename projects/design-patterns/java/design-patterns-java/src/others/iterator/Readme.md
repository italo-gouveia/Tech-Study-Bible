## Iterator Design Pattern in Java

### Explanation

1. **Iterator Interface**: Defines the methods `hasNext()` and `next()`, which are used to iterate through a collection.

2. **Iterable Interface**: Defines the method `iterator()` that returns an `Iterator` for the collection. This allows external code to obtain an iterator for the collection.

3. **Concrete Collection Class**: Implements the `IterableCollection` interface and maintains a list of items. It provides the `iterator()` method, which returns an instance of the `ConcreteIterator`.

4. **Concrete Iterator Class**: Implements the `Iterator` interface. It keeps track of the current position in the collection and provides implementations for the `hasNext()` and `next()` methods.

5. **Client**: Uses the `ConcreteCollection` and retrieves an `Iterator` to access and print each item in the collection.

### How to Use

- **Use the Iterator Pattern** when you need to access elements of a collection sequentially without exposing the underlying data structure.
- **Apply this pattern** to collections that require traversal and manipulation, allowing you to iterate over the elements without knowing their internal details.

### How Not to Use

- **Do not use** the Iterator Pattern if the collection is simple and doesn’t require traversal or if the performance overhead of using an iterator is not acceptable.
- **Avoid overuse** of this pattern if you don’t need multiple ways to iterate over the collection or if the collection is not expected to change dynamically.
