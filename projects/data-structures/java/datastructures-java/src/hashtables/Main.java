package hashtables;

public class Main {
    public static void main(String[] args) {
        // Create an instance of MyHashTable
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();

        // Adding key-value pairs
        System.out.println("Adding key-value pairs:");
        hashTable.put("Alice", 30);
        hashTable.put("Bob", 25);
        hashTable.put("Charlie", 35);
        // Expected: 3
        System.out.println("Size: " + hashTable.size());

        // Retrieving values
        System.out.println("\nRetrieving values:");
        // Update Alice's age
        hashTable.put("Alice", 31);
        // Expected: 31
        System.out.println("Alice: " + hashTable.get("Alice"));

        // Removing a key-value pair
        System.out.println("\nRemoving a key-value pair:");
        hashTable.remove("Bob");
        // Expected: null
        System.out.println("Bob: " + hashTable.get("Bob"));
        // Expected: 2
        System.out.println("Size: " + hashTable.size());

        // Checking if the hash table is empty
        System.out.println("\nChecking if the hash table is empty:");
        // Expected: false
        System.out.println("Is empty? " + hashTable.isEmpty());

        // Removing all elements
        hashTable.remove("Alice");
        hashTable.remove("Charlie");
        // Expected: 0
        System.out.println("Size: " + hashTable.size());
        // Expected: true
        System.out.println("Is empty? " + hashTable.isEmpty());
    }
}
