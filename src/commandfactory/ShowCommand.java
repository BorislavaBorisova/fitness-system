package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class ShowCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length == 2;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments) && us.userWithThisNameExists(arguments[1]) && us.isLogged()) {
            us.show(arguments[1]);
        }

    }

}
