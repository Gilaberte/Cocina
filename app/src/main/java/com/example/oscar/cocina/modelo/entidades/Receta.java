package com.example.oscar.cocina.modelo.entidades;

import java.util.ArrayList;

/**
 * Created by oscar on 15/09/2015.
 */
public class Receta {

    private String nombre;
    private String ingredientes;
    private String dificultad;
    private String preparacion;

    public Receta(String nombre, String ingredientes, String dificultad, String preparacion) {

        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.dificultad = dificultad;
        this.preparacion = preparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }
}
