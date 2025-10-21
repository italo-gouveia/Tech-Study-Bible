# Chapter 7: Dijkstra's Algorithm — Grokking Algorithms

### Introduction
- Dijkstra finds the **cheapest** path (lowest total weight), not the shortest path (fewest edges)
- BFS finds shortest path in unweighted graphs; Dijkstra finds cheapest path in weighted graphs
- Real-world example: GPS navigation (fastest route, not necessarily shortest distance)

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

### Exercises
1. Implement Dijkstra's algorithm to find the cheapest path between two nodes.
2. Compare Dijkstra vs BFS on the same graph (weighted vs unweighted).
3. Handle edge cases: disconnected graphs, negative weights, cycles.
