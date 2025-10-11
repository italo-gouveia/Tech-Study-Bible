/**
 * Solution 2: Sorting Approach
 * 
 * This approach sorts the array first, then checks if any adjacent elements are equal.
 * Since duplicates would be adjacent after sorting, we only need one pass.
 * 
 * Time Complexity: O(n log n) - Dominated by sorting
 * Space Complexity: O(1) - If sorting in-place, O(log n) for recursion stack
 * 
 * Advantages:
 * - Constant extra space (if sorting in-place)
 * - No need for additional data structures
 * - Good for space-constrained environments
 * 
 * Disadvantages:
 * - Slower than HashSet approach due to sorting
 * - Modifies the input array (if sorting in-place)
 * - Worse time complexity
 */
public class solution2 {
    
    /**
     * Check if array contains any duplicates using sorting
     * 
     * @param nums Input array of integers (will be modified if sorting in-place)
     * @return true if duplicates exist, false otherwise
     */
    public static boolean containsDuplicate(int[] nums) {
        // Early return for edge case
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // Sort the array - duplicates will be adjacent
        java.util.Arrays.sort(nums);
        
        // Check adjacent elements for duplicates
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true; // Duplicate found
            }
        }
        
        // No duplicates found
        return false;
    }
    
    /**
     * Alternative implementation that doesn't modify the original array
     * Creates a copy for sorting, preserving the original input
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) - For the copy of the array
     */
    public static boolean containsDuplicateNonDestructive(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // Create a copy to avoid modifying the original array
        int[] sortedNums = nums.clone();
        java.util.Arrays.sort(sortedNums);
        
        // Check adjacent elements for duplicates
        for (int i = 1; i < sortedNums.length; i++) {
            if (sortedNums[i] == sortedNums[i - 1]) {
                return true;
            }
        }
        
        return false;
    }
}
