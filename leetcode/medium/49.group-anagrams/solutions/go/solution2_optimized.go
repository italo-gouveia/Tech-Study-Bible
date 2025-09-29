/**
 * Solution 2 Optimized: Hash Table with Character Frequency Counting
 *
 * Highly optimized version using byte operations and efficient key generation
 * for maximum performance with ASCII-only lowercase letters.
 *
 * Time Complexity: O(n * m) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

package main

import (
	"strconv"
)

func groupAnagramsFrequencyOptimized(strs []string) [][]string {
	// Pre-allocate map with estimated capacity
	anagramMap := make(map[string][]string, len(strs)/2)

	for _, str := range strs {
		// Create a character frequency key using optimized function
		key := getCharacterFrequencyKeyOptimized(str)

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

/**
 * Optimized character frequency key generation
 * Uses byte operations and efficient string building
 */
func getCharacterFrequencyKeyOptimized(str string) string {
	// Count frequency of each character using byte operations
	var count [26]int // Use array instead of slice for better performance

	for i := 0; i < len(str); i++ {
		count[str[i]-'a']++
	}

	// Build key using efficient byte operations
	var key []byte
	for i := 0; i < 26; i++ {
		if count[i] > 0 {
			key = append(key, byte('a'+i))
			key = append(key, strconv.Itoa(count[i])...)
		}
	}

	return string(key)
}
