package ly.potential.command.types;

import ly.potential.canvas.drawing.Drawer;
import ly.potential.canvas.drawing.RectangleDrawer;
import ly.potential.command.Command;

public class RectangleCommand extends Command {

    public RectangleCommand(String ...args) {
        super(args);
    }

    @Override
    public Drawer getDrawer() {
        return new RectangleDrawer();
    }

    @Override
    public int getNumberOfArguments() {
        return 4;
    }
}
