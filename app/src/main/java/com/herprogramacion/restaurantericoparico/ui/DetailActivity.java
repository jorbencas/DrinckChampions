package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.activities.StreetViewActivity;
import com.herprogramacion.restaurantericoparico.adapters.DBAdapter;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;
import com.herprogramacion.restaurantericoparico.modelo.ToDoItem;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;


public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_POSITION = "com.herprogramacion.cursospoint.extra.POSITION";
    private static int position;
    private static List<ToDoItem> itemss;
    private static String urlb;
    private static String geob;
    private static String names;
    private static String panoid;
    private DatabaseReference databaseReference;
    private List<ToDoItem> items;
    private Long mRowId;
    private DBAdapter mDbHelper;
    private Spinner mCategory;
    private String siteId, siteName;

    private ImageView image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Toast alert = Toast.makeText(this,"Valor Position"+position,Toast.LENGTH_LONG);
        // alert.show();
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
        setToolbar(); // Reemplazar la action bar


        // Se obtiene la posición del item seleccionado
        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);
        // Carga los datos en la vista
        setupViews(itemss,position);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
    }private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)// Habilitar Up Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setupViews(List<ToDoItem> items, int position) {
        this.position = position;
        this.items=items;
        TextView name = (TextView) findViewById(R.id.detail_name);
        TextView description = (TextView) findViewById(R.id.detail_description);
        TextView author = (TextView) findViewById(R.id.detail_author);
        TextView price = (TextView) findViewById(R.id.detail_price);
        RatingBar rating = (RatingBar) findViewById(R.id.detail_rating);
        image = (ImageView) findViewById(R.id.detail_image);
        Button linkb = (Button) findViewById(R.id.linkb);
        Button saveb = (Button) findViewById(R.id.saveb);

        // Obtiene el curso ha detallar basado en la posición
        ToDoItem detailCourse = Comidas.getCourseByPosition(items,position);
        name.setText(detailCourse.getItem());
        description.setText(detailCourse.getDescription());
        author.setText("");
        price.setText(detailCourse.getPrecio()+" Vol.");
        rating.setRating(detailCourse.getRating());
        saveb.setText("Guardar cerveza "+detailCourse.getItem());
        urlb = detailCourse.getUrl();
        geob = detailCourse.getGeo();
        names = detailCourse.getItem();
        panoid = detailCourse.getPanoid();
        int numero =(int) Math.floor(Math.random()*(1000-2000+1)+2000);

        //Sacar fecha para el descuento
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
        int day =calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = (calendarNow.get(Calendar.MONTH)+1);
        int year = calendarNow.get(Calendar.YEAR);
        String fecha = (day+"-"+month+"-"+year);
        //Sacar descuento
        String[] users = {"50% de descuento en ", "Prueba Gratis una ", "10% de descuento en ", "Envios Gratis en un tu compra de", "25% de descuento en ", "2+1 de regalo en ", "80% de descuento en ", "Visita guiada a la fabrica de ", "40% de descuento en ","Invitacion vip a la fiesta en ibiza de "};
        int positions = (int) (Math.random() * 222) % 10;
        String descuento = users[positions];

        if (Objects.equals(detailCourse.getImagedir(), "nd") || (Objects.equals(detailCourse.getImagedir(), ""))) {

            Glide.with(this).load(detailCourse.getImage()).into(image);

        } else {
            Picasso.with(this).load(Uri.parse(detailCourse.getImagedir())).error(R.drawable.ic_nocover).into(image);
        }




        AdaptadorDirecciones.Direccion.getDIRECCIONES().add(new AdaptadorDirecciones.Direccion(12435+numero, "¡Promocion!", descuento+names, fecha));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                // Obtener intent de la actividad padre
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Comprobar si DetailActivity no se creó desde CourseActivity
                if (NavUtils.shouldUpRecreateTask(this, upIntent) || this.isTaskRoot()) {
                    // Construir de nuevo la tarea para ligar ambas actividades
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Terminar con el método correspondiente para Android 5.x
                    this.finishAfterTransition();
                    return true;
                }
                // Dejar que el sistema maneje el comportamiento del up button
                return false;
        }
    }
    public void linkb(View v){

        String link = urlb;
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);


    }

    public void geob(View v){

        Uri uri = Uri.parse(geob);
        if (URLUtil.isValidUrl(uri.toString())) {
            startActivity( new Intent(Intent.ACTION_VIEW, uri));

        }
    }

    public void street (View v){


        Intent intent = new Intent(DetailActivity.this, StreetViewActivity.class);
        intent.putExtra("panoId", String.valueOf(panoid));
        intent.putExtra("name", String.valueOf(names));
        startActivity(intent);


    }




/*
    public void saveb2(View v){

        SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
        String username = prefs.getString("username", null);

        String itemText = names;


        if (!itemText.isEmpty()) {
            ToDoItem toDoItem = new ToDoItem(itemText.trim(), username,"");
            //databaseReference.push().setValue(toDoItem);
            databaseReference.child(itemText.trim()).setValue(toDoItem,new DatabaseReference.CompletionListener(){
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error == null)
                        Toast.makeText(getApplicationContext(), "Cerveza guardada", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
*/









    public void saveb(View v){
        ToDoItem detailCourse = Comidas.getCourseByPosition(items,position);
        String category = "beer";
        String summary = detailCourse.getItem();
        String description = detailCourse.getDescription();
        String number = (detailCourse.getPrecio()+"");
        String imagelocal = Integer.toString(detailCourse.getImage());
        String imagedir = "nd";
        String moreinfo = detailCourse.getUrl();
        String locate = detailCourse.getGeo();
        if (mRowId == null) {
            long id = mDbHelper.createTodo(category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
            if (id > 0) {
                mRowId = id;
            }
            Toast.makeText(getApplicationContext(), "Cerveza Guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "La cerveza ya esta guardada", Toast.LENGTH_SHORT).show();
            mDbHelper.updateTodo(mRowId, category, summary, description, number, imagelocal, imagedir, moreinfo, locate);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void launch(Activity context, int position, View sharedView ,List<ToDoItem> items) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        itemss=items;
        context.startActivity(intent);
    }

}
