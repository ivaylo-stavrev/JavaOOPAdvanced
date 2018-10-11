import contracts.BoatSimulatorController;
import contracts.CommandHandler;
import contracts.Repository;
import controllers.BoatSimulatorControllerImpl;
import core.CommandHandlerImpl;
import core.Engine;
import database.BoatEngineRepository;
import database.BoatRepository;
import database.BoatSimulatorDatabase;
import io.implementations.ConsoleReader;
import io.implementations.ConsoleWriter;
import io.io_interfaces.Reader;
import io.io_interfaces.Writer;

public class Main {
    public static void main(String[] args) {
        Writer writer = new ConsoleWriter();
        Reader reader = new ConsoleReader();
        Repository boatRepository = new BoatRepository();
        Repository engineRepositiry = new BoatEngineRepository();
        BoatSimulatorDatabase database = new BoatSimulatorDatabase(boatRepository, engineRepositiry);
        BoatSimulatorController boatSimulatorController = new BoatSimulatorControllerImpl(database);
        CommandHandler commandHandler = new CommandHandlerImpl(boatSimulatorController);
        Runnable engine = new Engine(commandHandler, reader, writer);

        engine.run();

    }
}
