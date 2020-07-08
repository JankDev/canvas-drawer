package ly.potential.command.parsing;

import ly.potential.command.Command;
import ly.potential.command.CommandType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CommandParser implements Parser<Optional<Command>, String> {

    @Override
    public Optional<Command> parse(String input) {
        return Optional.ofNullable(input)
                .filter(s -> !s.isBlank())
                .map(String::strip)
                .map(Pattern.compile(" ")::split)
                .map(arr -> {
                    CommandType commandType = CommandType.valueOf(arr[0]);

                    if (commandType.getNumberOfArguments() != arr.length - 1)
                        throw new IllegalArgumentException("Argument list must be of size " + commandType.getNumberOfArguments());

                    return new Command(commandType, List.of(arr).subList(1, arr.length));
                });
    }

}
