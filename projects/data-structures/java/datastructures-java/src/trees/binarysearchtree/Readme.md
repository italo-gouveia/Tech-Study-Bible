# Explanation:

### Class Definition:
- **`BinarySearchTree:`** The main class representing the BST.
- **`Node:`** An inner class representing a node in the BST with value, left, and right children.

### Constructor:
Initializes the root of the BST as **`null`**.

### Insert:
- **`insert(int value):`** Public method to start the recursive insertion.
- **`insertRecursive(Node node, int value):`** Recursive helper method to find the correct place for the new value.

### Search:
- **`search(int value):`** Public method to start the recursive search.
- **`searchRecursive(Node node, int value):`** Recursive helper method to find the value in the BST.

### Find Min/Max:
- **`findMin():`** Finds the minimum value in the BST.
- **`findMax():`** Finds the maximum value in the BST.
- **`findMinRecursive(Node node):`** Recursive helper for minimum value.
- **`findMaxRecursive(Node node):`** Recursive helper for maximum value.

### In-Order Traversal:
- **`inOrderTraversal():`** Public method to perform in-order traversal (visits nodes in sorted order).
- **`inOrderRecursive(Node node):`** Recursive helper for in-order traversal.

### Delete:
- **`delete(int value):`** Public method to start the recursive deletion.
- **`deleteRecursive(Node node, int value):`** Recursive helper method to delete the node with the specified value.

### Find Min Node:
- **`findMinNode(Node node):`** Helper method to find the node with the minimum value in a subtree.

# When to Use:
- **Sorted Data:** When you need to maintain data in a sorted order and need efficient search, insertion, and deletion operations.
- **Efficient Lookups:** For scenarios where you frequently need to search for elements or maintain a dynamic set of elements with fast lookups.

# When Not to Use:
- **Unbalanced Trees:** If the tree becomes unbalanced (e.g., skewed), performance can degrade to O(n) for operations. In such cases, self-balancing trees like AVL trees or Red-Black trees are preferred.
- **Memory Overhead:** For applications with very large datasets, the recursive nature of BST operations can lead to significant memory overhead due to call stack usage.