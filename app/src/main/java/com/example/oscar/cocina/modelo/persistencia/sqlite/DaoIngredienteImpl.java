package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.GestorTransaccional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by manana on 21/09/15.
 */
public class DaoIngredienteImpl implements DaoIngrediente {

    public static final String TABLA_INGREDIENTE = "Ingrediente";
    public static final String INGREDIENTE_CAMPO_ID = "id";
    public static final String INGREDIENTE_CAMPO_NOMBRE = "nombre";
    public static final String INGREDIENTE_CAMPO_UNIDAD = "unidad";
    public static final String MEDIDA_CAMPO_NOMBRE_ALIAS = "medida";

    private SQLiteDatabase db;
    private CocinaApplication context;

    private Ingrediente ingrediente;

    public DaoIngredienteImpl(CocinaApplication context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }


    @Override
    public long createIngrediente(Ingrediente ingrediente) {

        Ingrediente nuevoIngrediente = getIngredienteByName(ingrediente.getNombre().toString());

        if( nuevoIngrediente == null ){

            long id = db.insert(TABLA_INGREDIENTE, INGREDIENTE_CAMPO_ID, ingredienteToContentValues(ingrediente));
            ingrediente.setId(id);
            nuevoIngrediente = ingrediente;

        }
        return nuevoIngrediente.getId();


    }

    @Override
    public Ingrediente getIngredienteByName(String name) {
        String whereClause = INGREDIENTE_CAMPO_NOMBRE + " = ?";
        String[] whereArgs = {name};

        Cursor cursor = db.query(TABLA_INGREDIENTE, null, whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            Ingrediente Ingrediente = cursorToIngrediente(cursor);
            return  Ingrediente;
        }

        return null;
    }


    @Override
    public ArrayList<Ingrediente> getIngredientesToReceta(long id) {

        Log.i(this.getClass().getName(), "Consultando los ingredientes de la receta: " + id);

        String sql = "SELECT Ingrediente.id, Ingrediente.nombre, Medida.nombre as '"+ MEDIDA_CAMPO_NOMBRE_ALIAS +"', RecetaIngrediente.unidad " +
                "FROM RecetaIngrediente " +
                "INNER JOIN Ingrediente " +
                "ON RecetaIngrediente.idIngrediente = Ingrediente.id " +
                "INNER JOIN Medida " +
                "ON RecetaIngrediente.idMedida = Medida.id " +
                "WHERE RecetaIngrediente.idReceta = ? ";

        Log.i(this.getClass().getName(), "SQL: " + sql);

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        ArrayList<Ingrediente> ingredientes = new ArrayList();

        if(cursor.moveToFirst()) {
            do {
                Ingrediente ingrediente = cursorToIngrediente(cursor);
                ingrediente.setMedida(cursor.getString(cursor.getColumnIndex(MEDIDA_CAMPO_NOMBRE_ALIAS)));
                ingrediente.setUnidad(cursor.getString(cursor.getColumnIndex(INGREDIENTE_CAMPO_UNIDAD)));

                ingredientes.add(ingrediente);
            } while (cursor.moveToNext());
        }

        Log.i(this.getClass().getName(), "Los ingredientes encontrados con: " + ingredientes);

        return ingredientes;

    }

    @Override
    public ArrayList<Ingrediente> getIngredientes() {
        return queryWithWhere(null, null);
    }



    private Ingrediente cursorToIngrediente(Cursor cursor) {
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setId(cursor.getInt(cursor.getColumnIndex(INGREDIENTE_CAMPO_ID)));
        ingrediente.setNombre(cursor.getString(cursor.getColumnIndex(INGREDIENTE_CAMPO_NOMBRE)));

        return ingrediente;
    }

    private ContentValues ingredienteToContentValues(Ingrediente entidad) {
        ContentValues ingredienteContentValues = new ContentValues();
        ingredienteContentValues.put(INGREDIENTE_CAMPO_NOMBRE, entidad.getNombre());
        return ingredienteContentValues;
    }

    private ArrayList<Ingrediente> queryWithWhere(String whereClause, String[] whereArgs) {
        ArrayList<Ingrediente> resultado = new ArrayList();

        Cursor cursor = db.query(TABLA_INGREDIENTE, null, whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            do {

                Ingrediente ingrediente = cursorToIngrediente(cursor);

                resultado.add(ingrediente);

            } while (cursor.moveToNext());
        }

        return resultado;
    }
}
