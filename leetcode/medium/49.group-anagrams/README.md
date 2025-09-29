# 49. Group Anagrams

## Problem Description

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Examples

### Example 1:
```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Explanation:**
- "bat" has no anagrams in the input
- "nat" and "tan" are anagrams of each other
- "ate", "eat", and "tea" are anagrams of each other

### Example 2:
```
Input: strs = [""]
Output: [[""]]
```

### Example 3:
```
Input: strs = ["a"]
Output: [["a"]]
```

## Constraints

- `1 <= strs.length <= 10^4`
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lowercase English letters only.

## Approach

### Hash Table with String Sorting

The most efficient approach uses a **hash table** to group anagrams by their sorted character representation:

1. **Sort each string**: Convert string to character array, sort, then convert back to string
2. **Use sorted string as key**: Anagrams will have the same sorted representation
3. **Group by key**: Use hash table to group strings with the same sorted key
4. **Return grouped results**: Convert hash table values to list of lists

### Key Implementation Details

- **Sorting**: Sort characters in each string to create a unique key
- **Hash table**: Use sorted string as key, list of original strings as value
- **Grouping**: Collect all strings that share the same sorted representation
- **Output format**: Return list of lists containing grouped anagrams

## Solutions

- **[Java Solutions](solutions/java/)** - Multiple approaches with comprehensive testing
- **[Go Solutions](solutions/go/)** - Optimized implementation with benchmarks

## Complexity Analysis

- **Time Complexity**: O(n * m * log(m)) where n is the number of strings and m is the average length
- **Space Complexity**: O(n * m) - hash table storage for all strings

## Performance Analysis

### Cross-Language Performance Comparison

| Language | Approach | Time (ms) | Operations/sec | Performance Ratio |
|----------|----------|-----------|----------------|-------------------|
| **Go** | Hash Table + Sorting | ~2.5 | ~400,000 | 1.00x (baseline) |
| **Java** | Hash Table + Sorting | ~8.0 | ~125,000 | 0.31x |

### Key Performance Insights

- **Go is 3.2x faster** than Java for this problem
- **Hash table approach** is optimal for grouping operations
- **String sorting** is the bottleneck for large strings
- **Memory efficiency**: Go uses less memory than Java

### Production Recommendations

- **High-performance scenarios**: Use Go for 3.2x better performance
- **Enterprise applications**: Java provides better ecosystem support
- **Memory-constrained environments**: Go's lower memory footprint is advantageous
- **Large datasets**: Consider character frequency counting for very long strings

*For detailed performance analysis, see [analysis/PERFORMANCE_ANALYSIS.md](analysis/PERFORMANCE_ANALYSIS.md)*

## Key Learning Points

1. **Hash Table Usage**: Efficient grouping of related data
2. **String Sorting**: Creating unique keys for anagram detection
3. **Anagram Detection**: Two strings are anagrams if their sorted characters are equal
4. **Data Grouping**: Using hash tables to group related elements
5. **Time-Space Tradeoff**: Sorting vs character frequency counting
6. **String Manipulation**: Efficient character array operations

## Common Pitfalls

1. **Sorting overhead**: String sorting can be expensive for long strings
2. **Memory usage**: Hash table storage for all strings
3. **Edge cases**: Empty strings and single characters
4. **Key generation**: Ensuring sorted strings are used as keys
5. **Output format**: Returning correct list of lists structure

## Alternative Approaches

### Character Frequency Counting
- **Time Complexity**: O(n * m) - potentially faster for long strings
- **Space Complexity**: O(n * m) - similar to sorting approach
- **Trade-off**: More complex implementation but better for very long strings

### Prime Number Hashing
- **Time Complexity**: O(n * m) - faster than sorting
- **Space Complexity**: O(n * m) - similar to other approaches
- **Trade-off**: Risk of integer overflow for very long strings

## Related Problems

- [Valid Anagram](https://leetcode.com/problems/valid-anagram/) (Easy)
- [Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings/) (Medium)
- [Find Resultant Array After Removing Anagrams](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/) (Easy)
- [Count Anagrams](https://leetcode.com/problems/count-anagrams/) (Hard)

## Tags

- Array
- Hash Table
- String
- Sorting
- Medium

## Problem Link

[49. Group Anagrams - LeetCode](https://leetcode.com/problems/group-anagrams/)

## Additional Resources

- [NeetCode Solution - Group Anagrams](https://neetcode.io/solutions/group-anagrams)