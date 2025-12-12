# Heaps - A Simple Guide

## 🎯 What is a Heap? (Like Explaining to a 5-Year-Old)

Imagine you have a pile of toys, and you always want to grab the **biggest** toy (or the **smallest** toy) from the pile. A heap is like a special way to organize your toys so you can always quickly find the biggest or smallest one!

**Real-world example:** Think of a hospital emergency room. Patients with the most serious injuries get treated first, even if they arrived later. A heap helps organize this priority system!

---

## 📚 What is a Heap?

A **heap** is a special tree-based data structure that satisfies the **heap property**:

### Max-Heap Property
- **Parent is always bigger** than its children
- The **largest** element is always at the root (top)
- Like a pyramid: the king (biggest) is at the top!

```
        50          ← Root (biggest)
       /  \
     30    40
    /  \  /  \
  20  10 25  35
```

### Min-Heap Property
- **Parent is always smaller** than its children
- The **smallest** element is always at the root (top)
- Like an inverted pyramid: the smallest is at the top!

```
        10          ← Root (smallest)
       /  \
     20    15
    /  \  /  \
  30  25 40  35
```

---

## 🏗️ How Heaps are Stored

Heaps are stored in an **array**, not as a tree with pointers! This makes them very memory-efficient.

### Visual Example: Max-Heap

**Tree View:**
```
        50
       /  \
     30    40
    /  \  /  \
  20  10 25  35
```

**Array View:**
```
Index:  0   1   2   3   4   5   6
Array: [50, 30, 40, 20, 10, 25, 35]
```

### How to Navigate the Array

For a node at index `i`:
- **Parent**: `(i - 1) / 2`
- **Left Child**: `2 * i + 1`
- **Right Child**: `2 * i + 2`

**Example:** Node at index 1 (value 30)
- Parent: `(1 - 1) / 2 = 0` → value 50 ✅
- Left Child: `2 * 1 + 1 = 3` → value 20 ✅
- Right Child: `2 * 1 + 2 = 4` → value 10 ✅

---

## 🔧 Key Operations

### 1. Insert (Add a New Element)

**Step-by-step for Max-Heap:**

1. **Add to the end** of the array (last position)
2. **Bubble up**: Compare with parent, swap if parent is smaller
3. **Repeat** until heap property is restored

**Visual Example: Insert 45 into max-heap**

**Initial heap:**
```
        50
       /  \
     30    40
    /  \  /  \
  20  10 25  35
```

**Step 1:** Add 45 at the end
```
        50
       /  \
     30    40
    /  \  /  \  \
  20  10 25  35 45
```

**Step 2:** Compare 45 with parent (40). 45 > 40, so swap!
```
        50
       /  \
     30    45    ← 45 moved up
    /  \  /  \  \
  20  10 25  40 35
```

**Step 3:** Compare 45 with parent (50). 45 < 50, so stop!
```
        50
       /  \
     30    45    ← Final position
    /  \  /  \  \
  20  10 25  40 35
```

**Time Complexity:** O(log n) - we only go up the tree height

---

### 2. Extract Max/Min (Remove Root)

**Step-by-step for Max-Heap:**

1. **Save the root** (this is what we return)
2. **Move last element** to root
3. **Bubble down**: Compare with children, swap with larger child
4. **Repeat** until heap property is restored

**Visual Example: Extract max from heap**

**Initial heap:**
```
        50          ← We want to remove this
       /  \
     30    40
    /  \  /  \
  20  10 25  35
```

**Step 1:** Save 50, move last element (35) to root
```
        35          ← Last element moved here
       /  \
     30    40
    /  \  /
  20  10 25
```

**Step 2:** Compare 35 with children (30, 40). 40 is larger, so swap!
```
        40          ← Swapped with larger child
       /  \
     30    35
    /  \  /
  20  10 25
```

**Step 3:** Compare 35 with children (25, none). 35 > 25, so stop!
```
        40          ← Final heap
       /  \
     30    35
    /  \  /
  20  10 25
```

**Return:** 50 (the original max)

**Time Complexity:** O(log n) - we only go down the tree height

---

## 📊 Max-Heap vs Min-Heap

| Feature | Max-Heap | Min-Heap |
|---------|----------|----------|
| **Root** | Largest element | Smallest element |
| **Property** | Parent ≥ Children | Parent ≤ Children |
| **Use Case** | Find maximum quickly | Find minimum quickly |
| **Example** | Priority queue (highest first) | Priority queue (lowest first) |

---

## 🎯 When to Use a Heap

### ✅ Perfect For:

1. **Priority Queues**
   - Emergency room: most urgent patients first
   - Task scheduler: highest priority tasks first
   - Example: Operating system process scheduling

2. **Finding K Largest/Smallest Elements**
   - Find top 10 highest scores
   - Find 5 cheapest products
   - Example: "Show me the 3 most expensive items"

3. **Heap Sort**
   - Efficient sorting algorithm
   - Time: O(n log n)

4. **Graph Algorithms**
   - Dijkstra's shortest path
   - Prim's minimum spanning tree
   - Always need the "best" next node

### ❌ Not Good For:

1. **Random Access**
   - Can't access element by index efficiently
   - Use array or hash table instead

2. **Searching for Specific Value**
   - Need to check every element
   - Use hash table or binary search tree instead

3. **Frequent Priority Changes**
   - Changing priorities is expensive
   - Use balanced BST instead

---

## ⚡ Performance Summary

| Operation | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| **Insert** | O(log n) | O(1) |
| **Extract Max/Min** | O(log n) | O(1) |
| **Peek (get root)** | O(1) | O(1) |
| **Build Heap** | O(n) | O(n) |
| **Search** | O(n) | O(1) |

**Key Insight:** Heaps are optimized for getting the max/min quickly, not for searching!

---

## 🧪 Example Walkthrough

Let's build a max-heap step by step with values: `[10, 20, 15, 30, 25]`

### Step 1: Insert 10
```
10
```

### Step 2: Insert 20
```
    20    ← 20 > 10, so it bubbles up
   /
  10
```

### Step 3: Insert 15
```
    20
   /  \
  10  15
```

### Step 4: Insert 30
```
    30    ← 30 bubbles up past 20
   /  \
  20  15
 /
10
```

### Step 5: Insert 25
```
    30
   /  \
  20  15
 /  \
10  25    ← 25 < 20, stays here
```

### Final Heap:
```
Array: [30, 20, 15, 10, 25]
```

### Extract Max:
1. Return 30
2. Move 25 to root
3. Bubble down: 25 swaps with 20
4. Final: `[25, 20, 15, 10]`

---

## 💡 Key Insights

1. **Complete Binary Tree**: Heaps are always complete (filled left to right)
2. **Array Storage**: More efficient than pointer-based trees
3. **Heapify**: The process of restoring heap property
4. **Bubble Up**: When inserting, move element up the tree
5. **Bubble Down**: When extracting, move element down the tree

---

## 🔗 Related Data Structures

- **Binary Search Tree**: Sorted, but slower insertions
- **Priority Queue**: Often implemented using heaps
- **Balanced BST**: Better for dynamic priority changes

---

## 📖 References


- LeetCode: Kth Largest Element, Last Stone Weight
- Real-world: Operating systems, game engines, network routing
