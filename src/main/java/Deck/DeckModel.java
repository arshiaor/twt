package Deck;

import Cards.BeanCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class DeckModel {
    private List<BeanCard> cards;
    private List<BeanCard> discardPile;
    private int refillCount;

    public DeckModel(){
        this.cards = new ArrayList<BeanCard>();
        this.refillCount=0;
        this.discardPile = new ArrayList<BeanCard>();
    }

    ///TODO 1. verify this function's return operator
    public List<BeanCard> drawCards(){
        return cards;
    }

    public List<BeanCard> getCards() {
        return cards;
    }

    public void setCards(List<BeanCard> cards) {
        this.cards = cards;
    }

    public int getRefillCount() {
        return refillCount;
    }

    public void setRefillCount(int refillCount) {
        this.refillCount = refillCount;
    }

    public List<BeanCard> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<BeanCard> discardPile) {
        this.discardPile = discardPile;
    }

    public void setNewDiscardPile() {
        this.discardPile = new ArrayList<>();
    }

    public void incrementRefillCount() {
        this.refillCount++;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void appendDiscardPile(List<BeanCard> discardPile) {
        this.discardPile.addAll(discardPile);
    }

    public BeanCard drawOneCard() {
        if (cards.isEmpty()) {
            if (refillCount < 2) {
                refillDeck();
            } else {
                endGame();
                throw new NoSuchElementException();
            }
        }

        BeanCard card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }


    private void refillDeck() {
        if (!discardPile.isEmpty()) {
            System.out.println("Refilling deck with " + discardPile.size() + " cards.");
            cards = new ArrayList<>(discardPile);
            discardPile.clear();
            refillCount += 1;
        }
    }

    private void endGame() {
        System.out.println("The game should end!");
        // Implement game-ending logic here, such as setting a flag or notifying players
    }
}
