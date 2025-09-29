/**
 * Solution 1: Hash Table with String Sorting
 * 
 * This solution uses a hash table to group anagrams by their sorted character representation.
 * Strings that are anagrams will have the same sorted character sequence.
 * 
 * Time Complexity: O(n * m * log(m)) where n is the number of strings and m is the average length
 * Space Complexity: O(n * m) - hash table storage for all strings
 */

import java.util.*;

class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Sort the characters in the string to create a unique key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            // Add the original string to the list for this key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        // Return all the grouped anagrams
        return new ArrayList<>(map.values());
    }
}
