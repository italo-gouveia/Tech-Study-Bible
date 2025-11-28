# 110. Balanced Binary Tree

- Problem: https://leetcode.com/problems/balanced-binary-tree/
- Reference: https://neetcode.io/solutions/balanced-binary-tree

## Problem Description

Given the `root` of a binary tree, determine if it is **height-balanced**.

A **height-balanced** binary tree is defined as a binary tree in which the left and right subtrees of **every node** differ in height by no more than 1.

## Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: true
Explanation: The left subtree of node 3 has height 1, right subtree has height 2. Difference = 1 ≤ 1 ✅
```

```
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Explanation: The left subtree of node 1 has height 3, right subtree has height 1. Difference = 2 > 1 ❌
```

```
Input: root = []
Output: true
Explanation: An empty tree is balanced by definition.
```

## Constraints

- Number of nodes: `0 <= n <= 5000`
- `-10^4 <= Node.val <= 10^4`

## Approaches

### Solution 1: Brute Force (Top-Down)
- **Time:** O(n²) — for each node, compute height of left and right subtrees separately
- **Space:** O(n) — recursion stack
- **Idea:** For every node, check if `|leftHeight - rightHeight| <= 1`, then recursively check left and right subtrees.
- **Why it falls short:** Height is recomputed many times for the same subtrees, leading to quadratic time.

### Solution 2: DFS Bottom-Up (Optimal) ⭐
- **Time:** O(n) — each node visited once
- **Space:** O(h) — recursion depth where `h` is the tree height
  - Best case (balanced): O(log n)
  - Worst case (linear): O(n)
- **Idea:** In a single DFS pass, return both the **balance status** (0 or 1) and the **height** of the subtree. Check balance at each node using the heights returned from children.
- **Key insight:** Combine height calculation with balance checking in one traversal. If any subtree is unbalanced, propagate that information upward.

### Solution 3: Iterative DFS (Post-Order)
- **Time:** O(n)
- **Space:** O(n) — stack + map for storing heights
- **Idea:** Use an explicit stack to simulate post-order traversal. Store heights in a map and check balance when both children are processed.
- **When to use:** When you need to avoid recursion or want explicit control over the stack.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Balance definition:** For every node, `|leftHeight - rightHeight| <= 1`
- An empty tree (`null`) is balanced by definition
- The optimal solution combines height calculation with balance checking in a single pass
- Similar pattern to [543. Diameter of Binary Tree](https://neetcode.io/solutions/diameter-of-binary-tree) — both use bottom-up DFS to compute multiple values in one traversal

## Differences Between Solutions

### Quick Comparison

| Solution | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Brute Force** | O(n²) | O(n) | ❌ Not recommended — too slow |
| **DFS Bottom-Up** | O(n) | O(h) | ✅ **Recommended — optimal and elegant** |
| **Iterative DFS** | O(n) | O(n) | When avoiding recursion |

### Detailed Explanation

#### 1. Brute Force (Top-Down)
- **How it works:** For each node, compute height of left and right subtrees separately, check if balanced, then recurse on children
- **Problem:** Height of subtrees is recomputed many times
- **Example:** To check root, compute height of left subtree. Then to check left child, recompute height of its left subtree again!

#### 2. DFS Bottom-Up (Optimal)
- **How it works:** Return `[isBalanced, height]` from each node. Use children's results to compute current node's balance and height in one pass
- **Key insight:** Bottom-up means we check balance as we return from recursion, using already-computed heights
- **Memory:** O(h) for recursion stack
- **Pros:** Single pass, no redundant calculations, elegant code
- **Cons:** Uses recursion (stack overflow risk in very deep trees)

**Example flow:**
```
Tree:       3
           / \
          9  20
             / \
            15  7

dfs(3):
  left = dfs(9) → [1, 1]  (balanced, height=1)
  right = dfs(20):
    left = dfs(15) → [1, 1]
    right = dfs(7) → [1, 1]
    → [1, 2]  (balanced, height=2)
  → |1 - 2| = 1 ≤ 1 ✅
  → [1, 3]  (balanced, height=3)
Result: true ✅
```

#### 3. Iterative DFS
- **How it works:** Use stack to simulate post-order traversal, store heights in a map
- **Memory:** O(n) for stack and map
- **Pros:** Avoids recursion
- **Cons:** More complex code, uses more memory

### When to Use Each

**Use DFS Bottom-Up when:**
- ✅ You want the optimal solution
- ✅ Tree depth is reasonable (< 1000 levels)
- ✅ In interviews (most expected solution)
- ✅ **Recommended for this problem!** ⭐

**Use Iterative DFS when:**
- ✅ Tree can be very deep (risk of stack overflow)
- ✅ You want to avoid recursion
- ✅ You need explicit control over traversal

**Avoid Brute Force:**
- ❌ Only for educational purposes to understand the problem
- ❌ Too slow for large trees

