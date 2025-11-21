package leetcode.easy.invertbinarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// LeetCode 226 — Invert Binary Tree
public class Solution {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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
    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTreeRecursive(root.right);
        root.right = invertTreeRecursive(temp);
        return root;
    }

    // Solution 2: Iterative DFS with a stack
    public TreeNode invertTreeIterativeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }

    // Solution 3: BFS with a queue
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    // Helper: clone tree to reuse samples across methods
    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }

    // Helper: level-order traversal for printing
    private static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return result;
    }

    private static TreeNode sampleTree1() {
        return new TreeNode(
            4,
            new TreeNode(
                2,
                new TreeNode(1),
                new TreeNode(3)
            ),
            new TreeNode(
                7,
                new TreeNode(6),
                new TreeNode(9)
            )
        );
    }

    private static TreeNode sampleTree2() {
        return new TreeNode(
            2,
            new TreeNode(1),
            new TreeNode(3)
        );
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<TreeNode> samples = Arrays.asList(
            sampleTree1(),
            sampleTree2(),
            null
        );

        for (int i = 0; i < samples.size(); i++) {
            TreeNode root = samples.get(i);
            System.out.printf("Case %d original: %s%n", i + 1, levelOrder(root));
            System.out.printf("  Recursive DFS: %s%n", levelOrder(solution.invertTreeRecursive(cloneTree(root))));
            System.out.printf("  Iterative DFS: %s%n", levelOrder(solution.invertTreeIterativeDFS(cloneTree(root))));
            System.out.printf("  BFS          : %s%n%n", levelOrder(solution.invertTreeBFS(cloneTree(root))));
        }
    }
}

