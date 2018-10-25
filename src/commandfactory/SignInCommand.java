package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class SignInCommand implements Command {
    boolean isValid(String[] arguments) {
        return (arguments.length == 3 && arguments[0].equals("signIn"));
    }

    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (!us.isLogged()) {
                us.signIn(arguments[1], arguments[2]);
            } else {
                System.out.println("You are currently logged");
            }

        } else {
            System.out.println("Wrong username and/or password");
        }
    }
}
