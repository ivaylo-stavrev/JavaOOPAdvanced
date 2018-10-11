package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;

public class Report implements Executable {
    @Inject
    private Repository repository;

    protected Report() {
    }

    @Override
    public String execute() {
        //this.repository = getRepository();
        String output = this.repository.getStatistics();
        return output;
    }
}
