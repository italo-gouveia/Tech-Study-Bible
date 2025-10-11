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

#### Basic Hash Table with Chaining

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
    
    def delete(self, key):
        """Delete key-value pair"""
        index = self._hash(key)
        bucket = self.table[index]
        
        for i, (k, v) in enumerate(bucket):
            if k == key:
                del bucket[i]
                return True
        
        return False  # Key not found

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

### Real-World Applications

#### 1. Database Indexing
- Fast lookups in databases
- Primary key indexing
- Foreign key relationships

#### 2. Caching
- Web page caching
- DNS resolution caching
- CPU cache mapping

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
