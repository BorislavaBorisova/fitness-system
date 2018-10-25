package runner;

import commandfactory.Terminal;

public class Runner {
    // Two profiles exist:
    // Ivan 1234567
    // Ina 7654321
    
    //writeMessage ToWhom "Message here"
    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.useFitnessSystem();
    }
}
