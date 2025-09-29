# Explanation:

### Class Definition:
**`Graph:`** The main class representing the graph data structure.

### Data Structure:
**`int[][] adjacencyMatrix:`** A 2D array where **`adjacencyMatrix[i][j]`** is 1 if there is an edge between vertex **`i`** and vertex **`j`**, and 0 otherwise.

### Constructor:
Initializes the adjacency matrix with zeros based on the number of vertices.

### Add Edge:
- **`addEdge(int vertex1, int vertex2):`** Adds an edge between two vertices by setting the corresponding entries in the matrix to 1. Since the graph is undirected, it sets both **`adjacencyMatrix[vertex1][vertex2]`** and **`adjacencyMatrix[vertex2][vertex1]`** to 1.

### Remove Edge:
- **`removeEdge(int vertex1, int vertex2):`** Removes an edge by setting the corresponding entries in the matrix to 0.

### Has Edge:
- **`hasEdge(int vertex1, int vertex2):`** Checks if there is an edge between two vertices by checking the value in the matrix.

### Display Matrix:
- **`displayMatrix():`** Prints the adjacency matrix to the console.

# When to Use:
- **Dense Graphs:** When the graph is dense (i.e., the number of edges is close to the maximum number of edges), an adjacency matrix is efficient because it allows for quick edge lookups.
- **Quick Edge Checks:** When you need to frequently check the presence or absence of edges, the constant-time complexity of checking an entry in the matrix is advantageous.
- **Algorithmic Simplicity:** For algorithms that require direct access to edge information (like Floyd-Warshall for shortest paths), an adjacency matrix can simplify the implementation.

# When Not to Use:
- **Sparse Graphs:** For very sparse graphs (i.e., where the number of edges is much less than the maximum number of edges), an adjacency matrix can be inefficient in terms of space. An adjacency list is typically more space-efficient in these cases.
- **Memory Constraints:** The adjacency matrix requires O(V^2) space, where V is the number of vertices. This can be impractical for graphs with a large number of vertices.