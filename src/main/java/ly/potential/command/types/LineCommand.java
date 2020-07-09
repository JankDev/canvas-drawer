package ly.potential.command.types;

import ly.potential.canvas.drawing.Drawer;
import ly.potential.canvas.drawing.LineDrawer;
import ly.potential.command.Command;

public class LineCommand extends Command {
    public LineCommand(String ...args) {
        super(args);
    }

    @Override
    public Drawer getDrawer() {
        return new LineDrawer();
    }

    @Override
    public int getNumberOfArguments() {
        return 4;
    }
}
