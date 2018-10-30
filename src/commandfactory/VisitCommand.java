package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class VisitCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length == 2;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (us.isCurrentUserAnAdminisrator()) {
                us.visit(arguments[1]);
            } else {
                System.out.println("You must be an Administrator to use this command");
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
