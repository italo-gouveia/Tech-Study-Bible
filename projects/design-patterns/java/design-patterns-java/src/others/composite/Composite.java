package others.composite;

import java.util.ArrayList;
import java.util.List;

// Composite class that implements the Component interface and contains children
public class Composite implements Component {
    private List<Component> children = new ArrayList<>();
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    public void add(Component component) {
        children.add(component); // Add child components
    }

    public void remove(Component component) {
        children.remove(component); // Remove child components
    }

    @Override
    public void operation() {
        System.out.println("Composite " + name + " operation");
        for (Component child : children) {
            child.operation(); // Delegate the operation to child components
        }
    }
}

