package sets;

public class Main {
    public static void main(String[] args) {
        // Create an instance of MySet
        MySet<String> set = new MySet<>();

        // Adding elements to the set
        System.out.println("Adding elements");
        // Expected: true
        System.out.println("Add 'apple': " + set.add("apple"));
        // Expected: true
        System.out.println("Add 'banana': " + set.add("banana"));
        // Expected: false (duplicate)
        System.out.println("Add 'apple': " + set.add("apple"));
        // Expected: 2
        System.out.println("Set size: " + set.size());

        // Checking if elements are present
        System.out.println("\nChecking presence of elements: ");
        // Expected: true
        System.out.println("Contains 'apple': " + set.contains("apple"));
        // Expected: true
        System.out.println("Contains 'banana': " + set.contains("banana"));
        // Expected: false
        System.out.println("Contains 'orange': " + set.contains("orange"));

        // Removing elements from the set
        System.out.println("\nRemoving elements: ");
        // Expected: true
        System.out.println("Remove 'apple': " + set.remove("apple"));
        // Expected: false
        System.out.println("Remove 'orange': " + set.remove("orange"));
        // Expected: 1
        System.out.println("Set Size: " + set.size());

        // Checking if the set is empty
        System.out.println("\nChecking if the set is empty:");
        // Expected: false
        System.out.println("Is the set empty? " + set.isEmpty());

        // Removing the last element
        // Expected: true
        System.out.println("Remove 'banana': " + set.remove("banana"));
        // Expected: 0
        System.out.println("Set Size: " + set.size());
        // Expected: true
        System.out.println("Is the set empty? " + set.isEmpty());
    }
}
