package others.command;

// Invoker class that invokes the command
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute(); // Execute the command
    }
}

