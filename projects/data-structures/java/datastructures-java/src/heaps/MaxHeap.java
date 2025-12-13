package heaps;

/**
 * MaxHeap - A complete binary tree where parent nodes are always greater than or equal to their children.
 * 
 * Visual Example:
 * 
 *        50          ← Root (largest element)
 *       /  \
 *     30    40
 *    /  \  /  \
 *  20  10 25  35
 * 
 * Stored as array: [50, 30, 40, 20, 10, 25, 35]
 * 
 * Key Operations:
 * - Insert: O(log n) - Add element and bubble up
 * - Extract Max: O(log n) - Remove root and bubble down
 * - Peek: O(1) - Get root without removing
 */
public class MaxHeap {
    private int[] heap;      // Array to store heap elements
    private int size;        // Current number of elements
    private int capacity;    // Maximum capacity

    /**
     * Constructor - Initialize empty max heap
     * 
     * @param capacity Maximum number of elements
     */
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    /**
     * Get parent index of a given node
     * 
     * Formula: parent(i) = (i - 1) / 2
     * 
     * Example: Node at index 3
     *   Parent = (3 - 1) / 2 = 1
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Get left child index of a given node
     * 
     * Formula: leftChild(i) = 2 * i + 1
     * 
     * Example: Node at index 1
     *   Left child = 2 * 1 + 1 = 3
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Get right child index of a given node
     * 
     * Formula: rightChild(i) = 2 * i + 2
     * 
     * Example: Node at index 1
     *   Right child = 2 * 1 + 2 = 4
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * Swap two elements in the heap
     */
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Heapify (bubble down) - Restore max-heap property by moving element down
     * 
     * Visual Example:
     * 
     * Before (violates heap property):
     *        10
     *       /  \
     *     30    40    ← 10 is smaller than children
     * 
     * After heapify:
     *        40        ← Swapped with larger child
     *       /  \
     *     30    10
     * 
     * @param index Starting index to heapify from
     */
    private void heapify(int index) {
        int largest = index;           // Assume current node is largest
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

        // If current node is not the largest, swap and continue
        if (largest != index) {
            swap(index, largest);
            heapify(largest);  // Recursively heapify the affected subtree
        }
    }

    /**
     * Insert a new element into the heap
     * 
     * Algorithm:
     * 1. Add element at the end (last position)
     * 2. Bubble up: Compare with parent, swap if parent is smaller
     * 3. Repeat until heap property is restored
     * 
     * Visual Example: Insert 45
     * 
     * Step 1: Add at end
     *        50
     *       /  \
     *     30    40
     *    /  \  /  \
     *  20  10 25  35
     *              \
     *              45  ← New element
     * 
     * Step 2: Compare 45 with parent (40). 45 > 40, swap!
     *        50
     *       /  \
     *     30    45    ← 45 moved up
     *    /  \  /  \
     *  20  10 25  40 35
     * 
     * Step 3: Compare 45 with parent (50). 45 < 50, stop!
     * 
     * @param element Value to insert
     * @throws IllegalStateException if heap is full
     */
    public void insert(int element) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full. Cannot insert more elements.");
        }

        // Step 1: Add at the end
        int currentIndex = size;
        heap[currentIndex] = element;
        size++;

        // Step 2: Bubble up - compare with parent and swap if needed
        while (currentIndex != 0 && heap[parent(currentIndex)] < heap[currentIndex]) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    /**
     * Extract and return the maximum element (root)
     * 
     * Algorithm:
     * 1. Save the root (this is the max we return)
     * 2. Move last element to root
     * 3. Bubble down: Compare with children, swap with larger child
     * 4. Repeat until heap property is restored
     * 
     * Visual Example: Extract max
     * 
     * Initial:
     *        50          ← We want to remove this
     *       /  \
     *     30    40
     * 
     * Step 1: Save 50, move last element (35) to root
     *        35          ← Last element moved here
     *       /  \
     *     30    40
     * 
     * Step 2: Compare 35 with children. 40 is larger, swap!
     *        40          ← Swapped with larger child
     *       /  \
     *     30    35
     * 
     * Return: 50
     * 
     * @return Maximum element in the heap
     * @throws IllegalStateException if heap is empty
     */
    public int extractMax() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty. Cannot extract maximum element.");
        }

        // Step 1: Save the root (maximum element)
        int maxElement = heap[0];

        // Step 2: Move last element to root
        heap[0] = heap[size - 1];
        size--;

        // Step 3: Bubble down - restore heap property
        if (size > 0) {
            heapify(0);
        }

        return maxElement;
    }

    /**
     * Get the maximum element without removing it
     * 
     * @return Maximum element (root)
     * @throws IllegalStateException if heap is empty
     */
    public int peek() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        return heap[0];
    }

    /**
     * Get current number of elements
     * 
     * @return Size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * Check if heap is empty
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Print the heap as an array
     * 
     * Example output: "Heap: 50 30 40 20 10"
     */
    public void printHeap() {
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /**
     * Print the heap in tree format (for visualization)
     * 
     * Example output:
     *        50
     *       /  \
     *     30    40
     */
    public void printHeapTree() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }
        printHeapTreeHelper(0, "", true);
    }

    private void printHeapTreeHelper(int index, String prefix, boolean isLast) {
        if (index >= size) {
            return;
        }

        System.out.println(prefix + (isLast ? "└── " : "├── ") + heap[index]);

        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size || right < size) {
            String newPrefix = prefix + (isLast ? "    " : "│   ");
            if (left < size) {
                printHeapTreeHelper(left, newPrefix, right >= size);
            }
            if (right < size) {
                printHeapTreeHelper(right, newPrefix, true);
            }
        }
    }
}
