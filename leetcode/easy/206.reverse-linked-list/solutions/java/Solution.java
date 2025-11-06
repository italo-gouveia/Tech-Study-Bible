package leetcode.easy.reverselinkedlist;

// LeetCode 206 — Reverse Linked List
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
    // Time: O(n), Space: O(1)
    // Use three pointers: prev, curr, next
    // Before changing curr.next, save it to avoid losing the rest of the list
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            // Save the next node before changing the link
            ListNode next = curr.next;
            // Reverse the link: point current to previous
            curr.next = prev;
            // Move prev and curr forward
            prev = curr;
            curr = next;
        }
        
        // prev is now the new head
        return prev;
    }
    
    // Solution 2: Recursive approach
    // Time: O(n), Space: O(n) for recursion stack
    // Recursively reverse the rest, then reverse the current link
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);
        
        // Reverse the link: head.next now points to head
        // After recursion, head.next is the last node of the reversed rest
        head.next.next = head;
        // Set head's next to null (it will be the tail)
        head.next = null;
        
        return newHead;
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
        
        // Example 1: [1,2,3,4,5]
        System.out.println("Example 1:");
        ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list1);
        
        ListNode reversed1 = solution.reverseListIterative(createList(new int[]{1, 2, 3, 4, 5}));
        System.out.print("Reversed (iterative): ");
        printList(reversed1);
        
        ListNode reversed1Rec = solution.reverseListRecursive(createList(new int[]{1, 2, 3, 4, 5}));
        System.out.print("Reversed (recursive): ");
        printList(reversed1Rec);
        
        System.out.println();
        
        // Example 2: [1,2]
        System.out.println("Example 2:");
        ListNode list2 = createList(new int[]{1, 2});
        System.out.print("Original: ");
        printList(list2);
        
        ListNode reversed2 = solution.reverseListIterative(createList(new int[]{1, 2}));
        System.out.print("Reversed (iterative): ");
        printList(reversed2);
        
        ListNode reversed2Rec = solution.reverseListRecursive(createList(new int[]{1, 2}));
        System.out.print("Reversed (recursive): ");
        printList(reversed2Rec);
        
        System.out.println();
        
        // Example 3: []
        System.out.println("Example 3:");
        ListNode list3 = createList(new int[]{});
        System.out.print("Original: ");
        printList(list3);
        
        ListNode reversed3 = solution.reverseListIterative(createList(new int[]{}));
        System.out.print("Reversed (iterative): ");
        printList(reversed3);
        
        ListNode reversed3Rec = solution.reverseListRecursive(createList(new int[]{}));
        System.out.print("Reversed (recursive): ");
        printList(reversed3Rec);
    }
}

