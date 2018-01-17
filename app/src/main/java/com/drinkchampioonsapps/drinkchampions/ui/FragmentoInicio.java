package com.drinkchampioonsapps.drinkchampions.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.activities.SplashActivity;

/**
 * Fragmento para la sección de "Inicio"
 */
public class FragmentoInicio extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    private TextView offlinetext;

    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        offlinetext = (TextView) view.findViewById(R.id.textViewoffline);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        if (SplashActivity.offline) {
            offlinetext.setVisibility(View.VISIBLE);
        } else {
            offlinetext.setVisibility(View.GONE);
        }

        adaptador = new AdaptadorInicio(getContext());
        reciclador.setAdapter(adaptador);
        return view;
    }

}
