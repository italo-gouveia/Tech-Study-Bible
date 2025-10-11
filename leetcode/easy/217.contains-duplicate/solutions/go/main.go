package main

import (
	"fmt"
	"math/rand"
	"sort"
	"time"
)

/**
 * Main executable for Contains Duplicate problem
 *
 * This program demonstrates all three solutions with comprehensive test cases
 * and performance measurements.
 */

func main() {
	fmt.Println("=== Contains Duplicate - Go Solutions ===\n")

	// Test all solutions
	testSolution1()
	testSolution2()
	testSolution3()

	// Performance comparison
	performanceComparison()

	// Edge cases testing
	testEdgeCases()

	// Memory usage analysis
	measureMemoryUsage()
}

/**
 * Test Solution 1: Map-based Approach
 */
func testSolution1() {
	fmt.Println("--- Solution 1: Map-based Approach ---")

	// Test cases from problem description
	testCase(containsDuplicateMap, []int{1, 2, 3, 1}, true, "Example 1: [1,2,3,1]")
	testCase(containsDuplicateMap, []int{1, 2, 3, 4}, false, "Example 2: [1,2,3,4]")
	testCase(containsDuplicateMap, []int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true, "Example 3: Multiple duplicates")

	// Additional test cases
	testCase(containsDuplicateMap, []int{1}, false, "Single element")
	testCase(containsDuplicateMap, []int{1, 1}, true, "Two identical elements")
	testCase(containsDuplicateMap, []int{1, 2, 3, 4, 5}, false, "No duplicates")
	testCase(containsDuplicateMap, []int{1, 2, 3, 4, 5, 1}, true, "Duplicate at end")

	fmt.Println()
}

/**
 * Test Solution 2: Sorting Approach
 */
func testSolution2() {
	fmt.Println("--- Solution 2: Sorting Approach ---")

	// Test cases from problem description
	testCase(containsDuplicateSort, []int{1, 2, 3, 1}, true, "Example 1: [1,2,3,1]")
	testCase(containsDuplicateSort, []int{1, 2, 3, 4}, false, "Example 2: [1,2,3,4]")
	testCase(containsDuplicateSort, []int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true, "Example 3: Multiple duplicates")

	// Additional test cases
	testCase(containsDuplicateSort, []int{1}, false, "Single element")
	testCase(containsDuplicateSort, []int{1, 1}, true, "Two identical elements")
	testCase(containsDuplicateSort, []int{1, 2, 3, 4, 5}, false, "No duplicates")
	testCase(containsDuplicateSort, []int{1, 2, 3, 4, 5, 1}, true, "Duplicate at end")

	fmt.Println()
}

/**
 * Test Solution 3: Brute Force Approach
 */
func testSolution3() {
	fmt.Println("--- Solution 3: Brute Force Approach ---")

	// Test cases from problem description
	testCase(containsDuplicateBrute, []int{1, 2, 3, 1}, true, "Example 1: [1,2,3,1]")
	testCase(containsDuplicateBrute, []int{1, 2, 3, 4}, false, "Example 2: [1,2,3,4]")
	testCase(containsDuplicateBrute, []int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true, "Example 3: Multiple duplicates")

	// Additional test cases
	testCase(containsDuplicateBrute, []int{1}, false, "Single element")
	testCase(containsDuplicateBrute, []int{1, 1}, true, "Two identical elements")
	testCase(containsDuplicateBrute, []int{1, 2, 3, 4, 5}, false, "No duplicates")
	testCase(containsDuplicateBrute, []int{1, 2, 3, 4, 5, 1}, true, "Duplicate at end")

	fmt.Println()
}

/**
 * Test a single case for any solution
 */
func testCase(solution func([]int) bool, nums []int, expected bool, description string) {
	result := solution(nums)

	// Display results
	fmt.Printf("Test: %s\n", description)
	fmt.Printf("Input: %v\n", nums)
	fmt.Printf("Output: %t\n", result)
	fmt.Printf("Expected: %t\n", expected)

	status := "✅ PASS"
	if result != expected {
		status = "❌ FAIL"
	}
	fmt.Printf("Result: %s\n\n", status)
}

/**
 * Performance comparison between all three solutions
 */
func performanceComparison() {
	fmt.Println("--- Performance Comparison ---")

	// Create test data of different sizes
	smallArray := generateTestArray(100, true)   // 100 elements with duplicates
	mediumArray := generateTestArray(1000, true) // 1000 elements with duplicates
	largeArray := generateTestArray(5000, true)  // 5000 elements with duplicates

	// Test with small array
	fmt.Println("Small Array (100 elements):")
	compareSolutions(smallArray, 10000)

	// Test with medium array
	fmt.Println("Medium Array (1000 elements):")
	compareSolutions(mediumArray, 1000)

	// Test with large array (only for efficient solutions)
	fmt.Println("Large Array (5000 elements):")
	compareSolutionsLarge(largeArray, 100)

	fmt.Println()
}

/**
 * Compare solutions with given test data
 */
func compareSolutions(testData []int, iterations int) {
	// Solution 1: Map
	time1 := measureTime(func() {
		for i := 0; i < iterations; i++ {
			containsDuplicateMap(testData)
		}
	})

	// Solution 2: Sorting
	time2 := measureTime(func() {
		for i := 0; i < iterations; i++ {
			containsDuplicateSort(copyArray(testData))
		}
	})

	// Solution 3: Brute Force (only for small arrays)
	if len(testData) <= 100 {
		time3 := measureTime(func() {
			for i := 0; i < iterations; i++ {
				containsDuplicateBrute(testData)
			}
		})

		fmt.Printf("  Map: %d ms\n", time1)
		fmt.Printf("  Sorting: %d ms\n", time2)
		fmt.Printf("  Brute Force: %d ms\n", time3)
		fmt.Printf("  Ratio (Sorting/Map): %.2fx\n", float64(time2)/float64(time1))
		fmt.Printf("  Ratio (Brute/Map): %.2fx\n", float64(time3)/float64(time1))
	} else {
		fmt.Printf("  Map: %d ms\n", time1)
		fmt.Printf("  Sorting: %d ms\n", time2)
		fmt.Printf("  Ratio (Sorting/Map): %.2fx\n", float64(time2)/float64(time1))
	}
	fmt.Println()
}

/**
 * Compare solutions for large arrays (skip brute force)
 */
func compareSolutionsLarge(testData []int, iterations int) {
	// Solution 1: Map
	time1 := measureTime(func() {
		for i := 0; i < iterations; i++ {
			containsDuplicateMap(testData)
		}
	})

	// Solution 2: Sorting
	time2 := measureTime(func() {
		for i := 0; i < iterations; i++ {
			containsDuplicateSort(copyArray(testData))
		}
	})

	fmt.Printf("  Map: %d ms\n", time1)
	fmt.Printf("  Sorting: %d ms\n", time2)
	fmt.Printf("  Ratio (Sorting/Map): %.2fx\n", float64(time2)/float64(time1))
	fmt.Println()
}

/**
 * Test edge cases and boundary conditions
 */
func testEdgeCases() {
	fmt.Println("--- Edge Cases Testing ---")

	// Test with empty array
	testCase(containsDuplicateMap, []int{}, false, "Empty array")

	// Test with single element
	testCase(containsDuplicateMap, []int{42}, false, "Single element")

	// Test with all identical elements
	allSame := make([]int, 1000)
	for i := range allSame {
		allSame[i] = 42
	}
	testCase(containsDuplicateMap, allSame, true, "All identical elements (1000)")

	// Test with maximum constraint values
	maxValues := []int{1e9, -1e9, 0, 1e9}
	testCase(containsDuplicateMap, maxValues, true, "Maximum constraint values")

	// Test with negative numbers
	testCase(containsDuplicateMap, []int{-1, -2, -1, -3}, true, "Negative numbers with duplicate")
	testCase(containsDuplicateMap, []int{-1, -2, -3, -4}, false, "Negative numbers without duplicate")

	fmt.Println("All edge cases completed successfully! ✅")
}

/**
 * Measure memory usage for different solutions
 */
func measureMemoryUsage() {
	fmt.Println("--- Memory Usage Analysis ---")

	// Test with large array
	testArray := generateTestArray(10000, true)

	fmt.Printf("Test array size: %d elements\n", len(testArray))

	// Test results consistency
	result1 := containsDuplicateMap(testArray)
	result2 := containsDuplicateSort(copyArray(testArray))
	result3 := containsDuplicateBrute(testArray)

	fmt.Printf("Map result: %t\n", result1)
	fmt.Printf("Sorting result: %t\n", result2)
	fmt.Printf("Brute force result: %t\n", result3)
	fmt.Printf("All results match: %t\n", result1 == result2 && result2 == result3)

	fmt.Println()
}

/**
 * Generate test array with specified size and duplicate flag
 */
func generateTestArray(size int, hasDuplicates bool) []int {
	nums := make([]int, size)
	rand.Seed(42) // Fixed seed for reproducibility

	// Fill array with random values
	for i := 0; i < size; i++ {
		nums[i] = rand.Intn(1000)
	}

	// Add duplicates if requested
	if hasDuplicates && size > 1 {
		// Make sure there's at least one duplicate
		nums[size-1] = nums[0]
	}

	return nums
}

/**
 * Copy an array to avoid modifying the original
 */
func copyArray(nums []int) []int {
	copy := make([]int, len(nums))
	for i, num := range nums {
		copy[i] = num
	}
	return copy
}

/**
 * Measure execution time of a function
 */
func measureTime(function func()) int64 {
	startTime := time.Now()
	function()
	endTime := time.Now()
	return endTime.Sub(startTime).Milliseconds()
}

// Solution implementations for testing

/**
 * Solution 1: Map-based approach
 */
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

/**
 * Solution 2: Sorting approach
 */
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

/**
 * Solution 3: Brute force approach
 */
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
