package queues;

public class MyQueue<T> {

    // Node class represents each element in the queue
    private static class Node<T> {
        private T data; //Data stored in the node
        private Node<T> next; // Reference to the next node in the queue

        // Constructor to initialize a node with data
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // Points to the front (first) node in the queue
    private Node<T> rear; // Points to the rear (last) node in the queue
    private int size; // Number of elements in the queue

    // Constructor to initialize an empty queue
    public MyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Method to add an element to the rear of the queue (enqueue operation)
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        // If the queue is empty, set both front and rear to the new node
        if(isEmpty()) {
            front = newNode;
        } else {
            // Otherwise, link the current rear node to the new node
            rear.next = newNode;
        }

        // Update rear to point to the new node and increment the size of the queue
        rear = newNode;
        size++;
    }

    // Method to remove and return the element at the front of the queue (dequeue operation)
    public T dequeue() throws IllegalAccessException {
        // Check if the queue is empty
        if (isEmpty()) {
            throw new IllegalAccessException("Queue is empty. Cannot dequeue.");
        }

        // Retrieve the data from the front node
        T data = front.data;

        // Move front to the next node
        front = front.next;

        // If front is now null (queue is empty after dequeue), update rear to null as well
        if (front == null) {
            rear = null;
        }

        // Decrement the size of the queue and return the dequeued data
        size--;
        return data;
    }

    // Method to return the element at the front of the queue without removing it (peek operation)
    public T peek() throws IllegalAccessException {
        // Check if the queue is empty
        if (isEmpty()) {
            throw new IllegalAccessException("Queue is empty. Cannot peek.");
        }

        // Return the data from the front node
        return front.data;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to return the number of elements in the queue
    public int size() {
        return size;
    }
}
