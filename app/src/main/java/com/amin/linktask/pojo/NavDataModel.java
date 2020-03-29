package com.amin.linktask.pojo;

import android.graphics.drawable.Drawable;

public class NavDataModel {
    private Drawable icon;
    private String name;
    private Boolean isSelected;

    public NavDataModel(Drawable icon, String name,Boolean isSelected) {
        this.icon = icon;
        this.name = name;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
