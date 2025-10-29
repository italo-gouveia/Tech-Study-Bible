# 1. Two Sum

- Problem: https://leetcode.com/problems/two-sum/
- Reference: https://neetcode.io/solutions/two-sum

## Problem Description

Given an array of integers `nums` and an integer `target`, return indices `i` and `j` such that `nums[i] + nums[j] == target` and `i != j`.

You may assume that every input has exactly one pair of indices `i` and `j` that satisfy the condition.

## Examples

### Example 1:
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

### Example 2:
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
Explanation: nums[1] + nums[2] == 6
```

### Example 3:
```
Input: nums = [3,3], target = 6
Output: [0,1]
Explanation: nums[0] + nums[1] == 6
```

### Example 4:
```
Input: nums = [4,5,6], target = 10
Output: [0,2]
Explanation: nums[0] + nums[2] == 10
```

## Constraints

- `2 <= nums.length <= 104`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- Only one valid answer exists.

## Approaches

### Solution 1: One-Pass Hash Table (Optimal)
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Approach**: Use a hash map to store seen elements and their indices. For each element, check if its complement (target - current) exists in the map.
- **Best For**: Most efficient solution, single pass through array

### Solution 2: Two-Pass Hash Table
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Approach**: First pass builds hash map, second pass checks for complements.
- **Best For**: When you need to separate building and searching phases

### Solution 3: Brute Force
- **Time Complexity**: O(n²)
- **Space Complexity**: O(1)
- **Approach**: Check every pair of elements.
- **Best For**: Educational purposes, small inputs

## Solutions

- [Java Solutions](solutions/java/README.md)
- [Go Solutions](solutions/go/README.md)

## Performance Comparison

| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| **One-Pass Hash Table** | O(n) | O(n) | Most efficient, single pass |
| **Two-Pass Hash Table** | O(n) | O(n) | When phases need separation |
| **Brute Force** | O(n²) | O(1) | Small inputs, education |

## Analysis

- **Approach**: [analysis/approach.md](analysis/approach.md)
- **Complexity**: [analysis/complexity.md](analysis/complexity.md)
- **Test Cases**: [analysis/test-cases.md](analysis/test-cases.md)
- **Performance**: [analysis/PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md)

## Key Learning Points

1. **Hash Table for O(1) lookups**: Perfect for complement checking
2. **One-pass optimization**: Build and search simultaneously for efficiency
3. **Complement strategy**: target - current = complement
4. **Index tracking**: Store indices in hash map for return value

## Related Problems

- [Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) (Easy)
- [3Sum](https://leetcode.com/problems/3sum/) (Medium)
- [4Sum](https://leetcode.com/problems/4sum/) (Medium)

## Tags

- Array
- Hash Table
- Easy
