/**
 * Solution 2: Mathematical Approach
 * 
 * This approach uses the mathematical insight that if two strings have a common divisor,
 * then str1 + str2 = str2 + str1. The GCD string length equals the GCD of the string lengths.
 * 
 * Time Complexity: O(m+n) where m and n are lengths of str1 and str2
 * Space Complexity: O(m+n) for string concatenation
 */

class Solution2 {
    public String gcdOfStrings(String str1, String str2) {
        // Concatenation equality check; avoids extra logic and stays branch-light
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int a = str1.length();
        int b = str2.length();
        // Iterative Euclid's algorithm for GCD of lengths
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return str1.substring(0, a);
    }
}
