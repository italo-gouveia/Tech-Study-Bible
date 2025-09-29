package trees.treeset;

public class TreeSetExample {
    public static void main(String[] args) {
        // Create a new TreeSet of integers
        java.util.TreeSet<Integer> treeSet = new java.util.TreeSet<>();

        // Add elements to the TreeSet
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(15);
        treeSet.add(25);
        treeSet.add(10); // Duplicate, should not be added

        // Print the TreeSet
        System.out.println("TreeSet: " + treeSet);

        // Check if the TreeSet contains certain elements
        System.out.println("TreeSet contains 10: " + treeSet.contains(10)); // true
        System.out.println("TreeSet contains 20: " + treeSet.contains(20)); // true
        System.out.println("TreeSet contains 30: " + treeSet.contains(30)); // false

        // Display the first and last elements
        System.out.println("First element: " + treeSet.first());
        System.out.println("Last element: " + treeSet.last());

        // Remove an element from the TreeSet
        treeSet.remove(20);
        System.out.println("TreeSet after removing 20: " + treeSet);
    }
}

