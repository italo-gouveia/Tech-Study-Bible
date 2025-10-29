# Approach Analysis for Two Sum

## Problem Understanding

Given an array of integers `nums` and an integer `target`, return indices `i` and `j` such that `nums[i] + nums[j] == target` and `i != j`.

### Key Requirements
1. **Find complementary pair**: Two numbers that sum to target
2. **Return indices**: Not the values, but their positions
3. **Unique indices**: i ≠ j (can't use same element twice)
4. **Exactly one solution**: Guaranteed to exist

## Approach 1: One-Pass Hash Table (Optimal)

### Intuition
Instead of checking if complement exists in remaining array (which requires nested loops), we can use a hash map to store seen elements and check for complements in O(1) time.

### Algorithm
1. Create a hash map to store seen elements (value → index)
2. Iterate through the array
3. For each element, calculate complement = target - current
4. Check if complement exists in hash map
5. If found, return [map[complement], current_index]
6. Otherwise, add current element to map with its index

### Pseudocode
```
function twoSum(nums, target):
    map = new HashMap()
    
    for i = 0 to nums.length - 1:
        complement = target - nums[i]
        if complement in map:
            return [map[complement], i]
        map[nums[i]] = i
    
    return [] // Should never reach here
```

### Example Execution
```
nums = [2, 7, 11, 15], target = 9

i=0: nums[0]=2, complement=7, map={}, add 2→0 to map
     map = {2: 0}

i=1: nums[1]=7, complement=2, 2 in map! ✓
     return [map[2], 1] = [0, 1]
```

### Advantages
- **Optimal time complexity**: O(n) - single pass
- **Clear logic**: Easy to understand and implement
- **Memory efficient**: Only stores necessary elements
- **No sorting required**: Works with any input order

### Disadvantages
- **Extra space**: O(n) for hash map
- **Hash collision overhead**: Minor performance impact

## Approach 2: Two-Pass Hash Table

### Intuition
Separate the phase of building the hash map from searching for complements.

### Algorithm
1. **First pass**: Build hash map (value → index)
2. **Second pass**: For each element, check if complement exists
3. Make sure not to use same element twice

### Pseudocode
```
function twoSum(nums, target):
    map = new HashMap()
    
    // First pass: build hash map
    for i = 0 to nums.length - 1:
        map[nums[i]] = i
    
    // Second pass: find complement
    for i = 0 to nums.length - 1:
        complement = target - nums[i]
        if complement in map and map[complement] != i:
            return [i, map[complement]]
    
    return []
```

### Example Execution
```
nums = [2, 7, 11, 15], target = 9

First pass: map = {2: 0, 7: 1, 11: 2, 15: 3}

Second pass:
i=0: complement=7, 7 in map, map[7]!=0 → return [0, 1]
```

### Advantages
- **Clear separation**: Building and searching are separate
- **Single data structure**: Consistent hash map usage
- **Easy to debug**: Clear phases

### Disadvantages
- **Two passes**: Less efficient than one-pass
- **Need extra check**: Ensure complement isn't same element

## Approach 3: Brute Force (Educational)

### Intuition
Check every possible pair of elements.

### Algorithm
1. For each element at index i
2. For each subsequent element at index j
3. If nums[i] + nums[j] == target, return [i, j]

### Pseudocode
```
function twoSum(nums, target):
    for i = 0 to nums.length - 2:
        for j = i + 1 to nums.length - 1:
            if nums[i] + nums[j] == target:
                return [i, j]
    
    return []
```

### Example Execution
```
nums = [2, 7, 11, 15], target = 9

i=0: j=1: 2+7=9 ✓ return [0, 1]
```

### Advantages
- **Constant space**: O(1) extra space
- **Simple logic**: Nested loops are intuitive
- **No data structures**: Only uses input array

### Disadvantages
- **Poor time complexity**: O(n²) - quadratic
- **Not scalable**: Slow for large inputs
- **Inefficient**: Unnecessary comparisons

## Approach Comparison

| Aspect | One-Pass Hash | Two-Pass Hash | Brute Force |
|--------|--------------|---------------|-------------|
| **Time** | O(n) | O(n) | O(n²) |
| **Space** | O(n) | O(n) | O(1) |
| **Passes** | 1 | 2 | n²/2 comparisons |
| **Hash Collisions** | Possible | Possible | None |
| **Best Case** | O(n) | O(n) | O(1) - first pair |
| **Worst Case** | O(n) | O(n) | O(n²) |
| **Simplicity** | High | Medium | Very High |
| **Efficiency** | Excellent | Good | Poor |

## Implementation Details

### Java - One-Pass Hash Table

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        return new int[]{};
    }
}
```

### Go - One-Pass Hash Table

```go
func twoSum(nums []int, target int) []int {
    m := make(map[int]int)
    
    for i, num := range nums {
        complement := target - num
        if idx, exists := m[complement]; exists {
            return []int{idx, i}
        }
        m[num] = i
    }
    
    return []int{}
}
```

## Edge Cases

### Case 1: Two elements
```
Input: nums = [1, 2], target = 3
Output: [0, 1]
```
One-pass: Finds complement in first iteration

### Case 2: Negative numbers
```
Input: nums = [-1, -2, -3], target = -4
Output: [0, 2]
```
Hash map handles negative keys correctly

### Case 3: Same element twice
```
Input: nums = [3, 3], target = 6
Output: [0, 1]
```
One-pass stores first index, finds match with second

### Case 4: Duplicate values
```
Input: nums = [2, 2, 2, 3], target = 4
Output: [0, 1]
```
One-pass returns first two occurrences

### Case 5: Large range
```
Input: nums = [10^9, -10^9, 0], target = 0
Output: [1, 2]
```
Handles edge of integer range

## Performance Optimizations

### 1. Pre-allocate HashMap Capacity
```java
Map<Integer, Integer> map = new HashMap<>(nums.length);
```
Reduces resizing operations

### 2. Use int[] for Return (Java)
```java
return new int[]{map.get(complement), i};
```
More efficient than ArrayList

### 3. Map Instead of Multimap
Store only first occurrence for duplicates (as per problem constraints)

## When to Use Each Approach

### Use One-Pass Hash Table When:
- ✅ Performance is critical
- ✅ Single pass optimization matters
- ✅ Modern best practice
- ✅ **Recommended for production**

### Use Two-Pass Hash Table When:
- ✅ Code clarity is priority
- ✅ Building and searching need separation
- ✅ Debugging complex logic
- ✅ Teaching/learning phases

### Use Brute Force When:
- ✅ Educational purposes
- ✅ Very small inputs (n < 100)
- ✅ No hash map available
- ✅ Space is extremely constrained

## Conclusion

The **One-Pass Hash Table** approach is the optimal solution, providing O(n) time complexity with a single iteration through the array. It's the recommended approach for production code due to its efficiency and clarity.

The key insight is using a hash map to store seen elements, allowing O(1) lookup for complements, which transforms the problem from O(n²) to O(n).
