/**
 * Main executable for Plus Minus problem
 *
 * This file contains the main function with sample usage and comprehensive testing.
 * Updated to use the correct HackerRank signature: public static void plusMinus(List<Integer> arr)
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("=== Plus Minus - Java Solutions ===");
        
        // Test Solution 1
        System.out.println("--- Solution 1: Basic Counting Approach ---");
        testSolution1();
        
        // Test Solution 2
        System.out.println("\n--- Solution 2: Stream-based Approach ---");
        testSolution2();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        edgeCasesTest();
    }
    
    /**
     * Test Solution 1: Basic Counting Approach
     */
    private static void testSolution1() {
        // Test cases from problem description
        testCase1(Arrays.asList(1, 1, 0, -1, -1), "Example 1: Two positive, two negative, one zero");
        testCase1(Arrays.asList(-4, 3, -9, 0, 4, 1), "Example 2: Three positive, two negative, one zero");
        
        // Additional test cases
        testCase1(Arrays.asList(1, 2, 3, 4, 5), "All positive numbers");
        testCase1(Arrays.asList(-1, -2, -3, -4, -5), "All negative numbers");
        testCase1(Arrays.asList(0, 0, 0, 0, 0), "All zeros");
        testCase1(Arrays.asList(0), "Single zero");
        testCase1(Arrays.asList(1), "Single positive");
        testCase1(Arrays.asList(-1), "Single negative");
    }
    
    /**
     * Test Solution 2: Stream-based Approach
     */
    private static void testSolution2() {
        // Test cases from problem description
        testCase2(Arrays.asList(1, 1, 0, -1, -1), "Example 1: Two positive, two negative, one zero");
        testCase2(Arrays.asList(-4, 3, -9, 0, 4, 1), "Example 2: Three positive, two negative, one zero");
        
        // Additional test cases
        testCase2(Arrays.asList(1, 2, 3, 4, 5), "All positive numbers");
        testCase2(Arrays.asList(-1, -2, -3, -4, -5), "All negative numbers");
        testCase2(Arrays.asList(0, 0, 0, 0, 0), "All zeros");
        testCase2(Arrays.asList(0), "Single zero");
        testCase2(Arrays.asList(1), "Single positive");
        testCase2(Arrays.asList(-1), "Single negative");
    }
    
    private static void testCase1(List<Integer> arr, String description) {
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: %s%n", arr);
        System.out.print("Output: ");
        Solution1.plusMinus(arr);
        System.out.println();
    }
    
    private static void testCase2(List<Integer> arr, String description) {
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: %s%n", arr);
        System.out.print("Output: ");
        Solution2.plusMinus(arr);
        System.out.println();
    }
    
    /**
     * Performance comparison between solutions
     */
    private static void performanceComparison() {
        System.out.println("\n--- Performance Comparison ---");
        
        // Create test array
        List<Integer> testArray = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            testArray.add(rand.nextInt(201) - 100); // Random numbers from -100 to 100
        }
        
        // Warm up JVM (suppress output)
        for (int i = 0; i < 1000; i++) {
            // Redirect output to avoid spam
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                public void write(int b) {}
            }));
            Solution1.plusMinus(Arrays.asList(1, -1, 0));
            Solution2.plusMinus(Arrays.asList(1, -1, 0));
            System.setOut(originalOut);
        }
        
        // Test Solution 1 (without printing output)
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // Redirect output to avoid spam
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                public void write(int b) {}
            }));
            Solution1.plusMinus(testArray);
            System.setOut(originalOut);
        }
        long endTime = System.nanoTime();
        double solution1Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        // Test Solution 2 (without printing output)
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // Redirect output to avoid spam
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                public void write(int b) {}
            }));
            Solution2.plusMinus(testArray);
            System.setOut(originalOut);
        }
        endTime = System.nanoTime();
        double solution2Time = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        System.out.printf("Test data: Array of %d random integers (-100 to 100)%n", testArray.size());
        System.out.printf("Solution 1 (Basic Counting): %.2f ms for 10,000 iterations%n", solution1Time);
        System.out.printf("Solution 2 (Stream-based): %.2f ms for 10,000 iterations%n", solution2Time);
        System.out.printf("Performance ratio: %.2fx%n%n", solution1Time / solution2Time);
    }
    
    /**
     * Edge cases testing
     */
    private static void edgeCasesTest() {
        System.out.println("--- Edge Cases Testing ---");
        
        // Test with maximum constraint values
        List<Integer> maxArray = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            maxArray.add(100); // Positive
        }
        for (int i = 0; i < 50; i++) {
            maxArray.add(-100); // Negative
        }
        
        System.out.printf("Test: Maximum constraint values (%d elements)%n", maxArray.size());
        System.out.printf("Input: [first 10 elements: %s...]%n", maxArray.subList(0, 10));
        System.out.print("Output: ");
        Solution1.plusMinus(maxArray);
        System.out.println();
        
        // Test with mixed values
        List<Integer> mixedArray = Arrays.asList(100, -100, 0, 50, -50, 25, -25, 0, 75, -75);
        System.out.printf("Test: Mixed positive, negative, and zero values%n");
        System.out.printf("Input: %s%n", mixedArray);
        System.out.print("Output: ");
        Solution1.plusMinus(mixedArray);
        System.out.println();
        
        // Test with boundary values
        List<Integer> boundaryArray = Arrays.asList(1, -1, 0, 100, -100);
        System.out.printf("Test: Boundary values (1, -1, 0, 100, -100)%n");
        System.out.printf("Input: %s%n", boundaryArray);
        System.out.print("Output: ");
        Solution1.plusMinus(boundaryArray);
        System.out.println();
        
        System.out.println("All edge cases completed successfully! ✅");
    }
}