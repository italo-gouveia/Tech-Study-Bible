package graphs.graphwithedgelist;

public class Main {
    public static void main(String[] args) {
        // Create an instance of Graph
        Graph graph = new Graph();

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2, 5); // Weighted edge with weight 5
        graph.addEdge(1, 2);
        graph.addEdge(2, 3, 10); // Weighted edge with weight 10

        // Display all edges
        System.out.println("Edges in the graph:");
        graph.displayEdges();

        // Remove an edge and display the updated edges
        System.out.println("\nRemoving edge between 0 and 2");
        graph.removeEdge(0, 2);
        System.out.println("Updated edges:");
        graph.displayEdges();

        // Display all edges using the getEdges method
        System.out.println("\nEdges using getEdges method:");
        for (Graph.Edge edge : graph.getEdges()) {
            System.out.println(edge);
        }
    }
}

