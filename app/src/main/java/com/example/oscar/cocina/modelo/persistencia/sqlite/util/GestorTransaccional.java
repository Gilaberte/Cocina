package com.example.oscar.cocina.modelo.persistencia.sqlite.util;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manana on 06/10/15.
 */
public interface GestorTransaccional {

    void beginTransaccion();
    void setTransactionSuccessful();
    void endTransaccion();

}
