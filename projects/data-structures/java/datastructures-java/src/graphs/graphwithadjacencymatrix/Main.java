package graphs.graphwithadjacencymatrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of Graph with 5 vertices
        Graph graph = new Graph(5);

        // Add edges between vertices
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        // Display the adjacency matrix
        System.out.println("Adjacency Matrix:");
        graph.displayMatrix();

        // Check if there is an edge between vertices
        System.out.println("\nChecking edges:");
        // Expected: true
        System.out.println("Has edge between 0 and 1: " + graph.hasEdge(0, 1));
        // Expected: false
        System.out.println("Has edge between 1 and 4: " + graph.hasEdge(1, 4));

        // Remove an edge and display the matrix again
        System.out.println("\nRemoving edge between 1 and 2");
        graph.removeEdge(1, 2);
        System.out.println("Updated Adjacency Matrix:");
        graph.displayMatrix();
    }
}
