package ex01_logger;

import ex01_logger.core.Engine;
import ex01_logger.io.implementations.ConsoleReader;
import ex01_logger.io.implementations.ConsoleWriter;
import ex01_logger.io.io_interfaces.Reader;
import ex01_logger.io.io_interfaces.Writer;

public class Main {
    public static void main(String[] args) {
        Writer writer = new ConsoleWriter();
        Reader reader = new ConsoleReader();

        Engine engine = new Engine(writer, reader);
        engine.run();
    }
}
