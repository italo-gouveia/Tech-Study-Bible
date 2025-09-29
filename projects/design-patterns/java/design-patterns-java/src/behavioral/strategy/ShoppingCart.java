package behavioral.strategy;

public class ShoppingCart {
    private int totalAmount;

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void pay(PaymentStrategy paymentMethod) {
        paymentMethod.pay(totalAmount);
    }
}
