package ly.potential.command;

import ly.potential.canvas.drawing.Drawer;

import java.util.Arrays;

public abstract class Command {
    private final String[] arguments;

    protected Command(String ...arguments){
        if(arguments.length!= getNumberOfArguments())
            throw new IllegalArgumentException("Argument list must be of size " + getNumberOfArguments());

        this.arguments = Arrays.copyOf(arguments,arguments.length);
    }

    public String[] getArguments() {
        return arguments;
    }

    public abstract Drawer getDrawer();

    public abstract int getNumberOfArguments();

    public abstract String commandFormat();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Arrays.equals(arguments, command.arguments);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arguments);
    }
}