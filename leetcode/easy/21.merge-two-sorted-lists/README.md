# 21. Merge Two Sorted Lists

- Problem: https://leetcode.com/problems/merge-two-sorted-lists/
- Reference: https://neetcode.io/solutions/merge-two-sorted-lists

## Problem Description

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

## Examples

```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

```
Input: list1 = [], list2 = []
Output: []
```

```
Input: list1 = [], list2 = [0]
Output: [0]
```

## Constraints

- The number of nodes in both lists is in the range `[0, 50]`
- `-100 <= Node.val <= 100`
- Both `list1` and `list2` are sorted in **non-decreasing order**

## Approaches

### Solution 1: Iterative (Optimal Space)
- **Time:** O(n + m) — single pass through both lists
- **Space:** O(1) — only using a dummy node and pointers
- **Idea:** Use a dummy node to simplify edge cases. Compare nodes from both lists, link the smaller one to the result, and advance the pointer. After one list is exhausted, link the remaining nodes.

**Key insight:** The dummy node eliminates the need to handle the empty list case separately. We can always start building from `dummy.Next`.

### Solution 2: Recursive
- **Time:** O(n + m) — visits each node once
- **Space:** O(n + m) — recursion stack depth equals total nodes
- **Idea:** Recursively merge the rest of the lists. At each step, choose the smaller node, recursively merge the rest, and link it.

**Key insight:** The base cases handle when one list is empty. The recursive case chooses the smaller node and continues merging.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Iterative approach** is preferred for space efficiency (O(1) vs O(n+m))
- **Dummy node pattern** simplifies code by avoiding special cases for empty lists
- Both lists are already sorted, so we can merge in a single pass
- When one list is exhausted, we can directly link the remaining nodes (no need to continue comparing)
- The problem asks to merge **in place** — we're reusing existing nodes, not creating new ones

## Visualization

**Example:** `list1 = [1,2,4]`, `list2 = [1,3,4]`

**Iterative process:**
```
Step 1: Compare 1 and 1 → link 1, advance list1
dummy → 1
         ↑
       list1=[2,4], list2=[1,3,4]

Step 2: Compare 2 and 1 → link 1, advance list2
dummy → 1 → 1
              ↑
       list1=[2,4], list2=[3,4]

Step 3: Compare 2 and 3 → link 2, advance list1
dummy → 1 → 1 → 2
                 ↑
       list1=[4], list2=[3,4]

Step 4: Compare 4 and 3 → link 3, advance list2
dummy → 1 → 1 → 2 → 3
                    ↑
       list1=[4], list2=[4]

Step 5: Compare 4 and 4 → link 4, advance list1
dummy → 1 → 1 → 2 → 3 → 4
                       ↑
       list1=[], list2=[4]

Step 6: list1 is empty → link remaining [4]
dummy → 1 → 1 → 2 → 3 → 4 → 4

Result: [1,1,2,3,4,4]
```

