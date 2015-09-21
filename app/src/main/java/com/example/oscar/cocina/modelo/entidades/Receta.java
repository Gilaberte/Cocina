package com.example.oscar.cocina.modelo.entidades;

import java.util.ArrayList;

/**
 * Created by oscar on 15/09/2015.
 */
public class Receta {

    private String nombre;
    private Ingrediente ingredientes;
    private String dificultad;
    private String preparacion;

    public Receta(String nombre, Ingrediente ingredientes, String dificultad, String preparacion) {

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

    public Ingrediente getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingrediente ingredientes) {
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
