package leetcode.easy.besttimetobuyandsellstock;

import java.util.Arrays;

// LeetCode 121 — Best Time to Buy and Sell Stock
public class Solution {

    // Solution 1: Brute Force (check every buy/sell pair)
    // Time: O(n^2), Space: O(1)
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = buy + 1; sell < prices.length; sell++) {
                int profit = prices[sell] - prices[buy];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    // Solution 2: Two Pointers (track best buy index) - Optimized
    // Time: O(n), Space: O(1)
    // Optimized: removed redundant comparison, always calculate profit
    public int maxProfitTwoPointers(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int buy = 0;
        int maxProfit = 0;

        for (int sell = 1; sell < prices.length; sell++) {
            int profit = prices[sell] - prices[buy];
            if (profit > maxProfit) {
                maxProfit = profit;
            }
            if (prices[sell] < prices[buy]) {
                buy = sell; // found a cheaper day to buy
            }
        }

        return maxProfit;
    }

    // Solution 3: DP-style (track min price and best profit)
    // Time: O(n), Space: O(1)
    public int maxProfitDP(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int minBuy = prices[0];
        int maxProfit = 0;

        for (int price : prices) {
            int profit = price - minBuy;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
            if (price < minBuy) {
                minBuy = price;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] testCases = new int[][]{
            {10, 1, 5, 6, 7, 1},
            {10, 8, 7, 5, 2},
            {7, 1, 5, 3, 6, 4},
            {2},
            {}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] prices = testCases[i];
            System.out.printf("Case %d: prices=%s%n", i + 1, Arrays.toString(prices));
            System.out.printf("  Brute Force: %d%n", solution.maxProfitBruteForce(prices));
            System.out.printf("  Two Pointers: %d%n", solution.maxProfitTwoPointers(prices));
            System.out.printf("  DP Track-Min: %d%n%n", solution.maxProfitDP(prices));
        }
    }
}

