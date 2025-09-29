package trees.treemap.tremapfromscratch;

public class TreeMap<K extends Comparable<K>, V> {
    // Inner class to represent a node in the Red-Black Tree
    private class Node {
        K key; // Key of the node
        V value; // Value of the node
        Node left; // Left child
        Node right; // Right child
        Node parent; // Parent node
        boolean isRed; // Color of the node (true for red, false for black)

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.isRed = true; // New nodes are always red initially
        }
    }

    private Node root; // Root of the tree
    private Node TNULL; // Sentinel node used for leaves

    // Constructor to initialize the TreeMap
    public TreeMap() {
        TNULL = new Node(null, null);
        TNULL.isRed = false; // TNULL is always black
        root = TNULL;
    }

    // Preorder traversal to print the tree
    public void printTree() {
        printTreeHelper(this.root, "", true);
    }

    // Helper method for printing the tree
    private void printTreeHelper(Node node, String indent, boolean last) {
        if (node != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = node.isRed ? "RED" : "BLACK";
            System.out.println(node.key + "(" + sColor + ")");
            printTreeHelper(node.left, indent, false);
            printTreeHelper(node.right, indent, true);
        }
    }

    // Insert a key-value pair into the tree
    public void put(K key, V value) {
        Node node = new Node(key, value);
        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key.compareTo(y.key) < 0) {
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

    // Fix the Red-Black Tree after an insertion
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

    // Get the value associated with a key
    public V get(K key) {
        Node node = searchTreeByKey(key);
        if (node != TNULL) {
            return node.value;
        }
        return null;
    }

    // Search the tree for a given key
    private Node searchTreeByKey(K key) {
        Node node = this.root;
        while (node != TNULL) {
            if (key.equals(node.key)) {
                return node;
            }
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return TNULL;
    }

    // Main method for testing
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // Insert values into the TreeMap
        treeMap.put(10, "Ten");
        treeMap.put(20, "Twenty");
        treeMap.put(30, "Thirty");
        treeMap.put(15, "Fifteen");
        treeMap.put(25, "Twenty Five");

        // Print the TreeMap
        System.out.println("TreeMap structure:");
        treeMap.printTree();

        // Retrieve values
        System.out.println("\nRetrieve values:");
        System.out.println("Value for key 15: " + treeMap.get(15));
        System.out.println("Value for key 25: " + treeMap.get(25));
        System.out.println("Value for key 100: " + treeMap.get(100)); // Should return null
    }
}
