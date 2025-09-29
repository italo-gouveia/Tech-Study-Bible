Explanation:
Class Definition:

Graph: The main class representing the graph data structure using an edge list.
Inner Class Edge:

Represents an edge with two vertices and an optional weight.
Constructor: Initializes the edge with two vertices and a weight.
Getters: Methods to retrieve vertex and weight information.
toString Method: Provides a string representation for easy printing.
Constructor:

Initializes the edges list to store all edges.
Add Edge:

addEdge(int vertex1, int vertex2, int weight): Adds an edge with a specified weight.
Overloaded method addEdge(int vertex1, int vertex2) for unweighted edges, defaulting the weight to 1.
Remove Edge:

removeEdge(int vertex1, int vertex2): Removes an edge between two vertices. The removeIf method is used to filter and remove the matching edge(s).
Get Edges:

getEdges(): Returns a copy of the list of edges.
Display Edges:

displayEdges(): Prints all edges to the console.
When to Use:
Sparse Graphs: When the graph is sparse (i.e., the number of edges is much less than the maximum number of edges), the edge list representation can be efficient in terms of space.
Simple Representation: When you need a straightforward representation of the graph where you only care about the edges.
Algorithmic Applications: For algorithms that work on edges, like Kruskal’s Minimum Spanning Tree (MST) algorithm.
When Not to Use:
Dense Graphs: For dense graphs where the number of edges is close to the maximum number, the edge list may become less efficient compared to an adjacency matrix or list.
Frequent Edge Lookups: If you frequently need to check the existence of an edge between two vertices, an adjacency matrix or adjacency list might be more efficient since they provide faster edge lookups.