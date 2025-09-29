package creational.singleton;

public class Singleton {
    // private static instance variable
    private static Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() {
        // Initialization code here
    }

    // static method to get the singleton instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // other methods and fields
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
