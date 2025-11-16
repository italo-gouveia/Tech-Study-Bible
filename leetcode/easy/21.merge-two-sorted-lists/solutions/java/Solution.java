package leetcode.easy.mergetwosortedlists;

// LeetCode 21 — Merge Two Sorted Lists
// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    
    // Solution 1: Iterative approach (Optimal Space)
    // Time: O(n + m), Space: O(1)
    // Uses a dummy node to simplify edge cases
    // Compare nodes from both lists, link the smaller one, and advance
    public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Compare nodes from both lists and link the smaller one
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Link remaining nodes from the non-empty list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        // Return the head of the merged list (skip dummy node)
        return dummy.next;
    }
    
    // Solution 2: Recursive approach
    // Time: O(n + m), Space: O(n + m) for recursion stack
    // Base case: if one list is empty, return the other
    // Recursive case: choose the smaller node and recursively merge the rest
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // Base case: if one list is empty, return the other
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // Choose the smaller node and recursively merge the rest
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
    
    // Helper method to create a linked list from an array
    public static ListNode createList(int[] vals) {
        if (vals.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(vals[0]);
        ListNode curr = head;
        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
        }
        return head;
    }
    
    // Helper method to print a linked list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" → ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Example 1: list1 = [1,2,4], list2 = [1,3,4]
        System.out.println("Example 1:");
        ListNode list1 = createList(new int[]{1, 2, 4});
        ListNode list2 = createList(new int[]{1, 3, 4});
        System.out.print("List1: ");
        printList(list1);
        System.out.print("List2: ");
        printList(list2);
        
        ListNode merged1 = solution.mergeTwoListsIterative(
            createList(new int[]{1, 2, 4}), 
            createList(new int[]{1, 3, 4})
        );
        System.out.print("Merged (iterative): ");
        printList(merged1);
        
        ListNode merged1Rec = solution.mergeTwoListsRecursive(
            createList(new int[]{1, 2, 4}), 
            createList(new int[]{1, 3, 4})
        );
        System.out.print("Merged (recursive): ");
        printList(merged1Rec);
        
        System.out.println();
        
        // Example 2: list1 = [], list2 = []
        System.out.println("Example 2:");
        ListNode list3 = createList(new int[]{});
        ListNode list4 = createList(new int[]{});
        System.out.print("List1: ");
        printList(list3);
        System.out.print("List2: ");
        printList(list4);
        
        ListNode merged2 = solution.mergeTwoListsIterative(
            createList(new int[]{}), 
            createList(new int[]{})
        );
        System.out.print("Merged (iterative): ");
        printList(merged2);
        
        ListNode merged2Rec = solution.mergeTwoListsRecursive(
            createList(new int[]{}), 
            createList(new int[]{})
        );
        System.out.print("Merged (recursive): ");
        printList(merged2Rec);
        
        System.out.println();
        
        // Example 3: list1 = [], list2 = [0]
        System.out.println("Example 3:");
        ListNode list5 = createList(new int[]{});
        ListNode list6 = createList(new int[]{0});
        System.out.print("List1: ");
        printList(list5);
        System.out.print("List2: ");
        printList(list6);
        
        ListNode merged3 = solution.mergeTwoListsIterative(
            createList(new int[]{}), 
            createList(new int[]{0})
        );
        System.out.print("Merged (iterative): ");
        printList(merged3);
        
        ListNode merged3Rec = solution.mergeTwoListsRecursive(
            createList(new int[]{}), 
            createList(new int[]{0})
        );
        System.out.print("Merged (recursive): ");
        printList(merged3Rec);
    }
}

