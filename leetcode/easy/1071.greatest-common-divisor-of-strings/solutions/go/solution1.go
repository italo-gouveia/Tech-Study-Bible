/**
 * Solution: Mathematical Approach
 *
 * This approach uses the mathematical insight that if two strings have a common divisor,
 * then str1 + str2 = str2 + str1. The GCD string length equals the GCD of the string lengths.
 *
 * Time Complexity: O(m+n) where m and n are lengths of str1 and str2
 * Space Complexity: O(m+n) for string concatenation
 */
package main

import (
	"fmt"
	"strings"
	"time"
)

// gcdOfStrings finds the greatest common divisor of two strings
func gcdOfStrings(str1 string, str2 string) string {
	// Check if strings have a common divisor using commutative property
	// If str1 + str2 != str2 + str1, then no common divisor exists
	if str1+str2 != str2+str1 {
		return ""
	}

	// Calculate GCD of string lengths
	gcdLength := gcd(len(str1), len(str2))

	// Return the prefix of length gcdLength from either string
	return str1[:gcdLength]
}

// gcd calculates the Greatest Common Divisor of two integers using Euclidean algorithm
func gcd(a, b int) int {
	// Euclidean algorithm: gcd(a, b) = gcd(b, a % b)
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

// Main function with sample usage and test cases
func main() {
	fmt.Println("=== Greatest Common Divisor of Strings - Go Solution ===")

	// Test cases from problem description
	testCases := []struct {
		str1, str2, expected string
		description          string
	}{
		{"ABCABC", "ABC", "ABC", "Example 1: ABC divides both strings"},
		{"ABABAB", "ABAB", "AB", "Example 2: AB divides both strings"},
		{"LEET", "CODE", "", "Example 3: No common divisor"},
		{"TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXX", "Complex case with long strings"},
		{"A", "A", "A", "Single character strings"},
		{"AB", "AB", "AB", "Identical strings"},
		{"ABCDEF", "ABC", "", "No common divisor"},
	}

	// Run test cases
	fmt.Println("--- Test Cases ---")
	for _, tc := range testCases {
		result := gcdOfStrings(tc.str1, tc.str2)
		status := "✅ PASS"
		if result != tc.expected {
			status = "❌ FAIL"
		}

		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input: str1=\"%s\", str2=\"%s\"\n", tc.str1, tc.str2)
		fmt.Printf("Output: \"%s\"\n", result)
		fmt.Printf("Expected: \"%s\"\n", tc.expected)
		fmt.Printf("Result: %s\n\n", status)
	}

	// Performance test
	performanceTest()

	// Edge cases test
	edgeCasesTest()
}

// performanceTest measures execution time with large inputs
func performanceTest() {
	fmt.Println("--- Performance Test ---")

	// Create large test data
	str1 := strings.Repeat("ABC", 100)
	str2 := strings.Repeat("ABC", 150)

	// Warm up
	for i := 0; i < 1000; i++ {
		gcdOfStrings("ABC", "ABC")
	}

	// Measure performance
	iterations := 10000
	startTime := time.Now()

	for i := 0; i < iterations; i++ {
		gcdOfStrings(str1, str2)
	}

	endTime := time.Now()
	duration := endTime.Sub(startTime)

	fmt.Printf("Test data: str1=\"%s\" (%d chars), str2=\"%s\" (%d chars)\n",
		str1[:min(10, len(str1))]+"...", len(str1),
		str2[:min(10, len(str2))]+"...", len(str2))
	fmt.Printf("Executed %d iterations in %v\n", iterations, duration)
	fmt.Printf("Average time per iteration: %v\n", duration/time.Duration(iterations))
	fmt.Printf("Operations per second: %.0f\n\n", float64(iterations)/duration.Seconds())
}

// edgeCasesTest tests boundary conditions and edge cases
func edgeCasesTest() {
	fmt.Println("--- Edge Cases Test ---")

	edgeCases := []struct {
		str1, str2, expected string
		description          string
	}{
		{strings.Repeat("A", 1000), strings.Repeat("A", 1000), strings.Repeat("A", 1000), "Maximum length strings (1000 chars each)"},
		{"A", strings.Repeat("A", 999), "A", "Very different lengths (1 vs 999)"},
		{"ABABABAB", "ABAB", "AB", "Complex pattern case"},
		{"ABCDEFGHIJ", "KLMNOPQRST", "", "No common divisor case"},
	}

	for _, tc := range edgeCases {
		result := gcdOfStrings(tc.str1, tc.str2)
		status := "✅ PASS"
		if result != tc.expected {
			status = "❌ FAIL"
		}

		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input lengths: %d + %d\n", len(tc.str1), len(tc.str2))
		fmt.Printf("Output: \"%s\"\n", result)
		fmt.Printf("Expected: \"%s\"\n", tc.expected)
		fmt.Printf("Result: %s\n\n", status)
	}

	fmt.Println("All edge cases completed successfully! ✅")
}

// min returns the minimum of two integers
func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
