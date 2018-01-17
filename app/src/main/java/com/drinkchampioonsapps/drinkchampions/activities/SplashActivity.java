package com.drinkchampioonsapps.drinkchampions.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.drinkchampioonsapps.drinkchampions.adapters.DBOfflineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.drinkchampioonsapps.drinkchampions.R;
import com.drinkchampioonsapps.drinkchampions.modelo.Comidas;
import com.drinkchampioonsapps.drinkchampions.modelo.ToDoItem;
import com.drinkchampioonsapps.drinkchampions.ui.ActividadPrincipal;


public class SplashActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    private final int ANIMATION_DURATION = 3000;
    private RelativeLayout relativeLayout;
    private Animation animation;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    private DBOfflineAdapter mDbHelper;
    private Long mRowId;
    public static boolean offline=false;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.splash);

        mDbHelper = new DBOfflineAdapter(this);
        mDbHelper.open();

        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        animation = AnimationUtils.loadAnimation(this, R.anim.up_translation);
        animation.setDuration(10);

        relativeLayout.startAnimation(animation);


        if (isOnlineNet()==true) {

            //Borramos los datos anteriormente guardados.
            mDbHelper.deletedb();

            offline=false;

            databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (Comidas.getPrincipales().isEmpty()) {
                        Iterable<DataSnapshot> references = dataSnapshot.getChildren();
                        while (references.iterator().hasNext()) {
                            DataSnapshot nextDataReference = references.iterator().next();
                            ToDoItem beer = nextDataReference.getValue(ToDoItem.class);

                            switch (beer.getPositions()) {

                                case 1:
                                    Comidas.getPrincipales().add(beer);
                                    break;
                                case 3:
                                    Comidas.getBEBIDAS().add(beer);
                                    break;
                                case 4:
                                    Comidas.getPOSTRES().add(beer);
                                    break;
                            }




                        //Descarga offline

                        String category = String.valueOf(beer.getPositions());
                        String summary = beer.getItem();
                        String description = beer.getDescription();
                        String number = (beer.getPrecio()+"");
                        String imagelocal = Integer.toString(beer.getImage());
                        String imagedir = beer.getImagedir();
                        String moreinfo = beer.getUrl();
                        String locate = beer.getGeo();

                        mDbHelper.createTodo(category, summary, description, number, imagelocal, imagedir, moreinfo, locate);

                        }
                    }
                }

                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                    Log.e("***oooo***", "No hay internet!!!");
                }
            });
        } else {

            offline=true;

            Cursor all = mDbHelper.selecall();

            if (all.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {

                    String item =  all.getString(2);
                    String description = all.getString(3);
                    String username=  "offline";
                    float vol = Float.parseFloat(all.getString(4));
                    int image = Integer.parseInt(all.getString(5));
                    String imagedir = all.getString(6);
                    float rating = 5;
                    int positions = Integer.parseInt(all.getString(1));
                    String url = all.getString(7);
                    String geo = all.getString(8);
                   String panoid = "";

                    ToDoItem beer = new ToDoItem(item, username, description,  vol,  image,  imagedir,  rating,  positions,  url, geo, panoid);


                    switch (beer.getPositions()) {

                        case 1:
                            Comidas.getPrincipales().add(beer);
                            break;
                        case 3:
                            Comidas.getBEBIDAS().add(beer);
                            break;
                        case 4:
                            Comidas.getPOSTRES().add(beer);
                            break;
                    }

                } while(all.moveToNext());
            }


            Log.e("***oooo***", "No hay internet!!!");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, ActividadPrincipal.class));
                SplashActivity.this.finish();
            }
        }, ANIMATION_DURATION);

    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}

