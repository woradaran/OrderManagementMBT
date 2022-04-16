package th.ac.kmitl.se;

public class Card {
    String cardID;
    String nameOnCard;
    int expiryMonth;
    int expiryYear;

    public Card(String cardID, String nameOnCard, int expiryMonth, int expiryYear) {
        this.cardID = cardID;
        this.nameOnCard = nameOnCard;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }
}
