import BeanField.BeanFieldModel;
import Cards.BeanCard;
import Cards.CardFactory;
import Deck.DeckModel;
import Games.GameController;
import Player.PlayerController;
import Player.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class HarvestTest {

    @Mock
    private DeckModel deckModel;

    @Mock
    private GameController gameController;

    @InjectMocks
    private PlayerController playerController; // Assuming the harvestField method is here

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set the singleton instance of GameController to the mocked instance
        GameController.setInstance(gameController);

        // Mock the methods to return the necessary mock objects
        when(gameController.getDeckModel()).thenReturn(deckModel);
    }

    /**
     * Test to check if the number of cards in the discard pile is correct after harvesting.
     */
    @Test
    public void testHarvestFieldExpectedNumberOfCards() {
        // Setup the player and beanField with mocked data
        PlayerModel player = new PlayerModel();
        BeanFieldModel beanField = new BeanFieldModel(2); // Initialize with 2 fields

        // First field with RedBeans
        List<BeanCard> redBeanField = new ArrayList<>();
        redBeanField.add(CardFactory.createBeanCard("RedBean"));
        redBeanField.add(CardFactory.createBeanCard("RedBean"));

        // Second field with SoyBeans
        List<BeanCard> soyBeanField = new ArrayList<>();
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));

        List<List<BeanCard>> fields = beanField.getFields();
        fields.set(0, redBeanField);
        fields.set(1, soyBeanField);

        // Mock the deck's discard pile
        List<BeanCard> discardPile = new ArrayList<>();
        when(deckModel.getDiscardPile()).thenReturn(discardPile);
        // Mock the behavior of appendDiscardPile
        doAnswer(invocation -> {
            List<BeanCard> cards = invocation.getArgument(0);
            discardPile.addAll(cards);
            return null;
        }).when(deckModel).appendDiscardPile(redBeanField);

        doAnswer(invocation -> {
            List<BeanCard> cards = invocation.getArgument(0);
            discardPile.addAll(cards);
            return null;
        }).when(deckModel).appendDiscardPile(soyBeanField);

        // Execute the harvestField method
        player.setField(beanField);
        playerController.harvestAllFields(player);

        // Check if the number of cards is as expected (4 cards in discard pile)
        assertEquals(4, discardPile.size());
    }

    /**
     * Test to check if the correct number of gold coins is added to the player after harvesting.
     */
    @Test
    public void testHarvestFieldExpectedGoldCoins() {
        // Setup the player and beanField with mocked data
        PlayerModel player = new PlayerModel();
        player.setGoldCoins(0);
        BeanFieldModel beanField = new BeanFieldModel(2); // Initialize with 2 fields

        // First field with RedBeans
        List<BeanCard> redBeanField = new ArrayList<>();
        redBeanField.add(CardFactory.createBeanCard("RedBean"));
        redBeanField.add(CardFactory.createBeanCard("RedBean"));

        // Second field with SoyBeans
        List<BeanCard> soyBeanField = new ArrayList<>();
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));

        List<List<BeanCard>> fields = beanField.getFields();
        fields.set(0, redBeanField);
        fields.set(1, soyBeanField);

        // Mock the deck's discard pile
        List<BeanCard> discardPile = new ArrayList<>();
        when(deckModel.getDiscardPile()).thenReturn(discardPile);

        // Execute the harvestField method
        player.setField(beanField);
        playerController.harvestAllFields(player);

        // According to the rules:
        // 2 RedBeans => 1 coin
        // 2 SoyBeans => 1 coin
        // Check if the gold coins were added correctly (2 coins)
        assertEquals(2, player.getGoldCoins());
    }

    /**
     * Test to check if the field is empty after harvesting.
     */
    @Test
    public void testHarvestFieldEmptyFieldAfterHarvest() {
        // Setup the player and beanField with mocked data
        PlayerModel player = new PlayerModel();
        BeanFieldModel beanField = new BeanFieldModel(2); // Initialize with 2 fields

        // First field with RedBeans
        List<BeanCard> redBeanField = new ArrayList<>();
        redBeanField.add(CardFactory.createBeanCard("RedBean"));
        redBeanField.add(CardFactory.createBeanCard("RedBean"));

        // Second field with SoyBeans
        List<BeanCard> soyBeanField = new ArrayList<>();
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));

        List<List<BeanCard>> fields = beanField.getFields();
        fields.set(0, redBeanField);
        fields.set(1, soyBeanField);

        // Mock the deck's discard pile
        List<BeanCard> discardPile = new ArrayList<>();
        when(deckModel.getDiscardPile()).thenReturn(discardPile);

        // Execute the harvestField method
        playerController.harvestField(player, beanField);

        // Check if the fields are empty after harvest
        assertEquals(0, fields.get(0).size());
        assertEquals(0, fields.get(1).size());
    }

    /**
     * Test to check if the discard pile includes the harvested cards.
     */
    @Test
    public void testHarvestFieldDiscardPile() {
        // Setup the player and beanField with mocked data
        PlayerModel player = new PlayerModel();
        BeanFieldModel beanField = new BeanFieldModel(2); // Initialize with 2 fields

        // First field with RedBeans
        List<BeanCard> redBeanField = new ArrayList<>();
        redBeanField.add(CardFactory.createBeanCard("RedBean"));
        redBeanField.add(CardFactory.createBeanCard("RedBean"));

        // Second field with SoyBeans
        List<BeanCard> soyBeanField = new ArrayList<>();
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));
        soyBeanField.add(CardFactory.createBeanCard("SoyBean"));

        List<List<BeanCard>> fields = beanField.getFields();
        fields.set(0, redBeanField);
        fields.set(1, soyBeanField);

        // Mock the deck's discard pile
        List<BeanCard> discardPile = new ArrayList<>();
        when(deckModel.getDiscardPile()).thenReturn(discardPile);
        // Mock the behavior of appendDiscardPile
        doAnswer(invocation -> {
            List<BeanCard> cards = invocation.getArgument(0);
            discardPile.addAll(cards);
            return null;
        }).when(deckModel).appendDiscardPile(redBeanField);

        doAnswer(invocation -> {
            List<BeanCard> cards = invocation.getArgument(0);
            discardPile.addAll(cards);
            return null;
        }).when(deckModel).appendDiscardPile(soyBeanField);

        // Execute the harvestField method
        playerController.harvestField(player, beanField);

        // Check if the discard pile includes the harvested cards (4 cards)
        assertEquals(4, discardPile.size());
    }
}
