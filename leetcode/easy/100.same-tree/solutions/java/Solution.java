package leetcode.easy.sametree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// LeetCode 100 — Same Tree
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

    // --- Solution 1: Recursive DFS (Top-Down) ---

    // Time: O(n), Space: O(h)
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTreeRecursive(p.left, q.left) &&
               isSameTreeRecursive(p.right, q.right);
    }

    // --- Solution 2: Iterative DFS with Stack ---

    // Time: O(n), Space: O(n)
    public boolean isSameTreeIterativeDFS(TreeNode p, TreeNode q) {
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p, q});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode node1 = nodes[0];
            TreeNode node2 = nodes[1];

            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val) {
                return false;
            }

            // Push children in same order: right, then left (so left is processed first)
            stack.push(new TreeNode[]{node1.right, node2.right});
            stack.push(new TreeNode[]{node1.left, node2.left});
        }

        return true;
    }

    // --- Solution 3: BFS (Level-Order) ---

    // Time: O(n), Space: O(n)
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.offer(p);
        q2.offer(q);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode nodeP = q1.poll();
            TreeNode nodeQ = q2.poll();

            if (nodeP == null && nodeQ == null) {
                continue;
            }
            if (nodeP == null || nodeQ == null) {
                return false;
            }
            if (nodeP.val != nodeQ.val) {
                return false;
            }

            q1.offer(nodeP.left);
            q1.offer(nodeP.right);
            q2.offer(nodeQ.left);
            q2.offer(nodeQ.right);
        }

        return q1.isEmpty() && q2.isEmpty();
    }

    // --- Sample Trees ---

    private static TreeNode[] sampleEqualTrees() {
        // [1,2,3] and [1,2,3]
        TreeNode p = new TreeNode(
            1,
            new TreeNode(2),
            new TreeNode(3)
        );

        TreeNode q = new TreeNode(
            1,
            new TreeNode(2),
            new TreeNode(3)
        );

        return new TreeNode[]{p, q};
    }

    private static TreeNode[] sampleDifferentStructure() {
        // [4,7] vs [4,null,7]
        TreeNode p = new TreeNode(
            4,
            new TreeNode(7),
            null
        );

        TreeNode q = new TreeNode(
            4,
            null,
            new TreeNode(7)
        );

        return new TreeNode[]{p, q};
    }

    private static TreeNode[] sampleDifferentValues() {
        // [1,2,3] vs [1,3,2]
        TreeNode p = new TreeNode(
            1,
            new TreeNode(2),
            new TreeNode(3)
        );

        TreeNode q = new TreeNode(
            1,
            new TreeNode(3),
            new TreeNode(2)
        );

        return new TreeNode[]{p, q};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode[][] tests = new TreeNode[][]{
            sampleEqualTrees(),
            sampleDifferentStructure(),
            sampleDifferentValues(),
            {null, null}
        };

        String[] names = new String[]{
            "Equal trees: [1,2,3] vs [1,2,3]",
            "Different structure: [4,7] vs [4,null,7]",
            "Different values: [1,2,3] vs [1,3,2]",
            "Both empty: [] vs []"
        };

        for (int i = 0; i < tests.length; i++) {
            TreeNode p = tests[i][0];
            TreeNode q = tests[i][1];
            System.out.printf("%s%n", names[i]);
            System.out.printf("  Recursive DFS: %b%n", solution.isSameTreeRecursive(p, q));
            System.out.printf("  Iterative DFS: %b%n", solution.isSameTreeIterativeDFS(p, q));
            System.out.printf("  BFS          : %b%n%n", solution.isSameTreeBFS(p, q));
        }
    }
}



