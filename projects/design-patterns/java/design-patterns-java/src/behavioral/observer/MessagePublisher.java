package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Subject (Observable)
public class MessagePublisher {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void publish(String message) {
        notifyObservers(message);
    }
}
