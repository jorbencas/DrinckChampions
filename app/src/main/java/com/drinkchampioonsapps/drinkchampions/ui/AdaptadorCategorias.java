package com.drinkchampioonsapps.drinkchampions.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.modelo.ToDoItem;

import java.util.List;
import java.util.Objects;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> implements ItemClickListener {
    private final Context context;
    private final List<ToDoItem> items;
    private ImageView ivBookCover;

    public AdaptadorCategorias(Context context,List<ToDoItem> items) {
        this.items = items;
        this.context = context;
    }
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v,this);
    }

    public void onItemClick(View view, int position) {
        // Imagen a compartir entre transiciones
        View sharedImage = view.findViewById(R.id.image);
        DetailActivity.launch((Activity) context, position, sharedImage ,items);
    }




    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ToDoItem item = items.get(i);
        if (Objects.equals(item.getImagedir(), "nd") || (Objects.equals(item.getImagedir(), ""))) {

            Glide.with(context)
                    .load(item.getImage())
                    .centerCrop()
                    .into(viewHolder.imagen);
        } else {
            Glide.with(viewHolder.itemView.getContext()).load(Uri.parse(item.getImagedir())).centerCrop().error(R.drawable.ic_nocover).into(viewHolder.getimagen());
        }

        viewHolder.nombre.setText(item.getItem());
        viewHolder.precio.setText(item.getPrecio()+"vol");

    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public ItemClickListener listener;

        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
            v.setOnClickListener(this);
            this.listener = listener;
        }
        public ImageView getimagen() { return imagen; }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }


}
interface ItemClickListener {
    void onItemClick(View view, int position);
}
