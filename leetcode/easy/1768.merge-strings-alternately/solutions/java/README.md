# Java Solutions for Merge Strings Alternately

## Solution 1: StringBuilder with Two Pointers

### Approach
This solution uses a `StringBuilder` for efficient string concatenation and two pointers to traverse both strings simultaneously. It's the most straightforward approach that's easy to understand and implement.

### Code
```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder builder = new StringBuilder(word1.length() + word2.length());
        int i = 0, j = 0;
        
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) builder.append(word1.charAt(i++));
            if (j < word2.length()) builder.append(word2.charAt(j++));
        }
        
        return builder.toString();
    }
}
```

### Line-by-Line Explanation

1. **Line 4**: `StringBuilder builder = new StringBuilder(word1.length() + word2.length());`
   - Creates a StringBuilder with pre-allocated capacity
   - This avoids multiple memory reallocations during string building
   - The capacity is exactly the sum of both string lengths

2. **Line 5**: `int i = 0, j = 0;`
   - Initialize two pointers, one for each string
   - `i` tracks position in `word1`, `j` tracks position in `word2`

3. **Line 7**: `while (i < word1.length() || j < word2.length())`
   - Continue loop until both strings are fully processed
   - Uses OR condition because we want to process remaining characters from either string

4. **Line 8**: `if (i < word1.length()) builder.append(word1.charAt(i++));`
   - Check if word1 still has characters to process
   - `charAt(i++)` gets character at position i, then increments i (post-increment)
   - Append the character to the result

5. **Line 9**: `if (j < word2.length()) builder.append(word2.charAt(j++));`
   - Check if word2 still has characters to process
   - Same logic as above but for word2

6. **Line 12**: `return builder.toString();`
   - Convert StringBuilder to String and return

### Complexity
- **Time**: O(m + n) - We visit each character exactly once
- **Space**: O(m + n) - For the result string

### Advantages
- Easy to understand and implement
- Handles edge cases naturally
- Efficient string building with StringBuilder

---

## Solution 2: Character Array with Bulk Copy

### Approach
This solution uses a character array and bulk copy operations for maximum efficiency. It first interleaves characters up to the minimum length, then copies remaining characters in bulk using `getChars()`.

### Code
```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        char[] res = new char[m + n];
        int min = Math.min(m, n);
        int k = 0;

        for (int i = 0; i < min; i++) {
            res[k++] = word1.charAt(i);
            res[k++] = word2.charAt(i);
        }

        if (m > min) {
            word1.getChars(min, m, res, k);
        } else if (n > min) {
            word2.getChars(min, n, res, k);
        }

        return new String(res);
    }
}
```

### Line-by-Line Explanation

1. **Line 4**: `int m = word1.length(), n = word2.length();`
   - Store lengths of both strings for efficiency
   - Avoids multiple length() calls

2. **Line 5**: `char[] res = new char[m + n];`
   - Create result array with exact size needed
   - More memory efficient than StringBuilder for this use case

3. **Line 6**: `int min = Math.min(m, n);`
   - Find minimum length to determine interleaving range
   - Both strings have characters up to this point

4. **Line 7**: `int k = 0;`
   - Index for the result array
   - Tracks where to place the next character

5. **Lines 9-12**: Interleaving loop
   - `for (int i = 0; i < min; i++)` - Loop up to minimum length
   - `res[k++] = word1.charAt(i);` - Add character from word1, increment k
   - `res[k++] = word2.charAt(i);` - Add character from word2, increment k

6. **Lines 14-18**: Handle remaining characters
   - `if (m > min)` - word1 is longer
   - `word1.getChars(min, m, res, k)` - Bulk copy remaining characters
   - `getChars(srcBegin, srcEnd, dst, dstBegin)` - Efficient bulk copy
   - Same logic for word2 if it's longer

7. **Line 20**: `return new String(res);`
   - Convert character array to String

### Complexity
- **Time**: O(m + n) - Each character is processed once
- **Space**: O(m + n) - For the result array

### Advantages
- Most efficient approach
- Uses bulk copy operations for remaining characters
- Minimal memory overhead
- No string concatenation overhead

## Performance

### Java-Specific Results
- **Solution 1 (StringBuilder)**: 153.62 ms for 10,000 iterations
- **Solution 2 (Character Array)**: 70.94 ms for 10,000 iterations
- **Performance ratio**: Character Array approach is **2.17x faster**

### Java Advantages
- **JIT Optimization**: Significant performance improvement after warm-up
- **Bulk Operations**: `getChars()` provides efficient bulk copying
- **Memory Management**: Pre-allocated arrays avoid dynamic resizing
- **Enterprise Ready**: Excellent for production applications

### When to Use Each Approach
- **Solution 1**: Educational purposes, interviews, prototyping
- **Solution 2**: Production code, performance-critical applications, large-scale processing

> 📊 **Complete Performance Analysis**: See the main [README.md](../../README.md#performance-analysis) for cross-language comparisons and detailed benchmarks.

## Key Java Concepts Demonstrated

### String Building Efficiency
- **StringBuilder**: Use for dynamic string building
- **Pre-allocation**: Set capacity when known for better performance
- **Avoid String concatenation**: `+=` in loops is O(n²) complexity

### Character Array Operations
- **getChars()**: Efficient bulk copy from string to array
- **Character arrays**: More memory efficient than StringBuilder for fixed-size results

### Post-increment Operator
- `i++` vs `++i`: Post-increment returns current value, then increments
- Useful for array indexing and string building

## Edge Cases Handled
1. **Equal length strings**: Both solutions handle naturally
2. **One string longer**: Remaining characters are appended
3. **Empty strings**: Handled by length checks
4. **Single character strings**: Work correctly with the logic

## Executable Code

### Running the Solutions
```bash
# Compile and run main executable
javac -cp . *.java
java -cp . leetcode.easy.mergeStringsAlternately.Main
```

### Running Unit Tests
```bash
# Run with JUnit 5
java -jar junit-platform-console-standalone-1.9.3.jar -cp . --scan-classpath
```

### Build Instructions
See [BUILD_AND_RUN.md](BUILD_AND_RUN.md) for detailed compilation and execution instructions.

## Testing Recommendations
```java
// Test cases to verify
"abc", "pqr" -> "apbqcr"
"ab", "pqrs" -> "apbqrs"  
"abcd", "pq" -> "apbqcd"
"a", "b" -> "ab"
"", "abc" -> "abc"
"abc", "" -> "abc"
```

### Comprehensive Test Coverage
- ✅ All provided examples
- ✅ Edge cases (empty strings, single characters)
- ✅ Boundary conditions (maximum constraint values)
- ✅ Performance tests with large inputs
- ✅ Cross-solution consistency tests
- ✅ Memory usage validation


