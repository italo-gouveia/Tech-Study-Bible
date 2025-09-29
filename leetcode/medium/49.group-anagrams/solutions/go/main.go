/**
 * Main executable for Group Anagrams problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */

package main

import (
	"fmt"
	"math/rand"
	"strings"
	"time"
)

// Main function with sample usage and test cases
func main() {
	fmt.Println("=== Group Anagrams - Go Solution ===")

	// Test cases from problem description
	testCases := []struct {
		input       []string
		description string
	}{
		{[]string{"eat", "tea", "tan", "ate", "nat", "bat"}, "Example 1: Multiple anagram groups"},
		{[]string{""}, "Example 2: Empty string"},
		{[]string{"a"}, "Example 3: Single character"},
		{[]string{"abc", "bca", "cab", "xyz", "zyx", "yxz"}, "Multiple groups with same size"},
		{[]string{"listen", "silent", "enlist", "inlets"}, "Single large anagram group"},
		{[]string{"a", "b", "c", "d"}, "No anagrams - all unique"},
	}

	// Run test cases for Solution 1
	fmt.Println("--- Solution 1: Hash Table with String Sorting ---")
	for _, tc := range testCases {
		result := groupAnagrams(tc.input)
		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input: %v\n", tc.input)
		fmt.Printf("Output: %v\n", result)
		fmt.Printf("Number of groups: %d\n\n", len(result))
	}

	// Run test cases for Solution 2
	fmt.Println("--- Solution 2: Hash Table with Character Frequency Counting ---")
	for _, tc := range testCases {
		result := groupAnagramsOptimized(tc.input)
		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input: %v\n", tc.input)
		fmt.Printf("Output: %v\n", result)
		fmt.Printf("Number of groups: %d\n\n", len(result))
	}

	// Performance test
	performanceTest()

	// Performance comparison with optimized solutions
	fmt.Println("\n=== Optimized Solutions Performance ===")
	runPerformanceComparison()

	// Edge cases test
	edgeCasesTest()
}

// performanceTest tests performance with various input sizes
func performanceTest() {
	fmt.Println("--- Performance Test ---")

	testData := [][]string{
		generateTestData(10, 5),    // Small dataset
		generateTestData(100, 10),  // Medium dataset
		generateTestData(1000, 20), // Large dataset
	}

	for i, testArray := range testData {
		fmt.Printf("Testing dataset %d: %d strings, avg length %d\n",
			i+1, len(testArray), getAverageLength(testArray))

		// Test Solution 1
		start := time.Now()
		for j := 0; j < 1000; j++ {
			groupAnagrams(testArray)
		}
		duration1 := time.Since(start)

		// Test Solution 2
		start = time.Now()
		for j := 0; j < 1000; j++ {
			groupAnagramsOptimized(testArray)
		}
		duration2 := time.Since(start)

		fmt.Printf("Solution 1 (Sorting): %v for 1,000 iterations\n", duration1)
		fmt.Printf("Solution 2 (Frequency): %v for 1,000 iterations\n", duration2)
		fmt.Printf("Performance ratio: %.2fx\n\n", float64(duration1)/float64(duration2))
	}
}

// edgeCasesTest tests boundary conditions
func edgeCasesTest() {
	fmt.Println("--- Edge Cases Test ---")

	edgeCases := [][]string{
		{""},                      // Single empty string
		{"a"},                     // Single character
		{"a", "a"},                // Duplicate strings
		{"abc", "abc", "abc"},     // Multiple duplicates
		{"a", "b", "c", "d", "e"}, // All unique
		{"listen", "silent", "enlist", "inlets", "tinsel"}, // Large anagram group
		{"", "a", "ab", "abc"},                             // Mixed lengths including empty
	}

	for i, testCase := range edgeCases {
		result1 := groupAnagrams(testCase)
		result2 := groupAnagramsOptimized(testCase)
		fmt.Printf("Edge case %d: Input: %v -> Solution1: %d groups, Solution2: %d groups\n",
			i+1, testCase, len(result1), len(result2))
	}

	fmt.Println("\nAll edge cases completed successfully! ✅")
}

// generateTestData generates test data with specified number of strings and average length
func generateTestData(numStrings, avgLength int) []string {
	result := make([]string, numStrings)
	rand.Seed(time.Now().UnixNano())

	for i := 0; i < numStrings; i++ {
		length := avgLength + rand.Intn(avgLength/2) // Vary length slightly
		var sb strings.Builder

		for j := 0; j < length; j++ {
			sb.WriteByte(byte('a' + rand.Intn(26)))
		}

		result[i] = sb.String()
	}

	return result
}

// getAverageLength calculates average length of strings in slice
func getAverageLength(strs []string) int {
	if len(strs) == 0 {
		return 0
	}

	totalLength := 0
	for _, str := range strs {
		totalLength += len(str)
	}

	return totalLength / len(strs)
}
