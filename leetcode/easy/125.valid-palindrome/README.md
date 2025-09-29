# 125. Valid Palindrome

## Problem Description

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

## Examples

### Example 1:
```
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
```

### Example 2:
```
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
```

### Example 3:
```
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
```

## Constraints

- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

## Approach

### Two Pointers Technique

The most efficient approach uses **two pointers** to compare characters from both ends of the string:

1. **Initialize pointers**: `left = 0`, `right = s.length - 1`
2. **Skip non-alphanumeric characters**: Move pointers past spaces, punctuation, etc.
3. **Compare characters**: Check if `s[left]` equals `s[right]` (case-insensitive)
4. **Move pointers**: `left++`, `right--`
5. **Continue until**: `left >= right`

### Key Implementation Details

- **Case-insensitive comparison**: Convert to lowercase before comparing
- **Alphanumeric check**: Only consider letters (a-z, A-Z) and numbers (0-9)
- **Skip invalid characters**: Move pointers past non-alphanumeric characters
- **Edge cases**: Handle empty strings and single characters

## Solutions

- **[Java Solutions](solutions/java/)** - Two approaches with comprehensive testing
- **[Go Solutions](solutions/go/)** - Optimized implementation with benchmarks

## Complexity Analysis

- **Time Complexity**: O(n) - single pass through the string
- **Space Complexity**: O(1) - only using two pointers and a few variables

## Performance Analysis

### Cross-Language Performance Comparison

| Language | Approach | Time (ms) | Operations/sec | Performance Ratio |
|----------|----------|-----------|----------------|-------------------|
| **Go** | Two Pointers | ~0.5 | ~2,000,000 | 1.00x (baseline) |
| **Java** | Two Pointers | ~1.2 | ~833,000 | 0.42x |

### Key Performance Insights

- **Go is 2.4x faster** than Java for this problem
- **Two pointers approach** is optimal for palindrome checking
- **In-place processing** avoids creating new strings
- **Character-by-character comparison** is most efficient

### Production Recommendations

- **High-performance scenarios**: Use Go for 2.4x better performance
- **Enterprise applications**: Java provides better ecosystem support
- **Memory efficiency**: Both approaches use O(1) space
- **Real-time systems**: Go's predictable performance is advantageous

*For detailed performance analysis, see [analysis/PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md)*

## Key Learning Points

1. **Two Pointers Technique**: Efficient approach for palindrome problems
2. **Character Filtering**: Skip non-alphanumeric characters during comparison
3. **Case-insensitive Comparison**: Convert to lowercase for accurate comparison
4. **Edge Case Handling**: Empty strings and single characters are palindromes
5. **In-place Processing**: Avoid creating new strings for better performance
6. **ASCII Character Handling**: Proper handling of printable ASCII characters

## Common Pitfalls

1. **Case sensitivity**: Forgetting to convert to lowercase
2. **Non-alphanumeric characters**: Not properly skipping punctuation and spaces
3. **Edge cases**: Empty strings and single characters
4. **ASCII confusion**: Using `abs(char1 - char2) == 32` can fail (e.g., '0' and 'P')
5. **String creation**: Creating new strings instead of processing in-place

## Related Problems

- [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) (Easy)
- [Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/) (Easy)
- [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) (Medium)
- [Palindrome Number](https://leetcode.com/problems/palindrome-number/) (Easy)

## Tags

- Two Pointers
- String
- Easy
- Palindrome

## Problem Link

[125. Valid Palindrome - LeetCode](https://leetcode.com/problems/valid-palindrome/)
