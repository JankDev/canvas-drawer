package ly.potential;

import ly.potential.command.executing.CommandExecutor;
import ly.potential.command.parsing.CommandParser;

import java.io.Console;

public class Shell {
    private final CommandParser commandParser;
    private final CommandExecutor commandExecutor;

    public Shell() {
        this.commandParser = new CommandParser();
        this.commandExecutor = new CommandExecutor();
    }

    void start(Console console) {
        while (true) {
            var userInput = console.readLine("enter command: ");

            try {
                commandParser.parse(userInput)
                        .flatMap(commandExecutor::execute)
                        .ifPresentOrElse(System.out::print, () -> {
                            System.exit(0); // safe to use as we don't have any resources to release
                        });
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getLocalizedMessage());
            }
        }
    }
}
