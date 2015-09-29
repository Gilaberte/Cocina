package com.example.oscar.cocina.modelo.persistencia;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface DaoReceta {



    List<Receta> getRecetas();

    Receta getRecetaById(int id);

    int addReceta(Receta receta);

    void removeReceta(Receta receta);


    void updateReceta(Receta receta, int position);
}
