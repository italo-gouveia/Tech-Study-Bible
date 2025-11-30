package main

import "fmt"

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// --- Solution 1: Brute Force (Top-Down) ---

// height calculates the height of a subtree.
// Time: O(n) for each call
func height(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return 1 + max(height(root.Left), height(root.Right))
}

// isBalancedBruteForce checks balance by recomputing heights at each node.
// Time: O(n²), Space: O(n)
func isBalancedBruteForce(root *TreeNode) bool {
	if root == nil {
		return true
	}

	leftHeight := height(root.Left)
	rightHeight := height(root.Right)

	if abs(leftHeight-rightHeight) > 1 {
		return false
	}

	return isBalancedBruteForce(root.Left) && isBalancedBruteForce(root.Right)
}

// --- Solution 2: DFS Bottom-Up (Optimal) ---

// dfsResult holds balance status and height.
type dfsResult struct {
	balanced int // 1 = balanced, 0 = unbalanced
	height   int
}

// isBalancedDFS uses bottom-up DFS to check balance in one pass.
// Time: O(n), Space: O(h)
func isBalancedDFS(root *TreeNode) bool {
	result := dfs(root)
	return result.balanced == 1
}

// dfs returns [balanced, height] for the subtree rooted at node.
func dfs(node *TreeNode) dfsResult {
	if node == nil {
		return dfsResult{balanced: 1, height: 0}
	}

	left := dfs(node.Left)
	right := dfs(node.Right)

	// Check if current node is balanced
	balanced := 0
	if left.balanced == 1 && right.balanced == 1 && abs(left.height-right.height) <= 1 {
		balanced = 1
	}

	height := 1 + max(left.height, right.height)

	return dfsResult{balanced: balanced, height: height}
}

// Helper functions
func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

// --- Sample Trees ---

func sampleTree1() *TreeNode {
	// [3,9,20,null,null,15,7] - balanced
	return &TreeNode{
		Val: 3,
		Left: &TreeNode{
			Val: 9,
		},
		Right: &TreeNode{
			Val: 20,
			Left: &TreeNode{
				Val: 15,
			},
			Right: &TreeNode{
				Val: 7,
			},
		},
	}
}

func sampleTree2() *TreeNode {
	// [1,2,2,3,3,null,null,4,4] - unbalanced
	return &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 3,
				Left: &TreeNode{
					Val: 4,
				},
				Right: &TreeNode{
					Val: 4,
				},
			},
			Right: &TreeNode{
				Val: 3,
			},
		},
		Right: &TreeNode{
			Val: 2,
		},
	}
}

func sampleTree3() *TreeNode {
	// [1,2,3,null,null,4] - balanced
	return &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
			Left: &TreeNode{
				Val: 4,
			},
		},
	}
}

func main() {
	testCases := []struct {
		name string
		root *TreeNode
	}{
		{"Example 1: [3,9,20,null,null,15,7]", sampleTree1()},
		{"Example 2: [1,2,2,3,3,null,null,4,4]", sampleTree2()},
		{"Example 3: [1,2,3,null,null,4]", sampleTree3()},
		{"Empty tree: []", nil},
	}

	for _, tc := range testCases {
		fmt.Printf("%s\n", tc.name)
		fmt.Printf("  Brute Force: %v\n", isBalancedBruteForce(tc.root))
		fmt.Printf("  DFS Optimal: %v\n", isBalancedDFS(tc.root))
		fmt.Println()
	}
}
