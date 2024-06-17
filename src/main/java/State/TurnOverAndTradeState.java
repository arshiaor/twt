package State;

import Cards.BeanCard;
import Player.PlayerModel;
import Deck.DeckModel;
import Games.GameController;
import Player.PlayerController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurnOverAndTradeState implements State {
    @Override
    public void plantBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot plant bean cards in this phase.");
    }

    @Override
    public void turnOverAndTrade(GameContext context) {
        // Implement the logic for turning over and trading bean cards
        System.out.println("Turning over and trading bean cards.");
        // Transition to the next state
        GameController gameController = GameController.getInstance();
        DeckModel deckModel = gameController.getDeckModel();
        PlayerController playerController = gameController.getPlayerController();
        List<BeanCard> facedUpCards = new ArrayList<>();
        PlayerModel activePlayer = playerController.getActivePlayer();

        if(activePlayer != null) {
            //Refactor this method in Deck Model
            facedUpCards.add(deckModel.drawOneCard());
            facedUpCards.add(deckModel.drawOneCard());

            // Ask the user if they want to trade
            //TODO: Call the askUserForTrade(); for client's interaction
            boolean wantsToTrade = false;

            if(wantsToTrade) {
                // Proceed to card selection for trading
                // The user can select card from his/her hand or from the faceup cards
                // NOTE: Hard coded to trade face-up cards.
                boolean isOfferAccepted = activePlayer.offerCardsForTradeFromFacedUpCards(playerController.getRandomNonActiveRandomPlayer(), facedUpCards);
                if(isOfferAccepted) {//TODO:
                  //Trade Cards and mark them as traded
                  //Add them to traded pile of the player
                }
            } else {
                // Proceed to planting the cards in the fields (phase 3)
                playerController.harvestField(activePlayer, activePlayer.getField());
                context.setState(new PlantFinalCardsState());
                return;
            }
        }

        //TODO: Call the askUserForEndingTrade(); for client's interaction
        boolean wantsToEndTrade = true;
        if (wantsToEndTrade) {
            //Go to the next phase
            context.setState(new PlantFinalCardsState());
        } else {
            //Repeat the second phase ( trading )
            context.setState(new TurnOverAndTradeState());
        }
    }

    @Override
    public void plantTurnedOverAndTradedCards(GameContext context) {
        throw new IllegalStateException("Cannot plant turned-over and traded cards in this phase.");
    }

    @Override
    public void purchaseBuilding(GameContext context) {

    }

    @Override
    public void drawBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot draw bean cards in this phase.");
    }

    private boolean askUserForTrade() {
        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // Prompt the user to decide if they want to trade
        while (true) {
            System.out.print("Do you want to trade the bean cards? (yes/no): ");
            userInput = scanner.nextLine().trim().toLowerCase();

            // Check user input and return true or false based on their decision
            if (userInput.equals("yes")) {
                return true;
            } else if (userInput.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private boolean askUserForEndingTrade(){
        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // Prompt the user to decide if they want to trade
        while (true) {
            System.out.print("Do you want to end trade? (yes/no): ");
            userInput = scanner.nextLine().trim().toLowerCase();

            // Check user input and return true or false based on their decision
            if (userInput.equals("yes")) {
                return true;
            } else if (userInput.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

}
