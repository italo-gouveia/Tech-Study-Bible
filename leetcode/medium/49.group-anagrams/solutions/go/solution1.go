/**
 * Solution 1: Hash Table with String Sorting
 *
 * This solution uses a hash table to group anagrams by their sorted character representation.
 * Strings that are anagrams will have the same sorted character sequence.
 *
 * Time Complexity: O(n * m * log(m)) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

package main

import (
	"slices"
)

func groupAnagrams(strs []string) [][]string {
	// Map to store groups of anagrams
	anagramMap := make(map[string][]string)

	for _, str := range strs {
		// Sort the characters in the string to create a unique key
		chars := []rune(str)
		slices.Sort(chars)
		key := string(chars)

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
