package trees.simpletree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        // Insert elements into the tree
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // Perform inorder traversal
        System.out.print("Inorder traversal: ");
        tree.inorderTraversal();  // Output: 20 30 40 50 60 70 80

        // Perform preorder traversal
        System.out.print("Preorder traversal: ");
        tree.preorderTraversal(); // Output: 50 30 20 40 70 60 80

        // Perform postorder traversal
        System.out.print("Postorder traversal: ");
        tree.postorderTraversal(); // Output: 20 40 30 60 80 70 50
    }
}
