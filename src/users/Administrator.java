package users;

import java.io.Serializable;

public class Administrator extends User implements Serializable {
    public Administrator(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight) {
        super(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight, setHeight);
    }

}
