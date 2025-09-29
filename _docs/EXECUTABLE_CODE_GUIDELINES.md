# Executable Code and Testing Guidelines

## Overview
All solutions in this repository should be executable and testable. This document provides guidelines for creating runnable code with proper test coverage.

## Executable Code Requirements

### Main Function Requirements
1. **Standalone Execution**: Each solution should be runnable independently
2. **Sample Test Cases**: Include example usage in main function
3. **Clear Output**: Display results in a readable format
4. **Error Handling**: Handle edge cases gracefully

### Package Structure
- **Java**: Use appropriate package declarations
- **Go**: Use proper module structure
- **Python**: Include `if __name__ == "__main__"` blocks
- **C++**: Include main function with test cases

## Testing Strategy

### Unit Testing
1. **Comprehensive Coverage**: Test all edge cases and examples
2. **Automated Testing**: Use testing frameworks for each language
3. **Performance Testing**: Include timing measurements
4. **Documentation**: Document test cases and expected results

### Test Organization
```
solutions/
├── java/
│   ├── Solution.java          # Main solution class
│   ├── SolutionTest.java      # Unit tests
│   └── Main.java             # Executable with sample usage
├── go/
│   ├── solution.go           # Main solution
│   ├── solution_test.go      # Unit tests
│   └── main.go              # Executable with sample usage
```

## Language-Specific Guidelines

### Java
```java
// Package declaration
package leetcode.easy.mergeStringsAlternately;

// Main class with executable code
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        testCase(solution, "abc", "pqr", "apbqcr");
        testCase(solution, "ab", "pqrs", "apbqrs");
        // ... more test cases
    }
    
    private static void testCase(Solution solution, String word1, String word2, String expected) {
        String result = solution.mergeAlternately(word1, word2);
        System.out.printf("Input: word1=\"%s\", word2=\"%s\"%n", word1, word2);
        System.out.printf("Output: \"%s\"%n", result);
        System.out.printf("Expected: \"%s\"%n", expected);
        System.out.printf("Result: %s%n%n", result.equals(expected) ? "PASS" : "FAIL");
    }
}
```

### Go
```go
// Package declaration
package main

import (
    "fmt"
    "testing"
)

// Main function with sample usage
func main() {
    // Test cases
    testCases := []struct {
        word1, word2, expected string
    }{
        {"abc", "pqr", "apbqcr"},
        {"ab", "pqrs", "apbqrs"},
        // ... more test cases
    }
    
    for _, tc := range testCases {
        result := mergeAlternately(tc.word1, tc.word2)
        fmt.Printf("Input: word1=\"%s\", word2=\"%s\"\n", tc.word1, tc.word2)
        fmt.Printf("Output: \"%s\"\n", result)
        fmt.Printf("Expected: \"%s\"\n", tc.expected)
        fmt.Printf("Result: %s\n\n", getResult(result == tc.expected))
    }
}

// Unit tests
func TestMergeAlternately(t *testing.T) {
    tests := []struct {
        word1, word2, expected string
    }{
        {"abc", "pqr", "apbqcr"},
        {"ab", "pqrs", "apbqrs"},
        // ... more test cases
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

### Python
```python
# Main execution block
if __name__ == "__main__":
    solution = Solution()
    
    # Test cases
    test_cases = [
        ("abc", "pqr", "apbqcr"),
        ("ab", "pqrs", "apbqrs"),
        # ... more test cases
    ]
    
    for word1, word2, expected in test_cases:
        result = solution.mergeAlternately(word1, word2)
        print(f"Input: word1='{word1}', word2='{word2}'")
        print(f"Output: '{result}'")
        print(f"Expected: '{expected}'")
        print(f"Result: {'PASS' if result == expected else 'FAIL'}")
        print()

# Unit tests using unittest
import unittest

class TestMergeAlternately(unittest.TestCase):
    def test_merge_alternately(self):
        solution = Solution()
        test_cases = [
            ("abc", "pqr", "apbqcr"),
            ("ab", "pqrs", "apbqrs"),
            # ... more test cases
        ]
        
        for word1, word2, expected in test_cases:
            with self.subTest(word1=word1, word2=word2):
                result = solution.mergeAlternately(word1, word2)
                self.assertEqual(result, expected)
```

### C++
```cpp
#include <iostream>
#include <string>
#include <vector>
#include <cassert>

// Main function with test cases
int main() {
    Solution solution;
    
    // Test cases
    std::vector<std::tuple<std::string, std::string, std::string>> testCases = {
        {"abc", "pqr", "apbqcr"},
        {"ab", "pqrs", "apbqrs"},
        // ... more test cases
    };
    
    for (const auto& [word1, word2, expected] : testCases) {
        std::string result = solution.mergeAlternately(word1, word2);
        std::cout << "Input: word1=\"" << word1 << "\", word2=\"" << word2 << "\"" << std::endl;
        std::cout << "Output: \"" << result << "\"" << std::endl;
        std::cout << "Expected: \"" << expected << "\"" << std::endl;
        std::cout << "Result: " << (result == expected ? "PASS" : "FAIL") << std::endl << std::endl;
    }
    
    return 0;
}
```

## Test Case Standards

### Comprehensive Coverage
1. **Provided Examples**: All examples from the problem description
2. **Edge Cases**: Empty strings, single characters, maximum lengths
3. **Boundary Conditions**: Minimum and maximum constraint values
4. **Stress Tests**: Large inputs, unusual patterns

### Test Case Format
```java
// Standard test case structure
public class TestCase {
    private String word1;
    private String word2;
    private String expected;
    private String description;
    
    public TestCase(String word1, String word2, String expected, String description) {
        this.word1 = word1;
        this.word2 = word2;
        this.expected = expected;
        this.description = description;
    }
    
    // Getters and test execution
}
```

### Expected Test Output Format
```
Input: word1="abc", word2="pqr"
Output: "apbqcr"
Expected: "apbqcr"
Result: PASS

Input: word1="ab", word2="pqrs"
Output: "apbqrs"
Expected: "apbqrs"
Result: PASS
```

## Performance Testing

### Timing Measurements
```java
// Java performance testing
public static void performanceTest() {
    Solution solution = new Solution();
    String word1 = "a".repeat(1000);
    String word2 = "b".repeat(1000);
    
    long startTime = System.nanoTime();
    String result = solution.mergeAlternately(word1, word2);
    long endTime = System.nanoTime();
    
    System.out.printf("Performance Test - Input size: %d+%d characters%n", 
        word1.length(), word2.length());
    System.out.printf("Execution time: %.2f ms%n", 
        (endTime - startTime) / 1_000_000.0);
    System.out.printf("Result length: %d characters%n", result.length());
}
```

### Memory Usage
```java
// Memory usage measurement
public static void memoryTest() {
    Runtime runtime = Runtime.getRuntime();
    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
    
    // Execute solution
    Solution solution = new Solution();
    String result = solution.mergeAlternately("a".repeat(1000), "b".repeat(1000));
    
    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
    System.out.printf("Memory used: %d bytes%n", afterMemory - beforeMemory);
}
```

## Build and Run Instructions

### Java
```bash
# Compile
javac -cp . Solution.java Main.java

# Run
java -cp . Main

# Run tests
javac -cp . Solution.java SolutionTest.java
java -cp . org.junit.runner.JUnitCore SolutionTest
```

### Go
```bash
# Run main
go run main.go solution.go

# Run tests
go test -v

# Build executable
go build -o merge_strings main.go solution.go
./merge_strings
```

### Python
```bash
# Run main
python solution.py

# Run tests
python -m unittest solution_test.py

# Run with pytest
pytest solution_test.py -v
```

### C++
```bash
# Compile and run
g++ -std=c++17 -o solution solution.cpp
./solution

# Compile with tests
g++ -std=c++17 -o test_solution solution.cpp test_solution.cpp
./test_solution
```

## Documentation Requirements

### Code Comments
1. **Function Documentation**: Document parameters, return values, and complexity
2. **Inline Comments**: Explain complex logic and algorithms
3. **Test Documentation**: Document test cases and expected behavior
4. **Performance Notes**: Document performance characteristics

### README Updates
Each solution folder should include:
1. **Compilation Instructions**: How to build and run
2. **Test Instructions**: How to run tests
3. **Sample Output**: Expected output from main function
4. **Performance Benchmarks**: Timing and memory usage

## Quality Assurance

### Code Review Checklist
- [ ] Code compiles without errors
- [ ] All test cases pass
- [ ] Main function produces expected output
- [ ] Performance is within acceptable limits
- [ ] Code follows language-specific style guides
- [ ] Documentation is complete and accurate

### Automated Testing
1. **CI/CD Integration**: Set up automated testing pipeline
2. **Code Coverage**: Ensure comprehensive test coverage
3. **Performance Regression**: Monitor performance over time
4. **Style Checking**: Automated code style validation

## Best Practices

### Maintainability
1. **Modular Design**: Separate solution logic from testing code
2. **Clear Interfaces**: Well-defined function signatures
3. **Error Handling**: Graceful handling of edge cases
4. **Documentation**: Keep documentation up to date

### Performance
1. **Efficient Algorithms**: Use optimal time and space complexity
2. **Memory Management**: Avoid memory leaks and excessive allocations
3. **Profiling**: Regular performance profiling and optimization
4. **Benchmarking**: Compare different approaches

### Testing
1. **Test-Driven Development**: Write tests before implementation
2. **Regression Testing**: Ensure changes don't break existing functionality
3. **Integration Testing**: Test with real-world scenarios
4. **Performance Testing**: Regular performance validation

## Conclusion

Executable code with comprehensive testing ensures that solutions are:
- **Verifiable**: Can be tested and validated
- **Maintainable**: Easy to modify and extend
- **Educational**: Clear examples for learning
- **Production-Ready**: Suitable for real-world use

Following these guidelines will result in high-quality, executable solutions that serve as excellent learning resources and reference implementations.
