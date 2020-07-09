package ly.potential;

import ly.potential.canvas.Canvas;
import ly.potential.command.Command;
import ly.potential.command.execution.CommandExecutor;
import ly.potential.command.parsing.CommandParser;

import java.io.Console;

public class Shell {
    private final CommandParser commandParser;
    private final CommandExecutor commandExecutor;

    private Canvas currentCanvas = null;

    public Shell() {
        this.commandParser = new CommandParser();
        this.commandExecutor = new CommandExecutor();
    }

    void start(Console console) {
        while (true) {
            var userInput = console.readLine("enter command: ");

            try {
                Command command = commandParser.parse(userInput);
                commandExecutor.execute(currentCanvas, command)
                        .ifPresentOrElse(canvas -> {
                            currentCanvas = canvas;
                            System.out.print(canvas);
                        }, () -> {
                            System.exit(0); // safe to use as we don't have any resources to release
                        });
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println(exception.getLocalizedMessage());
            }
        }
    }
}
