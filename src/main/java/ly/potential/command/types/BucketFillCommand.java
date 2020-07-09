package ly.potential.command.types;

import ly.potential.canvas.drawing.BucketFillDrawer;
import ly.potential.canvas.drawing.Drawer;
import ly.potential.command.Command;

public class BucketFillCommand extends Command {

    public BucketFillCommand(String ...args) {
        super(args);
    }

    @Override
    public Drawer getDrawer() {
        return new BucketFillDrawer();
    }

    @Override
    public int getNumberOfArguments() {
        return 3;
    }
}
