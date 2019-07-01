package com.hnb;

public class Attribute<T> {
    private boolean asterisk;
    private Enum key;
    private T value;

    public Attribute(Enum key, T value) {
        this.key = key;
        this.value = value;
        this.asterisk = false;
    }

    public boolean isAsterisk() {
        return asterisk;
    }

    public void setAsterisk(boolean asterisk) {
        this.asterisk = asterisk;
    }

    public Enum getKey(){
        return this.key;
    }

    public String toString() {
        return new StringBuilder(this.key.toString()).append(value).toString();
    }
}
