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

        int layout = R.layout.recetas_list_item;

        RecetaAdapter recetaAdapter = new RecetaAdapter(recetas, this, layout);
        listado.setAdapter(recetaAdapter);


        //ONCLICK PARA PASAR A OTRO ACTIVITY
        this.btCambiarActivity = (Button) findViewById(R.id.btCambiarPantalla);

        this.btCambiarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), SecondaryActivity.class);

                intent.putExtra("dato", "DATO PASADO DE LA PRIMERA ACTIVITY A ESTA");

                startActivity(intent);


            }
        });


    }


}
