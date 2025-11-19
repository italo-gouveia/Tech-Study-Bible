# 121. Best Time to Buy and Sell Stock

- Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- Reference: https://neetcode.io/solutions/best-time-to-buy-and-sell-stock

## Problem Description

You are given an integer array `prices` where `prices[i]` is the price of a stock on the `i`-th day.

You may choose a single day to buy one share and choose a different day in the future to sell it. Return the **maximum profit** you can achieve. If no profit is possible, return `0`.

## Examples

```
Input: prices = [10,1,5,6,7,1]
Output: 6
Explanation: Buy at 1 (day 2) and sell at 7 (day 5).
```

```
Input: prices = [10,8,7,5,2]
Output: 0
Explanation: Prices never go up after any buy, so profit stays 0.
```

```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy at 1 (day 2) and sell at 6 (day 5).
```

## Constraints

- `1 <= prices.length <= 10^5`
- `0 <= prices[i] <= 10^4`

## Approaches

### Solution 1: Brute Force
- **Time:** O(n²)
- **Space:** O(1)
- **Idea:** Try every `(buy, sell)` pair and track the best profit.
- **Why it falls short:** Nested loops time out quickly as `prices.length` grows.

### Solution 2: Two Pointers / Sliding Window (Optimal)
- **Time:** O(n)
- **Space:** O(1)
- **Idea:** Maintain a left pointer (`buy`) at the lowest price seen so far and a right pointer (`sell`) scanning forward. Update the max profit whenever `prices[sell] > prices[buy]`; otherwise move `buy` to `sell`.
- **Key insight:** We only care about the minimum price before the current day to compute the best profit ending today.

### Solution 3: Dynamic Programming (Single Pass)
- **Time:** O(n)
- **Space:** O(1)
- **Idea:** Track `minBuy` (the best price so far) and `maxProfit`. For every price, compute the profit if we sell today (`price - minBuy`), update `maxProfit`, then possibly lower `minBuy`.
- **Why it works:** Equivalent to Kadane's-style running best — we only make one trade, so we just need the lowest buy before each sell.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- The stock must be **bought before it is sold**; no reordering of days.
- If prices keep decreasing, the answer is `0`, because doing nothing beats any loss.
- Tracking the running minimum price is both the two-pointer and DP intuition from [NeetCode's explanation](https://neetcode.io/solutions/best-time-to-buy-and-sell-stock).
- Constraints are small enough for brute force (≤10⁵) only in theory, but optimal O(n) is expected in interviews.

