package runner;

import commandfactory.Terminal;

public class Runner {
    // Two profiles exist:
    // ivan 1234567 (member)
    // ina 7654321 (instructor) (for more instructors the code is 9As1)

    // Administrator 12Ab

    // COMMANDS:
    // writeMessage ToWhom "Message here"
    // seeAllNewMessages
    // showStatistics (shows personal statistics)
    // show <username> (shows information about the user according to your type of
    // account)
    // deleteMyProfile
    // signUp
    // signIn <username> <password>
    // visit <id>(ONLY FOR ADMIN - a member with this id visits the fitness)
    // logout
    // addEquipment <name> (ONLY FOR ADMIN - add equipment with this name)
    /// activate <id> - (ONLY FOR ADMIN - activates the card of a member so he/she
    // can visit the fitness)
    // ONLY MEMBERS HAVE CARDS FOR THE FITNESS

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.useFitnessSystem();
    }
}
