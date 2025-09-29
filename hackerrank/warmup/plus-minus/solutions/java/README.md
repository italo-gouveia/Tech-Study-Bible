# Java Solutions - Plus Minus

## Overview
This directory contains Java implementations of the Plus Minus problem with multiple approaches and comprehensive testing.

## Solutions

### Solution 1: Basic Counting Approach
- **File**: `solution1.java`
- **Approach**: Traditional loop with conditional counting
- **Performance**: Faster execution, lower memory overhead
- **Best for**: Maximum performance scenarios

### Solution 2: Stream-based Approach
- **File**: `solution2.java`
- **Approach**: Java 8+ streams with functional programming
- **Performance**: Slightly slower due to stream overhead
- **Best for**: Functional programming style, complex transformations

## Performance

### Java Performance Results

| Solution | Time (ms) | Operations/sec | Performance Ratio |
|----------|-----------|----------------|-------------------|
| **Solution 1** (Basic Counting) | 271.90 | 36,780 | 1.00x (baseline) |
| **Solution 2** (Stream-based) | 293.27 | 34,100 | 0.93x |

### Key Insights

- **Solution 1 is 7% faster** than Solution 2
- **Basic counting approach** outperforms stream operations for simple counting tasks
- **Stream overhead**: Functional programming constructs add performance cost
- **JVM optimization**: Performance improves after JIT compilation

### When to Use Each Approach

- **Solution 1**: Use for maximum performance and simple counting operations
- **Solution 2**: Use when functional programming style is preferred or for complex data transformations

### Cross-Language Comparison

- **Java vs Go**: Go is 2x faster (140ms vs 272ms for 10,000 iterations)
- **Memory usage**: Go uses significantly less memory than Java
- **Enterprise vs Performance**: Java provides better ecosystem, Go provides better performance

*For complete performance analysis, see [../../README.md](../../README.md#performance-analysis)*

## Running the Solutions

### Compilation and Execution
```bash
# Compile all files
javac -cp . solution1.java solution2.java Main.java

# Run the main program with tests and performance comparison
java -cp . Main
```

### Individual Solution Testing
```bash
# Test Solution 1 only
java -cp . Solution1

# Test Solution 2 only  
java -cp . Solution2
```

## Test Coverage

The `Main.java` file includes:
- **Basic functionality tests** with various input scenarios
- **Performance comparison** between both solutions
- **Edge case testing** with boundary conditions
- **Comprehensive output** showing execution times and ratios

## Key Features

1. **Multiple Approaches**: Both traditional and functional programming styles
2. **Performance Testing**: Built-in benchmarking and comparison
3. **Edge Case Coverage**: Tests for all boundary conditions
4. **Clean Output**: Proper formatting with 6 decimal places
5. **Educational Value**: Demonstrates different Java programming paradigms

## Complexity Analysis

- **Time Complexity**: O(n) - single pass through the array
- **Space Complexity**: O(1) - only using a few variables for counting
- **Precision**: Handles floating-point precision requirements correctly
