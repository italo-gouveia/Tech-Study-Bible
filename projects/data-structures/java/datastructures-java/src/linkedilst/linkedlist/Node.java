package linkedilst.linkedlist;

//Node class represents each node in the Linked List
public class Node {
    int data; // Data stored in the node
    Node next; // Reference to the next node in the list

    // Constructor to initialize a node with given data
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
