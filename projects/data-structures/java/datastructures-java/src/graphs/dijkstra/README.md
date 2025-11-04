# Dijkstra's Algorithm Implementation

This implementation provides a complete Java solution for Dijkstra's shortest path algorithm, following the examples and exercises from Chapter 7 of "Grokking Algorithms".

## 🎯 Overview

Dijkstra's algorithm finds the **cheapest path** (lowest total weight) between vertices in a weighted graph, not the shortest path (fewest edges). It's perfect for scenarios like:
- GPS navigation (fastest route)
- Network routing (lowest latency)
- Game AI (cheapest path to target)
- Resource allocation (minimum cost)

## 📊 Algorithm Explanation

### Key Concept: "Cheapest First"
The algorithm always processes the node with the lowest total cost first, guaranteeing we find the optimal path using a greedy approach.

### Steps:
1. Start at source node with cost = 0
2. For each iteration:
   - Pick the unvisited node with the **cheapest** known cost
   - Update costs to all its neighbors
   - Mark current node as visited
3. Continue until all nodes processed

## 🏗️ Implementation Details

### Classes:
- **`DijkstraAlgorithm`**: Main class containing the algorithm implementation
- **`Edge`**: Inner class representing weighted edges
- **`Node`**: Inner class for priority queue nodes
- **`Main`**: Demonstration class with examples

### Key Methods:
- `addVertex(String vertex)`: Add a vertex to the graph
- `addEdge(String source, String destination, int weight)`: Add weighted edge
- `findShortestPath(String source, String destination)`: Find shortest path
- `reconstructPath(String source, String destination, Map<String, String> previous)`: Reconstruct the actual path

## 🚀 Usage Examples

### Example 1: Basic Usage
```java
DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();

// Add edges with weights
dijkstra.addEdge("A", "B", 6);
dijkstra.addEdge("A", "C", 2);
dijkstra.addEdge("B", "E", 3);

// Find shortest path
Map<String, Object> result = dijkstra.findShortestPath("A", "E");
int distance = (Integer) result.get("shortestDistance");
```

### Example 2: Path Reconstruction
```java
@SuppressWarnings("unchecked")
Map<String, String> previous = (Map<String, String>) result.get("previous");
List<String> path = dijkstra.reconstructPath("A", "E", previous);
System.out.println("Path: " + String.join(" → ", path));
```

## 📈 Complexity Analysis

- **Time Complexity**: O((V + E) log V) with binary heap
- **Space Complexity**: O(V) for distances and heap

Where:
- V = number of vertices
- E = number of edges

## 🎓 Examples Included

### 1. Chapter Example
Implements the detailed step-by-step example from the book:
```
A --6-- B --3-- E
|       |       |
2       4       1
|       |       |
C --1-- D --5-- F
```

### 2. Practical Exercise
Solves the exercise problem from the chapter:
```
Start --4-- A --2-- B --1-- End
   |        |       |
   2        3       6
   |        |       |
   C --5-- D --3-- E
```

### 3. Additional Complex Example
Demonstrates the algorithm on a more complex graph with multiple paths.

## 🏃‍♂️ How to Run

1. Compile the Java files:
```bash
javac *.java
```

2. Run the main class:
```bash
java Main
```

## ✅ Expected Output

The program will demonstrate:
- Graph structure visualization
- Shortest path calculations
- Path reconstruction
- Distance verification
- Multiple example scenarios

## 🔧 Key Features

- **Weighted Graph Support**: Handles graphs with weighted edges
- **Path Reconstruction**: Returns both distance and actual path
- **Early Termination**: Stops when destination is reached
- **Comprehensive Examples**: Includes all examples from the chapter
- **Clean Code Structure**: Well-documented and organized code

## 🎯 Learning Objectives

After studying this implementation, you'll understand:
- How Dijkstra's algorithm works step-by-step
- Why it uses a greedy approach
- How to implement it efficiently with priority queues
- How to reconstruct the actual shortest path
- How to verify results manually

## 📚 Related Concepts

- **BFS vs Dijkstra**: BFS finds shortest path (fewest edges), Dijkstra finds cheapest path (lowest weight)
- **Priority Queue**: Essential for efficient implementation
- **Greedy Algorithms**: Dijkstra is a classic greedy algorithm
- **Graph Theory**: Understanding weighted graphs and shortest paths

## ⚠️ Limitations

- Only works with **non-negative weights**
- For negative weights, use **Bellman-Ford algorithm**
- Not suitable for graphs with negative cycles
