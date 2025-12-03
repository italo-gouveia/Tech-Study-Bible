package main

import (
	"fmt"
)

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// --- Solution 1: Recursive DFS (Top-Down) ---

// isSameTreeRecursive compares two trees using recursion.
// Time: O(n), Space: O(h)
func isSameTreeRecursive(p *TreeNode, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	if p.Val != q.Val {
		return false
	}
	return isSameTreeRecursive(p.Left, q.Left) && isSameTreeRecursive(p.Right, q.Right)
}

// --- Solution 2: Iterative DFS with Stack ---

// isSameTreeIterativeDFS compares two trees using an explicit stack.
// Time: O(n), Space: O(n)
func isSameTreeIterativeDFS(p *TreeNode, q *TreeNode) bool {
	type pair struct {
		p *TreeNode
		q *TreeNode
	}

	stack := []pair{{p, q}}

	for len(stack) > 0 {
		// Pop from stack
		n := len(stack) - 1
		curr := stack[n]
		stack = stack[:n]

		node1, node2 := curr.p, curr.q

		if node1 == nil && node2 == nil {
			continue
		}
		if node1 == nil || node2 == nil {
			return false
		}
		if node1.Val != node2.Val {
			return false
		}

		// Push children in the same order: right, then left (so left is processed first)
		stack = append(stack, pair{node1.Right, node2.Right})
		stack = append(stack, pair{node1.Left, node2.Left})
	}

	return true
}

// --- Solution 3: BFS (Level-Order Comparison) ---

// isSameTreeBFS compares two trees using BFS with two queues.
// Time: O(n), Space: O(n)
func isSameTreeBFS(p *TreeNode, q *TreeNode) bool {
	queueP := []*TreeNode{p}
	queueQ := []*TreeNode{q}

	for len(queueP) > 0 && len(queueQ) > 0 {
		nodeP := queueP[0]
		queueP = queueP[1:]

		nodeQ := queueQ[0]
		queueQ = queueQ[1:]

		if nodeP == nil && nodeQ == nil {
			continue
		}
		if nodeP == nil || nodeQ == nil {
			return false
		}
		if nodeP.Val != nodeQ.Val {
			return false
		}

		queueP = append(queueP, nodeP.Left, nodeP.Right)
		queueQ = append(queueQ, nodeQ.Left, nodeQ.Right)
	}

	return len(queueP) == 0 && len(queueQ) == 0
}

// --- Sample Trees ---

func sampleEqualTrees() (*TreeNode, *TreeNode) {
	// [1,2,3] and [1,2,3]
	p := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}

	q := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}

	return p, q
}

func sampleDifferentStructure() (*TreeNode, *TreeNode) {
	// [4,7] vs [4,null,7]
	p := &TreeNode{
		Val: 4,
		Left: &TreeNode{
			Val: 7,
		},
	}

	q := &TreeNode{
		Val: 4,
		Right: &TreeNode{
			Val: 7,
		},
	}

	return p, q
}

func sampleDifferentValues() (*TreeNode, *TreeNode) {
	// [1,2,3] vs [1,3,2]
	p := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}

	q := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 3,
		},
		Right: &TreeNode{
			Val: 2,
		},
	}

	return p, q
}

func main() {
	tests := []struct {
		name string
		p, q *TreeNode
	}{
		{"Equal trees: [1,2,3] vs [1,2,3]", sampleEqualTrees()},
		{"Different structure: [4,7] vs [4,null,7]", sampleDifferentStructure()},
		{"Different values: [1,2,3] vs [1,3,2]", sampleDifferentValues()},
		{"Both empty: [] vs []", nil, nil},
	}

	for _, tc := range tests {
		fmt.Println(tc.name)
		fmt.Printf("  Recursive DFS: %v\n", isSameTreeRecursive(tc.p, tc.q))
		fmt.Printf("  Iterative DFS: %v\n", isSameTreeIterativeDFS(tc.p, tc.q))
		fmt.Printf("  BFS          : %v\n", isSameTreeBFS(tc.p, tc.q))
		fmt.Println()
	}
}
