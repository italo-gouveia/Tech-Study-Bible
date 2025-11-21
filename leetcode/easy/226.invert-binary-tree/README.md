# 226. Invert Binary Tree

- Problem: https://leetcode.com/problems/invert-binary-tree/
- Reference: https://neetcode.io/solutions/invert-binary-tree
- 📚 **Diferenças entre as soluções**: [DIFERENCAS_SOLUCOES.md](DIFERENCAS_SOLUCOES.md) - Explicação detalhada das 3 abordagens

## Problem Description

Given the `root` of a binary tree, invert the tree (swap every left/right subtree) and return its root.

## Examples

```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

```
Input: root = [2,1,3]
Output: [2,3,1]
```

```
Input: root = []
Output: []
```

## Constraints

- Number of nodes: `0 <= n <= 100`
- `-100 <= Node.val <= 100`

## Approaches

### Solution 1: Recursive DFS (Preorder Swap)
- **Time:** O(n)
- **Space:** O(n) worst case (recursion stack)
- **Idea:** For each node, swap `left` and `right` pointers and recurse into both children.
- **Key insight:** Swapping before the recursive calls ensures the entire subtree is mirrored.

### Solution 2: Iterative DFS (Stack)
- **Time:** O(n)
- **Space:** O(n) for the stack
- **Idea:** Use a stack to mimic recursion. Pop a node, swap children, push them (if non-null).
- **Why use it:** Avoids recursion depth worries and keeps logic iterative.

### Solution 3: BFS (Queue)
- **Time:** O(n)
- **Space:** O(n) for the queue
- **Idea:** Level-order traversal; when visiting each node, swap children and enqueue them.
- **When helpful:** Useful if you already have a queue-based template or want level-by-level control.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- All approaches simply differ by traversal order; each node is processed once.
- A `nil` (Go) / `null` (Java) root should be returned as-is.
- DFS (recursive) is the canonical solution, but BFS/iterative DFS can be easier to debug in some contexts.

## Differences Between Solutions

### Quick Comparison

| Solution | Data Structure | Traversal Order | When to Use |
|----------|---------------|-----------------|-------------|
| **Recursive DFS** | Call stack (automatic) | Depth-first | ✅ **Most elegant and simple** |
| **Iterative DFS** | Stack (manual) | Depth-first | When avoiding recursion |
| **BFS** | Queue | Level-order (breadth-first) | When needing level-by-level control |

### Detailed Explanation

#### 1. Recursive DFS
- **How it works:** Recursively calls the function for each node, swapping children before recursing
- **Data structure:** Uses the system's call stack (LIFO - Last In, First Out)
- **Memory:** O(h) where h = tree height; O(n) worst case for linear trees
- **Pros:** Simplest and most elegant code (3-4 lines)
- **Cons:** Risk of stack overflow in very deep trees (rare)

**Example flow:**
```
Tree:       4           Process: 4 → 7 → 9 → 6 → 2 → 3 → 1
           / \          (goes deep first, then backtracks)
          2   7
         / \ / \
        1  3 6  9
```

#### 2. Iterative DFS
- **How it works:** Uses an explicit stack to mimic recursion with a loop
- **Data structure:** Manual stack (LIFO)
- **Memory:** O(h) where h = tree height; O(n) worst case
- **Pros:** Avoids recursion, gives explicit control over the stack
- **Cons:** Slightly more code, need to manage stack manually

**Example flow:**
```
Stack operations:
[4] → pop 4, swap, push 2, push 7
[2, 7] → pop 2, swap, push 1, push 3
[1, 3, 7] → pop 1 (no children)
[3, 7] → pop 3 (no children)
[7] → pop 7, swap, push 6, push 9
[6, 9] → pop 6, pop 9
[] → done
```

#### 3. BFS (Level-Order)
- **How it works:** Processes nodes level by level using a queue
- **Data structure:** Queue (FIFO - First In, First Out)
- **Memory:** O(w) where w = maximum width; O(n/2) worst case for complete trees
- **Pros:** Processes level by level (useful for debugging/visualization)
- **Cons:** Can use more memory in very wide trees

**Example flow:**
```
Queue operations:
[4] → dequeue 4, swap, enqueue 7, enqueue 2
[7, 2] → dequeue 7, swap, enqueue 9, enqueue 6
[2, 9, 6] → dequeue 2, swap, enqueue 3, enqueue 1
[9, 6, 3, 1] → dequeue all (no children)
[] → done
```

### Memory Comparison

**Linear tree (worst case for DFS):**
```
1
 \
  2
   \
    3
     \
      4

Recursive DFS:    O(n) - call stack with n frames
Iterative DFS:    O(n) - stack with n elements
BFS:              O(1) - queue with only 1 element at a time! ⭐
```

**Complete tree (worst case for BFS):**
```
        1
       / \
      2   3
     / \ / \
    4  5 6  7

Recursive DFS:    O(log n) - height = log n
Iterative DFS:    O(log n) - height = log n
BFS:              O(n/2) - maximum width = n/2 ⚠️
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
- ✅ You need fine-grained control over processing

**Use BFS when:**
- ✅ You need level-by-level processing (debugging/visualization)
- ✅ Tree is deep but narrow (BFS uses less memory)
- ✅ You want to see inversion happen layer by layer

### Important Note

**All three solutions produce the same result!** The difference is only in **how** they traverse the tree, not in the final inverted tree structure. Choose based on your specific needs, constraints, or coding style preferences.

