package trees.binarysearchtree;

public class BinarySearchTree {
    // Inner class to define a node in the BST (Binary Search Tree)
    private static class Node {
        // Value of the node
        int value;
        // Left child of the node
        Node left;
        // Right child of the node
        Node right;

        Node (int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Root of the BST
    private Node root;

    // Constructor to initialize an empty BST
    public BinarySearchTree() {
        this.root = null;
    }

    // Method to insert a new value into the BST
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    // Recursive helper method to insert a new value
    private Node insertRecursive(Node node, int value) {
        // If the current node is null, place the new node here
        if (node == null) {
            return new Node(value);
        }

        // Recur down tree and insert the value in the correct place
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }

        // Return the unchanged node pointer
        return node;
    }

    // Method to search for a value in the BST
    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    // Recursive helper method to search for a value
    private boolean searchRecursive(Node node, int value) {
        // Base cases: node is null or node's value matches the search value
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }

        // Recur down the tree based on the value comparison
        if (value < node.value) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }

    // Method to find the minimum value in the BST
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinRecursive(root);
    }

    // Recursive helper method to find the minimum value
    private int findMinRecursive(Node node) {
        // Traverse to the leftmost node
        if (node.left == null) {
            return node.value;
        }
        return findMinRecursive(node.left);
    }

    // Method to find the maximum value in the BST
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxRecursive(root);
    }

    // Recursive helper method to find the maximum value
    private int findMaxRecursive(Node node) {
        // Traverse to the rightmost node
        if (node.right == null) {
            return node.value;
        }
        return findMaxRecursive(node.right);
    }

    // Method to display the BST in-order
    public void inOrderTraversal() {
        inOrderRecursive(root);
        System.out.println();
    }

    // Recursive helper method to perform in-order traversal
    private void inOrderRecursive(Node node) {
        if (node != null) {
            // Visit left subtree
            inOrderRecursive(node.left);
            // Visit node
            System.out.println(node.value + " ");
            // Visit right subtree
            inOrderRecursive(node.right);
        }
    }

    // Method to delete a value from the BST
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // Recursive helper method to delete a value
    private Node deleteRecursive(Node node, int value) {
        // Base case: node is null
        if (node == null) {
            return null;
        }

        // Recur down the tree based on the value comparison
        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Node with the value found
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            Node successor = findMinNode(node.right);

            // Copy the inorder successor's content to this node
            node.value = successor.value;

            // Delete the inorder successor
            node.right = deleteRecursive(node.right, successor.value);
        }

        return node;
    }

    // Helper method to find the node with the minimum value
    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
