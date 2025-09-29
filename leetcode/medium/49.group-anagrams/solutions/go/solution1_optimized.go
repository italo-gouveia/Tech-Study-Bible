/**
 * Solution 1 Optimized: Hash Table with Byte Sorting
 *
 * Optimized version using byte operations instead of rune operations
 * for better performance with ASCII-only lowercase letters.
 *
 * Time Complexity: O(n * m * log(m)) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

package main

import (
	"slices"
)

func groupAnagramsSortingOptimized(strs []string) [][]string {
	// Pre-allocate map with estimated capacity
	anagramMap := make(map[string][]string, len(strs)/2)

	for _, str := range strs {
		// Use byte slice instead of rune slice for ASCII-only strings
		chars := []byte(str)
		slices.Sort(chars)
		key := string(chars)

		// Add the original string to the list for this key
		anagramMap[key] = append(anagramMap[key], str)
	}

	// Pre-allocate result slice with exact capacity
	result := make([][]string, 0, len(anagramMap))
	for _, group := range anagramMap {
		result = append(result, group)
	}

	return result
}
