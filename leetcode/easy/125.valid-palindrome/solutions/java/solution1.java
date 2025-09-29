/**
 * Solution 1: Two Pointers Approach
 * 
 * This solution uses two pointers to compare characters from both ends of the string.
 * It skips non-alphanumeric characters and performs case-insensitive comparison.
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - only using two pointers and a few variables
 */

class Solution1 {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !isAlphanumeric(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !isAlphanumeric(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Check if a character is alphanumeric (letter or digit)
     */
    private boolean isAlphanumeric(char c) {
        return Character.isLetterOrDigit(c);
    }
}
