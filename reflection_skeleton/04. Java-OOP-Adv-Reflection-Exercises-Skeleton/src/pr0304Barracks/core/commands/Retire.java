package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class Retire implements Executable {
    @Inject
    private Repository repository;
    @Inject
    private String[] data;

    protected Retire() {
    }

    @Override
    public String execute() {
        //this.repository = getRepository();
        //this.data = getData();
        try {
            this.repository.removeUnit(this.data[1]);
            return String.format("%s retired!", this.data[1]);
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }
    }
}
