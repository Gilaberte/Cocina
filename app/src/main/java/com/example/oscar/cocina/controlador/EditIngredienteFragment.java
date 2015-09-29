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

import com.example.oscar.cocina.vista.IngredienteAdapter;
import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

/**
 * Created by manana on 24/09/15.
 */
public class EditIngredienteFragment extends DialogFragment {

    private Ingrediente ingrediente;
    private IngredienteAdapter ingredienteAdapter;

    private ArrayAdapter medidasIngredienteAdapter;
    private ArrayAdapter cantidadesIngredienteAdapter;

    private Spinner spinnerAddIngredienteCantidades;
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

        medidasIngredienteAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.receta_medidas));
        cantidadesIngredienteAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.receta_cantidades));


        spinnerAddIngredienteMedidas = (Spinner) view.findViewById(R.id.spinnerAddRecetaMedidasEdit);
        spinnerAddIngredienteMedidas.setAdapter(medidasIngredienteAdapter);


        spinnerAddIngredienteCantidades = (Spinner) view.findViewById(R.id.spinnerAddRecetaCantidadesEdit);
        spinnerAddIngredienteCantidades.setAdapter(cantidadesIngredienteAdapter);

        etNombreIngrediente = (EditText) view.findViewById(R.id.etIngredienteNameEdit);
        etNombreIngrediente.setText(ingrediente.getNombre());


        builder.setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(ingrediente.getNombre())
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        ingrediente.setNombre(etNombreIngrediente.getText().toString());
                        ingrediente.setCantidad(spinnerAddIngredienteCantidades.getSelectedItem().toString());
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
