package ex01_logger.io.implementations;

import ex01_logger.io.io_interfaces.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
