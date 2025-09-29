package others.composite;

public class Main {
    public static void main(String[] args) {
        // Create leaf objects
        Component leaf1 = new Leaf("Leaf1");
        Component leaf2 = new Leaf("Leaf2");

        // Create composite object
        Composite composite1 = new Composite("Composite1");
        Composite composite2 = new Composite("Composite2");

        // Build the tree structure
        composite1.add(leaf1);
        composite1.add(leaf2);
        composite2.add(composite1);

        // Perform operations
        composite2.operation();
    }
}
