package ex02_blob.core;

import ex02_blob.annotations.Inject;
import ex02_blob.interfaces.Executable;
import ex02_blob.interfaces.Repository;
import ex02_blob.io.io_interfaces.Reader;
import ex02_blob.io.io_interfaces.Writer;
import ex02_blob.models.Blob;
import ex02_blob.observers.Subject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Engine implements Runnable {
    public static final String DEFAULT_COMMAND_SUFIX = "Command";
    public static final String COMMANDS_CLASS_PATH = "ex02_blob.core.commands.";

    private Reader reader;
    private Writer writer;
    private String[] data;
    private Repository<Blob> blobRepository;
    private Subject subject;

    public Engine(Reader reader, Writer writer, Repository<Blob> blobRepository, Subject subject) {
        this.reader = reader;
        this.writer = writer;
        this.blobRepository = blobRepository;
        this.subject = subject;
    }

    @Override
    public void run() {
        String line;
        while (true) {
            line = this.reader.readLine();

            if ("drop".equals(line)) {
                break;
            }
            this.data = line.split("\\s+");
            String commandName = Character.toUpperCase(data[0].charAt(0)) + data[0].substring(1);
            this.data = Arrays.stream(this.data)
                    .skip(1)
                    .toArray(String[]::new);
            try {
                Class<?> commandClass = Class.forName(COMMANDS_CLASS_PATH + commandName + DEFAULT_COMMAND_SUFIX);
                Constructor<?> commandCtor = commandClass.getDeclaredConstructor();
                Executable command = (Executable) commandCtor.newInstance();
                this.injectionDependencies(command);
                command.execute();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            this.subject.notifyAllObservers();
        }
    }

    private <T> void injectionDependencies(T command) throws IllegalAccessException {
        Field[] commandFields = command.getClass().getDeclaredFields();
        Field[] engineFields = this.getClass().getDeclaredFields();

        for (Field commandField : commandFields) {
            commandField.setAccessible(true);
            if (commandField.isAnnotationPresent(Inject.class))
            for (Field engineField : engineFields) {
                engineField.setAccessible(true);
                if (commandField.getType().equals(engineField.getType())
                        && commandField.getType().getSimpleName().equals(engineField.getType().getSimpleName())) {
                    commandField.set(command, engineField.get(this));
                }
            }
        }
    }
}
