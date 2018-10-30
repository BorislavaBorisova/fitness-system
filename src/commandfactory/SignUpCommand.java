package commandfactory;

import java.util.Scanner;

import usersystem.UserSystem;

public class SignUpCommand implements Command {
    private boolean isValid(String[] arguments) {
        return arguments.length == 1 && arguments[0].equals("signUp");
    }

    boolean isValidUsername(String username) {
        return username.matches("([a-zA-Z]|\\d|\\_)+");
    }

    boolean isValidName(String name) {
        return name.matches("([a-zA-Z]+\\-?[a-zA-Z]+)");
    }

    boolean isValidPassword(String password) {
        return password.length() > 6;
    }

    boolean isValidEmail(String email) {
        return email.matches("([a-z]|\\d|\\_|\\.)+\\@([a-z]|\\d)+\\.[a-z]+");
    }

    boolean isValidAge(int age) {
        return age > 16 && age < 110;
    }

    boolean isValidWeight(double weight) {
        return weight > 35 && weight < 150;
    }

    boolean isValidHeight(double height) {
        return height > 100 && height < 220;
    }

    public void execute(UserSystem us, String[] arguments, String input, Scanner scanner) {
        if (isValid(arguments) && !us.isLogged()) {
            System.out.println("Sign up as Member or Instructor? ");
            String type;
            type = scanner.nextLine();

            while (!type.equals("Member") && !type.equals("Instructor")) {
                System.out.println("Enter \"Member\" of \"Instructor\" ");
                type = scanner.nextLine();
            }

            System.out.print("Username: ");
            String username = scanner.nextLine();
            while (!isValidUsername(username) || us.userWithThisNameExists(username)) {
                System.out.println("Invalid username. Try again");
                username = scanner.nextLine();
            }

            System.out.print("Name: ");
            String name = scanner.nextLine();
            while (!isValidName(name)) {
                System.out.println("Invalid name. Try again");
                name = scanner.nextLine();
            }

            System.out.print("Surname: ");
            String surname = scanner.nextLine();
            while (!isValidName(surname)) {
                System.out.println("Invalid surname. Try again");
                surname = scanner.nextLine();
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();
            while (!isValidPassword(password)) {
                System.out.println("Password must be at least seven symbols. Try again");
                password = scanner.nextLine();
            }

            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            while (!isValidEmail(email)) {
                System.out.println("Invalid e-mail address. Try again");
                email = scanner.nextLine();
            }

            System.out.print("Age: ");
            String ageStr = scanner.nextLine();
            while (!ageStr.matches("\\d+") || !isValidAge(Integer.parseInt(ageStr))) {
                System.out.println("Invalid age. Try again");
                ageStr = scanner.nextLine();
            }

            System.out.print("Weight: ");
            String weight = scanner.nextLine();
            while (!weight.matches("\\d+(\\.\\d+)?") || !isValidWeight(Double.parseDouble(weight))) {
                System.out.println("Invalid weight. Try again");
                weight = scanner.nextLine();
            }

            System.out.print("Height: ");
            String height = scanner.nextLine();
            while (!height.matches("\\d+(\\.\\d+)?") || !isValidHeight(Double.parseDouble(height))) {
                System.out.println("Invalid height. Try again");
                height = scanner.nextLine();
            }

            if (type.equals("Member")) {
                us.signUpMember(username, password, name, surname, email, Integer.parseInt(ageStr),
                        Double.parseDouble(weight), Double.parseDouble(height));
                us.writeUsers();
                us.writeIDs();
                System.out.println("You have signed up successfully");
            } else {
                System.out.println("Enter code for instructors");
                String code = scanner.nextLine();
                while (!code.equals(us.getInstructorsCode())) {
                    System.out.println("Incorrect code");
                    code = scanner.nextLine();
                }

                System.out.print("Fitness: ");
                String fitness = scanner.nextLine();

                System.out.print("Description: ");
                String description = scanner.nextLine();

                us.signUpInstructor(username, password, name, surname, email, Integer.parseInt(ageStr),
                        Integer.parseInt(weight), Integer.parseInt(height), fitness, description);
               
                System.out.println("You have signed up successfully");
            }
        }
    };
}
