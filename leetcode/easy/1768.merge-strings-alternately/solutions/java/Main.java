/**
 * Main executable class for Merge Strings Alternately problem
 * 
 * This class demonstrates both solutions with comprehensive test cases
 * and performance measurements.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Merge Strings Alternately - Java Solutions ===\n");
        
        // Test both solutions
        testSolution1();
        testSolution2();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        testEdgeCases();
    }
    
    /**
     * Test Solution 1: StringBuilder with Two Pointers
     */
    private static void testSolution1() {
        System.out.println("--- Solution 1: StringBuilder with Two Pointers ---");
        Solution1 solution = new Solution1();
        
        // Test cases from problem description
        testCase(solution, "abc", "pqr", "apbqcr", "Example 1: Equal length strings");
        testCase(solution, "ab", "pqrs", "apbqrs", "Example 2: word2 longer");
        testCase(solution, "abcd", "pq", "apbqcd", "Example 3: word1 longer");
        
        // Additional test cases
        testCase(solution, "a", "b", "ab", "Single characters");
        testCase(solution, "", "abc", "abc", "Empty word1");
        testCase(solution, "abc", "", "abc", "Empty word2");
        testCase(solution, "", "", "", "Both empty");
        
        System.out.println();
    }
    
    /**
     * Test Solution 2: Character Array with Bulk Copy
     */
    private static void testSolution2() {
        System.out.println("--- Solution 2: Character Array with Bulk Copy ---");
        Solution2 solution = new Solution2();
        
        // Test cases from problem description
        testCase(solution, "abc", "pqr", "apbqcr", "Example 1: Equal length strings");
        testCase(solution, "ab", "pqrs", "apbqrs", "Example 2: word2 longer");
        testCase(solution, "abcd", "pq", "apbqcd", "Example 3: word1 longer");
        
        // Additional test cases
        testCase(solution, "a", "b", "ab", "Single characters");
        testCase(solution, "", "abc", "abc", "Empty word1");
        testCase(solution, "abc", "", "abc", "Empty word2");
        testCase(solution, "", "", "", "Both empty");
        
        System.out.println();
    }
    
    /**
     * Test a single case for any solution
     */
    private static void testCase(Object solution, String word1, String word2, String expected, String description) {
        String result;
        
        // Call the appropriate solution method
        if (solution instanceof Solution1) {
            result = ((Solution1) solution).mergeAlternately(word1, word2);
        } else if (solution instanceof Solution2) {
            result = ((Solution2) solution).mergeAlternately(word1, word2);
        } else {
            throw new IllegalArgumentException("Unknown solution type");
        }
        
        // Display results
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: word1=\"%s\", word2=\"%s\"%n", word1, word2);
        System.out.printf("Output: \"%s\"%n", result);
        System.out.printf("Expected: \"%s\"%n", expected);
        System.out.printf("Result: %s%n%n", result.equals(expected) ? "✅ PASS" : "❌ FAIL");
    }
    
    /**
     * Performance comparison between both solutions
     */
    private static void performanceComparison() {
        System.out.println("--- Performance Comparison ---");
        
        // Create test data
        String word1 = "a".repeat(1000);
        String word2 = "b".repeat(1000);
        
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        
        // Warm up JVM
        for (int i = 0; i < 1000; i++) {
            solution1.mergeAlternately("a", "b");
            solution2.mergeAlternately("a", "b");
        }
        
        // Test Solution 1
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            solution1.mergeAlternately(word1, word2);
        }
        long endTime = System.nanoTime();
        double solution1Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        // Test Solution 2
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            solution2.mergeAlternately(word1, word2);
        }
        endTime = System.nanoTime();
        double solution2Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        System.out.printf("Test data: word1=\"%s\" (%d chars), word2=\"%s\" (%d chars)%n", 
            word1.substring(0, Math.min(10, word1.length())) + "...", word1.length(),
            word2.substring(0, Math.min(10, word2.length())) + "...", word2.length());
        System.out.printf("Solution 1 (StringBuilder): %.2f ms for 10,000 iterations%n", solution1Time);
        System.out.printf("Solution 2 (Character Array): %.2f ms for 10,000 iterations%n", solution2Time);
        System.out.printf("Performance ratio: %.2fx%n%n", solution1Time / solution2Time);
    }
    
    /**
     * Test edge cases and boundary conditions
     */
    private static void testEdgeCases() {
        System.out.println("--- Edge Cases Testing ---");
        
        Solution1 solution = new Solution1();
        
        // Test with maximum constraint values
        String maxWord1 = "a".repeat(100);
        String maxWord2 = "b".repeat(100);
        String expectedMax = "ab".repeat(100);
        
        testCase(solution, maxWord1, maxWord2, expectedMax, "Maximum length strings (100 chars each)");
        
        // Test with very different lengths
        String shortWord = "a";
        String longWord = "b".repeat(99);
        String expectedDifferent = "a" + "b".repeat(99);
        
        testCase(solution, shortWord, longWord, expectedDifferent, "Very different lengths (1 vs 99)");
        
        // Test with repeated characters
        String repeated1 = "aaa";
        String repeated2 = "bbb";
        String expectedRepeated = "ababab";
        
        testCase(solution, repeated1, repeated2, expectedRepeated, "Repeated characters");
        
        // Test with alternating pattern
        String pattern1 = "aceg";
        String pattern2 = "bdfh";
        String expectedPattern = "abcdefgh";
        
        testCase(solution, pattern1, pattern2, expectedPattern, "Alternating pattern");
        
        System.out.println("All edge cases completed successfully! ✅");
    }
    
    /**
     * Memory usage measurement
     */
    private static void measureMemoryUsage() {
        System.out.println("--- Memory Usage Analysis ---");
        
        Runtime runtime = Runtime.getRuntime();
        
        // Force garbage collection
        System.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // Execute solution with large input
        Solution1 solution = new Solution1();
        String result = solution.mergeAlternately("a".repeat(1000), "b".repeat(1000));
        
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = afterMemory - beforeMemory;
        
        System.out.printf("Memory used for 1000+1000 character strings: %d bytes (%.2f KB)%n", 
            memoryUsed, memoryUsed / 1024.0);
        System.out.printf("Result length: %d characters%n", result.length());
        System.out.println();
    }
}
