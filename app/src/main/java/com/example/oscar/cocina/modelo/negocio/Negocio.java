package com.example.oscar.cocina.modelo.negocio;

import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.Persistencia;

import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public interface Negocio {



    List<Receta> getRecetas();
}
