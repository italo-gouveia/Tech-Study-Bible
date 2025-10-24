# Chapter 7: Dijkstra's Algorithm — Grokking Algorithms

### Introduction
- Dijkstra finds the **cheapest** path (lowest total weight), not the shortest path (fewest edges)
- BFS finds shortest path in unweighted graphs; Dijkstra finds cheapest path in weighted graphs
- Real-world example: GPS navigation (fastest route, not necessarily shortest distance)

## 🎯 Simple Explanation (Like for a 5-Year-Old)

Imagine you're in a city and want to go from your house to school spending the least amount of money on bus tickets. Each bus costs a different amount for each part of the journey. Dijkstra's algorithm is like a very smart GPS that always chooses the path that will cost you the least money!

**The golden rule**: Always take the cheapest bus first, even if it doesn't seem like the most direct path!

### Graph Terminology
- **Weighted Graph**: edges have numbers (weights/costs) associated with them
  - Also called "valued graph"
- **Unweighted Graph**: edges have no weights
  - Also called "non-valued graph"
- **Cycles**: paths that start and end at the same node
  - Dijkstra works on: acyclic graphs OR graphs with positive-weight cycles only
  - Negative weights break Dijkstra (use Bellman-Ford instead)

### Dijkstra's Algorithm (High-Level)
1. Start at source node, cost = 0
2. For each iteration:
   - Pick the unvisited node with the **cheapest** known cost
   - Update costs to all its neighbors
   - Mark current node as visited
3. Continue until all nodes processed

## 📊 Step-by-Step Graphical Example

Let's solve this simple graph: we want to go from point **A** to point **E** spending the least amount of money possible.

### Initial Graph:
```
    A --6-- B --3-- E
    |       |       |
    2       4       1
    |       |       |
    C --1-- D --5-- F
```

### Step 1: We start at A (cost = 0)
```
Known costs:
A: 0 ✅ (visited)
B: ∞
C: ∞
D: ∞
E: ∞
F: ∞

Neighbors of A:
- A → B: cost = 6
- A → C: cost = 2

We update the costs:
A: 0 ✅
B: 6
C: 2
D: ∞
E: ∞
F: ∞
```

### Step 2: We choose C (lowest unvisited cost = 2)
```
Known costs:
A: 0 ✅
B: 6
C: 2 ✅ (visited)
D: ∞
E: ∞
F: ∞

Neighbors of C:
- C → D: cost = 1

Path A → C → D: 2 + 1 = 3

We update the costs:
A: 0 ✅
B: 6
C: 2 ✅
D: 3
E: ∞
F: ∞
```

### Step 3: We choose D (lowest unvisited cost = 3)
```
Known costs:
A: 0 ✅
B: 6
C: 2 ✅
D: 3 ✅ (visited)
E: ∞
F: ∞

Neighbors of D:
- D → B: cost = 4
- D → F: cost = 5

Path A → C → D → B: 3 + 4 = 7
Path A → C → D → F: 3 + 5 = 8

But B already has cost 6 (A → B direct), which is less than 7
We only update F:
A: 0 ✅
B: 6
C: 2 ✅
D: 3 ✅
E: ∞
F: 8
```

### Step 4: We choose B (lowest unvisited cost = 6)
```
Known costs:
A: 0 ✅
B: 6 ✅ (visited)
C: 2 ✅
D: 3 ✅
E: ∞
F: 8

Neighbors of B:
- B → E: cost = 3

Path A → B → E: 6 + 3 = 9

We update E:
A: 0 ✅
B: 6 ✅
C: 2 ✅
D: 3 ✅
E: 9
F: 8
```

### Step 5: We choose F (lowest unvisited cost = 8)
```
Known costs:
A: 0 ✅
B: 6 ✅
C: 2 ✅
D: 3 ✅
E: 9
F: 8 ✅ (visited)

Neighbors of F:
- F → E: cost = 1

Path A → C → D → F → E: 8 + 1 = 9

E already has cost 9, so we don't change anything.
```

### Step 6: We choose E (last node)
```
Known costs:
A: 0 ✅
B: 6 ✅
C: 2 ✅
D: 3 ✅
E: 9 ✅ (visited)
F: 8 ✅
```

### 🎉 Final Result:
**Cheapest path from A to E: A → B → E**
**Total cost: 9**

### Verification of Possible Paths:
1. A → B → E: 6 + 3 = **9** ✅ (lowest)
2. A → C → D → B → E: 2 + 1 + 4 + 3 = 10
3. A → C → D → F → E: 2 + 1 + 5 + 1 = 9 ✅ (same cost, alternative path)

### Key Insight: "Cheapest First"
- Always process the node with the lowest total cost first
- This guarantees we find the optimal path (greedy approach)
- Uses a priority queue (min-heap) to efficiently find the cheapest node

### Complexity
- Time: O((V + E) log V) with binary heap
- Space: O(V) for distances and heap

### Example Use Cases
- GPS navigation (fastest route)
- Network routing (lowest latency)
- Game AI (cheapest path to target)
- Resource allocation (minimum cost)

### Limitations
- Only works with non-negative weights
- For negative weights, use Bellman-Ford algorithm
- Not suitable for graphs with negative cycles

### In-Repo References
- Java graph implementations:
  - `projects/data-structures/java/datastructures-java/src/graphs/`

## 🏋️ Practical Exercise: Minimum Path Verification

### Problem 1: Find the Cheapest Path
Given the graph below, find the cheapest path from point **Start** to **End**.

```
    Start --4-- A --2-- B --1-- End
       |        |       |
       2        3       6
       |        |       |
       C --5-- D --3-- E
```

**Instructions:**
1. Execute Dijkstra's algorithm step by step
2. Note the costs at each stage
3. Identify the final path
4. Calculate the total cost

### Problem 2: Multiple Destinations
In the same graph, find the cheapest paths from **Start** to all other points.

### Problem 3: Manual Verification
Verify if your answer is correct by calculating all possible paths from Start to End:
- Start → A → B → End
- Start → C → D → A → B → End
- Start → C → D → E → B → End
- And others...

### Solutions:
<details>
<summary>Click to see the answers</summary>

**Problem 1 - Answer:**
- Path: Start → A → B → End
- Cost: 4 + 2 + 1 = **7**

**Problem 2 - Minimum costs from Start to all points:**
- Start: 0
- A: 4 (Start → A)
- B: 6 (Start → A → B)
- C: 2 (Start → C)
- D: 7 (Start → C → D)
- E: 10 (Start → C → D → E)
- End: 7 (Start → A → B → End)

</details>

### Advanced Exercises
1. Implement Dijkstra's algorithm to find the cheapest path between two nodes.
2. Compare Dijkstra vs BFS on the same graph (weighted vs unweighted).
3. Handle edge cases: disconnected graphs, negative weights, cycles.
4. **Challenge**: Modify the algorithm to also return the path (not just the cost).
5. **Challenge**: Implement a version that finds paths to multiple destinations simultaneously.
