package com.tgf.ecoapp.ui.recycle;

import java.io.Serializable;

/**
 * Created by Martin B. on 5/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class Container implements Serializable {
    private String name;
    private String description;
    private int imageResId; // resource ID for container image

    // Constructor
    public Container(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
