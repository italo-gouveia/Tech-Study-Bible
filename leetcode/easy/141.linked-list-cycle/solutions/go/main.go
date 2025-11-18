package main

import "fmt"

// ListNode represents a node in a singly linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

// hasCycleHashSet detects cycle using hash set
// Time: O(n), Space: O(n)
// Traverse the list and store visited nodes in a map
// If we encounter a node that's already visited, there's a cycle
func hasCycleHashSet(head *ListNode) bool {
	seen := make(map[*ListNode]bool)
	curr := head

	for curr != nil {
		if seen[curr] {
			return true
		}
		seen[curr] = true
		curr = curr.Next
	}

	return false
}

// hasCycleFloyd detects cycle using Floyd's Cycle Detection (Tortoise and Hare)
// Time: O(n), Space: O(1)
// Use two pointers: slow moves 1 step, fast moves 2 steps
// If there's a cycle, fast will eventually catch up to slow
func hasCycleFloyd(head *ListNode) bool {
	if head == nil || head.Next == nil {
		return false
	}

	slow := head
	fast := head

	// Fast pointer moves 2 steps, slow moves 1 step
	// Check fast and fast.Next to avoid nil pointer dereference
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next

		// If they meet, there's a cycle
		if fast == slow {
			return true
		}
	}

	// Fast pointer reached the end, no cycle
	return false
}

// Helper function to create a linked list from a slice
// pos indicates the index where the tail connects (for cycle creation)
// pos = -1 means no cycle
func createList(vals []int, pos int) *ListNode {
	if len(vals) == 0 {
		return nil
	}

	nodes := make([]*ListNode, len(vals))
	for i, val := range vals {
		nodes[i] = &ListNode{Val: val}
		if i > 0 {
			nodes[i-1].Next = nodes[i]
		}
	}

	// Create cycle if pos is valid
	if pos >= 0 && pos < len(nodes) {
		nodes[len(nodes)-1].Next = nodes[pos]
	}

	return nodes[0]
}

// Helper function to print a linked list (limited to avoid infinite loop with cycles)
func printList(head *ListNode, maxNodes int) {
	curr := head
	count := 0
	visited := make(map[*ListNode]bool)

	for curr != nil && count < maxNodes {
		if visited[curr] {
			fmt.Print("(cycle detected)")
			break
		}
		visited[curr] = true
		fmt.Print(curr.Val)
		if curr.Next != nil && count < maxNodes-1 {
			fmt.Print(" → ")
		}
		curr = curr.Next
		count++
	}
	fmt.Println()
}

func main() {
	// Example 1: head = [3,2,0,-4], pos = 1 (has cycle)
	fmt.Println("Example 1:")
	list1 := createList([]int{3, 2, 0, -4}, 1)
	fmt.Print("List: ")
	printList(list1, 10)

	result1Hash := hasCycleHashSet(list1)
	result1Floyd := hasCycleFloyd(createList([]int{3, 2, 0, -4}, 1))
	fmt.Printf("Has cycle (HashSet): %v\n", result1Hash)
	fmt.Printf("Has cycle (Floyd): %v\n", result1Floyd)

	fmt.Println()

	// Example 2: head = [1,2], pos = 0 (has cycle)
	fmt.Println("Example 2:")
	list2 := createList([]int{1, 2}, 0)
	fmt.Print("List: ")
	printList(list2, 10)

	result2Hash := hasCycleHashSet(list2)
	result2Floyd := hasCycleFloyd(createList([]int{1, 2}, 0))
	fmt.Printf("Has cycle (HashSet): %v\n", result2Hash)
	fmt.Printf("Has cycle (Floyd): %v\n", result2Floyd)

	fmt.Println()

	// Example 3: head = [1], pos = -1 (no cycle)
	fmt.Println("Example 3:")
	list3 := createList([]int{1}, -1)
	fmt.Print("List: ")
	printList(list3, 10)

	result3Hash := hasCycleHashSet(list3)
	result3Floyd := hasCycleFloyd(createList([]int{1}, -1))
	fmt.Printf("Has cycle (HashSet): %v\n", result3Hash)
	fmt.Printf("Has cycle (Floyd): %v\n", result3Floyd)

	fmt.Println()

	// Additional test: head = [], pos = -1 (empty list)
	fmt.Println("Additional test - Empty list:")
	list4 := createList([]int{}, -1)
	fmt.Print("List: ")
	printList(list4, 10)

	result4Hash := hasCycleHashSet(list4)
	result4Floyd := hasCycleFloyd(createList([]int{}, -1))
	fmt.Printf("Has cycle (HashSet): %v\n", result4Hash)
	fmt.Printf("Has cycle (Floyd): %v\n", result4Floyd)
}
