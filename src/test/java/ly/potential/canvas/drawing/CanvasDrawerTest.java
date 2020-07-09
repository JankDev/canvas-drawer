package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CanvasDrawerTest {
    private CanvasDrawer drawer;

    @BeforeEach
    void setUp() {
        drawer = new CanvasDrawer();
    }

    @Test
    void drawShouldThrowNumberFormatExceptionIfArgumentsCouldNotBeParsed() {
        assertThrows(NumberFormatException.class, () -> drawer.draw(null, "r", "7"));
    }

    @Test
    void drawShouldThrowIllegalStateExceptionIfWithOrHeightAreLessThanOne() {
        assertThrows(IllegalStateException.class, () -> drawer.draw(null, "0", "1"));
    }

    @Test
    void drawShouldCreateACanvasWithAnDrawingAreaOfSizeWidthXHeightAndABorder() {
        Canvas canvas = drawer.draw(null, "5", "10");
        String[] state = canvas.getCurrentState();

        final int expectedWidth = 5;
        final int expectedHeight = 10;

        assertEquals(expectedHeight, canvas.getHeight());
        assertEquals(expectedWidth, canvas.getWidth());

        assertEquals(CanvasDrawer.HORIZONTAL_BORDER.repeat(expectedWidth + 2), state[0]);
        assertEquals(CanvasDrawer.HORIZONTAL_BORDER.repeat(expectedWidth + 2), state[expectedHeight + 1]);

        String leftBorder = Arrays.stream(state)
                .skip(1)
                .map(row -> row.substring(0, 1))
                .limit(expectedHeight)
                .collect(Collectors.joining(""));

        assertEquals(CanvasDrawer.VERTICAL_BORDER.repeat(expectedHeight), leftBorder);
    }
}