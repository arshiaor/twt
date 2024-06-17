package Games;

import Cards.BeanCard;
import Cards.BeanCardController;
import Player.PlayerController;
import Player.PlayerModel;
import State.GameContext;
import BeanField.BeanFieldModel;
import Cards.*;
import Deck.DeckModel;
import Player.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;


public class GameController {

    private static GameController instance;
    private GameModel game;
    private PlayerController playerController;
    private BeanCardController beanCardController;
    private GameContext gameContext;
    private DeckModel deckModel;

    private GameController() {
        this.game = new GameModel();
        this.playerController = new PlayerController();
        this.beanCardController = new BeanCardController();
        this.gameContext = new GameContext();
        this.deckModel = new DeckModel();
        }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
            instance.initializeGame();
        }
        return instance;
    }

    public void initializeGame() {

        //Set Game properties
        game.setPlayers(playerController.getPlayers());
        deckModel.setCards(beanCardController.getBeanCards());
        int currentRefillCount = deckModel.getRefillCount();

        //Assign the Starting Player card to a Player
        assignStartingPlayerCardToRandomPlayer();

        //deal 5 cards to each player from the pile
        dealInitialCardsToPlayers();

        //Assign Field mats based on the number of players
        assignBeanFieldMats();

        System.out.println("Game is being initialized");
        System.out.println(" -------------------------");

        System.out.println("Hash code");
        System.out.println(this.hashCode());

        while (currentRefillCount <= 2) {
            gameContext.plantBeanCards();
            gameContext.turnOverAndTrade(); // Should transition to PlantTurnedOverAndTradedCardsState
            gameContext.plantTurnedOverAndTradedCards(); // Should transition to DrawBeanCardsState
            gameContext.drawBeanCards();
           currentRefillCount = deckModel.getRefillCount();
           System.out.println(" -------------------------");
           System.out.println("Number of round is :" + currentRefillCount);
        }


        System.out.print("Game is finished");
        finishGame();
        //Finish the running of the code here
    }

    // Private function to randomly select a player from the PlayerController
    private void assignStartingPlayerCardToRandomPlayer() {
        List<PlayerModel> players = playerController.getPlayers();
        List<BeanCard> beanCards = deckModel.getCards();
        Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        //Get the Starting Card in the Cards
        BeanCard startingCard = beanCards.stream()
                .filter(beanCard -> "StartingCard".equals(beanCard.getCardName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No card named 'StartingCard' found in the deck."));

        //Remove it from the pile
        beanCards.removeIf(beanCard -> beanCard.equals(startingCard));
        System.out.println("The CARD IS " + startingCard.getCardName());

        //Select a random player
        PlayerModel player =  playerController.getPlayers().get(randomIndex);
        System.out.println("Starting Player name is : " + player.getName());

        //Give them the Starting Player Card and set as active player
        player.setStartingPlayerCard(Optional.of(startingCard));
        player.setCurrentPlayer(true);
        System.out.println(player.getStartingPlayerCard());
        System.out.println(" -------------------------");
    }

    private void dealInitialCardsToPlayers() {
        List<PlayerModel> players = playerController.getPlayers();
        List<BeanCard> beanCards = deckModel.getCards();

        //deal 5 cards to each player from bean cards and remove the afterward
        for (PlayerModel player : players) {
            //Take 5 cards , add 1 each time
            for (int i = 0; i < 5; i++) {
                if (!beanCards.isEmpty()) {
                    BeanCard cardToDeal = beanCards.remove(beanCards.size() - 1); // Remove the first card from the list
                    player.addToHand(cardToDeal);// Add the removed card to the player's hand
                }
            }
        }

        for (PlayerModel player : players) {
            System.out.println(player.getName() + ": Has " + player.getHand().size() + " Number of Cards");
            System.out.println(" -------------------------");
        }

    }

    private void assignBeanFieldMats() {
        List<PlayerModel> players = playerController.getPlayers();
        int numberOfPlayers = players.size();

        for (PlayerModel player : players) {
            if (numberOfPlayers == 3) {
                player.setField(new BeanFieldModel(3));
            } else if (numberOfPlayers == 4 || numberOfPlayers == 5) {
                player.setField(new BeanFieldModel(2));
            } else {
                throw new IllegalArgumentException("Unsupported number of players: " + numberOfPlayers);
            }
        }
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (playerController != null ? playerController.hashCode() : 0);
        result = 31 * result + (beanCardController != null ? beanCardController.hashCode() : 0);
        result = 31 * result + (gameContext != null ? gameContext.hashCode() : 0);
        result = 31 * result + (deckModel != null ? deckModel.hashCode() : 0);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GameController that = (GameController) obj;

        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        if (playerController != null ? !playerController.equals(that.playerController) : that.playerController != null)
            return false;
        if (beanCardController != null ? !beanCardController.equals(that.beanCardController) : that.beanCardController != null)
            return false;
        if (gameContext != null ? !gameContext.equals(that.gameContext) : that.gameContext != null) return false;
        return deckModel != null ? deckModel.equals(that.deckModel) : that.deckModel == null;
    }

    @Override
    public String toString() {
        return "GameController{" +
                "game=" + game +
                ", playerController=" + playerController +
                ", beanCardController=" + beanCardController +
                ", gameContext=" + gameContext +
                ", deckModel=" + deckModel +
                '}';
    }


    private void finishGame() {
        // Perform any necessary cleanup and final state updates
        // This might include saving the final game state, releasing resources, etc.

        System.out.println("Performing final cleanup...");

        // Example of final cleanup tasks
        declareWinner();
        displayFinalResults();
        resetGameContext();

        System.out.println("Game has been successfully terminated.");
    }

    private void declareWinner() {
        PlayerModel winner = playerController.getThePlayerWithMostCoins();
        System.out.print("The winner is " + winner.getName());
    }

    private void displayFinalResults() {
        // Display the final results of the game
        System.out.println("Final results:");
        for (PlayerModel player : game.getPlayers()) {
            System.out.println(player.getName() + ": " + player.getGoldCoins() + " coins");
        }
    }

    private void resetGameContext() {
        // Reset the game context for a new game, if applicable
        instance = null;
    }


    public static void main(String[] args) {
        //Initializes the game based on the instance
        GameController.getInstance();
    }


    public static void setInstance(GameController instance) {
        GameController.instance = instance;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public BeanCardController getBeanCardController() {
        return beanCardController;
    }

    public void setBeanCardController(BeanCardController beanCardController) {
        this.beanCardController = beanCardController;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }
}
