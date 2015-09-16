package com.example.oscar.cocina.modelo.persistencia;

import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class PersistenciaImpl implements Persistencia {

    List<Receta> recetas = new ArrayList<>();

    public PersistenciaImpl() {

        addReceta("Huevo frito", "Huevos y sal", "Facil", "poner en sarten");
        addReceta("Macarrones", "macarrones y tomate", "Facil", "Hervir y poner tomate");

    }

    @Override
    public List<Receta> getRecetas() {
        return recetas;
    }

    public void addReceta(String nombre, String ingredientes, String dificultad, String preparacion){

        recetas.add(new Receta(nombre, ingredientes, dificultad, preparacion));

    }
}
