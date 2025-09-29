/**
 * Unit tests for Merge Strings Alternately solution
 *
 * This file contains comprehensive unit tests using Go's testing package.
 */
package main

import (
	"strings"
	"testing"
)

// TestMergeAlternately tests the main function with various inputs
func TestMergeAlternately(t *testing.T) {
	tests := []struct {
		word1, word2, expected string
	}{
		{"abc", "pqr", "apbqcr"},
		{"ab", "pqrs", "apbqrs"},
		{"abcd", "pq", "apbqcd"},
		{"a", "b", "ab"},
		{"", "abc", "abc"},
		{"abc", "", "abc"},
		{"", "", ""},
		{"aaa", "bbb", "ababab"},
		{"aceg", "bdfh", "abcdefgh"},
	}

	for _, test := range tests {
		result := mergeAlternately(test.word1, test.word2)
		if result != test.expected {
			t.Errorf("mergeAlternately(%q, %q) = %q, expected %q",
				test.word1, test.word2, result, test.expected)
		}
	}
}

// TestEdgeCases tests boundary conditions and edge cases
func TestEdgeCases(t *testing.T) {
	// Test maximum constraint values
	word1 := strings.Repeat("a", 100)
	word2 := strings.Repeat("b", 100)
	expected := strings.Repeat("ab", 100)
	result := mergeAlternately(word1, word2)

	if result != expected {
		t.Errorf("mergeAlternately(max strings) = %q, expected %q", result, expected)
	}

	// Test very different lengths
	result = mergeAlternately("a", strings.Repeat("b", 99))
	expected = "a" + strings.Repeat("b", 99)

	if result != expected {
		t.Errorf("mergeAlternately(different lengths) = %q, expected %q", result, expected)
	}

	// Test single character vs long string
	result = mergeAlternately("x", strings.Repeat("y", 50))
	expected = "x" + strings.Repeat("y", 50)

	if result != expected {
		t.Errorf("mergeAlternately(single vs long) = %q, expected %q", result, expected)
	}
}

// TestPerformance tests with large inputs
func TestPerformance(t *testing.T) {
	word1 := strings.Repeat("a", 1000)
	word2 := strings.Repeat("b", 1000)

	// Test that function can handle large inputs without panicking
	result := mergeAlternately(word1, word2)

	if len(result) != 2000 {
		t.Errorf("Expected result length 2000, got %d", len(result))
	}

	// Verify the result is correct
	expected := strings.Repeat("ab", 1000)
	if result != expected {
		t.Errorf("Large input result incorrect, expected %q, got %q", expected, result)
	}
}

// TestResultLength verifies that result length is always sum of input lengths
func TestResultLength(t *testing.T) {
	testCases := []struct {
		word1, word2 string
	}{
		{"abc", "pqr"},
		{"ab", "pqrs"},
		{"abcd", "pq"},
		{"a", "b"},
		{"", "abc"},
		{"abc", ""},
		{"", ""},
		{"aaa", "bbb"},
	}

	for _, tc := range testCases {
		result := mergeAlternately(tc.word1, tc.word2)
		expectedLength := len(tc.word1) + len(tc.word2)

		if len(result) != expectedLength {
			t.Errorf("Result length %d, expected %d for inputs %q, %q",
				len(result), expectedLength, tc.word1, tc.word2)
		}
	}
}

// TestAlternatingPattern verifies correct alternating pattern
func TestAlternatingPattern(t *testing.T) {
	// Test with equal length strings
	result := mergeAlternately("aceg", "bdfh")
	expected := "abcdefgh"

	if result != expected {
		t.Errorf("Alternating pattern failed: got %q, expected %q", result, expected)
	}

	// Test with different length strings
	result = mergeAlternately("ace", "bdfh")
	expected = "abcdefh"

	if result != expected {
		t.Errorf("Alternating pattern with different lengths failed: got %q, expected %q", result, expected)
	}
}

// TestRepeatedCharacters tests with repeated characters
func TestRepeatedCharacters(t *testing.T) {
	testCases := []struct {
		word1, word2, expected string
	}{
		{"aaa", "bbb", "ababab"},
		{"aa", "bbbb", "ababbb"},
		{"aaaa", "bb", "ababaa"},
	}

	for _, tc := range testCases {
		result := mergeAlternately(tc.word1, tc.word2)
		if result != tc.expected {
			t.Errorf("Repeated characters test failed: mergeAlternately(%q, %q) = %q, expected %q",
				tc.word1, tc.word2, result, tc.expected)
		}
	}
}

// TestEmptyStrings tests various empty string combinations
func TestEmptyStrings(t *testing.T) {
	testCases := []struct {
		word1, word2, expected string
	}{
		{"", "", ""},
		{"", "abc", "abc"},
		{"abc", "", "abc"},
		{"a", "", "a"},
		{"", "a", "a"},
	}

	for _, tc := range testCases {
		result := mergeAlternately(tc.word1, tc.word2)
		if result != tc.expected {
			t.Errorf("Empty string test failed: mergeAlternately(%q, %q) = %q, expected %q",
				tc.word1, tc.word2, result, tc.expected)
		}
	}
}

// BenchmarkMergeAlternately benchmarks the function with medium-sized inputs
func BenchmarkMergeAlternately(b *testing.B) {
	word1 := strings.Repeat("a", 100)
	word2 := strings.Repeat("b", 100)

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		mergeAlternately(word1, word2)
	}
}

// BenchmarkMergeAlternatelySmall benchmarks with small inputs
func BenchmarkMergeAlternatelySmall(b *testing.B) {
	word1 := "abc"
	word2 := "pqr"

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		mergeAlternately(word1, word2)
	}
}

// BenchmarkMergeAlternatelyLarge benchmarks with large inputs
func BenchmarkMergeAlternatelyLarge(b *testing.B) {
	word1 := strings.Repeat("a", 1000)
	word2 := strings.Repeat("b", 1000)

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		mergeAlternately(word1, word2)
	}
}

// BenchmarkMergeAlternatelyUnequal benchmarks with very different input sizes
func BenchmarkMergeAlternatelyUnequal(b *testing.B) {
	word1 := "a"
	word2 := strings.Repeat("b", 100)

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		mergeAlternately(word1, word2)
	}
}

// TestConcurrency tests that the function is safe for concurrent use
func TestConcurrency(t *testing.T) {
	// This test verifies that the function doesn't have race conditions
	// when called concurrently (though this particular function is stateless)

	done := make(chan bool, 10)

	for i := 0; i < 10; i++ {
		go func() {
			result := mergeAlternately("abc", "pqr")
			if result != "apbqcr" {
				t.Errorf("Concurrent execution failed: got %q, expected %q", result, "apbqcr")
			}
			done <- true
		}()
	}

	// Wait for all goroutines to complete
	for i := 0; i < 10; i++ {
		<-done
	}
}

// TestMemoryEfficiency tests that the function doesn't leak memory
func TestMemoryEfficiency(t *testing.T) {
	// Run the function many times to check for memory leaks
	for i := 0; i < 1000; i++ {
		result := mergeAlternately("abcdefghij", "klmnopqrst")
		if len(result) != 20 {
			t.Errorf("Memory efficiency test failed: expected length 20, got %d", len(result))
		}
	}
}
