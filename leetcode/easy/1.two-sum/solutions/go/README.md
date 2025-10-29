# Go Solutions for Two Sum

## Solutions Implemented

### Solution 1: One-Pass Hash Table ✅ (Recommended)
- **File**: `solution1.go`
- **Time**: O(n)
- **Space**: O(n)
- **Approach**: Single pass with map for complement lookup

### Solution 2: Two-Pass Hash Table
- **File**: `solution2.go`
- **Time**: O(n)
- **Space**: O(n)
- **Approach**: Build map first, then search

## How to Compile and Run

### Run Solution 1
```bash
go run solution1.go main.go
```

### Run Solution 2
```bash
go run solution2.go main.go
```

### Run Tests
```bash
go test -v
```

### Build Executable
```bash
go build -o two-sum
./two-sum
```

## Solution Explanation

### Solution 1 (Optimal)

**Key Steps:**
1. Create `map[int]int` to store (value → index)
2. For each element, calculate `complement = target - current`
3. If complement exists in map, return indices
4. Otherwise, add current element to map

**Why it's optimal:**
- ✅ Single pass through array
- ✅ O(1) map lookup
- ✅ Idiomatic Go code
- ✅ Memory efficient with range loop

**Example:**
```go
Input: nums = [2, 7, 11, 15], target = 9

Iteration 0: num = 2, i = 0
  complement = 9 - 2 = 7
  map = {}, complement not found
  map = {2: 0}

Iteration 1: num = 7, i = 1
  complement = 9 - 7 = 2
  map contains 2! Return [0, 1]
```

### Solution 2 (Alternative)

**Key Steps:**
1. First pass: Build complete map
2. Second pass: Check for complements
3. Ensure index != current index

**Use case:**
- When you need clear separation of phases
- Teaching or debugging purposes

## Go-Specific Features

### Map Syntax
```go
m := make(map[int]int)              // Create map
if idx, exists := m[complement]; exists {  // Check existence
    return []int{idx, i}
}
m[num] = i                          // Store value
```

### Range Loop
```go
for i, num := range nums {
    // i is index, num is value
}
```

### Slice Literals
```go
return []int{idx, i}  // Return slice of indices
```

## Testing

The `solution_test.go` includes comprehensive tests:
- Standard test cases
- Edge cases with duplicates
- Negative numbers
- Small arrays

Run with:
```bash
go test -v
```

## Performance Notes

- **Map allocation**: `make(map[int]int)` pre-allocates for efficiency
- **Range loop**: More efficient than index-based loop
- **Multiple return values**: Clean existence checking with `if idx, exists := ...`

Both solutions achieve O(n) time complexity. Solution 1 is preferred for production code.
