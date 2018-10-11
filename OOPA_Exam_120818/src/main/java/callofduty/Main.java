package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.core.MissionManagerImpl;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionControl;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleReader;
import callofduty.io.ConsoleWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)  {
        OutputWriter writer = new ConsoleWriter();
        InputReader reader = new ConsoleReader();
        MissionControl missionControl = new MissionControlImpl();
        MissionManager missionManager = new MissionManagerImpl(missionControl);
        String result;

        while (true) {
            String[] lineSplitted = reader.readLine().split("\\s+");
            String command = lineSplitted[0];
            List<String> arguments = Arrays.stream(lineSplitted).skip(1).collect(Collectors.toList());

            switch (command) {
                case "Agent":
                    result = missionManager.agent(arguments);
                    writer.println(result);
                    break;
                case "Request":
                    result = missionManager.request(arguments);
                    writer.println(result);
                    break;
                case "Complete":
                    result = missionManager.complete(arguments);
                    writer.println(result);
                    break;
                case "Status":
                    result = missionManager.status(arguments);
                    writer.println(result);
                    break;
                case "Over":
                    result = missionManager.over(arguments);
                    writer.println(result);
                    break;
            }

            if ("Over".equals(command)) {
                break;
            }
        }
    }
}




