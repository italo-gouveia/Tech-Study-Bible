package linkedilst.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        linkedList.insertAtEnd(10); //Insert 10 at the end: 10
        linkedList.insertAtBeginning(5); // Insert 5 at the beginning: 5 -> 10
        linkedList.insertAtEnd(15); // Insert 15 at the end: 5 -> 10 -> 15

        System.out.println("Linked list: ");
        linkedList.displayList(); // Output: Linked List: 5 10 15

        linkedList.deleteNode(10); // Delete node with data 10: 5 -> 15

        System.out.println("After Deletion: ");
        linkedList.displayList(); // Output: After deletion: 5 15

    }
}
