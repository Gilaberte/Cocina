package com.example.oscar.cocina.controlador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.vista.IngredienteAdapter;

import java.io.Serializable;

public class ViewRecetaActivity extends Activity {

    Receta receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receta);

        CocinaApplication context = (CocinaApplication) getApplicationContext();

        Intent intent = getIntent();
        receta = (Receta) intent.getSerializableExtra("receta");
        TextView name = (TextView) findViewById(R.id.ViewrecetaName);
        TextView dificultad = (TextView) findViewById(R.id.ViewrecetaDificultad);
        TextView preparacion = (TextView) findViewById(R.id.ViewrecetaPreparación);
        ListView ingredientes = (ListView) findViewById(R.id.lvRecetaIngredientesView);

        name.setText(receta.getNombre());
        dificultad.setText(receta.getDificultad());

        int layout = R.layout.ingredientes_list_item;
        IngredienteAdapter ingredienteAdapter = new IngredienteAdapter(context.getServicio().getIngredientesToReceta(receta.getId()), context, layout);
        ingredientes.setAdapter(ingredienteAdapter);
        preparacion.setText(receta.getPreparacion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_receta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
