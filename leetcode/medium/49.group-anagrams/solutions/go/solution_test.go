/**
 * Unit tests and benchmarks for Group Anagrams solutions
 */

package main

import (
	"fmt"
	"reflect"
	"testing"
)

// Test cases for groupAnagrams function
func TestGroupAnagrams(t *testing.T) {
	testCases := []struct {
		input    []string
		expected [][]string
		name     string
	}{
		{
			[]string{"eat", "tea", "tan", "ate", "nat", "bat"},
			[][]string{{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}},
			"Example 1: Multiple anagram groups",
		},
		{
			[]string{""},
			[][]string{{""}},
			"Example 2: Empty string",
		},
		{
			[]string{"a"},
			[][]string{{"a"}},
			"Example 3: Single character",
		},
		{
			[]string{"abc", "bca", "cab", "xyz", "zyx", "yxz"},
			[][]string{{"abc", "bca", "cab"}, {"xyz", "zyx", "yxz"}},
			"Multiple groups with same size",
		},
		{
			[]string{"listen", "silent", "enlist", "inlets"},
			[][]string{{"listen", "silent", "enlist", "inlets"}},
			"Single large anagram group",
		},
		{
			[]string{"a", "b", "c", "d"},
			[][]string{{"a"}, {"b"}, {"c"}, {"d"}},
			"No anagrams - all unique",
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := groupAnagrams(tc.input)
			if !isValidAnagramGrouping(result, tc.expected) {
				t.Errorf("groupAnagrams(%v) = %v; expected valid grouping", tc.input, result)
			}
		})
	}
}

// Test cases for groupAnagramsOptimized function
func TestGroupAnagramsOptimized(t *testing.T) {
	testCases := []struct {
		input    []string
		expected [][]string
		name     string
	}{
		{
			[]string{"eat", "tea", "tan", "ate", "nat", "bat"},
			[][]string{{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}},
			"Example 1: Multiple anagram groups",
		},
		{
			[]string{""},
			[][]string{{""}},
			"Example 2: Empty string",
		},
		{
			[]string{"a"},
			[][]string{{"a"}},
			"Example 3: Single character",
		},
		{
			[]string{"abc", "bca", "cab", "xyz", "zyx", "yxz"},
			[][]string{{"abc", "bca", "cab"}, {"xyz", "zyx", "yxz"}},
			"Multiple groups with same size",
		},
		{
			[]string{"listen", "silent", "enlist", "inlets"},
			[][]string{{"listen", "silent", "enlist", "inlets"}},
			"Single large anagram group",
		},
		{
			[]string{"a", "b", "c", "d"},
			[][]string{{"a"}, {"b"}, {"c"}, {"d"}},
			"No anagrams - all unique",
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := groupAnagramsOptimized(tc.input)
			if !isValidAnagramGrouping(result, tc.expected) {
				t.Errorf("groupAnagramsOptimized(%v) = %v; expected valid grouping", tc.input, result)
			}
		})
	}
}

// Test edge cases
func TestEdgeCases(t *testing.T) {
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
		t.Run(fmt.Sprintf("EdgeCase_%d", i+1), func(t *testing.T) {
			result1 := groupAnagrams(testCase)
			result2 := groupAnagramsOptimized(testCase)

			if len(result1) != len(result2) {
				t.Errorf("Solutions disagree on number of groups for %v: groupAnagrams=%d, groupAnagramsOptimized=%d",
					testCase, len(result1), len(result2))
			}
		})
	}
}

// Benchmark groupAnagrams function
func BenchmarkGroupAnagrams(b *testing.B) {
	testString := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagrams(testString)
	}
}

// Benchmark groupAnagramsOptimized function
func BenchmarkGroupAnagramsOptimized(b *testing.B) {
	testString := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagramsOptimized(testString)
	}
}

// Benchmark with different string lengths
func BenchmarkGroupAnagramsSmall(b *testing.B) {
	testString := []string{"a", "b", "c", "d"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagrams(testString)
	}
}

func BenchmarkGroupAnagramsOptimizedSmall(b *testing.B) {
	testString := []string{"a", "b", "c", "d"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagramsOptimized(testString)
	}
}

// Benchmark with large anagram groups
func BenchmarkGroupAnagramsLarge(b *testing.B) {
	testString := []string{"listen", "silent", "enlist", "inlets", "tinsel", "elints"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagrams(testString)
	}
}

func BenchmarkGroupAnagramsOptimizedLarge(b *testing.B) {
	testString := []string{"listen", "silent", "enlist", "inlets", "tinsel", "elints"}
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		groupAnagramsOptimized(testString)
	}
}

// Helper function to validate anagram grouping
func isValidAnagramGrouping(result, expected [][]string) bool {
	if len(result) != len(expected) {
		return false
	}

	// Create a map to count occurrences in result
	resultMap := make(map[string]int)
	for _, group := range result {
		for _, str := range group {
			resultMap[str]++
		}
	}

	// Create a map to count occurrences in expected
	expectedMap := make(map[string]int)
	for _, group := range expected {
		for _, str := range group {
			expectedMap[str]++
		}
	}

	// Compare the maps
	return reflect.DeepEqual(resultMap, expectedMap)
}
