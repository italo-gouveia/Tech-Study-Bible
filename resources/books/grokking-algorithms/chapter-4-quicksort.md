# Chapter 4: Quicksort - Grokking Algorithms

### Recursion and Divide & Conquer

Before diving into quicksort, the chapter explains the fundamental concepts of recursion and divide-and-conquer algorithms.

#### Key Concepts

**Recursion**: A function that calls itself. Every recursive function has:
- **Base case**: The condition that stops the recursion
- **Recursive case**: The part where the function calls itself

**Divide & Conquer**: A problem-solving approach that:
1. **Divide**: Break the problem into smaller subproblems
2. **Conquer**: Solve the subproblems recursively
3. **Combine**: Combine the solutions to solve the original problem

### Big O Analysis Exercises

#### 4.5: Print Each Element in Array
**Question**: How long would it take in Big O notation to print the value of each element in an array?

**Answer**: **O(n)**
- We need to visit each element exactly once
- If array has n elements, we perform n operations
- Time grows linearly with input size

```python
def print_array(arr):
    for element in arr:  # O(n) - visits each element once
        print(element)
```

#### 4.6: Double Each Element in Array
**Question**: How long would it take in Big O notation to double the value of each element in an array?

**Answer**: **O(n)**
- We need to visit each element exactly once
- Each element is modified once
- Time grows linearly with input size

```python
def double_array(arr):
    for i in range(len(arr)):  # O(n) - visits each element once
        arr[i] *= 2
    return arr
```

#### 4.7: Double Only First Element
**Question**: How long would it take in Big O notation to double the value of only the first element in an array?

**Answer**: **O(1)**
- We only access and modify one element
- Time is constant regardless of array size
- This is constant time operation

```python
def double_first_element(arr):
    if arr:  # O(1) - constant time check
        arr[0] *= 2  # O(1) - constant time access and modification
    return arr
```

#### 4.8: Create Multiplication Table
**Question**: How long would it take in Big O notation to create a multiplication table with all elements of the array? For example, if your array is [2,3,7,8,10], you would first multiply each element by 2, then multiply each element by 3, then by 7, and so on.

**Answer**: **O(n²)**
- For each element in the array (n elements), we multiply it by every other element
- This creates a nested loop: for each of n elements, we do n operations
- Total operations: n × n = n²

```python
def create_multiplication_table(arr):
    table = []
    for i in range(len(arr)):        # O(n) - outer loop
        row = []
        for j in range(len(arr)):    # O(n) - inner loop
            row.append(arr[i] * arr[j])  # O(1) - multiplication
        table.append(row)            # O(1) - append
    return table

# Example with [2, 3, 7, 8, 10]:
# Result would be:
# [4,  6,  14, 16, 20]  # 2 × each element
# [6,  9,  21, 24, 30]  # 3 × each element  
# [14, 21, 49, 56, 70]  # 7 × each element
# [16, 24, 56, 64, 80]  # 8 × each element
# [20, 30, 70, 80, 100] # 10 × each element
```

#### Key Insights from These Exercises

1. **O(1)**: Constant time - operation doesn't depend on input size
2. **O(n)**: Linear time - operation scales directly with input size
3. **O(n²)**: Quadratic time - operation scales with square of input size
4. **Nested loops** often indicate O(n²) or higher complexity
5. **Single pass** through data usually indicates O(n) complexity

### Exercises

#### 1. Recursive Sum Function

Write a recursive function that sums all values in a list by taking the first element and passing the rest to the function.

```python
def recursive_sum(arr):
    # Base case: empty list
    if not arr:
        return 0
    
    # Recursive case: first element + sum of rest
    return arr[0] + recursive_sum(arr[1:])

# Example
numbers = [1, 2, 3, 4, 5]
result = recursive_sum(numbers)  # 15
```

**Analysis:**
- **Base case**: Empty list returns 0
- **Recursive case**: First element + recursive sum of remaining elements
- **Time complexity**: O(n)
- **Space complexity**: O(n) due to call stack

#### 2. Recursive Count Function

Write a recursive function that counts the number of items in a list.

```python
def recursive_count(arr):
    # Base case: empty list
    if not arr:
        return 0
    
    # Recursive case: 1 + count of rest
    return 1 + recursive_count(arr[1:])

# Example
items = ['a', 'b', 'c', 'd']
count = recursive_count(items)  # 4
```

**Analysis:**
- **Base case**: Empty list returns 0
- **Recursive case**: 1 + recursive count of remaining elements
- **Time complexity**: O(n)
- **Space complexity**: O(n) due to call stack

#### 3. Recursive Maximum Function

Find the maximum value in a list using recursion.

```python
def recursive_max(arr):
    # Base case: single element
    if len(arr) == 1:
        return arr[0]
    
    # Recursive case: max of first element and max of rest
    return max(arr[0], recursive_max(arr[1:]))

# Example
numbers = [3, 7, 2, 9, 1, 5]
maximum = recursive_max(numbers)  # 9
```

**Analysis:**
- **Base case**: Single element list returns that element
- **Recursive case**: Maximum of first element and recursive max of remaining elements
- **Time complexity**: O(n)
- **Space complexity**: O(n) due to call stack

#### 4. Binary Search - Divide & Conquer Analysis

Binary search from Chapter 1 is also a divide-and-conquer algorithm. Let's analyze its base and recursive cases.

```python
def binary_search(arr, target, left=0, right=None):
    if right is None:
        right = len(arr) - 1
    
    # Base case: search space is empty
    if left > right:
        return -1
    
    # Divide: find middle element
    mid = (left + right) // 2
    
    # Base case: target found
    if arr[mid] == target:
        return mid
    
    # Recursive case: search in appropriate half
    elif arr[mid] > target:
        return binary_search(arr, target, left, mid - 1)
    else:
        return binary_search(arr, target, mid + 1, right)
```

**Analysis:**
- **Base cases**: 
  - Empty search space (left > right) → return -1
  - Target found (arr[mid] == target) → return mid
- **Recursive case**: Search in the appropriate half of the array
- **Time complexity**: O(log n)
- **Space complexity**: O(log n) due to call stack

### Understanding Base Cases

The book explains that a base case is the smallest possible division of the problem that can be solved directly. 

**Example**: Dividing a rectangular plot of land into squares
- If the plot is 1680m × 640m, we need to find the largest square that can divide both dimensions
- The base case would be finding the smallest square dimension that can evenly divide the total area
- This is essentially finding the Greatest Common Divisor (GCD)

```python
def gcd(a, b):
    # Base case: one dimension divides the other evenly
    if a % b == 0:
        return b
    
    # Recursive case: find GCD of smaller dimension and remainder
    return gcd(b, a % b)

# Example: 1680 × 640
result = gcd(1680, 640)  # 80
# So the largest square that can divide the plot is 80m × 80m
```

### Key Takeaways

1. **Recursion requires a base case** to prevent infinite loops
2. **Divide & conquer** breaks problems into smaller, manageable pieces
3. **Base cases** represent the smallest solvable subproblem
4. **Recursive cases** break down the problem and combine results
5. **Binary search** is a classic divide-and-conquer algorithm with O(log n) complexity

### Common Patterns

- **List processing**: Handle first element + recurse on rest
- **Search algorithms**: Divide search space in half
- **Tree traversal**: Process current node + recurse on children
- **Mathematical problems**: Break into smaller mathematical operations

### Algorithm Comparison: Big O Notation

One of the most important concepts in the book is understanding how different algorithms perform as input size grows. Here's a comparison of common algorithms with their Big O complexities and execution times for different array sizes.

#### Algorithm Complexities

| Algorithm | Best Case | Average Case | Worst Case | Space |
|-----------|-----------|--------------|------------|-------|
| **Binary Search** | O(log n) | O(log n) | O(log n) | O(1) |
| **Simple Search** | O(1) | O(n) | O(n) | O(1) |
| **Quicksort** | O(n log n) | O(n log n) | O(n²) | O(log n) |
| **Selection Sort** | O(n²) | O(n²) | O(n²) | O(1) |
| **Traveling Salesman** | O(n!) | O(n!) | O(n!) | O(n) |

#### Execution Time Comparison

The book provides a practical comparison showing how execution time grows with input size. Assuming each operation takes 1 second:

| Array Size | Binary Search | Simple Search | Quicksort | Selection Sort | Traveling Salesman |
|------------|---------------|---------------|-----------|----------------|-------------------|
| **10** | 3.3 seconds | 10 seconds | 33 seconds | 100 seconds | 3.6 million years |
| **100** | 6.6 seconds | 100 seconds | 664 seconds | 10,000 seconds | 4 × 10¹⁴⁰ years |
| **1,000** | 10 seconds | 1,000 seconds | 10,000 seconds | 1,000,000 seconds | 4 × 10²⁵⁶⁷ years |

#### Key Insights

1. **Binary Search vs Simple Search**:
   - For 1 billion items: Binary search takes ~30 operations, simple search takes 1 billion
   - Binary search is dramatically faster for large datasets

2. **Quicksort vs Selection Sort**:
   - Quicksort scales much better: O(n log n) vs O(n²)
   - For 1,000 items: Quicksort ~10,000 operations, Selection Sort ~1,000,000 operations

3. **Quicksort vs Merge Sort**:
   - Both have O(n log n) average time complexity
   - **Quicksort**: Faster in practice due to better cache performance, but O(n²) worst case
   - **Merge Sort**: Consistent O(n log n) performance, but uses O(n) extra space
   - **Space**: Quicksort O(log n), Merge Sort O(n)
   - **Stability**: Quicksort is not stable, Merge Sort is stable

3. **Traveling Salesman Problem**:
   - Demonstrates exponential growth: O(n!)
   - Becomes computationally infeasible very quickly
   - 5 cities: 120 possible routes
   - 10 cities: 3.6 million possible routes
   - 20 cities: 2.4 × 10¹⁸ possible routes

#### Practical Implications

```python
# Example: Why algorithm choice matters
import time

def simple_search(arr, target):
    """O(n) - Linear search"""
    for i, item in enumerate(arr):
        if item == target:
            return i
    return -1

def binary_search(arr, target):
    """O(log n) - Binary search (requires sorted array)"""
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

# Performance difference becomes dramatic with large datasets
large_array = list(range(1, 1000001))  # 1 million items
target = 500000

# Simple search: O(n) - could take significant time
# Binary search: O(log n) - very fast even with millions of items
```

#### Quicksort vs Merge Sort: Detailed Comparison

| Aspect | Quicksort | Merge Sort |
|--------|-----------|------------|
| **Average Time** | O(n log n) | O(n log n) |
| **Worst Time** | O(n²) | O(n log n) |
| **Best Time** | O(n log n) | O(n log n) |
| **Space Complexity** | O(log n) | O(n) |
| **In-place** | Yes | No |
| **Stable** | No | Yes |
| **Cache Performance** | Excellent | Good |
| **Worst Case Scenario** | Already sorted data | Always consistent |

**When to Choose Quicksort:**
- General-purpose sorting
- Memory is limited
- You need in-place sorting
- Average performance is more important than worst-case guarantees

**When to Choose Merge Sort:**
- You need guaranteed O(n log n) performance
- Stability is required (equal elements maintain relative order)
- You're sorting linked lists
- Worst-case performance is critical

```python
# Quicksort implementation
def quicksort(arr):
    if len(arr) <= 1:
        return arr
    
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    
    return quicksort(left) + middle + quicksort(right)

# Merge Sort implementation
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    
    return merge(left, right)

def merge(left, right):
    result = []
    i = j = 0
    
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    
    result.extend(left[i:])
    result.extend(right[j:])
    return result
```

#### When to Use Each Algorithm

- **Binary Search**: When data is sorted and you need fast lookups
- **Simple Search**: When data is unsorted or very small datasets
- **Quicksort**: General-purpose sorting, good average performance, memory-efficient
- **Merge Sort**: When you need guaranteed performance and stability
- **Selection Sort**: Simple to understand, but avoid for large datasets
- **Traveling Salesman**: Use approximation algorithms for real-world problems

### Performance Considerations

- **Time complexity**: Often O(n) for simple recursion, O(log n) for divide-and-conquer
- **Space complexity**: O(n) due to call stack depth
- **Stack overflow**: Deep recursion can cause stack overflow
- **Tail recursion**: Some languages optimize tail-recursive calls
- **Algorithm choice matters**: The difference between O(n) and O(n²) becomes enormous with large inputs
