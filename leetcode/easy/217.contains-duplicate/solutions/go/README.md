# Go Solutions - Contains Duplicate

This directory contains Go implementations for LeetCode problem 217: Contains Duplicate.

## Files

- **solution1.go**: Map-based approach (optimal)
- **solution2.go**: Sorting approach (space-efficient)
- **solution3.go**: Brute force approach (educational)
- **main.go**: Runnable examples with comprehensive testing
- **solution_test.go**: Unit tests and benchmarks
- **go.mod**: Module definition

## Quick Start

### 1. Run the Main Program

```bash
# Navigate to the go solutions directory
cd leetcode/easy/217.contains-duplicate/solutions/go/

# Run the main program with comprehensive tests
go run main.go
```

### 2. Run Tests

```bash
# Run all unit tests
go test

# Run tests with verbose output
go test -v

# Run specific test
go test -run TestContainsDuplicateMap
```

### 3. Run Benchmarks

```bash
# Run all benchmarks
go test -bench=.

# Run specific benchmark
go test -bench=BenchmarkContainsDuplicateMap

# Run benchmarks with memory allocation info
go test -bench=. -benchmem

# Run benchmarks for specific size
go test -bench=Size_1000
```

### 4. Build and Run Individual Solutions

```bash
# Build the program
go build -o contains-duplicate main.go

# Run the built executable
./contains-duplicate

# Build for different platforms
GOOS=windows GOARCH=amd64 go build -o contains-duplicate.exe main.go
```

## Solutions Overview

### Solution 1: Map-based Approach (Recommended)

```go
func containsDuplicate(nums []int) bool {
    if len(nums) <= 1 {
        return false
    }
    
    seen := make(map[int]bool)
    for _, num := range nums {
        if seen[num] {
            return true
        }
        seen[num] = true
    }
    return false
}
```

- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Best For**: General purpose, optimal performance

### Solution 2: Sorting Approach

```go
func containsDuplicate(nums []int) bool {
    if len(nums) <= 1 {
        return false
    }
    
    sort.Ints(nums)
    for i := 1; i < len(nums); i++ {
        if nums[i] == nums[i-1] {
            return true
        }
    }
    return false
}
```

- **Time Complexity**: O(n log n)
- **Space Complexity**: O(1) extra space
- **Best For**: Space-constrained environments

### Solution 3: Brute Force (Educational)

```go
func containsDuplicate(nums []int) bool {
    if len(nums) <= 1 {
        return false
    }
    
    for i := 0; i < len(nums)-1; i++ {
        for j := i + 1; j < len(nums); j++ {
            if nums[i] == nums[j] {
                return true
            }
        }
    }
    return false
}
```

- **Time Complexity**: O(n²)
- **Space Complexity**: O(1)
- **Best For**: Learning purposes only

## Expected Output

### Main Program Output

```
=== Contains Duplicate - Go Solutions ===

--- Solution 1: Map-based Approach ---
Test: Example 1: [1,2,3,1]
Input: [1 2 3 1]
Output: true
Expected: true
Result: ✅ PASS

[... more test results ...]

--- Performance Comparison ---
Small Array (100 elements):
  Map: 2 ms
  Sorting: 8 ms
  Brute Force: 15 ms
  Ratio (Sorting/Map): 4.00x
  Ratio (Brute/Map): 7.50x

[... more performance results ...]
```

### Benchmark Output

```
goos: linux
goarch: amd64
BenchmarkContainsDuplicateMap/Size_100-8         	  500000	      3000 ns/op
BenchmarkContainsDuplicateMap/Size_500-8         	  200000	      8000 ns/op
BenchmarkContainsDuplicateMap/Size_1000-8        	  100000	     15000 ns/op
BenchmarkContainsDuplicateSort/Size_100-8        	  100000	     12000 ns/op
BenchmarkContainsDuplicateSort/Size_500-8        	   20000	     65000 ns/op
BenchmarkContainsDuplicateSort/Size_1000-8       	   10000	    130000 ns/op
```

## Performance Analysis

### Typical Performance Ratios

| Array Size | Map | Sorting | Brute Force |
|------------|-----|---------|-------------|
| 100        | 1x  | 4x      | 8x          |
| 1000       | 1x  | 8x      | 80x         |
| 10000      | 1x  | 12x     | N/A         |

### Memory Usage

- **Map**: ~40 bytes per element (hash table overhead)
- **Sorting**: ~8 bytes per element (Go slice overhead)
- **Brute Force**: ~8 bytes per element (no additional data structures)

## Testing

### Unit Tests

The test suite includes:

- **Basic functionality tests**: All three solutions with standard test cases
- **Edge cases**: Empty arrays, single elements, all duplicates
- **Consistency tests**: All solutions produce the same results
- **Large array tests**: Performance with maximum constraint sizes
- **Random tests**: 100 randomly generated test cases

### Benchmarks

Comprehensive benchmarks for:

- **Performance comparison**: All approaches across different array sizes
- **Memory usage**: Allocation patterns for each solution
- **Scalability**: How performance degrades with input size

### Running Tests

```bash
# Run all tests
go test

# Run with coverage
go test -cover

# Run with race detection
go test -race

# Run benchmarks with profiling
go test -bench=. -cpuprofile=cpu.prof
go test -bench=. -memprofile=mem.prof
```

## Advanced Usage

### Custom Test Cases

Add custom test cases in `solution_test.go`:

```go
func TestCustomCases(t *testing.T) {
    customTests := []struct {
        name     string
        nums     []int
        expected bool
    }{
        {"Custom test 1", []int{1, 2, 3}, false},
        {"Custom test 2", []int{1, 1, 2}, true},
    }
    
    for _, tt := range customTests {
        t.Run(tt.name, func(t *testing.T) {
            result := containsDuplicateMap(tt.nums)
            if result != tt.expected {
                t.Errorf("Expected %v, got %v", tt.expected, result)
            }
        })
    }
}
```

### Performance Profiling

```bash
# CPU profiling
go test -bench=. -cpuprofile=cpu.prof
go tool pprof cpu.prof

# Memory profiling
go test -bench=. -memprofile=mem.prof
go tool pprof mem.prof

# Web interface for profiling
go tool pprof -http=:8080 cpu.prof
```

### Optimization Tips

1. **Pre-allocate map capacity**:
   ```go
   seen := make(map[int]bool, len(nums))
   ```

2. **Use struct{} for memory efficiency**:
   ```go
   seen := make(map[int]struct{})
   ```

3. **Avoid unnecessary allocations**:
   ```go
   // Good: reuse slice
   nums := make([]int, 0, capacity)
   
   // Bad: create new slice each time
   nums = append(nums, newElement)
   ```

## Production Recommendations

### For LeetCode Submissions

Use **Solution 1 (Map-based)**:
- Best time complexity: O(n)
- Simple and readable
- Handles all edge cases

### For Production Systems

Choose based on requirements:

- **High performance**: Map-based approach
- **Memory constrained**: Sorting approach
- **Educational**: All approaches for comparison

### Never Use in Production

**Solution 3 (Brute Force)** is for educational purposes only due to O(n²) complexity.

## Troubleshooting

### Common Issues

1. **Import errors**: Make sure you're in the correct directory
2. **Build errors**: Ensure Go 1.21+ is installed
3. **Test failures**: Check that all dependencies are available

### Performance Issues

1. **Slow benchmarks**: Reduce iteration count for initial testing
2. **Memory issues**: Use smaller test arrays for development
3. **Inconsistent results**: Ensure proper warm-up in benchmarks

## Integration

### With IDEs

- **VS Code**: Use Go extension for syntax highlighting and debugging
- **GoLand**: Full IDE support with integrated testing and profiling
- **Vim/Emacs**: Use appropriate Go plugins

### With CI/CD

```yaml
# Example GitHub Actions workflow
name: Go Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - uses: actions/setup-go@v2
      with:
        go-version: 1.21
    - run: cd leetcode/easy/217.contains-duplicate/solutions/go && go test
    - run: cd leetcode/easy/217.contains-duplicate/solutions/go && go test -bench=.
```



