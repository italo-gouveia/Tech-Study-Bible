/**
 * Solution 1: StringBuilder with Two Pointers
 * 
 * This approach uses a StringBuilder for efficient string concatenation
 * and two pointers to traverse both strings simultaneously.
 * 
 * Time Complexity: O(m + n) where m and n are lengths of word1 and word2
 * Space Complexity: O(m + n) for the result string
 */

class Solution1 {
    public String mergeAlternately(String word1, String word2) {
        // Pre-allocate StringBuilder capacity for optimal performance
        // We know the final string will be exactly word1.length() + word2.length()
        StringBuilder builder = new StringBuilder(word1.length() + word2.length());
        
        // Initialize two pointers for both strings
        int i = 0, j = 0;
        
        // Continue until we've processed all characters from both strings
        while (i < word1.length() || j < word2.length()) {
            // Add character from word1 if it hasn't been exhausted
            if (i < word1.length()) {
                builder.append(word1.charAt(i++)); // Post-increment: use i, then increment
            }
            
            // Add character from word2 if it hasn't been exhausted
            if (j < word2.length()) {
                builder.append(word2.charAt(j++)); // Post-increment: use j, then increment
            }
        }
        
        // Convert StringBuilder to String and return
        return builder.toString();
    }
}


