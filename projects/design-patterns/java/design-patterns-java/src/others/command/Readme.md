## Command Design Pattern in Java

### Explanation

1. **Command Interface**: Defines an `execute` method that concrete commands will implement. It provides a common interface for executing commands.

2. **Concrete Commands**: Implement the `Command` interface and define the binding between a receiver (e.g., `Light`) and an action (e.g., `turnOn` or `turnOff`). Each command encapsulates a request to perform an action on the receiver.

3. **Receiver**: The class that performs the actual work (e.g., `Light`). It contains methods that can be called by commands.

4. **Invoker**: Stores a command and at some point asks the command to execute the request. It decouples the sender from the receiver.

5. **Client**: Creates instances of `ConcreteCommand`, sets the appropriate command on the `Invoker`, and triggers the command execution.

### How to Use

- **Use the Command Pattern** when you need to parameterize objects with operations, delay the execution of an operation, queue operations, or support undo/redo functionality.
- **Encapsulate requests** as objects to allow flexible command execution, including logging, queuing, and scheduling.

### How Not to Use

- **Do not use** the Command Pattern if the operation does not need to be encapsulated as an object, or if the commands are simple and do not benefit from the decoupling provided by the pattern.
- **Avoid overuse** in cases where it introduces unnecessary complexity, especially if the application does not require command history, undo/redo, or dynamic command changes.
