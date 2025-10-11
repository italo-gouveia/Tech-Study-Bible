# Complexity Analysis - Contains Duplicate

## Overview

This document provides detailed time and space complexity analysis for all three approaches to solving the Contains Duplicate problem.

## Approach 1: HashSet/Map-based

### Time Complexity

#### Best Case: O(1)
- **Scenario**: First two elements are duplicates
- **Operations**: 1 insertion + 1 lookup = 2 operations
- **Example**: `[1, 1, 2, 3, 4]` → finds duplicate immediately

#### Average Case: O(n)
- **Scenario**: Duplicate found somewhere in the middle
- **Operations**: Hash operations are O(1) average, n/2 elements processed
- **Example**: `[1, 2, 3, 4, 1, 5, 6]` → finds duplicate at position 4

#### Worst Case: O(n)
- **Scenario**: No duplicates found, must check all elements
- **Operations**: n insertions + n lookups = 2n operations
- **Example**: `[1, 2, 3, 4, 5]` → processes all elements

#### Hash Operations Analysis
- **Insertion**: O(1) average, O(n) worst case (hash collision)
- **Lookup**: O(1) average, O(n) worst case (hash collision)
- **Total**: O(n) average, O(n²) worst case (theoretical)

### Space Complexity

#### Best Case: O(1)
- **Scenario**: First two elements are duplicates
- **Memory**: Only stores 1 element in hash set
- **Example**: `[1, 1]` → hash set contains only {1}

#### Average Case: O(n)
- **Scenario**: All elements are unique
- **Memory**: Stores all n elements in hash set
- **Example**: `[1, 2, 3, 4, 5]` → hash set contains {1, 2, 3, 4, 5}

#### Worst Case: O(n)
- **Scenario**: All elements are unique
- **Memory**: Stores all n elements in hash set
- **Additional overhead**: Hash table structure overhead

### Memory Breakdown
```
Hash Set Memory = n × (element_size + hash_overhead)
                = n × (4 bytes + ~8 bytes)  // for integers
                = n × 12 bytes average
```

## Approach 2: Sorting

### Time Complexity

#### Best Case: O(n log n)
- **Scenario**: Array is already sorted (best case for most sorting algorithms)
- **Operations**: O(n log n) for sorting + O(n) for duplicate check
- **Note**: Still O(n log n) due to sorting dominance

#### Average Case: O(n log n)
- **Scenario**: Random array
- **Operations**: O(n log n) for sorting + O(n) for duplicate check
- **Example**: `[3, 1, 4, 1, 5]` → sort to `[1, 1, 3, 4, 5]` + check adjacent

#### Worst Case: O(n log n)
- **Scenario**: Reverse sorted array (worst case for some sorting algorithms)
- **Operations**: O(n log n) for sorting + O(n) for duplicate check
- **Example**: `[5, 4, 3, 2, 1]` → sort + check

### Sorting Algorithm Analysis

#### Quick Sort (Default in most languages)
- **Average**: O(n log n)
- **Worst**: O(n²) - rare with good pivot selection
- **Space**: O(log n) for recursion stack

#### Merge Sort
- **All cases**: O(n log n)
- **Space**: O(n) for temporary arrays

#### Heap Sort
- **All cases**: O(n log n)
- **Space**: O(1) - in-place sorting

#### Tim Sort (Python, Java)
- **Best**: O(n) - already sorted arrays
- **Average**: O(n log n)
- **Worst**: O(n log n)

### Space Complexity

#### In-place Sorting: O(1)
- **Memory**: No additional arrays created
- **Stack space**: O(log n) for recursion (Quick Sort)
- **Total**: O(log n)

#### Non-in-place Sorting: O(n)
- **Memory**: Temporary arrays for merging/copying
- **Example**: Merge Sort creates temporary arrays

#### Duplicate Check: O(1)
- **Memory**: Only uses loop variables
- **No additional data structures**

## Approach 3: Brute Force

### Time Complexity

#### Best Case: O(1)
- **Scenario**: First two elements are duplicates
- **Operations**: 1 comparison
- **Example**: `[1, 1, 2, 3, 4]` → finds duplicate at (0,1)

#### Average Case: O(n²)
- **Scenario**: Duplicate found somewhere in the middle
- **Operations**: n(n-1)/2 comparisons on average
- **Example**: `[1, 2, 3, 4, 1, 5]` → finds duplicate at (0,4)

#### Worst Case: O(n²)
- **Scenario**: No duplicates found, must check all pairs
- **Operations**: n(n-1)/2 = n²/2 - n/2 comparisons
- **Example**: `[1, 2, 3, 4, 5]` → checks all pairs

### Comparison Count Analysis
```
Total Comparisons = n(n-1)/2
For n = 100: 4,950 comparisons
For n = 1,000: 499,500 comparisons
For n = 10,000: 49,995,000 comparisons
```

### Space Complexity

#### All Cases: O(1)
- **Memory**: Only uses loop variables (i, j)
- **No additional data structures**
- **Constant space regardless of input size**

## Complexity Comparison Table

| Approach | Best Time | Average Time | Worst Time | Space |
|----------|-----------|--------------|------------|-------|
| **HashSet** | O(1) | O(n) | O(n) | O(n) |
| **Sorting** | O(n log n) | O(n log n) | O(n log n) | O(1) or O(log n) |
| **Brute Force** | O(1) | O(n²) | O(n²) | O(1) |

## Detailed Complexity Analysis

### Input Size Impact

#### Small Arrays (n ≤ 100)
- **HashSet**: Fastest, minimal memory overhead
- **Sorting**: Good performance, constant space
- **Brute Force**: Acceptable performance

#### Medium Arrays (100 < n ≤ 10,000)
- **HashSet**: Still fastest, moderate memory usage
- **Sorting**: Good performance, constant space
- **Brute Force**: Performance degradation noticeable

#### Large Arrays (n > 10,000)
- **HashSet**: Best choice, memory usage becomes significant
- **Sorting**: Good alternative, consistent performance
- **Brute Force**: Unacceptable performance

### Memory Usage Patterns

#### HashSet Approach
```
Memory Usage = n × (element_size + hash_overhead)
             = n × (4 bytes + 8 bytes)  // for integers
             = 12n bytes

Examples:
n = 1,000:    12 KB
n = 10,000:   120 KB
n = 100,000:  1.2 MB
```

#### Sorting Approach
```
Memory Usage = O(1) for in-place sorting
             = O(log n) for recursion stack
             = ~20 bytes + log₂(n) × 8 bytes

Examples:
n = 1,000:    ~100 bytes
n = 10,000:   ~120 bytes
n = 100,000:  ~140 bytes
```

#### Brute Force Approach
```
Memory Usage = O(1)
             = 16 bytes (two loop variables)

Examples:
n = 1,000:    16 bytes
n = 10,000:   16 bytes
n = 100,000:  16 bytes
```

## Performance Characteristics

### Time Complexity Growth

#### HashSet: Linear Growth
- **Slope**: ~1 (constant factor)
- **Predictable**: Performance scales linearly with input
- **Early termination**: Can be much faster with duplicates

#### Sorting: Log-Linear Growth
- **Slope**: log n (increasing factor)
- **Consistent**: Always O(n log n) regardless of input
- **No early termination**: Must sort entire array

#### Brute Force: Quadratic Growth
- **Slope**: n (quadratic factor)
- **Unpredictable**: Performance degrades rapidly
- **Early termination**: Can be much faster with duplicates

### Space-Time Trade-offs

#### Time vs Space Trade-off
```
HashSet:    Fast Time    ↔    High Space
Sorting:    Medium Time  ↔    Low Space
Brute:      Slow Time    ↔    Lowest Space
```

#### Optimal Choice Matrix
| Input Size | Memory Available | Best Choice |
|------------|------------------|-------------|
| Small      | Any              | HashSet     |
| Medium     | High             | HashSet     |
| Medium     | Low              | Sorting     |
| Large      | High             | HashSet     |
| Large      | Low              | Sorting     |
| Any        | Critical         | Brute Force |

## Real-world Performance

### Typical Execution Times (Relative)

#### For n = 1,000 elements:
- **HashSet**: 1x (baseline)
- **Sorting**: 3-4x slower
- **Brute Force**: 20-50x slower

#### For n = 10,000 elements:
- **HashSet**: 1x (baseline)
- **Sorting**: 4-6x slower
- **Brute Force**: 200-500x slower

### Memory Usage (Actual)

#### For n = 1,000 integers:
- **HashSet**: ~12 KB
- **Sorting**: ~100 bytes
- **Brute Force**: ~16 bytes

#### For n = 10,000 integers:
- **HashSet**: ~120 KB
- **Sorting**: ~120 bytes
- **Brute Force**: ~16 bytes

## Optimization Opportunities

### HashSet Optimizations
1. **Pre-allocate capacity**: `new HashSet<>(nums.length)`
2. **Use appropriate hash function**: Minimize collisions
3. **Choose right data structure**: HashSet vs LinkedHashSet vs TreeSet

### Sorting Optimizations
1. **In-place sorting**: Avoid copying arrays
2. **Choose sorting algorithm**: Quick Sort vs Merge Sort vs Heap Sort
3. **Early termination**: Stop if duplicate found during sorting (complex)

### Brute Force Optimizations
1. **Early termination**: Return immediately when duplicate found
2. **Skip redundant comparisons**: Use optimized nested loops
3. **Memory access patterns**: Optimize cache usage

## Conclusion

The complexity analysis shows that:

1. **HashSet approach** provides the best time complexity but uses more memory
2. **Sorting approach** offers a good balance with consistent performance
3. **Brute force approach** should only be used for educational purposes

The choice depends on the specific requirements:
- **Performance critical**: Use HashSet
- **Memory constrained**: Use Sorting
- **Learning purposes**: Compare all approaches
