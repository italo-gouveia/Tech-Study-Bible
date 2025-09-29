package behavioral.templatemethod;

// Abstract class defining the template method
public abstract class AbstractClass {
    // Template method
    public final void templateMethod() {
        System.out.println("Starting the algorithm...");
        step1();
        step2();
        step3();
        System.out.println("Algorithm complete.");
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void step1();
    protected abstract void step2();

    // Concrete method with default implementation
    protected void step3() {
        System.out.println("Default implementation of step3");
    }
}
