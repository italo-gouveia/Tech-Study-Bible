package others.iterator;

import java.util.ArrayList;
import java.util.List;

// Concrete collection class that implements IterableCollection
public class ConcreteCollection<T> implements IterableCollection<T> {
    private List<T> items = new ArrayList<>();

    // Add an item to the collection
    public void add(T item) {
        items.add(item);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator(); // Return an iterator for the collection
    }

    // Inner class that implements the Iterator interface
    private class ConcreteIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < items.size(); // Check if there are more elements
        }

        @Override
        public T next() {
            if (hasNext()) {
                return items.get(currentIndex++); // Return the next element and increment index
            }
            throw new IndexOutOfBoundsException("No more elements");
        }
    }
}

