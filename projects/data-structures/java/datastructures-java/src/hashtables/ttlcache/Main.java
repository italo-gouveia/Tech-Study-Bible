package hashtables.ttlcache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Main class to demonstrate TTL Cache functionality
 * 
 * This class provides comprehensive examples of TTL Cache operations
 * including basic usage, expiration handling, and real-world scenarios.
 * 
 * @author Italo Gouveia
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== TTL Cache Demonstration ===\n");
        
        // Test 1: Basic Operations with Expiration
        testBasicOperations();
        
        // Test 2: Custom TTL per Item
        testCustomTTL();
        
        // Test 3: Automatic Cleanup
        testAutomaticCleanup();
        
        // Test 4: TTL Extension
        testTTLExtension();
        
        // Test 5: Real-World Scenarios
        testRealWorldScenarios();
        
        // Test 6: Performance Test
        testPerformance();
    }
    
    /**
     * Test basic cache operations with expiration
     */
    private static void testBasicOperations() {
        System.out.println("1. Basic Operations with Expiration Test:");
        System.out.println("----------------------------------------");
        
        // Create cache with 2-second default TTL
        TTLCache<String, String> cache = new TTLCache<>(2000);
        
        // Add items
        cache.put("session1", "user123");
        cache.put("session2", "user456");
        
        System.out.println("After adding 2 items:");
        System.out.println("Size: " + cache.size());
        System.out.println("Contains 'session1': " + cache.containsKey("session1"));
        System.out.println("session1 value: " + cache.get("session1"));
        System.out.println("Remaining TTL for session1: " + cache.getRemainingTTL("session1") + "ms");
        
        // Wait for expiration
        System.out.println("\nWaiting for expiration (3 seconds)...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("After expiration:");
        System.out.println("Contains 'session1': " + cache.containsKey("session1"));
        System.out.println("session1 value: " + cache.get("session1"));
        System.out.println("Valid size: " + cache.validSize());
        
        System.out.println();
    }
    
    /**
     * Test custom TTL per item
     */
    private static void testCustomTTL() {
        System.out.println("2. Custom TTL per Item Test:");
        System.out.println("----------------------------");
        
        TTLCache<String, Integer> cache = new TTLCache<>(10000); // 10s default
        
        // Add items with different TTLs
        cache.put("short", 1, 1000);  // 1 second
        cache.put("medium", 2, 3000); // 3 seconds
        cache.put("long", 3, 5000);   // 5 seconds
        
        System.out.println("Added items with different TTLs:");
        System.out.println("short TTL: " + cache.getRemainingTTL("short") + "ms");
        System.out.println("medium TTL: " + cache.getRemainingTTL("medium") + "ms");
        System.out.println("long TTL: " + cache.getRemainingTTL("long") + "ms");
        
        // Wait and check expiration
        try {
            Thread.sleep(2000); // Wait 2 seconds
            System.out.println("\nAfter 2 seconds:");
            System.out.println("short exists: " + cache.containsKey("short"));
            System.out.println("medium exists: " + cache.containsKey("medium"));
            System.out.println("long exists: " + cache.containsKey("long"));
            
            Thread.sleep(2000); // Wait another 2 seconds
            System.out.println("\nAfter 4 seconds total:");
            System.out.println("short exists: " + cache.containsKey("short"));
            System.out.println("medium exists: " + cache.containsKey("medium"));
            System.out.println("long exists: " + cache.containsKey("long"));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    /**
     * Test automatic cleanup functionality
     */
    private static void testAutomaticCleanup() {
        System.out.println("3. Automatic Cleanup Test:");
        System.out.println("-------------------------");
        
        // Create cache with auto cleanup every 1 second
        TTLCache<String, String> cache = new TTLCache<>(1000, true, 1000);
        
        // Add items that will expire quickly
        cache.put("temp1", "data1");
        cache.put("temp2", "data2");
        cache.put("temp3", "data3");
        
        System.out.println("Initial size: " + cache.size());
        
        // Wait for expiration and cleanup
        try {
            Thread.sleep(1500); // Wait for expiration
            System.out.println("After 1.5 seconds (before cleanup): " + cache.size());
            
            Thread.sleep(1000); // Wait for cleanup cycle
            System.out.println("After cleanup cycle: " + cache.size());
            
            // Check stats
            Map<String, Object> stats = cache.getStats();
            System.out.println("Cache stats: " + stats);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            cache.shutdown();
        }
        
        System.out.println();
    }
    
    /**
     * Test TTL extension functionality
     */
    private static void testTTLExtension() {
        System.out.println("4. TTL Extension Test:");
        System.out.println("---------------------");
        
        TTLCache<String, String> cache = new TTLCache<>(2000); // 2s default
        
        cache.put("extendable", "important_data");
        
        System.out.println("Initial TTL: " + cache.getRemainingTTL("extendable") + "ms");
        
        // Extend TTL
        boolean extended = cache.extendTTL("extendable", 5000); // Add 5 seconds
        System.out.println("TTL extended: " + extended);
        System.out.println("New TTL: " + cache.getRemainingTTL("extendable") + "ms");
        
        // Try to extend non-existent key
        boolean notExtended = cache.extendTTL("nonexistent", 1000);
        System.out.println("Extend non-existent key: " + notExtended);
        
        System.out.println();
    }
    
    /**
     * Test real-world scenarios
     */
    private static void testRealWorldScenarios() {
        System.out.println("5. Real-World Scenarios Test:");
        System.out.println("-----------------------------");
        
        // Scenario 1: Session Management
        System.out.println("Scenario 1: User Session Management");
        TTLCache<String, String> sessionCache = new TTLCache<>(300000); // 5 minutes
        
        sessionCache.put("user123", "session_data_abc");
        sessionCache.put("user456", "session_data_def");
        
        System.out.println("Active sessions: " + sessionCache.validSize());
        System.out.println("User 123 session: " + sessionCache.get("user123"));
        
        // Scenario 2: API Response Caching
        System.out.println("\nScenario 2: API Response Caching");
        TTLCache<String, String> apiCache = new TTLCache<>(60000); // 1 minute
        
        // Simulate API calls
        String apiKey = "weather:london";
        String cachedResponse = apiCache.get(apiKey);
        
        if (cachedResponse == null) {
            System.out.println("Cache MISS - Making API call");
            String apiResponse = "London weather: 15°C, cloudy";
            apiCache.put(apiKey, apiResponse);
            System.out.println("Cached response: " + apiResponse);
        } else {
            System.out.println("Cache HIT - Using cached response: " + cachedResponse);
        }
        
        // Second call should hit cache
        cachedResponse = apiCache.get(apiKey);
        if (cachedResponse != null) {
            System.out.println("Cache HIT - Using cached response: " + cachedResponse);
        }
        
        // Scenario 3: Rate Limiting
        System.out.println("\nScenario 3: Rate Limiting");
        TTLCache<String, Integer> rateLimitCache = new TTLCache<>(60000); // 1 minute
        
        String clientIP = "192.168.1.100";
        
        // Simulate requests
        for (int i = 1; i <= 5; i++) {
            Integer requestCount = rateLimitCache.get(clientIP);
            
            if (requestCount == null) {
                rateLimitCache.put(clientIP, 1);
                System.out.println("Request " + i + ": First request from " + clientIP);
            } else {
                rateLimitCache.put(clientIP, requestCount + 1);
                System.out.println("Request " + i + ": Request count for " + clientIP + " = " + (requestCount + 1));
            }
        }
        
        System.out.println();
    }
    
    /**
     * Test performance with large dataset
     */
    private static void testPerformance() {
        System.out.println("6. Performance Test:");
        System.out.println("-------------------");
        
        TTLCache<Integer, String> cache = new TTLCache<>(5000, true, 1000); // 5s TTL, 1s cleanup
        
        long startTime = System.nanoTime();
        
        // Add many items
        for (int i = 0; i < 10000; i++) {
            cache.put(i, "Value" + i);
        }
        
        long putTime = System.nanoTime();
        
        // Access items
        for (int i = 0; i < 10000; i++) {
            cache.get(i);
        }
        
        long getTime = System.nanoTime();
        
        // Manual cleanup
        cache.cleanup();
        
        long cleanupTime = System.nanoTime();
        
        System.out.println("Performance Results:");
        System.out.println("Put 10000 items: " + (putTime - startTime) / 1_000_000 + " ms");
        System.out.println("Get 10000 items: " + (getTime - putTime) / 1_000_000 + " ms");
        System.out.println("Manual cleanup: " + (cleanupTime - getTime) / 1_000_000 + " ms");
        System.out.println("Final cache size: " + cache.size());
        
        // Wait for expiration and check stats
        try {
            Thread.sleep(6000); // Wait for expiration
            System.out.println("\nAfter expiration:");
            System.out.println("Cache size: " + cache.size());
            System.out.println("Valid size: " + cache.validSize());
            
            Map<String, Object> stats = cache.getStats();
            System.out.println("Final stats: " + stats);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            cache.shutdown();
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrate cache with different TTL strategies
     */
    private static void demonstrateTTLStrategies() {
        System.out.println("7. TTL Strategies Demonstration:");
        System.out.println("--------------------------------");
        
        // Strategy 1: Fixed TTL
        System.out.println("Strategy 1: Fixed TTL (5 minutes)");
        TTLCache<String, String> fixedCache = new TTLCache<>(300000);
        fixedCache.put("fixed_key", "fixed_value");
        System.out.println("Fixed TTL cache: " + fixedCache);
        
        // Strategy 2: Variable TTL per item
        System.out.println("\nStrategy 2: Variable TTL per item");
        TTLCache<String, String> variableCache = new TTLCache<>(300000);
        variableCache.put("short_lived", "data", 5000);   // 5 seconds
        variableCache.put("medium_lived", "data", 30000); // 30 seconds
        variableCache.put("long_lived", "data", 300000);  // 5 minutes
        System.out.println("Variable TTL cache: " + variableCache);
        
        // Strategy 3: No auto cleanup (manual only)
        System.out.println("\nStrategy 3: Manual cleanup only");
        TTLCache<String, String> manualCache = new TTLCache<>(5000, false, 0);
        manualCache.put("manual_key", "manual_value");
        System.out.println("Manual cleanup cache: " + manualCache);
        
        // Cleanup manually
        manualCache.cleanup();
        System.out.println("After manual cleanup: " + manualCache);
    }
}
