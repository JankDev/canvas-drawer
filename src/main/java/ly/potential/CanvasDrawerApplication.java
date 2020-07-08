package ly.potential;

import java.io.Console;

public class CanvasDrawerApplication {
    public static void main(String[] args) {
        CanvasDrawerApplication.start();
    }

    static void start() {
        System.out.println("Starting the application...");
        Console console = System.console();

        if (console == null) {
            System.out.println("A console could not be found. Please start this application from the terminal.");
            System.exit(1);
        }

        System.out.println("Application started.");
        new Shell().start(console);
    }
}
