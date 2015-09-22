package com.example.oscar.cocina.modelo.persistencia;

import android.content.res.Resources;
import android.widget.ArrayAdapter;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manana on 21/09/15.
 */
public class DaoIngredienteImpl implements DaoIngrediente {

    private CocinaApplication context;

    private Ingrediente ingrediente;

    public DaoIngredienteImpl(CocinaApplication context) {
        this.context = context;
    }


    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
