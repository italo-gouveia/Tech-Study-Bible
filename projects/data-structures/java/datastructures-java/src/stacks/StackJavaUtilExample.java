package stacks;

import java.util.Stack;

public class StackJavaUtilExample {
    public static void main(String[] args) {
        // Create a stack
        Stack<String> stack = new Stack<>();

        // Pushing elements onto the stack
        stack.push("Java");
        stack.push("Python");
        stack.push("C++");
        stack.push("JavaScript");

        // Displaying the stack
        System.out.println("Stack: " + stack);

        // Popping the top element
        String poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);

        // Displaying the stack after pop operation
        System.out.println("Stack after pop operation: " + stack);

        // Peeking at the top element
        String topElement = stack.peek();
        System.out.println("Top element: " + topElement);

        // Checking if the stack is empty
        System.out.println("Is the stack empty? " + stack.isEmpty());

        // Searching for an element in the stack
        String searchElement = "Python";
        int position = stack.search(searchElement);
        if (position != -1) {
            System.out.println(searchElement + " found at position " + position + " from the top of the stack.");
        } else {
            System.out.println(searchElement + " not found in the stack.");
        }
    }
}
