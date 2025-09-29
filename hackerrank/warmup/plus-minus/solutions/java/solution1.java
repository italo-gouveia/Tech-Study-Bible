/**
 * Solution 1: Basic Counting Approach
 * 
 * This solution uses simple counters to track positive, negative, and zero elements
 * in a single pass through the array.
 * 
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) - only using a few variables for counting
 */

import java.util.*;

class Solution1 {
    public static void plusMinus(List<Integer> arr) {
        int n = arr.size();
        int positive = 0, negative = 0, zero = 0;
        
        // Count elements in each category
        for (int num : arr) {
            if (num > 0) {
                positive++;
            } else if (num < 0) {
                negative++;
            } else {
                zero++;
            }
        }
        
        // Calculate and print ratios with 6 decimal places
        System.out.printf("%.6f%n", (double) positive / n);
        System.out.printf("%.6f%n", (double) negative / n);
        System.out.printf("%.6f%n", (double) zero / n);
    }
}
