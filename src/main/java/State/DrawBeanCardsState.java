package State;

import Cards.BeanCard;
import Deck.DeckModel;
import Games.GameController;
import Player.PlayerController;
import Player.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrawBeanCardsState implements State{
    @Override
    public void plantBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot plant bean cards in this phase.");
    }

    @Override
    public void turnOverAndTrade(GameContext context) {
        throw new IllegalStateException("Cannot turn over and trade in this phase.");
    }

    @Override
    public void plantTurnedOverAndTradedCards(GameContext context) {
        throw new IllegalStateException("Cannot plant turned-over and traded cards in this phase.");
    }

    @Override
    public void drawBeanCards(GameContext context) {
        System.out.println("Drawing bean cards.");

        //Retrieve instances
        GameController gameController = GameController.getInstance();
        PlayerController playerController = gameController.getPlayerController();
        DeckModel deckModel = gameController.getDeckModel();


        Optional<PlayerModel> player = playerController.getPlayers()
                .stream()
                .filter(PlayerModel::isCurrentPlayer)
                .findFirst();

        //Add card to the active player's hand
        addCardsToHandFromDeck(deckModel, player);

        //Move over to the next player
        playerController.moveToNextPlayer();

        // Transition to the next state (back to planting phase)
        context.setState(new PlantBeanCardsState());
    }

    @Override
    public void purchaseBuilding(GameContext context) {

    }

    //TODO: Encapsulate this logic in the DockController
    private void addCardsToHandFromDeck(DeckModel deckModel, Optional<PlayerModel> playerModel) {
        // Ensure there are enough cards in the deck
        GameController gameController = GameController.getInstance();
        List<BeanCard> deckCards =  deckModel.getCards();
        if(deckCards.size() < 3) {

            List<BeanCard> refillCards = deckModel.getDiscardPile();

            //Empty the discard pile
            deckModel.setNewDiscardPile();

            //Add the new cards to the deck
            deckModel.setCards(refillCards);

            //Add the refill count
            deckModel.incrementRefillCount();

            //shuffle the newly added cards
            deckModel.shuffle();

            //Refresh newly added cards by the discard pile
            deckCards = deckModel.getCards();
        }

        // Get the last 3 cards from the deck
        List<BeanCard> cardsToAdd = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardsToAdd.add(deckCards.remove(deckCards.size() - 1));
        }

        // Add these cards to the beginning of the player's hand
        List<BeanCard> playerHand = playerModel.get().getHand();
        for (int i = cardsToAdd.size() - 1; i >= 0; i--) {
            playerHand.add(0, cardsToAdd.get(i));
        }
    }

}
