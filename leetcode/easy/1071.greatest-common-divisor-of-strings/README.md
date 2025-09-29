# 1071. Greatest Common Divisor of Strings

## Problem Description

For two strings `s` and `t`, we say "t divides s" if and only if `s = t + t + t + ... + t + t` (i.e., `t` is concatenated with itself one or more times).

Given two strings `str1` and `str2`, return the largest string `x` such that `x` divides both `str1` and `str2`.

## Examples

### Example 1:
```
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Explanation: "ABC" divides both "ABCABC" and "ABC"
```

### Example 2:
```
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Explanation: "AB" divides both "ABABAB" and "ABAB"
```

### Example 3:
```
Input: str1 = "LEET", str2 = "CODE"
Output: ""
Explanation: No common divisor exists
```

## Constraints

- `1 <= str1.length, str2.length <= 1000`
- `str1` and `str2` consist of English uppercase letters.

## Approach

This problem has two main approaches:

1. **Brute Force**: Check all possible prefix strings of the shorter string
2. **Mathematical**: Use the mathematical property that if strings have a common divisor, then `str1 + str2 = str2 + str1`, and the GCD string length equals the GCD of the string lengths

## Visual ASCII Examples

### Example: ABCABC and ABC
```
str1: ABC ABC
str2:     ABC
gcd:  ABC
```
Reason: Repeating "ABC" builds both strings.

### Example: ABABAB and ABAB
```
str1: AB AB AB
str2:    AB AB
gcd:  AB
```
Reason: Repeating "AB" builds both strings.

### Example: LEET and CODE (no divisor)
```
str1: LEET
str2: CODE
cat1: LEETCODE
cat2: CODELEET
cat1 != cat2 → no common divisor
```

### Why concatenation order matters
```
If str1 + str2 == str2 + str1 → they share the same repeating base
Otherwise → no common divisor exists
```

## Solutions

- [Java Solutions](solutions/java/README.md)
- [Go Solutions](solutions/go/README.md)

## Complexity Analysis

- **Time Complexity**: 
  - Brute Force: O(min(m,n) ⋅ (m+n))
  - Mathematical: O(m+n)
- **Space Complexity**: O(min(m,n))

## Performance Analysis

### Cross-Language Performance Comparison

| Language | Solution | Approach | 10k Iterations | Relative Speed |
|----------|----------|----------|----------------|----------------|
| **Java** | Solution 1 | Brute Force | 177.79 ms | 1x (baseline) |
| **Java** | Solution 2 | Mathematical | 3.70 ms | **48x faster** |
| **Go** | Solution 1 | Mathematical | 11.04 ms | **16x faster** |

### Key Performance Insights

1. **Mathematical approach is significantly faster** than brute force (48x improvement)
2. **Java Solution 2** achieves the best absolute performance (3.70ms for 10k iterations)
3. **Go solution** provides excellent performance with clean, readable code
4. **String concatenation** is highly optimized in both languages
5. **Euclidean algorithm** implementation is efficient across languages

### Benchmark Results

**Go Benchmarks:**
```
BenchmarkGcdOfStrings-8         31254444                32.19 ns/op
BenchmarkGcdOfStringsLarge-8     2094182               547.9 ns/op
```

**Java Performance:**
- Solution 1 (Brute Force): 177.79 ms for 10,000 iterations
- Solution 2 (Mathematical): 3.70 ms for 10,000 iterations

### Production Recommendations

- **For LeetCode submissions**: Use Java Solution 2 (mathematical approach)
- **For enterprise applications**: Java provides JIT optimization benefits
- **For microservices/concurrent systems**: Go offers excellent performance with clean code
- **Avoid brute force** in production (48x slower than mathematical approach)

> 📊 **Detailed Performance Analysis**: See [PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md) for comprehensive benchmarks and optimization techniques.

## Key Learning Points

1. **String Division Concept**: Understanding what it means for one string to "divide" another
2. **Mathematical Insight**: The commutative property of string concatenation indicates divisibility
3. **GCD Application**: Using mathematical GCD to find optimal string length
4. **Prefix Checking**: Efficient ways to verify if a string is a valid divisor

## Related Problems

- [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/) (Easy)
- [String Matching in an Array](https://leetcode.com/problems/string-matching-in-an-array/) (Easy)

## Tags

- String
- Math
- Easy
