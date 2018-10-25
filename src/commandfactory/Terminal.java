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
        factory.put("signUp", CommandType.SignUp);
        factory.put("signIn", CommandType.SignIn);
        factory.put("deleteMyProfile", CommandType.DeleteMyProfile);
        factory.put("show", CommandType.Show);
        factory.put("writeMessage", CommandType.WriteMessage);
        factory.put("seeAllNewMessages", CommandType.SeeAllMessages);
        factory.put("logout", CommandType.Logout);
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
