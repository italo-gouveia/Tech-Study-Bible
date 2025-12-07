package main

import "fmt"

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// --- Brute Force (for reference, not used in main) ---

// height computes the height of a tree (in nodes).
func height(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left := height(root.Left)
	right := height(root.Right)
	if left > right {
		return 1 + left
	}
	return 1 + right
}

// diameterBruteForce computes diameter in O(n^2) by recomputing heights.
func diameterBruteForce(root *TreeNode) int {
	if root == nil {
		return 0
	}
	leftHeight := height(root.Left)
	rightHeight := height(root.Right)
	throughRoot := leftHeight + rightHeight

	leftDia := diameterBruteForce(root.Left)
	rightDia := diameterBruteForce(root.Right)

	// Take the maximum among:
	// - path through this root
	// - best diameter in left subtree
	// - best diameter in right subtree
	max := throughRoot
	if leftDia > max {
		max = leftDia
	}
	if rightDia > max {
		max = rightDia
	}
	return max
}

// --- Optimal DFS with global diameter ---

// diameterDFS computes diameter in O(n) using a single DFS.
func diameterDFS(root *TreeNode) int {
	maxDia := 0

	var dfs func(node *TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return 0
		}

		left := dfs(node.Left)
		right := dfs(node.Right)

		// Update global diameter: path through this node
		if left+right > maxDia {
			maxDia = left + right
		}

		// Return height of this subtree
		if left > right {
			return 1 + left
		}
		return 1 + right
	}

	dfs(root)
	return maxDia
}

// --- Helpers to build sample trees and print results ---

func sampleTree1() *TreeNode {
	// Corresponds to [1,null,2,3,4,5] shape example
	return &TreeNode{
		Val: 1,
		Right: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 3,
				Left: &TreeNode{
					Val: 4,
					Left: &TreeNode{
						Val: 5,
					},
				},
			},
		},
	}
}

func sampleTree2() *TreeNode {
	// [1,2,3]
	return &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}
}

func sampleTree3() *TreeNode {
	// Skewed tree [1,2,null,3]
	return &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 3,
			},
		},
	}
}

func main() {
	tests := []struct {
		name string
		root *TreeNode
	}{
		{"Example-like 1", sampleTree1()},
		{"Example 2: [1,2,3]", sampleTree2()},
		{"Skewed: [1,2,null,3]", sampleTree3()},
	}

	for _, tc := range tests {
		fmt.Println(tc.name)
		fmt.Printf("  Diameter (Brute Force): %d\n", diameterBruteForce(tc.root))
		fmt.Printf("  Diameter (DFS Optimal): %d\n", diameterDFS(tc.root))
		fmt.Println()
	}
}



