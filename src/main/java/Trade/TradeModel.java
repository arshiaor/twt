package Trade;

import Cards.BeanCardModel;
import Player.PlayerModel;

import java.util.List;

public class TradeModel {
    private PlayerModel fromPlayer;
    private PlayerModel toPlayer;
    private List<BeanCardModel> offeredCards;
    private List<BeanCardModel> requestedCards;

    public TradeModel(PlayerModel fromPlayer, PlayerModel toPlayer, List<BeanCardModel> offeredCards, List<BeanCardModel> requestedCards) {
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.offeredCards = offeredCards;
        this.requestedCards = requestedCards;
    }
    public void executeTrade(PlayerModel fromPlayer, PlayerModel toPlayer, List<BeanCardModel> requestedCards){}



}
