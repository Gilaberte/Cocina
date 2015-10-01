package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;

import java.util.ArrayList;

/**
 * Created by manana on 21/09/15.
 */
public interface DaoIngrediente {

    long createIngrediente(Ingrediente ingrediente);

    Ingrediente getIngredienteByName(String name);

    ArrayList<Ingrediente> getIngredientesToReceta(long id);


}
