# Test Cases for Two Sum

## Standard Test Cases

### Test Case 1: Basic Case
```
Input: nums = [2, 7, 11, 15], target = 9
Output: [0, 1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9
```

### Test Case 2: Non-consecutive
```
Input: nums = [3, 2, 4], target = 6
Output: [1, 2]
Explanation: nums[1] + nums[2] = 2 + 4 = 6
```

### Test Case 3: Duplicate values
```
Input: nums = [3, 3], target = 6
Output: [0, 1]
Explanation: nums[0] + nums[1] = 3 + 3 = 6
```

### Test Case 4: End elements
```
Input: nums = [4, 5, 6], target = 10
Output: [0, 2]
Explanation: nums[0] + nums[2] = 4 + 6 = 10
```

## Edge Cases

### Edge Case 1: Minimum size
```
Input: nums = [1, 2], target = 3
Output: [0, 1]
Explanation: Only two elements, both form the pair
```

### Edge Case 2: Negative numbers
```
Input: nums = [-1, -2, -3], target = -4
Output: [0, 2]
Explanation: nums[0] + nums[2] = -1 + (-3) = -4
```

### Edge Case 3: Mixed signs
```
Input: nums = [-1, 2, 3], target = 1
Output: [0, 1]
Explanation: nums[0] + nums[1] = -1 + 2 = 1
```

### Edge Case 4: Large numbers
```
Input: nums = [1000000000, 500000000], target = 1500000000
Output: [0, 1]
Explanation: Sum of two large numbers
```

### Edge Case 5: Zero sum
```
Input: nums = [1, -1, 2, 3], target = 0
Output: [0, 1]
Explanation: nums[0] + nums[1] = 1 + (-1) = 0
```

### Edge Case 6: All same elements
```
Input: nums = [5, 5, 5, 5], target = 10
Output: [0, 1]
Explanation: First pair of identical elements
```

## Stress Test Cases

### Large Array (n = 10,000)
```
nums = [random integers from -10^9 to 10^9]
target = some valid sum
Expected: O(n) time complexity should handle efficiently
```

### Sorted Array
```
Input: nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], target = 11
Output: [0, 9]
Explanation: First and last elements
```

### Unsorted Array
```
Input: nums = [9, 1, 7, 3, 5, 2, 8, 6, 4], target = 10
Output: Multiple valid pairs possible, one returned
```

## Invalid Test Cases (Should not occur per problem statement)

### Note: These are mentioned but shouldn't be in actual test cases
- Empty array (constraint: 2 <= nums.length)
- No solution (guaranteed one solution exists)
- Multiple solutions (only one valid answer exists)

## Verification Strategy

### For each test case:
1. Verify indices are valid (0 <= i, j < nums.length)
2. Verify indices are different (i ≠ j)
3. Verify sum equals target (nums[i] + nums[j] == target)
4. If multiple valid pairs exist, verify return format is consistent
