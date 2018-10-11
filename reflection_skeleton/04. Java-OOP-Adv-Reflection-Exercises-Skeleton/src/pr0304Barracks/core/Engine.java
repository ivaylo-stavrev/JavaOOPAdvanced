package pr0304Barracks.core;

import pr0304Barracks.contracts.*;
import pr0304Barracks.contracts.Runnable;
import pr0304Barracks.core.commands.Command;
import pr0304Barracks.core.commands.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
	private static final String COMMANDS_PACKAGE_NAME =
			"pr0304Barracks.core.commands.";

	private String[] data;
	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpredCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String interpredCommand(String[] data, String commandName) {

		this.data = data;
		Executable newCommand;
		try {
			//Class<?> commandType = Class.forName(COMMANDS_PACKAGE_NAME + Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1));
			//Constructor<?> commandTypeCtor = commandType.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
			//commandTypeCtor.setAccessible(true);
			//newCommand = (Executable) commandTypeCtor.newInstance(data, this.repository, this.unitFactory);
			//return newCommand.execute();

			Class<?> commandType = Class.forName(COMMANDS_PACKAGE_NAME + Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1));
			Constructor<?> declaredConstructor = commandType.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			newCommand = (Executable) declaredConstructor.newInstance();
			this.injectDependencies(newCommand);
			return newCommand.execute();
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid command!");
		}
	}

	private <T> void injectDependencies(T newCommand) throws IllegalAccessException {
		Field[] commandFields = newCommand.getClass().getDeclaredFields();
		Field[] engineFields = this.getClass().getDeclaredFields();

		for (Field commandField : commandFields) {
			commandField.setAccessible(true);
			if (commandField.isAnnotationPresent(Inject.class)) {
				for (Field engineField : engineFields) {
					engineField.setAccessible(true);
					if (commandField.getType().getSimpleName().equals(engineField.getType().getSimpleName())
							&& commandField.getType().equals(engineField.getType())) {
						commandField.set(newCommand, engineField.get(this));
					}
				}
			}
		}
	}
}
