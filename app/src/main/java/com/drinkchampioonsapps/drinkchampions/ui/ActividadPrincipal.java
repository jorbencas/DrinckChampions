package com.drinkchampioonsapps.drinkchampions.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.activities.BookListActivity;
import com.drinkchampioonsapps.drinkchampions.activities.ChooserActivity;
import com.drinkchampioonsapps.drinkchampions.activities.LoginActivity;
import com.drinkchampioonsapps.drinkchampions.activities.MainActivity;
import com.drinkchampioonsapps.drinkchampions.activities.TareasSQLiteActivity;

import java.util.Locale;


public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.actividad_principal);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoCuenta();
                break;
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoCategorias();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
            case R.id.item_search:
                startActivity(new Intent(this, BookListActivity.class));
                break;
            case R.id.item_location:
                startActivity(new Intent(this, LiteDemoActivity.class));
                break;
            case R.id.item_favorito:
                startActivity(new Intent(this, TareasSQLiteActivity.class));
                break;
            case R.id.item_firebase:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.social_networks:
                startActivity(new Intent(this, ChooserActivity.class));
                break;

            case R.id.item_registro:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.en:
                change_lang("en");
                break;
            case R.id.es:
                change_lang("es");
                break;
            case R.id.va:
                change_lang("va");
                break;


        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void change_lang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        //String lang_default = Locale.getDefault().getLanguage();
        //Toast toast1 = Toast.makeText(getApplicationContext(), lang_default, Toast.LENGTH_SHORT);
        //toast1.show();

        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
        //getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        finish();
        Intent refresh = new Intent(ActividadPrincipal.this, ActividadPrincipal.class);
        startActivity(refresh);
    }
}
