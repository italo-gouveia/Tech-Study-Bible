/**
 * Solution 2: Hash Table with Character Frequency Counting
 * 
 * This solution uses character frequency counting instead of sorting to create unique keys.
 * For each string, we count the frequency of each character and use that as the key.
 * 
 * Time Complexity: O(n * m) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

import java.util.*;

class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Create a character frequency key
            String key = getCharacterFrequencyKey(str);
            
            // Add the original string to the list for this key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        // Return all the grouped anagrams
        return new ArrayList<>(map.values());
    }
    
    /**
     * Create a unique key based on character frequencies
     * Format: "a1b2c3..." where numbers represent frequencies
     */
    private String getCharacterFrequencyKey(String str) {
        int[] count = new int[26]; // Assuming only lowercase letters
        
        // Count frequency of each character
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Build the key string
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                key.append((char)('a' + i)).append(count[i]);
            }
        }
        
        return key.toString();
    }
}
