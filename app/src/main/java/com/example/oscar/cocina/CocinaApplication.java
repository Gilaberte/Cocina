package com.example.oscar.cocina;

import android.app.Application;

import com.example.oscar.cocina.modelo.negocio.Negocio;
import com.example.oscar.cocina.modelo.negocio.NegocioImpl;
import com.example.oscar.cocina.modelo.persistencia.PersistenciaImpl;
import com.example.oscar.cocina.modelo.servicio.Servicio;
import com.example.oscar.cocina.modelo.servicio.ServicioImpl;

/**
 * Created by oscar on 15/09/2015.
 */
public class CocinaApplication extends Application {

    private Servicio servicio;

    @Override
    public void onCreate() {
        super.onCreate();
        Negocio negocio = new NegocioImpl(new PersistenciaImpl());

        this.servicio = new ServicioImpl(negocio);

    }

    public Servicio getServicio() {
        return servicio;
    }
}
