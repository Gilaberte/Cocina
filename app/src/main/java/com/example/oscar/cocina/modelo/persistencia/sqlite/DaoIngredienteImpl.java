package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

/**
 * Created by manana on 21/09/15.
 */
public class DaoIngredienteImpl implements DaoIngrediente {

    private SQLiteDatabase db;
    private CocinaApplication context;

    private Ingrediente ingrediente;

    public DaoIngredienteImpl(CocinaApplication context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }


    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }
}
