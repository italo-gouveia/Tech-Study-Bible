package hashtables;

import java.util.LinkedList;
import java.util.List;

public class MyHashTable<K, V> {
    // Array of LinkedLists to store key-value pairs
    private LinkedList<Entry<K, V>>[] table;

    //Number of elements in the hash table
    private int size;

    // Capacity of the hash table
    private static final int INITIAL_CAPACITY = 16;

    // Inner class to represent a key-value pair
    private static class Entry<K, V> {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor to initialize the hash table
    public MyHashTable() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    // Hash function to determine the index for a key
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Method to put a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        // Check if the key already exists and update the value if so
        for (Entry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Otherwise, add a new entry
        list.add(new Entry<>(key, value));
        size++;
    }

    // Method to get the value associated with a key
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];

        // Search for the key and return the associated value
        for (Entry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        // Key not found
        return null;
    }

    // Method to remove a key-value pair from the hash table
    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];

        // Iterate through the list and remove the entry with the matching key
        for (Entry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                list.remove(entry);
                size--;
                return;
            }
        }
    }

    // Method to get the number of key-value pairs in the hash table
    public int size() {
        return size;
    }

    // Method to check if the hash table is empty
    public boolean isEmpty() {
        return size == 0;
    }
}
