package com.example.oscar.cocina.modelo.negocio;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoIngrediente;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoReceta;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.GestorTransaccional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class NegocioImpl implements Negocio {

    private DaoReceta daoReceta;
    private DaoIngrediente daoIngrediente;
    private GestorTransaccional gestorTransaccional;

    public NegocioImpl(DaoReceta daoReceta, DaoIngrediente daoIngrediente, GestorTransaccional gestorTransaccional) {

        this.daoReceta = daoReceta;
        this.daoIngrediente = daoIngrediente;
        this.gestorTransaccional = gestorTransaccional;
    }
    @Override
    public List<Receta> getRecetas() {

        return daoReceta.getRecetas();

    }


    @Override
    public void addReceta(Receta receta) {

        try {
            gestorTransaccional.beginTransaccion();

            receta = daoReceta.addReceta(receta);

            long idReceta = receta.getId();
            List<Ingrediente> ingredientes = receta.getIngredientes();

            for (Ingrediente ingrediente: ingredientes) {

                long idIngrediente = daoIngrediente.createIngrediente(ingrediente);

                daoReceta.addIngredienteReceta(idReceta, idIngrediente);


            }

            gestorTransaccional.setTransactionSuccessful();
        } finally {
            gestorTransaccional.endTransaccion();
        }












    }

    @Override
    public void updateReceta(Receta receta) {





        try {
            gestorTransaccional.beginTransaccion();

            receta = daoReceta.updateReceta(receta);
            daoReceta.removeIngredientesByRecetaId(receta);

            long idReceta = receta.getId();
            List<Ingrediente> ingredientes = receta.getIngredientes();

            for (Ingrediente ingrediente: ingredientes) {

                long idIngrediente = daoIngrediente.createIngrediente(ingrediente);

                daoReceta.addIngredienteReceta(idReceta, idIngrediente);


            }
            gestorTransaccional.setTransactionSuccessful();
        } finally {
            gestorTransaccional.endTransaccion();
        }








    }

    @Override
    public Receta getRecetaById(int id) {
        return daoReceta.getRecetaById(id);
    }

    @Override
    public void removeReceta(Receta receta) {


        try {
            gestorTransaccional.beginTransaccion();

            daoReceta.removeIngredientesByRecetaId(receta);
            daoReceta.removeReceta(receta);
            gestorTransaccional.setTransactionSuccessful();
        } finally {
            gestorTransaccional.endTransaccion();
        }


    }

    @Override
    public ArrayList<Ingrediente> getIngredientesToReceta(long id) {
        return daoIngrediente.getIngredientesToReceta(id);
    }

    @Override
    public ArrayList<Ingrediente> getIngredientes() {
        return daoIngrediente.getIngredientes();
    }


}
