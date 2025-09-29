package queues;

public class Main {
    // Main method to demonstrate usage of the MyQueue class
    public static void main(String[] args) throws IllegalAccessException {
        // Create a new instance of MyQueue to store integers
        MyQueue<Integer> queue = new MyQueue<>();

        // Enqueue some elements into the queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Print the current size of the queue
        System.out.println("Queue size: " + queue.size()); // Output: 3

        // Peek at the front element of the queue without removing it
        System.out.println("Front element: " + queue.peek()); // Output: 10

        // Dequeue an element from the queue
        int removedElement = queue.dequeue();
        System.out.println("Removed element: " + removedElement); // Output: 10

        // Print the current size of the queue after dequeue operation
        System.out.println("Queue size after dequeue: " + queue.size()); // Output: 2
    }
}
