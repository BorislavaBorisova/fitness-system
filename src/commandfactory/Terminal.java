package commandfactory;

import java.util.HashMap;
import java.util.Scanner;

import usersystem.UserSystem;

public class Terminal {
    private UserSystem userSystem;
    private Scanner scanner;
    private static HashMap<String, CommandType> factory = new HashMap<>();
    private boolean finished;

    public Terminal() {
        userSystem = new UserSystem();
        scanner = new Scanner(System.in);
        finished = false;
    }

    static {
        factory.put("signUp", CommandType.SIGN_UP);
        factory.put("signIn", CommandType.SIGN_IN);
        factory.put("deleteMyProfile", CommandType.DELETE_MY_PROFILE);
        factory.put("show", CommandType.SHOW);
        factory.put("writeMessage", CommandType.WRITE_MESSAGE);
        factory.put("seeAllNewMessages", CommandType.SEE_ALL_MESSAGES);
        factory.put("logout", CommandType.LOGOUT);
        factory.put("addEquipment", CommandType.ADD_EQUIPMENT);
        factory.put("activate", CommandType.ACTIVATE_CARD);
        factory.put("visit", CommandType.VISITS);
        factory.put("showStatistics", CommandType.SHOW_STATISTICS);
    }

    public void useFitnessSystem() {
        System.out.println("signUp or signIn");
        String command;

        while (!finished) {
            command = scanner.nextLine();
            String[] argumentsArray = command.split(" ");
            if (factory.containsKey(argumentsArray[0])) {
                factory.get(argumentsArray[0]).getCommand().execute(userSystem, argumentsArray, command, scanner);
            } else if (command.equals("end")) {
                finished = true;
                scanner.close();
                continue;
            } else {
                System.out.println("Invalid command");
            }
        }

    }
}
