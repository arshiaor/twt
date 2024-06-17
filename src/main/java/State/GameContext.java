package State;

public class GameContext {
    private State state;

    public GameContext() {
        // Initial state
        state = new PlantBeanCardsState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void plantBeanCards() {
        state.plantBeanCards(this);
    }

    public void turnOverAndTrade() {
        state.turnOverAndTrade(this);
    }

    public void plantTurnedOverAndTradedCards() {
        state.plantTurnedOverAndTradedCards(this);
    }

    public void drawBeanCards() {
        state.drawBeanCards(this);
    }
}
