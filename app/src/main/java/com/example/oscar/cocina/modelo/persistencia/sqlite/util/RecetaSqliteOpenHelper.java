package com.example.oscar.cocina.modelo.persistencia.sqlite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.oscar.cocina.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by manana on 30/09/15.
 */
public class RecetaSqliteOpenHelper extends SQLiteOpenHelper{

    private static final String TABLE_MEDIDAS = "Medidas";
    private Context context;

    public RecetaSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        ejecutarScript(sqLiteDatabase, context.getResources().getStringArray(R.array.scriptCreacion));


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public static void ejecutarScript(SQLiteDatabase db, String[] script) {
        try {
            db.beginTransaction();

            for (String sql : script) {
                db.execSQL(sql);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
