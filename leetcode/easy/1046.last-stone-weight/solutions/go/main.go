package main

import (
	"container/heap"
	"fmt"
	"sort"
)

// --- Solution 1: Sorting (Naive) ---

func lastStoneWeightSorting(stones []int) int {
	stoneList := make([]int, len(stones))
	copy(stoneList, stones)

	for len(stoneList) > 1 {
		sort.Ints(stoneList)
		n := len(stoneList)
		first := stoneList[n-1]
		second := stoneList[n-2]
		stoneList = stoneList[:n-2]

		diff := first - second
		if diff > 0 {
			stoneList = append(stoneList, diff)
		}
	}

	if len(stoneList) == 0 {
		return 0
	}
	return stoneList[0]
}

// --- Solution 2: Max-Heap (Optimal) - Direct Implementation ---

// MaxHeap implements heap.Interface for a max-heap
type MaxHeap []int

func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i] > h[j] } // Max-heap: larger is "less"
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

// lastStoneWeight - Main function for LeetCode submission
func lastStoneWeight(stones []int) int {
	h := &MaxHeap{}
	heap.Init(h)

	// Add all stones to heap
	for _, stone := range stones {
		heap.Push(h, stone)
	}

	// Simulate smashing until ≤ 1 stone remains
	for h.Len() > 1 {
		first := heap.Pop(h).(int)
		second := heap.Pop(h).(int)

		diff := first - second
		if diff > 0 {
			heap.Push(h, diff)
		}
	}

	if h.Len() == 0 {
		return 0
	}
	return heap.Pop(h).(int)
}

func lastStoneWeightHeap(stones []int) int {
	return lastStoneWeight(stones)
}

// --- Solution 3: Min-Heap with Negated Values (Alternative) ---

// MinHeap implements heap.Interface for a min-heap
type MinHeap []int

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

// Using min-heap with negated values to simulate max-heap
func lastStoneWeightMinHeap(stones []int) int {
	h := &MinHeap{}
	heap.Init(h)

	// Add all stones as negative values (min-heap of negatives = max-heap)
	for _, stone := range stones {
		heap.Push(h, -stone)
	}

	// Simulate smashing until ≤ 1 stone remains
	for h.Len() > 1 {
		first := -heap.Pop(h).(int) // Negate to get original value
		second := -heap.Pop(h).(int)

		diff := first - second
		if diff > 0 {
			heap.Push(h, -diff) // Store as negative
		}
	}

	if h.Len() == 0 {
		return 0
	}
	return -heap.Pop(h).(int) // Negate to get original value
}

func main() {
	testCases := [][]int{
		{2, 7, 4, 1, 8, 1},
		{1},
		{2, 3, 6, 2, 4},
		{1, 3},
	}

	for _, stones := range testCases {
		fmt.Printf("Stones: %v\n", stones)
		fmt.Printf("  Sorting: %d\n", lastStoneWeightSorting(stones))
		fmt.Printf("  Max-Heap: %d\n", lastStoneWeightHeap(stones))
		fmt.Printf("  Min-Heap (negated): %d\n", lastStoneWeightMinHeap(stones))
		fmt.Println()
	}
}
