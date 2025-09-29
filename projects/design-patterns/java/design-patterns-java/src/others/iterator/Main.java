package others.iterator;

public class Main {
    public static void main(String[] args) {
        ConcreteCollection<String> collection = new ConcreteCollection<>();

        // Add items to the collection
        collection.add("Item 1");
        collection.add("Item 2");
        collection.add("Item 3");

        // Retrieve an iterator and use it to iterate over the collection
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); // Print each item
        }
    }
}
