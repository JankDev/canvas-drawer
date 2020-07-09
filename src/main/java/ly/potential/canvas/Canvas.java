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
        return x > 0 && x < width && y > 0 && y < height;
    }

    @Override
    public String toString() {
        return String.join("\n", currentState) + "\n";
    }
}
