package main

import "fmt"

// ListNode represents a node in a singly linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

// mergeTwoListsIterative merges two sorted linked lists iteratively
// Time: O(n + m), Space: O(1)
// Uses a dummy node to simplify edge cases
func mergeTwoListsIterative(list1 *ListNode, list2 *ListNode) *ListNode {
	// Dummy node to simplify edge cases
	dummy := &ListNode{}
	current := dummy

	// Compare nodes from both lists and link the smaller one
	for list1 != nil && list2 != nil {
		if list1.Val < list2.Val {
			current.Next = list1
			list1 = list1.Next
		} else {
			current.Next = list2
			list2 = list2.Next
		}
		current = current.Next
	}

	// Link remaining nodes from the non-empty list
	if list1 != nil {
		current.Next = list1
	} else {
		current.Next = list2
	}

	// Return the head of the merged list (skip dummy node)
	return dummy.Next
}

// mergeTwoListsRecursive merges two sorted linked lists recursively
// Time: O(n + m), Space: O(n + m) for recursion stack
func mergeTwoListsRecursive(list1 *ListNode, list2 *ListNode) *ListNode {
	// Base case: if one list is empty, return the other
	if list1 == nil {
		return list2
	}
	if list2 == nil {
		return list1
	}

	// Choose the smaller node and recursively merge the rest
	if list1.Val <= list2.Val {
		list1.Next = mergeTwoListsRecursive(list1.Next, list2)
		return list1
	}
	list2.Next = mergeTwoListsRecursive(list1, list2.Next)
	return list2
}

// Helper function to create a linked list from a slice
func createList(vals []int) *ListNode {
	if len(vals) == 0 {
		return nil
	}

	head := &ListNode{Val: vals[0]}
	curr := head
	for i := 1; i < len(vals); i++ {
		curr.Next = &ListNode{Val: vals[i]}
		curr = curr.Next
	}
	return head
}

// Helper function to print a linked list
func printList(head *ListNode) {
	curr := head
	for curr != nil {
		fmt.Print(curr.Val)
		if curr.Next != nil {
			fmt.Print(" → ")
		}
		curr = curr.Next
	}
	fmt.Println()
}

func main() {
	// Example 1: list1 = [1,2,4], list2 = [1,3,4]
	fmt.Println("Example 1:")
	list1 := createList([]int{1, 2, 4})
	list2 := createList([]int{1, 3, 4})
	fmt.Print("List1: ")
	printList(list1)
	fmt.Print("List2: ")
	printList(list2)

	merged1 := mergeTwoListsIterative(createList([]int{1, 2, 4}), createList([]int{1, 3, 4}))
	fmt.Print("Merged (iterative): ")
	printList(merged1)

	merged1Rec := mergeTwoListsRecursive(createList([]int{1, 2, 4}), createList([]int{1, 3, 4}))
	fmt.Print("Merged (recursive): ")
	printList(merged1Rec)

	fmt.Println()

	// Example 2: list1 = [], list2 = []
	fmt.Println("Example 2:")
	list3 := createList([]int{})
	list4 := createList([]int{})
	fmt.Print("List1: ")
	printList(list3)
	fmt.Print("List2: ")
	printList(list4)

	merged2 := mergeTwoListsIterative(createList([]int{}), createList([]int{}))
	fmt.Print("Merged (iterative): ")
	printList(merged2)

	merged2Rec := mergeTwoListsRecursive(createList([]int{}), createList([]int{}))
	fmt.Print("Merged (recursive): ")
	printList(merged2Rec)

	fmt.Println()

	// Example 3: list1 = [], list2 = [0]
	fmt.Println("Example 3:")
	list5 := createList([]int{})
	list6 := createList([]int{0})
	fmt.Print("List1: ")
	printList(list5)
	fmt.Print("List2: ")
	printList(list6)

	merged3 := mergeTwoListsIterative(createList([]int{}), createList([]int{0}))
	fmt.Print("Merged (iterative): ")
	printList(merged3)

	merged3Rec := mergeTwoListsRecursive(createList([]int{}), createList([]int{0}))
	fmt.Print("Merged (recursive): ")
	printList(merged3Rec)
}
