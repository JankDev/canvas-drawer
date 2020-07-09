package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleDrawerTest {
    private RectangleDrawer drawer;

    @BeforeEach
    void setUp() {
        drawer = new RectangleDrawer();
    }

    @Test
    void drawShouldThrowIllegalArgumentExceptionIfArgsCannotBeParsed() {
        assertThrows(NumberFormatException.class, () -> drawer.draw(null, "1", "2", "o", "2"));
    }

    @Test
    void drawShouldThrowIllegalStateExceptionIfPointsOfRectangleAreOutsideTheCanvas() {
        Canvas canvas = new Canvas(new String[10], 8, 6);

        assertThrows(IllegalStateException.class, () -> drawer.draw(canvas, "1", "3", "7", "4"));
    }

    @Test
    void drawShouldThrowIllegalArgumentExceptionIfBottomRightCornerIfAboveOrOnTheLeftOfUpperLeftCorner(){
        assertThrows(IllegalArgumentException.class, () -> drawer.draw(null,"2","3","1","2"));
    }

    @Test
    void drawShouldReturnANewCanvasWithARectangleDrawnInside() {
        String[] state = new String[]{"------", "|    |", "|    |", "|    |", "------"};
        Canvas canvas = new Canvas(state, 3, 4);

        Canvas result = drawer.draw(canvas, "1", "1", "3", "3");

        String[] expectedState = new String[]{"------", "|XXX |", "|X X |", "|XXX |", "------"};
        assertArrayEquals(expectedState, result.getCurrentState());
    }
}