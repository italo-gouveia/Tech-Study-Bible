/**
 * Main executable for Valid Palindrome problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */

package main

import (
	"fmt"
	"math/rand"
	"time"
)

// Main function with sample usage and test cases
func main() {
	fmt.Println("=== Valid Palindrome - Go Solution ===")

	// Test cases from problem description
	testCases := []struct {
		input       string
		expected    bool
		description string
	}{
		{"A man, a plan, a canal: Panama", true, "Example 1: Complex palindrome with punctuation"},
		{"race a car", false, "Example 2: Not a palindrome"},
		{" ", true, "Example 3: Empty string after cleaning"},
		{"racecar", true, "Simple palindrome"},
		{"hello", false, "Not a palindrome"},
		{"a", true, "Single character"},
		{"", true, "Empty string"},
		{"A", true, "Single uppercase letter"},
		{"0P", false, "Edge case: 0P (0 and P)"},
		{"Madam, I'm Adam", true, "Complex palindrome with punctuation"},
		{"No 'x' in Nixon", true, "Complex palindrome with quotes"},
	}

	// Run test cases for Solution 1
	fmt.Println("--- Solution 1: Two Pointers with unicode functions ---")
	for _, tc := range testCases {
		result := isPalindrome(tc.input)
		status := "✅"
		if result != tc.expected {
			status = "❌"
		}
		fmt.Printf("%s Test: %s\n", status, tc.description)
		fmt.Printf("Input: \"%s\"\n", tc.input)
		fmt.Printf("Expected: %t, Got: %t\n\n", tc.expected, result)
	}

	// Run test cases for Solution 2
	fmt.Println("--- Solution 2: Two Pointers with Custom Character Check ---")
	for _, tc := range testCases {
		result := isPalindromeOptimized(tc.input)
		status := "✅"
		if result != tc.expected {
			status = "❌"
		}
		fmt.Printf("%s Test: %s\n", status, tc.description)
		fmt.Printf("Input: \"%s\"\n", tc.input)
		fmt.Printf("Expected: %t, Got: %t\n\n", tc.expected, result)
	}

	// Performance test
	performanceTest()

	// Edge cases test
	edgeCasesTest()
}

// performanceTest tests performance with various input sizes
func performanceTest() {
	fmt.Println("--- Performance Test ---")

	testStrings := []string{
		"A man, a plan, a canal: Panama",
		"race a car",
		"Madam, I'm Adam",
		"No 'x' in Nixon",
		generateLongPalindrome(1000),
		generateLongPalindrome(5000),
	}

	for _, testString := range testStrings {
		fmt.Printf("Testing string of length %d:\n", len(testString))

		// Test Solution 1
		start := time.Now()
		for i := 0; i < 10000; i++ {
			isPalindrome(testString)
		}
		duration1 := time.Since(start)

		// Test Solution 2
		start = time.Now()
		for i := 0; i < 10000; i++ {
			isPalindromeOptimized(testString)
		}
		duration2 := time.Since(start)

		fmt.Printf("Solution 1: %v for 10,000 iterations\n", duration1)
		fmt.Printf("Solution 2: %v for 10,000 iterations\n", duration2)
		fmt.Printf("Performance ratio: %.2fx\n\n", float64(duration1)/float64(duration2))
	}
}

// edgeCasesTest tests boundary conditions
func edgeCasesTest() {
	fmt.Println("--- Edge Cases Test ---")

	edgeCases := []string{
		"",            // Empty string
		"a",           // Single character
		"aa",          // Two same characters
		"ab",          // Two different characters
		"0P",          // Edge case mentioned in discussion
		"1Q",          // Similar edge case
		"2R",          // Similar edge case
		"aA",          // Case difference
		"a1A",         // Mixed alphanumeric
		"!@#$%^&*()",  // Only special characters
		"12321",       // Numeric palindrome
		"a1b2c3c2b1a", // Mixed alphanumeric palindrome
	}

	for _, testCase := range edgeCases {
		result1 := isPalindrome(testCase)
		result2 := isPalindromeOptimized(testCase)
		fmt.Printf("Input: \"%s\" -> Solution1: %t, Solution2: %t\n", testCase, result1, result2)
	}

	fmt.Println("\nAll edge cases completed successfully! ✅")
}

// generateLongPalindrome generates a long palindrome string for performance testing
func generateLongPalindrome(length int) string {
	rand.Seed(time.Now().UnixNano())

	// Generate first half
	firstHalf := make([]byte, length/2)
	for i := 0; i < length/2; i++ {
		firstHalf[i] = byte(rand.Intn(26) + 'a')
	}

	result := string(firstHalf)

	// Add middle character if odd length
	if length%2 == 1 {
		result += string(byte(rand.Intn(26) + 'a'))
	}

	// Mirror the first half
	for i := len(firstHalf) - 1; i >= 0; i-- {
		result += string(firstHalf[i])
	}

	return result
}
