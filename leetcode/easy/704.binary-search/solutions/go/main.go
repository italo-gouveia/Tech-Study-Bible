package main

import (
	"fmt"
	"sort"
)

// binarySearchRecursive performs a recursive binary search.
// Time: O(log n), Space: O(log n) recursion stack
func binarySearchRecursive(nums []int, target int) int {
	return binarySearchHelper(nums, target, 0, len(nums)-1)
}

func binarySearchHelper(nums []int, target, l, r int) int {
	if l > r {
		return -1
	}
	m := l + (r-l)/2
	if nums[m] == target {
		return m
	}
	if nums[m] < target {
		return binarySearchHelper(nums, target, m+1, r)
	}
	return binarySearchHelper(nums, target, l, m-1)
}

// binarySearchIterative performs the classic iterative binary search.
// Time: O(log n), Space: O(1)
func binarySearchIterative(nums []int, target int) int {
	l, r := 0, len(nums)-1
	for l <= r {
		m := l + (r-l)/2
		if nums[m] == target {
			return m
		}
		if nums[m] < target {
			l = m + 1
		} else {
			r = m - 1
		}
	}
	return -1
}

// binarySearchLowerBound finds the first index where nums[idx] >= target.
// Returns the index if nums[idx] == target, otherwise -1.
func binarySearchLowerBound(nums []int, target int) int {
	l, r := 0, len(nums)
	for l < r {
		m := l + (r-l)/2
		if nums[m] >= target {
			r = m
		} else {
			l = m + 1
		}
	}
	if l < len(nums) && nums[l] == target {
		return l
	}
	return -1
}

// binarySearchUpperBound finds the last index where nums[idx] <= target.
// Returns the index if nums[idx] == target, otherwise -1.
func binarySearchUpperBound(nums []int, target int) int {
	l, r := 0, len(nums)
	for l < r {
		m := l + (r-l)/2
		if nums[m] > target {
			r = m
		} else {
			l = m + 1
		}
	}
	if l > 0 && nums[l-1] == target {
		return l - 1
	}
	return -1
}

// binarySearchBuiltIn uses Go's sort.Search helper.
func binarySearchBuiltIn(nums []int, target int) int {
	idx := sort.Search(len(nums), func(i int) bool { return nums[i] >= target })
	if idx < len(nums) && nums[idx] == target {
		return idx
	}
	return -1
}

func main() {
	testCases := []struct {
		nums   []int
		target int
	}{
		{[]int{-1, 0, 2, 4, 6, 8}, 4},
		{[]int{-1, 0, 2, 4, 6, 8}, 3},
		{[]int{-1, 0, 3, 5, 9, 12}, 9},
		{[]int{-1, 0, 3, 5, 9, 12}, 2},
		{[]int{}, 1},
		{[]int{5}, 5},
	}

	for idx, tc := range testCases {
		fmt.Printf("Case %d: nums=%v target=%d\n", idx+1, tc.nums, tc.target)
		fmt.Printf("  Recursive: %d\n", binarySearchRecursive(tc.nums, tc.target))
		fmt.Printf("  Iterative: %d\n", binarySearchIterative(tc.nums, tc.target))
		fmt.Printf("  LowerBound: %d\n", binarySearchLowerBound(tc.nums, tc.target))
		fmt.Printf("  UpperBound: %d\n", binarySearchUpperBound(tc.nums, tc.target))
		fmt.Printf("  Built-in: %d\n", binarySearchBuiltIn(tc.nums, tc.target))
		fmt.Println()
	}
}
