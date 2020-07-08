package ly.potential.canvas;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Canvas {
    private String[] currentState;
    private int height;
    private int width;

    public Canvas() {
        currentState = null;
        height = 0;
        width = 0;
    }

    public Canvas(String[] currentState, int height, int width) {
        this.currentState = currentState;
        this.height = height;
        this.width = width;
    }

    public String[] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String[] currentState) {
        this.currentState = currentState;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isPointInside(int x, int y){
        return x > 0 && x < width && y > 0 && y < height;
    }

    @Override
    public String toString() {
        return String.join("\n", currentState) + "\n";
    }
}
