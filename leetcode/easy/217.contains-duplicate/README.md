# 217. Contains Duplicate

- Problem: https://leetcode.com/problems/contains-duplicate/
- Reference: https://neetcode.io/solutions/contains-duplicate

## Problem Description

Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

## Examples

### Example 1:
```
Input: nums = [1,2,3,1]
Output: true
Explanation: The element 1 occurs at the indices 0 and 3.
```

### Example 2:
```
Input: nums = [1,2,3,4]
Output: false
Explanation: All elements are distinct.
```

### Example 3:
```
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
Explanation: Multiple elements appear more than once.
```

## Constraints

- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

## Approaches

### Solution 1: HashSet (Optimal)
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Approach**: Use a HashSet to track seen elements. If we encounter an element already in the set, return true.
- **Best For**: General purpose, most efficient for this problem

### Solution 2: Sorting
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(1) extra space (if sorting in-place)
- **Approach**: Sort the array, then check if any adjacent elements are equal.
- **Best For**: When space is limited and you can modify the input array

### Solution 3: Brute Force (Educational)
- **Time Complexity**: O(n²)
- **Space Complexity**: O(1)
- **Approach**: Compare every element with every other element.
- **Best For**: Learning purposes only, not recommended for production

## Solutions

- [Java Solutions](solutions/java/README.md)
- [Go Solutions](solutions/go/README.md)

## Performance Comparison

| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| **HashSet** | O(n) | O(n) | General purpose |
| **Sorting** | O(n log n) | O(1) | Space-constrained |
| **Brute Force** | O(n²) | O(1) | Educational only |

## Analysis

- **Approach**: [analysis/approach.md](analysis/approach.md)
- **Complexity**: [analysis/complexity.md](analysis/complexity.md)
- **Test Cases**: [analysis/test-cases.md](analysis/test-cases.md)
- **Performance**: [analysis/PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md)

## Key Learning Points

1. **HashSet for O(1) lookups**: Perfect for tracking seen elements
2. **Sorting trade-offs**: Better space complexity but worse time complexity
3. **Early termination**: Return immediately when duplicate is found
4. **Edge cases**: Empty arrays, single elements, all duplicates

## Related Problems

- [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) (Easy)
- [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) (Medium)
- [Single Number](https://leetcode.com/problems/single-number/) (Easy)

## Tags

- Array
- Hash Table
- Sorting
- Easy
