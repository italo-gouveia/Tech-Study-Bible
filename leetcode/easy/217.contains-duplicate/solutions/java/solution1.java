/**
 * Solution 1: HashSet Approach (Optimal)
 * 
 * This approach uses a HashSet to track elements we've seen so far.
 * If we encounter an element that's already in the set, we found a duplicate.
 * 
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(n) - HashSet can contain up to n elements
 * 
 * Advantages:
 * - Optimal time complexity for this problem
 * - Simple and intuitive
 * - Early termination when duplicate is found
 * 
 * Disadvantages:
 * - Uses extra space proportional to input size
 * - Not suitable for space-constrained environments
 */
public class solution1 {
    
    /**
     * Check if array contains any duplicates using HashSet
     * 
     * @param nums Input array of integers
     * @return true if duplicates exist, false otherwise
     */
    public static boolean containsDuplicate(int[] nums) {
        // Early return for edge case
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        // HashSet to track seen elements
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        
        // Iterate through each element
        for (int num : nums) {
            // If add() returns false, element already exists (duplicate found)
            if (!seen.add(num)) {
                return true; // Early termination - duplicate found
            }
        }
        
        // No duplicates found
        return false;
    }
    
    /**
     * Alternative implementation using contains() method
     * This is slightly less efficient but more explicit
     */
    public static boolean containsDuplicateAlternative(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true; // Duplicate found
            }
            seen.add(num); // Add to set
        }
        
        return false;
    }
}
