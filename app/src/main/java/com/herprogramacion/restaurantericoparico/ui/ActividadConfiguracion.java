package com.herprogramacion.restaurantericoparico.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;
import com.herprogramacion.restaurantericoparico.R;




public class ActividadConfiguracion extends AppCompatActivity {
    public static String year;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_configuracion);

        agregarToolbar();

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu_lang, android.R.layout.simple_spinner_item);
        spinner=(Spinner) findViewById(R.id.year);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (spinner.getSelectedItem().toString().equals("2008")){
                    year = "2008";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2009")){
                    year = "2009";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2010")){
                    year = "2010";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2011")){
                    year = "2011";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2012")){
                    year = "2012";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2013")){
                    year = "2013";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2014")){
                    year = "2014";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2015")){
                    year = "2015";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2016")){
                    year = "2016";
                    guardarPreferencias();
                }else if (spinner.getSelectedItem().toString().equals("2017")){
                    year = "2017";
                    guardarPreferencias();
                }

            }
//            public void restartActivity(Activity Ajustes){
//
//                Intent intent=new Intent();
//                intent.setClass(Ajustes, Ajustes.getClass());
//                Ajustes.startActivity(intent);
//                Ajustes.finish();
//            }
//
//            public  void reiniciar_layout(){
//                Intent intent = new Intent(ActividadConfiguracion.this, ActividadConfiguracion.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//
//            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }
        });
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void guardarPreferencias(){
        SharedPreferences prefs = getSharedPreferences("menu_lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("preferenciasGuardadas", true);
        editor.putString("year", this.year);
        editor.commit();
        Toast.makeText(this, "guardando preferencias", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,  this.year, Toast.LENGTH_SHORT).show();
    }
}