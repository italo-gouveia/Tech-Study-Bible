package trees.avltree;

public class AVLTree {

    // Node class representing each node in the AVL Tree
    class Node {
        int key;             // Key of the node
        int height;          // Height of the node
        Node left, right;    // Left and right child nodes

        // Constructor
        Node(int key) {
            this.key = key;
            this.height = 1; // New nodes are initially at height 1
        }
    }

    private Node root; // Root node of the AVL Tree

    // Utility method to get the height of a node
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Utility method to get the balance factor of a node
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right rotate utility to balance the tree
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate utility to balance the tree
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a key into the AVL Tree and balance the tree
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        // Perform normal BST insert
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys are not allowed
            return node;
        }

        // Update height of this ancestor node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Utility method to print the tree in order
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Main method to test the AVL Tree
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();

        // Insert elements into the AVL Tree
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        // Print the in-order traversal of the AVL Tree
        System.out.println("In-order traversal of the AVL tree:");
        avl.inorder();
    }
}
