package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

import java.util.Arrays;

public class RectangleDrawer implements Drawer {
    @Override
    public Canvas draw(Canvas currentCanvas, String... args) {
        final var parsedArgs = Arrays.stream(args)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        final var newState = currentCanvas.getCurrentState();

        Integer x1 = parsedArgs[0], y1 = parsedArgs[1];
        Integer x2 = parsedArgs[2], y2 = parsedArgs[3];
        
        if (!currentCanvas.isPointInside(x1, y1) || !currentCanvas.isPointInside(x2, y2))
            throw new IllegalStateException("All points must be inside the canvas");


        for (int i = y1; i <= y2; i++) {
            var sb = new StringBuilder(newState[i]);

            if (i == y1 || i == y2) {
                sb.replace(x1, x2 + 1, "X".repeat(x2 - x1 + 1));
            } else {
                sb.setCharAt(x1, 'X');
                sb.setCharAt(x2, 'X');
            }

            newState[i] = sb.toString();
        }

        return new Canvas(newState, currentCanvas.getHeight(), currentCanvas.getWidth());
    }
}
