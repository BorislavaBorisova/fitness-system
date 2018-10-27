package card;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import users.Member;

public class Card implements Serializable{
    Date expirationDate;
    String ID;
    Member owner;
    
    public Card() {
        expirationDate = null;
        ID = UUID.randomUUID().toString();
        owner = null;
    }
    
    public void setOwner(Member member) {
        owner = member;
    }
    
    public void activateOneMonthSubscription() {
        Calendar calendar = Calendar.getInstance();
        if(expirationDate == null || expirationDate.before(calendar.getTime())) {
            Calendar cal = Calendar.getInstance();
            cal.roll(Calendar.MONTH, 1);
            expirationDate = cal.getTime();
        }else {
            System.out.println("You already have a card");
        }
    }
    
    public String getID() {
        return ID;
    }
}
