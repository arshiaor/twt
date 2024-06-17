package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BeanCardController {
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
