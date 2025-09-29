/**
 * Unit tests and benchmarks for Valid Palindrome solutions
 */

package main

import (
	"testing"
)

// Test cases for isPalindrome function
func TestIsPalindrome(t *testing.T) {
	testCases := []struct {
		input    string
		expected bool
		name     string
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
		{"aa", true, "Two same characters"},
		{"ab", false, "Two different characters"},
		{"1Q", false, "Edge case: 1Q"},
		{"2R", false, "Edge case: 2R"},
		{"aA", true, "Case difference"},
		{"a1A", true, "Mixed alphanumeric"},
		{"!@#$%^&*()", true, "Only special characters"},
		{"12321", true, "Numeric palindrome"},
		{"a1b2c3c2b1a", true, "Mixed alphanumeric palindrome"},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := isPalindrome(tc.input)
			if result != tc.expected {
				t.Errorf("isPalindrome(%q) = %t; want %t", tc.input, result, tc.expected)
			}
		})
	}
}

// Test cases for isPalindromeOptimized function
func TestIsPalindromeOptimized(t *testing.T) {
	testCases := []struct {
		input    string
		expected bool
		name     string
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
		{"aa", true, "Two same characters"},
		{"ab", false, "Two different characters"},
		{"1Q", false, "Edge case: 1Q"},
		{"2R", false, "Edge case: 2R"},
		{"aA", true, "Case difference"},
		{"a1A", true, "Mixed alphanumeric"},
		{"!@#$%^&*()", true, "Only special characters"},
		{"12321", true, "Numeric palindrome"},
		{"a1b2c3c2b1a", true, "Mixed alphanumeric palindrome"},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := isPalindromeOptimized(tc.input)
			if result != tc.expected {
				t.Errorf("isPalindromeOptimized(%q) = %t; want %t", tc.input, result, tc.expected)
			}
		})
	}
}

// Test edge cases
func TestEdgeCases(t *testing.T) {
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
		t.Run("EdgeCase_"+testCase, func(t *testing.T) {
			result1 := isPalindrome(testCase)
			result2 := isPalindromeOptimized(testCase)

			if result1 != result2 {
				t.Errorf("Solutions disagree on %q: isPalindrome=%t, isPalindromeOptimized=%t",
					testCase, result1, result2)
			}
		})
	}
}

// Benchmark isPalindrome function
func BenchmarkIsPalindrome(b *testing.B) {
	testString := "A man, a plan, a canal: Panama"
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindrome(testString)
	}
}

// Benchmark isPalindromeOptimized function
func BenchmarkIsPalindromeOptimized(b *testing.B) {
	testString := "A man, a plan, a canal: Panama"
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindromeOptimized(testString)
	}
}

// Benchmark with different string lengths
func BenchmarkIsPalindromeLarge(b *testing.B) {
	testString := generateLongPalindrome(1000)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindrome(testString)
	}
}

func BenchmarkIsPalindromeOptimizedLarge(b *testing.B) {
	testString := generateLongPalindrome(1000)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindromeOptimized(testString)
	}
}

// Benchmark with very long strings
func BenchmarkIsPalindromeVeryLarge(b *testing.B) {
	testString := generateLongPalindrome(10000)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindrome(testString)
	}
}

func BenchmarkIsPalindromeOptimizedVeryLarge(b *testing.B) {
	testString := generateLongPalindrome(10000)
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		isPalindromeOptimized(testString)
	}
}
