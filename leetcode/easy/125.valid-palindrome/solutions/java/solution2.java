/**
 * Solution 2: Two Pointers with Custom Character Check
 * 
 * This solution uses two pointers with a custom alphanumeric check function
 * that avoids using Character.isLetterOrDigit() for potentially better performance.
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - only using two pointers and a few variables
 */

class Solution2 {
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
            if (toLowerCase(s.charAt(left)) != toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Custom alphanumeric check - faster than Character.isLetterOrDigit()
     */
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }
    
    /**
     * Custom lowercase conversion - faster than Character.toLowerCase()
     */
    private char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32); // Convert to lowercase
        }
        return c;
    }
}
