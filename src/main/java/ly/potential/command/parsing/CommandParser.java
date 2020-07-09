package ly.potential.command.parsing;

import ly.potential.command.Command;
import ly.potential.command.CommandFactory;

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
                    String type = arr[0];
                    String[] args = List.of(arr).subList(1, arr.length).toArray(new String[0]);

                    return CommandFactory.getCommand(type, args);
                });
    }

}
