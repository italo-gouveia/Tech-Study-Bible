package tries;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    // Root node of the trie
    private final TrieNode root;

    // Constructor to initialize the trie
    public Trie() {
        root = new TrieNode();
    }

    // Inner class to represent a node in the trie
    private static class TrieNode {
        // Map to store child nodes (each character)
        private final Map<Character, TrieNode> children;
        // Flag to indicate if the node represents the end of a word
        private  boolean isEndOfWord;

        // Constructor to initialize the TrieNode
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    // Method to insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            // If the character is not present, add a new node
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        // Mark the end of the word
        node.isEndOfWord = true;
    }

    // Method to search for a word in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                // Character not found
                return false;
            }
        }
        // Check if it's the end of a valid word
        return node.isEndOfWord;
    }

    // Method to check if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                // Prefix not found
                return false;
            }
        }
        // Prefix found
        return true;
    }
}
