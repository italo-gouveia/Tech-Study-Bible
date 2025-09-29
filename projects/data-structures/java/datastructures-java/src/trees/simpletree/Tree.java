package trees.simpletree;

// Define the Tree class
public class Tree {

    private TreeNode root; // Root of the tree

    // Constructor to initialize an empty tree
    public Tree() {
        this.root = null;
    }

    // Method to insert a new node into the tree
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    // Recursive method to insert a new node into the tree
    private TreeNode insertRecursive(TreeNode root, int data) {
        // If the tree is empty, create a new node and return it
        if (root == null) {
            return new TreeNode(data);
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRecursive(root.left, data); // Insert into the left subtree
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data); // Insert into the right subtree
        }

        // Return the unchanged node pointer
        return root;
    }

    // Method to perform inorder traversal of the tree
    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    // Recursive method to perform inorder traversal
    private void inorderRecursive(TreeNode root) {
        if (root != null) {
            inorderRecursive(root.left); // Traverse left subtree
            System.out.print(root.data + " "); // Visit the root
            inorderRecursive(root.right); // Traverse right subtree
        }
    }

    // Method to perform preorder traversal of the tree
    public void preorderTraversal() {
        preorderRecursive(root);
        System.out.println();
    }

    // Recursive method to perform preorder traversal
    private void preorderRecursive(TreeNode root) {
        if (root != null) {
            System.out.println(root.data + " "); // Visit the root
            preorderRecursive(root.left); // Traverse left subtree
            preorderRecursive(root.right); // Traverse right subtree
        }
    }

    // Method to perform postorder traversal of the tree
    public void postorderTraversal() {
        postorderRecursive(root);
        System.out.println();
    }

    // Recursive method to perform postorder traversal
    private void postorderRecursive(TreeNode root) {
        if (root != null) {
            postorderRecursive(root.left); // Traverse left subtree
            postorderRecursive(root.right); // Traverse right subtree
            System.out.println(root.data + " "); // Visit the root
        }
    }

}
