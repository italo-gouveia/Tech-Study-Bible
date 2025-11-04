package leetcode.easy.firstuniquechar;

// LeetCode 387 — Two-pass O(n) with O(1) space (lowercase letters)
public class Solution {
    public int firstUniqChar(String s) {
        // 'a'..'z' are contiguous in ASCII. Subtracting 'a' normalizes letters to 0..25:
        // 'a'-'a'=0, 'b'-'a'=1, ..., 'z'-'a'=25. This lets us index a 26-sized array.
        int[] freq = new int[26];
        char[] arr = s.toCharArray();

        // First pass: count frequencies
        for (char c : arr) {
            // Map 'a'..'z' to 0..25 via (c - 'a')
            freq[c - 'a']++;
        }

        // Second pass: first index with count == 1
        for (int i = 0; i < arr.length; i++) {
            // Check the normalized bucket; 1 means unique occurrence
            if (freq[arr[i] - 'a'] == 1) return i;
        }
        return -1;
    }
}


