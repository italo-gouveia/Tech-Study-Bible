# 572. Subtree of Another Tree

- Problem: https://leetcode.com/problems/subtree-of-another-tree/
- Reference: https://neetcode.io/solutions/subtree-of-another-tree

## Problem Description

Given the roots of two binary trees `root` and `subRoot`, return `true` if there is a **subtree** of `root` with the same structure and node values as `subRoot`, and `false` otherwise.

A **subtree** of a binary tree `tree` is a tree that consists of a node in `tree` and **all of that node's descendants**. The tree `tree` itself is also considered a subtree of itself.

## Examples

```
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
```

```
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false
```

## Constraints

- `1 <= number of nodes in root <= 2000`
- `1 <= number of nodes in subRoot <= 1000`
- `-10^4 <= root.val, subRoot.val <= 10^4`

## Approaches

### Solution 1: DFS + Same Tree Check (Top-Down) ⭐
- **Time:** O(m * n) in the worst case  
  - `n` = number of nodes in `root`  
  - `m` = number of nodes in `subRoot`
- **Space:** O(h_root + h_sub) — recursion depth of both trees
- **Idea:**  
  - Walk the big tree `root` with DFS.
  - At each node, if its value matches `subRoot.val`, call a helper `sameTree(node, subRoot)` to check if the subtrees are **exactly the same** (reusing the logic from [100. Same Tree](https://neetcode.io/solutions/same-tree)).
  - If any call to `sameTree` returns `true`, `subRoot` is a subtree.
- **Key insight:** This is literally “Same Tree” applied at every candidate node in `root`.

Algorithm sketch:
- If `subRoot` is `null` → return `true` (empty tree is a subtree of any tree).
- If `root` is `null` but `subRoot` is not → return `false`.
- At each node:
  - If `sameTree(root, subRoot)` is `true` → return `true`.
  - Else, recursively check `isSubtree(root.left, subRoot)` and `isSubtree(root.right, subRoot)`.

### Solution 2: Serialization + Pattern Matching
- **Time:** O(m + n) with efficient pattern matching (e.g., Z-function or KMP)
- **Space:** O(m + n)
- **Idea:**  
  - Serialize both trees into strings (e.g., preorder) with **null markers** and separators.
  - Let `S_root` be serialization of `root`, `S_sub` serialization of `subRoot`.
  - Check if `S_sub` is a substring of `S_root` using linear-time pattern matching.
- **Why include it:** Shows an alternative, more “stringy” view of the problem; often overkill for interviews but good to know.

## Solutions

- Go: `solutions/go/main.go`
- Java: `solutions/java/Solution.java`

## Key Notes

- This problem is tightly related to [100. Same Tree](https://neetcode.io/solutions/same-tree) — we reuse the same equality check as a helper.
- The DFS + `sameTree` approach is the most common and intuitive for interviews.
- Be careful with edge cases:
  - Empty `subRoot` → always `true`.
  - Non-empty `subRoot` and empty `root` → `false`.


