package creational.factorymethod;

public class ConcreteCreator2 implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProduct2();
    }
}
