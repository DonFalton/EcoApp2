package com.tgf.ecoapp.ui.recycle;

import java.io.Serializable;

/**
 * Created by Martin B. on 5/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class Container implements Serializable {
    private String name; // Nombre del contenedor
    private String description; // Descripción del contenedor
    private int imageResId; // ID del recurso de imagen del contenedor

    // Constructor
    public Container(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getters para obtener los valores de los atributos

    /**
     * Obtiene el nombre del contenedor.
     * @return El nombre del contenedor.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción del contenedor.
     * @return La descripción del contenedor.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene el ID del recurso de imagen del contenedor.
     * @return El ID del recurso de imagen del contenedor.
     */
    public int getImageResId() {
        return imageResId;
    }
}
