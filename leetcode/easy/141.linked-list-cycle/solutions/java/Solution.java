package leetcode.easy.linkedlistcycle;

import java.util.HashSet;

// LeetCode 141 — Linked List Cycle
// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    
    // Solution 1: Hash Set approach
    // Time: O(n), Space: O(n)
    // Traverse the list and store visited nodes in a hash set
    // If we encounter a node that's already visited, there's a cycle
    public boolean hasCycleHashSet(ListNode head) {
        HashSet<ListNode> seen = new HashSet<>();
        ListNode curr = head;
        
        while (curr != null) {
            if (seen.contains(curr)) {
                return true;
            }
            seen.add(curr);
            curr = curr.next;
        }
        
        return false;
    }
    
    // Solution 2: Floyd's Cycle Detection (Tortoise and Hare) - Optimal
    // Time: O(n), Space: O(1)
    // Use two pointers: slow moves 1 step, fast moves 2 steps
    // If there's a cycle, fast will eventually catch up to slow
    public boolean hasCycleFloyd(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Fast pointer moves 2 steps, slow moves 1 step
        // Check fast and fast.next to avoid null pointer exception
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            // If they meet, there's a cycle
            if (fast == slow) {
                return true;
            }
        }
        
        // Fast pointer reached the end, no cycle
        return false;
    }
    
    // Helper method to create a linked list from an array
    // pos indicates the index where the tail connects (for cycle creation)
    // pos = -1 means no cycle
    public static ListNode createList(int[] vals, int pos) {
        if (vals.length == 0) {
            return null;
        }
        
        ListNode[] nodes = new ListNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            nodes[i] = new ListNode(vals[i]);
            if (i > 0) {
                nodes[i - 1].next = nodes[i];
            }
        }
        
        // Create cycle if pos is valid
        if (pos >= 0 && pos < nodes.length) {
            nodes[nodes.length - 1].next = nodes[pos];
        }
        
        return nodes[0];
    }
    
    // Helper method to print a linked list (limited to avoid infinite loop with cycles)
    public static void printList(ListNode head, int maxNodes) {
        ListNode curr = head;
        int count = 0;
        HashSet<ListNode> visited = new HashSet<>();
        
        while (curr != null && count < maxNodes) {
            if (visited.contains(curr)) {
                System.out.print("(cycle detected)");
                break;
            }
            visited.add(curr);
            System.out.print(curr.val);
            if (curr.next != null && count < maxNodes - 1) {
                System.out.print(" → ");
            }
            curr = curr.next;
            count++;
        }
        System.out.println();
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Example 1: head = [3,2,0,-4], pos = 1 (has cycle)
        System.out.println("Example 1:");
        ListNode list1 = createList(new int[]{3, 2, 0, -4}, 1);
        System.out.print("List: ");
        printList(list1, 10);
        
        boolean result1Hash = solution.hasCycleHashSet(list1);
        boolean result1Floyd = solution.hasCycleFloyd(createList(new int[]{3, 2, 0, -4}, 1));
        System.out.println("Has cycle (HashSet): " + result1Hash);
        System.out.println("Has cycle (Floyd): " + result1Floyd);
        
        System.out.println();
        
        // Example 2: head = [1,2], pos = 0 (has cycle)
        System.out.println("Example 2:");
        ListNode list2 = createList(new int[]{1, 2}, 0);
        System.out.print("List: ");
        printList(list2, 10);
        
        boolean result2Hash = solution.hasCycleHashSet(list2);
        boolean result2Floyd = solution.hasCycleFloyd(createList(new int[]{1, 2}, 0));
        System.out.println("Has cycle (HashSet): " + result2Hash);
        System.out.println("Has cycle (Floyd): " + result2Floyd);
        
        System.out.println();
        
        // Example 3: head = [1], pos = -1 (no cycle)
        System.out.println("Example 3:");
        ListNode list3 = createList(new int[]{1}, -1);
        System.out.print("List: ");
        printList(list3, 10);
        
        boolean result3Hash = solution.hasCycleHashSet(list3);
        boolean result3Floyd = solution.hasCycleFloyd(createList(new int[]{1}, -1));
        System.out.println("Has cycle (HashSet): " + result3Hash);
        System.out.println("Has cycle (Floyd): " + result3Floyd);
        
        System.out.println();
        
        // Additional test: head = [], pos = -1 (empty list)
        System.out.println("Additional test - Empty list:");
        ListNode list4 = createList(new int[]{}, -1);
        System.out.print("List: ");
        printList(list4, 10);
        
        boolean result4Hash = solution.hasCycleHashSet(list4);
        boolean result4Floyd = solution.hasCycleFloyd(createList(new int[]{}, -1));
        System.out.println("Has cycle (HashSet): " + result4Hash);
        System.out.println("Has cycle (Floyd): " + result4Floyd);
    }
}

