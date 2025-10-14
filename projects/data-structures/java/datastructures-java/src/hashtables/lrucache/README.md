# LRU Cache Implementation

## Overview

This implementation provides a **Least Recently Used (LRU) Cache** using Java. LRU Cache is a data structure that maintains a fixed-size cache and automatically evicts the least recently accessed item when the cache reaches capacity.

## Key Features

- **O(1) Time Complexity**: Both `get()` and `put()` operations run in constant time
- **Generic Types**: Supports any key-value pair types (`<K, V>`)
- **Thread-Safe Design**: Can be easily extended for concurrent access
- **Memory Efficient**: Uses HashMap + Doubly Linked List combination
- **Comprehensive API**: Full set of cache operations with edge case handling

## Data Structure Design

### Core Components

1. **HashMap<K, Node<K,V>>**: Provides O(1) access to nodes by key
2. **Doubly Linked List**: Maintains access order (most recent at head, least recent at tail)
3. **Dummy Head/Tail Nodes**: Simplify edge case handling for insertion/deletion

### Architecture

```
Head (dummy) ←→ Node1 ←→ Node2 ←→ Node3 ←→ Tail (dummy)
     ↑                                              ↑
   Most Recent                                 Least Recent
```

## API Reference

### Constructor

```java
LRUCache<K, V> cache = new LRUCache<>(capacity);
```

- **capacity**: Maximum number of items the cache can hold
- **Throws**: `IllegalArgumentException` if capacity ≤ 0

### Core Methods

#### `V get(K key)`
- **Purpose**: Retrieve value associated with key
- **Time Complexity**: O(1)
- **Returns**: Value if key exists, `null` otherwise
- **Side Effect**: Moves accessed item to front (marks as recently used)

#### `void put(K key, V value)`
- **Purpose**: Store key-value pair in cache
- **Time Complexity**: O(1)
- **Behavior**: 
  - If key exists: updates value and moves to front
  - If key doesn't exist: adds new entry
  - If cache full: evicts least recently used item

#### `V remove(K key)`
- **Purpose**: Remove key-value pair from cache
- **Time Complexity**: O(1)
- **Returns**: Value if key existed, `null` otherwise

### Utility Methods

#### `boolean containsKey(K key)`
- **Purpose**: Check if key exists in cache
- **Time Complexity**: O(1)

#### `int size()`
- **Purpose**: Get current number of items
- **Returns**: Number of items currently stored

#### `boolean isEmpty()`
- **Purpose**: Check if cache is empty
- **Returns**: `true` if no items stored

#### `void clear()`
- **Purpose**: Remove all items from cache

#### `int getCapacity()`
- **Purpose**: Get maximum capacity of cache

## Usage Examples

### Basic Usage

```java
// Create cache with capacity 3
LRUCache<String, Integer> cache = new LRUCache<>(3);

// Add items
cache.put("Alice", 30);
cache.put("Bob", 25);
cache.put("Charlie", 35);

// Access items (moves to front)
Integer age = cache.get("Alice"); // Returns 30

// Add new item (evicts least recent if full)
cache.put("David", 28); // Evicts "Bob" (least recently used)

// Check if key exists
boolean exists = cache.containsKey("Bob"); // Returns false
```

### Real-World Scenario: Database Query Cache

```java
LRUCache<String, String> queryCache = new LRUCache<>(100);

// Cache database query results
String query = "SELECT * FROM users WHERE id = 123";
String result = queryCache.get(query);

if (result == null) {
    // Cache miss - query database
    result = database.execute(query);
    queryCache.put(query, result);
} else {
    // Cache hit - use cached result
    System.out.println("Using cached result");
}
```

## Performance Characteristics

### Time Complexity
- **get()**: O(1) - HashMap lookup + linked list manipulation
- **put()**: O(1) - HashMap operations + linked list manipulation
- **remove()**: O(1) - HashMap removal + linked list manipulation
- **containsKey()**: O(1) - HashMap contains operation

### Space Complexity
- **Overall**: O(capacity) - Fixed size based on capacity
- **HashMap**: O(capacity) - One entry per cache item
- **Linked List**: O(capacity) - One node per cache item
- **Total Overhead**: Minimal - just pointers and hash table structure

## Implementation Details

### Hash Function
Uses Java's built-in `hashCode()` method for key hashing:
```java
key.hashCode() // Provides good distribution for most key types
```

### Collision Handling
HashMap handles collisions internally, ensuring O(1) average case performance.

### Memory Management
- **Automatic Eviction**: When capacity exceeded, least recently used item is automatically removed
- **Reference Management**: Proper cleanup of linked list pointers
- **Garbage Collection**: Unused nodes become eligible for GC immediately

## When to Use

### ✅ Good Use Cases
- **Database Query Caching**: Cache frequently accessed query results
- **Web Page Caching**: Cache rendered pages or API responses
- **Session Management**: Store user sessions with automatic cleanup
- **Image/Resource Caching**: Cache frequently accessed media files
- **Computation Memoization**: Cache expensive function results

### ❌ Not Suitable For
- **Very Large Datasets**: Fixed capacity limitation
- **Frequent Full Cache**: High eviction rate reduces effectiveness
- **Random Access Patterns**: No locality benefits
- **Memory-Constrained Environments**: Fixed memory footprint

## Thread Safety

**Current Implementation**: Not thread-safe
**For Concurrent Access**: Use synchronization or concurrent data structures:

```java
// Option 1: Synchronized wrapper
Collections.synchronizedMap(cache);

// Option 2: Concurrent implementation
// Would require thread-safe HashMap and linked list operations
```

## Testing

The implementation includes comprehensive tests covering:
- Basic operations (put, get, remove)
- Cache eviction behavior
- Edge cases (capacity 1, empty cache)
- Performance with large datasets
- Real-world scenarios

## Comparison with Other Cache Types

| Cache Type | Eviction Strategy | Use Case |
|------------|------------------|----------|
| **LRU** | Least Recently Used | Temporal locality patterns |
| **LFU** | Least Frequently Used | Frequency-based access patterns |
| **FIFO** | First In, First Out | Simple time-based eviction |
| **TTL** | Time To Live | Data with expiration times |

## Files in This Package

- **`LRUCache.java`**: Main implementation class
- **`Main.java`**: Comprehensive demonstration and testing
- **`README.md`**: This documentation file

## Dependencies

- **Java 8+**: Uses modern Java features
- **Standard Library**: Only uses `java.util.HashMap` and `java.util.Map`

## Future Enhancements

1. **Thread-Safe Version**: Add concurrent access support
2. **Statistics Tracking**: Hit/miss ratio monitoring
3. **Configurable Eviction**: Support for different eviction strategies
4. **Persistence**: Save/load cache state
5. **Size-Based Eviction**: Evict by memory usage instead of count
