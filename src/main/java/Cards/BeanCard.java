package Cards;

import java.util.Map;

public class BeanCard implements Card{

    private String cardName;
    private int numberOfCards;
    private Map<Integer, Integer> coinValues;

    public BeanCard(String cardName, int numberOfCards, Map<Integer, Integer> coinValues) {
        this.cardName = cardName;
        this.numberOfCards = numberOfCards;
        this.coinValues = coinValues;
    }

    @Override
    public void create() {

    }

    @Override
    public void onTap() {
        // Face-up or Face-down
    }

    @Override
    public void render() {
        //Has its own attr for render
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public Map<Integer, Integer> getCoinValues() {
        return coinValues;
    }

    public void setCoinValues(Map<Integer, Integer> coinValues) {
        this.coinValues = coinValues;
    }
}
