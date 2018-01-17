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
import com.drinkchampioonsapps.drinkchampions.modelo.Comidas;
import com.drinkchampioonsapps.drinkchampions.modelo.ToDoItem;

import java.util.List;
import java.util.Objects;

import static com.drinkchampioonsapps.drinkchampions.R.*;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> implements ItemClickListener {
    private final Context context;
    private final List<ToDoItem> items = Comidas.Principales;
    private ImageView ivBookCover;

    public AdaptadorInicio(Context context) {
        this.context = context;
    }

    public int getItemCount() {
        return items.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onItemClick(View view, int position) {
        View sharedImage = view.findViewById(id.image);
        DetailActivity.launch((Activity) context, position, sharedImage, items);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public ItemClickListener listener;

        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(id.nombre_comida);
            precio = (TextView) v.findViewById(id.precio_comida);
            imagen = (ImageView) v.findViewById(id.miniatura_comida);
            v.setOnClickListener(this);
            this.listener = listener;
        }

        public ImageView getimagen() { return imagen; }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        ToDoItem item = Comidas.getPrincipales().get(i);

        if (Objects.equals(item.getImagedir(), "nd") || (Objects.equals(item.getImagedir(), ""))) {

            Glide.with(viewHolder.itemView.getContext())
                    .load(item.getImage())
                    .centerCrop()
                    .into(viewHolder.imagen);

        } else {
            Glide.with(viewHolder.itemView.getContext()).load(Uri.parse(item.getImagedir())).centerCrop().error(R.drawable.ic_nocover).into(viewHolder.getimagen());
        }
        viewHolder.nombre.setText(item.getItem());
        viewHolder.precio.setText(item.getPrecio() + " Vol");

    }
}

