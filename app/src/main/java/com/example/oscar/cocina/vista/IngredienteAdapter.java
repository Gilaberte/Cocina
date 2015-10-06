package com.example.oscar.cocina.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Ingrediente;

import java.util.List;

/**
 * Created by oscar on 21/09/2015.
 */
public class IngredienteAdapter extends BaseAdapter {

    private List<Ingrediente> datos;
    private Context context;
    private int layout;

    public IngredienteAdapter(List<Ingrediente> datos, Context context, int layout) {
        this.layout = layout;
        this.datos = datos;
        this.context = context;
    }


    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);

            TextView tvNombreIngrediente = (TextView) convertView.findViewById(R.id.tvNombreIngrediente);
            TextView tvUnidadIngrediente = (TextView) convertView.findViewById(R.id.tvUnidadIngrediente);
            TextView tvMedidaIngrediente = (TextView) convertView.findViewById(R.id.tvMedidaIngrediente);


            IngredienteAdapterDecorator ingredienteAdapterDecorator = new IngredienteAdapterDecorator(tvNombreIngrediente, tvUnidadIngrediente, tvMedidaIngrediente);
            convertView.setTag(ingredienteAdapterDecorator);
        }

        Ingrediente item = (Ingrediente) getItem(position);

        ((IngredienteAdapterDecorator) convertView.getTag()).tvNombreIngrediente.setText(item.getNombre());
        ((IngredienteAdapterDecorator) convertView.getTag()).tvUnidadIngrediente.setText(item.getUnidad());
        ((IngredienteAdapterDecorator) convertView.getTag()).tvMedidaIngrediente.setText(item.getMedida());


        return convertView;
    }


        public void addAll(List<Ingrediente> listado){
        datos.clear();
        datos.addAll(listado);
    }

    private class IngredienteAdapterDecorator{


        public TextView tvNombreIngrediente;
        public TextView tvUnidadIngrediente;
        public TextView tvMedidaIngrediente;

        public IngredienteAdapterDecorator(TextView tvNombreIngrediente, TextView tvCantidadIngrediente, TextView tvMedidaIngrediente) {
            this.tvNombreIngrediente = tvNombreIngrediente;
            this.tvUnidadIngrediente = tvCantidadIngrediente;
            this.tvMedidaIngrediente = tvMedidaIngrediente;
        }
    }
}
