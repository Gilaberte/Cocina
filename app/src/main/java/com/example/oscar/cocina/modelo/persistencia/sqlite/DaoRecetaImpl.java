package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.text.ParseException;
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


    private SQLiteDatabase db;
    private CocinaApplication context;

    private List<Receta> recetas = new ArrayList<>();

    public DaoRecetaImpl(CocinaApplication context, SQLiteDatabase db) {

        this.context = context;
        this.db = db;


        //addReceta("Huevo frito", "Huevos y sal", "Facil", "poner en sarten");
        //addReceta("Macarrones", "macarrones y tomate", "Facil", "Hervir y poner tomate");

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
            Receta receta = cursorToReceta(cursor);
            return  receta;
        }

        return null;
    }


    @Override
    public int addReceta(Receta receta){

        /*recetas.add(receta);
        return recetas.indexOf(receta);*/

        long idAutogenerado = db.insert(TABLA_RECETA, RECETA_CAMPO_ID, noticiaToContentValues(receta));
        receta.setId(idAutogenerado);
        return (int)idAutogenerado;

    }

    @Override
    public void removeReceta(Receta receta) {

        String whereClause = RECETA_CAMPO_ID + " = ?";
        String[] whereArgs = {String.valueOf(receta.getId())};

        db.delete(TABLA_RECETA, whereClause, whereArgs);


    }

    @Override
    public void updateReceta(Receta receta, int position) {

        String whereClause = RECETA_CAMPO_ID + " = ?";
        String[] whereArgs = {String.valueOf(receta.getId())};

        db.update(TABLA_RECETA, noticiaToContentValues(receta), whereClause, whereArgs);
    }



    private ContentValues noticiaToContentValues(Receta entidad) {
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
        LinkedList<Receta> resultado = new LinkedList<>();

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
