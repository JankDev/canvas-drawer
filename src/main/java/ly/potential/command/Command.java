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
}