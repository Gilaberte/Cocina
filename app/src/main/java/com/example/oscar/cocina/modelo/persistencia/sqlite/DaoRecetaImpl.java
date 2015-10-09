package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class DaoRecetaImpl implements DaoReceta {

    public static final String TABLA_RECETA = "Receta";
    public static final String RECETA_CAMPO_ID = "id";
    public static final String RECETA_CAMPO_NOMBRE = "nombre";
    public static final String RECETA_CAMPO_DIFICULTAD = "dificultad";
    public static final String RECETA_CAMPO_PREPARACION = "preparacion";

    public static final String TABLA_RECETA_INGREDIENTE = "RecetaIngrediente";
    public static final String RECETA_INGREDIENTE_CAMPO_IDRECETA = "idReceta";
    public static final String RECETA_INGREDIENTE_CAMPO_IDINGREDIENTE = "idIngrediente";
    public static final String RECETA_INGREDIENTE_CAMPO_IDMEDIDA = "idMedida";
    public static final String RECETA_INGREDIENTE_CAMPO_UNIDAD = "unidad";

    private SQLiteDatabase db;
    private CocinaApplication context;

    private List<Receta> recetas = new ArrayList();




    public DaoRecetaImpl(CocinaApplication context, SQLiteDatabase db) {

        this.context = context;
        this.db = db;


    }



    @Override
    public List<Receta> getRecetas() {

        return queryWithWhere(null, null);

    }

    @Override
    public Receta getRecetaById(int id) {


        String whereClause = RECETA_CAMPO_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLA_RECETA, null, whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            return cursorToReceta(cursor);
        }

        return null;
    }


    @Override
    public Receta addReceta(Receta receta){

        Log.i(this.getClass().getName(), "Añadiendo la receta: " + receta);

        long idAutogenerado = db.insert(TABLA_RECETA, RECETA_CAMPO_ID, recetaToContentValues(receta));

        receta.setId(idAutogenerado);

        Log.i(this.getClass().getName(), "Añadiendo la receta: " + receta);

        return receta;
    }

    @Override
    public void removeReceta(Receta receta) {

        String whereClause = RECETA_CAMPO_ID + " = ?";
        String[] whereArgs = {String.valueOf(receta.getId())};

        db.delete(TABLA_RECETA, whereClause, whereArgs);


    }

    @Override
    public Receta updateReceta(Receta receta) {

        String whereClause = RECETA_CAMPO_ID + " = ?";
        String[] whereArgs = {String.valueOf(receta.getId())};

        db.update(TABLA_RECETA, recetaToContentValues(receta), whereClause, whereArgs);
        return receta;
    }

    @Override
    public void addIngredienteReceta(long idReceta, long idIngrediente, long idMedida, String unidad) {

        ContentValues ingredienteContentValues = new ContentValues();
        ingredienteContentValues.put(RECETA_INGREDIENTE_CAMPO_IDRECETA, idReceta);
        ingredienteContentValues.put(RECETA_INGREDIENTE_CAMPO_IDINGREDIENTE, idIngrediente);
        ingredienteContentValues.put(RECETA_INGREDIENTE_CAMPO_IDMEDIDA, idMedida);
        ingredienteContentValues.put(RECETA_INGREDIENTE_CAMPO_UNIDAD, unidad);

        db.insert(TABLA_RECETA_INGREDIENTE, null, ingredienteContentValues);



    }

    @Override
    public void removeIngredientesByRecetaId(Receta receta) {

        String whereClause = RECETA_INGREDIENTE_CAMPO_IDRECETA + " = ?";
        String[] whereArgs = {String.valueOf(receta.getId())};

        db.delete(TABLA_RECETA_INGREDIENTE, whereClause, whereArgs);
    }



    private ContentValues recetaToContentValues(Receta entidad) {
        ContentValues recetaContentValues = new ContentValues();
        recetaContentValues.put(RECETA_CAMPO_NOMBRE,entidad.getNombre());
        recetaContentValues.put(RECETA_CAMPO_DIFICULTAD,entidad.getDificultad());
        recetaContentValues.put(RECETA_CAMPO_PREPARACION, entidad.getPreparacion());
        return recetaContentValues;
    }

    private Receta cursorToReceta(Cursor cursor) {
        Receta receta = new Receta();

        receta.setId(cursor.getInt(cursor.getColumnIndex(RECETA_CAMPO_ID)));
        receta.setNombre(cursor.getString(cursor.getColumnIndex(RECETA_CAMPO_NOMBRE)));
        receta.setDificultad(cursor.getString(cursor.getColumnIndex(RECETA_CAMPO_DIFICULTAD)));
        receta.setPreparacion(cursor.getString(cursor.getColumnIndex(RECETA_CAMPO_PREPARACION)));

        return receta;
    }

    private List<Receta> queryWithWhere(String whereClause, String[] whereArgs) {
        LinkedList<Receta> resultado = new LinkedList();

        Cursor cursor = db.query(TABLA_RECETA, null, whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            do {

                Receta receta = cursorToReceta(cursor);

                resultado.add(receta);

            } while (cursor.moveToNext());
        }

        return resultado;
    }

}
