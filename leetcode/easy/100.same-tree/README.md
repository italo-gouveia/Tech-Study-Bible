# 100. Same Tree

- Problem: https://leetcode.com/problems/same-tree/
- Reference: https://neetcode.io/solutions/same-tree

## Problem Description

Given the roots of two binary trees `p` and `q`, return `true` if the trees are **exactly the same**, and `false` otherwise.

Two binary trees are considered the same if:
- They have the **same structure** (nodes in the same positions)
- The **values** at all corresponding nodes are equal

## Examples

```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```

```
Input: p = [4,7], q = [4,null,7]
Output: false
Explanation: Structure differs (left-null vs right-child).
```

```
Input: p = [1,2,3], q = [1,3,2]
Output: false
Explanation: Same structure, different values in children.
```

## Constraints

- Number of nodes: `0 <= n <= 100`
- `-100 <= Node.val <= 100`

## Approaches

### Solution 1: Recursive DFS (Top-Down) ⭐
- **Time:** O(n) — visit each node at most once
- **Space:** O(h) — recursion depth, where `h` is the tree height
- **Idea:** At each pair of nodes `(p, q)`:
  - If both are `null` → they match
  - If one is `null` and the other is not → mismatch
  - If both exist but `p.val != q.val` → mismatch
  - Otherwise, recursively compare:
    - `p.left` with `q.left`
    - `p.right` with `q.right`
- **Key insight:** This is a direct structural and value comparison using DFS.

### Solution 2: Iterative DFS (Stack)
- **Time:** O(n)
- **Space:** O(n)
- **Idea:** Use a stack of node pairs `(pNode, qNode)`:
  - Pop a pair and run the same checks as in recursive DFS
  - If they match, push their left children and right children as new pairs
- **When to use:** When you want to avoid recursion or control the stack explicitly.

### Solution 3: BFS (Level-Order Comparison)
- **Time:** O(n)
- **Space:** O(n)
- **Idea:** Use two queues and compare level by level:
  - Dequeue corresponding nodes from both trees
  - Perform the same null/value checks
  - Enqueue their children in the same order (left, then right)
- **When to use:** When you prefer level-order logic or want a breadth-first view.

## Solutions

- Go: `solutions/go/main.go`
- Java: `solutions/java/Solution.java`

## Key Notes

- Both **structure** and **values** must match.
- If both roots are `null`, the trees are trivially the same.
- All three approaches implement the **same logic**; they differ only in traversal style (recursive DFS, iterative DFS, BFS).
- Recursive DFS is the most concise and commonly expected solution in interviews.


