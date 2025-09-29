package behavioral.templatemethod;

public class Main {
    public static void main(String[] args) {
        AbstractClass algorithm = new ConcreteClass();
        algorithm.templateMethod();
    }
}
