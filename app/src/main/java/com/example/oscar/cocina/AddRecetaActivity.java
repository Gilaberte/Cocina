package com.example.oscar.cocina;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddRecetaActivity extends Activity {

    private Spinner spinnerAddIngredienteCantidades;
    private Spinner spinnerAddIngredienteMedidas;

    private CocinaApplication context;

    private Receta receta;

    private ListView listadoRecetaIngredientes;

    private View btAddIngrediente;

    private EditText etNombreIngrediente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        //Obtenemos contexto
        context = (CocinaApplication) getApplicationContext();

        //Referencia al listado donde almacenaremos el listado de ingredientes
        listadoRecetaIngredientes = (ListView) findViewById(R.id.lvRecetaIngredientes);

        //Creamos nuevo objeto Receta
        receta = new Receta();

        //Nombre Ingrediente
        etNombreIngrediente = (EditText) findViewById(R.id.etIngredienteName);
        
        //MEDIDAS INGREDIENTES
        spinnerAddIngredienteMedidas = (Spinner) findViewById(R.id.spinnerAddRecetaMedidas);
        ArrayAdapter medidasAdapter = context.getServicio().getMedidasIngredientes();
        spinnerAddIngredienteMedidas.setAdapter(medidasAdapter);

        //CANTIDADES INGREDIENTES
        spinnerAddIngredienteCantidades = (Spinner) findViewById(R.id.spinnerAddRecetaCantidades);
        final ArrayAdapter cantidadesAdapter = context.getServicio().getCantidadesIngredientes();
        spinnerAddIngredienteCantidades.setAdapter(cantidadesAdapter);

        btAddIngrediente = findViewById(R.id.btAddIngrediente);
        btAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String etNombreIngredienteText = etNombreIngrediente.getText().toString();

                String itemIngredienteCantidad = spinnerAddIngredienteCantidades.getSelectedItem().toString();
                String itemIngredienteMedida = spinnerAddIngredienteMedidas.getSelectedItem().toString();


                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setNombre(etNombreIngredienteText);
                ingrediente.setCantidad(itemIngredienteCantidad);
                ingrediente.setMedida(itemIngredienteMedida);

                receta.setIngrediente(ingrediente);

                ArrayList<Ingrediente> ingredientes = (ArrayList<Ingrediente>) receta.getIngredientes();

                int layout = R.layout.ingredientes_list_item;
                IngredienteAdapter ingredienteAdapter = new IngredienteAdapter(ingredientes, context, layout);
                listadoRecetaIngredientes.setAdapter(ingredienteAdapter);
                //Toast.makeText(context, itemIngredienteCantidad, Toast.LENGTH_LONG).show();


            }
        });



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
