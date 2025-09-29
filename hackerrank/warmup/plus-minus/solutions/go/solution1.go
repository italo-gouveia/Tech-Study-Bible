/**
 * Solution: Basic Counting Approach
 *
 * This solution uses simple counters to track positive, negative, and zero elements
 * in a single pass through the array.
 *
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) - only using a few variables for counting
 */
package main

import "fmt"

// plusMinus calculates and prints the ratios of positive, negative, and zero elements
func plusMinus(arr []int32) {
	n := len(arr)
	positive, negative, zero := 0, 0, 0

	// Count elements in each category
	for _, num := range arr {
		if num > 0 {
			positive++
		} else if num < 0 {
			negative++
		} else {
			zero++
		}
	}

	// Calculate and print ratios with 6 decimal places
	fmt.Printf("%.6f\n", float64(positive)/float64(n))
	fmt.Printf("%.6f\n", float64(negative)/float64(n))
	fmt.Printf("%.6f\n", float64(zero)/float64(n))
}
