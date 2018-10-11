package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.InvocationTargetException;

public class Add implements Executable {
    @Inject
    private UnitFactory unitFactory;
    @Inject
    private Repository repository;
    @Inject
    private String[] data;

    //protected Add(String[] data, Repository repository, UnitFactory unitFactory) {
    //    super(data, repository, unitFactory);
    //}

    public Add() {
    }

    @Override
    public String execute() {
        //this.unitFactory = getUnitFactory();
        //this.repository = getRepository();
        //this.data = getData();

        String unitType = data[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = this.unitFactory.createUnit(unitType);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }
        this.repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
