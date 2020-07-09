package ly.potential.command.execution;

import ly.potential.canvas.Canvas;
import ly.potential.command.Command;
import ly.potential.command.types.CanvasCommand;
import ly.potential.command.types.QuitCommand;

import java.util.Optional;

public class CommandExecutor {
    public Optional<Canvas> execute(final Canvas currentCanvas, final Command command) {
        if (currentCanvas == null && !isValidFirstCommand(command))
            throw new IllegalArgumentException("First command must be 'C' or 'Q'");

        if (isCanvasCommandAfterCanvasHasBeenCreated(command, currentCanvas))
            throw new IllegalArgumentException("Canvas already exists");

        return Optional.ofNullable(command.getDrawer())
                .map(drawer -> {
                    try {
                        return drawer.draw(currentCanvas, command.getArguments());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Command must be of format: " + command.commandFormat());
                    }
                });
    }

    private boolean isCanvasCommandAfterCanvasHasBeenCreated(final Command command, final Canvas currentCanvas) {
        return currentCanvas != null && command instanceof CanvasCommand;
    }

    private boolean isValidFirstCommand(final Command command) {
        return (command instanceof CanvasCommand || command instanceof QuitCommand);
    }
}



