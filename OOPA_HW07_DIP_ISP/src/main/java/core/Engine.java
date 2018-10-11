package core;

import contracts.CommandHandler;
import io.io_interfaces.Reader;
import io.io_interfaces.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine implements Runnable{
    private CommandHandler commandHandler;
    private Reader reader;
    private Writer writer;

    public Engine(CommandHandler commandHandler, Reader reader, Writer writer) {
        this.commandHandler = commandHandler;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run()
    {
        while (true)
        {
            //Scanner scanner = new Scanner(System.in);
            String line = reader.readLine();
            String name = "";
            List<String> parameters = new ArrayList<>();

            if ("End".equals(line)) {
                break;
            }

            List<String> tokens = Arrays.asList(line.split("\\\\"));
            name = tokens.get(0);
            parameters = tokens.stream().skip(1).collect(Collectors.toList());

            try
            {
                String commandResult = this.commandHandler.executeCommand(name, parameters);
                writer.writeLine(commandResult);
                //System.out.println(commandResult);
            }
            catch (Exception ex)
            {
                writer.writeLine(ex.getMessage());
                //System.out.println(ex.getMessage());
            }

            //line = reader.readLine();
        }
    }

//    public static void main(String[] args) {
//        BoatSimulatorController ctrl = new BoatSimulatorController() {
//            @Override
//            public Race getCurrentRace() {
//                return null;
//            }
//
//            @Override
//            public BoatSimulatorDatabase getDatabase() {
//                return null;
//            }
//
//            @Override
//            public String createBoatEngine(String model, int horsepower, int displacement, String engineType) {
//                return null;
//            }
//
//            @Override
//            public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws
//                    NonExistantModelException, DuplicateModelException {
//                return null;
//            }
//
//            @Override
//            public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
//                return null;
//            }
//
//            @Override
//            public String signUpBoat(String model) throws NonExistantModelException, DuplicateModelException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String startRace() throws InsufficientContestantsException, NoSetRaceException {
//                return null;
//            }
//
//            @Override
//            public String getStatistics() {
//                return null;
//            }
//        };
//
//        CommandHandlerImpl commandHandler = new CommandHandlerImpl(ctrl);
//        Engine engine = new Engine();
//        engine.Run();
//    }
}
