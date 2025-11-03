package graphs.dijkstra;

import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Dijkstra's Algorithm Implementation ===\n");
        
        // Example 1: The detailed example from the chapter
        demonstrateChapterExample();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Example 2: The practical exercise from the chapter
        demonstratePracticalExercise();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Example 3: Additional example with disconnected components
        demonstrateAdditionalExample();
    }
    
    /**
     * Demonstrates the detailed step-by-step example from the chapter
     * Graph: A --6-- B --3-- E
     *        |       |       |
     *        2       4       1
     *        |       |       |
     *        C --1-- D --5-- F
     */
    private static void demonstrateChapterExample() {
        System.out.println("📊 Chapter Example: Step-by-Step Graph");
        System.out.println("Graph: A --6-- B --3-- E");
        System.out.println("       |       |       |");
        System.out.println("       2       4       1");
        System.out.println("       |       |       |");
        System.out.println("       C --1-- D --5-- F\n");
        
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        
        // Build the graph
        dijkstra.addEdge("A", "B", 6);
        dijkstra.addEdge("A", "C", 2);
        dijkstra.addEdge("B", "E", 3);
        dijkstra.addEdge("C", "D", 1);
        dijkstra.addEdge("B", "D", 4);
        dijkstra.addEdge("D", "F", 5);
        dijkstra.addEdge("E", "F", 1);
        
        // Print graph structure
        dijkstra.printGraph();
        
        // Find shortest path from A to E
        System.out.println("\n🔍 Finding shortest path from A to E:");
        Map<String, Object> result = dijkstra.findShortestPath("A", "E");
        
        @SuppressWarnings("unchecked")
        Map<String, Integer> distances = (Map<String, Integer>) result.get("distances");
        @SuppressWarnings("unchecked")
        Map<String, String> previous = (Map<String, String>) result.get("previous");
        int shortestDistance = (Integer) result.get("shortestDistance");
        
        // Reconstruct path
        List<String> path = dijkstra.reconstructPath("A", "E", previous);
        
        System.out.println("Shortest distance: " + shortestDistance);
        System.out.println("Path: " + String.join(" → ", path));
        
        // Show all distances from A
        System.out.println("\nAll distances from A:");
        for (String vertex : dijkstra.getVertices()) {
            System.out.println("  " + vertex + ": " + distances.get(vertex));
        }
        
        // Verify with manual calculation
        System.out.println("\n✅ Verification:");
        System.out.println("  A → B → E: 6 + 3 = 9");
        System.out.println("  A → C → D → F → E: 2 + 1 + 5 + 1 = 9 (alternative)");
    }
    
    /**
     * Demonstrates the practical exercise from the chapter
     * Graph: Start --4-- A --2-- B --1-- End
     *           |        |       |
     *           2        3       6
     *           |        |       |
     *           C --5-- D --3-- E
     */
    private static void demonstratePracticalExercise() {
        System.out.println("🏋️ Practical Exercise: Minimum Path Verification");
        System.out.println("Graph: Start --4-- A --2-- B --1-- End");
        System.out.println("          |        |       |");
        System.out.println("          2        3       6");
        System.out.println("          |        |       |");
        System.out.println("          C --5-- D --3-- E\n");
        
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        
        // Build the graph
        dijkstra.addEdge("Start", "A", 4);
        dijkstra.addEdge("Start", "C", 2);
        dijkstra.addEdge("A", "B", 2);
        dijkstra.addEdge("A", "D", 3);
        dijkstra.addEdge("B", "End", 1);
        dijkstra.addEdge("B", "E", 6);
        dijkstra.addEdge("C", "D", 5);
        dijkstra.addEdge("D", "E", 3);
        
        // Print graph structure
        dijkstra.printGraph();
        
        // Problem 1: Find shortest path from Start to End
        System.out.println("\n🔍 Problem 1: Finding shortest path from Start to End:");
        Map<String, Object> result = dijkstra.findShortestPath("Start", "End");
        
        @SuppressWarnings("unchecked")
        Map<String, Integer> distances = (Map<String, Integer>) result.get("distances");
        @SuppressWarnings("unchecked")
        Map<String, String> previous = (Map<String, String>) result.get("previous");
        int shortestDistance = (Integer) result.get("shortestDistance");
        
        List<String> path = dijkstra.reconstructPath("Start", "End", previous);
        
        System.out.println("Shortest distance: " + shortestDistance);
        System.out.println("Path: " + String.join(" → ", path));
        
        // Problem 2: All distances from Start
        System.out.println("\n🔍 Problem 2: All minimum costs from Start:");
        for (String vertex : dijkstra.getVertices()) {
            System.out.println("  " + vertex + ": " + distances.get(vertex));
        }
        
        // Problem 3: Manual verification
        System.out.println("\n✅ Problem 3: Manual Verification:");
        System.out.println("  Start → A → B → End: 4 + 2 + 1 = 7 ✅ (correct)");
        System.out.println("  Start → C → D → A → B → End: 2 + 5 + 3 + 2 + 1 = 13");
        System.out.println("  Start → C → D → E → B → End: 2 + 5 + 3 + 6 + 1 = 17");
    }
    
    /**
     * Demonstrates additional example with more complex graph
     */
    private static void demonstrateAdditionalExample() {
        System.out.println("🚀 Additional Example: Complex Graph");
        System.out.println("Graph with multiple paths and weights\n");
        
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        
        // Build a more complex graph
        dijkstra.addEdge("X", "Y", 7);
        dijkstra.addEdge("X", "Z", 3);
        dijkstra.addEdge("Y", "W", 4);
        dijkstra.addEdge("Z", "W", 2);
        dijkstra.addEdge("Z", "V", 8);
        dijkstra.addEdge("W", "V", 1);
        dijkstra.addEdge("W", "U", 6);
        dijkstra.addEdge("V", "U", 2);
        
        // Print graph structure
        dijkstra.printGraph();
        
        // Find shortest path from X to U
        System.out.println("\n🔍 Finding shortest path from X to U:");
        Map<String, Object> result = dijkstra.findShortestPath("X", "U");
        
        @SuppressWarnings("unchecked")
        Map<String, Integer> distances = (Map<String, Integer>) result.get("distances");
        @SuppressWarnings("unchecked")
        Map<String, String> previous = (Map<String, String>) result.get("previous");
        int shortestDistance = (Integer) result.get("shortestDistance");
        
        List<String> path = dijkstra.reconstructPath("X", "U", previous);
        
        System.out.println("Shortest distance: " + shortestDistance);
        System.out.println("Path: " + String.join(" → ", path));
        
        // Show all distances
        System.out.println("\nAll distances from X:");
        for (String vertex : dijkstra.getVertices()) {
            System.out.println("  " + vertex + ": " + distances.get(vertex));
        }
    }
}
