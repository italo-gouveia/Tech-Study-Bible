package linkedilst.linkedlist;

import java.util.LinkedList;

public class LinkedListJavaUtilExample {

    public static void main(String[] args) {
        // Creating a LinkedList
        LinkedList<String> linkedList = new LinkedList<>();

        // Adding elements to the LinkedList
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        // Displaying the LinkedList
        System.out.println("LinkedList: " + linkedList);

        // Adding an element at the beginning
        linkedList.addFirst("Orange");
        System.out.println("After adding at first: " + linkedList);

        // Adding an element at the end
        linkedList.addLast("Grapes");
        System.out.println("After adding at last: " + linkedList);

        // Getting the first and last elements
        String first = linkedList.getFirst();
        String last = linkedList.getLast();
        System.out.println("First element: " + first);
        System.out.println("Last element: " + last);

        // Removing elements
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("After removing first and last: " + linkedList);

        // Adding element at a specific index
        linkedList.add(2, "Mango");
        System.out.println("After adding at index 2: " + linkedList);

        // Removing element at a specific index
        linkedList.remove(1);
        System.out.println("After removing element at index 1: " + linkedList);

        // Checking if an element exists
        boolean exists = linkedList.contains("Apple");
        System.out.println("Does 'Apple' exist in the list? " + exists);

        // Size of the LinkedList
        int size = linkedList.size();
        System.out.println("Size of the LinkedList: " + size);

        // Iterating through the LinkedList
        System.out.print("Elements of the LinkedList: ");
        for (String element : linkedList) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
