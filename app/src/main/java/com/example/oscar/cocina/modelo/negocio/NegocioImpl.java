package com.example.oscar.cocina.modelo.negocio;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoIngrediente;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoReceta;

import java.util.ArrayList;
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

        List<Receta> recetas= daoReceta.getRecetas();
        for (Receta receta: recetas) {
            receta.setIngredientes(daoReceta.getIngredientesReceta(receta.getId()));
        }

        return recetas;
    }

    @Override
    public void addReceta(Receta receta) {

        daoReceta.addReceta(receta);
        long idReceta = receta.getId();
        List<Ingrediente> ingredientes = receta.getIngredientes();

        for (Ingrediente ingrediente: ingredientes) {

            long idIngrediente = daoIngrediente.createIngrediente(ingrediente);
            daoReceta.addIngredienteReceta(idReceta, idIngrediente);
        }




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
