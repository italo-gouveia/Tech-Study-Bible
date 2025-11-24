package leetcode.easy.maximumdepthofbinarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// LeetCode 104 — Maximum Depth of Binary Tree
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

    // Solution 1: Recursive DFS
    // Time: O(n), Space: O(h) where h = height
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthRecursive(root.left), maxDepthRecursive(root.right));
    }

    // Solution 2: Iterative DFS with Stack
    // Time: O(n), Space: O(n)
    public int maxDepthIterativeDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 1));
        int maxDepth = 0;

        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            TreeNode node = current.node;
            int depth = current.depth;

            if (node != null) {
                maxDepth = Math.max(maxDepth, depth);
                stack.push(new Pair(node.left, depth + 1));
                stack.push(new Pair(node.right, depth + 1));
            }
        }

        return maxDepth;
    }

    // Helper class for (node, depth) pairs
    static class Pair {
        TreeNode node;
        int depth;

        Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // Solution 3: BFS (Level-Order Traversal)
    // Time: O(n), Space: O(n)
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return depth;
    }

    // Helper: create sample trees
    private static TreeNode sampleTree1() {
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
        return new TreeNode(
            1,
            null,
            new TreeNode(2)
        );
    }

    private static TreeNode sampleTree3() {
        return new TreeNode(
            1,
            new TreeNode(
                2,
                new TreeNode(4),
                new TreeNode(5)
            ),
            new TreeNode(3)
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
            "Example 2: [1,null,2]",
            "Custom: [1,2,3,4,5]",
            "Empty tree: []"
        };

        for (int i = 0; i < testCases.length; i++) {
            TreeNode root = testCases[i];
            System.out.printf("%s%n", names[i]);
            System.out.printf("  Recursive DFS: %d%n", solution.maxDepthRecursive(root));
            System.out.printf("  Iterative DFS: %d%n", solution.maxDepthIterativeDFS(root));
            System.out.printf("  BFS          : %d%n%n", solution.maxDepthBFS(root));
        }
    }
}

