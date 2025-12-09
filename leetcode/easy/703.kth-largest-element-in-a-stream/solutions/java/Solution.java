package leetcode.easy.kthlargestelementinastream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// LeetCode 703 — Kth Largest Element in a Stream
public class Solution {

    // --- Solution 1: Sorting (Naive) ---

    static class KthLargestSorting {
        private List<Integer> arr;
        private int k;

        public KthLargestSorting(int k, int[] nums) {
            this.k = k;
            this.arr = new ArrayList<>();
            for (int num : nums) {
                arr.add(num);
            }
        }

        public int add(int val) {
            arr.add(val);
            Collections.sort(arr);
            return arr.get(arr.size() - k);
        }
    }

    // --- Solution 2: Min-Heap (Optimal) ---

    static class KthLargestHeap {
        private PriorityQueue<Integer> minHeap;
        private int k;

        public KthLargestHeap(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>(); // Min-heap by default

            // Add initial values
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            minHeap.offer(val);

            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest (root of min-heap)
            }

            // Root is the kth largest (smallest of the k largest)
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Solution 1: Sorting ===");
        KthLargestSorting kl1 = new KthLargestSorting(3, new int[]{4, 5, 8, 2});
        System.out.printf("add(3): %d%n", kl1.add(3));   // 4
        System.out.printf("add(5): %d%n", kl1.add(5));   // 5
        System.out.printf("add(10): %d%n", kl1.add(10)); // 5
        System.out.printf("add(9): %d%n", kl1.add(9));   // 8
        System.out.printf("add(4): %d%n", kl1.add(4));   // 8

        System.out.println("\n=== Solution 2: Min-Heap (Optimal) ===");
        KthLargestHeap kl2 = new KthLargestHeap(3, new int[]{4, 5, 8, 2});
        System.out.printf("add(3): %d%n", kl2.add(3));   // 4
        System.out.printf("add(5): %d%n", kl2.add(5));   // 5
        System.out.printf("add(10): %d%n", kl2.add(10)); // 5
        System.out.printf("add(9): %d%n", kl2.add(9));   // 8
        System.out.printf("add(4): %d%n", kl2.add(4));   // 8

        System.out.println("\n=== Example 2: With Duplicates ===");
        KthLargestHeap kl3 = new KthLargestHeap(3, new int[]{1, 2, 3, 3});
        System.out.printf("add(3): %d%n", kl3.add(3)); // 3
        System.out.printf("add(5): %d%n", kl3.add(5)); // 3
        System.out.printf("add(6): %d%n", kl3.add(6)); // 3
        System.out.printf("add(7): %d%n", kl3.add(7)); // 5
        System.out.printf("add(8): %d%n", kl3.add(8)); // 6
    }
}



