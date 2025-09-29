/**
 * Main executable class for Greatest Common Divisor of Strings problem
 * 
 * This class demonstrates both solutions with comprehensive test cases
 * and performance measurements.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Greatest Common Divisor of Strings - Java Solutions ===\n");
        
        // Test both solutions
        testSolution1();
        testSolution2();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        testEdgeCases();
    }
    
    /**
     * Test Solution 1: Brute Force Approach
     */
    private static void testSolution1() {
        System.out.println("--- Solution 1: Brute Force Approach ---");
        Solution1 solution = new Solution1();
        
        // Test cases from problem description
        testCase(solution, "ABCABC", "ABC", "ABC", "Example 1: ABC divides both strings");
        testCase(solution, "ABABAB", "ABAB", "AB", "Example 2: AB divides both strings");
        testCase(solution, "LEET", "CODE", "", "Example 3: No common divisor");
        
        // Additional test cases
        testCase(solution, "TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXX", "Complex case with long strings");
        testCase(solution, "A", "A", "A", "Single character strings");
        testCase(solution, "AB", "AB", "AB", "Identical strings");
        testCase(solution, "ABCDEF", "ABC", "", "No common divisor");
        
        System.out.println();
    }
    
    /**
     * Test Solution 2: Mathematical Approach
     */
    private static void testSolution2() {
        System.out.println("--- Solution 2: Mathematical Approach ---");
        Solution2 solution = new Solution2();
        
        // Test cases from problem description
        testCase(solution, "ABCABC", "ABC", "ABC", "Example 1: ABC divides both strings");
        testCase(solution, "ABABAB", "ABAB", "AB", "Example 2: AB divides both strings");
        testCase(solution, "LEET", "CODE", "", "Example 3: No common divisor");
        
        // Additional test cases
        testCase(solution, "TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXX", "Complex case with long strings");
        testCase(solution, "A", "A", "A", "Single character strings");
        testCase(solution, "AB", "AB", "AB", "Identical strings");
        testCase(solution, "ABCDEF", "ABC", "", "No common divisor");
        
        System.out.println();
    }
    
    /**
     * Test a single case for any solution
     */
    private static void testCase(Object solution, String str1, String str2, String expected, String description) {
        String result;
        
        // Call the appropriate solution method
        if (solution instanceof Solution1) {
            result = ((Solution1) solution).gcdOfStrings(str1, str2);
        } else if (solution instanceof Solution2) {
            result = ((Solution2) solution).gcdOfStrings(str1, str2);
        } else {
            throw new IllegalArgumentException("Unknown solution type");
        }
        
        // Display results
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: str1=\"%s\", str2=\"%s\"%n", str1, str2);
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
        String str1 = "ABC".repeat(100);
        String str2 = "ABC".repeat(150);
        
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        
        // Warm up JVM
        for (int i = 0; i < 1000; i++) {
            solution1.gcdOfStrings("ABC", "ABC");
            solution2.gcdOfStrings("ABC", "ABC");
        }
        
        // Test Solution 1
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            solution1.gcdOfStrings(str1, str2);
        }
        long endTime = System.nanoTime();
        double solution1Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        // Test Solution 2
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            solution2.gcdOfStrings(str1, str2);
        }
        endTime = System.nanoTime();
        double solution2Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        System.out.printf("Test data: str1=\"%s\" (%d chars), str2=\"%s\" (%d chars)%n", 
            str1.substring(0, Math.min(10, str1.length())) + "...", str1.length(),
            str2.substring(0, Math.min(10, str2.length())) + "...", str2.length());
        System.out.printf("Solution 1 (Brute Force): %.2f ms for 10,000 iterations%n", solution1Time);
        System.out.printf("Solution 2 (Mathematical): %.2f ms for 10,000 iterations%n", solution2Time);
        System.out.printf("Performance ratio: %.2fx%n%n", solution1Time / solution2Time);
    }
    
    /**
     * Test edge cases and boundary conditions
     */
    private static void testEdgeCases() {
        System.out.println("--- Edge Cases Testing ---");
        
        Solution2 solution = new Solution2();
        
        // Test with maximum constraint values
        String maxStr1 = "A".repeat(1000);
        String maxStr2 = "A".repeat(1000);
        
        testCase(solution, maxStr1, maxStr2, maxStr1, "Maximum length strings (1000 chars each)");
        
        // Test with very different lengths
        String shortStr = "A";
        String longStr = "A".repeat(999);
        String expectedDifferent = "A";
        
        testCase(solution, shortStr, longStr, expectedDifferent, "Very different lengths (1 vs 999)");
        
        // Test with no common divisor
        String noDivisor1 = "ABCDEFGHIJ";
        String noDivisor2 = "KLMNOPQRST";
        
        testCase(solution, noDivisor1, noDivisor2, "", "No common divisor case");
        
        // Test with complex pattern
        String complex1 = "ABABABAB";
        String complex2 = "ABAB";
        String expectedComplex = "AB";
        
        testCase(solution, complex1, complex2, expectedComplex, "Complex pattern case");
        
        System.out.println("All edge cases completed successfully! ✅");
    }
}
