package Strategy;

import BeanField.BeanFieldModel;
import Cards.BeanCard;
import Player.PlayerModel;

import java.util.List;

public class PlantDeckBeanCards implements PlantStrategy{

    @Override
    public void plantCards(PlayerModel player) {
        BeanCard faceUpBeanCard = player.getHand().get(player.getHand().size() - 1);
        BeanFieldModel beanFields = player.getField();
        boolean planted = false;

        // Check for a matching field
        for (List<BeanCard> field : beanFields.getFields()) {
            if (!field.isEmpty() && field.get(field.size() - 1).getCardName().equals(faceUpBeanCard.getCardName())) {
                field.add(faceUpBeanCard);
                planted = true;
                break;
            }
        }

        // If no matching field, plant in the first available empty field
        if (!planted) {
            for (List<BeanCard> field : beanFields.getFields()) {
                if (field.isEmpty()) {
                    field.add(faceUpBeanCard);
                    planted = true;
                    break;
                }
            }
        }

        if(planted) {
            player.drawCards();
        }
    }

}
