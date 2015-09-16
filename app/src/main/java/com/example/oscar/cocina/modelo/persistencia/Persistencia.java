package com.example.oscar.cocina.modelo.persistencia;

import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface Persistencia {



    List<Receta> getRecetas();

    void addReceta(String nombre, String ingredientes, String dificultad, String preparacion);

}
