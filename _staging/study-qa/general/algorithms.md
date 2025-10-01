# Algorithms - Study Q&A

## Time and Space Complexity

### Q: What is Big O notation and why is it important?
**A:** Big O notation describes the worst-case time/space complexity of an algorithm:

- **O(1)**: Constant time - accessing array element
- **O(log n)**: Logarithmic - binary search
- **O(n)**: Linear - linear search
- **O(n log n)**: Linearithmic - merge sort, heap sort
- **O(n²)**: Quadratic - bubble sort, selection sort
- **O(2ⁿ)**: Exponential - recursive Fibonacci

```java
// O(1) - Constant time
int getFirst(int[] arr) {
    return arr[0]; // single operation
}

// O(n) - Linear time
boolean contains(int[] arr, int target) {
    for (int num : arr) { // iterate through all elements
        if (num == target) return true;
    }
    return false;
}

// O(n²) - Quadratic time
void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {        // n iterations
        for (int j = 0; j < arr.length - 1; j++) { // n iterations
            if (arr[j] > arr[j + 1]) {
                swap(arr, j, j + 1);
            }
        }
    }
}
```

### Q: How do you analyze algorithm complexity?
**A:** Steps to analyze:
1. **Identify the input size** (n)
2. **Count operations** in terms of n
3. **Consider worst-case scenario**
4. **Drop constants and lower-order terms**
5. **Express in Big O notation**

#### Example 1: Linear Search
```go
func findMax(arr []int) int {
    max := arr[0]                    // 1 operation
    for i := 1; i < len(arr); i++ {  // n-1 iterations
        if arr[i] > max {            // 1 operation per iteration
            max = arr[i]             // 1 operation (worst case)
        }
    }
    return max                       // 1 operation
}
// Total: 1 + (n-1) * 2 + 1 = 2n operations
// Complexity: O(n)
```

#### Example 2: Nested Loops
```go
func findPairs(arr []int, target int) int {
    count := 0                       // 1 operation
    for i := 0; i < len(arr); i++ {  // n iterations
        for j := i + 1; j < len(arr); j++ { // (n-1) + (n-2) + ... + 1 = n(n-1)/2
            if arr[i] + arr[j] == target {  // 1 operation per iteration
                count++                     // 1 operation (worst case)
            }
        }
    }
    return count                     // 1 operation
}
// Total: 1 + n(n-1)/2 * 2 + 1 = n² - n + 2
// Complexity: O(n²)
```

#### Example 3: Logarithmic Complexity
```go
func binarySearch(arr []int, target int) int {
    left, right := 0, len(arr)-1     // 2 operations
    for left <= right {              // log₂(n) iterations (worst case)
        mid := left + (right-left)/2 // 3 operations per iteration
        if arr[mid] == target {      // 1 operation per iteration
            return mid               // 1 operation (best case)
        } else if arr[mid] < target { // 1 operation per iteration
            left = mid + 1           // 1 operation per iteration
        } else {                     // 1 operation per iteration
            right = mid - 1          // 1 operation per iteration
        }
    }
    return -1                        // 1 operation
}
// Total: 2 + log₂(n) * 6 + 1 = 6*log₂(n) + 3
// Complexity: O(log n)
```

#### Example 4: Recursive Algorithm
```go
func fibonacci(n int) int {
    if n <= 1 {                      // 1 operation
        return n                     // 1 operation
    }
    return fibonacci(n-1) + fibonacci(n-2) // 2 recursive calls
}
// Each call creates 2 more calls until base case
// Total calls: 2^n (exponential)
// Complexity: O(2^n)
```

#### Example 5: Space Complexity Analysis
```go
func mergeSort(arr []int) []int {
    if len(arr) <= 1 {               // 1 operation
        return arr                   // O(1) space
    }
    
    mid := len(arr) / 2              // 1 operation
    left := mergeSort(arr[:mid])     // O(n/2) space + O(n/2) time
    right := mergeSort(arr[mid:])    // O(n/2) space + O(n/2) time
    
    return merge(left, right)        // O(n) space + O(n) time
}

func merge(left, right []int) []int {
    result := make([]int, len(left)+len(right)) // O(n) space
    i, j, k := 0, 0, 0                          // 3 operations
    
    for i < len(left) && j < len(right) {       // O(n) iterations
        if left[i] <= right[j] {                // 1 operation
            result[k] = left[i]                 // 1 operation
            i++                                 // 1 operation
        } else {                                // 1 operation
            result[k] = right[j]                // 1 operation
            j++                                 // 1 operation
        }
        k++                                     // 1 operation
    }
    
    // Copy remaining elements: O(n) operations
    copy(result[k:], left[i:])
    copy(result[k:], right[j:])
    
    return result
}
// Time: T(n) = 2T(n/2) + O(n) = O(n log n)
// Space: O(n) for result arrays + O(log n) for recursion stack = O(n)
```

#### Example 6: Amortized Analysis
```go
type DynamicArray struct {
    data []int
    size int
    capacity int
}

func (da *DynamicArray) append(val int) {
    if da.size >= da.capacity {      // 1 operation
        // Resize: double capacity
        newCapacity := da.capacity * 2  // 1 operation
        newData := make([]int, newCapacity) // O(capacity) space
        copy(newData, da.data)        // O(capacity) time
        da.data = newData             // 1 operation
        da.capacity = newCapacity     // 1 operation
    }
    da.data[da.size] = val           // 1 operation
    da.size++                        // 1 operation
}
// Most operations: O(1)
// Resize operations: O(n) but happen rarely
// Amortized complexity: O(1)
```

#### Example 7: Multiple Variables
```go
func matrixMultiply(a, b [][]int) [][]int {
    n := len(a)                      // 1 operation
    result := make([][]int, n)       // O(n) space
    for i := 0; i < n; i++ {         // n iterations
        result[i] = make([]int, n)   // O(n) space per iteration
        for j := 0; j < n; j++ {     // n iterations per i
            for k := 0; k < n; k++ { // n iterations per j
                result[i][j] += a[i][k] * b[k][j] // 3 operations
            }
        }
    }
    return result
}
// Total: 1 + n * (n + n * n * 3) = 1 + n² + 3n³
// Complexity: O(n³)
// Space: O(n²) for result matrix
```

#### Common Complexity Patterns
```go
// O(1) - Constant
func getFirst(arr []int) int {
    return arr[0]
}

// O(log n) - Logarithmic
func binarySearch(arr []int, target int) int {
    // Binary search implementation
}

// O(n) - Linear
func linearSearch(arr []int, target int) int {
    for i, val := range arr {
        if val == target {
            return i
        }
    }
    return -1
}

// O(n log n) - Linearithmic
func mergeSort(arr []int) []int {
    // Merge sort implementation
}

// O(n²) - Quadratic
func bubbleSort(arr []int) {
    for i := 0; i < len(arr); i++ {
        for j := 0; j < len(arr)-1; j++ {
            if arr[j] > arr[j+1] {
                arr[j], arr[j+1] = arr[j+1], arr[j]
            }
        }
    }
}

// O(2ⁿ) - Exponential
func fibonacci(n int) int {
    if n <= 1 {
        return n
    }
    return fibonacci(n-1) + fibonacci(n-2)
}
```

## Sorting Algorithms

### Q: Compare different sorting algorithms
**A:** Common sorting algorithms:

| Algorithm | Time (Best) | Time (Average) | Time (Worst) | Space | Stable |
|-----------|-------------|----------------|--------------|-------|--------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |

### Q: Implement Quick Sort
**A:** Divide and conquer algorithm:

```java
public void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);  // sort left subarray
        quickSort(arr, pivotIndex + 1, high); // sort right subarray
    }
}

private int partition(int[] arr, int low, int high) {
    int pivot = arr[high]; // choose last element as pivot
    int i = low - 1;       // index of smaller element
    
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return i + 1;
}
```

## Searching Algorithms

### Q: Compare linear search vs binary search
**A:**

**Linear Search:**
- Time: O(n)
- Space: O(1)
- Works on any array
- No sorting required

**Binary Search:**
- Time: O(log n)
- Space: O(1) iterative, O(log n) recursive
- Requires sorted array
- Much faster for large datasets

```go
// Linear Search - O(n)
func linearSearch(arr []int, target int) int {
    for i, val := range arr {
        if val == target {
            return i
        }
    }
    return -1
}

// Binary Search - O(log n)
func binarySearch(arr []int, target int) int {
    left, right := 0, len(arr)-1
    
    for left <= right {
        mid := left + (right-left)/2 // avoid overflow
        
        if arr[mid] == target {
            return mid
        } else if arr[mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return -1
}
```

## Dynamic Programming

### Q: What is dynamic programming?
**A:** DP solves problems by breaking them into overlapping subproblems and storing results:

**Key characteristics:**
1. **Optimal substructure**: Optimal solution contains optimal solutions to subproblems
2. **Overlapping subproblems**: Same subproblems solved multiple times
3. **Memoization**: Store results to avoid recomputation

### Q: Implement Fibonacci with DP
**A:** Classic DP example:

```java
// Naive recursive - O(2^n)
public int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n-1) + fibonacci(n-2);
}

// Top-down DP (memoization) - O(n)
public int fibonacciMemo(int n) {
    int[] memo = new int[n + 1];
    return fibonacciHelper(n, memo);
}

private int fibonacciHelper(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];
    
    memo[n] = fibonacciHelper(n-1, memo) + fibonacciHelper(n-2, memo);
    return memo[n];
}

// Bottom-up DP (tabulation) - O(n)
public int fibonacciDP(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

## Graph Algorithms

### Q: What are the main graph traversal algorithms?
**A:** Two fundamental approaches:

**Depth-First Search (DFS):**
- Uses stack (recursion or explicit stack)
- Goes deep before going wide
- Good for: path finding, cycle detection, topological sort

**Breadth-First Search (BFS):**
- Uses queue
- Goes wide before going deep
- Good for: shortest path (unweighted), level-order traversal

```go
// DFS - Recursive
func dfs(graph map[int][]int, start int, visited map[int]bool) {
    visited[start] = true
    fmt.Println(start)
    
    for _, neighbor := range graph[start] {
        if !visited[neighbor] {
            dfs(graph, neighbor, visited)
        }
    }
}

// BFS - Iterative with queue
func bfs(graph map[int][]int, start int) {
    visited := make(map[int]bool)
    queue := []int{start}
    visited[start] = true
    
    for len(queue) > 0 {
        node := queue[0]
        queue = queue[1:]
        fmt.Println(node)
        
        for _, neighbor := range graph[node] {
            if !visited[neighbor] {
                visited[neighbor] = true
                queue = append(queue, neighbor)
            }
        }
    }
}
```

### Q: How does Dijkstra's algorithm work?
**A:** Finds shortest path in weighted graph:

```java
public int[] dijkstra(int[][] graph, int start) {
    int n = graph.length;
    int[] dist = new int[n];
    boolean[] visited = new boolean[n];
    
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;
    
    for (int count = 0; count < n - 1; count++) {
        int u = minDistance(dist, visited);
        visited[u] = true;
        
        for (int v = 0; v < n; v++) {
            if (!visited[v] && graph[u][v] != 0 && 
                dist[u] != Integer.MAX_VALUE && 
                dist[u] + graph[u][v] < dist[v]) {
                dist[v] = dist[u] + graph[u][v];
            }
        }
    }
    return dist;
}
```

## Greedy Algorithms

### Q: What is a greedy algorithm?
**A:** Makes locally optimal choice at each step, hoping to find global optimum:

**Characteristics:**
- Makes choice that looks best at the moment
- Never reconsider previous choices
- May not always find optimal solution

**Examples:**
- Activity Selection Problem
- Fractional Knapsack
- Huffman Coding
- Minimum Spanning Tree (Kruskal's, Prim's)

```go
// Activity Selection Problem
type Activity struct {
    start, end int
}

func activitySelection(activities []Activity) []Activity {
    // Sort by end time
    sort.Slice(activities, func(i, j int) bool {
        return activities[i].end < activities[j].end
    })
    
    var result []Activity
    result = append(result, activities[0])
    lastEnd := activities[0].end
    
    for i := 1; i < len(activities); i++ {
        if activities[i].start >= lastEnd {
            result = append(result, activities[i])
            lastEnd = activities[i].end
        }
    }
    return result
}
```
