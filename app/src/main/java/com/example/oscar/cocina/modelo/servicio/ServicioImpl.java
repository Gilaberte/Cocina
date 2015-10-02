package com.example.oscar.cocina.modelo.servicio;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.negocio.Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class ServicioImpl implements Servicio {

    private Negocio negocio;

    public ServicioImpl(Negocio negocio) {
        this.negocio = negocio;
    }


    @Override
    public List<Receta> getRecetas() {
        return negocio.getRecetas();
    }

    @Override
    public void addReceta(Receta receta) {
        negocio.addReceta(receta);
    }

    @Override
    public void updateReceta(Receta receta) {
        negocio.updateReceta(receta);
    }

    @Override
    public Receta getRecetaById(int id) {
        return negocio.getRecetaById(id);
    }

    @Override
    public void removeReceta(Receta receta) {
        negocio.removeReceta(receta);
    }

    @Override
    public ArrayList<Ingrediente> getIngredientesToReceta(long id) {
        return negocio.getIngredientesToReceta(id);
    }




}
