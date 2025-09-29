/**
 * Solution 1: Brute Force Approach
 * 
 * This approach checks all possible prefix strings of the shorter string
 * to find the longest one that divides both input strings.
 * 
 * Time Complexity: O(min(m,n) ⋅ (m+n)) where m and n are lengths of str1 and str2
 * Space Complexity: O(min(m,n)) for the result string
 */

class Solution1 {
    public String gcdOfStrings(String str1, String str2) {
        // Find the shorter string to use as base for checking prefixes
        String shorter = str1.length() <= str2.length() ? str1 : str2;
        String longer = str1.length() > str2.length() ? str1 : str2;
        
        // Check all possible prefix strings starting from the longest
        for (int i = shorter.length(); i > 0; i--) {
            String candidate = shorter.substring(0, i);
            
            // Check if this candidate divides both strings
            if (divides(str1, candidate) && divides(str2, candidate)) {
                return candidate;
            }
        }
        
        // No common divisor found
        return "";
    }
    
    /**
     * Check if the given divisor string divides the target string
     * @param target The string to be divided
     * @param divisor The potential divisor string
     * @return true if divisor divides target, false otherwise
     */
    private boolean divides(String target, String divisor) {
        // Length must be divisible
        if (target.length() % divisor.length() != 0) {
            return false;
        }
        
        // Check if target is made up of repeated divisor strings
        int repetitions = target.length() / divisor.length();
        StringBuilder expected = new StringBuilder();
        
        for (int i = 0; i < repetitions; i++) {
            expected.append(divisor);
        }
        
        return expected.toString().equals(target);
    }
}
