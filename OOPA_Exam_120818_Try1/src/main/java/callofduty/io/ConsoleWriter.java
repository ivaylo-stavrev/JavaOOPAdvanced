package callofduty.io;

import callofduty.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

    @Override
    public void print(String output) {
        System.out.printf(output);
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }
}
