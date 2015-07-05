package com.machichi.app.fcttest.map;

/**
 * Created by Elias on 05/06/2015.
 */

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.machichi.app.fcttest.R;

public class PageMapFragment extends SupportMapFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GoogleMap map=getMap();

        if (savedInstanceState == null) {
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044,
                            -73.98180484771729));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

            map.moveCamera(center);
            map.animateCamera(zoom);
        }

        addMarker(map, 40.748963847316034, -73.96807193756104, R.string.un,
                R.string.deux);

        addMarker(map, 40.76866299974387, -73.98268461227417,
                R.string.un,
                R.string.deux);
        addMarker(map, 40.765136435316755, -73.97989511489868,
                R.string.un,
                R.string.deux);
        addMarker(map, 40.70686417491799, -74.01572942733765,
                R.string.un,
                R.string.deux);

                /*addMarker(map, 40.76866299974387, -73.98268461227417,
                R.string.lincoln_center, R.string.lincoln_center_snippet);
        addMarker(map, 40.765136435316755, -73.97989511489868,
                R.string.carnegie_hall, R.string.practice_x3);
        addMarker(map, 40.70686417491799, -74.01572942733765,
                R.string.downtown_club, R.string.heisman_trophy);*/
    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           int title, int snippet) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(getString(title))
                .snippet(getString(snippet)));
    }



}