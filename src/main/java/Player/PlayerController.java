package Player;

import BeanField.BeanFieldModel;
import Cards.BeanCard;
import Deck.DeckModel;
import Games.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerController {
    private final List<PlayerModel> players;
    public PlayerController() {
        this.players = new ArrayList<PlayerModel>();
        initializePlayers();
    }

    private void initializePlayers() {
        PlayerModel player1 = new PlayerModel();
        player1.setName("Player1");
        PlayerModel player2 = new PlayerModel();
        player2.setName("Player2");
        PlayerModel player3 = new PlayerModel();
        player3.setName("Player3");
        PlayerModel player4 = new PlayerModel();
        player4.setName("Player4");

        //adding the new Players to Array List of Players
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
    }

    public void harvestField(PlayerModel player, BeanFieldModel beanField) {
        //Get field that wants to be harvested
        GameController gameController = GameController.getInstance();
        List<BeanCard> fieldCards = beanField.getFields().get(0);
        DeckModel deckModel = gameController.getDeckModel();

        for (List<BeanCard> field : beanField.getFields()) {
            // Ensure there are cards in the field
            if (field.isEmpty()) {
                continue; // Skip empty fields
            }

            // Get the number of cards in the field
            int numberOfCards = field.size();

            if (numberOfCards > 1) {
                // Based on one of the cards calculate the value
                BeanCard sampleCard = field.get(0);

                // Get the closest number on the Beanometer that gets coins on the maximum number of cards, and take as much as those and remove them from the field
                int coins = calculateCoins(numberOfCards, sampleCard);

                // Add coins to the player's total gold coins
                player.setGoldCoins(player.getGoldCoins() + coins);


                System.out.print("---------------");
                System.out.print("Name: " + player.getName() + "     " + "Gold: " + player.getGoldCoins() +  "    ");
                System.out.print("---------------------------------------");

                // Retrieve the cards in the field before clearing them and add them to the discard pile
                deckModel.appendDiscardPile(field);
                field.clear();
            }
        }

        // Ensure there are cards in the field
//        if (fieldCards.isEmpty()) {
//            throw new IllegalStateException("The field is empty. There are no cards to harvest.");
//        }
//
//        // Get the number of cards in the field
//        int numberOfCards = fieldCards.size();
//
//        if(numberOfCards > 1) {
//            //Based on one of the cards calculate the value
//            BeanCard sampleCard = fieldCards.get(0);
//
//            //Get the closest number on the Beanometer that gets coins on the maximum number of cards , and take as much as those and remove them from the field
//            int coins = calculateCoins(numberOfCards, sampleCard);
//
//            // Add coins to the player's total gold coins
//            player.setGoldCoins(player.getGoldCoins() + coins);
//
//            // The field should be empty after the harvest ,
//            //For now we just harvest the first field without user selection
//            //Retrieve the cards in the field before clearing them and add them to the discard pile
//            List<BeanCard> harvestedCards = beanField.getAllCardsInField(0);
//            deckModel.appendDiscardPile(harvestedCards);
//            beanField.getFields().get(0).clear();
//
//        }
    }

    public void harvestAllFields(PlayerModel player) {
        GameController gameController = GameController.getInstance();
        DeckModel deckModel = gameController.getDeckModel();
        BeanFieldModel beanField = player.getField();

        for (List<BeanCard> field : beanField.getFields()) {
            // Ensure there are cards in the field
            if (field.isEmpty()) {
                continue; // Skip empty fields
            }

            // Get the number of cards in the field
            int numberOfCards = field.size();

            if (numberOfCards > 1) {
                // Based on one of the cards calculate the value
                BeanCard sampleCard = field.get(0);

                // Get the closest number on the Beanometer that gets coins on the maximum number of cards, and take as much as those and remove them from the field
                int coins = calculateCoins(numberOfCards, sampleCard);

                // Add coins to the player's total gold coins
                player.setGoldCoins(player.getGoldCoins() + coins);

                // Debugging output
                System.out.println("---------------");
                System.out.println("Name: " + player.getName() + "     " + "Gold: " + player.getGoldCoins());
                System.out.println("---------------------------------------");

                // Retrieve the cards in the field before clearing them and add them to the discard pile
                deckModel.appendDiscardPile(field);
                field.clear();
            }
        }
    }

    private int calculateCoins(int numberOfCards, BeanCard card) {
        Map<Integer, Integer> beanometer = card.getCoinValues();

        int maxCoins = 0;
        for (Map.Entry<Integer, Integer> entry : beanometer.entrySet()) {
            if (entry.getKey() <= numberOfCards && entry.getValue() > maxCoins) {
                maxCoins = entry.getValue();
            }
        }
        return maxCoins;
    }

    public void moveToNextPlayer() {
        int activePlayerIndex = -1;

        // Identify the active player
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isCurrentPlayer()) {
                activePlayerIndex = i;
                players.get(i).setCurrentPlayer(false); // Set the current active player to inactive
                break;
            }
        }

        if (activePlayerIndex == -1) {
            throw new IllegalStateException("No active player found");
        }

        // Move to the next player
        int nextPlayerIndex = (activePlayerIndex + 1) % players.size();
        players.get(nextPlayerIndex).setCurrentPlayer(true); // Set the next player as active
    }

    //return the list of players
    public List<PlayerModel> getPlayers() {
        return this.players;
    }

    public PlayerModel getActivePlayer() {
        for (PlayerModel player : this.players) {
            if (player.isCurrentPlayer()) {
                return player;
            }
        }
        return null;
    }

    public PlayerModel getThePlayerWithMostCoins() {
        if (players == null || players.isEmpty()) {
            return null; // or throw an exception, or handle the empty case appropriately
        }

        PlayerModel playerWithMostCoins = players.get(0);

        for (PlayerModel player : players) {
            if (player.getGoldCoins() > playerWithMostCoins.getGoldCoins()) {
                playerWithMostCoins = player;
            }
        }

        return playerWithMostCoins;
    }

    public PlayerModel getRandomNonActiveRandomPlayer() {
        //Randomly select an available index in the players list
        //then set that player isCurrentPlayer var to true
        Random rand  = new Random();
        int index = rand.nextInt(this.players.size());
        if (!this.players.get(index).isCurrentPlayer()) {
            return players.get(index);
        }
        return null;
    }

    public void setActivePlayer() {
        //Randomly select an available index in the players list
        //then set that player isCurrentPlayer var to true
        Random rand  = new Random();
        int index = rand.nextInt(this.players.size());

        //Set all players isCurrentPlayer to false
        for(PlayerModel player : this.players) {
            player.setCurrentPlayer(false);
        }
        players.get(index).setCurrentPlayer(true);
    }

}
