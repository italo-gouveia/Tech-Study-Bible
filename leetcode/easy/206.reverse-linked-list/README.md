# 206. Reverse Linked List

- Problem: https://leetcode.com/problems/reverse-linked-list/
- Reference: https://neetcode.io/solutions/reverse-linked-list

## Problem Description

Given the head of a singly linked list, reverse the list, and return the reversed list.

## Examples

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

```
Input: head = [1,2]
Output: [2,1]
```

```
Input: head = []
Output: []
```

## Constraints

- The number of nodes in the list is in the range `[0, 5000]`
- `-5000 <= Node.val <= 5000`

**Follow up:** A linked list can be reversed either iteratively or recursively. Could you implement both?

## Approaches

### Solution 1: Iterative (Optimal Space)
- **Time:** O(n) — single pass through the list
- **Space:** O(1) — only using a few pointers
- **Idea:** Use three pointers (prev, curr, next) to reverse links in place. Traverse once, reversing each link as you go.

**Key insight:** Before changing `curr.Next`, save `curr.Next` to a temporary variable so you don't lose the rest of the list.

### Solution 2: Recursive
- **Time:** O(n) — visits each node once
- **Space:** O(n) — recursion stack depth equals list length
- **Idea:** Recursively reverse the rest of the list, then reverse the current link. The base case is when we reach the end (null).

**Key insight:** After reversing the rest, `head.Next.Next` points back to `head`, so we can reverse the link.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Iterative approach** is preferred for space efficiency (O(1) vs O(n))
- **Recursive approach** is more elegant but uses O(n) space for the call stack
- Both approaches reverse **in place** — no need to create new nodes
- The trick is to **save the next node** before changing the current node's pointer

## Visualization

**Before:** `1 → 2 → 3 → 4 → 5 → null`

**After:** `5 → 4 → 3 → 2 → 1 → null`

**Iterative process:**
```
Step 1: prev=null, curr=1, next=2
        1 → 2 → 3 → 4 → 5
        ↑
       curr

Step 2: prev=1, curr=2, next=3
        null ← 1  2 → 3 → 4 → 5
               ↑  ↑
             prev curr

Step 3: prev=2, curr=3, next=4
        null ← 1 ← 2  3 → 4 → 5
                  ↑  ↑
                prev curr

...and so on until curr is null
```

