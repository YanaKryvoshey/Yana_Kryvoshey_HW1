package com.example.yana_kryvoshey_hw1.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.MySpShareInformation;
import com.example.yana_kryvoshey_hw1.R;
import com.example.yana_kryvoshey_hw1.Objects.Record;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class GameOverActivity extends AppCompatActivity
{
    public static final String NAME = "NAME";
    public static final String SCORE = "SCORE";
    private TextView gameover_LBL_winner;
    private ImageView gameover_img_winner;
    boolean locationPermissionGranted = false;
    ArrayList<Record> allRecords = new ArrayList<Record>();
    MySp mySP;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mySP = new MySp(getApplicationContext());
        gameover_LBL_winner = findViewById(R.id.gameover_LBL_winner);
        gameover_img_winner = findViewById(R.id.gameover_img_winner);
        Bundle bundle = getIntent().getExtras();
        String winner = bundle.getString(NAME);
        int score = getIntent().getIntExtra(SCORE, -1);
        showTheWinner(winner, score);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLsatLocation(winner, score);
        checkIfTheScoreIsRecord(winner, score);

         }

//ask for permission to get location
    private void fetchLsatLocation(String winner, int score) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new  String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        } Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                    currentLocation = location;
            }
        });
    }

    //if the new score is record i save it,
    // if the player didn't give me hes location i put default location
    private void checkIfTheScoreIsRecord(String winner, int score) {
        allRecords = MySpShareInformation.getAllTheRecords(this); //get all the records from the ShareInformation
            if (score > allRecords.get(9).getScore()){
                allRecords.remove(9);
                if (currentLocation == null){
                    allRecords.add(new Record(winner, score,32.0339,34.7524));
                }else {
                    allRecords.add(new Record(winner, score,currentLocation.getLatitude(),currentLocation.getLongitude()));
                }
                Collections.sort(allRecords);
                MySpShareInformation.saveTheRecords(allRecords,this); //save all the records to the ShareInformation
            }

    }

    
//show the winner and make victory sound
    private void showTheWinner(String winner, int score) {
        if (score > 0){
            if(winner.equals("princess")){
                //if princess is the winner
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.princess));

            }else{
                //if princess is the winner
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.unicorn));
            }
            MediaPlayer mpGameOver = MediaPlayer.create(this, R.raw.winner_sound);
            mpGameOver.start();
        }
        //NO one is winner
        else if (score == 0){
            gameover_LBL_winner.setText(winner);
        }
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "GameOverActivityonStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "GameOverActivityonResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "GameOverActivityonPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "GameOverActivityonStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "GameOverActivityonDestroy");
        super.onDestroy();
    }


}