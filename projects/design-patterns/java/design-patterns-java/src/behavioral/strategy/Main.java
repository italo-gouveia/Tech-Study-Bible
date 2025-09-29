package behavioral.strategy;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setTotalAmount(100);

        // Payment using credit card
        PaymentStrategy creditCardPayment = new CreditCardStrategy("John Doe", "1234567890123456");
        cart.pay(creditCardPayment);

        // Payment using PayPal
        PaymentStrategy paypalPayment = new PayPalStrategy("john.doe@example.com");
        cart.pay(paypalPayment);
    }
}
