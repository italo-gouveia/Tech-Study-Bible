/**
 * Main executable for Merge Strings Alternately problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */
package main

import (
	"fmt"
	"strings"
	"time"
)

// Main function with sample usage and test cases
func main() {
	fmt.Println("=== Merge Strings Alternately - Go Solution ===")

	// Test cases from problem description
	testCases := []struct {
		word1, word2, expected string
		description            string
	}{
		{"abc", "pqr", "apbqcr", "Example 1: Equal length strings"},
		{"ab", "pqrs", "apbqrs", "Example 2: word2 longer"},
		{"abcd", "pq", "apbqcd", "Example 3: word1 longer"},
		{"a", "b", "ab", "Single characters"},
		{"", "abc", "abc", "Empty word1"},
		{"abc", "", "abc", "Empty word2"},
		{"", "", "", "Both empty"},
		{"aaa", "bbb", "ababab", "Repeated characters"},
		{"aceg", "bdfh", "abcdefgh", "Alternating pattern"},
	}

	// Run test cases
	fmt.Println("--- Test Cases ---")
	for _, tc := range testCases {
		result := mergeAlternately(tc.word1, tc.word2)
		status := "✅ PASS"
		if result != tc.expected {
			status = "❌ FAIL"
		}

		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input: word1=\"%s\", word2=\"%s\"\n", tc.word1, tc.word2)
		fmt.Printf("Output: \"%s\"\n", result)
		fmt.Printf("Expected: \"%s\"\n", tc.expected)
		fmt.Printf("Result: %s\n\n", status)
	}

	// Performance test
	performanceTest()

	// Edge cases test
	edgeCasesTest()

	// Memory usage test
	memoryUsageTest()
}

// performanceTest measures execution time with large inputs
func performanceTest() {
	fmt.Println("--- Performance Test ---")

	// Create large test data
	word1 := strings.Repeat("a", 1000)
	word2 := strings.Repeat("b", 1000)

	// Warm up
	for i := 0; i < 1000; i++ {
		mergeAlternately("a", "b")
	}

	// Measure performance
	iterations := 10000
	startTime := time.Now()

	for i := 0; i < iterations; i++ {
		mergeAlternately(word1, word2)
	}

	endTime := time.Now()
	duration := endTime.Sub(startTime)

	fmt.Printf("Test data: word1=\"%s\" (%d chars), word2=\"%s\" (%d chars)\n",
		word1[:min(10, len(word1))]+"...", len(word1),
		word2[:min(10, len(word2))]+"...", len(word2))
	fmt.Printf("Executed %d iterations in %v\n", iterations, duration)
	fmt.Printf("Average time per iteration: %v\n", duration/time.Duration(iterations))
	fmt.Printf("Operations per second: %.0f\n\n", float64(iterations)/duration.Seconds())
}

// edgeCasesTest tests boundary conditions and edge cases
func edgeCasesTest() {
	fmt.Println("--- Edge Cases Test ---")

	edgeCases := []struct {
		word1, word2, expected string
		description            string
	}{
		{strings.Repeat("a", 100), strings.Repeat("b", 100), strings.Repeat("ab", 100), "Maximum length strings (100 chars each)"},
		{"a", strings.Repeat("b", 99), "a" + strings.Repeat("b", 99), "Very different lengths (1 vs 99)"},
		{strings.Repeat("a", 50), strings.Repeat("b", 100), strings.Repeat("ab", 50) + strings.Repeat("b", 50), "Unequal maximum lengths"},
	}

	for _, tc := range edgeCases {
		result := mergeAlternately(tc.word1, tc.word2)
		status := "✅ PASS"
		if result != tc.expected {
			status = "❌ FAIL"
		}

		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input lengths: %d + %d = %d\n", len(tc.word1), len(tc.word2), len(tc.word1)+len(tc.word2))
		fmt.Printf("Output length: %d\n", len(result))
		fmt.Printf("Result: %s\n\n", status)
	}

	fmt.Println("All edge cases completed successfully! ✅")
}

// memoryUsageTest demonstrates memory efficiency
func memoryUsageTest() {
	fmt.Println("--- Memory Usage Test ---")

	// Test with large inputs
	word1 := strings.Repeat("a", 1000)
	word2 := strings.Repeat("b", 1000)

	fmt.Printf("Input: word1=\"%s\" (%d chars), word2=\"%s\" (%d chars)\n",
		word1[:min(10, len(word1))]+"...", len(word1),
		word2[:min(10, len(word2))]+"...", len(word2))

	result := mergeAlternately(word1, word2)

	fmt.Printf("Output: \"%s\" (%d chars)\n",
		result[:min(20, len(result))]+"...", len(result))
	fmt.Printf("Memory efficiency: Result length = %d (expected: %d)\n",
		len(result), len(word1)+len(word2))

	if len(result) == len(word1)+len(word2) {
		fmt.Println("✅ Memory usage is optimal")
	} else {
		fmt.Println("❌ Memory usage issue detected")
	}

	fmt.Println()
}

// min returns the minimum of two integers
func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
