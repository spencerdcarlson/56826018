package com.hnb;

public enum ItemType {
    ANIMAL("[ANIMAL]"), HABITAT("[HABITAT]");

    private final String label;

    private ItemType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return this.label;
    }

}
