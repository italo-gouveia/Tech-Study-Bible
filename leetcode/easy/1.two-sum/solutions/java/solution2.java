import java.util.*;

/**
 * Solution 2: Two-Pass Hash Table
 * 
 * Time Complexity: O(n) - two passes through array
 * Space Complexity: O(n) - hash map stores all elements
 * 
 * Approach:
 * - First pass: build hash map (value → index)
 * - Second pass: check for complement
 * - Ensure complement isn't the same element
 */
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // First pass: build hash map
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        // Second pass: find complement
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        
        return new int[]{}; // Should never reach here
    }
}
