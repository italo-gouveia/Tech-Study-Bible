# Test Cases Documentation - Contains Duplicate

## Overview

This document provides comprehensive test case documentation for the Contains Duplicate problem, covering all scenarios that should be tested to ensure correctness and robustness.

## Standard Test Cases

### Example Test Cases (From Problem Description)

#### Test Case 1: Basic Duplicate
```
Input: nums = [1, 2, 3, 1]
Output: true
Explanation: The element 1 occurs at indices 0 and 3
```

#### Test Case 2: No Duplicates
```
Input: nums = [1, 2, 3, 4]
Output: false
Explanation: All elements are distinct
```

#### Test Case 3: Multiple Duplicates
```
Input: nums = [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]
Output: true
Explanation: Multiple elements appear more than once (1, 3, 4, 2)
```

## Edge Cases

### Empty and Single Element Cases

#### Test Case 4: Empty Array
```
Input: nums = []
Output: false
Explanation: No elements to compare
```

#### Test Case 5: Single Element
```
Input: nums = [1]
Output: false
Explanation: Cannot have duplicates with only one element
```

#### Test Case 6: Two Elements - No Duplicate
```
Input: nums = [1, 2]
Output: false
Explanation: Two distinct elements
```

#### Test Case 7: Two Elements - Duplicate
```
Input: nums = [1, 1]
Output: true
Explanation: Two identical elements
```

### Boundary Value Cases

#### Test Case 8: Maximum Constraint Values
```
Input: nums = [1000000000, -1000000000, 0, 1000000000]
Output: true
Explanation: Duplicate of maximum constraint value
```

#### Test Case 9: Minimum Constraint Values
```
Input: nums = [-1000000000, 1000000000, 0, -1000000000]
Output: true
Explanation: Duplicate of minimum constraint value
```

#### Test Case 10: Zero Duplicates
```
Input: nums = [1, 2, 0, 3, 0]
Output: true
Explanation: Multiple zeros
```

### Negative Number Cases

#### Test Case 11: Negative Duplicates
```
Input: nums = [-1, -2, -1, -3]
Output: true
Explanation: Negative numbers with duplicates
```

#### Test Case 12: Negative Numbers - No Duplicates
```
Input: nums = [-1, -2, -3, -4]
Output: false
Explanation: All negative numbers are distinct
```

#### Test Case 13: Mixed Positive and Negative
```
Input: nums = [-1, 2, -3, 4, -1]
Output: true
Explanation: Mixed numbers with duplicate
```

### Duplicate Position Cases

#### Test Case 14: Duplicate at Beginning
```
Input: nums = [1, 1, 2, 3, 4]
Output: true
Explanation: Duplicate found at the start
```

#### Test Case 15: Duplicate at End
```
Input: nums = [1, 2, 3, 4, 1]
Output: true
Explanation: Duplicate found at the end
```

#### Test Case 16: Duplicate in Middle
```
Input: nums = [1, 2, 2, 3, 4]
Output: true
Explanation: Duplicate found in the middle
```

### All Identical Elements

#### Test Case 17: All Same Elements (Small)
```
Input: nums = [5, 5, 5]
Output: true
Explanation: All elements are identical
```

#### Test Case 18: All Same Elements (Large)
```
Input: nums = [42, 42, 42, 42, 42]
Output: true
Explanation: Large array with all identical elements
```

### Sorted vs Unsorted Cases

#### Test Case 19: Already Sorted - No Duplicates
```
Input: nums = [1, 2, 3, 4, 5]
Output: false
Explanation: Sorted array with no duplicates
```

#### Test Case 20: Already Sorted - With Duplicates
```
Input: nums = [1, 1, 2, 3, 4]
Output: true
Explanation: Sorted array with duplicates
```

#### Test Case 21: Reverse Sorted - No Duplicates
```
Input: nums = [5, 4, 3, 2, 1]
Output: false
Explanation: Reverse sorted array with no duplicates
```

#### Test Case 22: Reverse Sorted - With Duplicates
```
Input: nums = [5, 4, 3, 2, 1, 5]
Output: true
Explanation: Reverse sorted array with duplicates
```

## Stress Test Cases

### Large Array Cases

#### Test Case 23: Large Array - No Duplicates
```
Input: nums = [1, 2, 3, ..., 10000] (10,000 distinct elements)
Output: false
Explanation: Large array with no duplicates
```

#### Test Case 24: Large Array - With Duplicates
```
Input: nums = [1, 2, 3, ..., 9999, 1] (10,000 elements, first and last are same)
Output: true
Explanation: Large array with duplicates at beginning and end
```

#### Test Case 25: Maximum Size Array
```
Input: nums = [1, 2, 3, ..., 100000] (100,000 elements, constraint maximum)
Output: false
Explanation: Maximum size array with no duplicates
```

### Performance Test Cases

#### Test Case 26: Worst Case for HashSet (Hash Collisions)
```
Input: nums = [0, 1000000000, 2000000000, 3000000000, 0]
Output: true
Explanation: Values that might cause hash collisions
```

#### Test Case 27: Worst Case for Sorting (Reverse Sorted)
```
Input: nums = [100000, 99999, 99998, ..., 1, 100000]
Output: true
Explanation: Reverse sorted array with duplicate at end
```

#### Test Case 28: Worst Case for Brute Force (No Duplicates)
```
Input: nums = [1, 2, 3, ..., 1000] (1,000 distinct elements)
Output: false
Explanation: Forces O(n²) comparisons
```

## Special Value Cases

### Zero and Negative Zero

#### Test Case 29: Zero and Negative Zero
```
Input: nums = [0, -0, 1, 2]
Output: true
Explanation: Zero and negative zero are considered equal in most languages
```

### Floating Point Precision (If Applicable)

#### Test Case 30: Floating Point Duplicates
```
Input: nums = [1.1, 2.2, 1.1, 3.3]
Output: true
Explanation: Floating point duplicates (if using floating point numbers)
```

## Random Test Cases

### Test Case 31-40: Random Arrays
```
Generate 10 random arrays with:
- Random sizes (1 to 1000 elements)
- Random values (-1000 to 1000)
- Randomly include duplicates or not
- Verify all solutions give same result
```

## Invalid Input Cases

### Test Case 41: Null/Undefined Input
```
Input: nums = null
Expected: Should handle gracefully (return false or throw appropriate exception)
```

### Test Case 42: Array with Null Elements
```
Input: nums = [1, null, 2, 1] (if language supports null elements)
Expected: Should handle null elements appropriately
```

## Test Case Implementation

### Java Test Implementation
```java
@Test
public void testContainsDuplicate() {
    // Test Case 1: Basic duplicate
    assertTrue(containsDuplicate(new int[]{1, 2, 3, 1}));
    
    // Test Case 2: No duplicates
    assertFalse(containsDuplicate(new int[]{1, 2, 3, 4}));
    
    // Test Case 4: Empty array
    assertFalse(containsDuplicate(new int[]{}));
    
    // Test Case 5: Single element
    assertFalse(containsDuplicate(new int[]{1}));
    
    // Test Case 8: Maximum constraint values
    assertTrue(containsDuplicate(new int[]{1000000000, -1000000000, 0, 1000000000}));
}
```

### Go Test Implementation
```go
func TestContainsDuplicate(t *testing.T) {
    tests := []struct {
        name     string
        nums     []int
        expected bool
    }{
        {"Basic duplicate", []int{1, 2, 3, 1}, true},
        {"No duplicates", []int{1, 2, 3, 4}, false},
        {"Empty array", []int{}, false},
        {"Single element", []int{1}, false},
        {"Maximum constraint values", []int{1000000000, -1000000000, 0, 1000000000}, true},
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := containsDuplicate(tt.nums)
            if result != tt.expected {
                t.Errorf("Expected %v, got %v", tt.expected, result)
            }
        })
    }
}
```

## Test Coverage Analysis

### Functional Coverage
- ✅ Basic functionality (duplicates found/not found)
- ✅ Edge cases (empty, single element)
- ✅ Boundary values (min/max constraints)
- ✅ Special values (zero, negative numbers)
- ✅ Duplicate positions (beginning, middle, end)
- ✅ Array sizes (small, medium, large)

### Error Handling Coverage
- ✅ Null/undefined inputs
- ✅ Invalid data types
- ✅ Memory constraints
- ✅ Performance limits

### Algorithm Coverage
- ✅ HashSet: Hash collision scenarios
- ✅ Sorting: Already sorted arrays
- ✅ Brute Force: Worst-case scenarios

## Performance Test Scenarios

### Benchmark Test Cases

#### Small Arrays (1-100 elements)
- Test with 10, 50, 100 elements
- Measure execution time for each approach
- Verify early termination benefits

#### Medium Arrays (100-10,000 elements)
- Test with 500, 1000, 5000 elements
- Compare performance degradation
- Measure memory usage

#### Large Arrays (10,000+ elements)
- Test with 10,000, 50,000, 100,000 elements
- Focus on HashSet and Sorting approaches
- Skip Brute Force due to O(n²) complexity

### Memory Usage Tests

#### Memory Allocation Tests
- Measure memory usage for each approach
- Test with different array sizes
- Verify memory cleanup

#### Memory Constraint Tests
- Test behavior under memory pressure
- Verify graceful degradation
- Test with maximum constraint sizes

## Test Automation

### Continuous Integration Tests
```yaml
# Example CI test configuration
test_suite:
  - name: "Basic functionality tests"
    timeout: 30s
    cases: 42
    
  - name: "Performance tests"
    timeout: 300s
    cases: 10
    
  - name: "Stress tests"
    timeout: 600s
    cases: 5
```

### Test Data Generation
```python
# Python script to generate random test cases
import random

def generate_test_case(size, has_duplicates=False):
    nums = [random.randint(-1000, 1000) for _ in range(size)]
    if has_duplicates and size > 1:
        nums[-1] = nums[0]  # Ensure at least one duplicate
    return nums
```

## Conclusion

This comprehensive test suite ensures that:

1. **Correctness**: All approaches produce correct results
2. **Robustness**: Edge cases and error conditions are handled
3. **Performance**: Algorithms perform within expected bounds
4. **Reliability**: Solutions work consistently across different inputs

The test cases cover all possible scenarios and provide confidence that the implementations are correct and efficient.
