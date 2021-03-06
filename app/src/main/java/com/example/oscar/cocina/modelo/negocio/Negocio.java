package com.example.oscar.cocina.modelo.negocio;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface Negocio {



    List<Receta> getRecetas();

    void addReceta(Receta receta);

    void updateReceta(Receta receta);

    Receta getRecetaById(int id);

    void removeReceta(Receta receta);

    ArrayList<Ingrediente> getIngredientesToReceta(long id);


    ArrayList<Ingrediente> getIngredientes();

    List<String> getMedidas();
}
