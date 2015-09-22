package com.example.oscar.cocina.modelo.entidades;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oscar on 15/09/2015.
 */
public class Receta {

    private String nombre;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
    private String dificultad;
    private String preparacion;

    public Receta() {
        super();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
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
