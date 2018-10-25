package commandfactory;

import java.util.Arrays;
import java.util.Scanner;

import usersystem.UserSystem;

public class WriteMessageCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length >= 3 && arguments[2].startsWith("\"") && arguments[arguments.length-1].endsWith("\"");
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (us.userWithThisNameExists(arguments[1])) {    
                us.writeMessage(arguments[1], input.split("\"")[1]);
                us.writeUsers();
            } else {
                System.out.println("A user with this username does not exist");
            }
        } else {
            System.out.println("Invalid arguments");
        }

    }

}
