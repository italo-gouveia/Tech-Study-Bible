# Performance Analysis — 20. Valid Parentheses

## Time & Space Complexity
- **Time**: O(n) - single pass through string
- **Space**: O(n) worst-case (all opening brackets)

## Performance Comparison

### Go Benchmarks
```
BenchmarkIsValid-8    1365045    1142 ns/op    64 B/op    1 allocs/op
```

### Java Benchmarks
```
Solution1 (char array): 430 ns/op
Solution2 (Deque):      473 ns/op
Ratio (Deque/Array):    1.10x
```

### Cross-Language Comparison
- **Go**: 1142 ns/op (with GC overhead)
- **Java (char array)**: 430 ns/op (fastest)
- **Java (Deque)**: 473 ns/op (10% slower than array)

**Java char array is ~2.7x faster than Go** due to:
- No GC pressure (primitive array)
- Direct memory access
- JVM optimizations

## Implementation Notes
- Early return for odd-length strings
- Stack simulation with byte slice (pre-allocated capacity)
- Map lookup for bracket matching: O(1) per character
- Constant-time stack operations (append/pop)