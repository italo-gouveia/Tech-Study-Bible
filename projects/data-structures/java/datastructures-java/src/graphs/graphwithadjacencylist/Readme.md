# Explanation:

### Class Definition:
- **Graph:** The main class representing the graph data structure.

### Data Structure:
- **Map<String, List<String>> adjacencyList:** A map where each key is a vertex and the value is a list of adjacent vertices.

### Constructor:
- Initializes the adjacency list with an empty HashMap.

### Add Vertex:
- **addVertex(String vertex):** Adds a vertex to the graph if it does not already exist.

### Add Edge:
- **addEdge(String vertex1, String vertex2):** Adds an edge between two vertices. If vertices don't exist, they are added. Since the graph is undirected, the edge is added to both vertices' adjacency lists.

### Remove Edge:
- **removeEdge(String vertex1, String vertex2):** Removes the edge between two vertices if it exists.

### Remove Vertex:
- **removeVertex(String vertex):** Removes a vertex and all its edges from the graph. This involves removing the vertex from the adjacency lists of all connected vertices.

### Get Vertices:
- **getVertices():** Returns a list of all vertices in the graph.

### Get Edges:
- **getEdges(String vertex):** Returns a list of all edges connected to a given vertex.

# When to Use:
- **Network Analysis:** To model and analyze networks like social networks, computer networks, or transportation systems.
- **Pathfinding Algorithms:** For algorithms like Dijkstra's or A* to find the shortest path between nodes.
- **Graph-Based Problems:** When solving problems involving relationships and connections, such as finding cycles or checking connectivity.

# When Not to Use:
- **Simple List/Array Data:** If you only need simple linear data structures like arrays or lists without complex relationships, a graph may be unnecessary.
- **Sparse Connections:** For very sparse graphs where most vertices are not connected, an adjacency matrix might be less efficient than an adjacency list.


# Vertices (Nodes)
**Definition:**
- Vertices (also known as nodes) are the fundamental units of a graph. Each vertex represents an entity or a point in the graph.

**Characteristics:**
- **Label/Identifier:** Each vertex is usually labeled or identified by a unique key. This can be a number, a string, or any other identifier.
- **Attributes:** Vertices can have attributes or properties associated with them. For example, in a social network graph, a vertex representing a person might have attributes like name, age, or email.

**Examples:**
- In a social network graph, vertices represent users.
- In a city map graph, vertices represent locations or intersections.

**Visualization:**
- Vertices are often depicted as circles or dots in graphical representations of graphs.

# Edges (Links)
**Definition:**
- **Edges** (also known as links or arcs) are the connections between vertices. They represent the relationships or paths between the entities denoted by the vertices.

**Characteristics:**
- **Direction:** Edges can be either directed or undirected:
  - **Directed Edge:** Indicates a one-way relationship, where the connection has a direction from one vertex to another. For example, in a workflow graph, an edge from task A to task B means A must be completed before B. 
  - **Undirected Edge**: Indicates a two-way or bidirectional relationship, where the connection does not have a direction. For example, in an undirected social network graph, a friendship between two people is mutual.

**Weight:** Edges can have weights or costs associated with them. This is useful in scenarios where the connection has a quantifiable value, such as distance, time, or cost. For example, in a graph representing a network of cities, the weight might represent the distance between cities.

**Examples:**
- In a social network graph, an edge might represent a friendship between two users.
- In a city map graph, an edge might represent a road connecting two intersections, with the weight representing the distance or travel time.

**Visualization:**
- Edges are typically represented as lines connecting the circles (vertices) in graphical representations of graphs.

# Graph Types Based on Edges
1. **Undirected Graph:**
- **Definition:** A graph where edges do not have a direction. If there is an edge between vertex A and vertex B, you can traverse from A to B and from B to A.
- **Example:** A friendship graph where friendships are mutual.
2. **Directed Graph (Digraph):**
- **Definition:** A graph where edges have a direction. An edge from vertex A to vertex B is not necessarily the same as an edge from B to A.
- **Example:** A web page link graph where links point from one page to another.
3. **Weighted Graph:**
- **Definition:** A graph where edges have weights or costs associated with them. Weights can represent various metrics like distance, time, or cost.
- **Example:** A road network where edges represent roads with distances as weights.
4. **Unweighted Graph:**
- **Definition:** A graph where edges do not have weights. The presence of an edge is the only information.
- **Example:** A simple social network graph where only the existence of connections is relevant.

# Graph Representations
Graphs can be represented in several ways in computer science:

1. **Adjacency List:**
- **Description:** Each vertex has a list of its adjacent vertices. Efficient for sparse graphs.
- **Implementation:** A common way to implement this is using a map where the key is a vertex and the value is a list of adjacent vertices.
2. **Adjacency Matrix:**
- **Description:** A 2D matrix where each cell (i, j) represents the presence or weight of an edge between vertices i and j. Efficient for dense graphs.
- **Implementation:** A 2D array where each cell indicates the connection between two vertices (and optionally the weight).
3. **Edge List:**
- **Description:** A list of all edges in the graph, where each edge is represented by a pair of vertices (and optionally a weight).
- **Implementation:** A list or array where each entry is a pair (or triplet if weighted) of vertices.

# Use Cases
- **Social Networks:** Vertices represent users, and edges represent friendships or connections.
- **Web Navigation:** Vertices represent web pages, and edges represent hyperlinks between them.
- **Routing Algorithms:** In computer networks or transportation maps, vertices represent locations or nodes, and edges represent paths with weights indicating distances or costs.

# Summary
- **Vertices** are the fundamental entities or points in a graph.
- **Edges** are the connections or relationships between vertices.
- The type of graph (directed, undirected, weighted, unweighted) affects how you interpret the connections and relationships in the graph.
- Graphs can be represented in different ways depending on the use case and requirements, such as adjacency lists, adjacency matrices, or edge lists.

This detailed breakdown should help you understand the basic concepts of vertices and edges, and how they are used in various applications of graph theory.