package others.command;

// Concrete Command to turn off the light
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff(); // Delegate the command to the Light object
    }
}