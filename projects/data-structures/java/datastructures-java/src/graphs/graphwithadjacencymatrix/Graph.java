package graphs.graphwithadjacencymatrix;

public class Graph {
    // Number of vertices in the graph
    private final int numVertices;
    // 2D array to represent the adjacency matrix
    private final int [][] adjacencyMatrix;

    // Constructor to initialize the graph with a given number of vertices
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        // Initialize the adjacency matrix with zeros
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    // Method to add an edge between two vertices
    public void addEdge(int vertex1, int vertex2) {
        // Check for valid vertex indices
        if (vertex1 < 0 || vertex1 >= numVertices || vertex2 < 0 || vertex2 >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }

        // Since the graph is undirected, we set the value for both (vertex1, vertex2) and (vertex2, vertex1)
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1;
    }

    // Method to remove an edge between two vertices
    public void removeEdge(int vertex1, int vertex2) {
        // Check for valid vertex indices
        if (vertex1 < 0 || vertex1 >= numVertices || vertex2 < 0 || vertex2 >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        // Remove the edge by setting the value to zero
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;
    }

    // Method to check if there is an edge between two vertices
    public boolean hasEdge(int vertex1, int vertex2) {
        // Check for valid vertex indices
        if (vertex1 < 0 || vertex1 >= numVertices || vertex2 < 0 || vertex2 >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        // Return true if there is an edge
        return adjacencyMatrix[vertex1][vertex2] == 1;
    }

    // Method to display the adjacency matrix
    public void displayMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
