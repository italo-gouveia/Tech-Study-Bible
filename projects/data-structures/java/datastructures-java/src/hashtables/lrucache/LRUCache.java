package hashtables.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache Implementation
 * 
 * This implementation uses a HashMap for O(1) access and a doubly linked list
 * for O(1) insertion and deletion operations.
 * 
 * Time Complexity:
 * - get(): O(1)
 * - put(): O(1)
 * 
 * Space Complexity: O(capacity)
 * 
 * @author Italo Gouveia
 */
public class LRUCache<K, V> {
    
    // Node class for doubly linked list
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        Node() {
            // For sentinel nodes (head and tail)
        }
    }
    
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final Node<K, V> head; // Dummy head node
    private final Node<K, V> tail; // Dummy tail node
    
    /**
     * Constructor to initialize LRU Cache with given capacity
     * 
     * @param capacity Maximum number of items that can be stored in cache
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Initialize dummy head and tail nodes
        this.head = new Node<>();
        this.tail = new Node<>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    /**
     * Get value associated with key
     * Moves the accessed item to the front (most recently used)
     * 
     * @param key The key to look up
     * @return Value associated with key, or null if key doesn't exist
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        
        if (node == null) {
            return null; // Key not found
        }
        
        // Move to front (mark as recently used)
        moveToHead(node);
        return node.value;
    }
    
    /**
     * Put key-value pair into cache
     * If key exists, update value and move to front
     * If key doesn't exist, add new entry
     * If cache is full, remove least recently used item
     * 
     * @param key The key to store
     * @param value The value to associate with key
     */
    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        
        if (node != null) {
            // Key exists, update value and move to front
            node.value = value;
            moveToHead(node);
        } else {
            // Key doesn't exist, create new node
            Node<K, V> newNode = new Node<>(key, value);
            
            if (cache.size() >= capacity) {
                // Cache is full, remove least recently used
                Node<K, V> tail = popTail();
                cache.remove(tail.key);
            }
            
            // Add new node to cache and front of list
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }
    
    /**
     * Remove key-value pair from cache
     * 
     * @param key The key to remove
     * @return Value associated with key, or null if key doesn't exist
     */
    public V remove(K key) {
        Node<K, V> node = cache.get(key);
        
        if (node == null) {
            return null; // Key not found
        }
        
        // Remove from cache and list
        cache.remove(key);
        removeNode(node);
        return node.value;
    }
    
    /**
     * Check if cache contains key
     * 
     * @param key The key to check
     * @return true if key exists in cache, false otherwise
     */
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
    
    /**
     * Get current number of items in cache
     * 
     * @return Number of items currently stored in cache
     */
    public int size() {
        return cache.size();
    }
    
    /**
     * Check if cache is empty
     * 
     * @return true if cache is empty, false otherwise
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    
    /**
     * Clear all items from cache
     */
    public void clear() {
        cache.clear();
        // Reset linked list
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * Get current capacity of cache
     * 
     * @return Maximum capacity of cache
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Move node to head (mark as recently used)
     * 
     * @param node Node to move to head
     */
    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }
    
    /**
     * Remove node from linked list
     * 
     * @param node Node to remove
     */
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * Add node to head of linked list
     * 
     * @param node Node to add to head
     */
    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * Remove and return the tail node (least recently used)
     * 
     * @return The tail node that was removed
     */
    private Node<K, V> popTail() {
        Node<K, V> lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }
    
    /**
     * Get string representation of cache contents
     * Shows items from most recently used to least recently used
     * 
     * @return String representation of cache
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LRUCache(capacity=").append(capacity).append(", size=").append(size()).append("): [");
        
        Node<K, V> current = head.next;
        boolean first = true;
        
        while (current != tail) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(current.key).append("=").append(current.value);
            current = current.next;
            first = false;
        }
        
        sb.append("]");
        return sb.toString();
    }
}
