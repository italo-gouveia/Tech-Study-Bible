package creational.factorymethod;

public class ConcreteCreator1 implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProduct1();
    }
}
