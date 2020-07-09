package ly.potential.command.types;

import ly.potential.canvas.drawing.CanvasDrawer;
import ly.potential.canvas.drawing.Drawer;
import ly.potential.command.Command;

public class CanvasCommand extends Command {
    public CanvasCommand(String ...args) {
        super(args);
    }

    @Override
    public Drawer getDrawer() {
        return new CanvasDrawer();
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}
