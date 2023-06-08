package com.tgf.ecoapp.ui.map;
/**
 * Created by Martin B. on 8/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class MapsContainer {
    private String nombre;
    private double latitud;
    private double longitud;

    public MapsContainer() {
        // Constructor vacío necesario para Firebase
    }

    public MapsContainer(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
