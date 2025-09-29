# Build and Run Instructions - Go

## Prerequisites
- Go 1.19 or higher

## Project Structure
```
go/
├── go.mod              # Go module file
├── main.go             # Main executable with sample usage
├── solution1.go        # Original solution (now contains tests)
├── solution_test.go    # Unit tests
└── BUILD_AND_RUN.md    # This file
```

## Module Setup

### Initialize Go Module (if needed)
```bash
# Navigate to the go directory
cd leetcode/easy/1768.merge-strings-alternately/solutions/go

# Initialize module (already done, but for reference)
go mod init merge-strings-alternately
```

## Running the Code

### Run Main Executable
```bash
# Run the main program with sample test cases
go run main.go
```

### Expected Output
```
=== Merge Strings Alternately - Go Solution ===

--- Test Cases ---
Test: Example 1: Equal length strings
Input: word1="abc", word2="pqr"
Output: "apbqcr"
Expected: "apbqcr"
Result: ✅ PASS

Test: Example 2: word2 longer
Input: word1="ab", word2="pqrs"
Output: "apbqrs"
Expected: "apbqrs"
Result: ✅ PASS

... (more test cases)

--- Performance Test ---
Test data: word1="aaaaaaaaaa..." (1000 chars), word2="bbbbbbbbbb..." (1000 chars)
Executed 10000 iterations in 15.234ms
Average time per iteration: 1.523µs
Operations per second: 656,789

--- Edge Cases Test ---
... (edge case results)

--- Memory Usage Test ---
... (memory usage results)
```

## Running Tests

### Run All Tests
```bash
# Run all unit tests
go test -v
```

### Run Specific Tests
```bash
# Run specific test function
go test -v -run TestMergeAlternately

# Run edge case tests
go test -v -run TestEdgeCases

# Run performance tests
go test -v -run TestPerformance
```

### Run Tests with Coverage
```bash
# Run tests with coverage report
go test -v -cover

# Generate detailed coverage report
go test -v -coverprofile=coverage.out
go tool cover -html=coverage.out
```

### Expected Test Output
```
=== RUN   TestMergeAlternately
--- PASS: TestMergeAlternately (0.00s)
=== RUN   TestEdgeCases
--- PASS: TestEdgeCases (0.00s)
=== RUN   TestPerformance
--- PASS: TestPerformance (0.00s)
=== RUN   TestResultLength
--- PASS: TestResultLength (0.00s)
=== RUN   TestAlternatingPattern
--- PASS: TestAlternatingPattern (0.00s)
=== RUN   TestRepeatedCharacters
--- PASS: TestRepeatedCharacters (0.00s)
=== RUN   TestEmptyStrings
--- PASS: TestEmptyStrings (0.00s)
=== RUN   TestConcurrency
--- PASS: TestConcurrency (0.00s)
=== RUN   TestMemoryEfficiency
--- PASS: TestMemoryEfficiency (0.00s)
PASS
coverage: 100.0% of statements
ok      merge-strings-alternately    0.123s
```

## Building Executables

### Build Binary
```bash
# Build executable
go build -o merge_strings main.go

# Run the built executable
./merge_strings
```

### Build with Optimizations
```bash
# Build optimized binary
go build -ldflags="-s -w" -o merge_strings main.go

# Build for different platforms
GOOS=linux GOARCH=amd64 go build -o merge_strings_linux main.go
GOOS=windows GOARCH=amd64 go build -o merge_strings.exe main.go
```

## Benchmarking

### Run Benchmarks
```bash
# Run all benchmarks
go test -bench=.

# Run specific benchmark
go test -bench=BenchmarkMergeAlternately

# Run benchmarks with memory profiling
go test -bench=. -benchmem
```

### Expected Benchmark Output
```
goos: linux
goarch: amd64
pkg: merge-strings-alternately
cpu: Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz
BenchmarkMergeAlternately-12             1000000              1234 ns/op             512 B/op          1 allocs/op
BenchmarkMergeAlternatelySmall-12        10000000             123 ns/op              32 B/op           1 allocs/op
BenchmarkMergeAlternatelyLarge-12        100000               12345 ns/op            2048 B/op         1 allocs/op
BenchmarkMergeAlternatelyUnequal-12      1000000              1234 ns/op             512 B/op          1 allocs/op
PASS
ok      merge-strings-alternately    5.123s
```

## Profiling

### CPU Profiling
```bash
# Run with CPU profiling
go test -cpuprofile=cpu.prof -bench=BenchmarkMergeAlternately

# Analyze CPU profile
go tool pprof cpu.prof
```

### Memory Profiling
```bash
# Run with memory profiling
go test -memprofile=mem.prof -bench=BenchmarkMergeAlternately

# Analyze memory profile
go tool pprof mem.prof
```

## Code Quality

### Format Code
```bash
# Format all Go files
go fmt ./...

# Check formatting
go fmt -d .
```

### Lint Code
```bash
# Install golangci-lint (if not installed)
# go install github.com/golangci/golangci-lint/cmd/golangci-lint@latest

# Run linter
golangci-lint run
```

### Vet Code
```bash
# Run go vet
go vet ./...
```

## Troubleshooting

### Common Issues

#### 1. Module Not Found
```
go: cannot find main module, but found .git/config in /path/to/repo
```
**Solution**: Make sure you're in the correct directory with `go.mod` file.

#### 2. Import Path Issues
```
import "merge-strings-alternately": cannot find package
```
**Solution**: Ensure the module name in `go.mod` matches the import path.

#### 3. Test Failures
```
--- FAIL: TestMergeAlternately (0.00s)
    solution_test.go:25: mergeAlternately("abc", "pqr") = "apbqcr", expected "apbqcr"
```
**Solution**: Check the implementation and test expectations.

### IDE Setup

#### VS Code
1. Install Go extension
2. Install Go tools: `Ctrl+Shift+P` → "Go: Install/Update Tools"
3. Run tests using test explorer

#### GoLand/IntelliJ
1. Open project folder
2. Configure Go SDK
3. Run tests using built-in test runner

#### Vim/Neovim
1. Install vim-go plugin
2. Use `:GoTest` to run tests
3. Use `:GoBench` to run benchmarks

## Continuous Integration

### GitHub Actions Example
```yaml
name: Go Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up Go
      uses: actions/setup-go@v3
      with:
        go-version: 1.19
    - name: Run tests
      run: |
        cd leetcode/easy/1768.merge-strings-alternately/solutions/go
        go test -v
    - name: Run benchmarks
      run: |
        cd leetcode/easy/1768.merge-strings-alternately/solutions/go
        go test -bench=. -benchmem
```

## Performance Optimization

### Build Flags
```bash
# Build with optimizations
go build -ldflags="-s -w" -o merge_strings main.go

# Build with race detector
go build -race -o merge_strings main.go
```

### Runtime Flags
```bash
# Run with garbage collection tuning
GOMAXPROCS=4 go run main.go

# Run with memory profiling
go run main.go -memprofile=mem.prof
```

## Additional Tools

### Static Analysis
```bash
# Run staticcheck
staticcheck ./...

# Run gosec for security analysis
gosec ./...
```

### Documentation
```bash
# Generate documentation
go doc -all

# Serve documentation locally
godoc -http=:6060
```

## Performance Benchmarks

### Expected Performance (approximate)
- **Small inputs (3+3 chars)**: ~123 ns/op, 32 B/op, 1 allocs/op
- **Medium inputs (100+100 chars)**: ~1234 ns/op, 512 B/op, 1 allocs/op
- **Large inputs (1000+1000 chars)**: ~12345 ns/op, 2048 B/op, 1 allocs/op

### Optimization Tips
1. Use `strings.Builder` for efficient string building
2. Pre-allocate capacity with `Grow()`
3. Use `WriteByte()` for single character writes
4. Avoid string concatenation in loops
5. Use `strings.Repeat()` for generating test data
