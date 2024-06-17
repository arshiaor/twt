package State;

public class PurchaseBuildingState implements State{

    @Override
    public void plantBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot plant bean cards in this phase.");
    }

    @Override
    public void turnOverAndTrade(GameContext context) {
        throw new IllegalStateException("Cannot turn over and trade in this phase.");
    }

    @Override
    public void purchaseBuilding(GameContext context) {
        //Purchase Building here
    }

    @Override
    public void plantTurnedOverAndTradedCards(GameContext context) {
        System.out.println("Planting turned-over and traded bean cards.");
    }

    @Override
    public void drawBeanCards(GameContext context) {
        throw new IllegalStateException("Cannot draw bean cards in this phase.");
    }
}
