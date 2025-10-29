package main

/**
 * Solution 2: Two-Pass Hash Table
 *
 * Time Complexity: O(n) - two passes through array
 * Space Complexity: O(n) - map stores all elements
 *
 * Approach:
 * - First pass: build map (value → index)
 * - Second pass: check for complement
 * - Ensure complement isn't the same element
 */
func twoSum(nums []int, target int) []int {
	m := make(map[int]int)

	// First pass: build map
	for i, num := range nums {
		m[num] = i
	}

	// Second pass: find complement
	for i, num := range nums {
		complement := target - num
		if idx, exists := m[complement]; exists && idx != i {
			return []int{i, idx}
		}
	}

	return []int{} // Should never reach here
}
