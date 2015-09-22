package com.example.oscar.cocina.modelo.servicio;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface Servicio {

    List<Receta> getRecetas();

    public void addReceta(Receta receta);




}