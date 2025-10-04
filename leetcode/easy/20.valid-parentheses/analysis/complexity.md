# Complexity Analysis - 20. Valid Parentheses

## Time Complexity
**O(n)** - where n is the length of the input string

### Analysis
- Single pass through the string: O(n)
- Each character processed once
- Stack operations (push/pop): O(1) per operation
- Map lookup for bracket matching: O(1) per operation

### Breakdown
```java
for (int i = 0; i < s.length(); i++) {  // O(n) iterations
    char c = s.charAt(i);               // O(1)
    if (c == '(' || c == '[' || c == '{') {  // O(1)
        st[++top] = c;                  // O(1)
    } else {
        if (top < 0) return false;      // O(1)
        char o = st[top--];             // O(1)
        if (mismatch) return false;     // O(1)
    }
}
return top == -1;                       // O(1)
```

**Total: O(n)**

## Space Complexity
**O(n)** - worst case when all characters are opening brackets

### Analysis
- Stack storage: O(n) in worst case
- Map for bracket matching: O(1) - constant size (3 pairs)
- No additional data structures

### Best Case
- All closing brackets first: O(1) space
- Example: "))]]}}"

### Average Case
- Balanced brackets: O(n/2) ≈ O(n)
- Example: "()[]{}"

### Worst Case
- All opening brackets: O(n) space
- Example: "((([[[{{{"

## Edge Cases Analysis

### 1. Empty String
```java
s = ""
// Time: O(1) - early return
// Space: O(1) - no stack needed
```

### 2. Single Character
```java
s = "("
// Time: O(1) - early return
// Space: O(1) - stack size 1
```

### 3. Odd Length
```java
s = "(()"
// Time: O(1) - early return
// Space: O(1) - no processing
```

### 4. All Opening Brackets
```java
s = "((([[[{{{"
// Time: O(n) - process all characters
// Space: O(n) - stack stores all characters
```

### 5. All Closing Brackets
```java
s = "))]]}}"
// Time: O(n) - process all characters
// Space: O(1) - stack remains empty
```

## Algorithm Efficiency

### Why O(n) is Optimal
- Must examine every character to validate
- Cannot skip any character
- No way to validate without full scan

### Space-Time Trade-offs
- **Time**: O(n) - optimal
- **Space**: O(n) - necessary for stack
- **Alternative**: Recursive approach also O(n) space (call stack)

## Comparison with Alternatives

### 1. Stack-based (Current)
- Time: O(n)
- Space: O(n)
- **Best for**: General case

### 2. Recursive
- Time: O(n)
- Space: O(n) - call stack
- **Best for**: Functional programming style

### 3. Counter-based (Invalid)
- Time: O(n)
- Space: O(1)
- **Problem**: Cannot handle nested brackets like "([)]"

## Performance Characteristics

### Input Size Impact
- **Small strings (n < 100)**: Constant factors dominate
- **Medium strings (100 < n < 1000)**: Linear growth visible
- **Large strings (n > 1000)**: Pure O(n) behavior

### Memory Access Pattern
- **Sequential**: Good cache locality
- **Stack operations**: LIFO pattern, cache-friendly
- **No random access**: Predictable memory usage

## Real-world Considerations

### Scalability
- Handles strings up to ~10^6 characters efficiently
- Memory usage scales linearly with input
- No exponential blowup

### Optimization Opportunities
- Early termination on odd length
- Pre-allocated stack capacity
- Branch prediction friendly (simple if-else)
