package users;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

public class Instructor extends User {
    private String fitness;
    private String description;

    public Instructor(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight, String setFitness, String setDescription) {
        super(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight, setHeight);
        fitness = setFitness;
        description = setDescription;
    }
}
