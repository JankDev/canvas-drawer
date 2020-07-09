package ly.potential.command;

import ly.potential.command.types.*;

public class CommandFactory {
    public static Command getCommand(String type,String ...args){
        return switch(type){
            case "C" -> new CanvasCommand(args);
            case "L" -> new LineCommand(args);
            case "R" -> new RectangleCommand(args);
            case "B" -> new BucketFillCommand(args);
            case "Q" -> new QuitCommand(args);
            default -> throw new IllegalStateException("No such command: " + type);
        };
    }
}
