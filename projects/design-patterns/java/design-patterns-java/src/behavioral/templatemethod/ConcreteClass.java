package behavioral.templatemethod;

// Concrete subclass implementing abstract methods
public class ConcreteClass extends AbstractClass {
    @Override
    protected void step1() {
        System.out.println("Step 1 completed");
    }

    @Override
    protected void step2() {
        System.out.println("Step 2 completed");
    }
}
