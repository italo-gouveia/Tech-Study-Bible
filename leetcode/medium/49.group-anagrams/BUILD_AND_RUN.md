# Build and Run Instructions - Group Anagrams

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
go test -run TestGroupAnagrams

# Run specific benchmark
go test -bench=BenchmarkGroupAnagrams
```

#### Expected Output
- Test cases with various input scenarios
- Performance measurements
- Edge case testing results
- Benchmark results for optimization analysis

## Performance Comparison

### Expected Performance Results

| Language | Approach | Time (ms) | Performance Ratio |
|----------|----------|-----------|-------------------|
| **Go** | String Sorting | 1.94 | 1.00x (baseline) |
| **Go** | Frequency Counting | 3.11 | 1.60x |
| **Java** | String Sorting | 1.09 | 0.56x |
| **Java** | Frequency Counting | 0.87 | 0.45x |

### Benchmark Results (Go)
- **Small datasets**: 1,221 ns/op (sorting), 1,479 ns/op (frequency)
- **Large datasets**: 3,666 ns/op (sorting), 7,931 ns/op (frequency)
- **Mixed groups**: 7,569 ns/op (sorting), 3,391 ns/op (frequency)

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
leetcode/medium/49.group-anagrams/
├── README.md                           # Main problem documentation
├── BUILD_AND_RUN.md                   # This file
├── analysis/
│   ├── approach.md                    # Detailed approach analysis
│   ├── complexity.md                  # Complexity analysis
│   ├── test-cases.md                  # Comprehensive test cases
│   └── PERFORMANCE_ANALYSIS.md        # Detailed performance analysis
└── solutions/
    ├── java/
    │   ├── solution1.java             # Hash table with string sorting
    │   ├── solution2.java             # Hash table with character frequency
    │   ├── Main.java                  # Test runner and performance comparison
    │   └── README.md                  # Java-specific documentation
    └── go/
        ├── solution1.go               # Hash table with string sorting
        ├── solution2.go               # Hash table with character frequency
        ├── main.go                    # Test runner and performance analysis
        ├── solution_test.go           # Unit tests and benchmarks
        ├── go.mod                     # Go module definition
        └── README.md                  # Go-specific documentation
```

## Performance Tips

### For Java
1. **Warm up JVM**: Run multiple iterations before measuring performance
2. **Use Solution 2**: Character frequency counting is 26% faster for large datasets
3. **Optimize HashMap**: Set appropriate initial capacity
4. **Profile when needed**: Use JProfiler or similar tools

### For Go
1. **Use benchmarks**: `go test -bench=.` for performance optimization
2. **Use Solution 1**: String sorting is 60% faster than frequency counting
3. **Minimize allocations**: Direct string operations are most efficient
4. **Profile when needed**: Use `go tool pprof` for detailed analysis

## Test Cases

### Standard Test Cases
- `["eat","tea","tan","ate","nat","bat"]` → `[["bat"],["nat","tan"],["ate","eat","tea"]]`
- `[""]` → `[[""]]`
- `["a"]` → `[["a"]]`

### Edge Cases
- `["abc","bca","cab","xyz","zyx","yxz"]` → `[["abc","bca","cab"],["xyz","zyx","yxz"]]`
- `["listen","silent","enlist","inlets"]` → `[["listen","silent","enlist","inlets"]]`
- `["a","b","c","d"]` → `[["a"],["b"],["c"],["d"]]`

### Performance Test Cases
- Small datasets (10 strings)
- Medium datasets (100 strings)
- Large datasets (1000 strings)

## Next Steps

1. **Run the solutions** using the commands above
2. **Compare performance** between languages and approaches
3. **Read the analysis** in `analysis/` directory
4. **Experiment with optimizations** based on the results
5. **Apply learnings** to similar grouping problems

## Additional Resources

- **Problem Link**: [49. Group Anagrams - LeetCode](https://leetcode.com/problems/group-anagrams/)
- **Related Problems**: Valid Anagram, Group Shifted Strings
- **Algorithm Pattern**: Hash Table with String Processing
- **Difficulty**: Medium
