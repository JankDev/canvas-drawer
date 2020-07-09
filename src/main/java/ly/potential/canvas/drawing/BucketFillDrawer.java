package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

import java.util.List;

public class BucketFillDrawer implements Drawer {
    private Canvas currentCanvas;

    @Override
    public Canvas draw(Canvas currentCanvas, String... args) {
        this.currentCanvas = currentCanvas;
        int x = Integer.parseInt(args[0]), y = Integer.parseInt(args[1]);
        char color = args[2].charAt(0);

        if (!currentCanvas.isPointInside(x, y))
            throw new IllegalStateException("The point must be inside the canvas");

        var newState = currentCanvas.getCurrentState();
        newState = fillCanvas(newState, x, y, color);

        return new Canvas(newState, currentCanvas.getHeight(), currentCanvas.getWidth());
    }

    /**
     * This function recursively fills all nearby points with the given color
     *
     * @param state The current state of the canvas
     * @param x The x coordinate of the point around which the area should be filled
     * @param y The y coordinate of the point around which the area should be filled
     * @param color The "color" which will be used for filling the area
     * @return the new state of the canvas with an area filled with the given color
     */
    private String[] fillCanvas(String[] state, int x, int y, char color) {
        final var coloredFields = List.of(LineDrawer.DRAWING_SIGN, CanvasDrawer.HORIZONTAL_BORDER, CanvasDrawer.VERTICAL_BORDER, color);

        if (!currentCanvas.isPointInside(x, y) || coloredFields.contains(state[y].charAt(x))) {
            return state;
        } else {
            var sb = new StringBuilder(state[y]);

            sb.setCharAt(x, color);
            state[y] = sb.toString();

            var left = fillCanvas(state, x - 1, y, color);
            var upperLeft = fillCanvas(left, x - 1, y - 1, color);
            var up = fillCanvas(upperLeft, x, y - 1, color);
            var upperRight = fillCanvas(up, x + 1, y - 1, color);
            var right = fillCanvas(upperRight, x + 1, y, color);
            var lowerRight = fillCanvas(right, x + 1, y + 1, color);
            var lower = fillCanvas(lowerRight, x, y + 1, color);

            return fillCanvas(lower, x - 1, y + 1, color);
        }
    }
}
