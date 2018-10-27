package users;

import java.io.Serializable;
import java.util.Stack;

import card.Card;

public class User implements Serializable{
    private String username;
    private String name;
    private String surname;
    private int password;
    private String email;
    private int age;
    private double weight;
    private double height;

    private Stack<String> messages;

    public User(String setUsername, String setPassword, String setName, String setSurname, String setEmail, int setAge,
            double setWeight, double setHeight) {
        username = setUsername;
        password = setPassword.hashCode();
        name = setName;
        surname = setSurname;
        email = setEmail;
        age = setAge;
        weight = setWeight;
        height = setHeight;
        messages = new Stack<String>();
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public void printBasicInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("E-mail: " + email);
        System.out.println("Age: " + age);
    }

    public void printDetailedInfo() {
        printBasicInfo();
        System.out.println("Weight: " + weight);
        System.out.println("Height: " + height);
    }    

    public void printInfoForAdmins() {
        printDetailedInfo();      
    }

    public void addMessage(StringBuilder message) {
        messages.push(new String(message));
    }

    public void seeAllNewMessages() {
        while (!messages.isEmpty()) {
            System.out.println(messages.pop());
        }
    }

}
