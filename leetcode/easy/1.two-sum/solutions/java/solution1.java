import java.util.*;

/**
 * Solution 1: One-Pass Hash Table (Optimal)
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - hash map stores at most n elements
 * 
 * Approach:
 * - Use HashMap to store seen elements (value → index)
 * - For each element, calculate complement = target - current
 * - Check if complement exists in map
 * - If found, return indices; otherwise, add current to map
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        return new int[]{}; // Should never reach here
    }
}
