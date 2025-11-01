package graphs.dijkstra;

import java.util.*;

public class DijkstraAlgorithm {
    
    // ============================================================
    // PART 1: Edge - A "Road" Between Two Places
    // ============================================================
    // Think of an Edge like a road or path between two cities.
    // destination = where this road goes to
    // weight = how long it takes to travel this road (like "5 minutes")
    // ============================================================
    public static class Edge {
        private final String destination;  // Where does this road go? (e.g., "School")
        private final int weight;          // How long does it take? (e.g., 5 minutes)
        
        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
        
        public String getDestination() {
            return destination;
        }
        
        public int getWeight() {
            return weight;
        }
        
        @Override
        public String toString() {
            return destination + "(" + weight + ")";
        }
    }
    
    // ============================================================
    // PART 2: Node - A Place with How Far We've Traveled
    // ============================================================
    // Think of a Node like a note card that says:
    // "I'm at School, and it took me 5 minutes to get here"
    // vertex = where am I right now?
    // distance = how much time did I spend traveling to get here?
    // 
    // Comparable means we can compare two Nodes - the PriorityQueue
    // uses this to always pick the place with the SMALLEST distance first!
    // (Like a line where the person who waited the shortest time goes first)
    // ============================================================
    private static class Node implements Comparable<Node> {
        private final String vertex;    // Where am I? (e.g., "School")
        private final int distance;     // How long did it take to get here? (e.g., 5 minutes)
        
        public Node(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        public String getVertex() {
            return vertex;
        }
        
        // This tells Java: "Compare two Nodes by their distance"
        // Smaller distance = "better" (comes first in the priority queue)
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    // ============================================================
    // PART 3: Graph - Our Map of All Cities and Roads
    // ============================================================
    // This is like a big map that shows:
    // - Each city (the key)
    // - All the roads that leave from that city (the list of edges)
    // 
    // Example:
    //   "House" → [Road to School(5min), Road to Park(2min)]
    //   "School" → [Road to House(5min), Road to Library(3min)]
    // ============================================================
    private final Map<String, List<Edge>> graph;
    
    public DijkstraAlgorithm() {
        this.graph = new HashMap<>();
    }
    
    // ============================================================
    // PART 4: Adding Cities and Roads to Our Map
    // ============================================================
    
    /**
     * Add a new city to our map (but don't add any roads yet)
     * Like putting a new city on the map, but no roads go to/from it yet
     */
    public void addVertex(String vertex) {
        graph.putIfAbsent(vertex, new ArrayList<>());
    }
    
    /**
     * Add a road between two cities
     * This creates a two-way road (undirected graph):
     * - Road from source → destination (takes 'weight' time)
     * - Road from destination → source (also takes 'weight' time)
     * 
     * Example: addEdge("House", "School", 5)
     *   Creates: House → School (5 minutes)
     *   And:     School → House (5 minutes)
     */
    public void addEdge(String source, String destination, int weight) {
        // First, make sure both cities exist on our map
        addVertex(source);
        addVertex(destination);
        
        // Create a road FROM source TO destination
        // Example: House → School (5 minutes)
        graph.get(source).add(new Edge(destination, weight));
        
        // Since we want a two-way road, also create it the other way
        // Example: School → House (5 minutes)
        graph.get(destination).add(new Edge(source, weight));
    }
    
    // ============================================================
    // PART 5: THE MAIN ALGORITHM - Finding the Shortest Path!
    // ============================================================
    // This is like planning a trip:
    // "I want to go from House to School. What's the fastest way?"
    // 
    // The algorithm works like this:
    // 1. Start at your starting place (distance = 0, because you're already there!)
    // 2. Always visit the closest place you haven't visited yet
    // 3. When you visit a place, check all its neighbors
    //    - "Can I get there faster from here?" If yes, update!
    // 4. Keep going until you reach your destination (or visit everywhere)
    // ============================================================
    
    /**
     * Find the shortest path from source to destination using Dijkstra's algorithm
     * @param source Starting vertex (where you begin your journey)
     * @param destination Target vertex (where you want to go)
     * @return Map containing distances and previous vertices for path reconstruction
     */
    public Map<String, Object> findShortestPath(String source, String destination) {
        // First, make sure both places exist in our map!
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination vertex not found in graph");
        }
        
        // ============================================================
        // STEP 1: SET UP OUR HELPER TOOLS
        // ============================================================
        // distances = A notebook that says:
        //   "The shortest time to get to School is 5 minutes"
        //   "The shortest time to get to Park is 2 minutes"
        Map<String, Integer> distances = new HashMap<>();
        
        // previous = A notebook that remembers:
        //   "I got to School FROM House"
        //   "I got to Park FROM House"
        // This helps us rebuild the path later!
        Map<String, String> previous = new HashMap<>();
        
        // unvisited = A special line (priority queue) of places we haven't visited yet
        // It always puts the place with the SMALLEST distance first!
        // Like: "Oh, Park is only 2 minutes away? That person goes first!"
        PriorityQueue<Node> unvisited = new PriorityQueue<>();
        
        // visited = A list of places we've already been to
        // Once we visit a place, we don't need to visit it again!
        Set<String> visited = new HashSet<>();
        
        // ============================================================
        // STEP 2: INITIALIZE - Get Ready to Start!
        // ============================================================
        // For every city on the map, write down:
        //   "I don't know how to get there yet, so it's INFINITY minutes"
        // (Integer.MAX_VALUE is like "infinity" - a really, really big number)
        for (String vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        
        // EXCEPT for where we're starting! We're already there!
        // So the distance to the starting place is 0 minutes
        distances.put(source, 0);
        
        // Put our starting place in the "unvisited" line
        // We'll visit it first!
        unvisited.offer(new Node(source, 0));
        
        // ============================================================
        // STEP 3: THE MAIN LOOP - Visit Places One by One!
        // ============================================================
        // Keep going until we've checked all the places in our line
        while (!unvisited.isEmpty()) {
            // Get the place that's CLOSEST (has the smallest distance)
            // This is why we use a PriorityQueue - it always gives us
            // the place we can reach in the shortest time!
            Node currentNode = unvisited.poll();
            String currentVertex = currentNode.getVertex();
            
            // Wait, did I already visit this place?
            // (This can happen if we put the same place in the queue multiple times
            //  with different distances - we only want to process it once!)
            if (visited.contains(currentVertex)) {
                continue; // Skip it! Go to the next place.
            }
            
            // Okay, I'm visiting this place NOW!
            // Mark it as visited so I don't visit it again
            visited.add(currentVertex);
            
            // Did I reach my destination? YAY! We can stop early!
            // (We don't need to visit all places, just find the way to the destination)
            if (currentVertex.equals(destination)) {
                break; // Stop the loop - we're done!
            }
            
            // ============================================================
            // STEP 4: EXPLORE NEIGHBORS - Check All the Roads from Here!
            // ============================================================
            // Get all the places I can go to from my current location
            // Example: From House, I can go to School or Park
            List<Edge> neighbors = graph.get(currentVertex);
            
            if (neighbors != null) {
                // For each place I can go to from here...
                for (Edge edge : neighbors) {
                    String neighbor = edge.getDestination(); // Where does this road lead?
                    
                    // Did I already visit that neighbor? Skip it!
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    
                    // ============================================================
                    // STEP 5: CALCULATE AND UPDATE DISTANCES
                    // ============================================================
                    // Calculate: "How long would it take to get to the neighbor
                    //            if I go from my current location?"
                    // 
                    // newDistance = "How long to get HERE" + "How long to go FROM here to neighbor"
                    // Example: I'm at House (took 0 minutes), going to School (takes 5 minutes)
                    //          newDistance = 0 + 5 = 5 minutes
                    int newDistance = distances.get(currentVertex) + edge.getWeight();
                    
                    // Is this NEW way faster than what I thought before?
                    // Example: I thought School was 10 minutes away, but now I found
                    //          a path that's only 5 minutes! That's better!
                    if (newDistance < distances.get(neighbor)) {
                        // Update my notebook with the shorter distance!
                        distances.put(neighbor, newDistance);
                        
                        // Remember: "I got to this neighbor FROM currentVertex"
                        // This helps us rebuild the path later!
                        previous.put(neighbor, currentVertex);
                        
                        // Add this neighbor to the "unvisited" line
                        // We'll check it later (maybe we can find an even faster way!)
                        unvisited.offer(new Node(neighbor, newDistance));
                    }
                }
            }
        }
        
        // ============================================================
        // STEP 6: RETURN THE RESULTS
        // ============================================================
        // Package everything up to give back to the caller
        Map<String, Object> result = new HashMap<>();
        result.put("distances", distances);           // All the shortest distances
        result.put("previous", previous);             // How we got to each place
        result.put("shortestDistance", distances.get(destination)); // The answer!
        
        return result;
    }
    
    // ============================================================
    // PART 6: RECONSTRUCTING THE PATH - Backtracking Our Steps!
    // ============================================================
    // Remember the "previous" notebook we made?
    // It tells us: "I got to School FROM House"
    //             "I got to Park FROM House"
    // 
    // Now we use it to rebuild our path BACKWARDS!
    // Start at the destination and work backwards to the start,
    // then flip it around to get the path from start to finish!
    // ============================================================
    
    /**
     * Reconstruct the shortest path from source to destination
     * Uses the "previous" map to build the path backwards
     * 
     * Example:
     *   previous = {School: House, Park: House}
     *   destination = School
     *   source = House
     * 
     *   We build backwards: School → House → null
     *   Then reverse it: House → School
     */
    public List<String> reconstructPath(String source, String destination, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        
        // If we can't find the destination in our "previous" notebook,
        // it means there's no path at all!
        if (!previous.containsKey(destination)) {
            return path; // Return empty path - no way to get there!
        }
        
        // ============================================================
        // STEP 1: BUILD THE PATH BACKWARDS
        // ============================================================
        // Start at the destination and work backwards
        // Example: I want to go to School, so I start at School
        String current = destination;
        
        // Keep going backwards until we reach the start
        // Example: School → House → (null, because House was where we started!)
        while (current != null) {
            // Add the current place to our path
            path.add(current);
            
            // Go back one step: "Where did I come FROM to get here?"
            // Example: "I'm at School, and I came FROM House"
            current = previous.get(current);
        }
        
        // ============================================================
        // STEP 2: FLIP IT AROUND!
        // ============================================================
        // Right now our path is backwards: [School, House]
        // We want it forwards: [House, School]
        // So we flip it!
        Collections.reverse(path);
        
        return path;
    }
    
    /**
     * Get all vertices in the graph
     */
    public Set<String> getVertices() {
        return new HashSet<>(graph.keySet());
    }
    
    /**
     * Get neighbors of a vertex
     */
    public List<Edge> getNeighbors(String vertex) {
        return graph.getOrDefault(vertex, new ArrayList<>());
    }
    
    /**
     * Print the graph structure
     */
    public void printGraph() {
        System.out.println("Graph Structure:");
        for (Map.Entry<String, List<Edge>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
