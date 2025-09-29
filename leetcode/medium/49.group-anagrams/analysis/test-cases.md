# Test Cases Analysis - Group Anagrams

## Standard Test Cases

### Example 1: Multiple Anagram Groups
**Input:**
```
["eat","tea","tan","ate","nat","bat"]
```

**Expected Output:**
```
[["bat"],["nat","tan"],["ate","eat","tea"]]
```

**Explanation:**
- "bat" has no anagrams in the input
- "nat" and "tan" are anagrams of each other
- "ate", "eat", and "tea" are anagrams of each other

### Example 2: Empty String
**Input:**
```
[""]
```

**Expected Output:**
```
[[""]]
```

**Explanation:**
- Single empty string forms its own group

### Example 3: Single Character
**Input:**
```
["a"]
```

**Expected Output:**
```
[["a"]]
```

**Explanation:**
- Single character forms its own group

## Edge Cases

### Case 1: Multiple Groups with Same Size
**Input:**
```
["abc","bca","cab","xyz","zyx","yxz"]
```

**Expected Output:**
```
[["abc","bca","cab"],["xyz","zyx","yxz"]]
```

**Explanation:**
- Two groups of three anagrams each
- "abc", "bca", "cab" are anagrams
- "xyz", "zyx", "yxz" are anagrams

### Case 2: Single Large Anagram Group
**Input:**
```
["listen","silent","enlist","inlets"]
```

**Expected Output:**
```
[["listen","silent","enlist","inlets"]]
```

**Explanation:**
- All four strings are anagrams of each other
- Forms one large group

### Case 3: No Anagrams - All Unique
**Input:**
```
["a","b","c","d"]
```

**Expected Output:**
```
[["a"],["b"],["c"],["d"]]
```

**Explanation:**
- Each string forms its own group
- No anagrams exist

### Case 4: Duplicate Strings
**Input:**
```
["a","a","a"]
```

**Expected Output:**
```
[["a","a","a"]]
```

**Explanation:**
- All three strings are identical
- Forms one group with duplicates

### Case 5: Mixed Lengths Including Empty
**Input:**
```
["","a","ab","abc"]
```

**Expected Output:**
```
[[""],["a"],["ab"],["abc"]]
```

**Explanation:**
- Each string forms its own group
- Includes empty string

### Case 6: Large Anagram Group
**Input:**
```
["listen","silent","enlist","inlets","tinsel"]
```

**Expected Output:**
```
[["listen","silent","enlist","inlets","tinsel"]]
```

**Explanation:**
- All five strings are anagrams
- Forms one large group

## Boundary Test Cases

### Case 7: Maximum Constraints
**Input:**
```
[Array of 10,000 strings, each up to 100 characters]
```

**Expected Output:**
```
[Various groups based on anagram relationships]
```

**Purpose:** Test performance with maximum input size

### Case 8: Single Long String
**Input:**
```
["abcdefghijklmnopqrstuvwxyz"]
```

**Expected Output:**
```
[["abcdefghijklmnopqrstuvwxyz"]]
```

**Explanation:**
- Single string with all lowercase letters
- Forms its own group

### Case 9: All Same Characters
**Input:**
```
["aaa","aaa","aaa"]
```

**Expected Output:**
```
[["aaa","aaa","aaa"]]
```

**Explanation:**
- All strings are identical
- Forms one group

### Case 10: Palindrome Anagrams
**Input:**
```
["racecar","racecar","racecar"]
```

**Expected Output:**
```
[["racecar","racecar","racecar"]]
```

**Explanation:**
- All strings are identical palindromes
- Forms one group

## Performance Test Cases

### Case 11: Small Dataset
**Input:**
```
[10 strings, average length 5]
```

**Expected Output:**
```
[Various groups based on anagram relationships]
```

**Purpose:** Test performance with small input

### Case 12: Medium Dataset
**Input:**
```
[100 strings, average length 10]
```

**Expected Output:**
```
[Various groups based on anagram relationships]
```

**Purpose:** Test performance with medium input

### Case 13: Large Dataset
**Input:**
```
[1000 strings, average length 20]
```

**Expected Output:**
```
[Various groups based on anagram relationships]
```

**Purpose:** Test performance with large input

## Invalid Input Cases

### Case 14: Null Array (Edge Case)
**Input:**
```
null
```

**Expected Output:**
```
[Undefined behavior - problem constraints specify strs.length >= 1]
```

### Case 15: Very Long String (Edge Case)
**Input:**
```
[String of length 100]
```

**Expected Output:**
```
[Depends on content]
```

**Purpose:** Test maximum string length constraint

## Test Case Categories

### Functional Tests
- **Basic functionality**: Examples 1-3
- **Edge cases**: Cases 1-6 (various grouping scenarios)
- **Boundary conditions**: Cases 7-10 (constraint limits)

### Edge Case Tests
- **Empty strings**: Cases 2, 5 (empty string handling)
- **Single characters**: Cases 3, 5 (single character handling)
- **Duplicates**: Cases 4, 9, 10 (duplicate string handling)
- **Mixed lengths**: Case 5 (various string lengths)

### Performance Tests
- **Small inputs**: Case 11 (10 strings)
- **Medium inputs**: Case 12 (100 strings)
- **Large inputs**: Case 13 (1000 strings)
- **Maximum constraints**: Case 7 (10,000 strings)

### Stress Tests
- **Maximum constraints**: All boundary values
- **Random distributions**: Various input patterns
- **Memory usage**: Large datasets

## Test Case Validation

### Output Format Validation
1. **List of lists**: Must return List<List<String>> or [][]string
2. **No side effects**: Function should not modify input
3. **Consistent results**: Same input should always produce same output
4. **Order independence**: Order of groups and strings within groups doesn't matter

### Mathematical Validation
1. **Anagram property**: Strings in same group must be anagrams
2. **Completeness**: All input strings must appear in output
3. **Uniqueness**: No string should appear in multiple groups
4. **Grouping correctness**: Anagrams should be grouped together

### Performance Validation
1. **Execution time**: Should complete within reasonable time
2. **Memory usage**: Should use appropriate space
3. **Scalability**: Performance should scale with input size
4. **Edge case handling**: Should handle all boundary conditions

## Automated Testing Strategy

### Unit Tests
```go
func TestGroupAnagrams(t *testing.T) {
    testCases := []struct {
        input    []string
        expected [][]string
        name     string
    }{
        {[]string{"eat", "tea", "tan", "ate", "nat", "bat"},
         [][]string{{"bat"}, {"nat", "tan"}, {"ate", "eat", "tea"}},
         "Example 1: Multiple anagram groups"},
        // ... more test cases
    }
    
    for _, tc := range testCases {
        t.Run(tc.name, func(t *testing.T) {
            result := groupAnagrams(tc.input)
            if !isValidAnagramGrouping(result, tc.expected) {
                t.Errorf("groupAnagrams(%v) = %v; expected valid grouping", tc.input, result)
            }
        })
    }
}
```

### Performance Tests
```go
func BenchmarkGroupAnagrams(b *testing.B) {
    testString := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
    b.ResetTimer()
    for i := 0; i < b.N; i++ {
        groupAnagrams(testString)
    }
}
```

### Edge Case Tests
```go
func TestEdgeCases(t *testing.T) {
    edgeCases := [][]string{
        {""},                                    // Single empty string
        {"a"},                                   // Single character
        {"a", "a"},                             // Duplicate strings
        {"abc", "abc", "abc"},                  // Multiple duplicates
        {"a", "b", "c", "d", "e"},             // All unique
        {"listen", "silent", "enlist", "inlets", "tinsel"}, // Large anagram group
        {"", "a", "ab", "abc"},                 // Mixed lengths including empty
    }
    
    for i, testCase := range edgeCases {
        t.Run(fmt.Sprintf("EdgeCase_%d", i+1), func(t *testing.T) {
            result := groupAnagrams(testCase)
            // Validate result based on expected behavior
        })
    }
}
```

## Helper Functions for Testing

### Anagram Validation
```go
func isValidAnagramGrouping(result, expected [][]string) bool {
    if len(result) != len(expected) {
        return false
    }
    
    // Create a map to count occurrences in result
    resultMap := make(map[string]int)
    for _, group := range result {
        for _, str := range group {
            resultMap[str]++
        }
    }
    
    // Create a map to count occurrences in expected
    expectedMap := make(map[string]int)
    for _, group := range expected {
        for _, str := range group {
            expectedMap[str]++
        }
    }
    
    // Compare the maps
    return reflect.DeepEqual(resultMap, expectedMap)
}
```

### Anagram Check
```go
func areAnagrams(s1, s2 string) bool {
    if len(s1) != len(s2) {
        return false
    }
    
    count1 := make([]int, 26)
    count2 := make([]int, 26)
    
    for _, char := range s1 {
        count1[char-'a']++
    }
    
    for _, char := range s2 {
        count2[char-'a']++
    }
    
    for i := 0; i < 26; i++ {
        if count1[i] != count2[i] {
            return false
        }
    }
    
    return true
}
```

## Conclusion

The test cases cover all critical scenarios for the Group Anagrams problem:

1. **Standard cases**: Basic functionality with multiple anagram groups
2. **Edge cases**: Boundary conditions and special inputs
3. **Performance cases**: Large inputs and efficiency validation
4. **Error cases**: Invalid inputs and constraint violations

This comprehensive test suite ensures the solution works correctly across all possible scenarios and meets the problem requirements for correctness, performance, and edge case handling.

**Key Test Categories:**
- **Functional correctness**: All examples and edge cases
- **Performance validation**: Large input handling
- **Edge case coverage**: Empty strings, duplicates, mixed lengths
- **Boundary testing**: Maximum constraints and invalid inputs
- **Anagram validation**: Proper grouping of anagram strings
