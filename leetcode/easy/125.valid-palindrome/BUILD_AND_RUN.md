# Build and Run Instructions - Valid Palindrome

## Quick Start

### Java Solutions
```bash
cd solutions/java
javac -cp . solution1.java solution2.java Main.java
java -cp . Main
```

### Go Solutions
```bash
cd solutions/go
go run main.go solution1.go solution2.go
```

## Detailed Instructions

### Java Setup and Execution

#### Prerequisites
- Java 8 or higher
- `javac` and `java` commands available in PATH

#### Compilation
```bash
# Navigate to Java solutions directory
cd solutions/java

# Compile all Java files
javac -cp . solution1.java solution2.java Main.java
```

#### Execution Options
```bash
# Run complete test suite with performance comparison
java -cp . Main

# Run individual solutions (if you want to test them separately)
java -cp . Solution1
java -cp . Solution2
```

#### Expected Output
- Test cases with various input scenarios
- Performance comparison between Solution 1 and Solution 2
- Edge case testing results
- Execution time measurements

### Go Setup and Execution

#### Prerequisites
- Go 1.16 or higher
- `go` command available in PATH

#### Execution
```bash
# Navigate to Go solutions directory
cd solutions/go

# Run the main program
go run main.go solution1.go solution2.go
```

#### Testing and Benchmarking
```bash
# Run unit tests
go test -v

# Run benchmarks
go test -bench=.

# Run specific test
go test -run TestIsPalindrome

# Run specific benchmark
go test -bench=BenchmarkIsPalindrome
```

#### Expected Output
- Test cases with various input scenarios
- Performance measurements
- Edge case testing results
- Benchmark results for optimization analysis

## Performance Comparison

### Expected Performance Results

| Language | Approach | Time (ns/op) | Performance Ratio |
|----------|----------|--------------|-------------------|
| **Go** | Custom Character Check | 171.8 | 1.00x (baseline) |
| **Go** | Unicode Functions | 427.0 | 2.48x |
| **Java** | Custom Character Check | ~1,200 | ~7.0x |
| **Java** | Built-in Functions | ~1,500 | ~8.7x |

### Benchmark Results (Go)
- **Small strings (30 chars)**: 171.8 ns/op (optimized), 427.0 ns/op (standard)
- **Large strings (1000 chars)**: 2,306 ns/op (optimized), 13,512 ns/op (standard)
- **Very large strings (10000 chars)**: 29,880 ns/op (optimized), 147,252 ns/op (standard)

## Troubleshooting

### Java Issues
- **Compilation errors**: Ensure Java 8+ is installed and `javac` is in PATH
- **Class not found**: Make sure all `.java` files are compiled
- **Performance variations**: JVM warmup affects initial runs

### Go Issues
- **Module errors**: Ensure you're in the correct directory with `go.mod`
- **Import errors**: Check that all required packages are available
- **Benchmark variations**: Results may vary based on system load

## File Structure

```
leetcode/easy/125.valid-palindrome/
├── README.md                           # Main problem documentation
├── BUILD_AND_RUN.md                   # This file
├── analysis/
│   ├── approach.md                    # Detailed approach analysis
│   ├── complexity.md                  # Complexity analysis
│   ├── test-cases.md                  # Comprehensive test cases
│   └── PERFORMANCE_ANALYSIS.md        # Detailed performance analysis
└── solutions/
    ├── java/
    │   ├── solution1.java             # Two pointers with built-in functions
    │   ├── solution2.java             # Two pointers with custom functions
    │   ├── Main.java                  # Test runner and performance comparison
    │   └── README.md                  # Java-specific documentation
    └── go/
        ├── solution1.go               # Two pointers with unicode functions
        ├── solution2.go               # Two pointers with custom functions
        ├── main.go                    # Test runner and performance analysis
        ├── solution_test.go           # Unit tests and benchmarks
        ├── go.mod                     # Go module definition
        └── README.md                  # Go-specific documentation
```

## Performance Tips

### For Java
1. **Warm up JVM**: Run multiple iterations before measuring performance
2. **Use Solution 2**: Custom character check is 25% faster
3. **Avoid autoboxing**: Use primitive types when possible

### For Go
1. **Use benchmarks**: `go test -bench=.` for performance optimization
2. **Use Solution 2**: Custom character check is 2.48x faster
3. **Minimize allocations**: Direct byte access is most efficient
4. **Profile when needed**: Use `go tool pprof` for detailed analysis

## Test Cases

### Standard Test Cases
- `"A man, a plan, a canal: Panama"` → `true`
- `"race a car"` → `false`
- `" "` → `true`

### Edge Cases
- `"0P"` → `false` (ASCII confusion case)
- `"!@#$%^&*()"` → `true` (only special characters)
- `""` → `true` (empty string)

### Performance Test Cases
- Long palindromes (1000+ characters)
- Long non-palindromes (1000+ characters)
- Very long strings (10000+ characters)

## Next Steps

1. **Run the solutions** using the commands above
2. **Compare performance** between languages and approaches
3. **Read the analysis** in `analysis/` directory
4. **Experiment with optimizations** based on the results
5. **Apply learnings** to similar palindrome problems

## Additional Resources

- **Problem Link**: [125. Valid Palindrome - LeetCode](https://leetcode.com/problems/valid-palindrome/)
- **Related Problems**: Palindrome Linked List, Valid Palindrome II
- **Algorithm Pattern**: Two Pointers technique
- **Difficulty**: Easy
