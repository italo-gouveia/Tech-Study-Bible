# Performance Analysis for Two Sum

## Executive Summary

The **One-Pass Hash Table** approach is optimal for production code, providing O(n) time with single iteration.

## Detailed Analysis

### Time Complexity Comparison

```
Input Size    One-Pass Hash    Two-Pass Hash    Brute Force
--------      ---------------  ---------------  -----------
n=10          O(10)            O(20)            O(100)
n=100         O(100)           O(200)           O(10,000)
n=1,000       O(1,000)         O(2,000)         O(1,000,000)
n=10,000      O(10,000)        O(20,000)        O(100,000,000)
```

### Space Complexity Comparison

| Approach | Space | Explanation |
|----------|-------|-------------|
| One-Pass | O(n) | HashMap with at most n entries |
| Two-Pass | O(n) | HashMap with exactly n entries |
| Brute Force | O(1) | Only constant variables |

## Practical Performance

### Estimated Execution Time (ms)

For n = 1000 elements:
- One-Pass Hash: ~0.1-0.5ms
- Two-Pass Hash: ~0.2-0.8ms
- Brute Force: ~50-200ms

### Memory Usage (KB)

For n = 1000 elements:
- One-Pass Hash: ~24-48 KB
- Two-Pass Hash: ~24-48 KB
- Brute Force: ~0 KB

## Algorithm Efficiency

### One-Pass Hash Table
- **Strengths**:
  - ✅ Fastest single-pass approach
  - ✅ Early termination possible
  - ✅ Minimal iterations
  
- **Weaknesses**:
  - ⚠️ Hash map memory overhead
  - ⚠️ Potential hash collisions

### Two-Pass Hash Table
- **Strengths**:
  - ✅ Clear separation of phases
  - ✅ Easy to debug
  
- **Weaknesses**:
  - ⚠️ Two complete passes
  - ⚠️ Extra iteration overhead

### Brute Force
- **Strengths**:
  - ✅ No extra memory
  - ✅ Very simple implementation
  
- **Weaknesses**:
  - ❌ Quadratic time complexity
  - ❌ Not scalable

## Recommendations

### For Production Code
**Use One-Pass Hash Table**
- Best time complexity
- Acceptable space trade-off
- Modern best practice

### For Educational Purposes
**Study All Three Approaches**
- Understand trade-offs
- Learn when to use each
- Compare implementations

### For Constrained Environments
**Consider constraints**:
- Memory constrained: Brute Force (if n < 100)
- Time constrained: One-Pass Hash Table
- Code simplicity: One-Pass Hash Table
