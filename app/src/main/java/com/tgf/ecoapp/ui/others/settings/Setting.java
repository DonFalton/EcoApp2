package com.tgf.ecoapp.ui.others.settings;

/**
 * Created by Martin B. on 13/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class Setting {

    private String name; // nombre del ajuste

    // Constructor, getters y setters

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
