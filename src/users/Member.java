package users;

import card.Card;

public class Member extends User {
    private Card card;

    public Member(String setUsername, String setPassword, String setName, String setSurname, String setEmail,
            int setAge, double setWeight, double setHeight, Card setCard) {
        super(setUsername, setPassword, setName, setSurname, setEmail, setAge, setWeight, setHeight);
        card = setCard;
    }
    
    public void activate() {
        card.activateOneMonthSubscription();
    }
    
    public void printInfoForAdmins(){
        printDetailedInfo();
        System.out.println("ID: " + card.getID());
    }
    
    public String getID() {
        return card.getID();
    }
    
    public boolean hasActivatedCard(){
        return card.isActivated();
    }

}
