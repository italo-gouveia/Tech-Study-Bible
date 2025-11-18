# 141. Linked List Cycle

- Problem: https://leetcode.com/problems/linked-list-cycle/
- Reference: https://neetcode.io/solutions/linked-list-cycle

## Problem Description

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to. Note that `pos` is **not passed as a parameter**.

Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

## Examples

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
```

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
```

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

## Constraints

- The number of the nodes in the list is in the range `[0, 10^4]`
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a valid index in the linked-list

**Follow up:** Can you solve it using O(1) (i.e. constant) memory?

## Approaches

### Solution 1: Hash Set
- **Time:** O(n) — single pass through the list
- **Space:** O(n) — storing all visited nodes in a hash set
- **Idea:** Traverse the list and store each visited node in a hash set. If we encounter a node that's already in the set, we've found a cycle.

**Key insight:** A cycle exists if and only if we visit the same node twice during traversal.

### Solution 2: Floyd's Cycle Detection (Fast and Slow Pointers) ⭐ Optimal
- **Time:** O(n) — linear time complexity
- **Space:** O(1) — only using two pointers
- **Idea:** Use two pointers moving at different speeds. The slow pointer moves one step at a time, while the fast pointer moves two steps. If there's a cycle, the fast pointer will eventually catch up to the slow pointer.

**Key insight:** If there's a cycle, the fast pointer will eventually meet the slow pointer. If there's no cycle, the fast pointer will reach the end (null) first.

**Why it works:** When there's a cycle, the fast pointer moves faster and continuously loops through the cycle. With each step, it reduces the gap between itself and the slow pointer by one node. For example, if the gap is 10, the slow pointer moves by 1 (gap becomes 11), while the fast pointer moves by 2 (gap becomes 9). This process continues until the fast pointer catches up to the slow pointer, confirming a cycle.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Floyd's Cycle Detection (Tortoise and Hare)** is the optimal solution with O(1) space
- **Hash Set approach** is intuitive but requires O(n) extra space
- The fast pointer must check both `fast != null` and `fast.next != null` before moving two steps
- If the fast pointer reaches `null`, there's no cycle
- If `fast == slow` at any point, a cycle is detected

## Performance Notes

**Why Java might show 0ms vs Go's 7ms:**

1. **JIT Compilation**: Java uses Just-In-Time (JIT) compilation that can optimize code at runtime based on execution patterns. The JVM can inline methods, eliminate bounds checks, and apply other aggressive optimizations after "warming up".

2. **AOT Compilation**: Go compiles Ahead-Of-Time (AOT) to native code. While this eliminates runtime compilation overhead, it may not apply the same level of aggressive optimizations that JIT can.

3. **Benchmark Variance**: Small timing differences (0-10ms) can be due to:
   - System load
   - CPU cache effects
   - Garbage collection pauses (minimal in this case)
   - Measurement precision

4. **Both are Excellent**: 7ms is still extremely fast for this algorithm. The O(n) time complexity is the same in both languages, and both solutions are optimal.

**Bottom line**: The algorithm is correct and efficient in both languages. The small timing difference is likely due to runtime/compiler characteristics rather than algorithmic differences.

## Visualization

**Example with cycle:** `head = [3,2,0,-4]`, cycle at index 1

```
Step 1: slow=3, fast=3
3 → 2 → 0 → -4
↑   ↑
│   └─┐
└─────┘

Step 2: slow=2, fast=0
3 → 2 → 0 → -4
    ↑   ↑
    │   └─┐
    └─────┘

Step 3: slow=0, fast=2
3 → 2 → 0 → -4
        ↑   ↑
        │   └─┐
        └─────┘

Step 4: slow=-4, fast=-4 (they meet!)
3 → 2 → 0 → -4
            ↑↑
            └┘ (cycle detected!)
```

**Example without cycle:** `head = [1]`

```
Step 1: slow=1, fast=1
1 → null

Step 2: slow=null, fast=null (fast reaches end first)
No cycle detected
```

