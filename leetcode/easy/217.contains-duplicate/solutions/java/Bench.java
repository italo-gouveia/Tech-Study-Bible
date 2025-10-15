/**
 * Micro-benchmark class for Contains Duplicate solutions
 * 
 * This class provides detailed performance measurements for all three approaches
 * with various input sizes and scenarios.
 */
public class Bench {
    
    public static void main(String[] args) {
        System.out.println("=== Contains Duplicate - Micro-benchmark ===\n");
        
        // Run all benchmark tests
        benchmarkHashSet();
        benchmarkSorting();
        benchmarkBruteForce();
        
        // Compare approaches
        compareApproaches();
        
        // Memory benchmark
        memoryBenchmark();
    }
    
    /**
     * Benchmark HashSet approach with various input sizes
     */
    private static void benchmarkHashSet() {
        System.out.println("--- HashSet Approach Benchmark ---");
        
        int[] sizes = {100, 500, 1000, 5000, 10000};
        int iterations = 1000;
        
        for (int size : sizes) {
            int[] testArray = generateTestArray(size, true);
            
            // Warm up
            for (int i = 0; i < 100; i++) {
                solution1.containsDuplicate(testArray.clone());
            }
            
            // Benchmark
            long totalTime = 0;
            for (int i = 0; i < iterations; i++) {
                long startTime = System.nanoTime();
                solution1.containsDuplicate(testArray.clone());
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            
            double avgTimeMs = (totalTime / (double) iterations) / 1_000_000.0;
            System.out.printf("Size %d: %.3f ms average (%.2f ns per operation)%n", 
                size, avgTimeMs, totalTime / (double) iterations);
        }
        System.out.println();
    }
    
    /**
     * Benchmark Sorting approach with various input sizes
     */
    private static void benchmarkSorting() {
        System.out.println("--- Sorting Approach Benchmark ---");
        
        int[] sizes = {100, 500, 1000, 5000, 10000};
        int iterations = 1000;
        
        for (int size : sizes) {
            int[] testArray = generateTestArray(size, true);
            
            // Warm up
            for (int i = 0; i < 100; i++) {
                solution2.containsDuplicate(testArray.clone());
            }
            
            // Benchmark
            long totalTime = 0;
            for (int i = 0; i < iterations; i++) {
                long startTime = System.nanoTime();
                solution2.containsDuplicate(testArray.clone());
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            
            double avgTimeMs = (totalTime / (double) iterations) / 1_000_000.0;
            System.out.printf("Size %d: %.3f ms average (%.2f ns per operation)%n", 
                size, avgTimeMs, totalTime / (double) iterations);
        }
        System.out.println();
    }
    
    /**
     * Benchmark Brute Force approach (only for small sizes)
     */
    private static void benchmarkBruteForce() {
        System.out.println("--- Brute Force Approach Benchmark ---");
        
        int[] sizes = {10, 50, 100}; // Only small sizes due to O(n²) complexity
        int iterations = 100;
        
        for (int size : sizes) {
            int[] testArray = generateTestArray(size, true);
            
            // Warm up
            for (int i = 0; i < 10; i++) {
                solution3.containsDuplicate(testArray.clone());
            }
            
            // Benchmark
            long totalTime = 0;
            for (int i = 0; i < iterations; i++) {
                long startTime = System.nanoTime();
                solution3.containsDuplicate(testArray.clone());
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            
            double avgTimeMs = (totalTime / (double) iterations) / 1_000_000.0;
            System.out.printf("Size %d: %.3f ms average (%.2f ns per operation)%n", 
                size, avgTimeMs, totalTime / (double) iterations);
        }
        System.out.println();
    }
    
    /**
     * Compare all approaches side by side
     */
    private static void compareApproaches() {
        System.out.println("--- Approach Comparison ---");
        
        int[] sizes = {100, 500, 1000, 5000};
        int iterations = 1000;
        
        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", 
            "Size", "HashSet (ms)", "Sorting (ms)", "HashSet/Sorting", "Sorting/HashSet");
        System.out.println("-".repeat(80));
        
        for (int size : sizes) {
            int[] testArray = generateTestArray(size, true);
            
            // Benchmark HashSet
            long hashSetTime = benchmarkSingle(solution1::containsDuplicate, testArray, iterations);
            
            // Benchmark Sorting
            long sortingTime = benchmarkSingle(solution2::containsDuplicate, testArray, iterations);
            
            double hashSetMs = hashSetTime / 1_000_000.0;
            double sortingMs = sortingTime / 1_000_000.0;
            double ratio1 = (double) hashSetTime / sortingTime;
            double ratio2 = (double) sortingTime / hashSetTime;
            
            System.out.printf("%-10d %-15.3f %-15.3f %-15.2f %-15.2f%n", 
                size, hashSetMs, sortingMs, ratio1, ratio2);
        }
        
        System.out.println();
        
        // Show why brute force is not recommended
        System.out.println("Brute Force Performance (small sizes only):");
        System.out.printf("%-10s %-15s %-15s%n", "Size", "Brute Force (ms)", "vs HashSet");
        System.out.println("-".repeat(50));
        
        int[] smallSizes = {10, 50, 100};
        for (int size : smallSizes) {
            int[] testArray = generateTestArray(size, true);
            
            long bruteTime = benchmarkSingle(solution3::containsDuplicate, testArray, 100);
            long hashSetTime = benchmarkSingle(solution1::containsDuplicate, testArray, 100);
            
            double bruteMs = bruteTime / 1_000_000.0;
            double ratio = (double) bruteTime / hashSetTime;
            
            System.out.printf("%-10d %-15.3f %-15.2fx slower%n", size, bruteMs, ratio);
        }
        System.out.println();
    }
    
    /**
     * Memory usage benchmark
     */
    private static void memoryBenchmark() {
        System.out.println("--- Memory Usage Benchmark ---");
        
        Runtime runtime = Runtime.getRuntime();
        int testSize = 10000;
        int[] testArray = generateTestArray(testSize, true);
        
        // Force garbage collection
        System.gc();
        long baselineMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // Test HashSet memory usage
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        boolean result1 = solution1.containsDuplicate(testArray.clone());
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long hashSetMemory = afterMemory - beforeMemory;
        
        // Test Sorting memory usage
        System.gc();
        beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        boolean result2 = solution2.containsDuplicate(testArray.clone());
        afterMemory = runtime.totalMemory() - runtime.freeMemory();
        long sortingMemory = afterMemory - beforeMemory;
        
        System.out.printf("Test array size: %d elements%n", testSize);
        System.out.printf("HashSet memory usage: %d bytes (%.2f KB)%n", 
            hashSetMemory, hashSetMemory / 1024.0);
        System.out.printf("Sorting memory usage: %d bytes (%.2f KB)%n", 
            sortingMemory, sortingMemory / 1024.0);
        System.out.printf("Memory ratio (HashSet/Sorting): %.2f%n", 
            (double) hashSetMemory / sortingMemory);
        System.out.printf("Both results match: %s%n", result1 == result2 ? "✅" : "❌");
        System.out.println();
        
        // Show memory per element
        System.out.println("Memory per element analysis:");
        System.out.printf("HashSet: %.2f bytes/element%n", (double) hashSetMemory / testSize);
        System.out.printf("Sorting: %.2f bytes/element%n", (double) sortingMemory / testSize);
        System.out.println();
    }
    
    /**
     * Benchmark a single solution with given parameters
     */
    private static long benchmarkSingle(java.util.function.Function<int[], Boolean> solution, 
                                      int[] testArray, int iterations) {
        // Warm up
        for (int i = 0; i < 10; i++) {
            solution.apply(testArray.clone());
        }
        
        // Benchmark
        long totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            solution.apply(testArray.clone());
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        
        return totalTime / iterations; // Average time in nanoseconds
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
}


