/**
 * Solution 2: Hash Table with Character Frequency Counting
 *
 * This solution uses character frequency counting instead of sorting to create unique keys.
 * For each string, we count the frequency of each character and use that as the key.
 *
 * Time Complexity: O(n * m) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

package main

import (
	"fmt"
	"strings"
)

func groupAnagramsOptimized(strs []string) [][]string {
	// Map to store groups of anagrams
	anagramMap := make(map[string][]string)

	for _, str := range strs {
		// Create a character frequency key
		key := getCharacterFrequencyKey(str)

		// Add the original string to the list for this key
		anagramMap[key] = append(anagramMap[key], str)
	}

	// Convert map values to slice of slices
	result := make([][]string, 0, len(anagramMap))
	for _, group := range anagramMap {
		result = append(result, group)
	}

	return result
}

/**
 * Create a unique key based on character frequencies
 * Format: "a1b2c3..." where numbers represent frequencies
 */
func getCharacterFrequencyKey(str string) string {
	// Count frequency of each character (assuming only lowercase letters)
	count := make([]int, 26)

	for _, char := range str {
		count[char-'a']++
	}

	// Build the key string
	var key strings.Builder
	for i := 0; i < 26; i++ {
		if count[i] > 0 {
			key.WriteString(fmt.Sprintf("%c%d", 'a'+i, count[i]))
		}
	}

	return key.String()
}
