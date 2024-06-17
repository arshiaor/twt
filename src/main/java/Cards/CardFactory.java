package Cards;

import java.util.HashMap;
import java.util.Map;

public class CardFactory {

    public static BeanCard createBeanCard(String cardType) {
        switch (cardType) {
            case "GardenBean":
                return new BeanCard("GardenBean", 6, createCoinValues(2, 3));
            case "RedBean":
                return new BeanCard("RedBean", 8, createCoinValues(2, 3, 4, 5));
            case "BlackEyedBean":
                return new BeanCard("BlackEyedBean", 10, createCoinValues(2, 4, 5, 6));
            case "SoyBean":
                return new BeanCard("SoyBean", 12, createCoinValues(2, 4, 6, 7));
            case "GreenBean":
                return new BeanCard("GreenBean", 14, createCoinValues(3, 5, 6, 7));
            case "StinkBean":
                return new BeanCard("StinkBean", 16, createCoinValues(3, 5, 7, 8));
            case "ChiliBean":
                return new BeanCard("ChiliBean", 18, createCoinValues(3, 6, 8, 9));
            case "BlueBean":
                return new BeanCard("BlueBean", 20, createCoinValues(1, 2, 3, 4));
            case "StartingCard":
                return new BeanCard("StartingCard", 1, createCoinValues(2, 4, 6, 10));
            default:
                throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
    }

    public static BuildingCard createBuildingCard(String cardType) {
        return switch (cardType) {
            case "Saloon" -> new BuildingCard("Saloon", 5, "Provides extra trading benefits.");
            case "General Store" -> new BuildingCard("General Store", 6, "Increases hand limit.");
            case "Bank" -> new BuildingCard("Bank", 8, "Provides loan benefits.");
            case "Hotel" -> new BuildingCard("Hotel", 10, "Doubles end-game bonuses.");
            case "Railroad" -> new BuildingCard("Railroad", 7, "Accelerates the draw pile.");
            case "Blacksmith" -> new BuildingCard("Blacksmith", 4, "Reduces planting restrictions.");
            case "Church" -> new BuildingCard("Church", 3, "Provides protection.");
            case "School" -> new BuildingCard("School", 5, "Provides education benefits.");
            default -> throw new IllegalArgumentException("Unknown building type: " + cardType);
        };
    }

    private static Map<Integer, Integer> createCoinValues(int... values) {
        Map<Integer, Integer> coinValues = new HashMap<>();
        int segment = 0;
        int index = 0;

        for (int i = values[0]; i <= values[values.length - 1]; i++) {
            if (index < values.length && i == values[index]) {
                index++;
                segment++;
            }
            coinValues.put(i, segment);
        }
        return coinValues;
    }

}