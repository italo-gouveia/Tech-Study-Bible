package main

import (
	"container/heap"
	"fmt"
	"sort"
)

// --- Solution 1: Sorting (Naive) ---

type KthLargestSorting struct {
	arr []int
	k   int
}

func NewKthLargestSorting(k int, nums []int) *KthLargestSorting {
	arr := make([]int, len(nums))
	copy(arr, nums)
	return &KthLargestSorting{
		arr: arr,
		k:   k,
	}
}

func (kl *KthLargestSorting) Add(val int) int {
	kl.arr = append(kl.arr, val)
	sort.Ints(kl.arr)
	return kl.arr[len(kl.arr)-kl.k]
}

// --- Solution 2: Min-Heap (Optimal) ---

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

type KthLargest struct {
	minHeap MinHeap
	k       int
}

// Constructor matches LeetCode signature.
func Constructor(k int, nums []int) KthLargest {
	h := MinHeap{}
	heap.Init(&h)

	kl := KthLargest{
		minHeap: h,
		k:       k,
	}

	for _, num := range nums {
		kl.Add(num)
	}

	return kl
}

func (kl *KthLargest) Add(val int) int {
	heap.Push(&kl.minHeap, val)

	// Keep only k largest elements
	if kl.minHeap.Len() > kl.k {
		heap.Pop(&kl.minHeap)
	}

	// Root is the kth largest (smallest of the k largest)
	return kl.minHeap[0]
}

func main() {
	fmt.Println("=== Solution 1: Sorting ===")
	kl1 := NewKthLargestSorting(3, []int{4, 5, 8, 2})
	fmt.Printf("add(3): %d\n", kl1.Add(3))   // 4
	fmt.Printf("add(5): %d\n", kl1.Add(5))   // 5
	fmt.Printf("add(10): %d\n", kl1.Add(10)) // 5
	fmt.Printf("add(9): %d\n", kl1.Add(9))   // 8
	fmt.Printf("add(4): %d\n", kl1.Add(4))   // 8

	fmt.Println("\n=== Solution 2: Min-Heap (Optimal) ===")
	kl2 := Constructor(3, []int{4, 5, 8, 2})
	fmt.Printf("add(3): %d\n", kl2.Add(3))   // 4
	fmt.Printf("add(5): %d\n", kl2.Add(5))   // 5
	fmt.Printf("add(10): %d\n", kl2.Add(10)) // 5
	fmt.Printf("add(9): %d\n", kl2.Add(9))   // 8
	fmt.Printf("add(4): %d\n", kl2.Add(4))   // 8

	fmt.Println("\n=== Example 2: With Duplicates ===")
	kl3 := Constructor(3, []int{1, 2, 3, 3})
	fmt.Printf("add(3): %d\n", kl3.Add(3)) // 3
	fmt.Printf("add(5): %d\n", kl3.Add(5)) // 3
	fmt.Printf("add(6): %d\n", kl3.Add(6)) // 3
	fmt.Printf("add(7): %d\n", kl3.Add(7)) // 5
	fmt.Printf("add(8): %d\n", kl3.Add(8)) // 6
}
