package com.example.oscar.cocina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;

public class AddRecetaActivity extends Activity {

    private Spinner spinnerAddRecetaCantidades;

    private CocinaApplication context;

    private View btAddIngrediente;

    private EditText etNombreIngrediente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);

        context = (CocinaApplication) getApplicationContext();

        btAddIngrediente = findViewById(R.id.btAddIngrediente);

        etNombreIngrediente = (EditText) findViewById(R.id.etIngredienteName);

        btAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        //INGREDIENTES
        spinnerAddRecetaCantidades = (Spinner) findViewById(R.id.spinnerAddRecetaMedidas);

        context = (CocinaApplication) getApplicationContext();

        ArrayAdapter medidasAdapter = context.getServicio().getMedidasIngredientes();

        spinnerAddRecetaCantidades.setAdapter(medidasAdapter);



    }

    private void startActivityForResult(Intent intent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_receta, menu);
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
