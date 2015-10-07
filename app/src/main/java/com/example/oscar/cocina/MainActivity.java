package com.example.oscar.cocina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oscar.cocina.controlador.AddRecetaActivity;
import com.example.oscar.cocina.controlador.ViewRecetaActivity;
import com.example.oscar.cocina.modelo.entidades.Receta;
import com.example.oscar.cocina.modelo.servicio.Servicio;
import com.example.oscar.cocina.vista.RecetaAdapter;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends Activity {


    private CocinaApplication context;

    private static final int REQUEST_CODE_ADD_RECETA = 1;
    private ListView listadoRecetas;
    private RecetaAdapter recetaAdapter;
    private Button btCambiarActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listadoRecetas = (ListView) findViewById(R.id.lvRecetas);

        context = (CocinaApplication) getApplicationContext();

        Servicio servicio = context.getServicio();

        List<Receta> recetas = servicio.getRecetas();
        int layout = R.layout.recetas_list_item;
        recetaAdapter = new RecetaAdapter(recetas, this, layout);
        listadoRecetas.setAdapter(recetaAdapter);
        if( recetas.size() < 1 ){

            Toast.makeText(this, "No se han encontrado recetas", Toast.LENGTH_SHORT).show();

        }


        //Registramos el listado de ignredientes para que aparezca el menu contextual
        registerForContextMenu(listadoRecetas);

        //Si hacemos click sobre la receta la visualizamos
        listadoRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(MainActivity.this, ViewRecetaActivity.class);

                Receta recetaSelected = (Receta) recetaAdapter.getItem(position);
                intent.putExtra("receta", recetaSelected);

                startActivity(intent);

            }
        });

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
            intent.putExtra("receta", ((Serializable)null));
            startActivityForResult(intent, REQUEST_CODE_ADD_RECETA);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if( v.getId() == R.id.lvRecetas ){

            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

            Receta receta = (Receta) recetaAdapter.getItem(position);

            menu.setHeaderTitle(receta.getNombre());

            getMenuInflater().inflate(R.menu.menu_receta_context, menu);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;

        switch (item.getItemId()) {
            case R.id.action_receta_edicion:

                Intent intent = new Intent(MainActivity.this, AddRecetaActivity.class);

                Receta recetaSelected = (Receta) recetaAdapter.getItem(position);
                intent.putExtra("receta", recetaSelected);

                startActivityForResult(intent, REQUEST_CODE_ADD_RECETA);


                return true;
            case R.id.action_receta_borrar:

                Receta receta = (Receta) recetaAdapter.getItem(position);
                context.getServicio().removeReceta(receta);


                recetaAdapter.addAll(context.getServicio().getRecetas());
                recetaAdapter.notifyDataSetChanged();

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_ADD_RECETA) {

            //El resultado es de add receta
            if (resultCode == RESULT_OK) {

                String resultado = data.getStringExtra(AddRecetaActivity.RESULTADO_ADD_RECETA);



                recetaAdapter.addAll(context.getServicio().getRecetas());
                recetaAdapter.notifyDataSetChanged();
            }

        }
    }
}
