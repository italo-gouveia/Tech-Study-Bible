public class Bench {
    public static void main(String[] args) {
        String test = "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}";
        int iterations = 1_000_000;
        
        // Warmup
        for (int i = 0; i < 10000; i++) {
            solution1.isValid(test);
            solution2.isValid(test);
        }
        
        // Benchmark solution1 (char array)
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            solution1.isValid(test);
        }
        long time1 = System.nanoTime() - start;
        
        // Benchmark solution2 (Deque)
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            solution2.isValid(test);
        }
        long time2 = System.nanoTime() - start;
        
        System.out.printf("Solution1 (char array): %d ns/op%n", time1 / iterations);
        System.out.printf("Solution2 (Deque):      %d ns/op%n", time2 / iterations);
        System.out.printf("Ratio (Deque/Array):    %.2fx%n", (double) time2 / time1);
    }
}



