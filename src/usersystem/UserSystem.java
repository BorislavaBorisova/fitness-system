package usersystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import card.Card;
import equipment.Equipment;
import users.Administrator;
import users.Instructor;
import users.Member;
import users.User;

public class UserSystem {
    HashMap<String, User> users;
    HashMap<String, Equipment> equipment;
    HashMap<String, Member> ids;
    HashMap<Date, Member> visits;
    User currentUser;
    final String instructorsCode = "9As1";

    public UserSystem() {
        readEquipment();
        readUsers();
        readIDs();
        readVisits();
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

    // USERS: from file to hashmap
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

    // USERS: from hashmap to file
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
        writeUsers();
    }

    public void signUpMember(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight) {

        Card card = new Card();
        Member newUser = new Member(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight,
                setHeight, card);
        card.setOwner(newUser);
        ids.put(card.getID(), newUser);
        writeIDs();
        users.put(setUsername, newUser);
        writeUsers();
    }

    private void signUpAdministrator(Administrator administrator) {
        users.put(administrator.getUsername(), administrator);
        writeUsers();
    }

    public void signIn(String username, String password) {
        if (users.containsKey(username)) {
            if (password.hashCode() == users.get(username).getPassword()) {
                currentUser = users.get(username);
                System.out.println("You have signed in successfully");
                if (isCurrentUserAMember()) {
                    ((Member) currentUser).daysLeft();
                }
                System.out.println("Here is a list of new equipment at the gym:");

                Calendar cal = Calendar.getInstance();
                cal.roll(Calendar.DAY_OF_MONTH, -7);
                Date lastWeek = cal.getTime();

                Iterator<Entry<String, Equipment>> it = equipment.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Equipment> pair = (Map.Entry<String, Equipment>) it.next();
                    if ((pair.getValue().getDate()).after(lastWeek)) {
                        System.out.println(pair.getKey());
                    }
                }
            } else {
                System.out.println("Wrong password");
            }

        } else {
            System.out.println("A user with this name does not exist");
        }

    }

    public void deleteMyProfile() {
        users.remove(currentUser.getUsername());
        writeUsers();
        if (isCurrentUserAMember()) {
            ids.remove(((Member) currentUser).getID());
            writeIDs();
        }

        currentUser = null;
    }

    public void show(String username) {
        if (currentUser instanceof Instructor) {
            users.get(username).printDetailedInfo();
        } else if (currentUser instanceof Administrator) {
            users.get(username).printInfoForAdmins();
        } else {
            users.get(username).printBasicInfo();
        }
    }

    // messages

    public void writeMessage(String username, String message) {
        users.get(username).addMessage(new StringBuilder(currentUser.getUsername().toString() + ": " + (message)));
        writeIDs();
        writeUsers();
        System.out.println("Message sent");
    }

    public void seeAllMessages() {
        currentUser.seeAllNewMessages();
        writeIDs();
        writeUsers();
    }

    public void logout() {
        currentUser = null;
    }

    // EQUIPMENT: from file to hashmap
    public void readEquipment() {
        try {
            FileInputStream fileIn = new FileInputStream("temp/equipment.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            equipment = (HashMap<String, Equipment>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            equipment = new HashMap<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class HashMap not found");
            c.printStackTrace();
            return;
        }
    }

    // EQUIPMENT: from hashmap to file
    public void writeEquipment() {
        try {
            FileOutputStream fileOut = new FileOutputStream("temp/equipment.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(equipment);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEquipment(String type) {
        if (equipment.containsKey(type)) {
            equipment.get(type).addExistingEquipment();

        } else {
            Equipment newEquipment = new Equipment(type);
            equipment.put(type, newEquipment);
        }
    }

    public void activate(String id) {
        if (ids.containsKey(id)) {
            ((Member) ids.get(id)).activate();
            writeIDs();
            writeUsers();
        } else {
            System.out.println("This member does not exist");
        }

    }

    // IDs: from file to hashmap
    public void readIDs() {
        try {
            FileInputStream fileIn = new FileInputStream("temp/ids.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ids = (HashMap<String, Member>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            ids = new HashMap<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class HashMap not found");
            c.printStackTrace();
            return;
        }

    }

    // IDs: from hashmap to file
    public void writeIDs() {
        try {
            FileOutputStream fileOut = new FileOutputStream("temp/ids.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ids);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // VISITS: from file to hashmap
    public void readVisits() {
        try {
            FileInputStream fileIn = new FileInputStream("temp/visits.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            visits = (HashMap<Date, Member>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            visits = new HashMap<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class HashMap not found");
            c.printStackTrace();
            return;
        }

    }

    // VISITS: from hashmap to file
    public void writeVisits() {
        try {
            FileOutputStream fileOut = new FileOutputStream("temp/visits.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(visits);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void visit(String id) {
        if (ids.containsKey(id)) {
            if (ids.get(id).hasActivatedCard()) {
                visits.put(Calendar.getInstance().getTime(), ids.get(id));
                ids.get(id).visit();
                writeIDs();
                writeUsers();
                writeVisits();
                System.out.println("Successful entry");
            } else {
                System.out.println("First your card must be activated");
            }

        } else {
            System.out.println("No such member ID");
        }

    }

    public boolean isCurrentUserAnAdminisrator() {
        return currentUser instanceof Administrator;
    }

    public boolean isCurrentUserAMember() {
        return currentUser instanceof Member;
    }

    public void showStatistics() {
        currentUser.showStatistics();
    }

    public static void main(String[] args) {
        UserSystem us = new UserSystem();
        Administrator admin1 = new Administrator("Administrator", "12Ab", "John", "Doe", "adminOne@gmail.com", 30, 72,
                180);

        us.signUpAdministrator(admin1);

    }

}
