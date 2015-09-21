package com.example.oscar.cocina.modelo.persistencia;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface DaoReceta {



    List<Receta> getRecetas();

    void addReceta(String nombre, Ingrediente ingredientes, String dificultad, String preparacion);



}
