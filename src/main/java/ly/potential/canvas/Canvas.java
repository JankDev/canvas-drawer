package ly.potential.canvas;

import java.util.Arrays;
import java.util.Objects;

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
        return x >= 1 && x <= width && y >= 1 && y <= height;
    }

    @Override
    public String toString() {
        return String.join("\n", currentState) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Canvas canvas = (Canvas) o;
        return height == canvas.height &&
                width == canvas.width &&
                Arrays.equals(currentState, canvas.currentState);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.hashCode(currentState);
        return result;
    }
}
