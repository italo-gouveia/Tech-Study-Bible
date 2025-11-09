# Chapter 9: Dynamic Programming — Grokking Algorithms

## 1. Revisiting the Knapsack Problem

**Scenario:** A thief has a knapsack that can hold at most **4 kg**. Three items are available:

| Item   | Value (R$) | Weight (kg) |
|--------|------------|-------------|
| Guitar | 1,500      | 1           |
| Stereo | 3,000      | 4           |
| Laptop | 2,000      | 3           |

**Goal:** Select the combination of items with the **maximum total value** without exceeding the weight limit.

---

## 2. Brute Force (Exhaustive Search)

- Evaluate every subset of items and keep the most valuable combination.
- For `n` items there are `2^n` subsets.

| Items | Subsets to check |
|-------|------------------|
| 3     | 8                |
| 4     | 16               |
| 5     | 32               |
| 10    | 1,024            |
| 32    | ~4,300,000,000   |

**Time complexity:** `O(2^n)` — this becomes infeasible very quickly.

---

## 3. Greedy Approximation (Chapter 8)

- Pick items by the best value-to-weight ratio.
- Fast and simple, but the answer is only an approximation and may miss the true optimum.

We want the **exact** optimum without brute forcing every combination. Dynamic programming gives us that.

---

## 4. Dynamic Programming Overview

Dynamic programming (DP) solves a problem by combining solutions to smaller subproblems.

For knapsack:

1. Consider every item one at a time.
2. Consider every possible capacity from 1 kg up to 4 kg.
3. Store the best solution found so far for each `(item, capacity)` pair.
4. Use previously stored answers when we have spare capacity after picking an item.

---

## 5. DP Table Structure

- **Rows**: items (in the order we consider them)
  - Row 1: Guitar
  - Row 2: Stereo
  - Row 3: Laptop
- **Columns**: knapsack capacities (1 kg, 2 kg, 3 kg, 4 kg)

```
            Capacity →   1 kg   2 kg   3 kg   4 kg
Items ↓
Guitar
Stereo
Laptop
```

Each cell holds the **maximum value** achievable with the items up to that row and the capacity of that column.

The table starts empty. We fill it row by row.

---

## 6. Row 1 — Guitar (value 1,500; weight 1 kg)

With only the guitar available, the guitar fits in any knapsack of capacity ≥ 1 kg.

```
            1 kg   2 kg   3 kg   4 kg
Guitar      1500   1500   1500   1500
```

Interpretation:
- For every capacity, stealing the guitar yields 1,500 R$.
- This is our initial estimate of the best value for each capacity.

---

## 7. Row 2 — Stereo (value 3,000; weight 4 kg)

Now we can choose between guitar and stereo.

```
            1 kg   2 kg   3 kg   4 kg
Guitar      1500   1500   1500   1500
Stereo      1500   1500   1500   3000
```

Explanation:
- Capacities 1–3 kg: the stereo does **not** fit, so the best value stays 1,500.
- Capacity 4 kg: the stereo fits exactly and is worth 3,000. We update the cell.

Updated estimate for 4 kg is now **3,000 (Stereo)**.

---

## 8. Row 3 — Laptop (value 2,000; weight 3 kg)

Now all three items are available. Evaluate each capacity again.

```
            1 kg   2 kg   3 kg   4 kg
Guitar      1500   1500   1500   1500
Stereo      1500   1500   1500   3000
Laptop      1500   1500   2000   3500
```

Cell-by-cell logic for the laptop row:

- Capacities 1–2 kg: laptop does not fit → keep the previous value (1,500).
- Capacity 3 kg: laptop fits and gives 2,000. That beats 1,500 → update to 2,000.
- Capacity 4 kg: two choices
  1. Keep the previous best (3,000 from the stereo).
  2. Take the laptop (2,000) and use the remaining 1 kg for the best value we have for 1 kg. Row 2 column 1 stores 1,500 (the guitar).

  `2,000 + 1,500 = 3,500` → better than 3,000, so we update to **3,500 (Laptop + Guitar)**.

---

## 9. Final DP Table With Choices

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
```

Legend: `(G)` means the guitar is included. The bottom-right cell shows the optimal set: **Laptop + Guitar**.

---

## 10. Recurrence Formula

Let `dp[i][j]` be the best value using the first `i` items and capacity `j`.

```
if weight[i] > j:
    dp[i][j] = dp[i-1][j]         # item does not fit
else:
    dp[i][j] = max(
        dp[i-1][j],              # skip the item
        value[i] + dp[i-1][j - weight[i]]
    )
```

We apply this rule to every cell in the table. Every entry either:
- Carries over the previous best (skip the item), or
- Adds the item value plus the best value for the remaining capacity.

---

## 11. Combining Subproblems

Dynamic programming reuses answers to smaller capacities when space is left in the knapsack.

```
Best for 4 kg = Laptop (3 kg) + Best for remaining 1 kg
               = 2,000 + 1,500
               = 3,500
```

The entry "Best for remaining 1 kg" was already computed while processing smaller capacities.

---

## 12. Frequently Asked Questions About the Knapsack Problem

You might still think of this solution as some kind of magic. This section addresses common questions.

### 12.1 What Happens If You Add an Item?

**Question:** Imagine there's a **fourth item** you can steal that you hadn't noticed before. Suppose it's an **iPhone**.

| Item   | Value (R$) | Weight (kg) |
|--------|------------|-------------|
| iPhone | 2,000      | 1           |

**Do you need to recalculate everything from scratch?**

**Answer:** **No!** Remember: dynamic programming builds progressively. You only need to add **one new row** to the table.

**Current table (before iPhone):**

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
```

This means that for a 4 kg knapsack, you can steal items worth **R$ 3,500**.

**Now, let's add a row for the iPhone:**

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   [to fill]     [to fill]     [to fill]        [to fill]
```

**Try filling this new row before continuing!**

#### **Cell 1 (Capacity 1 kg)**

The iPhone can be taken with a 1 kg knapsack.

- Previous maximum: **R$ 1,500** (Guitar)
- iPhone value: **R$ 2,000**

Since 2,000 > 1,500, we take the **iPhone**.

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   2000 (I)      [to fill]     [to fill]        [to fill]
```

#### **Cell 2 (Capacity 2 kg)**

In the next cell, we can take the iPhone and the guitar.

- Previous maximum: **R$ 1,500** (Guitar)
- iPhone (2,000) + Best for remaining 1 kg: 2,000 + 1,500 = **R$ 3,500**

Since 3,500 > 1,500, we take **iPhone + Guitar**.

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   2000 (I)      3500 (I+G)    [to fill]        [to fill]
```

#### **Cell 3 (Capacity 3 kg)**

For cell 3, there's no better option than taking the iPhone and the guitar again.

- Previous maximum: **R$ 2,000** (Laptop)
- iPhone (2,000) + Best for remaining 2 kg: 2,000 + 1,500 = **R$ 3,500**

Since 3,500 > 2,000, we keep **iPhone + Guitar**.

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   2000 (I)      3500 (I+G)    3500 (I+G)       [to fill]
```

#### **Cell 4 (Capacity 4 kg)**

In the last cell, things get interesting!

- Previous maximum: **R$ 3,500** (Laptop + Guitar)
- iPhone (2,000) + Best for remaining 3 kg: 2,000 + 2,000 = **R$ 4,000**

Wait! Where did the 2,000 for 3 kg come from? Look at the **Laptop row, column 3** — it shows **R$ 2,000** (the laptop alone).

So: iPhone (2,000) + Laptop (2,000) = **R$ 4,000**!

Since 4,000 > 3,500, we have a **new maximum: iPhone + Laptop**!

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   2000 (I)      3500 (I+G)    3500 (I+G)       4000 (I+Laptop)
```

**Final answer:** With the iPhone available, the maximum value for a 4 kg knapsack is **R$ 4,000 (iPhone + Laptop)**.

### 12.2 Can Column Values Decrease?

**Question:** Is it possible for a column value to **decrease** as we add more rows?

**Answer:** **No!** At each iteration, you store the **current maximum estimate**. The estimate can **never go below** what it already is!

**Why?** The recurrence formula always takes the `max()` of:
1. The previous best (which can't decrease)
2. A new option (which might be better, but never worse)

So values can only **stay the same** or **increase** — they never decrease.

---

## 13. Exercises

### Exercise 9.1: MP3 Player

Imagine you can steal another item: an **MP3 player**.

| Item       | Value (R$) | Weight (kg) |
|------------|------------|-------------|
| MP3 Player | 1,000      | 1           |

**Question:** Should you steal it?

**Hint:** Add a new row to the table (after iPhone) and fill it using the same recurrence formula. Compare the new maximum value with the previous one.

**Answer:** 

Let's fill the MP3 Player row step by step:

**Cell 1 (Capacity 1 kg):**
- Previous maximum: **R$ 2,000** (iPhone)
- MP3 Player value: **R$ 1,000**

Since 2,000 > 1,000, we keep the **iPhone (2,000)**.

**Cell 2 (Capacity 2 kg):**
- Previous maximum: **R$ 3,500** (iPhone + Guitar)
- MP3 (1,000) + Best for remaining 1 kg: 1,000 + 2,000 = **R$ 3,000**

Since 3,500 > 3,000, we keep **iPhone + Guitar (3,500)**.

**Cell 3 (Capacity 3 kg):**
- Previous maximum: **R$ 3,500** (iPhone + Guitar)
- MP3 (1,000) + Best for remaining 2 kg: 1,000 + 3,500 = **R$ 4,500**

Since 4,500 > 3,500, we **update to MP3 + iPhone + Guitar (4,500)**!

**Cell 4 (Capacity 4 kg):**
- Previous maximum: **R$ 4,000** (iPhone + Laptop)
- MP3 (1,000) + Best for remaining 3 kg: 1,000 + 3,500 = **R$ 4,500**

Since 4,500 > 4,000, we **update to MP3 + iPhone + Guitar (4,500)**!

**Final table with MP3 Player:**

```
             1 kg           2 kg           3 kg              4 kg
Guitar   1500 (G)      1500 (G)      1500 (G)         1500 (G)
Stereo   1500 (G)      1500 (G)      1500 (G)         3000 (Stereo)
Laptop   1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
iPhone   2000 (I)      3500 (I+G)    3500 (I+G)       4000 (I+Laptop)
MP3      2000 (I)      3500 (I+G)    4500 (MP3+I+G)   4500 (MP3+I+G)
```

**Conclusion:** Yes! The MP3 player improves the solution. The new maximum for 4 kg is **R$ 4,500 (MP3 + iPhone + Guitar)**.

---

## 14. More FAQs: Row Order, Fill Direction, and Fractional Weights

### 14.1 What Happens If You Change the Row Order?

**Question:** Will the answer change if you process items in a different order?

**Answer:** **No!** The order of rows does **not** affect the final answer.

**Example:** Let's fill the table in this order: **Stereo, Laptop, Guitar** (instead of Guitar, Stereo, Laptop).

**Try filling it yourself before continuing!**

**Table with order: Stereo, Laptop, Guitar**

**Row 1 — Stereo (value 3,000; weight 4 kg):**
- Capacities 1–3 kg: Stereo doesn't fit → **0** (or we could leave empty)
- Capacity 4 kg: Stereo fits → **3,000 (Stereo)**

```
             1 kg           2 kg           3 kg              4 kg
Stereo        0             0             0             3000 (Stereo)
```

**Row 2 — Laptop (value 2,000; weight 3 kg):**
- Capacity 1 kg: Laptop doesn't fit → keep 0
- Capacity 2 kg: Laptop doesn't fit → keep 0
- Capacity 3 kg: Laptop fits → **2,000 (Laptop)**
- Capacity 4 kg: Compare Stereo (3,000) vs Laptop (2,000) → keep **3,000 (Stereo)**

```
             1 kg           2 kg           3 kg              4 kg
Stereo        0             0             0             3000 (Stereo)
Laptop        0             0          2000 (Laptop)    3000 (Stereo)
```

**Row 3 — Guitar (value 1,500; weight 1 kg):**
- Capacity 1 kg: Guitar fits → **1,500 (Guitar)**
- Capacity 2 kg: Guitar (1,500) vs previous (0) → **1,500 (Guitar)**
- Capacity 3 kg: Compare Laptop (2,000) vs Guitar (1,500) → keep **2,000 (Laptop)**
- Capacity 4 kg: Compare Stereo (3,000) vs Laptop+Guitar (2,000+1,500=3,500) → **3,500 (Laptop + Guitar)**

```
             1 kg           2 kg           3 kg              4 kg
Stereo        0             0             0             3000 (Stereo)
Laptop        0             0          2000 (Laptop)    3000 (Stereo)
Guitar     1500 (G)      1500 (G)      2000 (Laptop)    3500 (Laptop + G)
```

**Result:** The final answer is **still R$ 3,500 (Laptop + Guitar)**! ✅

**Why doesn't order matter?**

The recurrence formula considers **all possible combinations** regardless of processing order. Dynamic programming explores all subsets implicitly, so the order in which we add items doesn't change which combinations are possible.

**Important note:** While the **final answer** doesn't change, the **intermediate values** in the table will be different. But the optimal value for capacity 4 kg will always be the same, regardless of the order you process items.

### 14.2 Can You Fill the Table Column by Column?

**Question:** Is it possible to fill the table starting from columns instead of rows?

**Answer:** **Yes, you can!** For this problem, it doesn't make a difference. However, for other problems, the order might matter.

**Column-by-column approach:**

Instead of processing items row by row, you could process capacities column by column:

1. Fill all rows for capacity 1 kg
2. Then fill all rows for capacity 2 kg
3. Then fill all rows for capacity 3 kg
4. Finally, fill all rows for capacity 4 kg

**Why this works:**

The recurrence formula only depends on:
- Previous items (rows above) — available when filling by rows
- Smaller capacities (columns to the left) — available when filling by columns

Since we're filling from top-left to bottom-right in both cases, the dependencies are satisfied either way.

**When might order matter?**

For some DP problems, especially those with complex dependencies or 2D/3D grids, the fill order can be critical. But for the standard 0/1 Knapsack, both approaches work.

### 14.3 What Happens If You Add a Smaller Item?

**Question:** What if you can steal a **necklace** that weighs **0.5 kg** and is worth **R$ 1,000**?

**The Problem:**

Until now, your table assumed only **integer weights** (1 kg, 2 kg, 3 kg, 4 kg).

However, with the necklace (0.5 kg), if you take it, you'll have **3.5 kg** of space remaining.

**What's the maximum value for a 3.5 kg knapsack?**

You don't know! You only calculated for knapsacks of **1, 2, 3, and 4 kg** (integer weights).

**The Solution: Refine Your Table**

You need to **refine your table** to include fractional capacities. The table will be modified to include:

**New table structure with fractional weights:**

```
        0.5 kg   1 kg   1.5 kg   2 kg   2.5 kg   3 kg   3.5 kg   4 kg
Guitar
Stereo
Laptop
Necklace
```

**Why this is necessary:**

When you take the necklace (0.5 kg), you have 3.5 kg remaining. To know what to put in that 3.5 kg, you need to have already calculated the best value for a 3.5 kg knapsack.

**Practical implications:**

- **More columns** = more calculations
- **Smaller increments** (e.g., 0.1 kg steps) = even more calculations
- The problem becomes more complex, but the **same DP approach** still works

**Example calculation with necklace:**

For capacity 4 kg with all items including necklace:

- Previous best (without necklace): **R$ 3,500** (Laptop + Guitar)
- Necklace (1,000) + Best for 3.5 kg: 1,000 + (need to calculate 3.5 kg first)

**Key insight:** With fractional weights, you need to **define a granularity** (e.g., 0.5 kg, 0.1 kg, etc.) and create columns for each possible capacity at that granularity. This increases the table size but the algorithm remains the same.

---

## 15. Summary

- **Brute force** tries every subset: `O(2^n)` operations → impractical.
- **Greedy** is fast but approximate.
- **Dynamic programming** builds the answer from smaller subproblems, reusing work and guaranteeing the optimum.
- The final solution for a 4 kg knapsack is **Laptop + Guitar = R$ 3,500**.

Dynamic programming lets us compute the exact optimum efficiently by storing and reusing the results of sub-knapsacks.
