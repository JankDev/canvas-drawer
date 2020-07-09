package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineDrawerTest {
    private LineDrawer drawer;

    @BeforeEach
    void setUp() {
        drawer = new LineDrawer();
    }

    @Test
    void drawShouldThrowIllegalStateExceptionIfPointsIsOutsideCanvas() {
        Canvas canvas = new Canvas(null, 10, 10);

        assertThrows(IllegalStateException.class, () -> drawer.draw(canvas, "11", "9", "11", "7"));
    }

    @Test
    void drawShouldDrawAVerticalLineIfTheYCoordinatesVary() {
        String[] state = new String[]{"------", "|    |", "|    |", "|    |", "------"};
        Canvas canvas = new Canvas(state, state.length - 2, state[0].length() - 2);

        String[] expected = new String[]{"------", "|X   |", "|X   |", "|    |", "------"};

        assertArrayEquals(expected, drawer.draw(canvas, "1", "1", "1", "2").getCurrentState());
    }

    @Test
    void drawShouldDrawAHorizontalLineIfTheXCoordinatesVary() {
        String[] state = new String[]{"------", "|    |", "|    |", "|    |", "------"};
        Canvas canvas = new Canvas(state, state.length - 2, state[0].length() - 2);

        String[] expected = new String[]{"------", "|XX  |", "|    |", "|    |", "------"};

        assertArrayEquals(expected, drawer.draw(canvas, "1", "1", "2", "1").getCurrentState());
    }

    @Test
    void drawShouldReplaceAnyColoredFieldsWithX() {
        String[] state = new String[]{"------", "|XXoo|", "|XXoo|", "|oooo|", "------"};
        Canvas canvas = new Canvas(state, state.length - 2, state[0].length() - 2);

        String[] expected = new String[]{"------", "|XXXX|", "|XXoo|", "|oooo|", "------"};

        assertArrayEquals(expected, drawer.draw(canvas, "3", "1", "4", "1").getCurrentState());
    }
}