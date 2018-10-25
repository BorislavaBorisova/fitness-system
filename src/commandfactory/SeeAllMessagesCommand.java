package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class SeeAllMessagesCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length == 1;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (us.isLogged()) {
                us.seeAllMessages();
                us.writeUsers();
            } else {
                System.out.println("You are not logged");
            }
        } else {
            System.out.println("Invalid input");
        }

    }

}
