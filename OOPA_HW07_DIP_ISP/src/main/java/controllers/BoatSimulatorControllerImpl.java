package controllers;

import contracts.*;
import models.RaceImpl;
import models.boats.*;
import utility.Constants;
import database.BoatSimulatorDatabase;
import exeptions.*;
import models.engines.JetEngine;
import models.engines.SterndriveEngine;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class BoatSimulatorControllerImpl implements BoatSimulatorController {
    private Map<Boat, Double> finishedBoats;
    private Map<Boat, Double> notFinishedBoats;
    private BoatSimulatorDatabase database;
    private Race currentRace;
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);

    public BoatSimulatorControllerImpl(BoatSimulatorDatabase database) {
        this.database = database;
        this.finishedBoats = new LinkedHashMap<>();
        this.notFinishedBoats = new LinkedHashMap<>();
    }

    private String isFinished(Double time) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#.##");

        if (time < 0 || time == Double.POSITIVE_INFINITY || time == Double.NEGATIVE_INFINITY) {
            return "Did not finish!";
        }
        return String.format("%s sec", df.format(time));
    }

    private void findFastest(List<Boat> participants) {
        for (Boat participant : participants) {
            Double speed = participant.calculateRaceSpeed(this.currentRace);
            Double time = this.currentRace.getDistance() / speed;

            if (time <= 0) {
                this.notFinishedBoats.put(participant, time);
            } else {
                this.finishedBoats.put(participant, time);
            }
        }

        if (this.finishedBoats.size() >= 3) {
            this.notFinishedBoats.clear();
        }
    }

    private void validateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NO_SET_RACE_MESSAGE);
        }
    }

    private void validateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RACE_ALREADY_EXISTS_MESSAGE);
        }
    }

    @Override
    public String createBoatEngine(String model, int horsepower, int displacement, String engineType) throws DuplicateModelException {
        BoatEngine engine;
        switch (engineType) {
            case "JET":
                engine = new JetEngine(model, horsepower, displacement);
                break;
            case "STERNDRIVE":
                engine = new SterndriveEngine(model, horsepower, displacement);
                break;
            default:
                return null;
//                throw new NotImplementedException();
        }

        this.database.getEngines().add(engine);
        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model,
                horsepower,
                displacement);
    }

    @Override
    public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        BaseBoat boat = new RowBoat(model, weight, oars);
        this.database.getBoats().add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    @Override
    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        BaseBoat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.getBoats().add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    @Override
    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
        BoatEngine firstEngine = this.database.getEngines().getItem(firstEngineModel);
        BoatEngine secondEngine = this.database.getEngines().getItem(secondEngineModel);
        BaseBoat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.database.getBoats().add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    @Override
    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistantModelException, DuplicateModelException {
        BoatEngine engine = this.database.getEngines().getItem(engineModel);
        BaseBoat boat = new Yacht(model, weight, cargoWeight, engine);
        this.database.getBoats().add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    @Override
    public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
        this.validateRaceIsEmpty();
        this.currentRace = new RaceImpl(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        return
                String.format(
                        "A new race with distance %s meters, wind speed %s m/s and ocean current speed %s m/s has been set.",
                        distance, windSpeed, oceanCurrentSpeed);
    }

    @Override
    public String signUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
        Boat boat = this.database.getBoats().getItem(model);
        this.validateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat.isMotorBoat()) {
            throw new IllegalArgumentException(Constants.INCORRECT_BOAT_TYPE_MESSAGE);
        }
        this.currentRace.addParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    @Override
    public String startRace() throws InsufficientContestantsException, NoSetRaceException {
        this.validateRaceIsSet();
        List<Boat> participants = this.currentRace.getParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.INSUFFICIENT_CONTESTANTS_MESSAGE);
        }


        for (int i = 0; i < 3; i++) {
            findFastest(participants);
        }

        String[] places = {"First", "Second", "Third"};
        final int[] index = {0};

        StringBuilder result = new StringBuilder();

        this.finishedBoats
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(boat -> {
                    if (index[0] < 3) {
                        result.append(String.format("%s place: %s Model: %s Time: %s",
                                places[index[0]++],
                                boat.getKey().getClass().getSimpleName(),
                                boat.getKey().getModel(),
                                this.isFinished(boat.getValue())))
                                .append(System.lineSeparator());
                    }
                });

        for (Map.Entry<Boat, Double> doubleBoatEntry : this.notFinishedBoats.entrySet()) {
            if (index[0] == 3) {
                break;
            }
            result.append(String.format("%s place: %s Model: %s Time: Did not finish!",
                    places[index[0]++],
                    doubleBoatEntry.getKey().getClass().getSimpleName(),
                    doubleBoatEntry.getKey().getModel()))
                    .append(System.lineSeparator());
        }

        this.currentRace = null;
        this.finishedBoats.clear();
        this.notFinishedBoats.clear();

        return result.toString();
    }



    @Override
    public String getStatistics() {
        return null;
    }

//    public String getStatistic() {
//        //TODO Bonus Task Implement me
//        throw new NotImplementedException();
//    }


}
