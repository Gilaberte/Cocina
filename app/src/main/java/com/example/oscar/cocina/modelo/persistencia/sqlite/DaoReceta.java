package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.GestorTransaccional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface DaoReceta {


    List<Receta> getRecetas();

    Receta getRecetaById(int id);

    Receta addReceta(Receta receta);

    void removeReceta(Receta receta);

    Receta updateReceta(Receta receta);

    void addIngredienteReceta(long receta, long idReceta, long idIngrediente, String s);

    void removeIngredientesByRecetaId(Receta receta);

}
