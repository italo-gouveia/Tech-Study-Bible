package creational.factorymethod;

public class Main {
    public static void main(String[] args) {
        Creator creator1 = new ConcreteCreator1();
        Product product1 = creator1.createProduct();
        product1.showInfo();

        Creator creator2 = new ConcreteCreator2();
        Product product2 = creator2.createProduct();
        product2.showInfo();
    }
}