package card;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import users.Member;

public class Card implements Serializable {
    private Date expirationDate;
    private String ID;
    private Member owner;
    private int timesActivated;

    public Card() {
        expirationDate = null;
        ID = UUID.randomUUID().toString();
        owner = null;
        timesActivated = 0;
    }

    public void setOwner(Member member) {
        owner = member;
    }

    public void activateOneMonthSubscription() {
        Calendar calendar = Calendar.getInstance();
        if (expirationDate == null || expirationDate.before(calendar.getTime())) {
            Calendar cal = Calendar.getInstance();
            cal.roll(Calendar.MONTH, 1);
            
            expirationDate = cal.getTime();
            timesActivated++;
            System.out.println("Card successfully activated");
        } else {
            System.out.println("You already have a card");
        }
    }

    public String getID() {
        return ID;
    }

    public boolean isActivated() {
        if (expirationDate == null) {
            return false;
        } else {
            Calendar calendar = Calendar.getInstance();
            return expirationDate.after(calendar.getTime());
        }

    }

    public int timesActivated() {
        return timesActivated;
    }

    public void findDaysLeftToExpiration() {
        if (isActivated()) {
            LocalDate date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            System.out.println(ChronoUnit.DAYS.between(now, date) + " days left to expiration date");
        } else {
            System.out.println("Your card is not activated");
        }
    }
}
