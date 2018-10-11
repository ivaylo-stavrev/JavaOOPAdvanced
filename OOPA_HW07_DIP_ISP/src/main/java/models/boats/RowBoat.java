package models.boats;

import contracts.Race;
import utility.Validator;

public class RowBoat extends BaseBoat {
    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
    }

    public void setOars(int oars) {
        Validator.ValidatePropertyValue(oars, "Oars");
        this.oars = oars;
    }

    public int getOars() {
        return oars;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return (this.oars * 100) - this.getWeight() + race.getOceanCurrentSpeed();
    }

    @Override
    public boolean isMotorBoat() {
        return false;
    }
}
