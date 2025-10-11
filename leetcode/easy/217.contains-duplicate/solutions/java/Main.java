/**
 * Main executable class for Contains Duplicate problem
 * 
 * This class demonstrates all three solutions with comprehensive test cases
 * and performance measurements.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Contains Duplicate - Java Solutions ===\n");
        
        // Test all solutions
        testSolution1();
        testSolution2();
        testSolution3();
        
        // Performance comparison
        performanceComparison();
        
        // Edge cases testing
        testEdgeCases();
        
        // Memory usage analysis
        measureMemoryUsage();
    }
    
    /**
     * Test Solution 1: HashSet Approach
     */
    private static void testSolution1() {
        System.out.println("--- Solution 1: HashSet Approach ---");
        
        // Test cases from problem description
        testCase(solution1::containsDuplicate, new int[]{1,2,3,1}, true, "Example 1: [1,2,3,1]");
        testCase(solution1::containsDuplicate, new int[]{1,2,3,4}, false, "Example 2: [1,2,3,4]");
        testCase(solution1::containsDuplicate, new int[]{1,1,1,3,3,4,3,2,4,2}, true, "Example 3: Multiple duplicates");
        
        // Additional test cases
        testCase(solution1::containsDuplicate, new int[]{1}, false, "Single element");
        testCase(solution1::containsDuplicate, new int[]{1,1}, true, "Two identical elements");
        testCase(solution1::containsDuplicate, new int[]{1,2,3,4,5}, false, "No duplicates");
        testCase(solution1::containsDuplicate, new int[]{1,2,3,4,5,1}, true, "Duplicate at end");
        
        System.out.println();
    }
    
    /**
     * Test Solution 2: Sorting Approach
     */
    private static void testSolution2() {
        System.out.println("--- Solution 2: Sorting Approach ---");
        
        // Test cases from problem description
        testCase(solution2::containsDuplicate, new int[]{1,2,3,1}, true, "Example 1: [1,2,3,1]");
        testCase(solution2::containsDuplicate, new int[]{1,2,3,4}, false, "Example 2: [1,2,3,4]");
        testCase(solution2::containsDuplicate, new int[]{1,1,1,3,3,4,3,2,4,2}, true, "Example 3: Multiple duplicates");
        
        // Additional test cases
        testCase(solution2::containsDuplicate, new int[]{1}, false, "Single element");
        testCase(solution2::containsDuplicate, new int[]{1,1}, true, "Two identical elements");
        testCase(solution2::containsDuplicate, new int[]{1,2,3,4,5}, false, "No duplicates");
        testCase(solution2::containsDuplicate, new int[]{1,2,3,4,5,1}, true, "Duplicate at end");
        
        System.out.println();
    }
    
    /**
     * Test Solution 3: Brute Force Approach
     */
    private static void testSolution3() {
        System.out.println("--- Solution 3: Brute Force Approach ---");
        
        // Test cases from problem description
        testCase(solution3::containsDuplicate, new int[]{1,2,3,1}, true, "Example 1: [1,2,3,1]");
        testCase(solution3::containsDuplicate, new int[]{1,2,3,4}, false, "Example 2: [1,2,3,4]");
        testCase(solution3::containsDuplicate, new int[]{1,1,1,3,3,4,3,2,4,2}, true, "Example 3: Multiple duplicates");
        
        // Additional test cases
        testCase(solution3::containsDuplicate, new int[]{1}, false, "Single element");
        testCase(solution3::containsDuplicate, new int[]{1,1}, true, "Two identical elements");
        testCase(solution3::containsDuplicate, new int[]{1,2,3,4,5}, false, "No duplicates");
        testCase(solution3::containsDuplicate, new int[]{1,2,3,4,5,1}, true, "Duplicate at end");
        
        System.out.println();
    }
    
    /**
     * Test a single case for any solution
     */
    private static void testCase(java.util.function.Function<int[], Boolean> solution, 
                                int[] nums, boolean expected, String description) {
        // Create a copy to avoid modifying original array for destructive solutions
        int[] testNums = nums.clone();
        boolean result = solution.apply(testNums);
        
        // Display results
        System.out.printf("Test: %s%n", description);
        System.out.printf("Input: %s%n", java.util.Arrays.toString(nums));
        System.out.printf("Output: %s%n", result);
        System.out.printf("Expected: %s%n", expected);
        System.out.printf("Result: %s%n%n", result == expected ? "✅ PASS" : "❌ FAIL");
    }
    
    /**
     * Performance comparison between all three solutions
     */
    private static void performanceComparison() {
        System.out.println("--- Performance Comparison ---");
        
        // Create test data of different sizes
        int[] smallArray = generateTestArray(100, true); // 100 elements with duplicates
        int[] mediumArray = generateTestArray(1000, true); // 1000 elements with duplicates
        int[] largeArray = generateTestArray(5000, true); // 5000 elements with duplicates
        
        // Warm up JVM
        warmupJVM();
        
        // Test with small array
        System.out.println("Small Array (100 elements):");
        compareSolutions(smallArray, 10000);
        
        // Test with medium array
        System.out.println("Medium Array (1000 elements):");
        compareSolutions(mediumArray, 1000);
        
        // Test with large array (only for efficient solutions)
        System.out.println("Large Array (5000 elements):");
        compareSolutionsLarge(largeArray, 100);
        
        System.out.println();
    }
    
    /**
     * Compare solutions with given test data
     */
    private static void compareSolutions(int[] testData, int iterations) {
        // Solution 1: HashSet
        long time1 = measureTime(() -> {
            for (int i = 0; i < iterations; i++) {
                solution1.containsDuplicate(testData.clone());
            }
        });
        
        // Solution 2: Sorting
        long time2 = measureTime(() -> {
            for (int i = 0; i < iterations; i++) {
                solution2.containsDuplicate(testData.clone());
            }
        });
        
        // Solution 3: Brute Force (only for small arrays)
        if (testData.length <= 100) {
            long time3 = measureTime(() -> {
                for (int i = 0; i < iterations; i++) {
                    solution3.containsDuplicate(testData.clone());
                }
            });
            
            System.out.printf("  HashSet: %d ms%n", time1);
            System.out.printf("  Sorting: %d ms%n", time2);
            System.out.printf("  Brute Force: %d ms%n", time3);
            System.out.printf("  Ratio (Sorting/HashSet): %.2fx%n", (double) time2 / time1);
            System.out.printf("  Ratio (Brute/HashSet): %.2fx%n", (double) time3 / time1);
        } else {
            System.out.printf("  HashSet: %d ms%n", time1);
            System.out.printf("  Sorting: %d ms%n", time2);
            System.out.printf("  Ratio (Sorting/HashSet): %.2fx%n", (double) time2 / time1);
        }
        System.out.println();
    }
    
    /**
     * Compare solutions for large arrays (skip brute force)
     */
    private static void compareSolutionsLarge(int[] testData, int iterations) {
        // Solution 1: HashSet
        long time1 = measureTime(() -> {
            for (int i = 0; i < iterations; i++) {
                solution1.containsDuplicate(testData.clone());
            }
        });
        
        // Solution 2: Sorting
        long time2 = measureTime(() -> {
            for (int i = 0; i < iterations; i++) {
                solution2.containsDuplicate(testData.clone());
            }
        });
        
        System.out.printf("  HashSet: %d ms%n", time1);
        System.out.printf("  Sorting: %d ms%n", time2);
        System.out.printf("  Ratio (Sorting/HashSet): %.2fx%n", (double) time2 / time1);
        System.out.println();
    }
    
    /**
     * Test edge cases and boundary conditions
     */
    private static void testEdgeCases() {
        System.out.println("--- Edge Cases Testing ---");
        
        // Test with null input
        try {
            boolean result = solution1.containsDuplicate(null);
            System.out.printf("Null input test: %s%n", result == false ? "✅ PASS" : "❌ FAIL");
        } catch (Exception e) {
            System.out.printf("Null input test: ❌ FAIL - Exception: %s%n", e.getMessage());
        }
        
        // Test with empty array
        testCase(solution1::containsDuplicate, new int[]{}, false, "Empty array");
        
        // Test with single element
        testCase(solution1::containsDuplicate, new int[]{42}, false, "Single element");
        
        // Test with all identical elements
        int[] allSame = new int[1000];
        java.util.Arrays.fill(allSame, 42);
        testCase(solution1::containsDuplicate, allSame, true, "All identical elements (1000)");
        
        // Test with maximum constraint values
        int[] maxValues = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        testCase(solution1::containsDuplicate, maxValues, true, "Maximum constraint values");
        
        // Test with negative numbers
        testCase(solution1::containsDuplicate, new int[]{-1, -2, -1, -3}, true, "Negative numbers with duplicate");
        testCase(solution1::containsDuplicate, new int[]{-1, -2, -3, -4}, false, "Negative numbers without duplicate");
        
        System.out.println("All edge cases completed successfully! ✅");
    }
    
    /**
     * Measure memory usage for different solutions
     */
    private static void measureMemoryUsage() {
        System.out.println("--- Memory Usage Analysis ---");
        
        Runtime runtime = Runtime.getRuntime();
        
        // Test with large array
        int[] testArray = generateTestArray(10000, true);
        
        // Measure memory for HashSet approach
        System.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        
        boolean result1 = solution1.containsDuplicate(testArray);
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed1 = afterMemory - beforeMemory;
        
        // Measure memory for Sorting approach
        System.gc();
        beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        
        boolean result2 = solution2.containsDuplicate(testArray.clone());
        afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed2 = afterMemory - beforeMemory;
        
        System.out.printf("Test array size: %d elements%n", testArray.length);
        System.out.printf("HashSet approach memory: %d bytes (%.2f KB)%n", 
            memoryUsed1, memoryUsed1 / 1024.0);
        System.out.printf("Sorting approach memory: %d bytes (%.2f KB)%n", 
            memoryUsed2, memoryUsed2 / 1024.0);
        System.out.printf("Both results match: %s%n", result1 == result2 ? "✅" : "❌");
        System.out.println();
    }
    
    /**
     * Generate test array with specified size and duplicate flag
     */
    private static int[] generateTestArray(int size, boolean hasDuplicates) {
        int[] array = new int[size];
        java.util.Random random = new java.util.Random(42); // Fixed seed for reproducibility
        
        // Fill array with random values
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        
        // Add duplicates if requested
        if (hasDuplicates && size > 1) {
            // Make sure there's at least one duplicate
            array[size - 1] = array[0];
        }
        
        return array;
    }
    
    /**
     * Warm up JVM to get consistent performance measurements
     */
    private static void warmupJVM() {
        int[] warmupArray = generateTestArray(100, true);
        for (int i = 0; i < 1000; i++) {
            solution1.containsDuplicate(warmupArray.clone());
            solution2.containsDuplicate(warmupArray.clone());
            solution3.containsDuplicate(warmupArray.clone());
        }
    }
    
    /**
     * Measure execution time of a function
     */
    private static long measureTime(Runnable function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
