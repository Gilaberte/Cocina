package com.example.oscar.cocina.modelo.negocio;

import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.Persistencia;

import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class NegocioImpl implements Negocio {

    private Persistencia persistencia;

    public NegocioImpl(Persistencia persistencia) {

        this.persistencia = persistencia;
    }
    @Override
    public List<Receta> getRecetas() {
        return persistencia.getRecetas();
    }
}
