package ex02_blob.io.implementations;

import ex02_blob.io.io_interfaces.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
