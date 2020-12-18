package com.example.yana_kryvoshey_hw1.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.Objects.Record;


import com.example.yana_kryvoshey_hw1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

public class Fragment_Map extends Fragment  {


    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);
// print on the map the location of top ten
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                MySp.init(this);
                for (int i=0; i<10;i++){
                    double latitude = MySp.getInstance().getlatitude(MySp.KEYS.TOP_TEN_LATITUDE + i,-1);
                    double longituds = MySp.getInstance().getlongituds(MySp.KEYS.TOP_TEN_LONGITUDS + i,-1);
                Log.d("pttt", "Fragment map activity  TOP_TEN " + i + " latitude: "+ latitude +" longituds: "+ longituds);

                LatLng latLng = new LatLng(latitude,longituds);
                    map.addMarker(new MarkerOptions().position(latLng));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }

            }
        });
        return view;
    }

}
