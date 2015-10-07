package com.example.oscar.cocina.controlador;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.oscar.cocina.CocinaApplication;
import com.example.oscar.cocina.vista.IngredienteAdapter;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

import java.util.List;

/**
 * Created by manana on 24/09/15.
 */
public class EditIngredienteFragment extends DialogFragment {

    private Ingrediente ingrediente;
    private IngredienteAdapter ingredienteAdapter;

    private ArrayAdapter medidasIngredienteAdapter;

    private EditText etEditIngredienteUnidad;
    private Spinner spinnerAddIngredienteMedidas;
    private EditText etNombreIngrediente;

    private AlertDialog.Builder builder;

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setIngredienteAdapter(IngredienteAdapter ingredienteAdapter) {
        this.ingredienteAdapter = ingredienteAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.ingrediente_edit_fragment, null);

        builder.setView(view);

        List<String> medidasLista = ((CocinaApplication) getActivity().getApplicationContext()).getServicio().getMedidas();
        medidasIngredienteAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, medidasLista);

        spinnerAddIngredienteMedidas = (Spinner) view.findViewById(R.id.spinnerAddRecetaMedidasEdit);
        spinnerAddIngredienteMedidas.setAdapter(medidasIngredienteAdapter);


        etEditIngredienteUnidad = (EditText) view.findViewById(R.id.etEditIngredienteUnidad);
        etNombreIngrediente = (EditText) view.findViewById(R.id.etIngredienteNameEdit);

        //Seteamos valores por defecto
        etNombreIngrediente.setText(ingrediente.getNombre());
        etEditIngredienteUnidad.setText(ingrediente.getUnidad());
        spinnerAddIngredienteMedidas.setSelection(medidasLista.indexOf(ingrediente.getMedida()));



        builder.setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(ingrediente.getNombre())
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        ingrediente.setNombre(etNombreIngrediente.getText().toString());
                        ingrediente.setUnidad(etEditIngredienteUnidad.getText().toString());
                        ingrediente.setMedida(spinnerAddIngredienteMedidas.getSelectedItem().toString());
                        ingredienteAdapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.create();
    }

}
