package equipment;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Equipment implements Serializable {
    Date dateLastAdded;
    String typeOfEquipment;
    int count;

    public Equipment(String setTypeOfEquipment) {
        dateLastAdded = Calendar.getInstance().getTime();
        typeOfEquipment = setTypeOfEquipment;
        count = 1;
    }

    public void addExistingEquipment() {
        count++;
        dateLastAdded = Calendar.getInstance().getTime();
    }

    public Date getDate() {
        return dateLastAdded;
    }

}
