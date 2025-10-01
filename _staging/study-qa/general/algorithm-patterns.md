# Algorithm Patterns - Study Q&A

## Common Algorithm Patterns

### Q: What are the most common algorithm patterns in interviews?
**A:** The key patterns to master:

1. **Hashing vs Sorting**: When to use each approach
2. **Two Pointers / Sliding Window**: For array/string problems
3. **Prefix Sums**: For range queries and subarray problems
4. **Heaps / Priority Queues**: For top-K and streaming problems
5. **Balanced Trees / Maps**: For ordered operations
6. **Dynamic Programming**: For optimization problems
7. **Graph Traversal**: DFS/BFS for connectivity and path problems

### Q: When to use hashing vs sorting?
**A:** 

**Use Hashing when:**
- Need O(1) average lookup time
- Don't need ordered data
- Memory is not a constraint
- Frequent insertions/deletions

**Use Sorting when:**
- Need ordered data
- Memory is constrained
- Can afford O(n log n) preprocessing
- Need to process data in order

```java
// Hashing approach - O(n) time, O(n) space
public boolean hasDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (seen.contains(num)) return true;
        seen.add(num);
    }
    return false;
}

// Sorting approach - O(n log n) time, O(1) space
public boolean hasDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i-1]) return true;
    }
    return false;
}
```

## Two Pointers and Sliding Window

### Q: What's the difference between two pointers and sliding window?
**A:**

**Two Pointers:**
- Two indices moving in same direction or opposite directions
- Used for: palindrome checking, pair sum, removing duplicates
- Time: Usually O(n), Space: O(1)

**Sliding Window:**
- Fixed or variable size window moving through array/string
- Used for: substring problems, subarray sum, character frequency
- Time: Usually O(n), Space: O(k) where k is window size

```java
// Two Pointers - Valid Palindrome
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

// Sliding Window - Longest Substring Without Repeating Characters
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0, maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        while (window.contains(s.charAt(right))) {
            window.remove(s.charAt(left++));
        }
        window.add(s.charAt(right));
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

## Prefix Sums

### Q: What are prefix sums and when to use them?
**A:** Prefix sums precompute cumulative sums for efficient range queries:

**Use cases:**
- Range sum queries
- Subarray problems
- Cumulative calculations
- Difference arrays

**Time complexity:**
- Preprocessing: O(n)
- Query: O(1)
- Space: O(n)

```java
// Prefix Sum Array
public class PrefixSum {
    private int[] prefix;
    
    public PrefixSum(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
    
    // Get sum from index i to j (inclusive)
    public int rangeSum(int i, int j) {
        return prefix[j + 1] - prefix[i];
    }
}

// Example: Subarray Sum Equals K
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> prefixCount = new HashMap<>();
    prefixCount.put(0, 1); // empty subarray
    int sum = 0, count = 0;
    
    for (int num : nums) {
        sum += num;
        count += prefixCount.getOrDefault(sum - k, 0);
        prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
    }
    return count;
}
```

## Heaps and Priority Queues

### Q: When to use heaps/priority queues?
**A:** Use heaps for:
- **Top-K problems**: Find K largest/smallest elements
- **Streaming data**: Process data as it arrives
- **Merge operations**: Merge multiple sorted sequences
- **Scheduling**: Priority-based task scheduling

```java
// Top K Frequent Elements
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
    
    // Min heap of size k
    PriorityQueue<Integer> heap = new PriorityQueue<>(
        (a, b) -> freq.get(a) - freq.get(b)
    );
    
    for (int num : freq.keySet()) {
        heap.offer(num);
        if (heap.size() > k) {
            heap.poll(); // remove least frequent
        }
    }
    
    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        result[i] = heap.poll();
    }
    return result;
}
```

## Big-O Analysis

### Q: How to analyze algorithm complexity?
**A:** Steps for complexity analysis:

1. **Identify input size** (usually n)
2. **Count operations** in terms of input size
3. **Consider worst-case scenario**
4. **Drop constants and lower-order terms**
5. **Express in Big-O notation**

### Q: What's the difference between average and worst-case complexity?
**A:**

**Average Case:**
- Expected performance over all possible inputs
- Often more practical for real-world usage
- Example: HashMap operations are O(1) average

**Worst Case:**
- Performance on the most challenging input
- Guarantees upper bound on performance
- Example: HashMap operations are O(n) worst-case

**Interview line**: *"This approach is O(n) with a HashMap (average). Worst-case degrades to O(n) on heavy collisions, but in Java 8+ bins treeify to O(log n) beyond a threshold."*

## Common Complexity Classes

### Q: What are the main complexity classes?
**A:**

| Complexity | Description | Examples |
|------------|-------------|----------|
| O(1) | Constant | Array access, HashMap lookup |
| O(log n) | Logarithmic | Binary search, balanced tree operations |
| O(n) | Linear | Linear search, single pass through array |
| O(n log n) | Linearithmic | Merge sort, heap sort |
| O(n²) | Quadratic | Bubble sort, nested loops |
| O(2ⁿ) | Exponential | Recursive Fibonacci, subset generation |
| O(n!) | Factorial | Permutations, traveling salesman |

### Q: How to optimize algorithm complexity?
**A:** Common optimization strategies:

1. **Use appropriate data structures**: HashMap for O(1) lookup, heap for priority
2. **Avoid redundant computations**: Memoization, caching
3. **Reduce nested loops**: Use two pointers, sliding window
4. **Sort when beneficial**: Enables binary search, two pointers
5. **Use mathematical properties**: Prefix sums, mathematical formulas

```java
// Before: O(n²) - nested loops
public boolean hasDuplicate(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] == nums[j]) return true;
        }
    }
    return false;
}

// After: O(n) - using HashSet
public boolean hasDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (!seen.add(num)) return true; // add returns false if already present
    }
    return false;
}
```

## Practice Problems by Pattern

### Q: What are good practice problems for each pattern?
**A:**

**Hashing:**
- Two Sum, Group Anagrams, Valid Anagram
- Longest Substring Without Repeating Characters

**Two Pointers:**
- Valid Palindrome, Two Sum (sorted array)
- Container With Most Water, Remove Duplicates

**Sliding Window:**
- Longest Substring Without Repeating Characters
- Minimum Window Substring, Longest Repeating Character Replacement

**Prefix Sums:**
- Subarray Sum Equals K, Range Sum Query
- Continuous Subarray Sum

**Heaps:**
- Top K Frequent Elements, Kth Largest Element
- Merge k Sorted Lists, Find Median from Data Stream

**Dynamic Programming:**
- Climbing Stairs, House Robber
- Longest Common Subsequence, Edit Distance
