package main

import "fmt"

// TreeNode defines a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// invertTreeRecursive performs a DFS recursion swapping children.
// Time: O(n), Space: O(n) recursion depth worst-case.
func invertTreeRecursive(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	root.Left, root.Right = invertTreeRecursive(root.Right), invertTreeRecursive(root.Left)
	return root
}

// invertTreeIterativeDFS uses an explicit stack.
func invertTreeIterativeDFS(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}

	stack := []*TreeNode{root}
	for len(stack) > 0 {
		n := len(stack) - 1
		node := stack[n]
		stack = stack[:n]

		node.Left, node.Right = node.Right, node.Left

		if node.Left != nil {
			stack = append(stack, node.Left)
		}
		if node.Right != nil {
			stack = append(stack, node.Right)
		}
	}

	return root
}

// invertTreeBFS uses a queue (level-order traversal).
func invertTreeBFS(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}

	queue := []*TreeNode{root}
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]

		node.Left, node.Right = node.Right, node.Left

		if node.Left != nil {
			queue = append(queue, node.Left)
		}
		if node.Right != nil {
			queue = append(queue, node.Right)
		}
	}

	return root
}

// cloneTree creates a deep copy so we can reuse the original sample tree.
func cloneTree(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	newNode := &TreeNode{Val: root.Val}
	newNode.Left = cloneTree(root.Left)
	newNode.Right = cloneTree(root.Right)
	return newNode
}

// levelOrderValues returns the BFS order as a slice for easy printing.
func levelOrderValues(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	result := []int{}
	queue := []*TreeNode{root}

	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		result = append(result, node.Val)

		if node.Left != nil {
			queue = append(queue, node.Left)
		}
		if node.Right != nil {
			queue = append(queue, node.Right)
		}
	}

	return result
}

func sampleTree1() *TreeNode {
	return &TreeNode{
		Val: 4,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 1,
			},
			Right: &TreeNode{
				Val: 3,
			},
		},
		Right: &TreeNode{
			Val: 7,
			Left: &TreeNode{
				Val: 6,
			},
			Right: &TreeNode{
				Val: 9,
			},
		},
	}
}

func sampleTree2() *TreeNode {
	return &TreeNode{
		Val: 2,
		Left: &TreeNode{
			Val: 1,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}
}

func main() {
	cases := []*TreeNode{
		sampleTree1(),
		sampleTree2(),
		nil,
	}

	for idx, root := range cases {
		fmt.Printf("Case %d original: %v\n", idx+1, levelOrderValues(root))
		fmt.Printf("  Recursive DFS: %v\n", levelOrderValues(invertTreeRecursive(cloneTree(root))))
		fmt.Printf("  Iterative DFS : %v\n", levelOrderValues(invertTreeIterativeDFS(cloneTree(root))))
		fmt.Printf("  BFS           : %v\n", levelOrderValues(invertTreeBFS(cloneTree(root))))
		fmt.Println()
	}
}



