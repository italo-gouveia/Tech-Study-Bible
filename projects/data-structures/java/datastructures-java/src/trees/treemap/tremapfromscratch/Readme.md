## Explanation

### Node Class

- Represents a node in the Red-Black Tree with a key, value, left and right children, parent, and color.

### TreeMap Constructor

- Initializes the tree with a special sentinel node `TNULL`, which represents null leaves and is always black.

### Insertion (`put` method)

- Adds a key-value pair to the tree and maintains Red-Black Tree properties.
- Uses `fixInsert` to correct any violations of the Red-Black Tree properties after insertion.

### Fixing the Tree (`fixInsert` method)

- Adjusts the tree to maintain the Red-Black properties after insertion. This includes color changes and rotations.

### Rotations (`leftRotate` and `rightRotate` methods)

- Perform left and right rotations to maintain the balance of the tree.

### Searching (`searchTreeByKey` method)

- Finds a node by its key, used by the `get` method to retrieve values.

### Print Tree (`printTree` method)

- Displays the structure of the tree in a readable format.

## When to Use

- **Sorted Map:** When you need a map that maintains keys in sorted order and supports efficient retrieval of elements based on their keys.
- **Range Queries:** For scenarios where you need to perform range queries or need the smallest/largest entries efficiently.

## When Not to Use

- **Unsorted Map Requirements:** If you don't need sorted keys or if sorting adds unnecessary overhead, consider using a `HashMap`.
- **Memory Constraints:** For applications where memory usage is a concern, as Red-Black Trees can be more memory-intensive compared to simpler structures.

## Key Points

- **Red-Black Tree:** Ensures that the tree remains balanced with operations performing in O(log n) time complexity.
- **Balancing:** Automatic balancing is crucial for maintaining efficient performance in a `TreeMap`.

This implementation provides a foundational understanding of how a `TreeMap` works internally using a Red-Black Tree.

## TreeMap vs Red-Black Tree

### 1. TreeMap

- **Definition:** `TreeMap` is a Java class that implements the `NavigableMap` interface and is part of the Java Collections Framework.
- **Implementation:** Internally, `TreeMap` uses a Red-Black Tree to maintain the order of its keys. This means that while `TreeMap` itself is a high-level data structure providing map functionality, the underlying data structure used to store the elements is a Red-Black Tree.
- **Features:**
    - **Sorted Order:** Automatically maintains keys in sorted order.
    - **Navigable Methods:** Provides methods for navigation such as `firstEntry()`, `lastEntry()`, `ceilingEntry()`, and `floorEntry()`.
    - **Time Complexity:** Operations like insertion, deletion, and lookup have O(log n) time complexity because of the underlying Red-Black Tree structure.

### 2. Red-Black Tree

- **Definition:** A Red-Black Tree is a type of self-balancing binary search tree. It is a fundamental data structure used in computer science for efficient data storage and retrieval.
- **Characteristics:**
    - **Balance:** Ensures the tree remains balanced with certain properties that guarantee the tree's height is kept logarithmic relative to the number of elements. This balance is maintained through rules about the coloring of nodes and tree rotations.
- **Properties:**
    - Every node is either red or black.
    - The root is always black.
    - Red nodes cannot have red children (no two red nodes can be adjacent).
    - Every path from a node to its descendant NULL nodes must have the same number of black nodes.
- **Usage:** Red-Black Trees are used in various implementations of associative containers, including `TreeMap` in Java.

### Relationship

- **Implementation:** The `TreeMap` class in Java uses a Red-Black Tree to store its entries. This means that when you use a `TreeMap`, you are working with a Red-Black Tree under the hood.
- **Purpose:** The Red-Black Tree provides the underlying mechanism that allows `TreeMap` to maintain order and ensure efficient performance for its operations.

### Summary

- **TreeMap** is a high-level abstraction providing a sorted map interface in Java, and it uses a Red-Black Tree as its underlying implementation to manage key-value pairs.
- **Red-Black Tree** is a lower-level data structure used for balancing binary search trees and is implemented in various data structures including `TreeMap`.

In essence, while `TreeMap` and Red-Black Tree are not the same, the `TreeMap` is built on top of the Red-Black Tree data structure.
