package ly.potential.canvas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {

    @Test
    void isPointInsideTest() {
        Canvas canvas = new Canvas(new String[0], 10, 10);

        assertTrue(canvas.isPointInside(9, 9));
        assertFalse(canvas.isPointInside(-1, 0));
        assertFalse(canvas.isPointInside(11, 9));
    }

    @Test
    void toStringShouldHaveAsManyLineBreaksAsTheStateHasRows() {
        String[] state = new String[]{"----", "|  |", "|  |", "----"};
        Canvas canvas = new Canvas(state, 2, 2);

        assertEquals(4, canvas.toString().chars().filter(ch -> ch == '\n').count());
    }
}