package com.hnb;


public class Selection {
    private SelectionType type;
    private Object data;

    public Selection(SelectionType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public boolean isValid() {
        return this.type.equals(SelectionType.VALID);
    }

    public boolean isExit() {
        return this.type.equals(SelectionType.EXIT);
    }

    public Selection(SelectionType type) {
        this(type, null);
    }

    public SelectionType getType() {
        return type;
    }

    public Object getSelection() {
        return data;
    }
}
