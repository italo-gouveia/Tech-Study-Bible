/**
 * Solution: strings.Builder with Two Pointers
 *
 * This approach uses strings.Builder for efficient string concatenation
 * and two pointers to traverse both strings simultaneously.
 *
 * Time Complexity: O(m + n) where m and n are lengths of word1 and word2
 * Space Complexity: O(m + n) for the result string
 */
package main

import (
	"strings"
)

// mergeAlternately merges two strings by alternating characters
func mergeAlternately(word1 string, word2 string) string {
	// Create a strings.Builder for efficient string building
	var b strings.Builder

	// Pre-allocate capacity for optimal performance
	// We know the final string will be exactly len(word1) + len(word2)
	b.Grow(len(word1) + len(word2))

	// Initialize two pointers for both strings
	i, j := 0, 0

	// Continue until we've processed all characters from both strings
	for i < len(word1) || j < len(word2) {
		// Add character from word1 if available
		if i < len(word1) {
			b.WriteByte(word1[i]) // Write single byte (character)
			i++                   // Increment pointer
		}

		// Add character from word2 if available
		if j < len(word2) {
			b.WriteByte(word2[j]) // Write single byte (character)
			j++                   // Increment pointer
		}
	}

	// Convert Builder to string and return
	return b.String()
}
