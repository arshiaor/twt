package Player;

import Cards.BeanCard;
import BeanField.BeanFieldModel;
import Cards.BeanCardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerModel {
    private String name;
    private List<BeanCard> hand;
    private BeanFieldModel field;
    private Optional<BeanCard> startingPlayerCard;
    private List<BeanCard> tradedCards;
    private List<BeanCard> turnedOverCards;
    private int goldCoins;
    private boolean isCurrentPlayer;
    private boolean addAnother;

    public PlayerModel() {
        this.hand = new ArrayList<>();
        this.field = new BeanFieldModel(2);
        this.goldCoins = 0;
        this.isCurrentPlayer = false;
        this.receivedTradedCards = new ArrayList<>();
        this.turnedOverCards = new ArrayList<>();
        this.tradedCards = new ArrayList<>();
        this.startingPlayerCard = Optional.empty(); // Initialize as empty
    }

    public PlayerModel(String name) {
        this.name = name;
    }

    public boolean isAddAnother() {
        return addAnother;
    }

    public void setAddAnother(boolean addAnother) {
        this.addAnother = addAnother;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<BeanCardModel> receivedTradedCards;

    public List<BeanCard> drawCards() {
        return hand;
    }

    public void plantBean(List<BeanCardModel> facedUpCards) {
        // Implementation needed
    }

    public void harvestField(List<BeanCardModel> cards) {
        // Implementation needed
    }

    public void tradeBeans() {
        // Implementation needed
    }

    public List<BeanCard> offerCardsForTradeFromHand(PlayerModel toPlayer) {
        List<BeanCard> offeredCards = new ArrayList<>();
        for (int i = 0; i < 3 && !hand.isEmpty(); i++) {
            offeredCards.add(drawLastCard());
        }
        return offeredCards;
    }

    public boolean offerCardsForTradeFromFacedUpCards(PlayerModel toPlayer, List<BeanCard> facedUpCards) {
        // Implementation needed
        return false;
    }

    public List<BeanCard> acceptTradeOffer(PlayerModel fromPlayer, List<BeanCard> acceptedCards) {
        addToHand(acceptedCards);
        return acceptedCards;
    }

    public void addToHand(BeanCard card) {
        hand.add(card);
    }

    public void addToHand(List<BeanCard> acceptedCards) {
        hand.addAll(acceptedCards);
    }

    public List<BeanCardModel> getReceivedTradedCards() {
        return receivedTradedCards;
    }

    public void setReceivedTradedCards(List<BeanCardModel> receivedTradedCards) {
        this.receivedTradedCards = receivedTradedCards;
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }

    public BeanFieldModel getField() {
        return field;
    }

    public void setField(BeanFieldModel field) {
        this.field = field;
    }

    public List<BeanCard> getHand() {
        return hand;
    }

    public void setHand(List<BeanCard> hand) {
        this.hand = hand;
    }

    public Optional<BeanCard> getStartingPlayerCard() {
        return startingPlayerCard;
    }

    public void setStartingPlayerCard(Optional<BeanCard> startingPlayerCard) {
        this.startingPlayerCard = startingPlayerCard;
    }

    public List<BeanCard> getTradedCards() {
        return tradedCards;
    }

    public void setTradedCards(List<BeanCard> tradedCards) {
        this.tradedCards = tradedCards;
    }

    public void addMatField(BeanFieldModel beanFieldModel) {
        // Implementation needed
    }

    public List<BeanCard> getTurnedOverCards() {
        return turnedOverCards;
    }

    public void setTurnedOverCards(List<BeanCard> turnedOverCards) {
        this.turnedOverCards = turnedOverCards;
    }


    // Get the faced up card in player's hand
    public BeanCard drawLastCard() {
        if (!hand.isEmpty()) {
            return hand.remove(hand.size() - 1);
        }
        return null; // or throw an exception if preferred
    }
}
