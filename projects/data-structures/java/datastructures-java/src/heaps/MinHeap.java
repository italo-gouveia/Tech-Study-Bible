package heaps;

/**
 * MinHeap - A complete binary tree where parent nodes are always smaller than or equal to their children.
 * 
 * Visual Example:
 * 
 *        10          ← Root (smallest element)
 *       /  \
 *     20    15
 *    /  \  /  \
 *  30  25 40  35
 * 
 * Stored as array: [10, 20, 15, 30, 25, 40, 35]
 * 
 * Key Operations:
 * - Insert: O(log n) - Add element and bubble up
 * - Extract Min: O(log n) - Remove root and bubble down
 * - Peek: O(1) - Get root without removing
 * 
 * Use Cases:
 * - Priority queue (lowest priority first)
 * - Finding K smallest elements
 * - Dijkstra's algorithm (shortest path)
 */
public class MinHeap {
    private int[] heap;      // Array to store heap elements
    private int size;        // Current number of elements
    private int capacity;    // Maximum capacity

    /**
     * Constructor - Initialize empty min heap
     * 
     * @param capacity Maximum number of elements
     */
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    /**
     * Get parent index of a given node
     * 
     * Formula: parent(i) = (i - 1) / 2
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Get left child index of a given node
     * 
     * Formula: leftChild(i) = 2 * i + 1
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Get right child index of a given node
     * 
     * Formula: rightChild(i) = 2 * i + 2
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
     * Heapify (bubble down) - Restore min-heap property by moving element down
     * 
     * Visual Example:
     * 
     * Before (violates heap property):
     *        50
     *       /  \
     *     20    15    ← 50 is larger than children
     * 
     * After heapify:
     *        15        ← Swapped with smaller child
     *       /  \
     *     20    50
     * 
     * @param index Starting index to heapify from
     */
    private void heapify(int index) {
        int smallest = index;        // Assume current node is smallest
        int left = leftChild(index);
        int right = rightChild(index);

        // Compare with left child
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        // Compare with right child
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // If current node is not the smallest, swap and continue
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);  // Recursively heapify the affected subtree
        }
    }

    /**
     * Insert a new element into the heap
     * 
     * Algorithm:
     * 1. Add element at the end (last position)
     * 2. Bubble up: Compare with parent, swap if parent is larger
     * 3. Repeat until heap property is restored
     * 
     * Visual Example: Insert 5
     * 
     * Step 1: Add at end
     *        10
     *       /  \
     *     20    15
     *    /  \  /  \
     *  30  25 40  35
     *              \
     *              5   ← New element
     * 
     * Step 2: Compare 5 with parent (35). 5 < 35, swap!
     *        10
     *       /  \
     *     20    15
     *    /  \  /  \
     *  30  25 40   5   ← 5 moved up
     * 
     * Step 3: Compare 5 with parent (15). 5 < 15, swap!
     *        10
     *       /  \
     *     20     5    ← 5 moved up again
     *    /  \  /  \
     *  30  25 40  15
     * 
     * Step 4: Compare 5 with parent (10). 5 < 10, swap!
     *         5       ← 5 is now root!
     *       /  \
     *     20    10
     *    /  \  /  \
     *  30  25 40  15
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
        while (currentIndex != 0 && heap[parent(currentIndex)] > heap[currentIndex]) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    /**
     * Extract and return the minimum element (root)
     * 
     * Algorithm:
     * 1. Save the root (this is the min we return)
     * 2. Move last element to root
     * 3. Bubble down: Compare with children, swap with smaller child
     * 4. Repeat until heap property is restored
     * 
     * Visual Example: Extract min
     * 
     * Initial:
     *        10          ← We want to remove this
     *       /  \
     *     20    15
     * 
     * Step 1: Save 10, move last element (35) to root
     *        35          ← Last element moved here
     *       /  \
     *     20    15
     * 
     * Step 2: Compare 35 with children. 15 is smaller, swap!
     *        15          ← Swapped with smaller child
     *       /  \
     *     20    35
     * 
     * Return: 10
     * 
     * @return Minimum element in the heap
     * @throws IllegalStateException if heap is empty
     */
    public int extractMin() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty. Cannot extract minimum element.");
        }

        // Step 1: Save the root (minimum element)
        int minElement = heap[0];

        // Step 2: Move last element to root
        heap[0] = heap[size - 1];
        size--;

        // Step 3: Bubble down - restore heap property
        if (size > 0) {
            heapify(0);
        }

        return minElement;
    }

    /**
     * Get the minimum element without removing it
     * 
     * @return Minimum element (root)
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
     * Example output: "Heap: 10 20 15 30 25"
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
     *        10
     *       /  \
     *     20    15
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


