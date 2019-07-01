package com.hnb;

import java.util.Map;

public class MenuSelection extends Selection {
    public MenuSelection(SelectionType type, Object data) {
        super(type, data);
    }

    public MenuSelection(Selection selection) {
        this(selection.getType(), selection.getSelection());
    }

    @Override
    public Map<String, Item> getSelection() {
        return (Map<String, Item>) super.getSelection();
    }
}
