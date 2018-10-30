package users;

import java.io.Serializable;

import card.Card;

public class Member extends User implements Serializable {
    private Card card;
    private int timesVisited;
    private int visitsThisMonth;

    public Member(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight, Card setCard) {
        super(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight, setHeight);
        card = setCard;
        timesVisited = 0;
        visitsThisMonth = 0;
    }

    public void activate() {
        if (!card.isActivated()) {
            visitsThisMonth = 0;
            card.activateOneMonthSubscription();
        } else {
            System.out.println("Card already activated");
        }
    }

    public void printInfoForAdmins() {
        printDetailedInfo();
        System.out.println("ID: " + card.getID());
    }

    public String getID() {
        return card.getID();
    }

    public boolean hasActivatedCard() {
        return card.isActivated();
    }

    public int timesCardIsActivated() {
        return card.timesActivated();
    }

    public void visit() {
        timesVisited++;
        visitsThisMonth++;
    }

    public void showStatistics() {
        System.out.println("You activated your card " + card.timesActivated() + " times");
        if (card.timesActivated() == 0) {
            System.out.println("Number of visits for one month on average: 0");
        } else {
            System.out.println("Number of visits for one month on average: " + timesVisited / card.timesActivated());
        }
        System.out.println("This month you visited " + visitsThisMonth + " times");
    }

    public void daysLeft() {
        card.findDaysLeftToExpiration();
    }
}
