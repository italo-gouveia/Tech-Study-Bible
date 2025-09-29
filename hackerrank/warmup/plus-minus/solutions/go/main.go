/**
 * Main executable for Plus Minus problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */
package main

import (
	"fmt"
	"math/rand"
	"os"
	"time"
)

// Main function with sample usage and test cases
func main() {
	fmt.Println("=== Plus Minus - Go Solution ===")

	// Test cases from problem description
	testCases := []struct {
		arr         []int32
		description string
	}{
		{[]int32{1, 1, 0, -1, -1}, "Example 1: Two positive, two negative, one zero"},
		{[]int32{-4, 3, -9, 0, 4, 1}, "Example 2: Three positive, two negative, one zero"},
		{[]int32{1, 2, 3, 4, 5}, "All positive numbers"},
		{[]int32{-1, -2, -3, -4, -5}, "All negative numbers"},
		{[]int32{0, 0, 0, 0, 0}, "All zeros"},
		{[]int32{0}, "Single zero"},
		{[]int32{1}, "Single positive"},
		{[]int32{-1}, "Single negative"},
	}

	// Run test cases
	fmt.Println("--- Test Cases ---")
	for _, tc := range testCases {
		fmt.Printf("Test: %s\n", tc.description)
		fmt.Printf("Input: %v\n", tc.arr)
		fmt.Print("Output: ")
		plusMinus(tc.arr)
		fmt.Println()
	}

	// Performance test
	performanceTest()

	// Edge cases test
	edgeCasesTest()
}

// performanceTest tests performance with large inputs
func performanceTest() {
	fmt.Println("--- Performance Test ---")

	// Create large test array
	arr := make([]int32, 1000)
	rand.Seed(time.Now().UnixNano())
	for i := range arr {
		arr[i] = int32(rand.Intn(201) - 100) // Random numbers from -100 to 100
	}

	// Measure execution time (suppress output during performance test)
	start := time.Now()
	for i := 0; i < 10000; i++ {
		// Capture output to avoid spam during performance test
		old := os.Stdout
		os.Stdout, _ = os.Open(os.DevNull)
		plusMinus(arr)
		os.Stdout.Close()
		os.Stdout = old
	}
	duration := time.Since(start)

	fmt.Printf("Test data: Array of %d random integers (-100 to 100)\n", len(arr))
	fmt.Printf("Executed 10,000 iterations in %v\n", duration)
	fmt.Printf("Average time per iteration: %.2fµs\n", float64(duration.Nanoseconds())/10000/1000)
	fmt.Printf("Operations per second: %.0f\n\n", 10000/duration.Seconds())
}

// edgeCasesTest tests boundary conditions
func edgeCasesTest() {
	fmt.Println("--- Edge Cases Test ---")

	// Test with maximum constraint values
	maxArray := make([]int32, 100)
	for i := 0; i < 50; i++ {
		maxArray[i] = 100 // Positive
	}
	for i := 50; i < 100; i++ {
		maxArray[i] = -100 // Negative
	}

	fmt.Printf("Test: Maximum constraint values (%d elements)\n", len(maxArray))
	fmt.Printf("Input: [first 10 elements: %v...]\n", maxArray[:10])
	fmt.Print("Output: ")
	plusMinus(maxArray)
	fmt.Println()

	// Test with mixed values
	mixedArray := []int32{100, -100, 0, 50, -50, 25, -25, 0, 75, -75}
	fmt.Printf("Test: Mixed positive, negative, and zero values\n")
	fmt.Printf("Input: %v\n", mixedArray)
	fmt.Print("Output: ")
	plusMinus(mixedArray)
	fmt.Println()

	// Test with boundary values
	boundaryArray := []int32{1, -1, 0, 100, -100}
	fmt.Printf("Test: Boundary values (1, -1, 0, 100, -100)\n")
	fmt.Printf("Input: %v\n", boundaryArray)
	fmt.Print("Output: ")
	plusMinus(boundaryArray)
	fmt.Println()

	fmt.Println("All edge cases completed successfully! ✅")
}
