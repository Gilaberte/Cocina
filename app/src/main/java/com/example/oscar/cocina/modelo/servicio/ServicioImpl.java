package com.example.oscar.cocina.modelo.servicio;

import android.widget.ArrayAdapter;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.negocio.Negocio;

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
    public ArrayAdapter getMedidasIngredientes() {
        return negocio.getMedidasIngredientes();
    }

    @Override
    public ArrayAdapter getCantidadesIngredientes() {
        return negocio.getCantidadesIngredientes();
    }


}
