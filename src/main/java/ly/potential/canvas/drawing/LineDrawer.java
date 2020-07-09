package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

import java.util.Arrays;

public class LineDrawer implements Drawer {
    public static final String DRAWING_SIGN = "X";

    @Override
    public Canvas draw(Canvas currentCanvas, String... args) {
        final var parsedArgs = Arrays.stream(args)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        final int x1 = parsedArgs[0], y1 = parsedArgs[1], x2 = parsedArgs[2], y2 = parsedArgs[3];

        if (!currentCanvas.isPointInside(x1, y1) || !currentCanvas.isPointInside(x2, y2))
            throw new IllegalStateException("All points must be inside the canvas");

        final boolean isVertical = x2 - x1 == 0;

        final var newState = currentCanvas.getCurrentState();
        if (isVertical) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                var sb = new StringBuilder(newState[i]);
                sb.setCharAt(x1, DRAWING_SIGN.charAt(0));

                newState[i] = sb.toString();
            }
        } else {
            int min = Math.min(x1, x2);
            int max = Math.max(x1, x2);
            int diff = Math.abs(x2 - x1 + 1);

            newState[y1] = newState[y1].substring(0, min) + DRAWING_SIGN.repeat(diff) + newState[y1].substring(max + 1);
        }

        return new Canvas(newState, currentCanvas.getHeight(), currentCanvas.getWidth());
    }
}
