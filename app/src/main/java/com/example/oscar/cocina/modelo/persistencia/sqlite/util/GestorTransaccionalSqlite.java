package com.example.oscar.cocina.modelo.persistencia.sqlite.util;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manana on 06/10/15.
 */
public class GestorTransaccionalSqlite implements GestorTransaccional {

    private SQLiteDatabase db;

    public GestorTransaccionalSqlite(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void beginTransaccion() {
        db.beginTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        db.setTransactionSuccessful();
    }

    @Override
    public void endTransaccion() {
         db.endTransaction();
    }
}
