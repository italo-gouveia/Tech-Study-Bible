/**
 * Solution 2: Character Array with Bulk Copy
 * 
 * This approach uses a character array and bulk copy operations
 * for maximum efficiency. It first interleaves characters up to
 * the minimum length, then copies remaining characters in bulk.
 * 
 * Time Complexity: O(m + n) where m and n are lengths of word1 and word2
 * Space Complexity: O(m + n) for the result array
 */

class Solution2 {
    public String mergeAlternately(String word1, String word2) {
        // Get lengths of both strings
        int m = word1.length(), n = word2.length();
        
        // Create result array with exact size needed
        char[] res = new char[m + n];
        
        // Find the minimum length to determine interleaving range
        int min = Math.min(m, n);
        
        // Index for the result array
        int k = 0;

        // Interleave characters up to the minimum length
        // This ensures we alternate between both strings
        for (int i = 0; i < min; i++) {
            res[k++] = word1.charAt(i); // Add character from word1
            res[k++] = word2.charAt(i); // Add character from word2
        }

        // Handle remaining characters from the longer string
        // Use bulk copy for efficiency instead of character-by-character
        if (m > min) {
            // word1 is longer, copy remaining characters
            // getChars(srcBegin, srcEnd, dst, dstBegin)
            word1.getChars(min, m, res, k);
        } else if (n > min) {
            // word2 is longer, copy remaining characters
            word2.getChars(min, n, res, k);
        }

        // Convert character array to String
        return new String(res);
    }
}


