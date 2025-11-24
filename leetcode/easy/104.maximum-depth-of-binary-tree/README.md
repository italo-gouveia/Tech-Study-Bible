# 104. Maximum Depth of Binary Tree

- Problem: https://leetcode.com/problems/maximum-depth-of-binary-tree/
- Reference: https://neetcode.io/solutions/maximum-depth-of-binary-tree

## Problem Description

Given the `root` of a binary tree, return its **maximum depth**.

A binary tree's **maximum depth** is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: 3
Explanation: The longest path is 3 → 20 → 15 (or 3 → 20 → 7), depth = 3
```

```
Input: root = [1,null,2]
Output: 2
Explanation: The longest path is 1 → 2, depth = 2
```

```
Input: root = []
Output: 0
```

## Constraints

- Number of nodes: `0 <= n <= 10^4`
- `-100 <= Node.val <= 100`

## Approaches

### Solution 1: Recursive DFS
- **Time:** O(n) - visit each node once
- **Space:** O(h) where h = height of tree
  - Best case (balanced): O(log n)
  - Worst case (linear): O(n)
- **Idea:** For each node, return 1 + the maximum depth of its left and right subtrees. Base case: return 0 for null nodes.
- **Key insight:** The depth at any node = 1 (current node) + max(depth of left subtree, depth of right subtree).

### Solution 2: Iterative DFS (Stack)
- **Time:** O(n)
- **Space:** O(n) for the stack
- **Idea:** Use a stack to store pairs of (node, depth). Track the maximum depth seen so far.
- **Why use it:** Avoids recursion and gives explicit control over the traversal.

### Solution 3: BFS (Level-Order Traversal)
- **Time:** O(n)
- **Space:** O(n) for the queue (worst case: complete tree with width n/2)
- **Idea:** Process the tree level by level. Count how many levels we process.
- **When helpful:** Natural fit for level-order problems; easy to visualize.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Depth definition:** Number of nodes from root to farthest leaf (not edges)
- An empty tree (null root) has depth 0
- All three approaches visit each node exactly once
- Recursive DFS is the most elegant and commonly expected solution
- BFS naturally counts levels, making it intuitive for this problem

## Differences Between Solutions

### Quick Comparison

| Solution | Data Structure | Space Complexity | When to Use |
|----------|---------------|------------------|-------------|
| **Recursive DFS** | Call stack | O(h) - height | ✅ **Most elegant and simple** |
| **Iterative DFS** | Stack | O(n) | When avoiding recursion |
| **BFS** | Queue | O(n) worst case | When needing level-by-level processing |

### Detailed Explanation

#### 1. Recursive DFS
- **How it works:** Recursively calculates depth of left and right subtrees, returns 1 + max of both
- **Base case:** Returns 0 for null nodes
- **Memory:** O(h) where h = tree height
- **Pros:** Simplest code (1-2 lines), most intuitive
- **Cons:** Uses call stack (risk of stack overflow in very deep trees)

**Example:**
```
Tree:       3
           / \
          9  20
             / \
            15  7

maxDepth(3) = 1 + max(maxDepth(9), maxDepth(20))
maxDepth(9) = 1 + max(0, 0) = 1
maxDepth(20) = 1 + max(maxDepth(15), maxDepth(7))
maxDepth(15) = 1 + max(0, 0) = 1
maxDepth(7) = 1 + max(0, 0) = 1
maxDepth(20) = 1 + max(1, 1) = 2
maxDepth(3) = 1 + max(1, 2) = 3 ✅
```

#### 2. Iterative DFS
- **How it works:** Uses a stack to store (node, depth) pairs. Pops nodes, updates max depth, pushes children with incremented depth.
- **Memory:** O(n) for the stack
- **Pros:** Avoids recursion, explicit control
- **Cons:** More code, need to manage stack manually

#### 3. BFS (Level-Order)
- **How it works:** Processes tree level by level. Each iteration processes one complete level and increments the depth counter.
- **Memory:** O(w) where w = maximum width
- **Pros:** Natural for level-based problems, easy to visualize
- **Cons:** Can use more memory in wide trees

**Example:**
```
Level 0: [3]           → depth = 1
Level 1: [9, 20]       → depth = 2
Level 2: [15, 7]       → depth = 3
Result: 3 ✅
```

### When to Use Each

**Use Recursive DFS when:**
- ✅ You want the simplest and most elegant solution
- ✅ Tree depth is reasonable (< 1000 levels)
- ✅ In interviews (most expected solution)
- ✅ **Recommended for this problem!** ⭐

**Use Iterative DFS when:**
- ✅ Tree can be very deep (risk of stack overflow)
- ✅ You want to avoid recursion
- ✅ You need fine-grained control

**Use BFS when:**
- ✅ You need level-by-level processing
- ✅ You want to visualize the tree structure
- ✅ You're already working with level-order traversal

