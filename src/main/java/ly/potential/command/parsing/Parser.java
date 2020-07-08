package ly.potential.command.parsing;

public interface Parser<T, U> {
    T parse(U u);
}
