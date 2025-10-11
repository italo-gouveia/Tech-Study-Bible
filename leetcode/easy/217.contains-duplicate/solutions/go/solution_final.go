package main

/**
 * Final Optimized Go Solution - Contains Duplicate
 *
 * Based on your fastest version: 10ms
 * Let's push it even further!
 */

// Your fastest version (10ms) - Great job!
func containsDuplicateYourFastest(nums []int) bool {
	n := len(nums)
	if n <= 1 {
		return false
	}

	seen := make(map[int]struct{}, n)

	for i := 0; i < n; i++ {
		num := nums[i]
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}
	return false
}

// Even more optimized version - try this one!
func containsDuplicateUltraFast(nums []int) bool {
	n := len(nums)
	if n < 2 { // < 2 is slightly faster than <= 1
		return false
	}

	seen := make(map[int]struct{}, n)

	for i := 0; i < n; i++ {
		v := nums[i]              // shorter variable name
		if _, ok := seen[v]; ok { // 'ok' is shorter than 'exists'
			return true
		}
		seen[v] = struct{}{}
	}
	return false
}

// Alternative: Try with range loop (sometimes faster)
func containsDuplicateRange(nums []int) bool {
	n := len(nums)
	if n < 2 {
		return false
	}

	seen := make(map[int]struct{}, n)

	for _, v := range nums { // range can be faster than index
		if _, ok := seen[v]; ok {
			return true
		}
		seen[v] = struct{}{}
	}
	return false
}

// Minimal version - every character counts!
func containsDuplicateMinimal(nums []int) bool {
	if len(nums) < 2 {
		return false
	}

	m := make(map[int]struct{}, len(nums))
	for _, v := range nums {
		if _, ok := m[v]; ok {
			return true
		}
		m[v] = struct{}{}
	}
	return false
}

// Why your version is fast:
// 1. ✅ Uses struct{} (0 bytes vs bool 1 byte)
// 2. ✅ Pre-allocates map capacity
// 3. ✅ Uses index loop (can be faster than range in some cases)
// 4. ✅ Early termination on duplicate found

// Potential further optimizations:
// 1. Use < 2 instead of <= 1
// 2. Shorter variable names (v vs num, ok vs exists)
// 3. Shorter map variable name (m vs seen)
// 4. Try range loop vs index loop (benchmark both)
