/**
 * Solution 2: Two Pointers with Custom Character Check
 *
 * This solution uses two pointers with a custom alphanumeric check function
 * that avoids using unicode functions for potentially better performance.
 *
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - only using two pointers and a few variables
 */

package main

func isPalindromeOptimized(s string) bool {
	left, right := 0, len(s)-1

	for left < right {
		// Skip non-alphanumeric characters from left
		for left < right && !isAlphanumericOptimized(s[left]) {
			left++
		}

		// Skip non-alphanumeric characters from right
		for left < right && !isAlphanumericOptimized(s[right]) {
			right--
		}

		// Compare characters (case-insensitive)
		if toLowerCase(s[left]) != toLowerCase(s[right]) {
			return false
		}

		left++
		right--
	}

	return true
}

/**
 * Custom alphanumeric check - faster than unicode functions
 */
func isAlphanumericOptimized(c byte) bool {
	return (c >= 'a' && c <= 'z') ||
		(c >= 'A' && c <= 'Z') ||
		(c >= '0' && c <= '9')
}

/**
 * Custom lowercase conversion - faster than unicode.ToLower
 */
func toLowerCase(c byte) byte {
	if c >= 'A' && c <= 'Z' {
		return c + 32 // Convert to lowercase
	}
	return c
}
