package sets;

import java.util.LinkedList;

public class MySet<E> {
    // Array of LinkedLists to store elements
    private LinkedList<E>[] table;

    // Number of elements in the set
    private int size;

    // Capacity of the table
    private static final int INITIAL_CAPACITY = 16;

    // Constructor to initialize the set with a given capacity
    public MySet() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    // Hash function to determine the index in the array
    private int hash(E element) {
        return Math.abs(element.hashCode()) % table.length;
    }

    // Method to add an element to the set
    public boolean add(E element) {
        int index = hash(element);
        LinkedList<E> list = table[index];

        //Check if the element is already present
        if (list.contains(element)) {
            return false;
        }
        // Add the element to the list
        list.add(element);
        size++;
        return true;
    }

    // Method to remove an element from the set
    public boolean remove(E element) {
        int index = hash(element);
        LinkedList<E> list = table[index];
        // Remove the element from the list
        if (list.remove(element)) {
            size--;
            return true;
        }
        return false;
    }

    // Method to check if an element is in the set
    public boolean contains(E element) {
        int index = hash(element);
        LinkedList<E> list = table[index];
        return list.contains(element);
    }

    // Method to get the number of elements in the set
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
