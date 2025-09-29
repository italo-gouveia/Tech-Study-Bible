# Plus Minus - HackerRank

## Problem Description

Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero. Print the decimal value of each fraction on a new line with 6 places after the decimal.

**Note:** This challenge introduces precision problems. The test cases are scaled to six decimal places, though answers with absolute error of up to 10^-4 are acceptable.

## Examples

### Example 1:
```
Input: arr = [1, 1, 0, -1, -1]
Output: 
0.400000
0.400000
0.200000
```

**Explanation:**
- There are 5 elements: two positive, two negative and one zero
- Positive ratio: 2/5 = 0.400000
- Negative ratio: 2/5 = 0.400000  
- Zero ratio: 1/5 = 0.200000

### Example 2:
```
Input: arr = [-4, 3, -9, 0, 4, 1]
Output:
0.500000
0.333333
0.166667
```

**Explanation:**
- There are 6 elements: 3 positive, 2 negative, and 1 zero
- Positive ratio: 3/6 = 0.500000
- Negative ratio: 2/6 = 0.333333
- Zero ratio: 1/6 = 0.166667

## Constraints

- 0 < n ≤ 100
- -100 ≤ arr[i] ≤ 100

## Approach

The problem requires counting elements in three categories and calculating their ratios:

1. **Count Elements**: Iterate through the array and count positive, negative, and zero elements
2. **Calculate Ratios**: Divide each count by the total number of elements
3. **Format Output**: Print each ratio with exactly 6 decimal places

## Solutions

- [Java Solutions](solutions/java/README.md)
- [Go Solutions](solutions/go/README.md)

## Complexity Analysis

- **Time Complexity**: O(n) where n is the length of the array
- **Space Complexity**: O(1) - only using a few variables for counting

## Performance Analysis

### Cross-Language Performance Comparison

| Language | Approach | Time (ms) | Operations/sec | Performance Ratio |
|----------|----------|-----------|----------------|-------------------|
| **Go** | Basic Counting | 140.07 | 71,393 | 1.00x (baseline) |
| **Java** | Basic Counting | 271.90 | 36,780 | 0.51x |
| **Java** | Stream-based | 293.27 | 34,100 | 0.48x |

### Key Performance Insights

- **Go is 2x faster** than Java for this problem
- **Basic counting approach** outperforms stream-based operations in Java
- **Go benchmarks**: 6.6 ns/op (small arrays), 368 ns/op (large arrays)
- **Memory efficiency**: Go uses significantly less memory than Java

### Production Recommendations

- **High-performance scenarios**: Use Go for 2x better performance
- **Enterprise applications**: Java provides better ecosystem and abstraction
- **Memory-constrained environments**: Go's lower memory footprint is advantageous
- **Simple counting operations**: Avoid stream overhead, use basic loops

*For detailed performance analysis, see [analysis/PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md)*

## Key Learning Points

1. **Array Traversal**: Efficient single-pass iteration through the array
2. **Conditional Counting**: Using if-else statements to categorize elements
3. **Precision Handling**: Understanding floating-point precision requirements
4. **Output Formatting**: Proper formatting of decimal numbers with specific precision
5. **Language Performance**: Go's compiled nature provides significant performance advantages over Java
6. **Algorithm Choice**: Basic counting outperforms functional programming approaches for simple operations

## Related Problems

- [Diagonal Difference](https://www.hackerrank.com/challenges/diagonal-difference/problem) (Easy)
- [Staircase](https://www.hackerrank.com/challenges/staircase/problem) (Easy)
- [Mini-Max Sum](https://www.hackerrank.com/challenges/mini-max-sum/problem) (Easy)

## Tags

- Array
- Mathematics
- Warmup
- Easy

## Problem Link

[Plus Minus - HackerRank](https://www.hackerrank.com/challenges/plus-minus/problem)
