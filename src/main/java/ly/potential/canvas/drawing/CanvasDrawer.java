package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

public class CanvasDrawer implements Drawer {
    @Override
    public Canvas draw(Canvas currentCanvas, String... args){
        final int height = Integer.parseInt(args[1]) + 2;
        final int width = Integer.parseInt(args[0]) + 2;

        var stringBuilder = new StringBuilder();

        for (int row = 0; row < height; row++) {
            if (row == 0 || row == height - 1) {
                stringBuilder.append("-".repeat(width));
            } else {
                stringBuilder.append("|");
                stringBuilder.append(" ".repeat(width - 2));
                stringBuilder.append("|");
            }
            stringBuilder.append('\n');
        }

        return new Canvas(stringBuilder.toString().lines().toArray(String[]::new), height, width);
    }
}
