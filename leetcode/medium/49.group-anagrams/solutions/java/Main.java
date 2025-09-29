/**
 * Main executable for Group Anagrams problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("=== Group Anagrams - Java Solutions ===");
        
        // Test Solution 1
        System.out.println("--- Solution 1: Hash Table with String Sorting ---");
        testSolution1();
        
        // Test Solution 2
        System.out.println("\n--- Solution 2: Hash Table with Character Frequency Counting ---");
        testSolution2();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        edgeCasesTest();
    }
    
    /**
     * Test Solution 1: Hash Table with String Sorting
     */
    private static void testSolution1() {
        Solution1 solution = new Solution1();
        
        // Test cases from problem description
        testCase1(solution, new String[]{"eat","tea","tan","ate","nat","bat"}, 
                 "Example 1: Multiple anagram groups");
        testCase1(solution, new String[]{""}, "Example 2: Empty string");
        testCase1(solution, new String[]{"a"}, "Example 3: Single character");
        
        // Additional test cases
        testCase1(solution, new String[]{"abc","bca","cab","xyz","zyx","yxz"}, 
                 "Multiple groups with same size");
        testCase1(solution, new String[]{"listen","silent","enlist","inlets"}, 
                 "Single large anagram group");
        testCase1(solution, new String[]{"a","b","c","d"}, 
                 "No anagrams - all unique");
    }
    
    /**
     * Test Solution 2: Hash Table with Character Frequency Counting
     */
    private static void testSolution2() {
        Solution2 solution = new Solution2();
        
        // Test cases from problem description
        testCase2(solution, new String[]{"eat","tea","tan","ate","nat","bat"}, 
                 "Example 1: Multiple anagram groups");
        testCase2(solution, new String[]{""}, "Example 2: Empty string");
        testCase2(solution, new String[]{"a"}, "Example 3: Single character");
        
        // Additional test cases
        testCase2(solution, new String[]{"abc","bca","cab","xyz","zyx","yxz"}, 
                 "Multiple groups with same size");
        testCase2(solution, new String[]{"listen","silent","enlist","inlets"}, 
                 "Single large anagram group");
        testCase2(solution, new String[]{"a","b","c","d"}, 
                 "No anagrams - all unique");
    }
    
    private static void testCase1(Solution1 solution, String[] input, String description) {
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: %s%n", Arrays.toString(input));
        
        List<List<String>> result = solution.groupAnagrams(input);
        System.out.printf("Output: %s%n", result);
        System.out.printf("Number of groups: %d%n", result.size());
        System.out.println();
    }
    
    private static void testCase2(Solution2 solution, String[] input, String description) {
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: %s%n", Arrays.toString(input));
        
        List<List<String>> result = solution.groupAnagrams(input);
        System.out.printf("Output: %s%n", result);
        System.out.printf("Number of groups: %d%n", result.size());
        System.out.println();
    }
    
    /**
     * Performance comparison between solutions
     */
    private static void performanceComparison() {
        System.out.println("--- Performance Comparison ---");
        
        // Create test data of varying sizes
        String[][] testData = {
            generateTestData(10, 5),    // Small dataset
            generateTestData(100, 10),  // Medium dataset
            generateTestData(1000, 20), // Large dataset
        };
        
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        
        for (int i = 0; i < testData.length; i++) {
            String[] testArray = testData[i];
            System.out.printf("Testing dataset %d: %d strings, avg length %d%n", 
                            i + 1, testArray.length, getAverageLength(testArray));
            
            // Test Solution 1
            long startTime = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                solution1.groupAnagrams(testArray);
            }
            long endTime = System.nanoTime();
            double solution1Time = (endTime - startTime) / 1_000_000.0;
            
            // Test Solution 2
            startTime = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                solution2.groupAnagrams(testArray);
            }
            endTime = System.nanoTime();
            double solution2Time = (endTime - startTime) / 1_000_000.0;
            
            System.out.printf("Solution 1 (Sorting): %.2f ms for 1,000 iterations%n", solution1Time);
            System.out.printf("Solution 2 (Frequency): %.2f ms for 1,000 iterations%n", solution2Time);
            System.out.printf("Performance ratio: %.2fx%n%n", solution1Time / solution2Time);
        }
    }
    
    /**
     * Edge cases testing
     */
    private static void edgeCasesTest() {
        System.out.println("--- Edge Cases Testing ---");
        
        Solution1 solution = new Solution1();
        
        // Test edge cases
        String[][] edgeCases = {
            {""},                                    // Single empty string
            {"a"},                                   // Single character
            {"a", "a"},                             // Duplicate strings
            {"abc", "abc", "abc"},                  // Multiple duplicates
            {"a", "b", "c", "d", "e"},             // All unique
            {"listen", "silent", "enlist", "inlets", "tinsel"}, // Large anagram group
            {"", "a", "ab", "abc"},                 // Mixed lengths including empty
        };
        
        for (int i = 0; i < edgeCases.length; i++) {
            String[] testCase = edgeCases[i];
            List<List<String>> result = solution.groupAnagrams(testCase);
            System.out.printf("Edge case %d: Input: %s -> %d groups%n", 
                            i + 1, Arrays.toString(testCase), result.size());
        }
        
        System.out.println("\nAll edge cases completed successfully! ✅");
    }
    
    /**
     * Generate test data with specified number of strings and average length
     */
    private static String[] generateTestData(int numStrings, int avgLength) {
        String[] result = new String[numStrings];
        Random rand = new Random();
        
        for (int i = 0; i < numStrings; i++) {
            int length = avgLength + rand.nextInt(avgLength / 2); // Vary length slightly
            StringBuilder sb = new StringBuilder();
            
            for (int j = 0; j < length; j++) {
                sb.append((char)('a' + rand.nextInt(26)));
            }
            
            result[i] = sb.toString();
        }
        
        return result;
    }
    
    /**
     * Calculate average length of strings in array
     */
    private static int getAverageLength(String[] strs) {
        if (strs.length == 0) return 0;
        
        int totalLength = 0;
        for (String str : strs) {
            totalLength += str.length();
        }
        
        return totalLength / strs.length;
    }
}
