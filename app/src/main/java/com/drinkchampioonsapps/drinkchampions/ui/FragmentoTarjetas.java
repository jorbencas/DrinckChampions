package com.drinkchampioonsapps.drinkchampions.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.adapters.DBAdapter;
import com.drinkchampioonsapps.drinkchampions.modelo.Comidas;
import com.drinkchampioonsapps.drinkchampions.modelo.ToDoItem;

/**
 * Fragmento para la pestaña "TARJETAS" de la sección "Mi Cuenta"
 */
public class FragmentoTarjetas extends Fragment {


    private DBAdapter mDbHelper;
    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;
    private static final int DELETE_ID = Menu.FIRST + 1;

    public FragmentoTarjetas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        mDbHelper = new DBAdapter(getContext());
        mDbHelper.open();

        Comidas.getlikes().clear();

        Cursor all = mDbHelper.selecall();

        if (all.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                String item =  all.getString(2) + "";
                String description = all.getString(3);
                String username=  "likes";
                float vol = Float.parseFloat(all.getString(4));
                int image = Integer.parseInt(all.getString(5));
                String imagedir = all.getString(6);
                float rating = 5;
                int liked = 0;
                String url = all.getString(7);
                String geo = all.getString(8);
                String panoid = "";

                ToDoItem beer = new ToDoItem(item, username, description,  vol,  image,  imagedir,  rating,  liked,  url, geo, panoid);

                Comidas.getlikes().add(beer);


            } while(all.moveToNext());
        }


        adaptador = new AdaptadorCategorias(getContext(),Comidas.getlikes());

        reciclador.setAdapter(adaptador);

        return view;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deleteTodo(info.id);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }
}
