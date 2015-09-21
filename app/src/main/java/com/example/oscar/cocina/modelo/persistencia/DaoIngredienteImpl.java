package com.example.oscar.cocina.modelo.persistencia;

import android.content.res.Resources;
import android.widget.ArrayAdapter;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manana on 21/09/15.
 */
public class DaoIngredienteImpl implements DaoIngrediente {

    private CocinaApplication context;

    private ArrayAdapter medidasRecetaAdapter;


    public DaoIngredienteImpl(CocinaApplication context) {
        this.context = context;
    }

    @Override
    public ArrayAdapter getMedidasIngredientes() {

        Resources res = context.getResources();
        medidasRecetaAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, res.getStringArray(R.array.receta_medidas));

        return medidasRecetaAdapter;
    }
}
