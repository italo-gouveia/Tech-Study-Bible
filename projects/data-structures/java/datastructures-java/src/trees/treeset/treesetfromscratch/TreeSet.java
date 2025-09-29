package trees.treeset.treesetfromscratch;

public class TreeSet<E extends Comparable<E>> {
    // Inner class to represent a node in the Red-Black Tree
    private class Node {
        E element; // Element stored in the node
        Node left; // Left child
        Node right; // Right child
        Node parent; // Parent node
        boolean isRed; // Color of the node (true for red, false for black)

        Node(E element) {
            this.element = element;
            this.isRed = true; // New nodes are always red initially
        }
    }

    private Node root; // Root of the tree
    private Node TNULL; // Sentinel node used for leaves

    // Constructor to initialize the TreeSet
    public TreeSet() {
        TNULL = new Node(null);
        TNULL.isRed = false; // TNULL is always black
        root = TNULL;
    }

    // Add an element to the TreeSet
    public void add(E element) {
        Node node = new Node(element);
        Node y = null;
        Node x = this.root;

        // Find the correct position for the new node
        while (x != TNULL) {
            y = x;
            if (node.element.compareTo(x.element) < 0) {
                x = x.left;
            } else if (node.element.compareTo(x.element) > 0) {
                x = x.right;
            } else {
                return; // Element already exists, no duplicates allowed
            }
        }

        node.parent = y;
        if (y == null) {
            root = node; // Tree was empty
        } else if (node.element.compareTo(y.element) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.isRed = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Fix the Red-Black Tree properties after insertion
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.isRed) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.isRed) {
                    k.parent.isRed = false;
                    u.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.isRed) {
                    k.parent.isRed = false;
                    u.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.isRed = false;
    }

    // Rotate left
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Rotate right
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Check if the TreeSet contains a given element
    public boolean contains(E element) {
        Node node = searchTreeByKey(element);
        return node != TNULL;
    }

    // Search the tree for a given element
    private Node searchTreeByKey(E key) {
        Node node = this.root;
        while (node != TNULL) {
            int cmp = key.compareTo(node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return TNULL;
    }

    // Main method for testing
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Add elements to the TreeSet
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(15);
        treeSet.add(25);
        treeSet.add(10); // Duplicate, should not be added

        // Print if the TreeSet contains certain elements
        System.out.println("TreeSet contains 10: " + treeSet.contains(10)); // true
        System.out.println("TreeSet contains 20: " + treeSet.contains(20)); // true
        System.out.println("TreeSet contains 30: " + treeSet.contains(30)); // false
    }
}

