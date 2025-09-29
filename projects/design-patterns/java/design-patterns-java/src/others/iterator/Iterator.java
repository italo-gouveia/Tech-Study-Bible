package others.iterator;

// Iterator interface that defines methods for iteration
public interface Iterator<T> {
    boolean hasNext();  // Check if there are more elements
    T next();           // Get the next element
}

