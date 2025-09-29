package stacks;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();

        // Pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Peeking the top element
        System.out.println("Top element: " + stack.peek());

        // Popping elements from the stack
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());

        // Checking if stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Popping the last element
        System.out.println("Popped element: " + stack.pop());

        // Trying to pop from an empty stack
        // System.out.println("Popped element: " + stack.pop()); // Uncomment to test EmptyStackException
    }
}
