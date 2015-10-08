package com.example.oscar.cocina.controlador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.vista.IngredienteAdapter;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRecetaActivity extends Activity {

    public static final String RESULTADO_ADD_RECETA = "resultadoAddReceta";
    private Spinner spinnerAddIngredienteMedidas;
    private Spinner spinnerRecetaDificultad;

    private EditText etRecetaPreparacion;
    private EditText etNombreReceta;
    private AutoCompleteTextView etNombreIngrediente;
    private EditText etIngredienteUnidad;
    private ListView listadoRecetaIngredientes;
    private View btAddIngrediente;

    private CocinaApplication context;
    private Receta receta;


    private IngredienteAdapter ingredienteAdapter;
    private ArrayAdapter dificultadRecetaAdapter;
    private ArrayAdapter medidasIngredienteAdapter;
    private ArrayAdapter<String> nombreIngredienteAdapter;

    //Si es null estamos añadiendo, si no editando
    private Serializable recetaSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        //Obtenemos contexto
        context = (CocinaApplication) getApplicationContext();




        //Iniciamos adapters
        dificultadRecetaAdapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, getResources().getStringArray(R.array.receta_dificultad));
        medidasIngredienteAdapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, context.getServicio().getMedidas());

        ArrayList<Ingrediente> ingredientesList = context.getServicio().getIngredientes();
        String[] ingredientes = new String[ingredientesList.size()];
        //ingredientes = ingredientesList.toArray(ingredientes);

        int tmp = 0;
        for(Ingrediente s : ingredientesList) {
            ingredientes[tmp] = s.getNombre();
            tmp += 1;
        }

        nombreIngredienteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ingredientes);

        //Referencia al listadoRecetas donde almacenaremos el listadoRecetas de ingredientes
        listadoRecetaIngredientes = (ListView) findViewById(R.id.lvRecetaIngredientes);

        //Nombre Receta
        etNombreReceta = (EditText) findViewById(R.id.etRecetaName);

        //Nombre Ingrediente
        etNombreIngrediente = (AutoCompleteTextView) findViewById(R.id.etIngredienteName);
        etNombreIngrediente.setAdapter(nombreIngredienteAdapter);


        //UnidadIngrediente
        etIngredienteUnidad = (EditText) findViewById(R.id.etAddIngredienteUnidad);

        //Preparacion
        etRecetaPreparacion = (EditText) findViewById(R.id.et_receta_preparacion);

        //DIFICULTAD
        spinnerRecetaDificultad = (Spinner) findViewById(R.id.spinnerAddRecetaDificultad);
        spinnerRecetaDificultad.setAdapter(dificultadRecetaAdapter);

        //MEDIDAS INGREDIENTES
        spinnerAddIngredienteMedidas = (Spinner) findViewById(R.id.spinnerAddRecetaMedidas);
        spinnerAddIngredienteMedidas.setAdapter(medidasIngredienteAdapter);

        Intent intent = getIntent();
        recetaSelected = intent.getSerializableExtra("receta");

        if( recetaSelected != null ){

            this.receta = (Receta) recetaSelected;
            //TODO TENGO QUE OBTENER LOS INGREDIENTES
            this.receta.setIngredientes(context.getServicio().getIngredientesToReceta(this.receta.getId()));
            setCurrentReceta();

        }else{
            //Creamos nuevo objeto Receta NO CAMBIAR DE SITIO PORQUE DA ERROR AL INTENTAR OBTENER LOS INGREDIENTES
            this.receta = new Receta();
        }


        //Obtenemos ingredientes y los listamos
        int layout = R.layout.ingredientes_list_item;
        ingredienteAdapter = new IngredienteAdapter(this.receta.getIngredientes(), context, layout);
        listadoRecetaIngredientes.setAdapter(ingredienteAdapter);

        //Registramos el listado de ignredientes para que aparezca el menu contextual
        registerForContextMenu(listadoRecetaIngredientes);

        //Si hacemos click a añadir ingrediente...

        btAddIngrediente = findViewById(R.id.btAddIngrediente);
        btAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtenemos datos del nuevo ingrediente
                String etNombreIngredienteText = etNombreIngrediente.getText().toString();
                String etIngredienteUnidadText = etIngredienteUnidad.getText().toString();
                String itemIngredienteMedida = spinnerAddIngredienteMedidas.getSelectedItem().toString();

                Boolean ingredienteDuplicado = false;
                for ( Ingrediente ingrediente: receta.getIngredientes() ) {
                    if(ingrediente.getNombre().toString().equals(etNombreIngredienteText)){
                        ingredienteDuplicado = true;
                    }
                }

                if(etNombreIngredienteText.length() == 0){
                    Toast.makeText(context, "No puedes añadir ingredientes sin nombre", Toast.LENGTH_LONG).show();

                }else if(ingredienteDuplicado){

                    Toast.makeText(context, "No puedes duplicar ingredientes", Toast.LENGTH_LONG).show();
                }else{
                    //Guardamos el Ingrediente en un nuevo objeto ingrediente
                    Ingrediente ingrediente = new Ingrediente();
                    ingrediente.setNombre(etNombreIngredienteText);
                    ingrediente.setUnidad(etIngredienteUnidadText);
                    ingrediente.setMedida(itemIngredienteMedida);

                    //Añadimos ese nuevo objeto ingrediente a nuestro objeto receta.
                    AddRecetaActivity.this.receta.addIngrediente(ingrediente);

                    //Notificamos al adapter que actualice su contenido en el listadoRecetas de ingredientes
                    ingredienteAdapter.notifyDataSetChanged();

                    etNombreIngrediente.setText("");
                    etIngredienteUnidad.setText("");
                    spinnerAddIngredienteMedidas.setSelection(0);

                }



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



            if(etNombreReceta.getText().toString().trim().length() == 0) {

                Toast.makeText(context, "Debes poner un nombre", Toast.LENGTH_LONG).show();


            }else if(etRecetaPreparacion.getText().toString().trim().length() == 0){

                Toast.makeText(context, "Debes poner la preparación", Toast.LENGTH_LONG).show();

            }else{
                receta.setNombre(etNombreReceta.getText().toString());
                receta.setDificultad(spinnerRecetaDificultad.getSelectedItem().toString());
                receta.setPreparacion(etRecetaPreparacion.getText().toString());

                if( this.receta.getId() == -1 ){

                    context.getServicio().addReceta(receta);
                }else{
                    context.getServicio().updateReceta(receta);

                }

                Intent intent = new Intent();
                intent.putExtra("receta", receta);
                setResult(RESULT_OK, intent);
                finish();
            }



        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if( v.getId() == R.id.lvRecetaIngredientes ){

            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

            Ingrediente item = receta.getIngredienteById(position);

            menu.setHeaderTitle(item.getNombre());

            getMenuInflater().inflate(R.menu.menu_ingrediente_context, menu);

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;

        switch (item.getItemId()) {
            case R.id.action_ingrediente_edicion:

                EditIngredienteFragment editIngredienteFragment = new EditIngredienteFragment();
                editIngredienteFragment.setIngrediente(receta.getIngredienteById(position));
                editIngredienteFragment.setIngredienteAdapter(ingredienteAdapter);
                editIngredienteFragment.show(getFragmentManager(), "EditIngredienteFragment");


                return true;
            case R.id.action_ingrediente_borrar:

                receta.removeIngrediente(position);

                ingredienteAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void setCurrentReceta(){


        etNombreReceta.setText(receta.getNombre());
        String[] dificultad = getResources().getStringArray(R.array.receta_dificultad);
        String valueDificultad = receta.getDificultad();


        etRecetaPreparacion.setText(receta.getPreparacion());

        spinnerRecetaDificultad.setSelection(Arrays.binarySearch(dificultad, valueDificultad));
        dificultadRecetaAdapter.notifyDataSetChanged();



    }
}
