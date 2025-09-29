package structural.decorator;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: " + coffee.getCost() + "; Description: " + coffee.getDescription());

        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Cost: " + milkCoffee.getCost() + "; Description: " + milkCoffee.getDescription());

        Coffee sugarMilkCoffee = new SugarDecorator(coffee);
        System.out.println("Cost: " + sugarMilkCoffee.getCost() + "; Description: " + sugarMilkCoffee.getDescription());
    }
}
