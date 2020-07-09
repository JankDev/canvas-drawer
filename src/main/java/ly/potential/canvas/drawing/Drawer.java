package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

public interface Drawer {
    Canvas draw(Canvas currentCanvas, String... args);
}

