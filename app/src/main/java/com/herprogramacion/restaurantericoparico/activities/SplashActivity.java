package com.herprogramacion.restaurantericoparico.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.ValueEventListener;
import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.modelo.Comida;
import com.herprogramacion.restaurantericoparico.modelo.Comidas;
import com.herprogramacion.restaurantericoparico.modelo.ToDoItem;
import com.herprogramacion.restaurantericoparico.ui.ActividadPrincipal;

import java.util.Collections;


public class SplashActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    private final int ANIMATION_DURATION = 3000;
    private RelativeLayout relativeLayout;
    private Animation animation;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.splash);

        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        animation = AnimationUtils.loadAnimation(this, R.anim.up_translation);
        animation.setDuration(400);

        relativeLayout.startAnimation(animation);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (Comidas.getPrincipales().isEmpty()) {
                    Iterable<DataSnapshot> references = dataSnapshot.getChildren();
                    while (references.iterator().hasNext()) {
                        DataSnapshot nextDataReference = references.iterator().next();
                        ToDoItem beer = nextDataReference.getValue(ToDoItem.class);
                        //toast1 = Toast.makeText(getApplicationContext(), site.toString(), Toast.LENGTH_LONG);
                        //toast1.show();
                        //toast1 = Toast.makeText(getApplicationContext(), site.getEvent().toString(), Toast.LENGTH_SHORT);
                        //toast1.show();

                        //long myLong = site.getEvent().getDate();
                        //String myString = Long.toString(myLong);
                        //toast1 = Toast.makeText(getApplicationContext(), myString, Toast.LENGTH_SHORT);
                        //toast1.show();

                        //if (site.getEvent().getDate() > System.currentTimeMillis() && checkSiteType(site.getType())) {

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

                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

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
}
