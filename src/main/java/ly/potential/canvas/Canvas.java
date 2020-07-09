package ly.potential.canvas;

import java.util.Arrays;

public class Canvas {
    private final String[] currentState;
    private final int height;
    private final int width;

    public Canvas(String[] currentState, int height, int width) {
        this.currentState = currentState;
        this.height = height;
        this.width = width;
    }

    public String[] getCurrentState() {
        return Arrays.copyOf(currentState, currentState.length);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isPointInside(int x, int y) {
        return x >= 1 && x <= width - 2 && y >= 1 && y <= height - 2;
    }

    @Override
    public String toString() {
        return String.join("\n", currentState) + "\n";
    }
}
