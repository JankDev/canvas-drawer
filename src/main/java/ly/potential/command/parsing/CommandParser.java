package ly.potential.command.parsing;

import ly.potential.command.Command;
import ly.potential.command.CommandFactory;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.util.function.Predicate.not;

public class CommandParser {
    public Command parse(String input) {
        return Optional.ofNullable(input)
                .filter(not(String::isBlank))
                .map(String::strip)
                .map(Pattern.compile(" ")::split)
                .map(arr -> {
                    String type = arr[0].toUpperCase();
                    String[] args = List.of(arr).subList(1, arr.length).toArray(new String[0]);

                    return CommandFactory.getCommand(type, args);
                })
                .orElseThrow(() -> new IllegalArgumentException("Command must not be empty"));
    }

}
