# Explanation:

The Decorator pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class.

# Key Participants in Decorator Pattern:
**Component:** 
* Defines the interface for objects that can have responsibilities added to them dynamically.

**Concrete Component:** 
* Defines an object to which additional responsibilities can be added.

**Decorator:** 
* Maintains a reference to a Component object and defines an interface that conforms to Component's interface, adding additional responsibilities.

**Concrete Decorators:** 
* Add responsibilities to the Component.

# When to Use the Decorator Pattern:

* **To add responsibilities to objects dynamically:** 
Instead of subclassing to add behavior, you can use decorators to add specific behaviors to individual objects at runtime.

* **When subclassing would result in a large number of subclasses:** 
Decorators allow you to mix and match responsibilities instead of creating a new subclass for every combination.

* **When you want to extend behavior without modifying existing code:** 
Decorators offer a flexible alternative to subclassing for extending functionality.

# When Not to Use the Decorator Pattern:
* **When the core functionality of objects should not be modified:** If you need to keep the core class simple and unchanged, consider other patterns like Strategy or Adapter.

* **When there are many layers of wrappers:** Decorators can lead to a proliferation of small, specialized classes if used excessively.

----
In summary, the Decorator pattern is a powerful tool for adding responsibilities to objects dynamically, maintaining flexibility, and minimizing code duplication. It's widely used in frameworks where behaviors need to be added or removed dynamically.