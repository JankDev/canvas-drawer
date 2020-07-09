package ly.potential.canvas.drawing;

import ly.potential.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BucketFillDrawerTest {
    private BucketFillDrawer drawer;

    @BeforeEach
    void setUp() {
        drawer = new BucketFillDrawer();
    }

    @Test
    void drawShouldThrowIllegalStateExceptionIfPointIsOutsideCanvas() {
        Canvas canvas = new Canvas(null, 10, 5);

        assertThrows(IllegalStateException.class, () -> drawer.draw(canvas, "11", "6", "o"));
    }

    @Test
    void drawShouldReturnTheSameCanvasIfPointIsAlreadyColored() {
        String[] state = new String[]{"------", "|XXX |", "|X X |", "|XXX |", "------"};
        Canvas canvas = new Canvas(state, state.length - 2, state[0].length() - 2);

        String[] result = drawer.draw(canvas, "1", "1", "o").getCurrentState();
        assertArrayEquals(canvas.getCurrentState(), result);
    }

    @ParameterizedTest
    @MethodSource("fillSource")
    void drawShouldFillTheEntireAreaRestrictedByTheBorders(Canvas initial, Canvas expected, String x, String y) {
        assertArrayEquals(expected.getCurrentState(), drawer.draw(initial, x, y, "o").getCurrentState());
    }

    private static Stream<Arguments> fillSource() {
        return Stream.of(
                Arguments.of(
                        new Canvas(new String[]{"------", "|XXX |", "|X X |", "|XXX |", "------"}, 3, 4),
                        new Canvas(new String[]{"------", "|XXX |", "|XoX |", "|XXX |", "------"}, 3, 4),
                        "2", "2"
                ),
                Arguments.of(
                        new Canvas(new String[]{"-------", "|XX   |", "| X   |", "|XX   |", "-------"}, 3, 5),
                        new Canvas(new String[]{"-------", "|XXooo|", "| Xooo|", "|XXooo|", "-------"}, 3, 5),
                        "3", "3"
                )
        );
    }

}