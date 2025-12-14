package leetcode.easy.laststoneweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// LeetCode 1046 — Last Stone Weight
public class Solution {

    // --- Solution 1: Sorting (Naive) ---

    // Time: O(n² log n), Space: O(n)
    public int lastStoneWeightSorting(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        for (int stone : stones) {
            stoneList.add(stone);
        }

        while (stoneList.size() > 1) {
            Collections.sort(stoneList);
            int n = stoneList.size();
            int first = stoneList.remove(n - 1);
            int second = stoneList.remove(n - 2);
            int diff = first - second;

            if (diff > 0) {
                stoneList.add(diff);
            }
        }

        return stoneList.isEmpty() ? 0 : stoneList.get(0);
    }

    // --- Solution 2: Max-Heap (Optimal) ---

    // Time: O(n log n), Space: O(n)
    public int lastStoneWeightHeap(int[] stones) {
        // Max-heap: use reverse order comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Simulate smashing until ≤ 1 stone remains
        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();
            int diff = first - second;

            if (diff > 0) {
                maxHeap.offer(diff);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] testCases = {
            {2, 7, 4, 1, 8, 1},
            {1},
            {2, 3, 6, 2, 4},
            {1, 3}
        };

        for (int[] stones : testCases) {
            System.out.printf("Stones: %s%n", java.util.Arrays.toString(stones));
            System.out.printf("  Sorting: %d%n", solution.lastStoneWeightSorting(stones));
            System.out.printf("  Max-Heap: %d%n", solution.lastStoneWeightHeap(stones));
            System.out.println();
        }
    }
}

