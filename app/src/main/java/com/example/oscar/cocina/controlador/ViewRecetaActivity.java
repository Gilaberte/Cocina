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
import com.example.oscar.cocina.MainActivity;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.vista.IngredienteAdapter;

import java.io.Serializable;

public class ViewRecetaActivity extends Activity {

    private static final int REQUEST_CODE_EDIT_RECETA = 1 ;
    Receta receta;
    CocinaApplication context;
    IngredienteAdapter ingredienteAdapter;
    TextView name;
    TextView dificultad;
    TextView preparacion;
    ListView ingredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receta);

        context = (CocinaApplication) getApplicationContext();

        Intent intent = getIntent();
        receta = (Receta) intent.getSerializableExtra("receta");
        name = (TextView) findViewById(R.id.ViewrecetaName);
        dificultad = (TextView) findViewById(R.id.ViewrecetaDificultad);
        preparacion = (TextView) findViewById(R.id.ViewrecetaPreparación);
        ingredientes = (ListView) findViewById(R.id.lvRecetaIngredientesView);

        name.setText(receta.getNombre());
        dificultad.setText(receta.getDificultad());

        int layout = R.layout.ingredientes_list_item;
        ingredienteAdapter = new IngredienteAdapter(context.getServicio().getIngredientesToReceta(receta.getId()), context, layout);
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
        if (id == R.id.action_receta_borrar_view) {


            context.getServicio().removeReceta(receta);


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }else if( id == R.id.action_receta_edicion_view ){

            Intent intent = new Intent(this, AddRecetaActivity.class);
            intent.putExtra("receta", receta);

            startActivityForResult(intent, REQUEST_CODE_EDIT_RECETA);

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT_RECETA) {


            if (resultCode == RESULT_OK) {

                receta = (Receta) data.getSerializableExtra("receta");
                name.setText(receta.getNombre());
                dificultad.setText(receta.getDificultad());
                preparacion.setText(receta.getPreparacion());
                ingredienteAdapter.addAll(context.getServicio().getIngredientesToReceta(receta.getId()));
                ingredienteAdapter.notifyDataSetChanged();
            }

        }
    }
}
