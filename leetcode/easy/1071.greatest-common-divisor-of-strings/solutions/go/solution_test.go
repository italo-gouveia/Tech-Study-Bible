package main

import (
	"strings"
	"testing"
	"time"
)

// Unit tests using Go's testing package
func TestGcdOfStrings(t *testing.T) {
	tests := []struct {
		str1, str2, expected string
	}{
		{"ABCABC", "ABC", "ABC"},
		{"ABABAB", "ABAB", "AB"},
		{"LEET", "CODE", ""},
		{"TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXX"},
		{"A", "A", "A"},
		{"AB", "AB", "AB"},
		{"ABCDEF", "ABC", ""},
	}

	for _, test := range tests {
		result := gcdOfStrings(test.str1, test.str2)
		if result != test.expected {
			t.Errorf("gcdOfStrings(%q, %q) = %q, expected %q",
				test.str1, test.str2, result, test.expected)
		}
	}
}

// TestEdgeCases tests boundary conditions
func TestEdgeCases(t *testing.T) {
	// Test with maximum constraint values
	maxStr1 := strings.Repeat("A", 1000)
	maxStr2 := strings.Repeat("A", 1000)
	result := gcdOfStrings(maxStr1, maxStr2)
	if result != maxStr1 {
		t.Errorf("Expected %q, got %q", maxStr1, result)
	}

	// Test with very different lengths
	shortStr := "A"
	longStr := strings.Repeat("A", 999)
	expected := "A"
	result = gcdOfStrings(shortStr, longStr)
	if result != expected {
		t.Errorf("Expected %q, got %q", expected, result)
	}

	// Test with no common divisor
	noDivisor1 := "ABCDEFGHIJ"
	noDivisor2 := "KLMNOPQRST"
	result = gcdOfStrings(noDivisor1, noDivisor2)
	if result != "" {
		t.Errorf("Expected empty string, got %q", result)
	}
}

// TestPerformance tests performance with large inputs
func TestPerformance(t *testing.T) {
	// Create large test strings
	str1 := strings.Repeat("ABC", 100)
	str2 := strings.Repeat("ABC", 150)

	// Measure execution time
	start := time.Now()
	for i := 0; i < 10000; i++ {
		gcdOfStrings(str1, str2)
	}
	duration := time.Since(start)

	// Performance should be reasonable (less than 1 second for 10k iterations)
	if duration > time.Second {
		t.Errorf("Performance test took too long: %v", duration)
	}

	t.Logf("Performance test: 10,000 iterations completed in %v", duration)
}

// Benchmark tests
func BenchmarkGcdOfStrings(b *testing.B) {
	str1 := "ABCABC"
	str2 := "ABC"

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		gcdOfStrings(str1, str2)
	}
}

func BenchmarkGcdOfStringsLarge(b *testing.B) {
	str1 := strings.Repeat("ABC", 100)
	str2 := strings.Repeat("ABC", 150)

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		gcdOfStrings(str1, str2)
	}
}
