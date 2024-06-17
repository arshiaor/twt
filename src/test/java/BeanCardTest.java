import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeanCardTest {

    @Test
    public void testGetBeanCards() {
        // Call the method to test
        List<BeanCard> beanCards = getBeanCards();

        // Define the expected distribution
        Map<String, Integer> expectedCardTypeMap = Map.of(
                "GardenBean", 20,
                "RedBean", 18,
                "BlackEyedBean", 16,
                "SoyBean", 14,
                "GreenBean", 12,
                "StinkBean", 10,
                "ChiliBean", 8,
                "BlueBean", 6,
                "StartingCard", 1
        );

        // Check total number of cards
        int expectedTotalCards = expectedCardTypeMap.values().stream().mapToInt(Integer::intValue).sum();
        assertEquals(expectedTotalCards, beanCards.size(), "Total number of cards is incorrect");

        // Check distribution of each card type
        Map<String, Integer> actualCardTypeMap = new HashMap<>();
        for (BeanCard card : beanCards) {
            actualCardTypeMap.put(card.getType(), actualCardTypeMap.getOrDefault(card.getType(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : expectedCardTypeMap.entrySet()) {
            String cardType = entry.getKey();
            int expectedCount = entry.getValue();
            int actualCount = actualCardTypeMap.getOrDefault(cardType, 0);
            assertEquals(expectedCount, actualCount, "Card count mismatch for card type: " + cardType);
        }

        // Check shuffle (simple non-deterministic check, might fail occasionally)
        boolean isShuffled = false;
        List<BeanCard> unshuffledCards = createUnshuffledDeck(expectedCardTypeMap);
        for (int i = 0; i < beanCards.size(); i++) {
            if (!beanCards.get(i).equals(unshuffledCards.get(i))) {
                isShuffled = true;
                break;
            }
        }
        assertTrue(isShuffled, "The deck appears to be unshuffled");
    }

    private List<BeanCard> createUnshuffledDeck(Map<String, Integer> cardTypeMap) {
        List<BeanCard> unshuffledCards = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cardTypeMap.entrySet()) {
            String cardType = entry.getKey();
            int numberOfCards = entry.getValue();
            for (int i = 0; i < numberOfCards; i++) {
                unshuffledCards.add(CardFactory.createBeanCard(cardType));
            }
        }
        return unshuffledCards;
    }

    // Mock classes for the purpose of this test
    static class BeanCard {
        private String type;

        public BeanCard(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            BeanCard beanCard = (BeanCard) obj;
            return type.equals(beanCard.type);
        }

        @Override
        public int hashCode() {
            return type.hashCode();
        }
    }

    static class CardFactory {
        public static BeanCard createBeanCard(String type) {
            return new BeanCard(type);
        }
    }

    // The method under test
    public List<BeanCard> getBeanCards() {
        Map<String, Integer> cardTypeMap = Map.of(
                "GardenBean", 20,
                "RedBean", 18,
                "BlackEyedBean", 16,
                "SoyBean", 14,
                "GreenBean", 12,
                "StinkBean", 10,
                "ChiliBean", 8,
                "BlueBean", 6,
                "StartingCard", 1 // Assuming only one starting card
        );

        List<BeanCard> cards = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cardTypeMap.entrySet()) {
            String cardType = entry.getKey();
            int numberOfCards = entry.getValue();
            for (int i = 0; i < numberOfCards; i++) {
                cards.add(CardFactory.createBeanCard(cardType));
            }
        }

        Collections.shuffle(cards);
        System.out.println("Number of cards are : " + cards.size());
        System.out.println(cards);
        System.out.println(" -------------------------");
        return cards;
    }
}
