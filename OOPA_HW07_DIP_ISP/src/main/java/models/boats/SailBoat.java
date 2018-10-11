package models.boats;

import contracts.Race;
import utility.Constants;

public class SailBoat extends BaseBoat {
    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    public void setSailEfficiency(int sailEfficiency) {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new IllegalArgumentException(Constants.INCORRECT_SAIL_EFFICIENCY_MESSAGE);
        }
        this.sailEfficiency = sailEfficiency;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return (race.getWindSpeed() * (this.sailEfficiency / 100d)) - this.getWeight() + (race.getOceanCurrentSpeed() / 2d);
    }

    @Override
    public boolean isMotorBoat() {
        return false;
    }
}
