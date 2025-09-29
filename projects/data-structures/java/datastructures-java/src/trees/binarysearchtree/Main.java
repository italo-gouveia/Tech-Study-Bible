package trees.binarysearchtree;

public class Main {
    public static void main(String[] args) {
        // Create an instance of BinarySearchTree
        BinarySearchTree bst = new BinarySearchTree();

        // Insert values into the BST
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Display the BST in-order (should display sorted order)
        System.out.println("In-order traversal:");
        bst.inOrderTraversal(); // Expected: 20 30 40 50 60 70 80

        // Search for values in the BST
        System.out.println("Searching for 40: " + bst.search(40)); // Expected: true
        System.out.println("Searching for 25: " + bst.search(25)); // Expected: false

        // Find the minimum and maximum values
        System.out.println("Minimum value: " + bst.findMin()); // Expected: 20
        System.out.println("Maximum value: " + bst.findMax()); // Expected: 80

        // Delete a value from the BST
        System.out.println("Deleting value 20");
        bst.delete(20);
        System.out.println("In-order traversal after deletion:");
        bst.inOrderTraversal(); // Expected: 30 40 50 60 70 80
    }
}
