package State;

public interface State {
    void turnOverAndTrade(GameContext context);
    void plantTurnedOverAndTradedCards(GameContext context);
    void drawBeanCards(GameContext context);
    void purchaseBuilding(GameContext context);
    void plantBeanCards(GameContext gameContext);
}
