# 543. Diameter of Binary Tree

- Problem: https://leetcode.com/problems/diameter-of-binary-tree/
- Reference: https://neetcode.io/solutions/diameter-of-binary-tree

## Problem Description

Given the `root` of a binary tree, return the **length of the diameter** of the tree.

The **diameter** of a binary tree is the length (number of edges) of the **longest path** between any two nodes in the tree. This path may or may not pass through the root.

## Examples

```
Input: root = [1,null,2,3,4,5]
Output: 3
Explanation: Longest path has 3 edges, e.g. 1 → 2 → 3 → 5 or 5 → 3 → 2 → 4.
```

```
Input: root = [1,2,3]
Output: 2
Explanation: Longest path is 2 → 1 → 3 with length 2.
```

## Constraints

- Number of nodes: `1 <= n <= 10^4`
- `-100 <= Node.val <= 100`

## Approaches

### Solution 1: Brute Force (Height per Node)
- **Time:** O(n²) — for each node, compute left/right subtree height with DFS
- **Space:** O(n) — recursion stack
- **Idea:** For every node, compute `leftHeight + rightHeight` and track the maximum. But recomputing heights for every node leads to quadratic time.
- **Why it falls short:** Each subtree is recomputed many times.

### Solution 2: DFS with Global Diameter (Optimal)
- **Time:** O(n) — each node visited once
- **Space:** O(h) — recursion depth where `h` is the tree height
- **Idea:** In one DFS, return the **height** of the subtree and **update a global diameter** as `leftHeight + rightHeight` at each node.
- **Key insight:** Height of a node is `1 + max(leftHeight, rightHeight)`. The diameter can be updated “on the way back up” during recursion.

### Solution 3: Iterative DFS with Post-Order Simulation
- **Time:** O(n)
- **Space:** O(n) — stack + map for heights
- **Idea:** Use an explicit stack to simulate post-order traversal. After both children of a node are processed, compute its `height` and best `diameter` using stored child data.
- **Tradeoff:** More complex than recursive DFS, but avoids recursion limitations and is a good pattern for iterative tree DP.

## Solutions

- Go: `solutions/go/main.go`
- Java: `solutions/java/Solution.java`

## Key Notes

- **Height vs Diameter:**
  - Height of a node = number of nodes/edges from that node down to the deepest leaf.
  - Diameter through a node = `leftHeight + rightHeight` (in edges).
- The final answer is the **maximum diameter seen over all nodes**, not necessarily passing through the root.
- Reusing the height computation from [104. Maximum Depth of Binary Tree](https://neetcode.io/solutions/maximum-depth-of-binary-tree) is the core trick — we just track an extra global value for the diameter.

