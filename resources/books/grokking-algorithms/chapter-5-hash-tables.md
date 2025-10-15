# Chapter 5: Hash Tables - Grokking Algorithms

### Introduction to Hash Tables

Hash tables are one of the most useful data structures in computer science. They provide fast lookups, insertions, and deletions with average O(1) time complexity.

#### Key Concepts

**Hash Table**: A data structure that stores key-value pairs and provides fast access to values based on keys.

**Hash Function**: A function that maps keys to array indices. It should be:
- **Consistent**: Same input always produces same output
- **Fast**: O(1) time complexity
- **Uniform**: Distributes keys evenly across buckets

**Collision**: When two different keys hash to the same index. Common collision resolution strategies:
- **Chaining**: Store multiple values in the same bucket (linked list)
- **Open Addressing**: Find next available slot (linear probing, quadratic probing)

### Hash Function Consistency Exercise

One of the first exercises in the book tests understanding of what makes a hash function "consistent" - a fundamental concept for hash table performance.

#### What is Hash Function Consistency?

A hash function is **consistent** if it always returns the same output for the same input. This is crucial for hash tables to work correctly.

#### Exercise: Which Hash Functions Are Consistent?

Let's analyze these hash functions from Chapter 5:

##### 5.1: f(x) = 1
```python
def hash_function_1(x):
    return 1
```

**Analysis:**
- **Consistent**: ✅ Yes - always returns 1 for any input
- **Problem**: ❌ Terrible hash function - causes all collisions
- **Result**: All keys map to the same bucket, turning hash table into a linked list
- **Performance**: O(n) lookup time instead of O(1)

**Example:**
```python
hash_function_1("apple")    # Returns 1
hash_function_1("banana")   # Returns 1
hash_function_1("cherry")   # Returns 1
# All keys collide at index 1!
```

##### 5.2: f(x) = rand()
```python
import random

def hash_function_2(x):
    return random.randint(0, 100)
```

**Analysis:**
- **Consistent**: ❌ No - returns different values for same input
- **Problem**: Hash table becomes completely unreliable
- **Result**: Cannot find previously stored values
- **Why it fails**: Same key gets different hash values on each call

**Example:**
```python
hash_function_2("apple")    # Returns 42 (random)
hash_function_2("apple")    # Returns 17 (different random)
hash_function_2("apple")    # Returns 89 (different random)
# Same key, different hashes - impossible to retrieve!
```

##### 5.3: f(x) = proximo_espaco_vazio()
```python
def hash_function_3(x, hash_table):
    # Returns index of next empty space in hash table
    for i in range(len(hash_table)):
        if hash_table[i] is None:
            return i
    return -1  # Table full
```

**Analysis:**
- **Consistent**: ❌ No - depends on current state of hash table
- **Problem**: Same key gets different hash values based on table state
- **Result**: Unreliable hash table behavior
- **Why it fails**: Hash depends on external state, not just the key

**Example:**
```python
# Empty table: "apple" → index 0
hash_table = [None, None, None]
hash_function_3("apple", hash_table)  # Returns 0

# Table with some data: "apple" → index 2
hash_table = ["banana", "cherry", None]
hash_function_3("apple", hash_table)  # Returns 2 (different!)
# Same key, different hash based on table state!
```

##### 5.4: f(x) = len(x)
```python
def hash_function_4(x):
    return len(x)  # x must be a string
```

**Analysis:**
- **Consistent**: ✅ Yes - same string length always returns same value
- **Problem**: ⚠️ Causes collisions for strings of same length
- **Result**: Works but not optimal (all 3-letter words collide)
- **Performance**: Acceptable but not ideal

**Example:**
```python
hash_function_4("cat")     # Returns 3
hash_function_4("dog")     # Returns 3
hash_function_4("pig")     # Returns 3
hash_function_4("bird")    # Returns 4
# All 3-letter words collide at index 3!
```

#### Key Insights from the Exercise

1. **Consistency is essential**: Hash functions must be deterministic
2. **Good distribution**: Minimize collisions across different inputs
3. **Fast computation**: Hash function should be O(1) time
4. **Deterministic**: Same input → Same output, always
5. **State independence**: Hash should depend only on the key, not external state

#### Examples of Good Hash Functions

```python
# Simple hash for strings (simplified version)
def good_hash_function(s):
    hash_value = 0
    for char in s:
        # Use prime number 31 for better distribution
        hash_value = (hash_value * 31 + ord(char)) % 1000
    return hash_value

# Test consistency
print(good_hash_function("hello"))  # Always same output: 652
print(good_hash_function("hello"))  # Always same output: 652
print(good_hash_function("world"))  # Different but consistent: 893

# Test distribution
test_strings = ["apple", "banana", "cherry", "date", "elderberry"]
for s in test_strings:
    print(f"{s}: {good_hash_function(s)}")
```

### Hash Table Implementation

#### What Makes a Good Hash Function?
- **Deterministic and consistent**: same input → same output, always
- **Uniform distribution**: spreads keys evenly across buckets to minimize collisions (avoids clustering)
- **Fast to compute**: O(1) with low constant factors for frequent operations
- **Low collision rate**: different inputs rarely map to the same index
- **Stable under typical inputs**: resistant to common input patterns (e.g., similar prefixes)

Notes and examples:
- Cryptographic hashes (e.g., **SHA-256**) provide excellent distribution but are usually too slow for in-memory hash tables
- Non-cryptographic hashes (e.g., **MurmurHash3**, **FNV-1a**) are commonly used in hash tables due to speed and good distribution
- When mapping hash → index, prefer table sizes that reduce patterns (power-of-two sizes with bit-masking or primes with modulo)
- Bad hash functions cause many collisions, turning O(1) average into O(n) worst-case behavior

#### Basic Hash Table with Chaining

**📁 Java Implementation:**
- **Complete Implementation**: [`projects/data-structures/java/datastructures-java/src/hashtables/MyHashTable.java`](../projects/data-structures/java/datastructures-java/src/hashtables/MyHashTable.java)
- **Usage Examples**: [`projects/data-structures/java/datastructures-java/src/hashtables/Main.java`](../projects/data-structures/java/datastructures-java/src/hashtables/Main.java)
- **Documentation**: [`projects/data-structures/java/datastructures-java/src/hashtables/README.md`](../projects/data-structures/java/datastructures-java/src/hashtables/README.md)

**Key Features of the Java Implementation:**
- Generic types `<K, V>` for type safety
- Chaining collision resolution using `LinkedList<Entry<K, V>>[]`
- Hash function: `Math.abs(key.hashCode()) % table.length`
- Complete API: `put()`, `get()`, `remove()`, `size()`, `isEmpty()`
- Initial capacity of 16 buckets with automatic expansion

**Python Implementation (for comparison):**
```python
class HashTable:
    def __init__(self, size=10):
        self.size = size
        self.table = [[] for _ in range(size)]
    
    def _hash(self, key):
        """Simple hash function"""
        if isinstance(key, str):
            hash_value = 0
            for char in key:
                hash_value = (hash_value * 31 + ord(char)) % self.size
            return hash_value
        return key % self.size
    
    def set(self, key, value):
        """Insert key-value pair"""
        index = self._hash(key)
        bucket = self.table[index]
        
        # Check if key already exists
        for i, (k, v) in enumerate(bucket):
            if k == key:
                bucket[i] = (key, value)  # Update existing
                return
        
        # Add new key-value pair
        bucket.append((key, value))
    
    def get(self, key):
        """Get value by key"""
        index = self._hash(key)
        bucket = self.table[index]
        
        for k, v in bucket:
            if k == key:
                return v
        
        return None  # Key not found

# Example usage
phone_book = HashTable()
phone_book.set("Alice", "555-1234")
phone_book.set("Bob", "555-5678")
phone_book.set("Charlie", "555-9012")

print(phone_book.get("Alice"))    # "555-1234"
print(phone_book.get("Bob"))      # "555-5678"
print(phone_book.get("David"))    # None
```

### Collision Resolution Strategies

#### 1. Chaining (Separate Chaining)
- Store multiple values in the same bucket using a linked list
- Simple to implement
- Handles unlimited collisions
- Memory overhead for pointers

#### 2. Open Addressing
- Find next available slot in the table
- Linear probing: check next slot sequentially
- Quadratic probing: check slots at increasing intervals
- Double hashing: use second hash function for step size

```python
class HashTableOpenAddressing:
    def __init__(self, size=10):
        self.size = size
        self.keys = [None] * size
        self.values = [None] * size
    
    def _hash(self, key):
        return key % self.size
    
    def _linear_probe(self, key):
        """Find next available slot using linear probing"""
        index = self._hash(key)
        original_index = index
        
        while self.keys[index] is not None and self.keys[index] != key:
            index = (index + 1) % self.size
            if index == original_index:  # Table is full
                raise Exception("Hash table is full")
        
        return index
    
    def set(self, key, value):
        index = self._linear_probe(key)
        self.keys[index] = key
        self.values[index] = value
    
    def get(self, key):
        index = self._hash(key)
        original_index = index
        
        while self.keys[index] is not None:
            if self.keys[index] == key:
                return self.values[index]
            index = (index + 1) % self.size
            if index == original_index:  # Wrapped around
                break
        
        return None
```

### Performance Analysis

#### Time Complexity
- **Average case**: O(1) for insert, lookup, delete
- **Worst case**: O(n) when all keys hash to same bucket
- **Load factor**: Should be kept below 0.75 for optimal performance

#### Space Complexity
- **O(n)**: Linear in number of key-value pairs
- **Overhead**: Additional space for hash table structure

#### Load Factor
```
Load Factor = Number of elements / Table size
Optimal: 0.5 to 0.75
```

##### Practical Notes on Load Factor and Resizing
- When the load factor grows beyond the optimal range (e.g., > 0.75), collisions rise and performance degrades toward O(n)
- A load factor strictly greater than 1 means there are more elements than buckets; the table must be resized (rehash) to restore O(1) average time
- Resizing strategy (typical):
  - Allocate a new array (usually 2× the current size or next prime)
  - Recompute the hash for every key and insert into the new buckets (rehashing)
  - Temporary cost is O(n) during resize; amortized cost of operations remains O(1)
- Trade-offs:
  - Lower target load factor → fewer collisions but uses more memory
  - Higher target load factor → better memory use but more collisions
- Common practice:
  - Trigger resize when load factor exceeds 0.75
  - Use prime table sizes or power-of-two sizes with bit-masking (language-dependent)


### Real-World Applications

#### 1. Database Indexing
- Fast lookups in databases
- Primary key indexing
- Foreign key relationships

#### 2. Caching
- Web page caching
- DNS resolution caching
- CPU cache mapping

##### Cache Implementation with Hash Tables

**📁 Java LRU Cache Implementation:**
- **Complete Implementation**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/LRUCache.java`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/LRUCache.java)
- **Usage Examples**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/Main.java`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/Main.java)
- **Documentation**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/README.md`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/README.md)

**Key Features of the Java LRU Cache:**
- Generic types `<K, V>` for type safety
- HashMap + Doubly Linked List for O(1) operations
- Automatic eviction of least recently used items
- Comprehensive API with edge case handling
- Thread-safe design ready for concurrent access

**Python Implementation (for comparison):**
```python
class LRUCache:
    """Least Recently Used Cache implementation using hash table + doubly linked list"""
    
    class Node:
        def __init__(self, key=0, value=0):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None
    
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = {}  # Hash table: key -> Node
        self.head = self.Node()
        self.tail = self.Node()
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def get(self, key):
        """Get value and mark as recently used"""
        node = self.cache.get(key)
        if not node:
            return -1
        
        # Move to head (mark as recently used)
        self._move_to_head(node)
        return node.value
    
    def put(self, key, value):
        """Put key-value pair, evict LRU if necessary"""
        node = self.cache.get(key)
        
        if not node:
            new_node = self.Node(key, value)
            
            if len(self.cache) >= self.capacity:
                # Remove LRU item
                tail = self._pop_tail()
                del self.cache[tail.key]
            
            # Add new node
            self.cache[key] = new_node
            self._add_node(new_node)
        else:
            # Update existing node
            node.value = value
            self._move_to_head(node)

# Example usage
cache = LRUCache(3)
cache.put(1, "value1")
cache.put(2, "value2")
cache.put(3, "value3")
print(cache.get(2))    # "value2"
cache.put(4, "value4") # Evicts key 1 (LRU)
print(cache.get(1))    # -1 (not found)
print(cache.get(4))    # "value4"
```

##### Cache Performance Analysis
- **Time Complexity**: O(1) for both get and put operations
- **Space Complexity**: O(capacity) for hash table + linked list
- **Cache Hit**: When requested data is in cache (fast access)
- **Cache Miss**: When requested data is not in cache (slower access)

#### 3. Language Features
- Python dictionaries
- Java HashMap
- JavaScript objects
- C++ unordered_map

#### 4. Cryptographic Applications
- Password hashing
- Digital signatures
- Blockchain technology

### Common Hash Table Problems

#### Problem 1: Anagram Groups
```python
def group_anagrams(strs):
    """Group strings that are anagrams of each other"""
    hash_map = {}
    
    for s in strs:
        # Sort characters to create key
        key = ''.join(sorted(s))
        
        if key not in hash_map:
            hash_map[key] = []
        hash_map[key].append(s)
    
    return list(hash_map.values())

# Example
words = ["eat", "tea", "tan", "ate", "nat", "bat"]
print(group_anagrams(words))
# Output: [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
```

#### Problem 2: Two Sum
```python
def two_sum(nums, target):
    """Find two numbers that add up to target"""
    hash_map = {}
    
    for i, num in enumerate(nums):
        complement = target - num
        
        if complement in hash_map:
            return [hash_map[complement], i]
        
        hash_map[num] = i
    
    return []

# Example
nums = [2, 7, 11, 15]
target = 9
print(two_sum(nums, target))  # Output: [0, 1]
```

### Best Practices

#### 1. Choose Good Hash Function
- Use prime numbers for better distribution
- Consider your data characteristics
- Test for collision patterns

#### 2. Handle Collisions Properly
- Choose appropriate collision resolution strategy
- Monitor load factor
- Consider rehashing when load factor gets too high

#### 3. Memory Management
- Pre-allocate appropriate size
- Consider memory overhead
- Use appropriate data types

#### 4. Thread Safety
- Consider concurrent access patterns
- Use appropriate synchronization mechanisms
- Consider lock-free alternatives for high performance

### Key Takeaways

1. **Hash tables provide O(1) average time complexity** for insertions, lookups, and deletions
2. **Hash function consistency is crucial** - same input must always produce same output
3. **Collision resolution** is necessary when multiple keys hash to the same index
4. **Load factor management** is important for maintaining good performance
5. **Hash tables are widely used** in real-world applications for fast data access
6. **Choose appropriate collision resolution strategy** based on your use case

### Common Patterns

- **Frequency counting**: Count occurrences of elements
- **Lookup optimization**: Fast key-value access
- **Deduplication**: Remove duplicate elements
- **Set operations**: Union, intersection, difference
- **Memoization**: Cache function results

### Cache Types and Strategies

#### 1. LRU (Least Recently Used) Cache
- Evicts least recently accessed items
- Uses hash table + doubly linked list
- O(1) time complexity for all operations
- Good for temporal locality patterns

#### 2. LFU (Least Frequently Used) Cache
- Evicts least frequently accessed items
- Uses hash table + frequency tracking
- Good for items with varying access patterns

#### 3. FIFO (First In, First Out) Cache
- Evicts oldest items first
- Uses hash table + queue
- Simple implementation

#### 4. TTL (Time To Live) Cache
- Items expire after specified time
- Uses hash table + timestamp tracking
- Good for data with known expiration

**📁 Java TTL Cache Implementation:**
- **Complete Implementation**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/TTLCache.java`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/TTLCache.java)
- **Usage Examples**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/Main.java`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/Main.java)
- **Documentation**: [`projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/README.md`](../projects/data-structures/java/datastructures-java/src/hashtables/lrucache/ttlcache/README.md)

**Key Features of the Java TTL Cache:**
- Thread-safe implementation using ConcurrentHashMap
- Flexible TTL: global default or custom per item
- Background cleanup with ScheduledExecutorService
- TTL management: extend, check remaining time
- Performance monitoring and statistics
- Automatic expiration with lazy cleanup

**Python Implementation (for comparison):**
```python
# Simple TTL Cache Implementation
import time

class TTLCache:
    def __init__(self, ttl_seconds=300):  # 5 minutes default
        self.cache = {}
        self.ttl = ttl_seconds
    
    def get(self, key):
        if key in self.cache:
            value, timestamp = self.cache[key]
            if time.time() - timestamp < self.ttl:
                return value
            else:
                # Expired
                del self.cache[key]
        return None
    
    def put(self, key, value):
        self.cache[key] = (value, time.time())
    
    def is_valid(self, key):
        if key in self.cache:
            _, timestamp = self.cache[key]
            return time.time() - timestamp < self.ttl
        return False

# Example usage
ttl_cache = TTLCache(ttl_seconds=60)  # 1 minute TTL
ttl_cache.put("user:123", {"name": "Alice", "email": "alice@example.com"})
print(ttl_cache.get("user:123"))  # Returns user data
time.sleep(61)  # Wait for expiration
print(ttl_cache.get("user:123"))  # Returns None (expired)
```

### Cache Performance Metrics

#### Hit Rate vs Miss Rate
- **Hit Rate**: Percentage of requests served from cache
- **Miss Rate**: Percentage of requests that miss cache
- **Optimal Hit Rate**: 80-95% depending on use case

#### Cache Efficiency
```
Cache Efficiency = (Cache Hits / Total Requests) × 100%
```

#### Memory Usage
- **Cache Size**: Total memory used by cache
- **Memory Overhead**: Additional memory for cache structure
- **Eviction Policy**: How to free memory when cache is full

### Real-World Cache Examples

#### 1. Web Browser Cache
- Stores web pages, images, CSS, JavaScript
- Uses LRU strategy for page cache
- TTL for different resource types

#### 2. Database Query Cache
- Caches frequently executed queries
- Reduces database load
- Uses query hash as key

#### 3. CPU Cache
- L1, L2, L3 caches in processors
- Uses hardware-based hash functions
- Critical for performance

#### 4. CDN (Content Delivery Network)
- Caches content closer to users
- Reduces latency
- Uses geographic distribution

## Exercises — Hash Functions and Distribution

The following exercises evaluate which hash function yields a good distribution (few collisions) for a hash table of size 10. Candidate hash functions over strings:
- a) Always return "1"
- b) Use the string length as the index
- c) Use the first character as the index (all strings starting with the same letter collide)
- d) Map letters to primes (a=2, b=3, c=5, d=7, e=11, ...), the hash is the sum of letter primes modulo the table size

General notes:
- a) is always bad (everything collides)
- b) is good only if lengths vary
- c) is good only if first letters vary
- d) generally provides the best spread for typical text

### 5.5 Phone book (keys: names; values: phone numbers)
Names: Esther, Ben, Bob, Dan.
- a) Bad; b) Poor (lengths 6,3,3,3 → collisions); c) Medium (E,B,B,D → B collides); d) Good (distinct indices under mod 10)
Answer: d)

### 5.6 Battery sizes to power (keys: A, AA, AAA, AAAA)
- a) Bad; c) Bad (all start with A); b) Good (1,2,3,4); d) Good (2,4,6,8)
Answer: b) or d)

### 5.7 Book titles to authors (titles: Maus, Fun Home, Watchmen)
- a) Bad; b) Medium (lengths 4,8,8 → collisions); c) Good (M,F,W distinct first letters); d) Good (prime-sum mod tends to spread)
Answer: c) or d)
