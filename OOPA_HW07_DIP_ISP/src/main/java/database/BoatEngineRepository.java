package database;

import contracts.BoatEngine;
import contracts.Repository;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;
import utility.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoatEngineRepository implements Repository<BoatEngine> {
    private Map<String, BoatEngine> engines;

    public BoatEngineRepository() {
        this.engines = new LinkedHashMap<>();
    }

    @Override
    public void add(BoatEngine engine) throws DuplicateModelException {
        if (this.engines.containsKey(engine.getModel())) {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }
        this.engines.put(engine.getModel(), engine);
    }

    @Override
    public BoatEngine getItem(String model) throws NonExistantModelException {
        if (!this.engines.containsKey(model)) {
            throw new NonExistantModelException(Constants.NON_EXISTANT_MODEL_MESSAGE);
        }

        return this.engines.get(model);
    }
}
