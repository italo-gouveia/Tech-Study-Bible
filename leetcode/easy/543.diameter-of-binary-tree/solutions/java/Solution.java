package leetcode.easy.diameterofbinarytree;

// LeetCode 543 — Diameter of Binary Tree
public class Solution {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // --- Solution 1: Brute Force (O(n^2)) ---

    // Height in nodes.
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return 1 + Math.max(left, right);
    }

    // Brute force diameter: recompute heights at every node.
    public int diameterBruteForce(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int throughRoot = leftHeight + rightHeight;

        int leftDia = diameterBruteForce(root.left);
        int rightDia = diameterBruteForce(root.right);

        return Math.max(throughRoot, Math.max(leftDia, rightDia));
    }

    // --- Solution 2: Optimal DFS with global diameter ---

    public int diameterDFS(TreeNode root) {
        int[] maxDia = new int[1]; // acts as mutable container
        dfs(root, maxDia);
        return maxDia[0];
    }

    // Returns height and updates maxDia[0] with best diameter.
    private int dfs(TreeNode node, int[] maxDia) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left, maxDia);
        int right = dfs(node.right, maxDia);

        // Path through this node
        maxDia[0] = Math.max(maxDia[0], left + right);

        // Return height of this subtree
        return 1 + Math.max(left, right);
    }

    // --- Helpers to build sample trees ---

    private static TreeNode sampleTree1() {
        // Example-like tree: [1,null,2,3,4,5] shape
        return new TreeNode(
            1,
            null,
            new TreeNode(
                2,
                new TreeNode(
                    3,
                    new TreeNode(
                        4,
                        new TreeNode(5),
                        null
                    ),
                    null
                ),
                null
            )
        );
    }

    private static TreeNode sampleTree2() {
        // [1,2,3]
        return new TreeNode(
            1,
            new TreeNode(2),
            new TreeNode(3)
        );
    }

    private static TreeNode sampleTree3() {
        // Skewed: [1,2,null,3]
        return new TreeNode(
            1,
            new TreeNode(
                2,
                new TreeNode(3),
                null
            ),
            null
        );
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode[] tests = new TreeNode[]{
            sampleTree1(),
            sampleTree2(),
            sampleTree3()
        };

        String[] names = new String[]{
            "Example-like 1",
            "Example 2: [1,2,3]",
            "Skewed: [1,2,null,3]"
        };

        for (int i = 0; i < tests.length; i++) {
            TreeNode root = tests[i];
            System.out.printf("%s%n", names[i]);
            System.out.printf("  Diameter (Brute Force): %d%n", solution.diameterBruteForce(root));
            System.out.printf("  Diameter (DFS Optimal): %d%n%n", solution.diameterDFS(root));
        }
    }
}


