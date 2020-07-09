package ly.potential.command.executing;

import ly.potential.canvas.Canvas;
import ly.potential.command.Command;
import ly.potential.command.types.CanvasCommand;

import java.util.Optional;

public class CommandExecutor {
    private Canvas currentCanvas = null;

    public Optional<String> execute(Command command) {
        if (currentCanvas == null && !(command instanceof CanvasCommand))
            throw new IllegalArgumentException("First command must be 'C'");

        if (currentCanvas != null && (command instanceof CanvasCommand))
            throw new IllegalArgumentException("Canvas already exists");

        return Optional.ofNullable(command.getDrawer())
                .map(drawer -> {
                    currentCanvas = drawer.draw(currentCanvas, command.getArguments());
                    return currentCanvas;
                })
                .map(Canvas::toString);
    }
}



