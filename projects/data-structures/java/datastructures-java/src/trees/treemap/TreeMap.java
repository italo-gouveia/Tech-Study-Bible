package trees.treemap;

import java.util.Map;

public class TreeMap {
    public static void main(String[] args) {
        // Create an instance of TreeMap with Integer keys and String values
        java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();

        // Adding key-value pairs to the TreeMap
        map.put(3, "Three");
        map.put(1, "One");
        map.put(4, "Four");
        map.put(2, "Two");
        map.put(5, "Five");

        // Displaying the TreeMap (keys will be in ascending order)
        System.out.println("TreeMap contents: " + map);

        // Accessing values based on keys
        System.out.println("Value for key 3: " + map.get(3));

        // Checking if a key exists
        System.out.println("Does key 4 exist? " + map.containsKey(4));

        // Checking if a value exists
        System.out.println("Does value 'Five' exist? " + map.containsValue("Five"));

        // Removing a key-value pair
        map.remove(2);
        System.out.println("TreeMap after removing key 2: " + map);

        // Getting the first and last entries
        System.out.println("First entry: " + map.firstEntry()); // Smallest key
        System.out.println("Last entry: " + map.lastEntry()); // Largest key

        // Navigating through the map
        System.out.println("Entry with key greater than or equal to 3: " + map.ceilingEntry(3));
        System.out.println("Entry with key less than 3: " + map.floorEntry(3));

        // Iterating over the entries
        System.out.println("Iterating over TreeMap:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
