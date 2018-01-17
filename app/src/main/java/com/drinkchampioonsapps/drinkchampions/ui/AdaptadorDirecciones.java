package com.drinkchampioonsapps.drinkchampions.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drinkchampioonsapps.drinkchampions.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorDirecciones
        extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView direccion;
        public TextView departamento;
        public TextView ciudad;
        public TextView telefono;

        public ViewHolder(View v) {
            super(v);
            direccion = (TextView) v.findViewById(R.id.texto_direccion);
            departamento = (TextView) v.findViewById(R.id.texto_departamento);
            ciudad = (TextView) v.findViewById(R.id.texto_ciudad);
            telefono = (TextView) v.findViewById(R.id.texto_telefono);
        }
    }


    public AdaptadorDirecciones() {
    }

    @Override
    public int getItemCount() {
        return Direccion.DIRECCIONES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_direccion, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Direccion item = Direccion.DIRECCIONES.get(i);
        viewHolder.direccion.setText(item.categoria);
        viewHolder.departamento.setText(item.descripcion);
        viewHolder.ciudad.setText("Codigo para canjear: "+item.numeroDesceunto);
        viewHolder.telefono.setText(""+item.Fecha);
    }

    /**
     * Modelo de datos para probar el adaptador
     */
    public static class Direccion {
        public int numeroDesceunto;
        public String categoria;
        public String descripcion;
        public String Fecha;

        public Direccion(int numeroDesceunto, String categoria,
                         String descripcion, String Fecha) {
            this.numeroDesceunto = numeroDesceunto;
            this.categoria = categoria;
            this.descripcion = descripcion;
            this.Fecha = Fecha;
        }





        public final static List<Direccion> DIRECCIONES = new ArrayList<Direccion>();
        public static List<Direccion> getDIRECCIONES() {
            return DIRECCIONES;
        }

        /*
        static {
            DIRECCIONES.add(new Direccion(123653, "¡Descuento!", "50% de descuento en: ", "04-05-17"));
            DIRECCIONES.add(new Direccion(123653, "¡Descuento!", "25% de descuento en:", "04-05-17"));
            DIRECCIONES.add(new Direccion(123653, "¡Descuento!", "Prueba Gratis", "04-05-17"));
        }
        */
    }


}