package Strategy;

import BeanField.BeanFieldModel;
import Cards.BeanCard;
import Games.GameController;
import Player.PlayerModel;
import Player.PlayerController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlantTradedAndTurnedOverCards implements PlantStrategy {

    @Override
    public void plantCards(PlayerModel player) {
        // Plant turned over and traded cards in the fields for the player
        GameController gameController = GameController.getInstance();
        PlayerController playerController = gameController.getPlayerController();
        List<BeanCard> horizontalCards = player.getTurnedOverCards();
        List<BeanCard> tradedBeanCards = player.getTradedCards();
        List<BeanCard> allCards = new ArrayList<>();

        if (player.isCurrentPlayer()) {
            // Plant turned over cards that were not traded as well
            allCards.addAll(horizontalCards);
            allCards.addAll(tradedBeanCards);

            BeanFieldModel beanFields = player.getField();

            boolean isRemainedCards = !allCards.isEmpty();

            while (isRemainedCards) {
                isRemainedCards = plantCardsInEmptyFields(allCards, beanFields);
                isRemainedCards = isRemainedCards || plantCardsInOccupiedFields(allCards, beanFields);
                if (isRemainedCards) {
                    // Harvest field if there are cards remaining
                    playerController.harvestField(player, beanFields);
                }
            }
        }
    }

    private boolean plantCardsInOccupiedFields(List<BeanCard> allCards, BeanFieldModel beanFields) {
        boolean isRemainedCards = !allCards.isEmpty();
        Iterator<BeanCard> cardIterator = allCards.iterator();

        while (cardIterator.hasNext()) {
            BeanCard card = cardIterator.next();
            for (List<BeanCard> field : beanFields.getFields()) {
                if (!field.isEmpty() && field.get(field.size() - 1).getCardName().equals(card.getCardName())) {
                    field.add(card);
                    cardIterator.remove();
                    break;
                }
            }
        }

        return !allCards.isEmpty();
    }

    private boolean plantCardsInEmptyFields(List<BeanCard> allCards, BeanFieldModel beanFields) {
        boolean isRemainedCards = !allCards.isEmpty();
        Iterator<BeanCard> cardIterator = allCards.iterator();

        while (cardIterator.hasNext()) {
            BeanCard card = cardIterator.next();
            for (List<BeanCard> field : beanFields.getFields()) {
                if (field.isEmpty()) {
                    field.add(card);
                    cardIterator.remove();
                    break;
                }
            }
        }

        return !allCards.isEmpty();
    }
}
