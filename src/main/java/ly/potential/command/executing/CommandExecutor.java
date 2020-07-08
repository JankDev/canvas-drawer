package ly.potential.command.executing;

import ly.potential.canvas.Canvas;
import ly.potential.command.Command;
import ly.potential.command.CommandType;

import java.util.List;
import java.util.Optional;

public class CommandExecutor {
    public static final String DRAWING_SIGN = "X";
    private Canvas canvas = null;

    public Optional<String> execute(Command command) {
        if (canvas == null && !command.getType().equals(CommandType.C))
            throw new IllegalArgumentException("First command must be 'C'");

        return switch (command.getType()) {
            case C -> {
                var args = command.getArguments().stream()
                        .map(i -> {
                            try {
                                return Integer.parseInt(i);
                            } catch (NumberFormatException numberFormatException) {
                                throw new IllegalArgumentException("Arguments must be numbers!");
                            }
                        })
                        .toArray(Integer[]::new);

                var stringBuilder = new StringBuilder("");

                final int height = args[1] + 2;
                final int width = args[0] + 2;

                for (int row = 0; row < height; row++) {
                    if (row == 0 || row == height - 1) {
                        stringBuilder.append("-".repeat(width));
                    } else {
                        stringBuilder.append("|");
                        stringBuilder.append(" ".repeat(width - 2));
                        stringBuilder.append("|");
                    }
                    stringBuilder.append('\n');
                }

                canvas = new Canvas(stringBuilder.toString().lines().toArray(String[]::new), height, width);

                yield Optional.of(stringBuilder.toString());
            }
            case L -> {
                final var args = command.getArguments().stream()
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

                final int x1 = args[0], y1 = args[1], x2 = args[2], y2 = args[3];
                final boolean isVertical = x2 - x1 == 0;

                final var newState = canvas.getCurrentState();
                if (isVertical) {
                    for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                        var sb = new StringBuilder(newState[i]);
                        sb.setCharAt(x1, 'X');
                        newState[i] = sb.toString();
                    }
                } else {
                    int min = Math.min(x1, x2);
                    int max = Math.max(x1, x2);
                    int diff = Math.abs(x2 - x1 + 1);
                    newState[y1] = newState[y1].substring(0, min) + DRAWING_SIGN.repeat(diff) + newState[y1].substring(max + 1);
                }

                canvas.setCurrentState(newState);

                yield Optional.of(canvas.toString());
            }
            case R -> {
                final var args = command.getArguments().stream()
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                final var newState = canvas.getCurrentState();

                Integer y1 = args[1];
                Integer y2 = args[3];
                for (int i = y1; i <= y2; i++) {
                    var sb = new StringBuilder(newState[i]);

                    Integer x1 = args[0];
                    Integer x2 = args[2];
                    if (i == y1 || i == y2) {
                        sb.replace(x1, x2 + 1, "X".repeat(x2 - x1 + 1));
                    } else {
                        sb.setCharAt(x1, 'X');
                        sb.setCharAt(x2, 'X');
                    }

                    newState[i] = sb.toString();
                }

                canvas.setCurrentState(newState);
                yield Optional.of(canvas.toString());
            }
            case B -> {
                int x = Integer.parseInt(command.getArguments().get(0)), y = Integer.parseInt(command.getArguments().get(1));
                char color = command.getArguments().get(2).charAt(0);

                var newState = canvas.getCurrentState();
                newState = colorBoard(newState,x,y,color);

                canvas.setCurrentState(newState);
                yield Optional.of(canvas.toString());
            }
            case Q -> Optional.empty();
        };

    }

    public String[] colorBoard(String[] state, int x, int y, char color) {
        final var forbiddenChars = List.of('X', '-', '|', color);
        if (!canvas.isPointInside(x, y) || forbiddenChars.contains(state[y].charAt(x))) {
            return state;
        } else {
            var sb = new StringBuilder(state[y]);
            sb.setCharAt(x, color);
            state[y] = sb.toString();
            var left = colorBoard(state, x - 1, y, color);
            var upperLeft = colorBoard(left, x - 1, y - 1, color);
            var up = colorBoard(upperLeft, x, y - 1, color);
            var upperRight = colorBoard(up, x + 1, y - 1, color);
            var right = colorBoard(upperRight, x + 1, y, color);
            var lowerRight = colorBoard(right, x + 1, y + 1, color);
            var lower = colorBoard(lowerRight, x, y + 1, color);
            return colorBoard(lower, x - 1, y + 1, color);
        }
    }
}
