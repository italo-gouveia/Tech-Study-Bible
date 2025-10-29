# Java Solutions for Two Sum

## Solutions Implemented

### Solution 1: One-Pass Hash Table ✅ (Recommended)
- **File**: `solution1.java`
- **Time**: O(n)
- **Space**: O(n)
- **Approach**: Single pass with HashMap for complement lookup

### Solution 2: Two-Pass Hash Table
- **File**: `solution2.java`
- **Time**: O(n)
- **Space**: O(n)
- **Approach**: Build map first, then search

## How to Compile and Run

### Compile
```bash
javac *.java
```

### Run
```bash
java Main
```

### Run Individual Solution
```bash
# Compile specific solution
javac solution1.java

# Note: Each solution has a main method for testing
```

## Solution Explanation

### Solution 1 (Optimal)

**Key Steps:**
1. Create HashMap to store (value → index)
2. For each element, calculate `complement = target - current`
3. If complement exists in map, return indices
4. Otherwise, add current element to map

**Why it's optimal:**
- ✅ Single pass through array
- ✅ O(1) HashMap lookup
- ✅ Clear and readable

**Example:**
```java
Input: nums = [2, 7, 11, 15], target = 9

Iteration 0: nums[0] = 2
  complement = 9 - 2 = 7
  map = {}, complement not found
  map = {2: 0}

Iteration 1: nums[1] = 7
  complement = 9 - 7 = 2
  map contains 2! Return [0, 1]
```

### Solution 2 (Alternative)

**Key Steps:**
1. First pass: Build complete HashMap
2. Second pass: Check for complements
3. Ensure index != current index

**Use case:**
- When you need clear separation of building and searching
- Helpful for debugging or teaching phases

## Testing

The `Main.java` includes test cases covering:
- Standard case
- Duplicate values
- Small arrays
- Edge cases

## Performance

Both solutions achieve O(n) time complexity, but Solution 1 is preferred because:
- More efficient (single pass)
- Less code
- Modern best practice
