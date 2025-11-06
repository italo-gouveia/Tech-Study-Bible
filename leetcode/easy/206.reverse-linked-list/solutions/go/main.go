package main

import "fmt"

// ListNode represents a node in a singly linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

// reverseListIterative reverses a linked list iteratively
// Time: O(n), Space: O(1)
func reverseListIterative(head *ListNode) *ListNode {
	var prev *ListNode
	curr := head

	for curr != nil {
		// Save the next node before changing the link
		next := curr.Next
		// Reverse the link: point current to previous
		curr.Next = prev
		// Move prev and curr forward
		prev = curr
		curr = next
	}

	// prev is now the new head
	return prev
}

// reverseListRecursive reverses a linked list recursively
// Time: O(n), Space: O(n) for recursion stack
func reverseListRecursive(head *ListNode) *ListNode {
	// Base case: empty list or single node
	if head == nil || head.Next == nil {
		return head
	}

	// Recursively reverse the rest of the list
	newHead := reverseListRecursive(head.Next)

	// Reverse the link: head.Next now points to head
	// After recursion, head.Next is the last node of the reversed rest
	head.Next.Next = head
	// Set head's next to nil (it will be the tail)
	head.Next = nil

	return newHead
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
	// Example 1: [1,2,3,4,5]
	fmt.Println("Example 1:")
	list1 := createList([]int{1, 2, 3, 4, 5})
	fmt.Print("Original: ")
	printList(list1)

	reversed1 := reverseListIterative(createList([]int{1, 2, 3, 4, 5}))
	fmt.Print("Reversed (iterative): ")
	printList(reversed1)

	reversed1Rec := reverseListRecursive(createList([]int{1, 2, 3, 4, 5}))
	fmt.Print("Reversed (recursive): ")
	printList(reversed1Rec)

	fmt.Println()

	// Example 2: [1,2]
	fmt.Println("Example 2:")
	list2 := createList([]int{1, 2})
	fmt.Print("Original: ")
	printList(list2)

	reversed2 := reverseListIterative(createList([]int{1, 2}))
	fmt.Print("Reversed (iterative): ")
	printList(reversed2)

	reversed2Rec := reverseListRecursive(createList([]int{1, 2}))
	fmt.Print("Reversed (recursive): ")
	printList(reversed2Rec)

	fmt.Println()

	// Example 3: []
	fmt.Println("Example 3:")
	list3 := createList([]int{})
	fmt.Print("Original: ")
	printList(list3)

	reversed3 := reverseListIterative(createList([]int{}))
	fmt.Print("Reversed (iterative): ")
	printList(reversed3)

	reversed3Rec := reverseListRecursive(createList([]int{}))
	fmt.Print("Reversed (recursive): ")
	printList(reversed3Rec)
}
