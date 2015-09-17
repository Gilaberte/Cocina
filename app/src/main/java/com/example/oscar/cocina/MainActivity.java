package com.example.oscar.cocina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.servicio.Servicio;

import java.util.List;

public class MainActivity extends Activity {

    ListView listado;
    Button btCambiarActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listado = (ListView) findViewById(R.id.lvRecetas);

        CocinaApplication context = (CocinaApplication) getApplicationContext();

        Servicio servicio = context.getServicio();

        List<Receta> recetas = servicio.getRecetas();

        if( recetas.size() < 1 ){

            Toast.makeText(this, "No se han encontrado recetas", Toast.LENGTH_SHORT).show();

        }else{

            int layout = R.layout.recetas_list_item;
            RecetaAdapter recetaAdapter = new RecetaAdapter(recetas, this, layout);
            listado.setAdapter(recetaAdapter);
        }







        //ONCLICK PARA PASAR A OTRO ACTIVITY
        /*this.btCambiarActivity = (Button) findViewById(R.id.btCambiarPantalla);

        this.btCambiarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), SecondaryActivity.class);

                intent.putExtra("dato", "DATO PASADO DE LA PRIMERA ACTIVITY A ESTA");

                startActivity(intent);


            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_receta) {
            Intent intent = new Intent(MainActivity.this, AddRecetaActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }




}
