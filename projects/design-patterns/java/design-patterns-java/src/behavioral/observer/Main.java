package behavioral.observer;

public class Main {
    public static void main(String[] args) {
        MessagePublisher publisher = new MessagePublisher();

        Observer user1 = new User("User1");
        Observer user2 = new User("User2");

        publisher.attach(user1);
        publisher.attach(user2);

        publisher.publish("Hello subscribers!");

        publisher.detach(user1);

        publisher.publish("New message for remaining subscribers.");
    }
}
