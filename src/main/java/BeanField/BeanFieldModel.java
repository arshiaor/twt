package BeanField;

import Cards.BeanCard;

import java.util.ArrayList;
import java.util.List;

public class BeanFieldModel {

    private int numberOfFields;

    private List<List<BeanCard>> fields;

    public void plant(BeanCard card) {}

    public void harvest(List<BeanCard> cards){}

    public BeanFieldModel(int numberOfFields) {
        if (numberOfFields < 2 || numberOfFields > 3) {
            throw new IllegalArgumentException("BeanFieldMat can only have 2 or 3 fields.");
        }
        this.numberOfFields = numberOfFields;
        this.fields = new ArrayList<>();
        for (int i = 0; i < numberOfFields; i++) {
            fields.add(new ArrayList<>()); // Initialize each field as an empty list
        }
    }

    public void plant(BeanCard card, int fieldIndex) {
        if (fieldIndex < 0 || fieldIndex >= numberOfFields) {
            throw new IndexOutOfBoundsException("Invalid field index.");
        }
        fields.get(fieldIndex).add(card);
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void setNumberOfFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    public List<List<BeanCard>> getFields() {
        return fields;
    }

    public void setFields(List<List<BeanCard>> fields) {
        this.fields = fields;
    }

    public List<BeanCard> getAllCardsInField(int fieldIndex) {
        if (fieldIndex >= 0 && fieldIndex < fields.size()) {
            return fields.get(fieldIndex);
        } else {
            throw new IndexOutOfBoundsException("Invalid field index: " + fieldIndex);
        }
    }

}
