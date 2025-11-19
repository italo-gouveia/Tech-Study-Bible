package main

import "fmt"

// maxProfitBruteForce tries every (buy, sell) pair.
// Time: O(n^2), Space: O(1)
func maxProfitBruteForce(prices []int) int {
	maxProfit := 0
	for i := 0; i < len(prices); i++ {
		for j := i + 1; j < len(prices); j++ {
			profit := prices[j] - prices[i]
			if profit > maxProfit {
				maxProfit = profit
			}
		}
	}
	return maxProfit
}

// maxProfitTwoPointers scans once while tracking the best buy so far.
// Time: O(n), Space: O(1)
// Optimized: removed redundant comparison, always calculate profit
func maxProfitTwoPointers(prices []int) int {
	if len(prices) < 2 {
		return 0
	}

	buy := 0
	maxProfit := 0

	for sell := 1; sell < len(prices); sell++ {
		profit := prices[sell] - prices[buy]
		if profit > maxProfit {
			maxProfit = profit
		}
		if prices[sell] < prices[buy] {
			buy = sell
		}
	}

	return maxProfit
}

// maxProfitDP mirrors the common DP formulation (track min price so far).
// Time: O(n), Space: O(1)
func maxProfitDP(prices []int) int {
	if len(prices) == 0 {
		return 0
	}

	minBuy := prices[0]
	maxProfit := 0

	for _, price := range prices {
		profit := price - minBuy
		if profit > maxProfit {
			maxProfit = profit
		}
		if price < minBuy {
			minBuy = price
		}
	}

	return maxProfit
}

func main() {
	testCases := [][]int{
		{10, 1, 5, 6, 7, 1},
		{10, 8, 7, 5, 2},
		{7, 1, 5, 3, 6, 4},
		{2},
		{},
	}

	for idx, prices := range testCases {
		fmt.Printf("Case %d: prices=%v\n", idx+1, prices)
		fmt.Printf("  Brute Force: %d\n", maxProfitBruteForce(prices))
		fmt.Printf("  Two Pointers: %d\n", maxProfitTwoPointers(prices))
		fmt.Printf("  DP Track-Min: %d\n", maxProfitDP(prices))
		fmt.Println()
	}
}
