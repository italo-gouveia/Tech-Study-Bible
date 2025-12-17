# 1046. Last Stone Weight

- Problem: https://leetcode.com/problems/last-stone-weight/
- Reference: https://neetcode.io/solutions/last-stone-weight

## Problem Description

You are given an array of integers `stones` where `stones[i]` is the weight of the `i`-th stone.

We play a game with the stones. On each turn, we choose the **two heaviest stones** and smash them together:
- If `x == y`, both stones are destroyed
- If `x != y`, the lighter stone (`x`) is destroyed, and the heavier stone (`y`) has new weight `y - x`

Continue until there is **at most one stone left**. Return the weight of the last remaining stone, or `0` if none remain.

## Examples

```
Input: stones = [2,7,4,1,8,1]
Output: 1

Explanation:
- Smash 8 and 7 → 8-7=1 → stones: [2,4,1,1,1]
- Smash 4 and 2 → 4-2=2 → stones: [2,1,1,1]
- Smash 2 and 1 → 2-1=1 → stones: [1,1,1]
- Smash 1 and 1 → both destroyed → stones: [1]
- Last stone: 1 ✅
```

```
Input: stones = [1]
Output: 1
Explanation: Only one stone, so it remains.
```

```
Input: stones = [2,3,6,2,4]
Output: 1

Explanation:
- Smash 6 and 4 → 6-4=2 → stones: [2,3,2,2]
- Smash 3 and 2 → 3-2=1 → stones: [1,2,2]
- Smash 2 and 2 → both destroyed → stones: [1]
- Last stone: 1 ✅
```

## Constraints

- `1 <= stones.length <= 30`
- `1 <= stones[i] <= 1000`

## Approaches

### Solution 1: Sorting (Naive)
- **Time:** O(n² log n) — sort on each iteration
- **Space:** O(n) — store all stones
- **Idea:** Repeatedly sort the array, take the two largest, smash them, and add the result back (if non-zero).
- **Why it's slow:** Sorting the entire array on every iteration is expensive.

### Solution 2: Max-Heap (Optimal) ⭐
- **Time:** O(n log n) — build heap once, then O(log n) per operation
- **Space:** O(n) — heap storage
- **Idea:** Use a **max-heap** to efficiently get the two largest stones:
  - Build heap with all stones
  - While heap has > 1 stone:
    - Pop two largest stones
    - If different, push their difference back
  - Return the last stone (or 0 if heap is empty)
- **Key insight:** A max-heap gives us the largest elements in O(log n) time, avoiding repeated sorting.
- **Implementation:** Direct max-heap using `container/heap` with `Less(i, j) bool { return h[i] > h[j] }`

### Solution 2b: Min-Heap with Negated Values (Alternative)
- **Time:** O(n log n) — same as max-heap
- **Space:** O(n) — heap storage
- **Idea:** Use a **min-heap** but store **negated values** to simulate a max-heap:
  - Push `-stone` instead of `stone`
  - When popping, negate back: `-heap.Pop()`
  - This trick works because: min-heap of negatives = max-heap of positives
- **When to use:** If you already have a min-heap implementation and want to reuse it
- **Note:** The direct max-heap approach (Solution 2) is clearer and more intuitive

### Solution 3: Bucket Sort (For Small Values)
- **Time:** O(n + w) where `w` = max stone weight
- **Space:** O(w)
- **Idea:** Use counting/bucket sort when stone weights are bounded and small.
- **When to use:** When weights are small (≤ 1000) and you want to optimize further.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- **Max-heap trick:** In Go, we can use a min-heap with negated values to simulate a max-heap, or implement a proper max-heap
- **Java PriorityQueue:** By default is a min-heap, so we need to use a custom comparator for max-heap
- **Edge case:** If all stones cancel out, return 0
- **Simulation:** Continue until ≤ 1 stone remains

## Differences Between Solutions

### Quick Comparison

| Solution | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Sorting** | O(n² log n) | O(n) | ❌ Not recommended — too slow |
| **Max-Heap** | O(n log n) | O(n) | ✅ **Recommended — optimal** |
| **Bucket Sort** | O(n + w) | O(w) | When weights are small and bounded |

### Detailed Explanation

#### 1. Sorting Approach
- **How it works:** 
  1. Sort the array
  2. Take the two largest (last two elements)
  3. Calculate difference, remove both
  4. If difference > 0, add it back
  5. Repeat until ≤ 1 stone remains
- **Problem:** Sorting on every iteration is O(n log n), and we do it up to n times → O(n² log n)

#### 2. Max-Heap Approach (Optimal)
- **How it works:**
  1. Build a max-heap with all stones
  2. While heap size > 1:
     - Pop two largest stones
     - If they're different, push `|first - second|` back
  3. Return the last stone (or 0 if heap is empty)
- **Why it's better:**
  - Build heap once: O(n log n)
  - Each pop/push: O(log n)
  - Total: O(n log n) instead of O(n² log n)

**Example with stones = [2,7,4,1,8,1]:**
```
Build heap: [8,7,4,2,1,1] (max-heap)

Iteration 1:
  Pop 8 and 7 → difference = 1
  Push 1 → heap: [4,2,1,1,1]

Iteration 2:
  Pop 4 and 2 → difference = 2
  Push 2 → heap: [2,1,1,1]

Iteration 3:
  Pop 2 and 1 → difference = 1
  Push 1 → heap: [1,1,1]

Iteration 4:
  Pop 1 and 1 → difference = 0 (both destroyed)
  Don't push anything → heap: [1]

Result: 1 ✅
```

### When to Use Each

**Use Max-Heap when:**
- ✅ You want the optimal solution
- ✅ In interviews (most expected solution)
- ✅ **Recommended for this problem!** ⭐

**Use Sorting when:**
- ❌ Only for understanding the problem conceptually
- ❌ Not recommended for production

**Use Bucket Sort when:**
- ✅ Stone weights are small and bounded (≤ 1000)
- ✅ You want to optimize further (though heap is usually fine)

