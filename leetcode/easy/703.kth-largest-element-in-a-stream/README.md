# 703. Kth Largest Element in a Stream

- Problem: https://leetcode.com/problems/kth-largest-element-in-a-stream/
- Reference: https://neetcode.io/solutions/kth-largest-element-in-a-stream

## Problem Description

Design a class to find the **kth largest** integer in a stream of values, including duplicates.

For example, the 2nd largest from `[1, 2, 3, 3]` is `3` (not `2`). The stream is not necessarily sorted.

**Methods to implement:**
- `constructor(int k, int[] nums)` — Initializes the object with `k` and initial stream `nums`
- `add(int val)` — Adds `val` to the stream and returns the kth largest integer

## Examples

```
Input:
["KthLargest", [3, [4,5,8,2]], "add", [3], "add", [5], "add", [10], "add", [9], "add", [4]]

Output: [null, 4, 5, 5, 8, 8]

Explanation:
KthLargest kthLargest = new KthLargest(3, [4,5,8,2]);
kthLargest.add(3);   // return 4 (3rd largest: [2,3,4,5,8] → 4)
kthLargest.add(5);   // return 5 (3rd largest: [2,3,4,5,5,8] → 5)
kthLargest.add(10);  // return 5 (3rd largest: [2,3,4,5,5,8,10] → 5)
kthLargest.add(9);   // return 8 (3rd largest: [2,3,4,5,5,8,9,10] → 8)
kthLargest.add(4);   // return 8 (3rd largest: [2,3,4,4,5,5,8,9,10] → 8)
```

```
Input:
["KthLargest", [3, [1,2,3,3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]

Output: [null, 3, 3, 3, 5, 6]

Explanation:
KthLargest kthLargest = new KthLargest(3, [1,2,3,3]);
kthLargest.add(3);   // return 3 (3rd largest: [1,2,3,3,3] → 3)
kthLargest.add(5);   // return 3 (3rd largest: [1,2,3,3,3,5] → 3)
kthLargest.add(6);   // return 3 (3rd largest: [1,2,3,3,3,5,6] → 3)
kthLargest.add(7);   // return 5 (3rd largest: [1,2,3,3,3,5,6,7] → 5)
kthLargest.add(8);   // return 6 (3rd largest: [1,2,3,3,3,5,6,7,8] → 6)
```

## Constraints

- `1 <= k <= 1000`
- `0 <= nums.length <= 1000`
- `-1000 <= nums[i] <= 1000`
- `-1000 <= val <= 1000`
- There will always be at least `k` integers in the stream when searching for the kth largest

## Approaches

### Solution 1: Sorting (Naive)
- **Time:** O(m × n log n) where `m` = number of `add()` calls, `n` = current stream size
- **Space:** O(n) — store all elements
- **Idea:** Maintain a list of all values. On each `add()`, append the value, sort the list, and return the element at index `size - k`.
- **Why it's slow:** Sorting the entire array on every `add()` call is expensive.

### Solution 2: Min-Heap (Optimal) ⭐
- **Time:** O(m × log k) where `m` = number of `add()` calls
- **Space:** O(k) — only keep the k largest elements
- **Idea:** Use a **min-heap of size k** to maintain only the k largest elements:
  - When adding a value, push it to the heap
  - If heap size exceeds `k`, remove the smallest (which is the root of a min-heap)
  - The kth largest is always the root (smallest of the k largest)
- **Key insight:** We only need to track the k largest elements, not the entire stream. A min-heap of size k efficiently maintains this.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Kth largest with duplicates:** If `[1,2,3,3]` and k=2, the 2nd largest is `3` (not `2`)
- **Min-heap trick:** Keep only k elements in a min-heap. The root is the kth largest (smallest among the k largest)
- **Why min-heap works:** 
  - We want the kth largest, which is the smallest among the k largest elements
  - A min-heap of size k keeps the k largest elements, with the smallest at the root
  - When we add a new element larger than the root, we remove the root and add the new element
- **Space optimization:** O(k) instead of O(n) — we don't need to store all elements

## Differences Between Solutions

### Quick Comparison

| Solution | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Sorting** | O(m × n log n) | O(n) | ❌ Not recommended — too slow |
| **Min-Heap** | O(m × log k) | O(k) | ✅ **Recommended — optimal** |

### Detailed Explanation

#### 1. Sorting Approach
- **How it works:** Store all elements in a list. On each `add()`, append, sort, and return `arr[size - k]`
- **Problem:** Sorting the entire array on every call is expensive
- **Example:** If you have 1000 elements and call `add()` 1000 times, you sort 1000 times!

#### 2. Min-Heap Approach (Optimal)
- **How it works:** 
  1. Maintain a min-heap of size `k`
  2. In constructor: add all initial values, keeping only k largest
  3. In `add()`: push new value, if size > k, pop the smallest
  4. Return the root (smallest of the k largest = kth largest)
- **Why it's better:** 
  - Only stores k elements (O(k) space)
  - Each `add()` is O(log k) instead of O(n log n)
  - Much faster for large streams

**Example with k=3:**
```
Initial: [4,5,8,2]
Heap after constructor: [4,5,8] (min-heap, root=4)

add(3):
  Push 3 → heap: [3,4,5,8]
  Size (4) > k (3) → pop smallest (3)
  Heap: [4,5,8] → return 4 ✅

add(5):
  Push 5 → heap: [4,5,5,8]
  Size (4) > k (3) → pop smallest (4)
  Heap: [5,5,8] → return 5 ✅
```

## Step-by-Step Visual Example (k=5)

Let's trace through a detailed example with **k=5** and initial numbers: `[10, 7, 4, 2, 1, 9, 6]`

### Constructor Phase (Adding Initial Numbers)

**Step 1: Create empty heap**
```
k = 5
Heap: []
```

**Step 2: Add 10**
```
Heap: [10]
Size: 1 ≤ 5 ✅ OK
Top (smallest): 10
```

**Step 3: Add 7**
```
Heap: [7, 10]
Size: 2 ≤ 5 ✅ OK
Top (smallest): 7
```

**Step 4: Add 4**
```
Heap: [4, 7, 10]
Size: 3 ≤ 5 ✅ OK
Top (smallest): 4
```

**Step 5: Add 2**
```
Heap: [2, 4, 7, 10]
Size: 4 ≤ 5 ✅ OK
Top (smallest): 2
```

**Step 6: Add 1**
```
Heap: [1, 2, 4, 7, 10]
Size: 5 = 5 ✅ OK (exactly k elements!)
Top (smallest): 1
```

**Step 7: Add 9**
```
Heap: [1, 2, 4, 7, 9, 10] → Size 6 > 5! ❌
  → Remove smallest (1): [2, 4, 7, 9, 10]
Size: 5 ✅
Top (smallest): 2
```

**Step 8: Add 6**
```
Heap: [2, 4, 6, 7, 9, 10] → Size 6 > 5! ❌
  → Remove smallest (2): [4, 6, 7, 9, 10]
Size: 5 ✅
Top (smallest): 4
```

**Final state after constructor:**
```
Heap: [4, 6, 7, 9, 10]
Top: 4 (this is the 5th largest!)
```

### Add Operations

**add(3):**
```
Heap before: [4, 6, 7, 9, 10]
Add 3: [3, 4, 6, 7, 9, 10] → Size 6 > 5! ❌
  → Remove smallest (3): [4, 6, 7, 9, 10]
Heap after: [4, 6, 7, 9, 10]
Return: 4 ✅ (5th largest)
```

**add(8):**
```
Heap before: [4, 6, 7, 9, 10]
Add 8: [4, 6, 7, 8, 9, 10] → Size 6 > 5! ❌
  → Remove smallest (4): [6, 7, 8, 9, 10]
Heap after: [6, 7, 8, 9, 10]
Return: 6 ✅ (5th largest)
```

**add(12):**
```
Heap before: [6, 7, 8, 9, 10]
Add 12: [6, 7, 8, 9, 10, 12] → Size 6 > 5! ❌
  → Remove smallest (6): [7, 8, 9, 10, 12]
Heap after: [7, 8, 9, 10, 12]
Return: 7 ✅ (5th largest)
```

**add(5):**
```
Heap before: [7, 8, 9, 10, 12]
Add 5: [5, 7, 8, 9, 10, 12] → Size 6 > 5! ❌
  → Remove smallest (5): [7, 8, 9, 10, 12]
Heap after: [7, 8, 9, 10, 12]
Return: 7 ✅ (5th largest - didn't change because 5 < 7)
```

**add(11):**
```
Heap before: [7, 8, 9, 10, 12]
Add 11: [7, 8, 9, 10, 11, 12] → Size 6 > 5! ❌
  → Remove smallest (7): [8, 9, 10, 11, 12]
Heap after: [8, 9, 10, 11, 12]
Return: 8 ✅ (5th largest)
```

### Summary Table

| Action | Heap Before | Add Value | Heap After | Top (5th Largest) |
|--------|-------------|-----------|------------|-------------------|
| Constructor | `[]` | `[10,7,4,2,1,9,6]` | `[4,6,7,9,10]` | 4 |
| add(3) | `[4,6,7,9,10]` | 3 | `[4,6,7,9,10]` | 4 |
| add(8) | `[4,6,7,9,10]` | 8 | `[6,7,8,9,10]` | 6 |
| add(12) | `[6,7,8,9,10]` | 12 | `[7,8,9,10,12]` | 7 |
| add(5) | `[7,8,9,10,12]` | 5 | `[7,8,9,10,12]` | 7 |
| add(11) | `[7,8,9,10,12]` | 11 | `[8,9,10,11,12]` | 8 |

### Key Observations

1. **Heap always maintains exactly k largest elements** (after initial k elements are added)
2. **The root (top) is always the kth largest** (smallest among the k largest)
3. **When adding a value smaller than the root**, it gets removed immediately, so the answer doesn't change
4. **When adding a value larger than the root**, the old root is removed and the new value stays, updating the kth largest

### When to Use Each

**Use Min-Heap when:**
- ✅ You want the optimal solution
- ✅ Stream can be large (many `add()` calls)
- ✅ In interviews (most expected solution)
- ✅ **Recommended for this problem!** ⭐

**Avoid Sorting when:**
- ❌ Stream is large or many `add()` calls
- ❌ Only use for understanding the problem conceptually

