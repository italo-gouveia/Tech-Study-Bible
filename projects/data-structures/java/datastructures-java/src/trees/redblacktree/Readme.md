## Explanation

### Creation
- **Red-Black Tree** is created by defining a `Node` class with key, color, and pointers to left, right, and parent nodes. The `RedBlackTree` class manages the root node and provides methods for insertion, deletion, and traversal.

### Insertion
- When inserting elements using the `insert()` method, the tree performs a standard binary search tree (BST) insert. After insertion, the tree is balanced using rotations and color changes.

### Rotations
- **Left Rotation** (`leftRotate()`): Balances the tree when the right subtree is heavier.
- **Right Rotation** (`rightRotate()`): Balances the tree when the left subtree is heavier.
- **Balancing Insertion**: Handles cases where a single rotation is insufficient, using color changes and rotations to maintain balance.

### Deletion
- The `deleteNode()` method removes a node and balances the tree using a combination of color changes and rotations.

### Printing
- The `preorder()` method performs a pre-order traversal of the tree to display elements.

## When to Use Red-Black Tree

- **Balanced Tree with Efficient Operations:** For a self-balancing tree with O(log n) time complexity for operations.
- **Performance Stability:** When maintaining balanced performance with frequent modifications is crucial.
- **Sorted Data:** Useful for sorted data with efficient searching, insertion, and deletion.

## When Not to Use Red-Black Tree

- **Simple Use Cases:** For simpler data structures when performance is not a primary concern.
- **Memory Constraints:** When additional memory usage for maintaining balance is a concern.
- **Complexity:** If simpler structures like AVL Trees or Hash Tables are preferable.

## Summary

- **Red-Black Tree** is a self-balancing binary search tree with O(log n) time complexity for operations, maintaining balance through rotations and color changes.
- It is particularly useful when frequent insertions and deletions are required while maintaining sorted order.
