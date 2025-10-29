package main

/**
 * Solution 1: One-Pass Hash Table (Optimal)
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - map stores at most n elements
 *
 * Approach:
 * - Use map[int]int to store seen elements (value → index)
 * - For each element, calculate complement = target - current
 * - Check if complement exists in map
 * - If found, return indices; otherwise, add current to map
 *
 * Optimizations:
 * - Pre-allocate map with estimated capacity
 * - Use indexed loop instead of range for better performance
 */
func twoSum(nums []int, target int) []int {
	// Pré-aloca o map com capacidade estimada
	m := make(map[int]int, len(nums))

	for i := 0; i < len(nums); i++ {
		complement := target - nums[i]
		if idx, exists := m[complement]; exists {
			return []int{idx, i}
		}
		m[nums[i]] = i
	}

	return []int{} // Should never reach here
}
