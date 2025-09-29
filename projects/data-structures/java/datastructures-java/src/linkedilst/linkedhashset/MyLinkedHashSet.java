package linkedilst.linkedhashset;

import java.util.LinkedList;

public class MyLinkedHashSet<E> {
    // Array of LinkedLists to store elements
    private LinkedList<E>[] table;

    // Number of elements in the set
    private int size;

    // Capacity of the table
    private static final int INITIAL_CAPACITY = 16;

    // Inner class to represent a node in the linked list
    private static class Node<E> {
        E element;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    // Constructor to initialize the linked hash set
    public MyLinkedHashSet() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    // Hash function to determine the index for a given element
    private int hash(E element) {
        return Math.abs(element.hashCode()) % table.length;
    }

    // Method to add an element to the set
    public boolean add(E element) {
        int index = hash(element);
        LinkedList<E> list = table[index];
        if (list.contains(element)) {
            // Element already exists
            return false;
        }
        list.add(element);
        size++;
        return true;
    }

    // Method to remove an element from the set
    public boolean remove(E element) {
        int index = hash(element);
        LinkedList<E> list = table[index];
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

    // Method to check if the set is empty
    public boolean isEmpty() {
        return size == 0;
    }
}
