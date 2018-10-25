package commandfactory;
import java.util.Scanner;

import usersystem.UserSystem;;

public interface Command {
    public void execute(UserSystem us, String[] arguments, String input , Scanner scaner);
}
