package linkedilst.linkedlist;

// LinkedList class represents the linked list itself
public class LinkedList {
    private Node head; // Head node of the linked list

    // Constructor to initialize an empty linked list
    LinkedList() {
        this.head = null;
    }

    // Method to insert a new node at the beginning of the Linked List
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data); // Create a new node with the given data
        newNode.next = head; // Set the next of the new node to current head
        head = newNode; // Set the new node as the new head
    }

    // Method to insert a new node at the end of the Linked List
    public void insertAtEnd(int data) {
        Node newNode = new Node(data); // Create a new node with the given data
        if (head == null) { // If the list is empty, set new node as head
            head = newNode;
            return;
        }

        Node current = head;
        while(current.next != null) { // Traverse to the last node
            current = current.next;
        }
        current.next = newNode; // Set the next of the last node to the new node
    }

    // Method to delete a node with given data from the linked list
    public void deleteNode(int data) {
        if (head == null) return; // If the list is empty, return
        if (head.data == data) { // If head node itself holds the key to be deleted
            head = head.next; // Change the head to the next node
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null && current.data != data) {
            prev = current;
            current = current.next;
        }

        if(current == null) return; // If key was not present in the list
        prev.next = current.next; // Unlink the node from the linked list
    }

    // Method to display the linked list
    public void displayList() {
        Node current = head; // Start transversal from the head node
        while(current != null) {
            System.out.println(current.data + " "); // Print data of the current node
            current = current.next;
        }
        System.out.println();
    }
}
