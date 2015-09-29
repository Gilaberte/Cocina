package com.example.oscar.cocina.modelo.negocio;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface Negocio {



    List<Receta> getRecetas();

    int addReceta(Receta receta);

    Receta getRecetaById(int id);

    void removeReceta(Receta receta);

    void updateReceta(Receta receta, int position);
}
