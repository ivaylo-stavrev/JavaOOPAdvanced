package models.boats;

import contracts.BoatEngine;
import contracts.Race;
import utility.Validator;

public class Yacht extends BaseBoat {
    private BoatEngine engine;
    private int cargoWeight;

    public Yacht(String model, int weight, int cargoWeight, BoatEngine engine) {
        super(model, weight);
        this.engine = engine;
        this.setCargoWeight(cargoWeight);
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.ValidatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }

    public int getCargoWeight() {
        return this.cargoWeight;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return this.engine.getCachedOutput() - this.getWeight() - this.getCargoWeight() + (race.getOceanCurrentSpeed() / 2d);
    }

    @Override
    public boolean isMotorBoat() {
        return true;
    }
}
