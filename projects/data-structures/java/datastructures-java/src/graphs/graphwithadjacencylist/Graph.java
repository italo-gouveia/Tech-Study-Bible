package graphs.graphwithadjacencylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    // Map to store adjacency list representation of the graph
    private final Map<String, List<String>> adjacencyList;

    // Constructor to initialize the graph
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Method to add a vertex to the graph
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Method to add an edge between two vertices
    public void addEdge(String vertex1, String vertex2) {
        // Add vertices if they don't exist
        addVertex(vertex1);
        addVertex(vertex2);
        // Add the edge by updating the adjacency lists
        adjacencyList.get(vertex1).add(vertex2);
        // Since the graph is undirected
        adjacencyList.get(vertex2).add(vertex1);
    }

    // Method to remove an edge between two vertices
    public void removeEdge(String vertex1, String vertex2) {
        List<String> edgesFromVertex1 = adjacencyList.get(vertex1);
        List<String> edgesFromVertex2 = adjacencyList.get(vertex2);
        if (edgesFromVertex1 != null) {
            edgesFromVertex1.remove(vertex2);
        }
        if (edgesFromVertex2 != null) {
            edgesFromVertex2.remove(vertex1);
        }
    }

    // Method to remove a vertex from the graph
    public void removeVertex(String vertex) {
        // Remove all edges from the vertex
        List<String> edges = adjacencyList.get(vertex);
        if (edges != null) {
            for (String adjacentVertex : edges) {
                adjacencyList.get(adjacentVertex).remove(vertex);
            }
        }

        // Remove the vertex
        adjacencyList.remove(vertex);
    }

    // Method to get all vertices in the graph
    public List<String> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    // Method to get all edges connected to a vertex
    public List<String> getEdges(String vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }
}
