package com.drinkchampioonsapps.drinkchampions.activities;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.drinkchampioonsapps.drinkchampions.R;

public class StreetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    private String panoramicId, siteName;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.street_view);

        Bundle bundle = getIntent().getExtras();
        siteName = bundle.getString("name", "");
        //Toast toast1 = Toast.makeText(getApplicationContext(), siteName, Toast.LENGTH_SHORT);
        //toast1.show();




        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(siteName);
        }

        panoramicId = bundle.getString("panoId", "");
        StreetViewPanoramaFragment panoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.street_view);
        panoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        if (!panoramicId.isEmpty()) {
            streetViewPanorama.setStreetNamesEnabled(false);
            streetViewPanorama.setUserNavigationEnabled(false);
            streetViewPanorama.setPosition(panoramicId); //http://www.ekreativa.com/pano-id-extract/
        }
    }

    //////////////////////////////////ActionBar Options//////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
