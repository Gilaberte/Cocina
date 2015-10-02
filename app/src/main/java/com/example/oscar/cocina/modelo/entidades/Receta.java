package com.example.oscar.cocina.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oscar on 15/09/2015.
 */
public class Receta implements Serializable {

    private long id = -1;
    private String nombre;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
    private String dificultad;
    private String preparacion;

    public Receta() {
        super();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
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


    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public void removeIngrediente(int position) {
        this.ingredientes.remove(position);
    }


    public Ingrediente getIngredienteById(int position) {
        return ingredientes.get(position);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Receta{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", ingredientes=").append(ingredientes);
        sb.append(", dificultad='").append(dificultad).append('\'');
        sb.append(", preparacion='").append(preparacion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
