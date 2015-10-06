package com.example.oscar.cocina;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.oscar.cocina.modelo.negocio.Negocio;
import com.example.oscar.cocina.modelo.negocio.NegocioImpl;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoIngredienteImpl;
import com.example.oscar.cocina.modelo.persistencia.sqlite.DaoRecetaImpl;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.GestorTransaccional;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.GestorTransaccionalSqlite;
import com.example.oscar.cocina.modelo.persistencia.sqlite.util.RecetaSqliteOpenHelper;
import com.example.oscar.cocina.modelo.servicio.Servicio;
import com.example.oscar.cocina.modelo.servicio.ServicioImpl;

/**
 * Created by oscar on 15/09/2015.
 */
public class CocinaApplication extends Application {

    private Servicio servicio;

    @Override
    public void onCreate() {
        super.onCreate();

        RecetaSqliteOpenHelper sqliteOpenHelper = new RecetaSqliteOpenHelper(this, "Recetadb", null, 1);

        SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
        GestorTransaccional gestorTransaccional = new GestorTransaccionalSqlite(db);


        DaoRecetaImpl daoReceta = new DaoRecetaImpl(this, db);
        DaoIngredienteImpl daoIngrediente = new DaoIngredienteImpl(this, db);
        Negocio negocio = new NegocioImpl(daoReceta, daoIngrediente, gestorTransaccional);
        this.servicio = new ServicioImpl(negocio);
    }

    public Servicio getServicio() {
        return servicio;
    }
}
