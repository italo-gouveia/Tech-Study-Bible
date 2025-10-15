# Build and Run Instructions - Java Solutions

This document provides instructions for compiling and running the Contains Duplicate Java solutions.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command line access (Terminal/Command Prompt)

## Directory Structure

```
solutions/java/
├── solution1.java      # HashSet approach
├── solution2.java      # Sorting approach  
├── solution3.java      # Brute force approach
├── Main.java           # Test runner with comprehensive tests
├── Bench.java          # Micro-benchmark suite
└── BUILD_AND_RUN.md    # This file
```

## Quick Start

### 1. Compile All Files

```bash
# Navigate to the java solutions directory
cd leetcode/easy/217.contains-duplicate/solutions/java/

# Compile all Java files
javac *.java
```

### 2. Run the Main Test Suite

```bash
# Run comprehensive tests and performance comparison
java Main
```

### 3. Run Micro-benchmarks

```bash
# Run detailed performance benchmarks
java Bench
```

## Individual Solution Testing

### Test Solution 1 (HashSet)

```bash
# Compile and run Solution 1 only
javac solution1.java
java solution1
```

### Test Solution 2 (Sorting)

```bash
# Compile and run Solution 2 only
javac solution2.java
java solution2
```

### Test Solution 3 (Brute Force)

```bash
# Compile and run Solution 3 only
javac solution3.java
java solution3
```

## Expected Output

### Main Test Suite Output

```
=== Contains Duplicate - Java Solutions ===

--- Solution 1: HashSet Approach ---
Test: Example 1: [1,2,3,1]
Input: [1, 2, 3, 1]
Output: true
Expected: true
Result: ✅ PASS

[... more test results ...]

--- Performance Comparison ---
Small Array (100 elements):
  HashSet: 15 ms
  Sorting: 45 ms
  Brute Force: 123 ms
  Ratio (Sorting/HashSet): 3.00x
  Ratio (Brute/HashSet): 8.20x

[... more performance results ...]
```

### Benchmark Output

```
=== Contains Duplicate - Micro-benchmark ===

--- HashSet Approach Benchmark ---
Size 100: 0.015 ms average (14852.00 ns per operation)
Size 500: 0.078 ms average (78234.00 ns per operation)
Size 1000: 0.156 ms average (156234.00 ns per operation)
[... more benchmark results ...]
```

## Performance Expectations

### Typical Performance Ratios

| Array Size | HashSet | Sorting | Brute Force |
|------------|---------|---------|-------------|
| 100        | 1x      | 3x      | 8x          |
| 1000       | 1x      | 3-4x    | 80x+        |
| 10000      | 1x      | 4-5x    | N/A         |

*Note: Brute force is only tested on small arrays due to O(n²) complexity*

### Memory Usage

- **HashSet**: ~40 bytes per element (overhead for hash table)
- **Sorting**: ~4 bytes per element (just the array)
- **Brute Force**: ~0 bytes extra (no additional data structures)

## Troubleshooting

### Common Issues

#### 1. Compilation Errors

```bash
# If you get "package java.util does not exist" errors
# Make sure you're using JDK 8+ and not just JRE
java -version
```

#### 2. Out of Memory Errors

```bash
# If running benchmarks causes OutOfMemoryError
# Increase heap size
java -Xmx2g Main
java -Xmx2g Bench
```

#### 3. Performance Variations

- Performance may vary based on:
  - JVM version and vendor
  - Hardware specifications
  - System load
  - JIT compilation (warm-up effects)

### Optimized Compilation

```bash
# For production-like performance testing
javac -O *.java
java -XX:+UseG1GC -XX:+UseStringDeduplication Main
```

## Advanced Usage

### Custom Test Cases

You can modify the `Main.java` file to add your own test cases:

```java
// Add custom test cases in the test methods
testCase(solution1::containsDuplicate, 
         new int[]{your, custom, array}, 
         expectedResult, 
         "Your test description");
```

### Benchmark Customization

Modify `Bench.java` to test different scenarios:

```java
// Change array sizes in the sizes array
int[] sizes = {your, custom, sizes};

// Modify iterations for more/less precise results
int iterations = 10000; // More iterations = more precision
```

### Memory Profiling

For detailed memory analysis:

```bash
# Run with memory profiling
java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps Main

# Or use JVisualVM for GUI profiling
jvisualvm
```

## Production Recommendations

### For LeetCode Submissions

Use **Solution 1 (HashSet)** for optimal performance:
- Best time complexity: O(n)
- Simple and readable
- Handles all edge cases

### For Space-Constrained Environments

Use **Solution 2 (Sorting)** when memory is limited:
- Constant extra space: O(1)
- Slightly slower but still efficient
- Modifies input array

### Never Use in Production

**Solution 3 (Brute Force)** is for educational purposes only:
- Quadratic time complexity: O(n²)
- Unacceptable performance for large inputs
- Only included for learning purposes

## Integration with IDEs

### IntelliJ IDEA

1. Open the `solutions/java/` directory as a project
2. Run configurations will be automatically detected
3. Use built-in profiler for performance analysis

### Eclipse

1. Import as Java project
2. Right-click on `Main.java` → Run As → Java Application
3. Use built-in performance tools

### VS Code

1. Install Java Extension Pack
2. Open the directory in VS Code
3. Use integrated terminal for compilation and execution


