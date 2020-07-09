package ly.potential.command;

import ly.potential.command.types.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {
    @Test
    void getCommandTest() {
        assertTrue(CommandFactory.getCommand("C", "20", "4") instanceof CanvasCommand);
        assertTrue(CommandFactory.getCommand("B", "20", "4", "o") instanceof BucketFillCommand);
        assertTrue(CommandFactory.getCommand("R", "20", "4", "1", "4") instanceof RectangleCommand);
        assertTrue(CommandFactory.getCommand("L", "20", "4", "1", "4") instanceof LineCommand);
        assertTrue(CommandFactory.getCommand("Q") instanceof QuitCommand);
    }

    @Test
    void getCommandShouldThrowAnIllegalStateExceptionIfNoCommandFound() {
        assertThrows(IllegalStateException.class, () -> CommandFactory.getCommand("NoCommand"));
    }
}