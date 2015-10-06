package com.example.oscar.cocina.modelo.persistencia.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oscar on 06/10/2015.
 */
public class DaoMedidaImpl implements DaoMedida {

    public static final String TABLA_MEDIDA = "Medida";
    public static final String MEDIDA_CAMPO_ID = "id";
    public static final String MEDIDA_CAMPO_NOMBRE = "nombre";

    private SQLiteDatabase db;
    private CocinaApplication context;


    public DaoMedidaImpl(CocinaApplication context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;

    }

    @Override
    public List<String> getMedidas() {
            List<String> resultado = new LinkedList<>();

            Cursor cursor = db.query(TABLA_MEDIDA, null, null, null, null, null, null);

            if(cursor.moveToFirst()) {
                do {

                    String medida = cursor.getString(cursor.getColumnIndex(MEDIDA_CAMPO_NOMBRE));

                    resultado.add(medida);

                } while (cursor.moveToNext());
            }

            return resultado;

    }

    @Override
    public long getMedidaIdByName(String name) {
        String whereClause = MEDIDA_CAMPO_NOMBRE + " = ?";
        String[] whereArgs = {name};

        Cursor cursor = db.query(TABLA_MEDIDA, null, whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndex(MEDIDA_CAMPO_ID));
            return  id;
        }

        return -1;
    }
}
