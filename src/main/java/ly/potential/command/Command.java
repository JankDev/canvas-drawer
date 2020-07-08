package ly.potential.command;

import java.util.List;

public class Command {
    private final CommandType type;
    private final List<String> arguments;

    public Command(CommandType type, List<String> arguments){
        this.type = type;
        this.arguments = List.copyOf(arguments);
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getArguments() {
        return arguments;
    }
}