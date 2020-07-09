package ly.potential.command.execution;

import ly.potential.canvas.Canvas;
import ly.potential.command.types.CanvasCommand;
import ly.potential.command.types.LineCommand;
import ly.potential.command.types.QuitCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommandExecutorTest {
    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        executor = new CommandExecutor();
    }

    @Test
    void executeShouldThrowIllegalArgumentExceptionIfFirstCommandIsNeitherQuitNorCanvas() {
        assertThrows(IllegalArgumentException.class, () -> executor.execute(null, new LineCommand("1", "2", "3", "4")));
    }

    @Test
    void executeShouldThrowIllegalArgumentExceptionIfCanvasCommandWasBeingUserAfterTheCanvasHasBeenCreated() {
        Canvas canvas = new Canvas(new String[0], 1, 1);

        assertThrows(IllegalArgumentException.class, () -> executor.execute(canvas, new CanvasCommand("2", "2")));
    }

    @Test
    void executeShouldReturnAnEmptyOptionalIfQuitCommandWasUsed() {
        Canvas canvas = new Canvas(new String[0], 1, 1);

        assertTrue(executor.execute(canvas, new QuitCommand()).isEmpty());
    }

    @Test
    void executeShouldThrowAnIllegalArgumentExceptionIfTheDrawerThrewANumberFormatException() {
        Canvas canvas = new Canvas(new String[0], 1, 1);

        assertThrows(IllegalArgumentException.class, () -> executor.execute(canvas, new LineCommand("2", "1", "o", "3")));
    }

    @Test
    void executeShouldReturnAnOptionalContainingTheNewCanvas() {
        String[] state = new String[]{"----", "|  |", "----"};
        var expected = Optional.of(new Canvas(state, 1, 2));

        assertEquals(expected, executor.execute(null, new CanvasCommand("2", "1")));
    }
}