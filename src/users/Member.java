package users;

import java.util.Calendar;

import subscription.Subscription;

public class Member extends User {
    private Subscription subscription;
    private Calendar expirationDate;
    private boolean SPA;

    public Member(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight) {
        super(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight, setHeight);
        subscription = Subscription.UNSUBSCRIBED;
        expirationDate = null;
        SPA = false;
    }

    void changeSubscription(Subscription newSubscription) {
        expirationDate = Calendar.getInstance();
        if (newSubscription.equals(Subscription.ONEMONTH)) {
            expirationDate.roll(Calendar.MONTH, 1);
            SPA = false;
        } else if (newSubscription.equals(Subscription.SIXMONTS)) {
            expirationDate.roll(Calendar.MONTH, 6);
            SPA = true;
        } else if (newSubscription.equals(Subscription.ONEYEAR)) {
            expirationDate.roll(Calendar.MONTH, 12);
            SPA = true;
        } else {
            expirationDate = null;
            SPA = false;
        }

        subscription = newSubscription;
    }

    boolean isSubscribed() {
        if (expirationDate == null)
            return false;
        return expirationDate.after(Calendar.getInstance().getTime());
    }

}
