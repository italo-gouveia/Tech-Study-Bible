# TTL Cache Implementation

## Overview

This implementation provides a **Time To Live (TTL) Cache** using Java. TTL Cache is a data structure that automatically expires items after a specified time period, making it ideal for caching temporary data like sessions, API responses, or rate limiting.

## Key Features

- **Automatic Expiration**: Items automatically expire after their TTL
- **O(1) Operations**: Both `get()` and `put()` operations run in constant time
- **Thread-Safe**: Uses `ConcurrentHashMap` for concurrent access
- **Flexible TTL**: Global default TTL or custom TTL per item
- **Background Cleanup**: Optional automatic cleanup of expired items
- **TTL Management**: Extend TTL, check remaining time, manual cleanup
- **Performance Monitoring**: Built-in statistics and monitoring

## Data Structure Design

### Core Components

1. **ConcurrentHashMap<K, CacheEntry<V>>**: Thread-safe storage with O(1) access
2. **CacheEntry<V>**: Stores value with expiration timestamp
3. **ScheduledExecutorService**: Background cleanup thread (optional)
4. **Timestamp Tracking**: System.currentTimeMillis() for expiration logic

### Architecture

```
TTLCache
├── ConcurrentHashMap<K, CacheEntry<V>>
│   ├── Key1 → CacheEntry(value1, expirationTime1)
│   ├── Key2 → CacheEntry(value2, expirationTime2)
│   └── Key3 → CacheEntry(value3, expirationTime3)
└── ScheduledExecutorService (optional)
    └── Background cleanup thread
```

## API Reference

### Constructors

```java
// Default: 5 minutes TTL, auto cleanup every 60 seconds
TTLCache<K, V> cache = new TTLCache<>();

// Custom default TTL
TTLCache<K, V> cache = new TTLCache<>(ttlMilliseconds);

// Full control: TTL, auto cleanup, cleanup interval
TTLCache<K, V> cache = new TTLCache<>(defaultTTL, autoCleanup, cleanupInterval);
```

### Core Methods

#### `void put(K key, V value)`
- **Purpose**: Store key-value pair with default TTL
- **Time Complexity**: O(1)

#### `void put(K key, V value, long ttl)`
- **Purpose**: Store key-value pair with custom TTL
- **Time Complexity**: O(1)
- **Parameters**: `ttl` in milliseconds

#### `V get(K key)`
- **Purpose**: Retrieve value if key exists and hasn't expired
- **Time Complexity**: O(1)
- **Returns**: Value if valid, `null` if expired or not found
- **Side Effect**: Automatically removes expired entries

#### `CacheEntry<V> getWithTTL(K key)`
- **Purpose**: Get value with TTL information
- **Returns**: CacheEntry with value and expiration info

### Utility Methods

#### `boolean containsKey(K key)`
- **Purpose**: Check if key exists and hasn't expired
- **Time Complexity**: O(1)

#### `long getRemainingTTL(K key)`
- **Purpose**: Get remaining TTL for a key
- **Returns**: Remaining milliseconds, or -1 if not found/expired

#### `boolean extendTTL(K key, long additionalTTL)`
- **Purpose**: Extend TTL for existing key
- **Returns**: `true` if key existed and was extended

#### `void cleanup()`
- **Purpose**: Manually remove expired entries
- **Time Complexity**: O(n) where n is number of expired items

#### `Map<String, Object> getStats()`
- **Purpose**: Get cache statistics
- **Returns**: Map with size, TTL, and performance metrics

## Usage Examples

### Basic Usage

```java
// Create cache with 5-minute default TTL
TTLCache<String, String> cache = new TTLCache<>(300000);

// Add items with default TTL
cache.put("session1", "user123");
cache.put("session2", "user456");

// Add item with custom TTL (30 seconds)
cache.put("temp_data", "temporary", 30000);

// Retrieve items
String session = cache.get("session1"); // Returns "user123"

// Check expiration
long remainingTTL = cache.getRemainingTTL("session1");
System.out.println("Session expires in: " + remainingTTL + "ms");
```

### Session Management

```java
TTLCache<String, UserSession> sessionCache = new TTLCache<>(1800000); // 30 minutes

// User login
UserSession session = new UserSession("user123", "admin");
sessionCache.put("session_abc", session);

// Validate session
UserSession activeSession = sessionCache.get("session_abc");
if (activeSession != null) {
    System.out.println("User " + activeSession.getUserId() + " is logged in");
} else {
    System.out.println("Session expired or invalid");
}
```

### API Response Caching

```java
TTLCache<String, String> apiCache = new TTLCache<>(600000); // 10 minutes

public String getWeatherData(String city) {
    String cacheKey = "weather:" + city;
    String cachedResponse = apiCache.get(cacheKey);
    
    if (cachedResponse == null) {
        // Cache miss - call API
        String apiResponse = callWeatherAPI(city);
        apiCache.put(cacheKey, apiResponse);
        return apiResponse;
    } else {
        // Cache hit
        return cachedResponse;
    }
}
```

### Rate Limiting

```java
TTLCache<String, Integer> rateLimitCache = new TTLCache<>(60000); // 1 minute

public boolean isRateLimited(String clientIP, int maxRequests) {
    Integer requestCount = rateLimitCache.get(clientIP);
    
    if (requestCount == null) {
        // First request
        rateLimitCache.put(clientIP, 1);
        return false;
    } else if (requestCount < maxRequests) {
        // Within limit
        rateLimitCache.put(clientIP, requestCount + 1);
        return false;
    } else {
        // Rate limited
        return true;
    }
}
```

## Performance Characteristics

### Time Complexity
- **get()**: O(1) - HashMap lookup + expiration check
- **put()**: O(1) - HashMap insertion
- **cleanup()**: O(n) - where n is number of expired items
- **containsKey()**: O(1) - HashMap contains + expiration check

### Space Complexity
- **Overall**: O(n) where n is number of items
- **Per Item**: Key + Value + Timestamp (minimal overhead)
- **Background Thread**: Minimal memory footprint

### Memory Management
- **Automatic Expiration**: Expired items become eligible for GC
- **Manual Cleanup**: `cleanup()` method for immediate removal
- **Background Cleanup**: Periodic removal of expired items

## Configuration Options

### TTL Strategies

#### 1. Fixed TTL
```java
TTLCache<String, String> cache = new TTLCache<>(300000); // 5 minutes for all items
```

#### 2. Variable TTL per Item
```java
TTLCache<String, String> cache = new TTLCache<>(300000);
cache.put("short", "data", 5000);   // 5 seconds
cache.put("long", "data", 300000);  // 5 minutes
```

#### 3. No Auto Cleanup
```java
TTLCache<String, String> cache = new TTLCache<>(300000, false, 0);
// Manual cleanup only
cache.cleanup();
```

### Cleanup Strategies

#### 1. Automatic Background Cleanup
```java
TTLCache<String, String> cache = new TTLCache<>(300000, true, 60000);
// Cleanup every 60 seconds
```

#### 2. Manual Cleanup
```java
TTLCache<String, String> cache = new TTLCache<>(300000, false, 0);
cache.cleanup(); // Call manually when needed
```

#### 3. Lazy Cleanup
```java
TTLCache<String, String> cache = new TTLCache<>(300000, false, 0);
// Cleanup happens automatically on get() calls
```

## Thread Safety

**Thread-Safe Design:**
- Uses `ConcurrentHashMap` for thread-safe operations
- Background cleanup thread is properly managed
- All public methods are thread-safe
- Automatic shutdown handling

**Concurrent Access Patterns:**
```java
// Multiple threads can safely use the same cache instance
TTLCache<String, String> sharedCache = new TTLCache<>(300000);

// Thread 1
sharedCache.put("key1", "value1");

// Thread 2
String value = sharedCache.get("key1"); // Thread-safe
```

## When to Use

### ✅ Good Use Cases
- **Session Management**: User sessions with automatic expiration
- **API Response Caching**: Cache API responses with appropriate TTL
- **Rate Limiting**: Track request counts with time windows
- **Temporary Data**: Cache data that becomes stale over time
- **Security Tokens**: JWT tokens or API keys with expiration
- **Database Query Results**: Cache query results with freshness requirements

### ❌ Not Suitable For
- **Permanent Storage**: Data that should never expire
- **High-Frequency Expiration**: Items expiring very quickly (milliseconds)
- **Memory-Constrained Environments**: Fixed memory footprint per item
- **Complex Eviction Logic**: Need for sophisticated eviction strategies

## Comparison with Other Cache Types

| Cache Type | Eviction Strategy | Use Case | TTL Support |
|------------|------------------|----------|-------------|
| **TTL** | Time-based expiration | Temporary data | ✅ Built-in |
| **LRU** | Least Recently Used | Temporal locality | ❌ No |
| **LFU** | Least Frequently Used | Frequency patterns | ❌ No |
| **FIFO** | First In, First Out | Simple time-based | ❌ No |

## Real-World Examples

### 1. Web Session Management
```java
TTLCache<String, UserSession> sessionCache = new TTLCache<>(1800000); // 30 min

// User login
sessionCache.put(sessionId, userSession);

// Validate session
UserSession session = sessionCache.get(sessionId);
if (session != null) {
    // User is logged in
} else {
    // Session expired
}
```

### 2. Database Connection Pooling
```java
TTLCache<String, Connection> connectionCache = new TTLCache<>(300000); // 5 min

// Get connection
Connection conn = connectionCache.get(connectionId);
if (conn == null || conn.isClosed()) {
    conn = createNewConnection();
    connectionCache.put(connectionId, conn);
}
```

### 3. Microservice Response Caching
```java
TTLCache<String, ServiceResponse> responseCache = new TTLCache<>(600000); // 10 min

public ServiceResponse callService(String request) {
    String cacheKey = generateCacheKey(request);
    ServiceResponse cached = responseCache.get(cacheKey);
    
    if (cached == null) {
        cached = externalService.call(request);
        responseCache.put(cacheKey, cached);
    }
    
    return cached;
}
```

## Performance Optimization Tips

### 1. Choose Appropriate TTL
```java
// Too short: High cache miss rate
TTLCache<String, String> cache = new TTLCache<>(1000); // 1 second

// Too long: Stale data
TTLCache<String, String> cache = new TTLCache<>(3600000); // 1 hour

// Balanced: Based on data freshness requirements
TTLCache<String, String> cache = new TTLCache<>(300000); // 5 minutes
```

### 2. Optimize Cleanup Frequency
```java
// Too frequent: Unnecessary CPU usage
TTLCache<String, String> cache = new TTLCache<>(300000, true, 1000); // 1 second

// Too infrequent: Memory waste
TTLCache<String, String> cache = new TTLCache<>(300000, true, 300000); // 5 minutes

// Balanced: Based on TTL and memory constraints
TTLCache<String, String> cache = new TTLCache<>(300000, true, 60000); // 1 minute
```

### 3. Monitor Cache Statistics
```java
Map<String, Object> stats = cache.getStats();
System.out.println("Cache hit ratio: " + stats.get("hitRatio"));
System.out.println("Expired items: " + stats.get("expiredSize"));
```

## Files in This Package

- **`TTLCache.java`**: Main implementation class
- **`Main.java`**: Comprehensive demonstration and testing
- **`README.md`**: This documentation file

## Dependencies

- **Java 8+**: Uses modern Java features
- **Standard Library**: Only uses `java.util.concurrent` and `java.util` packages

## Future Enhancements

1. **TTL Callbacks**: Execute callbacks when items expire
2. **Persistent Storage**: Save/load cache state to disk
3. **Clustering Support**: Distributed TTL cache
4. **Metrics Integration**: Prometheus/StatsD metrics
5. **TTL Strategies**: Sliding vs fixed window TTL
6. **Memory-Based Eviction**: Evict by memory usage when TTL expires
