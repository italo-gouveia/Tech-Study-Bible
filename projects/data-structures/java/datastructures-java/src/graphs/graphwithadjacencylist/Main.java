package graphs.graphwithadjacencylist;

public class Main {
    public static void main(String[] args) {
        // Create an instance of Graph
        Graph graph = new Graph();

        // Add vertices to the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Add edges between vertices
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        // Print vertices and their edges
        System.out.println("Vertices and their edges:");
        for (String vertex : graph.getVertices()) {
            System.out.println(vertex + ": " + graph.getEdges(vertex));
        }

        // Remove an edge and a vertex
        System.out.println("\nRemoving edge between 'A' and 'B'");
        graph.removeEdge("A", "B");
        // Expected: [C]
        System.out.println("A: " + graph.getEdges("A"));
        // Expected: [C]
        System.out.println("B: " + graph.getEdges("B"));

        System.out.println("\nRemoving vertex 'D'");
        graph.removeVertex("D");
        // Expected: [A, B, C]
        System.out.println("Vertices: " + graph.getVertices());
        // Expected: [A, B]
        System.out.println("C's edges: " + graph.getEdges("C"));
    }
}

