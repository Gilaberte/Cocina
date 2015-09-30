package com.example.oscar.cocina.modelo.negocio;

import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoIngrediente;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoReceta;

import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class NegocioImpl implements Negocio {

    private DaoReceta daoReceta;
    private DaoIngrediente daoIngrediente;

    public NegocioImpl(DaoReceta daoReceta, DaoIngrediente daoIngrediente) {

        this.daoReceta = daoReceta;
        this.daoIngrediente = daoIngrediente;
    }
    @Override
    public List<Receta> getRecetas() {
        return daoReceta.getRecetas();
    }

    @Override
    public int addReceta(Receta receta) {
        return daoReceta.addReceta(receta);
    }

    @Override
    public Receta getRecetaById(int id) {
        return daoReceta.getRecetaById(id);
    }

    @Override
    public void removeReceta(Receta receta) {
        daoReceta.removeReceta(receta);
    }

    @Override
    public void updateReceta(Receta receta, int position) {
        daoReceta.updateReceta(receta, position);
    }


}
