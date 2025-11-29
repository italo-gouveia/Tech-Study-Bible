package leetcode.easy.balancedbinarytree;

// LeetCode 110 — Balanced Binary Tree
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

    // --- Solution 1: Brute Force (Top-Down) ---

    // Height in nodes.
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // Brute force: recompute heights at every node.
    // Time: O(n²), Space: O(n)
    public boolean isBalancedBruteForce(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalancedBruteForce(root.left) && isBalancedBruteForce(root.right);
    }

    // --- Solution 2: DFS Bottom-Up (Optimal) ---

    // Returns [isBalanced, height] where isBalanced: 1 = true, 0 = false
    // Time: O(n), Space: O(h)
    public boolean isBalancedDFS(TreeNode root) {
        int[] result = dfs(root);
        return result[0] == 1;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{1, 0}; // [balanced=1, height=0]
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Check if current node is balanced
        boolean balanced = (left[0] == 1 && right[0] == 1) &&
                          (Math.abs(left[1] - right[1]) <= 1);

        int height = 1 + Math.max(left[1], right[1]);

        return new int[]{balanced ? 1 : 0, height};
    }

    // --- Sample Trees ---

    private static TreeNode sampleTree1() {
        // [3,9,20,null,null,15,7] - balanced
        return new TreeNode(
            3,
            new TreeNode(9),
            new TreeNode(
                20,
                new TreeNode(15),
                new TreeNode(7)
            )
        );
    }

    private static TreeNode sampleTree2() {
        // [1,2,2,3,3,null,null,4,4] - unbalanced
        return new TreeNode(
            1,
            new TreeNode(
                2,
                new TreeNode(
                    3,
                    new TreeNode(4),
                    new TreeNode(4)
                ),
                new TreeNode(3)
            ),
            new TreeNode(2)
        );
    }

    private static TreeNode sampleTree3() {
        // [1,2,3,null,null,4] - balanced
        return new TreeNode(
            1,
            new TreeNode(2),
            new TreeNode(
                3,
                new TreeNode(4),
                null
            )
        );
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode[] testCases = {
            sampleTree1(),
            sampleTree2(),
            sampleTree3(),
            null
        };

        String[] names = {
            "Example 1: [3,9,20,null,null,15,7]",
            "Example 2: [1,2,2,3,3,null,null,4,4]",
            "Example 3: [1,2,3,null,null,4]",
            "Empty tree: []"
        };

        for (int i = 0; i < testCases.length; i++) {
            TreeNode root = testCases[i];
            System.out.printf("%s%n", names[i]);
            System.out.printf("  Brute Force: %b%n", solution.isBalancedBruteForce(root));
            System.out.printf("  DFS Optimal: %b%n%n", solution.isBalancedDFS(root));
        }
    }
}

