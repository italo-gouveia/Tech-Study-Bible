package main

import "fmt"

func main() {
	// Test Case 1
	fmt.Println("Test 1:")
	nums1 := []int{2, 7, 11, 15}
	target1 := 9
	result1 := twoSum(nums1, target1)
	fmt.Printf("Input: nums = %v, target = %d\n", nums1, target1)
	fmt.Printf("Output: %v\n", result1)
	fmt.Println("Expected: [0, 1]")
	fmt.Println()

	// Test Case 2
	fmt.Println("Test 2:")
	nums2 := []int{3, 2, 4}
	target2 := 6
	result2 := twoSum(nums2, target2)
	fmt.Printf("Input: nums = %v, target = %d\n", nums2, target2)
	fmt.Printf("Output: %v\n", result2)
	fmt.Println("Expected: [1, 2]")
	fmt.Println()

	// Test Case 3
	fmt.Println("Test 3:")
	nums3 := []int{3, 3}
	target3 := 6
	result3 := twoSum(nums3, target3)
	fmt.Printf("Input: nums = %v, target = %d\n", nums3, target3)
	fmt.Printf("Output: %v\n", result3)
	fmt.Println("Expected: [0, 1]")
	fmt.Println()

	// Test Case 4
	fmt.Println("Test 4:")
	nums4 := []int{4, 5, 6}
	target4 := 10
	result4 := twoSum(nums4, target4)
	fmt.Printf("Input: nums = %v, target = %d\n", nums4, target4)
	fmt.Printf("Output: %v\n", result4)
	fmt.Println("Expected: [0, 2]")
	fmt.Println()
}
