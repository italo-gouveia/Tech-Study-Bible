/**
 * Solution 1: Two Pointers Approach
 *
 * This solution uses two pointers to compare characters from both ends of the string.
 * It skips non-alphanumeric characters and performs case-insensitive comparison.
 *
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - only using two pointers and a few variables
 */

package main

import "unicode"

func isPalindrome(s string) bool {
	left, right := 0, len(s)-1

	for left < right {
		// Skip non-alphanumeric characters from left
		for left < right && !isAlphanumeric(rune(s[left])) {
			left++
		}

		// Skip non-alphanumeric characters from right
		for left < right && !isAlphanumeric(rune(s[right])) {
			right--
		}

		// Compare characters (case-insensitive)
		if unicode.ToLower(rune(s[left])) != unicode.ToLower(rune(s[right])) {
			return false
		}

		left++
		right--
	}

	return true
}

/**
 * Check if a character is alphanumeric (letter or digit)
 */
func isAlphanumeric(c rune) bool {
	return unicode.IsLetter(c) || unicode.IsDigit(c)
}
