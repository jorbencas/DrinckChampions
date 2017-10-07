package com.herprogramacion.restaurantericoparico.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.activities.BookListActivity;
import com.herprogramacion.restaurantericoparico.modelo.ToDoItem;

import java.util.HashMap;
import java.util.Map;


public class crudfilebase extends AppCompatActivity {
    private static final String EXTRA_POSITION = "com.herprogramacion.cursospoint.extra.POSITION";
    private TextView ivname;

    private Button btnupdate;

    private EditText tvname;
    private EditText tvdesc;
    private EditText tvvol;
    private EditText tvima;
    private EditText tvimadir;
    private EditText tvpun;
    private EditText tvpos;
    private EditText tvgeo;
    private EditText tvurl;
    private EditText tvpanoid;

    private String copylink;
    private String name;
    private String alias;
    private String date;
    private String nacio;
    private String image;

    private int position;

    private Context context;
    private static ToDoItem currentItem2;
    private static DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crudfirebase);

        //setToolbar();
        // Fetch views
        ivname = (TextView) findViewById(R.id.textView3);

        tvname = (EditText) findViewById(R.id.editnom);
        tvdesc = (EditText) findViewById(R.id.editdes);
        tvvol= (EditText) findViewById(R.id.editvol);
        tvima = (EditText) findViewById(R.id.editima);
        tvimadir = (EditText) findViewById(R.id.editimadir);
        tvpun = (EditText) findViewById(R.id.editpun);
        tvpos = (EditText) findViewById(R.id.editpos);
        tvgeo = (EditText) findViewById(R.id.editgeo);
        tvurl = (EditText) findViewById(R.id.editurl);
        tvpanoid = (EditText) findViewById(R.id.editpanoid);

        btnupdate = (Button) findViewById(R.id.btnsave);


        ivname.setText(currentItem2.getItem());

        tvname.setText(currentItem2.getItem());
        tvdesc.setText(currentItem2.getDescription());
        tvvol.setText(String.valueOf(currentItem2.getPrecio()));
        tvima.setText (String.valueOf(currentItem2.getImage()));
        tvimadir.setText (currentItem2.getImagedir());
        tvpun.setText(String.valueOf(currentItem2.getRating()));
        tvpos.setText(String.valueOf(currentItem2.getPositions()));
        tvgeo.setText(currentItem2.getGeo());
        tvurl.setText(currentItem2.getUrl());
        tvpanoid.setText(currentItem2.getPanoid());

        //Map<String, Object> updates = new HashMap<String, Object>();
        //updates.put("item", "Ole!");

    }

    public void update(final View v){

        currentItem2.setItem(tvname.getText().toString());
        currentItem2.setDescription(tvdesc.getText().toString());
        currentItem2.setVol(Float.parseFloat(tvvol.getText().toString()));
        currentItem2.setImage(Integer.parseInt(tvima.getText().toString()));
        currentItem2.setImagedir(tvimadir.getText().toString());
        currentItem2.setRating(Float.parseFloat(tvpun.getText().toString()));
        currentItem2.setPositions(Integer.parseInt(tvpos.getText().toString()));
        currentItem2.setUrl(tvurl.getText().toString());
        currentItem2.setGeo(tvgeo.getText().toString());
        currentItem2.setPanoid(tvpanoid.getText().toString());

        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("item", tvname.getText().toString());
        updates.put("description", tvdesc.getText().toString());
        updates.put("vol",Float.parseFloat(tvvol.getText().toString()));
        updates.put("image", Integer.parseInt(tvima.getText().toString()));
        updates.put("imagedir",tvimadir.getText().toString());
        updates.put("rating", Float.parseFloat(tvpun.getText().toString()));
        updates.put("positions", Integer.parseInt(tvpos.getText().toString()));
        updates.put("url", tvurl.getText().toString());
        updates.put("geo", tvgeo.getText().toString());
        updates.put("panoid", tvpanoid.getText().toString());
        //reference.updateChildren(updates);
        reference2.
                updateChildren(updates, new DatabaseReference.CompletionListener(){
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if(error == null)
                            Toast.makeText(v.getContext(), "Update OK", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(v.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

       finish();
    }

    /*
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)// Habilitar Up Button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    */
    // Populate data for the book



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.returnlist) {
            Intent intent = new Intent(this, BookListActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launch(Activity context, ToDoItem currentItem, DatabaseReference reference) {

        currentItem2=currentItem;
        reference2=reference;
        Intent intent = new Intent(context, crudfilebase.class);
        //intent.putExtra(EXTRA_POSITION, position);
        context.startActivity(intent);
    }

}