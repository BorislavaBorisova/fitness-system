package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class DeleteMyProfileCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length == 1;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (us.isLogged()) {
            if (isValid(arguments)) {
                us.deleteMyProfile();
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("You have to be signed in to delete your profile");
        }
    }

}
