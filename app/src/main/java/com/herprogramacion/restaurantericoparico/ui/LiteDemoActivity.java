/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.herprogramacion.restaurantericoparico.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.herprogramacion.restaurantericoparico.R;

/**
 * This demo shows some features supported in lite mode.
 * In particular it demonstrates the use of {@link com.google.android.gms.maps.model.Marker}s to
 * launch the Google Maps Mobile application, {@link com.google.android.gms.maps.CameraUpdate}s
 * and {@link com.google.android.gms.maps.model.Polygon}s.
 */
public class LiteDemoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final LatLng Tarragona = new LatLng(41.1188827, 1.2444909);
    private static final LatLng Barcelona = new LatLng(41.3850639, 2.1734035);
    private static final LatLng Madrid = new LatLng(40.4167754, -3.7037902);
    private static final LatLng Oviedo = new LatLng(43.39203860000001, -5.8121272);
    private static final LatLng Benicasim = new LatLng(40.055385, 0.0641504);
    private static final LatLng Cruzcampo = new LatLng(37.7825953, -3.8256733);
    private static final LatLng heineken = new LatLng(39.472974, -0.513492);

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lite_demo);

        // Get the map and register for the ready callback
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Move the camera to show all of Australia.
     * Construct a {@link com.google.android.gms.maps.model.LatLngBounds} from markers positions,
     * then move the camera.
     */
    public void showAustralia(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }
        // Create bounds that include all locations of the map
        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder()
                .include(Oviedo)
                .include(Barcelona)
                .include(Madrid)
                .include(Benicasim)
                .include(Tarragona)
                .include(Cruzcampo)
                .include(heineken);
        // Move camera to show all markers and locations
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));
    }

    /**
     * Called when the map is ready to add all markers and objects to the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers();


        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    showAustralia(null);
                }
            });
        }
    }



    /**
     * Add Markers with default info windows to the map.
     */
    private void addMarkers() {
        mMap.addMarker(new MarkerOptions()
                .position(Oviedo)
                .title("Fernando Alonso"));

        mMap.addMarker(new MarkerOptions()
                .position(Benicasim)
                .title("Roberto Merhi")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(Madrid)
                .title("Carlos Sainz Jr")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(Barcelona)
                .title("Jaime Alguersuari")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(Tarragona)
                .title("Pedro de la Rosa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions()
                .position(Cruzcampo)
                .title("Fabrica de Cruzcampo")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(heineken)
                .title("Fabrica de Heineken en España")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));





    }
}
