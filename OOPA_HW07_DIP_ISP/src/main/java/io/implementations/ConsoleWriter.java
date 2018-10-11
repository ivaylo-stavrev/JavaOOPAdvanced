package io.implementations;

import io.io_interfaces.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
