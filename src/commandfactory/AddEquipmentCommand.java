package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class AddEquipmentCommand implements Command {
    boolean isValid(String[] arguments) {
        return arguments.length >= 2;
    }

    @Override
    public void execute(UserSystem us, String[] arguments, String input, Scanner scaner) {
        if (isValid(arguments)) {
            if (us.isCurrentUserAnAdminisrator()) {
                StringBuilder name = new StringBuilder();
                name.append(arguments[1]);
                for (int i = 2; i < arguments.length; i++) {
                    name.append(" " + arguments[i]);
                }
                us.addEquipment(name.toString());
                us.writeEquipment();
                System.out.println("Successfully added");
            } else {
                System.out.println("You must be an Administrator to add equipment");
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
