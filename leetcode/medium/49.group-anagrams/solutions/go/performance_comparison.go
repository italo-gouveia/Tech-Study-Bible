/**
 * Performance comparison for all Group Anagrams solutions
 */

package main

import (
	"fmt"
	"time"
)

// Performance test runner
func runPerformanceComparison() {
	fmt.Println("=== Group Anagrams Performance Comparison ===")

	// Test data of varying sizes
	testData := [][]string{
		generateTestData(10, 5),    // Small dataset
		generateTestData(100, 10),  // Medium dataset
		generateTestData(1000, 20), // Large dataset
	}

	for i, testArray := range testData {
		fmt.Printf("\n--- Dataset %d: %d strings, avg length %d ---\n",
			i+1, len(testArray), getAverageLength(testArray))

		// Test existing solutions only
		testSolution("Sorting", groupAnagrams, testArray)
		testSolution("Sorting (bytes)", groupAnagramsSortingOptimized, testArray)
		testSolution("Frequency", groupAnagramsOptimized, testArray)
		testSolution("Frequency (bytes)", groupAnagramsFrequencyOptimized, testArray)
	}
}

// Test a single solution
func testSolution(name string, solution func([]string) [][]string, testArray []string) {
	// Warm up
	for i := 0; i < 100; i++ {
		solution(testArray)
	}

	// Actual test
	start := time.Now()
	for i := 0; i < 1000; i++ {
		solution(testArray)
	}
	duration := time.Since(start)

	fmt.Printf("%-25s: %v for 1,000 iterations\n", name, duration)
}
