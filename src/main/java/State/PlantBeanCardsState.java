package State;

import BeanField.BeanFieldModel;
import Cards.BeanCard;
import Games.GameController;
import Player.PlayerController;
import Player.PlayerModel;
import java.util.LinkedList;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.LinkedList;

public class PlantBeanCardsState implements State {

    @Override
    public void plantBeanCards(GameContext context) {
        //Get the required instances
        GameController gameController = GameController.getInstance();
        PlayerController playerController = gameController.getPlayerController();

        //Get the active player
        Optional<PlayerModel> player = playerController.getPlayers()
                .stream()
                .filter(PlayerModel::isCurrentPlayer)
                .findFirst();



        if (player.isPresent()) {
            // Check if the player has cards ( if true continue otherwise change the state to phase 2 )
            if (player.get().getHand().isEmpty()) {
                context.setState(new TurnOverAndTradeState());
        } else  {
                // First face-up card on the top of the stack should be planted
                plantFaceUpCard(player.get(), playerController);

                //Ask the user for input , if the it wants to plant go through it again
                // Second face-card planting is based on the choice of the user ( wantsToPlant = true || false )
                boolean isWillingToPlantAnother = true;  // use getPlayersDecision function to assign
                if(isWillingToPlantAnother) {
                    plantFaceUpCard(player.get(), playerController);
                }
            }
        }

        //Transition to the nextState
        context.setState(new TurnOverAndTradeState());
    }

    @Override
    public void purchaseBuilding(GameContext context) {

    }

    @Override
    public void turnOverAndTrade(GameContext context) {
        throw new IllegalStateException("Cannot turn over and trade in this phase.");
    }

    @Override
    public void plantTurnedOverAndTradedCards(GameContext context) {
        throw new IllegalStateException("Cannot plant turned-over and traded cards in this phase.");
    }

    @Override
    public void drawBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot draw bean cards in this phase.");
    }


    private void plantFaceUpCard(PlayerModel player, PlayerController playerController) {
        BeanCard faceUpBeanCard = player.getHand().get(player.getHand().size() - 1);
        BeanFieldModel beanFields = player.getField();
        boolean planted = tryPlantCard(faceUpBeanCard, beanFields);


        // Check for a matching field
        for (List<BeanCard> field : beanFields.getFields()) {
            if (!field.isEmpty() && field.get(field.size() - 1).getCardName().equals(faceUpBeanCard.getCardName())) {
                field.add(faceUpBeanCard);
                planted = true;
                break;
            }
        }

        // If no matching field and no empty field - > havrest
        if (!planted) {
             //Harvest
            playerController.harvestField(player, beanFields);
            // Retry planting the card after running harvesting
            planted = tryPlantCard(faceUpBeanCard, beanFields);
        }

        if(planted) {
            player.getHand().get(player.getHand().size() - 1);
            player.drawCards();
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




    // Simulate asking the player if they want to plant another card
    private boolean getPlayerDecision() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to plant another card? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }
}
