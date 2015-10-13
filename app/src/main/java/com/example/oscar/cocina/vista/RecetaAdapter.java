package com.example.oscar.cocina.vista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oscar.cocina.R;
import com.example.oscar.cocina.modelo.entidades.Receta;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 * Created by oscar on 15/09/2015.
 */
public class RecetaAdapter extends BaseAdapter {

    private List<Receta> datos;
    private Context context;
    private int layout;

    public RecetaAdapter(List<Receta> datos, Context context, int layout) {
        this.datos = datos;
        this.context = context;
        this.layout = layout;
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

            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            TextView tvDificultad = (TextView) convertView.findViewById(R.id.tvDificultad);

            ImageView ivImagenReceta = (ImageView)convertView.findViewById(R.id.imagenReceta);
            RecetaAdapterDecorator recetaAdapterDecorator = new RecetaAdapterDecorator(tvNombre, tvDificultad, ivImagenReceta);

            convertView.setTag(recetaAdapterDecorator);
        }




        Receta item = (Receta) getItem(position);

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String path = item.getNombre() + ".jpg";

        File file = new File(directory, path);

        Uri uri = Uri.fromFile(file);

        Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());

        ((RecetaAdapterDecorator) convertView.getTag()).ivImagenReceta.setImageBitmap(bitmap);

        ((RecetaAdapterDecorator) convertView.getTag()).tvNombre.setText(item.getNombre());
        //((RecetaAdapterDecorator) convertView.getTag()).tvIngredientes.setText(item.getIngredientes());
        ((RecetaAdapterDecorator) convertView.getTag()).tvDificultad.setText(item.getDificultad());
        //((RecetaAdapterDecorator) convertView.getTag()).tvPreparacion.setText(item.getPreparacion());


        return convertView;
    }

    public void addAll(List<Receta> listado){
        datos.clear();
        datos.addAll(listado);
    }

    private class RecetaAdapterDecorator{


        public TextView tvNombre;
        public TextView tvDificultad;
        public ImageView ivImagenReceta;

        public RecetaAdapterDecorator(TextView tvNombre, TextView tvDificultad, ImageView imagenReceta) {
            this.tvNombre = tvNombre;
            this.tvDificultad = tvDificultad;
            this.ivImagenReceta = imagenReceta;
        }
    }
}
