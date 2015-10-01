package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

/**
 * Created by manana on 21/09/15.
 */
public class DaoIngredienteImpl implements DaoIngrediente {

    public static final String TABLA_INGREDIENTE = "Ingrediente";
    public static final String INGREDIENTE_CAMPO_ID = "id";
    public static final String INGREDIENTE_CAMPO_NOMBRE = "nombre";

    private SQLiteDatabase db;
    private CocinaApplication context;

    private Ingrediente ingrediente;

    public DaoIngredienteImpl(CocinaApplication context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }


    @Override
    public long createIngrediente(Ingrediente ingrediente) {

        Ingrediente nuevoIngrediente = getIngredienteByName( ingrediente.getNombre().toString() );

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


    private Ingrediente cursorToIngrediente(Cursor cursor) {
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setId(cursor.getInt(cursor.getColumnIndex(INGREDIENTE_CAMPO_ID)));
        ingrediente.setNombre(cursor.getString(cursor.getColumnIndex(INGREDIENTE_CAMPO_NOMBRE)));

        return ingrediente;
    }

    private ContentValues ingredienteToContentValues(Ingrediente entidad) {
        ContentValues ingredienteContentValues = new ContentValues();
        ingredienteContentValues.put(INGREDIENTE_CAMPO_NOMBRE,entidad.getNombre());
        return ingredienteContentValues;
    }
}
