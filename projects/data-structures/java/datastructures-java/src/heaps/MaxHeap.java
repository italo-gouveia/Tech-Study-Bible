package heaps;

public class MaxHeap {
    private int[] heap; // Array to store heap elements
    private int size; // Current number of elements in the heap
    private int capacity; // Maximum capacity of the heap

    // Constructor to initialize an empty max heap with a specified capacity
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Method to return the index of the parent node of a given index
    private int parent(int index) {
        return (index - 1 ) / 2;
    }

    // Method to return the index of the left child node of a given index
    private int leftChild(int index) {
        return 2 * index + 2;
    }

    // Method to return the index of the right child node of a given index
    private int rightChild(int index){
        return 2 * index + 2;
    }

    // Method to swap two elements in the heap
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // Method to recursively heapify a subtree rooted at a given index
    // This assumes that the subtrees rooted at leftChild(index) and rightChild(index) are already max heaps
    private void heapify(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Compare with left child
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Compare with right child
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If largest is not the root, swap and recursively heapify the affected subtree
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    // Method to insert a new element into the heap
    public void insert(int element) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full. Cannot insert more elements.");
        }

        // Insert the new element at the end of the heap
        int currentIndex = size;
        heap[currentIndex] = element;
        size++;

        // Restore heap property by bubbling up the newly inserted element
        while (currentIndex != 0 && heap[parent(currentIndex)]< heap[currentIndex]) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // Method to extract the maximum element (root) from the heap
    public int extractMax() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty. Cannot extract maximum element.");
        }

        // Extract the root element (maximum element)
        int maxElement = heap[0];

        // Replace the root with the last element and heapify the root
        heap[0] = heap[size - 1];
        size--;
        heapify(0);

        return maxElement;
    }

    // Method to return the current size of the heap
    public int size() {
        return size;
    }

    // Method to check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to print the elements of the heap
    public void printHeap() {
        System.out.print("Heap: ");
        for (int i=0; i< size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

}
