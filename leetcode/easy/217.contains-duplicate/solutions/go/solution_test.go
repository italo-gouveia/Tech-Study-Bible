package main

import (
	"fmt"
	"math/rand"
	"sort"
	"testing"
	"time"
)

// Test cases for all solutions
var testCases = []struct {
	name     string
	nums     []int
	expected bool
}{
	// Examples from problem description
	{"Example 1: [1,2,3,1]", []int{1, 2, 3, 1}, true},
	{"Example 2: [1,2,3,4]", []int{1, 2, 3, 4}, false},
	{"Example 3: Multiple duplicates", []int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true},

	// Edge cases
	{"Empty array", []int{}, false},
	{"Single element", []int{1}, false},
	{"Two identical elements", []int{1, 1}, true},
	{"No duplicates", []int{1, 2, 3, 4, 5}, false},
	{"Duplicate at end", []int{1, 2, 3, 4, 5, 1}, true},
	{"Duplicate at beginning", []int{1, 1, 2, 3, 4}, true},
	{"All identical", []int{2, 2, 2, 2}, true},

	// Boundary cases
	{"Maximum constraint values", []int{1e9, -1e9, 0, 1e9}, true},
	{"Negative numbers with duplicate", []int{-1, -2, -1, -3}, true},
	{"Negative numbers without duplicate", []int{-1, -2, -3, -4}, false},
	{"Mixed positive and negative", []int{-1, 2, -3, 4, -1}, true},
}

// Test Solution 1: Map-based approach
func TestContainsDuplicateMap(t *testing.T) {
	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := containsDuplicateMap(tc.nums)
			if result != tc.expected {
				t.Errorf("containsDuplicateMap(%v) = %v; expected %v", tc.nums, result, tc.expected)
			}
		})
	}
}

// Test Solution 2: Sorting approach
func TestContainsDuplicateSort(t *testing.T) {
	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			// Create a copy to avoid modifying the original
			numsCopy := make([]int, len(tc.nums))
			copy(numsCopy, tc.nums)

			result := containsDuplicateSort(numsCopy)
			if result != tc.expected {
				t.Errorf("containsDuplicateSort(%v) = %v; expected %v", tc.nums, result, tc.expected)
			}
		})
	}
}

// Test Solution 3: Brute force approach
func TestContainsDuplicateBrute(t *testing.T) {
	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := containsDuplicateBrute(tc.nums)
			if result != tc.expected {
				t.Errorf("containsDuplicateBrute(%v) = %v; expected %v", tc.nums, result, tc.expected)
			}
		})
	}
}

// Test all solutions produce the same results
func TestSolutionsConsistency(t *testing.T) {
	// Generate random test cases
	rand.Seed(time.Now().UnixNano())

	for i := 0; i < 100; i++ {
		// Generate random array
		size := rand.Intn(50) + 1 // 1 to 50 elements
		nums := make([]int, size)
		for j := range nums {
			nums[j] = rand.Intn(100) - 50 // -50 to 49
		}

		// Test all solutions
		resultMap := containsDuplicateMap(nums)
		resultSort := containsDuplicateSort(append([]int(nil), nums...)) // Copy
		resultBrute := containsDuplicateBrute(nums)

		// All should give the same result
		if resultMap != resultSort || resultSort != resultBrute {
			t.Errorf("Inconsistent results for %v: Map=%v, Sort=%v, Brute=%v",
				nums, resultMap, resultSort, resultBrute)
		}
	}
}

// Benchmark Solution 1: Map-based approach
func BenchmarkContainsDuplicateMap(b *testing.B) {
	sizes := []int{100, 500, 1000, 5000, 10000}

	for _, size := range sizes {
		nums := generateTestArray(size, true)
		b.Run(fmt.Sprintf("Size_%d", size), func(b *testing.B) {
			for i := 0; i < b.N; i++ {
				containsDuplicateMap(nums)
			}
		})
	}
}

// Benchmark Solution 2: Sorting approach
func BenchmarkContainsDuplicateSort(b *testing.B) {
	sizes := []int{100, 500, 1000, 5000, 10000}

	for _, size := range sizes {
		nums := generateTestArray(size, true)
		b.Run(fmt.Sprintf("Size_%d", size), func(b *testing.B) {
			for i := 0; i < b.N; i++ {
				// Create a copy for each iteration to avoid modifying the original
				numsCopy := make([]int, len(nums))
				copy(numsCopy, nums)
				containsDuplicateSort(numsCopy)
			}
		})
	}
}

// Benchmark Solution 3: Brute force approach (only for small sizes)
func BenchmarkContainsDuplicateBrute(b *testing.B) {
	sizes := []int{10, 50, 100} // Only small sizes due to O(n²) complexity

	for _, size := range sizes {
		nums := generateTestArray(size, true)
		b.Run(fmt.Sprintf("Size_%d", size), func(b *testing.B) {
			for i := 0; i < b.N; i++ {
				containsDuplicateBrute(nums)
			}
		})
	}
}

// Benchmark comparison between approaches
func BenchmarkApproachComparison(b *testing.B) {
	size := 1000
	nums := generateTestArray(size, true)

	b.Run("Map", func(b *testing.B) {
		for i := 0; i < b.N; i++ {
			containsDuplicateMap(nums)
		}
	})

	b.Run("Sort", func(b *testing.B) {
		for i := 0; i < b.N; i++ {
			numsCopy := make([]int, len(nums))
			copy(numsCopy, nums)
			containsDuplicateSort(numsCopy)
		}
	})
}

// Memory benchmark
func BenchmarkMemoryUsage(b *testing.B) {
	size := 10000
	nums := generateTestArray(size, true)

	b.Run("Map_Memory", func(b *testing.B) {
		b.ReportAllocs()
		for i := 0; i < b.N; i++ {
			containsDuplicateMap(nums)
		}
	})

	b.Run("Sort_Memory", func(b *testing.B) {
		b.ReportAllocs()
		for i := 0; i < b.N; i++ {
			numsCopy := make([]int, len(nums))
			copy(numsCopy, nums)
			containsDuplicateSort(numsCopy)
		}
	})
}

// Test edge cases with nil and empty arrays
func TestEdgeCases(t *testing.T) {
	// Test with nil slice (should not panic)
	defer func() {
		if r := recover(); r != nil {
			t.Errorf("Function panicked with nil slice: %v", r)
		}
	}()

	// These should all return false without panicking
	testCases := [][]int{nil, {}, {1}}
	expected := []bool{false, false, false}

	for i, nums := range testCases {
		resultMap := containsDuplicateMap(nums)
		resultSort := containsDuplicateSort(append([]int(nil), nums...))
		resultBrute := containsDuplicateBrute(nums)

		expectedResult := expected[i]
		if resultMap != expectedResult {
			t.Errorf("Map: containsDuplicateMap(%v) = %v; expected %v", nums, resultMap, expectedResult)
		}
		if resultSort != expectedResult {
			t.Errorf("Sort: containsDuplicateSort(%v) = %v; expected %v", nums, resultSort, expectedResult)
		}
		if resultBrute != expectedResult {
			t.Errorf("Brute: containsDuplicateBrute(%v) = %v; expected %v", nums, resultBrute, expectedResult)
		}
	}
}

// Test with very large arrays to check for overflow/performance issues
func TestLargeArrays(t *testing.T) {
	if testing.Short() {
		t.Skip("Skipping large array tests in short mode")
	}

	// Test with maximum constraint size
	size := 100000
	nums := generateTestArray(size, true)

	// All solutions should handle large arrays without panicking
	resultMap := containsDuplicateMap(nums)
	resultSort := containsDuplicateSort(append([]int(nil), nums...))

	if resultMap != resultSort {
		t.Errorf("Inconsistent results for large array: Map=%v, Sort=%v", resultMap, resultSort)
	}
}

// Helper functions for testing

// containsDuplicateMap - Solution 1 implementation for testing
func containsDuplicateMap(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	seen := make(map[int]bool)
	for _, num := range nums {
		if seen[num] {
			return true
		}
		seen[num] = true
	}
	return false
}

// containsDuplicateSort - Solution 2 implementation for testing
func containsDuplicateSort(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			return true
		}
	}
	return false
}

// containsDuplicateBrute - Solution 3 implementation for testing
func containsDuplicateBrute(nums []int) bool {
	if len(nums) <= 1 {
		return false
	}

	for i := 0; i < len(nums)-1; i++ {
		for j := i + 1; j < len(nums); j++ {
			if nums[i] == nums[j] {
				return true
			}
		}
	}
	return false
}

// generateTestArray creates a test array with specified size and duplicate flag
func generateTestArray(size int, hasDuplicates bool) []int {
	nums := make([]int, size)

	// Fill with random values
	for i := 0; i < size; i++ {
		nums[i] = rand.Intn(1000)
	}

	// Add duplicates if requested
	if hasDuplicates && size > 1 {
		nums[size-1] = nums[0] // Ensure at least one duplicate
	}

	return nums
}

// Example function to demonstrate usage
func ExampleContainsDuplicate() {
	nums1 := []int{1, 2, 3, 1}
	nums2 := []int{1, 2, 3, 4}

	fmt.Println(containsDuplicateMap(nums1)) // true
	fmt.Println(containsDuplicateMap(nums2)) // false

	// Output:
	// true
	// false
}
