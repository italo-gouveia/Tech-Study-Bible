package linkedilst.linkedhashset;

public class Main {
    public static void main(String[] args) {
        // Create an instance of MyLinkedHashSet
        MyLinkedHashSet<String> linkedHashSet = new MyLinkedHashSet<>();

        // Adding element to the set
        System.out.println("Adding elements:");
        // Expected: true
        System.out.println("Add ´apple´: " + linkedHashSet.add("apple"));
        // Expected: true
        System.out.println("Add 'banana': " + linkedHashSet.add("banana"));
        // Expected: false
        System.out.println("Add 'apple': " + linkedHashSet.add("apple"));
        // Expected: 2
        System.out.println("Set size: " + linkedHashSet.size());

        // Checking if elements are present
        System.out.println("\nChecking presence of elements:");
        // Expected: true
        System.out.println("Contains 'apple': " + linkedHashSet.contains("apple"));
        // Expected: true
        System.out.println("Contains 'banana': " + linkedHashSet.contains("banana"));
        // Expected: false
        System.out.println("Contains 'orange': " + linkedHashSet.contains("orange"));

        // Removing elements from the set
        System.out.println("\nRemoving elements:");
        // Expected: true
        System.out.println("Remove 'apple': " + linkedHashSet.remove("apple"));
        // Expected: false (not present)
        System.out.println("Remove 'orange': " + linkedHashSet.remove("orange"));
        // Expected: 1
        System.out.println("Set size: " + linkedHashSet.size());

        // Checking if the set is empty
        System.out.println("\nChecking if the set is empty:");
        // Expected: false
        System.out.println("Is the set empty? " + linkedHashSet.isEmpty());

        // Removing the last element
        // Expected: true
        System.out.println("Remove 'banana': " + linkedHashSet.remove("banana"));
        // Expected: 0
        System.out.println("Set size: " + linkedHashSet.size());
        // Expected: true
        System.out.println("Is the set empty? " + linkedHashSet.isEmpty());
    }
}
