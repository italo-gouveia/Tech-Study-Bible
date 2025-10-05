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

### Performance Considerations

- **Time complexity**: Often O(n) for simple recursion, O(log n) for divide-and-conquer
- **Space complexity**: O(n) due to call stack depth
- **Stack overflow**: Deep recursion can cause stack overflow
- **Tail recursion**: Some languages optimize tail-recursive calls
