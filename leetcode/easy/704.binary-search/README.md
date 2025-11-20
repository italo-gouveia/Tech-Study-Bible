# 704. Binary Search

- Problem: https://leetcode.com/problems/binary-search/
- Reference: https://neetcode.io/solutions/binary-search

## Problem Description

Given an integer array `nums` sorted in ascending order and a target value `target`, return the index where the target is found. If the target does **not** exist, return `-1`. Your algorithm must run in `O(log n)` time.

## Examples

```
Input: nums = [-1,0,2,4,6,8], target = 4
Output: 3
```

```
Input: nums = [-1,0,2,4,6,8], target = 3
Output: -1
```

```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
```

## Constraints

- `1 <= nums.length <= 10^4`
- `-10^4 < nums[i], target < 10^4`
- All integers in `nums` are **unique**
- `nums` is sorted in ascending order

## Approaches

### Solution 1: Recursive Binary Search
- **Time:** O(log n)
- **Space:** O(log n) for recursion stack
- **Idea:** Repeatedly divide the search interval in half, calling the function recursively on the half that can contain the target.
- **Key detail:** Base case is when `l > r`, meaning the target is absent.

### Solution 2: Iterative Binary Search (Preferred)
- **Time:** O(log n)
- **Space:** O(1)
- **Idea:** Use a loop to mirror the recursive halves using two pointers `l` and `r`. Compare the middle element to decide which half to keep.
- **Why it's preferred:** Avoids recursion overhead and uses constant space.

### Solution 3: Lower Bound Pattern
- **Time:** O(log n)
- **Space:** O(1)
- **Idea:** Standard “lower bound” (a.k.a. `bisect_left`): push `r` towards the first position where `nums[m] >= target`, then check if `nums[l]` equals the target.
- **Use case:** Great when you later extend to range queries or first-occurrence searches.

### Solution 4: Upper Bound Pattern
- **Time:** O(log n)
- **Space:** O(1)
- **Idea:** Standard “upper bound” (a.k.a. `bisect_right`): push `l` towards the first index where `nums[m] > target`, then inspect `nums[l-1]`.
- **Use case:** Helpful when you need the last occurrence of a number or the insertion point to the right.

### Solution 5: Built-in Helpers
- **Time:** O(log n)
- **Space:** O(1)
- **Idea (Go):** Use `sort.Search` with a predicate `nums[i] >= target` and verify.
- **Idea (Java):** Use `Arrays.binarySearch` and return the index if present; otherwise `-1`.
- **Why include it:** Shows how to lean on the standard library when allowed.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- Binary search only works on **sorted** data.
- Be careful computing the mid index: `m := l + (r-l)/2` avoids overflow.
- The lower/upper bound templates make it easy to adapt the logic to “first greater-or-equal” or “first greater” queries.
- Recursion is elegant but iteration is usually preferred to avoid stack overhead.

