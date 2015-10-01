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

        return daoReceta.getRecetas();

    }

    @Override
    public void addReceta(Receta receta) {

        //TODO DEBERIA BORRAR LOS INGREDIENTES ASOCIADOS Y VOLVERLOS A CREAR

        if( receta.getId() == -1 ){

            receta = daoReceta.addReceta(receta);
        }else{

            receta = daoReceta.updateReceta(receta);
        }

        long idReceta = receta.getId();
        List<Ingrediente> ingredientes = receta.getIngredientes();

        for (Ingrediente ingrediente: ingredientes) {

            long idIngrediente = daoIngrediente.createIngrediente(ingrediente);

            if( !daoReceta.existIngredienteInReceta(idReceta, idIngrediente) ){

                daoReceta.addIngredienteReceta(idReceta, idIngrediente);
            }

        }




    }

    @Override
    public Receta getRecetaById(int id) {
        return daoReceta.getRecetaById(id);
    }

    @Override
    public void removeReceta(Receta receta) {

        //TODO TENGO QUE BORRAR LOS INGREDIENTES ASOCIADOS
        daoReceta.removeReceta(receta);
    }

    @Override
    public ArrayList<Ingrediente> getIngredientesToReceta(long id) {
        return daoReceta.getIngredientesToReceta(id);
    }


}
