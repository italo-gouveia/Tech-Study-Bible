package tries;

public class Main {
    public static void main(String[] args) {
        // Create an instance of Trie
        Trie trie = new Trie();

        // Insert words into the trie
        System.out.println("Inserting words: ");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");
        trie.insert("ball");

        // Search for words in the trie
        System.out.println("\nSearching for words:");
        // Expected: true
        System.out.println("Search 'apple': " + trie.search("apple"));
        // Expected: true
        System.out.println("Search 'app': " + trie.search("app"));
        // Expected: true
        System.out.println("Search 'bat': " + trie.search("bat"));
        // Expected: true
        System.out.println("Search 'ball': " + trie.search("ball"));
        // Expected: false
        System.out.println("Search 'bats': " + trie.search("bats"));

        // Check for prefixes
        System.out.println("\nChecking prefixes:");
        // Expected: true
        System.out.println("Starts with 'app': " + trie.startsWith("app"));
        // Expected: true
        System.out.println("Starts with 'bat': " + trie.startsWith("bat"));
        // Expected: true
        System.out.println("Starts with 'ba': " + trie.startsWith("ba"));
        // Expected: false
        System.out.println("Starts with 'baq': " + trie.startsWith("baq"));

    }
}
