package com.tgf.ecoapp.ui.others.settings;

import androidx.fragment.app.Fragment;

/**
 * Created by Martin B. on 10/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingOption {

    private String name; // nombre de la opción

    // Constructor, getters y setters

    public SettingOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
