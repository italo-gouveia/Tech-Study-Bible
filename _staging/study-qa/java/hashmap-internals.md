# HashMap Internals - Study Q&A

## Core Concepts

### Q: How does HashMap work internally in Java 8+?
**A:** HashMap is backed by an array of bins (buckets), where each bin can hold:
- **Linked list** of nodes (default)
- **Red-black tree** (when bin size exceeds threshold ~8)

**Key operations:**
1. **Index calculation**: `index = (n - 1) & hash` (capacity is power of 2)
2. **Hash spreading**: Higher bits spread to lower bits for better distribution
3. **Collision handling**: Separate chaining with possible treeification
4. **Resize**: Doubles capacity when `size > capacity * loadFactor`

```java
// Simplified HashMap structure
static final class Node<K,V> {
    final K key;
    V value;
    int hash;
    Node<K,V> next; // for chaining
}

// Index calculation
private int index(int hash) {
    return (table.length - 1) & hash;
}

// Hash spreading (similar to HashMap)
private static int spread(int h) {
    h ^= (h >>> 16); // spread higher bits to lower
    return h;
}
```

### Q: What's the load factor and why is it 0.75?
**A:** Load factor balances memory usage and collision probability:
- **Default**: 0.75
- **Purpose**: Triggers resize when `size > capacity * loadFactor`
- **Why 0.75**: Empirically provides good cache performance and distribution
- **Trade-off**: Lower = fewer collisions but more memory; Higher = more collisions but less memory

### Q: What are the time complexities for HashMap operations?
**A:**
- **Average case**: O(1) for get/put/remove
- **Worst case**: O(n) for linked list bins, O(log n) for treeified bins
- **Resize**: O(n) - all entries rehashed and redistributed

**Interview line**: *"This approach is O(n) with a HashMap (average). Worst-case degrades to O(n) on heavy collisions, but in Java 8+ bins treeify to O(log n) beyond a threshold."*

## Implementation Details

### Q: Explain the put operation step by step
**A:**
1. **Compute hash**: `hash = spread(key.hashCode())`
2. **Calculate index**: `index = (table.length - 1) & hash`
3. **Check bin**: If empty, create new node
4. **Handle collision**: If key exists, update value; otherwise append to chain
5. **Treeify check**: If bin size > threshold, convert to red-black tree
6. **Resize check**: If `size > threshold`, double capacity and rehash

```java
public V put(K key, V value) {
    int hash = spread(key.hashCode());
    int index = index(hash);
    
    // Check if key already exists
    for (Node<K,V> e = table[index]; e != null; e = e.next) {
        if (e.hash == hash && Objects.equals(e.key, key)) {
            V oldValue = e.value;
            e.value = value;
            return oldValue; // return old value
        }
    }
    
    // Add new node
    table[index] = new Node<>(hash, key, value, table[index]);
    if (++size > threshold) {
        resize();
    }
    return null;
}
```

### Q: What's the difference between HashMap and ConcurrentHashMap?
**A:**
- **HashMap**: Not thread-safe, single-threaded performance
- **ConcurrentHashMap**: Thread-safe with:
  - **Bin-level synchronization**: Each bin can be locked independently
  - **CAS operations**: Compare-and-swap for lock-free updates
  - **Striped updates**: No global lock, better concurrency
  - **Tree bins**: Use read-write locks for better concurrent access

## equals() and hashCode()

### Q: What's the contract between equals() and hashCode()?
**A:** The contract states:
1. **Consistency**: If `a.equals(b)`, then `a.hashCode() == b.hashCode()`
2. **Stability**: hashCode() must not change while object is in HashMap
3. **Distribution**: Good hashCode() spreads values evenly across bins

**Violation consequences:**
- Objects that are equal but have different hashCodes won't be found
- Poor hashCode() distribution causes clustering and performance degradation

```java
// Good implementation
public class Person {
    private final String name;
    private final int age;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return age == other.age && Objects.equals(name, other.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age); // consistent with equals
    }
}
```

## Performance Analysis

### Q: How does treeification improve performance?
**A:** Treeification converts linked list bins to red-black trees when:
- **Threshold**: Bin size exceeds 8 nodes
- **Benefit**: O(log n) worst-case instead of O(n)
- **Trade-off**: More memory overhead, but better worst-case performance
- **Revert**: Tree converts back to list when size drops below 6

### Q: What causes HashMap performance degradation?
**A:** Common causes:
1. **Poor hashCode()**: Clustering in few bins
2. **Mutable keys**: hashCode() changes after insertion
3. **High collision rate**: Many keys hash to same bin
4. **Frequent resizing**: Inefficient initial capacity

**Interview line**: *"On put, HashMap computes the index from the spread hash, inserts into the bin, and treeifies the bin when it grows too much. Resize happens when size > capacity*loadFactor."*

## Practical Examples

### Q: Implement a simple LRU Cache using LinkedHashMap
**A:**
```java
public class LruCache<K, V> {
    private final Map<K, V> map;
    
    public LruCache(int capacity) {
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public V get(K key) {
        return map.get(key); // moves to end (most recently used)
    }
    
    public void put(K key, V value) {
        map.put(key, value); // adds/updates and moves to end
    }
    
    public int size() {
        return map.size();
    }
}
```

**Complexity**: O(1) average for get/put operations.

### Q: What's the Big-O of common HashMap operations?
**A:**
- **get(key)**: O(1) average, O(n) worst-case (list), O(log n) worst-case (tree)
- **put(key, value)**: O(1) average, O(n) worst-case (list), O(log n) worst-case (tree)
- **remove(key)**: O(1) average, O(n) worst-case (list), O(log n) worst-case (tree)
- **containsKey(key)**: O(1) average, O(n) worst-case (list), O(log n) worst-case (tree)
- **resize()**: O(n) - all entries rehashed and redistributed

## Common Pitfalls

### Q: What are common HashMap mistakes?
**A:**
1. **Using mutable keys**: Key changes after insertion
2. **Inconsistent equals/hashCode**: Objects equal but different hashCodes
3. **Poor initial capacity**: Causes frequent resizing
4. **Ignoring null keys**: HashMap allows one null key
5. **Thread safety**: HashMap is not thread-safe

```java
// BAD: Mutable key
Map<List<String>, Integer> map = new HashMap<>();
List<String> key = new ArrayList<>();
key.add("test");
map.put(key, 1);
key.add("modified"); // hashCode() changed!
map.get(key); // returns null - key not found

// GOOD: Immutable key
Map<String, Integer> map = new HashMap<>();
String key = "test";
map.put(key, 1);
// key cannot be modified
map.get(key); // returns 1
```
