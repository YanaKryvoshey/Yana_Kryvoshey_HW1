package com.example.yana_kryvoshey_hw1.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MenuActivity extends AppCompatActivity {
    private Button main_LBL_start;
    private Button main_LBL_records;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        main_LBL_start = findViewById(R.id.main_LBL_start);
        main_LBL_records = findViewById(R.id.main_LBL_records);

        main_LBL_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity(MenuActivity.this);
            }
        });

        main_LBL_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecords(MenuActivity.this);
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLsatLocation();


    }
// ask from permission to get the place
    private void fetchLsatLocation() {
        if (ActivityCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MenuActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){

        }else{
            ActivityCompat.requestPermissions(MenuActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }

    }
//print text if didnt give permission to take the place
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0]+grantResults[1] == PackageManager.PERMISSION_GRANTED)){

        }else {
            Toast.makeText(getApplicationContext(),"Premission denied.",Toast.LENGTH_SHORT).show();
        }
    }


    private void showRecords(Activity activity) {
        Intent myIntent1 = new Intent(activity, RecordActivity.class);
        startActivity(myIntent1);
    }

    private void startGameActivity(Activity activity) {
        Intent myIntent = new Intent(activity, GameActivity.class);
        startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "MenuActivityonStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "MenuActivityonResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "MenuActivityonPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "MenuActivityonStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "MenuActivityonDestroy");
        super.onDestroy();
    }
}