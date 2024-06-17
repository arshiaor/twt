package Games;

import BeanField.BeanFieldModel;
import Cards.BeanCardModel;
import Deck.DeckModel;
import Player.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
private List<PlayerModel> players;
private DeckModel deck;
private List<BeanCardModel> discardpile;
private PlayerModel currentPlayer;

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<BeanCardModel> getDiscardpile() {
        return discardpile;
    }

    public void setDiscardpile(List<BeanCardModel> discardpile) {
        this.discardpile = discardpile;
    }

    //Initiator
    public GameModel() {
    this.players = new ArrayList<>();
    this.deck = new DeckModel();
    this.discardpile = new ArrayList<>();
}

    public void configureGame(int numberOfPlayers){

        //based on the numberOfPlayer received from the user in system input, the game should we configured
        //here's the configuration protocols:
        // if the number of  player is 3 , each mat should have three fields inside it and if it 4 or 5 players the number of fields in the matt should be two
        // after that a starting player should be assigned randomly from the List of Players, the list of players are now hard coded , there are 4 players in the list

        // Clear existing players
        this.players.clear();

        // Add players based on the number received
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerModel player = new PlayerModel(); // Assuming Player class exists
            this.players.add(player);
        }

        // Determine the number of fields in the mat based on the number of players
        int numberOfFields = (numberOfPlayers == 3) ? 3 : 2;

        // Assign fields to players
        for (PlayerModel player : this.players) {
            for (int i = 0; i < numberOfFields; i++) {
                player.addMatField(new BeanFieldModel(numberOfFields)); // Assuming MatField class exists and addMatField method is implemented in Player class
            }
        }

        // Assign a starting player randomly
//        Collections.shuffle(this.players); // Shuffle the list of players
//        setStartingPlayer();
    }

    public void startGame(){}


    public void endGame(){}


    public void setNextTurn(){}

    ///TODO 2. Verify this function's return operator
    public List<PlayerModel> calculateWinner(){
            return players;
    }

    public void resetGame(){}

    private void setStartingPlayer(){
        Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        this.currentPlayer = players.get(randomIndex);
    }

    public void reFillDeck(){}

}

