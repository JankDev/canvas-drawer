package ly.potential.command.parsing;

import ly.potential.command.Command;
import ly.potential.command.types.CanvasCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    private CommandParser parser = new CommandParser();

    @Test
    void parseShouldThrowIllegalArgumentExceptionIfInputIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("          "));
    }

    @Test
    void parseShouldTrimAndSplitTheInputBySpaceAndCreateACommandOutOfIt() {
        String input = "  C 1 2  ";

        Command expected = new CanvasCommand("1", "2");
        assertEquals(expected, parser.parse(input));
    }
}