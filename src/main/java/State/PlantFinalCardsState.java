package State;

import Games.GameController;
import Player.PlayerModel;
import Player.PlayerController;
import Strategy.PlantTradedAndTurnedOverCards;

import java.util.List;

public class PlantFinalCardsState implements State{
    @Override
    public void plantBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot plant bean cards in this phase.");
    }

    @Override
    public void turnOverAndTrade(GameContext context) {
        throw new IllegalStateException("Cannot turn over and trade in this phase.");
    }

    @Override
    public void purchaseBuilding(GameContext context) {

    }

    @Override
    public void plantTurnedOverAndTradedCards(GameContext context) {
        // Implement the logic for planting turned-over and traded bean cards
        System.out.println("Planting turned-over and traded bean cards.");
        PlantTradedAndTurnedOverCards plantTradedAndTurnedOverCards = new PlantTradedAndTurnedOverCards();
        GameController gameController = GameController.getInstance();
        PlayerController playerController = gameController.getPlayerController();
        List<PlayerModel> players = playerController.getPlayers();

        // All players who have traded cards ( have horizontal cards ) and active player ( face up cards ) must be planted
        // if the card type does not correspond the already planted cards harvest phase is going to happen
        //TODO: write down strategy pattern to use the implemented algorithms to plan the cards in the field
        //Go over through all make them to plant their cards
        for (PlayerModel player : players) {
            try {
                System.out.println("About to plant cards for player: " + player.getName());
                plantTradedAndTurnedOverCards.plantCards(player);
                playerController.harvestAllFields(player);
                System.out.println("Finished planting cards for player: " + player.getName());
            } catch (Exception e) {
                System.err.println("Error planting cards for player: " + player.getName());
                e.printStackTrace();
            }
        }
        // Transition to the next state
        context.setState(new DrawBeanCardsState());
    }

    @Override
    public void drawBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot draw bean cards in this phase.");
    }
}
