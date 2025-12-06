package main

import "fmt"

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// isSubtree checks if subRoot is a subtree of root using DFS + sameTree.
// Time: O(m * n) in worst case, Space: O(h_root + h_sub)
func isSubtree(root *TreeNode, subRoot *TreeNode) bool {
	if subRoot == nil {
		return true
	}
	if root == nil {
		return false
	}

	if sameTree(root, subRoot) {
		return true
	}

	return isSubtree(root.Left, subRoot) || isSubtree(root.Right, subRoot)
}

// sameTree checks if two trees are exactly the same (structure + values).
func sameTree(p *TreeNode, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	if p.Val != q.Val {
		return false
	}
	return sameTree(p.Left, q.Left) && sameTree(p.Right, q.Right)
}

// --- Sample Trees ---

func sample1() (*TreeNode, *TreeNode) {
	// root = [1,2,3,4,5], subRoot = [2,4,5] → true
	root := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 4,
			},
			Right: &TreeNode{
				Val: 5,
			},
		},
		Right: &TreeNode{
			Val: 3,
		},
	}

	subRoot := &TreeNode{
		Val: 2,
		Left: &TreeNode{
			Val: 4,
		},
		Right: &TreeNode{
			Val: 5,
		},
	}

	return root, subRoot
}

func sample2() (*TreeNode, *TreeNode) {
	// root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5] → false
	root := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 4,
				Left: &TreeNode{
					Val: 6,
				},
			},
			Right: &TreeNode{
				Val: 5,
			},
		},
		Right: &TreeNode{
			Val: 3,
		},
	}

	subRoot := &TreeNode{
		Val: 2,
		Left: &TreeNode{
			Val: 4,
		},
		Right: &TreeNode{
			Val: 5,
		},
	}

	return root, subRoot
}

func main() {
	tests := []struct {
		name    string
		root    *TreeNode
		subRoot *TreeNode
	}{
		{"Example 1: [1,2,3,4,5], [2,4,5]", sample1()},
		{"Example 2: [1,2,3,4,5,null,null,6], [2,4,5]", sample2()},
	}

	for _, tc := range tests {
		fmt.Println(tc.name)
		fmt.Printf("  isSubtree: %v\n", isSubtree(tc.root, tc.subRoot))
		fmt.Println()
	}
}
