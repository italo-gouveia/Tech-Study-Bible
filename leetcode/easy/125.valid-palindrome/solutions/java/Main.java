/**
 * Main executable for Valid Palindrome problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("=== Valid Palindrome - Java Solutions ===");
        
        // Test Solution 1
        System.out.println("--- Solution 1: Two Pointers with Character.isLetterOrDigit() ---");
        testSolution1();
        
        // Test Solution 2
        System.out.println("\n--- Solution 2: Two Pointers with Custom Character Check ---");
        testSolution2();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        edgeCasesTest();
    }
    
    /**
     * Test Solution 1: Two Pointers with Character.isLetterOrDigit()
     */
    private static void testSolution1() {
        // Test cases from problem description
        testCase1("A man, a plan, a canal: Panama", true, "Example 1: Complex palindrome with punctuation");
        testCase1("race a car", false, "Example 2: Not a palindrome");
        testCase1(" ", true, "Example 3: Empty string after cleaning");
        
        // Additional test cases
        testCase1("racecar", true, "Simple palindrome");
        testCase1("hello", false, "Not a palindrome");
        testCase1("a", true, "Single character");
        testCase1("", true, "Empty string");
        testCase1("A", true, "Single uppercase letter");
        testCase1("0P", false, "Edge case: 0P (0 and P)");
        testCase1("Madam, I'm Adam", true, "Complex palindrome with punctuation");
        testCase1("No 'x' in Nixon", true, "Complex palindrome with quotes");
    }
    
    /**
     * Test Solution 2: Two Pointers with Custom Character Check
     */
    private static void testSolution2() {
        // Test cases from problem description
        testCase2("A man, a plan, a canal: Panama", true, "Example 1: Complex palindrome with punctuation");
        testCase2("race a car", false, "Example 2: Not a palindrome");
        testCase2(" ", true, "Example 3: Empty string after cleaning");
        
        // Additional test cases
        testCase2("racecar", true, "Simple palindrome");
        testCase2("hello", false, "Not a palindrome");
        testCase2("a", true, "Single character");
        testCase2("", true, "Empty string");
        testCase2("A", true, "Single uppercase letter");
        testCase2("0P", false, "Edge case: 0P (0 and P)");
        testCase2("Madam, I'm Adam", true, "Complex palindrome with punctuation");
        testCase2("No 'x' in Nixon", true, "Complex palindrome with quotes");
    }
    
    private static void testCase1(String input, boolean expected, String description) {
        Solution1 solution = new Solution1();
        boolean result = solution.isPalindrome(input);
        String status = result == expected ? "✅" : "❌";
        System.out.printf("%s Test: %s%n", status, description);
        System.out.printf("Input: \"%s\"%n", input);
        System.out.printf("Expected: %s, Got: %s%n", expected, result);
        System.out.println();
    }
    
    private static void testCase2(String input, boolean expected, String description) {
        Solution2 solution = new Solution2();
        boolean result = solution.isPalindrome(input);
        String status = result == expected ? "✅" : "❌";
        System.out.printf("%s Test: %s%n", status, description);
        System.out.printf("Input: \"%s\"%n", input);
        System.out.printf("Expected: %s, Got: %s%n", expected, result);
        System.out.println();
    }
    
    /**
     * Performance comparison between solutions
     */
    private static void performanceComparison() {
        System.out.println("--- Performance Comparison ---");
        
        // Create test strings of varying lengths
        String[] testStrings = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "Madam, I'm Adam",
            "No 'x' in Nixon",
            generateLongPalindrome(1000),
            generateLongPalindrome(5000)
        };
        
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        
        for (String testString : testStrings) {
            System.out.printf("Testing string of length %d:%n", testString.length());
            
            // Test Solution 1
            long startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                solution1.isPalindrome(testString);
            }
            long endTime = System.nanoTime();
            double solution1Time = (endTime - startTime) / 1_000_000.0;
            
            // Test Solution 2
            startTime = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                solution2.isPalindrome(testString);
            }
            endTime = System.nanoTime();
            double solution2Time = (endTime - startTime) / 1_000_000.0;
            
            System.out.printf("Solution 1: %.2f ms for 10,000 iterations%n", solution1Time);
            System.out.printf("Solution 2: %.2f ms for 10,000 iterations%n", solution2Time);
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
        String[] edgeCases = {
            "",           // Empty string
            "a",          // Single character
            "aa",         // Two same characters
            "ab",         // Two different characters
            "0P",         // Edge case mentioned in discussion
            "1Q",         // Similar edge case
            "2R",         // Similar edge case
            "aA",         // Case difference
            "a1A",        // Mixed alphanumeric
            "!@#$%^&*()", // Only special characters
            "12321",      // Numeric palindrome
            "a1b2c3c2b1a" // Mixed alphanumeric palindrome
        };
        
        for (String testCase : edgeCases) {
            boolean result = solution.isPalindrome(testCase);
            System.out.printf("Input: \"%s\" -> %s%n", testCase, result);
        }
        
        System.out.println("\nAll edge cases completed successfully! ✅");
    }
    
    /**
     * Generate a long palindrome string for performance testing
     */
    private static String generateLongPalindrome(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        
        // Generate first half
        for (int i = 0; i < length / 2; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            sb.append(c);
        }
        
        // Add middle character if odd length
        if (length % 2 == 1) {
            sb.append((char) (rand.nextInt(26) + 'a'));
        }
        
        // Mirror the first half
        for (int i = length / 2 - 1; i >= 0; i--) {
            sb.append(sb.charAt(i));
        }
        
        return sb.toString();
    }
}
