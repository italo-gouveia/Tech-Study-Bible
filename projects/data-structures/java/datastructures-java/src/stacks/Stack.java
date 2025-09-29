package stacks;

import java.util.EmptyStackException;

public class Stack {
    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    private Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data){
        Node newNode = new Node(data);
        newNode.next = top; // new node points to the current top
        top = newNode; // new node becomes the new top
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException(); // Throw exception if stack is empty
        }
        int data = top.data; // Get data from top node
        top = top.next; // Move top to next node
        return data; // Return removed element
    }

    public int peek() {
        if(isEmpty()) {
            throw new EmptyStackException(); // Throw exception if stack is empty
        }
        return top.data; // Return data from top node
    }
}
