package heaps;

public class Main {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);

        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);
        maxHeap.insert(25);

        maxHeap.printHeap(); // Output: Heap: 30 25 15 10 20

        int maxElement = maxHeap.extractMax();
        System.out.println("Extracted max element: " + maxElement); // Output: Extracted max element: 30

        maxHeap.printHeap(); // Output: Heap: 25 20 15 10


        maxHeap.insert(24);
        maxHeap.insert(90);
        maxHeap.insert(34);
        maxHeap.insert(28);
        maxHeap.insert(32);


        int maxElement2 = maxHeap.extractMax();
        System.out.println("Extracted max element: " + maxElement2); // Output: Extracted max element: 90

        maxHeap.printHeap(); // Output: Heap: 34 32 25 28 24 15 20 10
    }
}
