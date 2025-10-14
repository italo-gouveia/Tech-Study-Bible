package hashtables.ttlcache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Map;

/**
 * TTL (Time To Live) Cache Implementation
 * 
 * This implementation provides a cache where items automatically expire
 * after a specified time period. Items are stored with timestamps and
 * automatically removed when they exceed their TTL.
 * 
 * Time Complexity:
 * - get(): O(1)
 * - put(): O(1)
 * - cleanup(): O(n) where n is number of expired items
 * 
 * Space Complexity: O(capacity)
 * 
 * Features:
 * - Automatic expiration based on TTL
 * - Background cleanup thread
 * - Thread-safe operations
 * - Configurable TTL per item or global default
 * - Manual cleanup support
 * 
 * @author Italo Gouveia
 */
public class TTLCache<K, V> {
    
    // Inner class to store value with timestamp
    private static class CacheEntry<V> {
        private final V value;
        private final long expirationTime;
        
        CacheEntry(V value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
        
        V getValue() {
            return value;
        }
        
        long getExpirationTime() {
            return expirationTime;
        }
        
        long getTimeToLive() {
            return Math.max(0, expirationTime - System.currentTimeMillis());
        }
    }
    
    private final Map<K, CacheEntry<V>> cache;
    private final long defaultTTL;
    private final ScheduledExecutorService cleanupExecutor;
    private final boolean autoCleanup;
    private final long cleanupInterval;
    
    /**
     * Constructor with default TTL and automatic cleanup
     * 
     * @param defaultTTL Default TTL in milliseconds
     * @param autoCleanup Whether to enable automatic cleanup
     * @param cleanupInterval Cleanup interval in milliseconds
     */
    public TTLCache(long defaultTTL, boolean autoCleanup, long cleanupInterval) {
        this.cache = new ConcurrentHashMap<>();
        this.defaultTTL = defaultTTL;
        this.autoCleanup = autoCleanup;
        this.cleanupInterval = cleanupInterval;
        
        if (autoCleanup) {
            this.cleanupExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "TTLCache-Cleanup");
                t.setDaemon(true);
                return t;
            });
            
            // Schedule periodic cleanup
            cleanupExecutor.scheduleAtFixedRate(
                this::cleanup,
                cleanupInterval,
                cleanupInterval,
                TimeUnit.MILLISECONDS
            );
        } else {
            this.cleanupExecutor = null;
        }
    }
    
    /**
     * Constructor with default TTL and automatic cleanup every 60 seconds
     * 
     * @param defaultTTL Default TTL in milliseconds
     */
    public TTLCache(long defaultTTL) {
        this(defaultTTL, true, 60000); // 60 seconds default cleanup
    }
    
    /**
     * Constructor with default TTL of 5 minutes and automatic cleanup
     */
    public TTLCache() {
        this(300000); // 5 minutes default TTL
    }
    
    /**
     * Put key-value pair with default TTL
     * 
     * @param key The key to store
     * @param value The value to associate with key
     */
    public void put(K key, V value) {
        put(key, value, defaultTTL);
    }
    
    /**
     * Put key-value pair with custom TTL
     * 
     * @param key The key to store
     * @param value The value to associate with key
     * @param ttl TTL in milliseconds
     */
    public void put(K key, V value, long ttl) {
        long expirationTime = System.currentTimeMillis() + ttl;
        cache.put(key, new CacheEntry<>(value, expirationTime));
    }
    
    /**
     * Get value associated with key
     * Returns null if key doesn't exist or has expired
     * 
     * @param key The key to look up
     * @return Value if key exists and hasn't expired, null otherwise
     */
    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        
        if (entry == null) {
            return null; // Key not found
        }
        
        if (entry.isExpired()) {
            // Remove expired entry
            cache.remove(key);
            return null;
        }
        
        return entry.getValue();
    }
    
    /**
     * Get value with TTL information
     * 
     * @param key The key to look up
     * @return CacheEntry with value and TTL info, or null if not found/expired
     */
    public CacheEntry<V> getWithTTL(K key) {
        CacheEntry<V> entry = cache.get(key);
        
        if (entry == null) {
            return null; // Key not found
        }
        
        if (entry.isExpired()) {
            // Remove expired entry
            cache.remove(key);
            return null;
        }
        
        return entry;
    }
    
    /**
     * Check if key exists and hasn't expired
     * 
     * @param key The key to check
     * @return true if key exists and hasn't expired, false otherwise
     */
    public boolean containsKey(K key) {
        CacheEntry<V> entry = cache.get(key);
        
        if (entry == null) {
            return false;
        }
        
        if (entry.isExpired()) {
            cache.remove(key);
            return false;
        }
        
        return true;
    }
    
    /**
     * Remove key-value pair from cache
     * 
     * @param key The key to remove
     * @return Value if key existed and wasn't expired, null otherwise
     */
    public V remove(K key) {
        CacheEntry<V> entry = cache.remove(key);
        
        if (entry == null || entry.isExpired()) {
            return null;
        }
        
        return entry.getValue();
    }
    
    /**
     * Get current number of items in cache (including expired items)
     * 
     * @return Number of items currently stored
     */
    public int size() {
        return cache.size();
    }
    
    /**
     * Get number of non-expired items in cache
     * 
     * @return Number of valid (non-expired) items
     */
    public int validSize() {
        cleanup(); // Remove expired items first
        return cache.size();
    }
    
    /**
     * Check if cache is empty (no valid items)
     * 
     * @return true if cache is empty or all items are expired, false otherwise
     */
    public boolean isEmpty() {
        cleanup();
        return cache.isEmpty();
    }
    
    /**
     * Clear all items from cache
     */
    public void clear() {
        cache.clear();
    }
    
    /**
     * Get remaining TTL for a key
     * 
     * @param key The key to check
     * @return Remaining TTL in milliseconds, or -1 if key doesn't exist
     */
    public long getRemainingTTL(K key) {
        CacheEntry<V> entry = cache.get(key);
        
        if (entry == null) {
            return -1; // Key not found
        }
        
        if (entry.isExpired()) {
            cache.remove(key);
            return -1; // Expired
        }
        
        return entry.getTimeToLive();
    }
    
    /**
     * Extend TTL for an existing key
     * 
     * @param key The key to extend TTL for
     * @param additionalTTL Additional TTL in milliseconds
     * @return true if key existed and TTL was extended, false otherwise
     */
    public boolean extendTTL(K key, long additionalTTL) {
        CacheEntry<V> entry = cache.get(key);
        
        if (entry == null || entry.isExpired()) {
            return false;
        }
        
        long newExpirationTime = entry.getExpirationTime() + additionalTTL;
        cache.put(key, new CacheEntry<>(entry.getValue(), newExpirationTime));
        return true;
    }
    
    /**
     * Manually clean up expired entries
     * This is automatically called if autoCleanup is enabled
     */
    public void cleanup() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
    
    /**
     * Get cache statistics
     * 
     * @return Map with cache statistics
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new ConcurrentHashMap<>();
        
        int totalSize = cache.size();
        int expiredCount = 0;
        int validCount = 0;
        
        for (CacheEntry<V> entry : cache.values()) {
            if (entry.isExpired()) {
                expiredCount++;
            } else {
                validCount++;
            }
        }
        
        stats.put("totalSize", totalSize);
        stats.put("validSize", validCount);
        stats.put("expiredSize", expiredCount);
        stats.put("defaultTTL", defaultTTL);
        stats.put("autoCleanup", autoCleanup);
        stats.put("cleanupInterval", cleanupInterval);
        
        return stats;
    }
    
    /**
     * Shutdown the cache and cleanup resources
     */
    public void shutdown() {
        if (cleanupExecutor != null && !cleanupExecutor.isShutdown()) {
            cleanupExecutor.shutdown();
            try {
                if (!cleanupExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    cleanupExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                cleanupExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        clear();
    }
    
    /**
     * Get string representation of cache contents
     * Only shows non-expired items
     * 
     * @return String representation of cache
     */
    @Override
    public String toString() {
        cleanup(); // Remove expired items first
        
        StringBuilder sb = new StringBuilder();
        sb.append("TTLCache(defaultTTL=").append(defaultTTL).append("ms, size=").append(size()).append("): {");
        
        boolean first = true;
        for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            
            CacheEntry<V> cacheEntry = entry.getValue();
            long remainingTTL = cacheEntry.getTimeToLive();
            
            sb.append(entry.getKey())
              .append("=")
              .append(cacheEntry.getValue())
              .append("(TTL:")
              .append(remainingTTL)
              .append("ms)");
            
            first = false;
        }
        
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Finalize method to ensure cleanup executor is shutdown
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }
}
