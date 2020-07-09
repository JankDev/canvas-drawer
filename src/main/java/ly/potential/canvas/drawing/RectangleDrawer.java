package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

import java.util.Arrays;

public class RectangleDrawer implements Drawer {
    @Override
    public Canvas draw(Canvas currentCanvas, String... args) throws NumberFormatException {
        final var parsedArgs = Arrays.stream(args)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        Integer x1 = parsedArgs[0], y1 = parsedArgs[1];
        Integer x2 = parsedArgs[2], y2 = parsedArgs[3];

        if (!isValidRectangle(x1, y1, x2, y2)) {
            throw new IllegalArgumentException("Invalid rectangle");
        }

        if (!currentCanvas.isPointInside(x1, y1) || !currentCanvas.isPointInside(x2, y2)) {
            throw new IllegalStateException("All points must be inside the canvas");
        }

        final var newState = currentCanvas.getCurrentState();

        for (int row = y1; row <= y2; row++) {
            var sb = new StringBuilder(newState[row]);

            if (isRectangleBorder(y1, y2, row)) {
                sb.replace(x1, x2 + 1, "X".repeat(x2 - x1 + 1));
            } else {
                sb.setCharAt(x1, 'X');
                sb.setCharAt(x2, 'X');
            }

            newState[row] = sb.toString();
        }

        return new Canvas(newState, currentCanvas.getHeight(), currentCanvas.getWidth());
    }

    private boolean isValidRectangle(int x1, int y1, int x2, int y2) {
        return x2 > x1 && y2 > y1;
    }

    private boolean isRectangleBorder(Integer y1, Integer y2, int row) {
        return row == y1 || row == y2;
    }
}
