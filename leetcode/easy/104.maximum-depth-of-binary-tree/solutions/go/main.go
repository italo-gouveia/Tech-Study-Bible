package main

import "fmt"

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// maxDepthRecursive calculates depth using recursion.
// Time: O(n), Space: O(h) where h = height
func maxDepthRecursive(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return 1 + max(maxDepthRecursive(root.Left), maxDepthRecursive(root.Right))
}

// maxDepthIterativeDFS uses a stack to track (node, depth) pairs.
// Time: O(n), Space: O(n)
func maxDepthIterativeDFS(root *TreeNode) int {
	if root == nil {
		return 0
	}

	type nodeDepth struct {
		node  *TreeNode
		depth int
	}

	stack := []nodeDepth{{root, 1}}
	maxDepth := 0

	for len(stack) > 0 {
		n := len(stack) - 1
		nd := stack[n]
		stack = stack[:n]

		if nd.node != nil {
			if nd.depth > maxDepth {
				maxDepth = nd.depth
			}
			stack = append(stack, nodeDepth{nd.node.Left, nd.depth + 1})
			stack = append(stack, nodeDepth{nd.node.Right, nd.depth + 1})
		}
	}

	return maxDepth
}

// maxDepthBFS uses level-order traversal to count levels.
// Time: O(n), Space: O(n)
func maxDepthBFS(root *TreeNode) int {
	if root == nil {
		return 0
	}

	queue := []*TreeNode{root}
	depth := 0

	for len(queue) > 0 {
		levelSize := len(queue)
		depth++

		for i := 0; i < levelSize; i++ {
			node := queue[0]
			queue = queue[1:]

			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
	}

	return depth
}

// Helper function for max
func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

// Helper: create sample trees
func sampleTree1() *TreeNode {
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
	return &TreeNode{
		Val: 1,
		Right: &TreeNode{
			Val: 2,
		},
	}
}

func sampleTree3() *TreeNode {
	return &TreeNode{
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
}

func main() {
	testCases := []struct {
		name string
		root *TreeNode
	}{
		{"Example 1: [3,9,20,null,null,15,7]", sampleTree1()},
		{"Example 2: [1,null,2]", sampleTree2()},
		{"Custom: [1,2,3,4,5]", sampleTree3()},
		{"Empty tree: []", nil},
	}

	for _, tc := range testCases {
		fmt.Printf("%s\n", tc.name)
		fmt.Printf("  Recursive DFS: %d\n", maxDepthRecursive(tc.root))
		fmt.Printf("  Iterative DFS: %d\n", maxDepthIterativeDFS(tc.root))
		fmt.Printf("  BFS          : %d\n", maxDepthBFS(tc.root))
		fmt.Println()
	}
}

