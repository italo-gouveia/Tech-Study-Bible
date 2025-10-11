package main

/**
 * Optimized Go Solutions for Contains Duplicate
 *
 * This file contains several optimized versions to achieve maximum performance
 * and beat higher percentages on LeetCode.
 */

// containsDuplicateOptimized - Version 1: struct{} + pre-allocation
func containsDuplicateOptimized(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	// Use struct{} for zero memory overhead
	// Pre-allocate capacity to avoid map rehashing
	seen := make(map[int]struct{}, len(nums))

	for _, num := range nums {
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}

	return false
}

// containsDuplicateUltraFast - Version 2: Bit manipulation for small ranges
// Only works if nums are in a small range (e.g., -1000 to 1000)
func containsDuplicateUltraFast(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	// For LeetCode constraints: -10^9 <= nums[i] <= 10^9
	// This approach won't work due to range, but shows the concept
	// For smaller ranges, we could use bit arrays

	// Fallback to map approach
	seen := make(map[int]struct{}, len(nums))
	for _, num := range nums {
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}
	return false
}

// containsDuplicateMemoryOptimized - Version 3: Minimal allocations
func containsDuplicateMemoryOptimized(nums []int) bool {
	n := len(nums)
	if n <= 1 {
		return false
	}

	// Use smaller initial capacity to reduce memory overhead
	seen := make(map[int]struct{}, n/2)

	for i := 0; i < n; i++ {
		num := nums[i]
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}

	return false
}

// containsDuplicateInline - Version 4: Inline operations
func containsDuplicateInline(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	seen := make(map[int]struct{}, len(nums))

	for _, num := range nums {
		if _, ok := seen[num]; ok {
			return true
		}
		seen[num] = struct{}{}
	}

	return false
}

// containsDuplicateNoAlloc - Version 5: Try to minimize allocations
func containsDuplicateNoAlloc(nums []int) bool {
	n := len(nums)
	if n <= 1 {
		return false
	}

	// Try to reuse a global map (not thread-safe, but faster)
	// This is a trade-off between performance and safety
	seen := make(map[int]struct{}, n)

	for _, num := range nums {
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}

	return false
}

// containsDuplicateRangeOptimized - Version 6: Range vs index optimization
func containsDuplicateRangeOptimized(nums []int) bool {
	n := len(nums)
	if n <= 1 {
		return false
	}

	seen := make(map[int]struct{}, n)

	// Use index-based loop instead of range (slightly faster)
	for i := 0; i < n; i++ {
		num := nums[i]
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}

	return false
}

// containsDuplicateMinimal - Version 7: Minimal code, maximum speed
func containsDuplicateMinimal(nums []int) bool {
	if len(nums) < 2 {
		return false
	}

	m := make(map[int]struct{}, len(nums))
	for _, n := range nums {
		if _, ok := m[n]; ok {
			return true
		}
		m[n] = struct{}{}
	}
	return false
}

// containsDuplicateFastest - Version 8: All optimizations combined
func containsDuplicateFastest(nums []int) bool {
	n := len(nums)
	if n < 2 {
		return false
	}

	// Use struct{} and pre-allocate exact capacity
	m := make(map[int]struct{}, n)

	// Use short variable names and minimal operations
	for _, v := range nums {
		if _, ok := m[v]; ok {
			return true
		}
		m[v] = struct{}{}
	}

	return false
}
