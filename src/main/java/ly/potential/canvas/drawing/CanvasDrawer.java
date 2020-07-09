package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;

public class CanvasDrawer implements Drawer {
    public static final String HORIZONTAL_BORDER = "-";
    public static final String VERTICAL_BORDER = "|";
    private static final int BORDER_MARGIN = 2;

    @Override
    public Canvas draw(Canvas currentCanvas, String... args) throws NumberFormatException, IllegalStateException {
        final int height = Integer.parseInt(args[1]);
        final int width = Integer.parseInt(args[0]);

        if (height < 1 || width < 1)
            throw new IllegalStateException("Canvas must be at least of size 1x1");

        final var stringBuilder = new StringBuilder();

        for (int row = 0; row < height + BORDER_MARGIN; row++) {
            if (isBorder(height, row)) {
                stringBuilder.append(HORIZONTAL_BORDER.repeat(width + BORDER_MARGIN));
            } else {
                stringBuilder.append(VERTICAL_BORDER).append(" ".repeat(width)).append(VERTICAL_BORDER);
            }
            stringBuilder.append('\n');
        }

        return new Canvas(stringBuilder.toString().lines().toArray(String[]::new), height, width);
    }

    private boolean isBorder(int height, int row) {
        return row == 0 || row == height + 1;
    }
}
