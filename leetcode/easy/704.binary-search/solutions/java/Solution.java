package leetcode.easy.binarysearch;

import java.util.Arrays;

// LeetCode 704 — Binary Search
public class Solution {

    // Solution 1: Recursive binary search.
    // Time: O(log n), Space: O(log n) for recursion stack.
    public int searchRecursive(int[] nums, int target) {
        return helper(nums, target, 0, nums.length - 1);
    }

    private int helper(int[] nums, int target, int l, int r) {
        if (l > r) {
            return -1;
        }
        int m = l + (r - l) / 2;
        if (nums[m] == target) {
            return m;
        }
        if (nums[m] < target) {
            return helper(nums, target, m + 1, r);
        }
        return helper(nums, target, l, m - 1);
    }

    // Solution 2: Iterative binary search (preferred).
    // Time: O(log n), Space: O(1)
    public int searchIterative(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    // Solution 3: Lower bound (first position >= target)
    public int searchLowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l < nums.length && nums[l] == target) {
            return l;
        }
        return -1;
    }

    // Solution 4: Upper bound (first position > target)
    public int searchUpperBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l > 0 && nums[l - 1] == target) {
            return l - 1;
        }
        return -1;
    }

    // Solution 5: Built-in helper (Arrays.binarySearch)
    public int searchBuiltIn(int[] nums, int target) {
        int idx = Arrays.binarySearch(nums, target);
        return idx >= 0 ? idx : -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] tests = {
            {-1, 0, 2, 4, 6, 8},
            {-1, 0, 2, 4, 6, 8},
            {-1, 0, 3, 5, 9, 12},
            {-1, 0, 3, 5, 9, 12},
            {},
            {5}
        };
        int[] targets = {4, 3, 9, 2, 1, 5};

        for (int i = 0; i < tests.length; i++) {
            int[] nums = tests[i];
            int target = targets[i];
            System.out.printf("Case %d: nums=%s target=%d%n", i + 1, Arrays.toString(nums), target);
            System.out.printf("  Recursive: %d%n", solution.searchRecursive(nums, target));
            System.out.printf("  Iterative: %d%n", solution.searchIterative(nums, target));
            System.out.printf("  LowerBound: %d%n", solution.searchLowerBound(nums, target));
            System.out.printf("  UpperBound: %d%n", solution.searchUpperBound(nums, target));
            System.out.printf("  Built-in: %d%n%n", solution.searchBuiltIn(nums, target));
        }
    }
}

