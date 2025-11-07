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

The entry “Best for remaining 1 kg” was already computed while processing smaller capacities.

---

## 12. Summary

- **Brute force** tries every subset: `O(2^n)` operations → impractical.
- **Greedy** is fast but approximate.
- **Dynamic programming** builds the answer from smaller subproblems, reusing work and guaranteeing the optimum.
- The final solution for a 4 kg knapsack is **Laptop + Guitar = R$ 3,500**.

Dynamic programming lets us compute the exact optimum efficiently by storing and reusing the results of sub-knapsacks.
