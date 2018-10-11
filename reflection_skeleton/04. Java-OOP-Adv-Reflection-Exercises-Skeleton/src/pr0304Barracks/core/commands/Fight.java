package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;

public class Fight implements Executable {

    protected Fight() {
    }

    @Override
    public String execute() {
        return "fight";
    }
}
