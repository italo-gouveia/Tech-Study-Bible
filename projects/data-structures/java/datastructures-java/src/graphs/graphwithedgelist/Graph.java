package graphs.graphwithedgelist;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // List to store all the edges in the graph
    private final List<Edge> edges;

    // Constructor to initialize the graph
    public Graph() {
        edges = new ArrayList<>();
    }

    // Inner class to represent an edge in the graph
    protected static class Edge {
        private final int vertex1;
        private final int vertex2;
        // Optional. for  weighted graphs
        private final int weight;

        // Constructor for the edge
        Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        // Constructor for unweighted edges
        Edge(int vertex1, int vertex2) {
            // Default weight is 1
            this(vertex1, vertex2, 1);
        }

        // Getter methods
        public int getVertex1() {
            return vertex1;
        }

        public int getVertex2() {
            return vertex2;
        }

        public int getWeight() {
            return weight;
        }

        // Override toString for easy printing
        @Override
        public String toString() {
            return "Edge{" +
                    "vertex1=" + vertex1 +
                    ", vertex2=" + vertex2 +
                    ", weight=" + weight +
                    '}';
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int vertex1, int vertex2, int weight) {
        edges.add(new Edge(vertex1, vertex2, weight));
    }

    // Overloaded method for unweighted edges
    public void addEdge(int vertex1, int vertex2) {
        // Default weight is 1
        addEdge(vertex1, vertex2, 1);
    }

    // Method to remove an edge between two vertices
    public void removeEdge(int vertex1, int vertex2) {
        edges.removeIf(edge -> (edge.getVertex1() == vertex1 && edge.getVertex2() == vertex2) ||
                (edge.getVertex1() == vertex2 && edge.getVertex2() == vertex1));
    }

    // Method to get all edges in the graph
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    // Method to display all edges
    public void displayEdges() {
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}
