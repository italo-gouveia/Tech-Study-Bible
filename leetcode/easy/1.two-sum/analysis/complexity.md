# Complexity Analysis for Two Sum

## Time Complexity

### Approach 1: One-Pass Hash Table
- **Best Case**: O(1)
  - Complement found immediately (second element)
- **Average Case**: O(n)
  - Complement found in middle of array
- **Worst Case**: O(n)
  - Complement found at last element or not found

**Breakdown:**
- Single loop: O(n) iterations
- HashMap operations (insert/lookup): O(1) average
- Total: O(n)

### Approach 2: Two-Pass Hash Table
- **Best Case**: O(n)
  - Constant time per operation, but must complete both passes
- **Average Case**: O(n)
- **Worst Case**: O(n)

**Breakdown:**
- First pass: O(n) for building map
- Second pass: O(n) for searching
- Total: O(2n) = O(n)

### Approach 3: Brute Force
- **Best Case**: O(1)
  - First pair is the solution
- **Average Case**: O(n²)
  - Solution found in middle of nested loops
- **Worst Case**: O(n²)
  - Solution found at last pair or not found

**Breakdown:**
- Outer loop: O(n) iterations
- Inner loop: O(n/2) average iterations
- Total: O(n²)

## Space Complexity

### Approach 1: One-Pass Hash Table
- **Auxiliary Space**: O(n)
  - HashMap stores at most n elements
- **Total Space**: O(n)

### Approach 2: Two-Pass Hash Table
- **Auxiliary Space**: O(n)
  - HashMap stores all n elements
- **Total Space**: O(n)

### Approach 3: Brute Force
- **Auxiliary Space**: O(1)
  - Only constant extra variables
- **Total Space**: O(1)

## Space-Time Trade-offs

| Approach | Time | Space | Trade-off |
|----------|------|-------|-----------|
| One-Pass Hash | O(n) | O(n) | Fast, uses extra memory |
| Two-Pass Hash | O(n) | O(n) | Clear logic, uses extra memory |
| Brute Force | O(n²) | O(1) | Slow, no extra memory |

## Hash Map Overhead

### Java HashMap
- **Average lookup**: O(1)
- **Average insert**: O(1)
- **Worst case (collisions)**: O(n)
- **Load factor**: 0.75 (default)
- **Buckets**: Rehashed when threshold exceeded

### Go map
- **Average lookup**: O(1)
- **Average insert**: O(1)
- **Worst case (collisions)**: O(n)
- **Dynamic growth**: Automatically resized
- **Memory**: More efficient than Java HashMap

## Input Size Impact

### Small Inputs (n ≤ 10)
- All approaches perform similarly
- Brute force may even be faster due to no hash overhead
- Memory difference negligible

### Medium Inputs (10 < n ≤ 1000)
- Hash-based approaches significantly faster
- Brute force begins to show performance issues
- Memory overhead acceptable

### Large Inputs (n > 1000)
- Hash-based approaches essential
- Brute force becomes impractical
- O(n²) time becomes noticeable

## Practical Benchmarks

### Hypothetical Performance (relative)
- **One-Pass Hash**: 100 units (baseline)
- **Two-Pass Hash**: 105-110 units (overhead)
- **Brute Force**: 1000-10000 units (depends on n)

### Memory Usage
- **HashMap**: ~20-40 bytes per element (Java)
- **map**: ~15-30 bytes per element (Go)
- Total overhead for n=1000: ~20-40 KB (acceptable)

## Conclusion

For production code, use the **One-Pass Hash Table** approach:
- ✅ Optimal O(n) time complexity
- ✅ Reasonable O(n) space complexity
- ✅ Single iteration for efficiency
- ✅ Modern best practice

The space-time trade-off is well worth it for realistic input sizes.
