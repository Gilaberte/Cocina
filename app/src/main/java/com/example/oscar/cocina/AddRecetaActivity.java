package com.example.oscar.cocina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.util.ArrayList;

public class AddRecetaActivity extends Activity {

    public static final String RESULTADO_ADD_RECETA = "resultadoAddReceta";
    private Spinner spinnerAddIngredienteCantidades;
    private Spinner spinnerAddIngredienteMedidas;

    private ArrayAdapter medidasIngredienteAdapter;
    private ArrayAdapter cantidadesIngredienteAdapter;

    private CocinaApplication context;

    private Receta receta;

    private EditText etNombreReceta;

    private EditText etNombreIngrediente;

    private ListView listadoRecetaIngredientes;

    private View btAddIngrediente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        //Obtenemos contexto
        context = (CocinaApplication) getApplicationContext();

        //Iniciamos adapters
        medidasIngredienteAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.receta_medidas));
        cantidadesIngredienteAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.receta_cantidades));

        //Referencia al listadoRecetas donde almacenaremos el listadoRecetas de ingredientes
        listadoRecetaIngredientes = (ListView) findViewById(R.id.lvRecetaIngredientes);

        //Creamos nuevo objeto Receta
        receta = new Receta();

        //Nombre Receta
        etNombreReceta = (EditText) findViewById(R.id.etRecetaName);

        //Nombre Ingrediente
        etNombreIngrediente = (EditText) findViewById(R.id.etIngredienteName);
        
        //MEDIDAS INGREDIENTES
        spinnerAddIngredienteMedidas = (Spinner) findViewById(R.id.spinnerAddRecetaMedidas);
        spinnerAddIngredienteMedidas.setAdapter(medidasIngredienteAdapter);

        //CANTIDADES INGREDIENTES
        spinnerAddIngredienteCantidades = (Spinner) findViewById(R.id.spinnerAddRecetaCantidades);
        spinnerAddIngredienteCantidades.setAdapter(cantidadesIngredienteAdapter);


        //Obtenemos ingredientes y los listamos
        ArrayList<Ingrediente> ingredientes = (ArrayList<Ingrediente>) receta.getIngredientes();
        int layout = R.layout.ingredientes_list_item;
        IngredienteAdapter ingredienteAdapter = new IngredienteAdapter(ingredientes, context, layout);
        listadoRecetaIngredientes.setAdapter(ingredienteAdapter);

        //Si hacemos click a añadir ingrediente...
        btAddIngrediente = findViewById(R.id.btAddIngrediente);
        btAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtenemos datos del nuevo ingrediente
                String etNombreIngredienteText = etNombreIngrediente.getText().toString();
                String itemIngredienteCantidad = spinnerAddIngredienteCantidades.getSelectedItem().toString();
                String itemIngredienteMedida = spinnerAddIngredienteMedidas.getSelectedItem().toString();

                //Guardamos el Ingrediente en un nuevo bojeto ingrediente
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setNombre(etNombreIngredienteText);
                ingrediente.setCantidad(itemIngredienteCantidad);
                ingrediente.setMedida(itemIngredienteMedida);

                //Añadimos ese nuevo objeto ingrediente a nuestro objeto receta.
                receta.addIngrediente(ingrediente);

                //Notificamos al adapter que actualice su contenido en el listadoRecetas de ingredientes
                IngredienteAdapter adapterTmp = (IngredienteAdapter) listadoRecetaIngredientes.getAdapter();
                adapterTmp.notifyDataSetChanged();
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
        if (id == R.id.guardar_receta) {

            receta.setNombre(etNombreReceta.getText().toString());
            receta.setDificultad("FACIL");
            receta.setPreparacion("asi asa");
            context.getServicio().addReceta(receta);

            Intent intent = new Intent();

            intent.putExtra(RESULTADO_ADD_RECETA, "ESTE ES TU RESULTADO");

            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
