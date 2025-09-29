# Test Cases for Merge Strings Alternately

## Provided Examples

### Example 1: Equal Length Strings
```
Input: word1 = "abc", word2 = "pqr"
Expected Output: "apbqcr"
Explanation: 
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
```

### Example 2: Second String Longer
```
Input: word1 = "ab", word2 = "pqrs"
Expected Output: "apbqrs"
Explanation:
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```

### Example 3: First String Longer
```
Input: word1 = "abcd", word2 = "pq"
Expected Output: "apbqcd"
Explanation:
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
```

## Edge Cases

### Case 1: Empty Strings
```
Input: word1 = "", word2 = ""
Expected Output: ""
Explanation: Both strings are empty, result is empty
```

### Case 2: One Empty String
```
Input: word1 = "", word2 = "abc"
Expected Output: "abc"
Explanation: word1 is empty, return word2

Input: word1 = "abc", word2 = ""
Expected Output: "abc"
Explanation: word2 is empty, return word1
```

### Case 3: Single Characters
```
Input: word1 = "a", word2 = "b"
Expected Output: "ab"
Explanation: Simple case with one character each

Input: word1 = "a", word2 = "bc"
Expected Output: "abc"
Explanation: One character from word1, rest from word2
```

### Case 4: Maximum Length (Constraint: 100 characters)
```
Input: word1 = "a" * 100, word2 = "b" * 100
Expected Output: "ab" * 100
Explanation: Both strings at maximum length

Input: word1 = "a" * 100, word2 = "b"
Expected Output: "ab" + "a" * 99
Explanation: One string at maximum length, other minimal
```

## Boundary Cases

### Case 1: Minimum Length (Constraint: 1 character minimum)
```
Input: word1 = "a", word2 = "b"
Expected Output: "ab"
Explanation: Both strings at minimum length
```

### Case 2: One Character vs Multiple
```
Input: word1 = "a", word2 = "bcdef"
Expected Output: "abcdef"
Explanation: One character from word1, all from word2
```

### Case 3: Multiple vs One Character
```
Input: word1 = "abcde", word2 = "f"
Expected Output: "afbcde"
Explanation: First character from word1, then from word2, then rest from word1
```

## Stress Test Cases

### Case 1: Very Different Lengths
```
Input: word1 = "a", word2 = "bcdefghijklmnopqrstuvwxyz"
Expected Output: "abcdefghijklmnopqrstuvwxyz"
Explanation: One character from word1, 25 from word2
```

### Case 2: Alternating Pattern
```
Input: word1 = "aceg", word2 = "bdfh"
Expected Output: "abcdefgh"
Explanation: Perfect alternating pattern
```

### Case 3: Repeated Characters
```
Input: word1 = "aaa", word2 = "bbb"
Expected Output: "ababab"
Explanation: All characters are the same within each string
```

## Invalid Input Cases (Not Applicable)

Since the problem constraints guarantee:
- `1 <= word1.length, word2.length <= 100`
- `word1` and `word2` consist of lowercase English letters

No invalid input cases need to be handled.

## Performance Test Cases

### Case 1: Large Equal Length Strings
```
Input: word1 = "a" * 100, word2 = "b" * 100
Expected Output: "ab" * 100
Time Complexity: O(200)
Space Complexity: O(200)
```

### Case 2: Large Unequal Length Strings
```
Input: word1 = "a" * 50, word2 = "b" * 100
Expected Output: "ab" * 50 + "b" * 50
Time Complexity: O(150)
Space Complexity: O(150)
```

## Test Case Validation

### Manual Verification
For each test case, manually trace through the algorithm:

1. **Initialize pointers**: i = 0, j = 0
2. **Process characters**: Alternate between word1[i] and word2[j]
3. **Handle remaining**: Append remaining characters from longer string
4. **Verify result**: Check that result matches expected output

### Automated Testing
```java
// Java test framework example
@Test
public void testMergeAlternately() {
    assertEquals("apbqcr", mergeAlternately("abc", "pqr"));
    assertEquals("apbqrs", mergeAlternately("ab", "pqrs"));
    assertEquals("apbqcd", mergeAlternately("abcd", "pq"));
    assertEquals("", mergeAlternately("", ""));
    assertEquals("abc", mergeAlternately("", "abc"));
    assertEquals("abc", mergeAlternately("abc", ""));
    assertEquals("ab", mergeAlternately("a", "b"));
}
```

```go
// Go test framework example
func TestMergeAlternately(t *testing.T) {
    tests := []struct {
        word1, word2, expected string
    }{
        {"abc", "pqr", "apbqcr"},
        {"ab", "pqrs", "apbqrs"},
        {"abcd", "pq", "apbqcd"},
        {"", "", ""},
        {"", "abc", "abc"},
        {"abc", "", "abc"},
        {"a", "b", "ab"},
    }
    
    for _, test := range tests {
        result := mergeAlternately(test.word1, test.word2)
        if result != test.expected {
            t.Errorf("mergeAlternately(%q, %q) = %q, expected %q", 
                test.word1, test.word2, result, test.expected)
        }
    }
}
```

## Edge Case Analysis

### Why These Test Cases Matter

1. **Empty Strings**: Test boundary conditions and null handling
2. **Single Characters**: Test minimal input scenarios
3. **Maximum Length**: Test performance with constraint limits
4. **Very Different Lengths**: Test algorithm robustness
5. **Repeated Characters**: Test with non-unique input

### Common Mistakes to Avoid

1. **Off-by-One Errors**: Incorrect loop bounds
2. **Index Out of Bounds**: Not checking string lengths properly
3. **Incorrect Alternating**: Wrong order of character selection
4. **Missing Remaining Characters**: Not appending leftover characters
5. **String Building Issues**: Inefficient string concatenation

## Test Case Coverage

### Functional Coverage
- ✅ Equal length strings
- ✅ Unequal length strings (both cases)
- ✅ Empty strings
- ✅ Single characters
- ✅ Maximum constraint values
- ✅ Minimum constraint values

### Performance Coverage
- ✅ Small inputs (1-10 characters)
- ✅ Medium inputs (10-50 characters)
- ✅ Large inputs (50-100 characters)
- ✅ Maximum inputs (100 characters each)

### Edge Case Coverage
- ✅ Boundary conditions
- ✅ Special patterns
- ✅ Repeated characters
- ✅ Very different lengths

## Conclusion

These test cases provide comprehensive coverage of the problem requirements and edge cases. They ensure that the solution works correctly for all valid inputs and handles boundary conditions properly. The test cases also help verify that the solution meets the performance requirements and handles the constraints correctly.


