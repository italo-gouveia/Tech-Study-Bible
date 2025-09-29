# Build and Run Instructions - Plus Minus

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
go run main.go solution1.go
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
go run main.go solution1.go
```

#### Testing and Benchmarking
```bash
# Run unit tests
go test -v

# Run benchmarks
go test -bench=.

# Run specific test
go test -run TestPlusMinus

# Run specific benchmark
go test -bench=BenchmarkPlusMinus
```

#### Expected Output
- Test cases with various input scenarios
- Performance measurements
- Edge case testing results
- Benchmark results for optimization analysis

## Performance Comparison

### Expected Performance Results

| Language | Approach | Time (ms) | Operations/sec |
|----------|----------|-----------|----------------|
| **Go** | Basic Counting | ~140 | ~71,000 |
| **Java** | Basic Counting | ~272 | ~37,000 |
| **Java** | Stream-based | ~293 | ~34,000 |

### Benchmark Results (Go)
- **Small arrays (5 elements)**: ~6.6 ns/op
- **Large arrays (1000 elements)**: ~368 ns/op

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
hackerrank/warmup/plus-minus/
├── README.md                           # Main problem documentation
├── BUILD_AND_RUN.md                   # This file
├── analysis/
│   └── PERFORMANCE_ANALYSIS.md        # Detailed performance analysis
└── solutions/
    ├── java/
    │   ├── solution1.java             # Basic counting approach
    │   ├── solution2.java             # Stream-based approach
    │   ├── Main.java                  # Test runner and performance comparison
    │   └── README.md                  # Java-specific documentation
    └── go/
        ├── solution1.go               # Basic counting approach
        ├── main.go                    # Test runner and performance analysis
        ├── solution_test.go           # Unit tests and benchmarks
        ├── go.mod                     # Go module definition
        └── README.md                  # Go-specific documentation
```

## Performance Tips

### For Java
1. **Warm up JVM**: Run multiple iterations before measuring performance
2. **Use Solution 1**: Basic counting is 7% faster than streams
3. **Avoid unnecessary allocations**: Use primitive types when possible

### For Go
1. **Use benchmarks**: `go test -bench=.` for performance optimization
2. **Minimize allocations**: Direct array access is most efficient
3. **Profile when needed**: Use `go tool pprof` for detailed analysis

## Next Steps

1. **Run the solutions** using the commands above
2. **Compare performance** between languages and approaches
3. **Read the analysis** in `analysis/PERFORMANCE_ANALYSIS.md`
4. **Experiment with optimizations** based on the results
5. **Apply learnings** to similar counting problems
