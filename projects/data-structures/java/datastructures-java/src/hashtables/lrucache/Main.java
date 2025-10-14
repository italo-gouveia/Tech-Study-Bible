package hashtables.lrucache;

/**
 * Main class to demonstrate LRU Cache functionality
 * 
 * This class provides comprehensive examples of LRU Cache operations
 * including basic usage, edge cases, and performance demonstrations.
 * 
 * @author Italo Gouveia
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== LRU Cache Demonstration ===\n");
        
        // Test 1: Basic Operations
        testBasicOperations();
        
        // Test 2: Cache Eviction
        testCacheEviction();
        
        // Test 3: Update Existing Key
        testUpdateExistingKey();
        
        // Test 4: Edge Cases
        testEdgeCases();
        
        // Test 5: Performance Test
        testPerformance();
    }
    
    /**
     * Test basic cache operations: put, get, contains, size
     */
    private static void testBasicOperations() {
        System.out.println("1. Basic Operations Test:");
        System.out.println("-------------------------");
        
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        
        // Add items
        cache.put("Alice", 30);
        cache.put("Bob", 25);
        cache.put("Charlie", 35);
        
        System.out.println("After adding 3 items:");
        System.out.println("Size: " + cache.size());
        System.out.println("Contains 'Alice': " + cache.containsKey("Alice"));
        System.out.println("Alice's age: " + cache.get("Alice"));
        System.out.println("Cache: " + cache);
        
        System.out.println();
    }
    
    /**
     * Test cache eviction when capacity is exceeded
     */
    private static void testCacheEviction() {
        System.out.println("2. Cache Eviction Test:");
        System.out.println("----------------------");
        
        LRUCache<String, String> cache = new LRUCache<>(3);
        
        // Fill cache
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");
        
        System.out.println("Cache full (3/3): " + cache);
        
        // Access 'A' to make it recently used
        cache.get("A");
        System.out.println("After accessing 'A': " + cache);
        
        // Add new item - should evict 'B' (least recently used)
        cache.put("D", "Date");
        System.out.println("After adding 'D': " + cache);
        System.out.println("Contains 'B': " + cache.containsKey("B"));
        System.out.println("Contains 'A': " + cache.containsKey("A"));
        
        System.out.println();
    }
    
    /**
     * Test updating existing key
     */
    private static void testUpdateExistingKey() {
        System.out.println("3. Update Existing Key Test:");
        System.out.println("----------------------------");
        
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        
        cache.put(1, "First");
        cache.put(2, "Second");
        cache.put(3, "Third");
        
        System.out.println("Initial cache: " + cache);
        
        // Update existing key
        cache.put(2, "Second Updated");
        System.out.println("After updating key 2: " + cache);
        System.out.println("Value for key 2: " + cache.get(2));
        
        System.out.println();
    }
    
    /**
     * Test edge cases and error handling
     */
    private static void testEdgeCases() {
        System.out.println("4. Edge Cases Test:");
        System.out.println("------------------");
        
        // Test with capacity 1
        LRUCache<String, Integer> cache = new LRUCache<>(1);
        
        cache.put("Only", 42);
        System.out.println("Capacity 1 cache: " + cache);
        
        cache.put("New", 100); // Should evict "Only"
        System.out.println("After adding new item: " + cache);
        System.out.println("Contains 'Only': " + cache.containsKey("Only"));
        
        // Test empty cache
        System.out.println("\nEmpty cache operations:");
        System.out.println("Size: " + cache.size());
        System.out.println("Is empty: " + cache.isEmpty());
        System.out.println("Get non-existent key: " + cache.get("NonExistent"));
        
        // Test remove
        cache.remove("New");
        System.out.println("After removing all items: " + cache);
        System.out.println("Is empty: " + cache.isEmpty());
        
        System.out.println();
    }
    
    /**
     * Test performance with larger dataset
     */
    private static void testPerformance() {
        System.out.println("5. Performance Test:");
        System.out.println("-------------------");
        
        LRUCache<Integer, String> cache = new LRUCache<>(1000);
        
        long startTime = System.nanoTime();
        
        // Add 1000 items
        for (int i = 0; i < 1000; i++) {
            cache.put(i, "Value" + i);
        }
        
        long putTime = System.nanoTime();
        
        // Access random items
        for (int i = 0; i < 1000; i++) {
            cache.get((int) (Math.random() * 1000));
        }
        
        long getTime = System.nanoTime();
        
        // Add more items to test eviction
        for (int i = 1000; i < 1100; i++) {
            cache.put(i, "Value" + i);
        }
        
        long endTime = System.nanoTime();
        
        System.out.println("Performance Results:");
        System.out.println("Put 1000 items: " + (putTime - startTime) / 1_000_000 + " ms");
        System.out.println("Get 1000 items: " + (getTime - putTime) / 1_000_000 + " ms");
        System.out.println("Add 100 more (with eviction): " + (endTime - getTime) / 1_000_000 + " ms");
        System.out.println("Final cache size: " + cache.size());
        System.out.println("Cache capacity: " + cache.getCapacity());
        
        System.out.println();
    }
    
    /**
     * Demonstrate real-world usage scenario
     */
    private static void demonstrateRealWorldScenario() {
        System.out.println("6. Real-World Scenario (Database Query Cache):");
        System.out.println("----------------------------------------------");
        
        // Simulate caching database query results
        LRUCache<String, String> queryCache = new LRUCache<>(5);
        
        // Simulate database queries
        String[] queries = {
            "SELECT * FROM users WHERE id = 1",
            "SELECT * FROM users WHERE id = 2", 
            "SELECT * FROM users WHERE id = 3",
            "SELECT * FROM users WHERE id = 1", // Repeat query
            "SELECT * FROM users WHERE id = 4",
            "SELECT * FROM users WHERE id = 5",
            "SELECT * FROM users WHERE id = 6", // Should evict query 2
            "SELECT * FROM users WHERE id = 2"  // Cache miss
        };
        
        for (String query : queries) {
            String result = queryCache.get(query);
            
            if (result == null) {
                // Cache miss - simulate database query
                result = "Query result for: " + query;
                queryCache.put(query, result);
                System.out.println("Cache MISS - Querying database: " + query);
            } else {
                // Cache hit
                System.out.println("Cache HIT - Using cached result: " + query);
            }
            
            System.out.println("Cache: " + queryCache);
            System.out.println();
        }
    }
}
