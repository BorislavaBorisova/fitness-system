package usersystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.tools.FileObject;

import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;

import users.Administrator;
import users.Instructor;
import users.Member;
import users.User;

public class UserSystem {
    HashMap<String, User> users;
    User currentUser;
    final String instructorsCode = "9As1";

    public UserSystem() {

        readUsers();
        currentUser = null;
    }

    public String getInstructorsCode() {
        return instructorsCode;
    }

    public boolean userWithThisNameExists(String username) {
        return users.containsKey(username);
    }

    public boolean isLogged() {
        return !(currentUser == null);
    }

    // from file to hashmap
    public void readUsers() {
        try {
            FileInputStream fileIn = new FileInputStream("temp/users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (HashMap<String, User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            users = new HashMap<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class HashMap not found");
            c.printStackTrace();
            return;
        }

    }

    // from hashmap to file
    public void writeUsers() {
        try {
            FileOutputStream fileOut = new FileOutputStream("temp/users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // signIn signUp

    public void signUpInstructor(String setUsername, String setPassword, String setName, String setSurname,
            String setEmail, int setAge, double setWeight, double setHeight, String setFitness, String setDescription) {
        Instructor newInstructor = new Instructor(setUsername, setPassword, setName, setSurname, setEmail, setAge,
                setWeight, setHeight, setFitness, setDescription);
        users.put(setUsername, newInstructor);
    }

    public void signUpUser(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight) {
        Member newUser = new Member(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight,
                setHeight);
        users.put(setUsername, newUser);
    }

    public void signIn(String username, String password) {
        if (users.containsKey(username) && password.hashCode() == users.get(username).getPassword()) {
            currentUser = users.get(username);
            System.out.println("You have signed in successfully");
        }

    }

    public void deleteMyProfile() {
        users.remove(currentUser.getUsername());
        currentUser = null;
    }

    public void show(String username) {
        if (currentUser instanceof Instructor || currentUser instanceof Administrator) {
            users.get(username).printDetailedInfo();
        } else {
            users.get(username).printBasicInfo();
        }
    }

    // messages

    public void writeMessage(String username, String message) {
        users.get(username).addMessage(new StringBuilder(currentUser.getUsername().toString() + ": " + (message)));
        System.out.println("Message sent");
    }

    public void seeAllMessages() {
        currentUser.seeAllNewMessages();
    }

    public void logout() {
        currentUser = null;
    }

}
