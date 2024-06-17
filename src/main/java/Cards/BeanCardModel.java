package Cards;

import java.util.Map;

public class BeanCardModel {
    private String beanType;
    private Map<Integer, Integer> beanometer;
    private boolean isTraded;

    public BeanCardModel(String beanType, Map<Integer, Integer> beanometer, boolean isTraded) {
        this.beanType = beanType;
        this.beanometer = beanometer;
        this.isTraded = false;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public Map<Integer, Integer> getBeanometer() {
        return beanometer;
    }

    public void setBeanometer(Map<Integer, Integer> beanometer) {
        this.beanometer = beanometer;
    }

    public boolean isTraded() {
        return isTraded;
    }

    public void setTraded(boolean traded) {
        isTraded = traded;
    }
}
