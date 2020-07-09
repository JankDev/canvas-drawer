package ly.potential.command.types;

import ly.potential.canvas.drawing.Drawer;
import ly.potential.command.Command;

public class QuitCommand extends Command {
    public QuitCommand(String ...args) {
        super(args);
    }

    @Override
    public Drawer getDrawer() {
        return null;
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }
}
