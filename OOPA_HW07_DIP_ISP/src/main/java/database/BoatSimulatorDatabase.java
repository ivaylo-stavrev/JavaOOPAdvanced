package database;

import contracts.BoatEngine;
import contracts.Repository;
import models.boats.BaseBoat;

public class BoatSimulatorDatabase {
    private Repository<BaseBoat> boats;
    private Repository<BoatEngine> engines;

    public BoatSimulatorDatabase(Repository<BaseBoat> boats, Repository<BoatEngine> engines) {
        this.setBoats(boats);
        this.setEngines(engines);
    }

    private void setEngines(Repository<BoatEngine> engines) {
        this.engines = engines;
    }

    private void setBoats(Repository<BaseBoat> boats) {
        this.boats = boats;
    }

    public Repository<BoatEngine> getEngines() {
        return this.engines;
    }

    public Repository<BaseBoat> getBoats() {
        return this.boats;
    }
}
