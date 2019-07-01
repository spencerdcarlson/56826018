package com.hnb;

public enum AttributeType {
    NAME("Name: "), AGE("Age: "), HEAlTH_CONCERNS("Health concerns: "),
    FEEDING_SCHEDULE("Feeding schedule: "), TEMPERATURE("Temperature: "), FOOD_SOURCE("Food source: "),
    CLEANLINESS("Cleanliness: ");

    private final String label;

    private AttributeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return this.label;
    }

    public static AttributeType getByLabel(String label) {
        for (final AttributeType v : values()) {
            if (v.label.startsWith(label)) {
                return v;
            }
        }
        return null;
    }


}
