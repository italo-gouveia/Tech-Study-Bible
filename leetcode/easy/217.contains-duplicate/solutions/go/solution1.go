/**
 * Solution 1: Map-based Approach (Equivalent to HashSet)
 *
 * This approach uses a Go map to track elements we've seen so far.
 * If we encounter an element that's already in the map, we found a duplicate.
 *
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(n) - Map can contain up to n elements
 *
 * Advantages:
 * - Optimal time complexity for this problem
 * - Simple and intuitive
 * - Early termination when duplicate is found
 *
 * Disadvantages:
 * - Uses extra space proportional to input size
 * - Not suitable for space-constrained environments
 */
package main

// containsDuplicate checks if array contains any duplicates using a map
func containsDuplicate(nums []int) bool {
	// Early return for edge case
	if len(nums) <= 1 {
		return false
	}

	// Map to track seen elements
	seen := make(map[int]bool)

	// Iterate through each element
	for _, num := range nums {
		// If element already exists in map, we found a duplicate
		if seen[num] {
			return true // Early termination - duplicate found
		}
		// Add element to map
		seen[num] = true
	}

	// No duplicates found
	return false
}

// containsDuplicateStruct uses struct{} for better memory efficiency
// struct{} takes 0 bytes vs bool which takes 1 byte
func containsDuplicateStruct(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	// Map with struct{} values for memory efficiency
	seen := make(map[int]struct{})

	for _, num := range nums {
		if _, exists := seen[num]; exists {
			return true // Duplicate found
		}
		seen[num] = struct{}{} // Add to map
	}

	return false
}

// containsDuplicateWithCapacity pre-allocates map capacity for better performance
func containsDuplicateWithCapacity(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	// Pre-allocate map capacity for better performance
	seen := make(map[int]bool, len(nums))

	for _, num := range nums {
		if seen[num] {
			return true
		}
		seen[num] = true
	}

	return false
}
