package database;

import contracts.Boat;
import contracts.Repository;
import utility.Constants;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoatRepository implements Repository<Boat> {
    private Map<String, Boat> boats;

    public BoatRepository() {
        this.boats = new LinkedHashMap<>();
    }

    @Override
    public void add(Boat boat) throws DuplicateModelException {
        if (this.boats.containsKey(boat.getModel())) {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }
        this.boats.put(boat.getModel(), boat);
    }

    @Override
    public Boat getItem(String model) throws NonExistantModelException {
        if (!this.boats.containsKey(model)) {
            throw new NonExistantModelException(Constants.NON_EXISTANT_MODEL_MESSAGE);
        }

        return this.boats.get(model);
    }
}
