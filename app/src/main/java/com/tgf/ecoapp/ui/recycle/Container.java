package com.tgf.ecoapp.ui.recycle;

/**
 * Created by Martin B. on 12/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class Container {
    private String Nombre;
    private String Contenido;
    private String Color;
    private String Descripción;

    // Asegúrate de tener un constructor vacío para Firestore
    public Container() {}

    public Container(String Nombre, String Contenido, String Color, String Descripción) {
        this.Nombre = Nombre;
        this.Contenido = Contenido;
        this.Color = Color;
        this.Descripción = Descripción;
    }

    // Getters y Setters para cada campo

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String descripción) {
        Descripción = descripción;
    }
}
