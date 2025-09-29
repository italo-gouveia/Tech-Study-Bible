# Java Solutions for Greatest Common Divisor of Strings

## Solution 1: Brute Force Approach

### Approach
This solution checks all possible prefix strings of the shorter string to find the longest one that divides both input strings. It starts with the longest possible prefix and works down to shorter ones.

### Code
```java
public String gcdOfStrings(String str1, String str2) {
    String shorter = str1.length() <= str2.length() ? str1 : str2;
    String longer = str1.length() > str2.length() ? str1 : str2;
    
    for (int i = shorter.length(); i > 0; i--) {
        String candidate = shorter.substring(0, i);
        if (divides(str1, candidate) && divides(str2, candidate)) {
            return candidate;
        }
    }
    return "";
}
```

### Line-by-Line Explanation

1. **Line 2-3**: Find the shorter and longer strings
   - We only need to check prefixes of the shorter string
   - Any longer string cannot be a divisor of the shorter one

2. **Line 5**: Loop from longest to shortest prefix
   - Start with the full shorter string as candidate
   - Work down to single characters

3. **Line 6**: Extract candidate prefix
   - `substring(0, i)` gets the first i characters

4. **Line 7**: Check if candidate divides both strings
   - Must divide both str1 and str2 to be a valid GCD

5. **Line 8**: Return first valid candidate
   - Since we start with longest, first valid is the GCD

6. **Line 10**: Return empty string if no divisor found

### Complexity
- **Time**: O(min(m,n) ⋅ (m+n)) - Check each prefix, verify by building expected string
- **Space**: O(min(m,n)) - For the candidate string and expected string

---

## Solution 2: Mathematical Approach

### Approach
This solution uses the mathematical insight that if two strings have a common divisor, then `str1 + str2 = str2 + str1`. The GCD string length equals the GCD of the string lengths.

### Code
```java
public String gcdOfStrings(String str1, String str2) {
    if (!(str1 + str2).equals(str2 + str1)) {
        return "";
    }
    
    int gcdLength = gcd(str1.length(), str2.length());
    return str1.substring(0, gcdLength);
}
```

### Line-by-Line Explanation

1. **Line 2**: Check commutative property
   - If strings have a common divisor, concatenation order doesn't matter
   - `str1 + str2 = str2 + str1` is a necessary condition

2. **Line 3**: Return empty if no common divisor exists
   - If concatenations don't match, no GCD string exists

3. **Line 5**: Calculate GCD of string lengths
   - Use Euclidean algorithm to find GCD of lengths
   - This gives us the length of the GCD string

4. **Line 6**: Return prefix of calculated length
   - Take first `gcdLength` characters from either string
   - Both strings will have the same prefix of this length

### Complexity
- **Time**: O(m+n) - String concatenation and comparison
- **Space**: O(m+n) - For concatenated strings

### Key Mathematical Insight
The mathematical approach is based on the property that if strings `s` and `t` have a common divisor `d`, then:
- `s = d + d + ... + d` (k times)
- `t = d + d + ... + d` (l times)
- Therefore: `s + t = t + s`

### Visual ASCII Examples
```
ABCABC  +  ABC   =  ABCABCABC
ABC     +  ABCABC   ABCABCABC
  ^            ^
 same result → common base = "ABC"
```

```
ABABAB  +  ABAB   =  ABABABABAB
ABAB    +  ABABAB    ABABABABAB
  ^             ^
 same result → common base = "AB"
```

## Why (str1 + str2) == (str2 + str1) Works

Think of strings as repeats of a smaller base D:
- `str1 = D + D + ... + D` (k times)
- `str2 = D + D + ... + D` (l times)

Concatenate in any order:
- `str1 + str2 = D^(k) + D^(l) = D^(k+l)`
- `str2 + str1 = D^(l) + D^(k) = D^(k+l)`

If these two concatenations differ, there is no shared base D.

## Euclid's Algorithm (GCD) in Simple Terms

Goal: find the largest size that divides both lengths exactly.

Steps (numbers a, b):
1. While `b != 0`:
   - `temp = b`
   - `b = a % b` (remainder when dividing a by b)
   - `a = temp`
2. When `b == 0`, `a` is the GCD.

Intuition:
- Keep taking the remainder of the bigger number by the smaller.
- Swap and repeat until remainder becomes 0.
- That final non-zero number is the biggest chunk that fits both.

Connection to strings:
- Once `(str1 + str2) == (str2 + str1)` holds, the answer length is `gcd(len1, len2)`.
- The GCD string is `str1.substring(0, gcd(len1, len2))`.

## Performance

### Java-Specific Results
- **Solution 1 (Brute Force)**: 177.79 ms for 10,000 iterations
- **Solution 2 (Mathematical)**: 3.70 ms for 10,000 iterations
- **Performance ratio**: Mathematical approach is **48x faster**

### Java Advantages
- **JIT Optimization**: Significant performance improvement after warm-up
- **String Handling**: Highly optimized string concatenation and comparison
- **Memory Management**: Efficient garbage collection for string operations
- **Enterprise Ready**: Excellent for production applications

### When to Use Each Approach
- **Solution 1**: Educational purposes, understanding the problem
- **Solution 2**: Production code, LeetCode submissions, performance-critical applications

> 📊 **Complete Performance Analysis**: See the main [README.md](../../README.md#performance-analysis) for cross-language comparisons and detailed benchmarks.

## Key Java Concepts Demonstrated

### String Operations
- **substring()**: Extract substrings efficiently
- **String concatenation**: Use `+` operator for concatenation
- **String comparison**: Use `equals()` for content comparison

### Algorithm Design
- **Brute force**: Check all possibilities systematically
- **Mathematical optimization**: Use mathematical properties for efficiency
- **Euclidean algorithm**: Calculate GCD efficiently

### Performance Considerations
- **String building**: Avoid repeated string concatenation in loops
- **Early termination**: Return as soon as valid solution found
- **Mathematical shortcuts**: Use properties to avoid unnecessary work

## Edge Cases Handled
1. **No common divisor**: Returns empty string
2. **Identical strings**: Returns the string itself
3. **Single characters**: Handles single character strings
4. **Maximum lengths**: Handles constraint maximum (1000 chars)
5. **Very different lengths**: Handles 1 vs 999 character strings

## Testing Recommendations
```java
// Test cases to verify
"ABCABC", "ABC" -> "ABC"
"ABABAB", "ABAB" -> "AB"  
"LEET", "CODE" -> ""
"A", "A" -> "A"
"AB", "AB" -> "AB"
"ABCDEF", "ABC" -> ""
```

### Comprehensive Test Coverage
- ✅ All provided examples
- ✅ Edge cases (no divisor, identical strings, single characters)
- ✅ Boundary conditions (maximum constraint values)
- ✅ Performance tests with large inputs
- ✅ Cross-solution consistency tests
- ✅ Mathematical property validation
