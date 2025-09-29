# 1768. Merge Strings Alternately

## Problem Description

You are given two strings `word1` and `word2`. Merge the strings by adding letters in alternating order, starting with `word1`. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

## Examples

### Example 1:
```
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
```

### Example 2:
```
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```

### Example 3:
```
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
```

## Constraints

- `1 <= word1.length, word2.length <= 100`
- `word1` and `word2` consist of lowercase English letters.

## Approach

The problem requires merging two strings by alternating characters from each string. The key insights are:

1. **Alternating Pattern**: Take one character from `word1`, then one from `word2`, and repeat
2. **Handle Different Lengths**: When one string is exhausted, append the remaining characters from the longer string
3. **Efficient String Building**: Use appropriate string building techniques for each language

## Solutions

- [Java Solutions](solutions/java/README.md)
- [Go Solutions](solutions/go/README.md)

## Complexity Analysis

- **Time Complexity**: O(m + n) where m and n are the lengths of word1 and word2
- **Space Complexity**: O(m + n) for the result string

## Performance Analysis

### Cross-Language Performance Comparison

| Language | Solution | Approach | 10k Iterations | Relative Speed |
|----------|----------|----------|----------------|----------------|
| **Java** | Solution 1 | StringBuilder | 153.62 ms | 1x (baseline) |
| **Java** | Solution 2 | Character Array | 70.94 ms | **2.17x faster** |
| **Go** | Solution 1 | strings.Builder | 34.04 ms | **4.5x faster** |

### Key Performance Insights

1. **Character array approach is faster** than StringBuilder in Java (2.17x improvement)
2. **Go solution** provides the best absolute performance (34.04ms for 10k iterations)
3. **String building techniques** vary significantly across languages
4. **Memory pre-allocation** is crucial for optimal performance
5. **Bulk operations** (like `getChars()`) outperform character-by-character operations

### Benchmark Results

**Go Benchmarks:**
```
BenchmarkMergeAlternately-8              2642133               424.0 ns/op
BenchmarkMergeAlternatelySmall-8        36507008                33.06 ns/op
BenchmarkMergeAlternatelyLarge-8          319796              3421 ns/op
```

**Java Performance:**
- Solution 1 (StringBuilder): 153.62 ms for 10,000 iterations
- Solution 2 (Character Array): 70.94 ms for 10,000 iterations

### Production Recommendations

- **For LeetCode submissions**: Use Java Solution 2 (Character Array) or Go solution
- **For enterprise applications**: Java provides good performance with familiar syntax
- **For high-performance systems**: Go offers the best performance with clean code
- **Memory efficiency**: Both approaches are memory-efficient with O(m+n) space complexity

> 📊 **Detailed Performance Analysis**: See [PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md) for comprehensive benchmarks and optimization techniques.

## Key Learning Points

1. **String Building Efficiency**: Different languages have different optimal approaches for string concatenation
2. **Two Pointer Technique**: Using two pointers to traverse both strings simultaneously
3. **Edge Case Handling**: Properly handling strings of different lengths
4. **Memory Pre-allocation**: Pre-allocating capacity when the final size is known

## Related Problems

- [Zigzag Iterator](https://leetcode.com/problems/zigzag-iterator/) (Medium)
- [Minimum Additions to Make Valid String](https://leetcode.com/problems/minimum-additions-to-make-valid-string/) (Medium)

## Tags

- Two Pointers
- String
- Easy


