# Test Cases Analysis - Plus Minus

## Standard Test Cases

### Example 1: Mixed Values
**Input:**
```
5
1 1 0 -1 -1
```

**Expected Output:**
```
0.400000
0.400000
0.200000
```

**Explanation:**
- Positive: 2 elements (1, 1) → 2/5 = 0.4
- Negative: 2 elements (-1, -1) → 2/5 = 0.4  
- Zero: 1 element (0) → 1/5 = 0.2

### Example 2: HackerRank Sample
**Input:**
```
6
-4 3 -9 0 4 1
```

**Expected Output:**
```
0.500000
0.333333
0.166667
```

**Explanation:**
- Positive: 3 elements (3, 4, 1) → 3/6 = 0.5
- Negative: 2 elements (-4, -9) → 2/6 = 0.333333
- Zero: 1 element (0) → 1/6 = 0.166667

## Edge Cases

### Case 1: All Positive Numbers
**Input:**
```
5
1 2 3 4 5
```

**Expected Output:**
```
1.000000
0.000000
0.000000
```

**Explanation:**
- All elements are positive
- No negative or zero elements

### Case 2: All Negative Numbers
**Input:**
```
4
-1 -2 -3 -4
```

**Expected Output:**
```
0.000000
1.000000
0.000000
```

**Explanation:**
- All elements are negative
- No positive or zero elements

### Case 3: All Zeros
**Input:**
```
3
0 0 0
```

**Expected Output:**
```
0.000000
0.000000
1.000000
```

**Explanation:**
- All elements are zero
- No positive or negative elements

### Case 4: Single Element - Positive
**Input:**
```
1
5
```

**Expected Output:**
```
1.000000
0.000000
0.000000
```

### Case 5: Single Element - Negative
**Input:**
```
1
-3
```

**Expected Output:**
```
0.000000
1.000000
0.000000
```

### Case 6: Single Element - Zero
**Input:**
```
1
0
```

**Expected Output:**
```
0.000000
0.000000
1.000000
```

## Boundary Test Cases

### Case 7: Maximum Constraint Values
**Input:**
```
100
100 100 100 ... 100 -100 -100 -100 ... -100
```
*(50 positive 100s, 50 negative -100s)*

**Expected Output:**
```
0.500000
0.500000
0.000000
```

### Case 8: Minimum Array Size
**Input:**
```
1
0
```

**Expected Output:**
```
0.000000
0.000000
1.000000
```

### Case 9: Boundary Values
**Input:**
```
5
1 -1 0 100 -100
```

**Expected Output:**
```
0.400000
0.400000
0.200000
```

## Precision Test Cases

### Case 10: Precision Requirements
**Input:**
```
3
1 0 0
```

**Expected Output:**
```
0.333333
0.000000
0.666667
```

**Explanation:**
- Positive: 1/3 = 0.333333 (exactly 6 decimal places)
- Negative: 0/3 = 0.000000
- Zero: 2/3 = 0.666667

### Case 11: Rounding Test
**Input:**
```
7
1 1 1 1 1 1 0
```

**Expected Output:**
```
0.857143
0.000000
0.142857
```

**Explanation:**
- Positive: 6/7 = 0.857143
- Zero: 1/7 = 0.142857

## Performance Test Cases

### Case 12: Large Array (Performance Test)
**Input:**
```
1000
[1000 random integers from -100 to 100]
```

**Expected Output:**
```
[Varies based on random distribution]
```

**Purpose:** Test performance with maximum constraint size

### Case 13: Repeated Values
**Input:**
```
10
5 5 5 5 5 -3 -3 -3 0 0
```

**Expected Output:**
```
0.500000
0.300000
0.200000
```

## Invalid Input Cases

### Case 14: Empty Array (Edge Case)
**Input:**
```
0
```

**Expected Output:**
```
[Undefined behavior - problem constraints specify n >= 1]
```

### Case 15: Out of Range Values
**Input:**
```
3
101 -101 0
```

**Expected Output:**
```
[Undefined behavior - problem constraints specify -100 <= arr[i] <= 100]
```

## Test Case Categories

### Functional Tests
- **Basic functionality**: Examples 1 and 2
- **Edge cases**: Cases 1-6 (single elements, all same type)
- **Boundary conditions**: Cases 7-9 (constraint limits)

### Precision Tests
- **Decimal precision**: Cases 10-11 (exact 6 decimal places)
- **Rounding behavior**: Verify correct rounding to 6 places

### Performance Tests
- **Large inputs**: Case 12 (1000 elements)
- **Repeated patterns**: Case 13 (efficiency with duplicates)

### Stress Tests
- **Maximum constraints**: All boundary values
- **Random distributions**: Various input patterns

## Test Case Validation

### Output Format Validation
1. **Decimal places**: Must be exactly 6 decimal places
2. **Line separation**: Each ratio on a new line
3. **No extra spaces**: Clean output format
4. **No trailing zeros**: Proper formatting (e.g., 0.500000 not 0.5000000)

### Mathematical Validation
1. **Sum of ratios**: Should equal 1.000000 (with rounding)
2. **Count consistency**: Sum of counts should equal array length
3. **Precision accuracy**: Ratios should be mathematically correct

### Performance Validation
1. **Execution time**: Should complete within reasonable time
2. **Memory usage**: Should use constant space O(1)
3. **Scalability**: Performance should scale linearly with input size

## Automated Testing Strategy

### Unit Tests
```go
func TestPlusMinus(t *testing.T) {
    testCases := []struct {
        input    []int32
        expected []string
    }{
        {[]int32{1, 1, 0, -1, -1}, []string{"0.400000", "0.400000", "0.200000"}},
        {[]int32{-4, 3, -9, 0, 4, 1}, []string{"0.500000", "0.333333", "0.166667"}},
        // ... more test cases
    }
    
    for _, tc := range testCases {
        // Test implementation
    }
}
```

### Performance Tests
```go
func BenchmarkPlusMinus(b *testing.B) {
    arr := make([]int32, 1000)
    // Initialize test data
    
    b.ResetTimer()
    for i := 0; i < b.N; i++ {
        plusMinus(arr)
    }
}
```

### Edge Case Tests
```go
func TestEdgeCases(t *testing.T) {
    // Test single elements
    // Test all positive/negative/zero
    // Test boundary values
    // Test maximum constraints
}
```

## Conclusion

The test cases cover all critical scenarios for the Plus Minus problem:

1. **Standard cases**: Basic functionality with mixed values
2. **Edge cases**: Boundary conditions and special inputs
3. **Precision cases**: Decimal formatting requirements
4. **Performance cases**: Large inputs and efficiency validation
5. **Error cases**: Invalid inputs and constraint violations

This comprehensive test suite ensures the solution works correctly across all possible scenarios and meets the problem requirements for precision, performance, and correctness.
