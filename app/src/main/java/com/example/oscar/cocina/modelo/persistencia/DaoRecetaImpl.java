package com.example.oscar.cocina.modelo.persistencia;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class DaoRecetaImpl implements DaoReceta {

    private CocinaApplication context;

    private List<Receta> recetas = new ArrayList<>();

    private ArrayAdapter medidasRecetaAdapter;

    public DaoRecetaImpl(CocinaApplication context) {

        this.context = context;


        //addReceta("Huevo frito", "Huevos y sal", "Facil", "poner en sarten");
        //addReceta("Macarrones", "macarrones y tomate", "Facil", "Hervir y poner tomate");

    }

    @Override
    public List<Receta> getRecetas() {
        return recetas;
    }

    public void addReceta(String nombre, ArrayList<Ingrediente> ingredientes, String dificultad, String preparacion){

        recetas.add(new Receta());

    }


}
