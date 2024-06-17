package Cards;

public class BuildingCard implements Card{

    private String buildingType;
    private int cost;
    private String attribute;

    public BuildingCard(String buildingType, int cost, String attribute) {
        this.buildingType = buildingType;
        this.cost = cost;
        this.attribute = attribute;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public void create() {

    }

    @Override
    public void onTap() {

    }

    @Override
    public void render() {

    }
}
