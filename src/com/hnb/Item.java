package com.hnb;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Item implements Monitorable {
    private List<Attribute> attributes = new ArrayList<>();
    private ItemType type;

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void setAsterisk(Enum key) {
        try {
            getAttribute(key).setAsterisk(true);
        } catch (NullPointerException e) {
            System.err.println("Can not set " + key + " as asterisked on " + this.toString());
        }
    }

    public Attribute getAsterisk() {
        // does not account for the fact that there could
        // possibly be multiple asterisk attributes
        // simply returns the first one
        for (Attribute attribute : attributes) {
            if (attribute.isAsterisk()) {
                return attribute;
            }
        }
        return null;
    }

    public Attribute getAttribute(Enum key) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey() == key) {
                return attribute;
            }
        }
        return null;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(getType().getLabel()).append("\n");
        for (Attribute attribute : attributes) {
            sb.append("\t").append(attribute).append("\n");
        }
//        sb.append("asterisk: ").append(getAsterisk()).append("\n");
        return sb.toString();
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public void showAsterisk() {
        final Attribute asterisk = getAsterisk();
        if(asterisk != null) {
            JOptionPane.showMessageDialog(null, getAsterisk());
        }
    }
}
