package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class ActivateCardCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length == 2;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (us.isCurrentUserAnAdminisrator()) {
                us.activate(arguments[1]);
            } else {
                System.out.println("You must be an Administrator to activate a card");
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
