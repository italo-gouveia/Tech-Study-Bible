/**
 * Solution 2: Stream-based Approach
 * 
 * This solution uses Java 8 streams to count elements in each category,
 * providing a more functional programming style.
 * 
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) - streams are processed lazily
 */

import java.util.*;

class Solution2 {
    public static void plusMinus(List<Integer> arr) {
        int n = arr.size();
        
        // Count elements using streams
        long positive = arr.stream().filter(x -> x > 0).count();
        long negative = arr.stream().filter(x -> x < 0).count();
        long zero = arr.stream().filter(x -> x == 0).count();
        
        // Calculate and print ratios with 6 decimal places
        System.out.printf("%.6f%n", (double) positive / n);
        System.out.printf("%.6f%n", (double) negative / n);
        System.out.printf("%.6f%n", (double) zero / n);
    }
}
