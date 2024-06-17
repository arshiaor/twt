import BeanField.BeanFieldModel;
import Cards.BeanCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlantTest {
    private BeanFieldModel beanFields;
    private BeanCard faceUpBeanCard;

    @BeforeEach
    void setUp() {
        // Initialize a BeanFieldModel with 3 fields
        beanFields = new BeanFieldModel(3);
    }

    @Test
    public void testTryPlantCardInMatchingField() {
        // Set up fields with some cards
        BeanCard card1 = new BeanCard("BeanA", 1, createCoinValues());
        beanFields.getFields().get(0).add(card1);

        faceUpBeanCard = new BeanCard("BeanA", 1, createCoinValues());

        // Attempt to plant the card
        boolean result = tryPlantCard(faceUpBeanCard, beanFields);

        // Check that the card was planted in the matching field
        assertTrue(result);
        assertEquals(2, beanFields.getFields().get(0).size());
        assertEquals(faceUpBeanCard, beanFields.getFields().get(0).get(1));
    }

    @Test
    public void testTryPlantCardInEmptyField() {
        faceUpBeanCard = new BeanCard("BeanB", 8, createCoinValues());

        // Attempt to plant the card
        boolean result = tryPlantCard(faceUpBeanCard, beanFields);

        // Check that the card was planted in the first empty field
        assertTrue(result);
        assertEquals(1, beanFields.getFields().get(0).size());
        assertEquals(faceUpBeanCard, beanFields.getFields().get(0).get(0));
    }

    @Test
    public void testTryPlantCardNoMatchingOrEmptyField() {
        // Set up fields with some cards
        BeanCard card1 = new BeanCard("BeanA", 10, createCoinValues());
        BeanCard card2 = new BeanCard("BeanB", 8, createCoinValues());
        BeanCard card3 = new BeanCard("BeanC", 12, createCoinValues());

        beanFields.getFields().get(0).add(card1);
        beanFields.getFields().get(1).add(card2);
        beanFields.getFields().get(2).add(card3);

        faceUpBeanCard = new BeanCard("BeanD", 6, createCoinValues());

        // Attempt to plant the card
        boolean result = tryPlantCard(faceUpBeanCard, beanFields);

        // Check that the card was not planted as there are no matching or empty fields
        assertFalse(result);
        for (List<BeanCard> field : beanFields.getFields()) {
            assertFalse(field.contains(faceUpBeanCard));
        }
    }


    private boolean tryPlantCard(BeanCard faceUpBeanCard, BeanFieldModel beanFields) {
        // Check for a matching field
        for (List<BeanCard> field : beanFields.getFields()) {
            if (!field.isEmpty() && field.get(field.size() - 1).getCardName().equals(faceUpBeanCard.getCardName())) {
                field.add(faceUpBeanCard);
                return true;
            }
        }

        // If no matching field, plant in the first available empty field
        for (List<BeanCard> field : beanFields.getFields()) {
            if (field.isEmpty()) {
                field.add(faceUpBeanCard);
                return true;
            }
        }

        // No matching field and no empty field
        return false;
    }



    private Map<Integer, Integer> createCoinValues() {
        Map<Integer, Integer> coinValues = new HashMap<>();
        coinValues.put(1, 1);
        coinValues.put(2, 2);
        coinValues.put(3, 3);
        coinValues.put(4, 4);
        return coinValues;
    }

}
