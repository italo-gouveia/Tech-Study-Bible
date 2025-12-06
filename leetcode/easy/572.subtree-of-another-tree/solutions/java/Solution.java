package leetcode.easy.subtreeofanothertree;

// LeetCode 572 — Subtree of Another Tree
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

    // --- DFS + Same Tree (Top-Down) ---

    // Time: O(m * n) worst-case, Space: O(h_root + h_sub)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (sameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Reuse Same Tree logic from problem 100.
    private boolean sameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }

    // --- Sample Trees ---

    private static TreeNode[] sample1() {
        // root = [1,2,3,4,5], subRoot = [2,4,5] → true
        TreeNode root = new TreeNode(
            1,
            new TreeNode(
                2,
                new TreeNode(4),
                new TreeNode(5)
            ),
            new TreeNode(3)
        );

        TreeNode subRoot = new TreeNode(
            2,
            new TreeNode(4),
            new TreeNode(5)
        );

        return new TreeNode[]{root, subRoot};
    }

    private static TreeNode[] sample2() {
        // root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5] → false
        TreeNode root = new TreeNode(
            1,
            new TreeNode(
                2,
                new TreeNode(
                    4,
                    new TreeNode(6),
                    null
                ),
                new TreeNode(5)
            ),
            new TreeNode(3)
        );

        TreeNode subRoot = new TreeNode(
            2,
            new TreeNode(4),
            new TreeNode(5)
        );

        return new TreeNode[]{root, subRoot};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode[][] tests = new TreeNode[][]{
            sample1(),
            sample2()
        };

        String[] names = new String[]{
            "Example 1: [1,2,3,4,5], [2,4,5]",
            "Example 2: [1,2,3,4,5,null,null,6], [2,4,5]"
        };

        for (int i = 0; i < tests.length; i++) {
            TreeNode root = tests[i][0];
            TreeNode subRoot = tests[i][1];
            System.out.printf("%s%n", names[i]);
            System.out.printf("  isSubtree: %b%n%n", solution.isSubtree(root, subRoot));
        }
    }
}


