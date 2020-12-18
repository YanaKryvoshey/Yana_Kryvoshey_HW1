package com.example.yana_kryvoshey_hw1.Activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.MySpShareInformation;
import com.example.yana_kryvoshey_hw1.Objects.Record;
import com.example.yana_kryvoshey_hw1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;

public class GameOverActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String SCORE = "SCORE";
    private TextView gameover_LBL_winner;
    private ImageView gameover_img_winner;
    ArrayList<Record> allRecords = new ArrayList<Record>();
    MySp mySP;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    //private static final int REQUEST_CODE = 101;


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
        fetchLsatLocation();

        checkIfTheScoreIsRecord(winner, score);

    }

    //ask for permission to get location
    private void fetchLsatLocation() {
        if (ActivityCompat.checkSelfPermission(GameOverActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GameOverActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(GameOverActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }

    }
    //print text if didnt give permission to take the place
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0]+grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else {
            Toast.makeText(getApplicationContext(),"Premission denied.",Toast.LENGTH_SHORT).show();
        }
    }
//get the current location
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE) ;
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Criteria criteria = new Criteria();
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null) {
                currentLocation = location;
            }
         }else {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


    //if the new score is record i save it,
    // if the player didn't give me hes location i put default location
    private void checkIfTheScoreIsRecord(String winner, int score) {
        allRecords = MySpShareInformation.getAllTheRecords(this); //get all the records from the ShareInformation
        if (score > allRecords.get(9).getScore()) {
            allRecords.remove(9);
            if (currentLocation != null) {
                allRecords.add(new Record(winner, score, currentLocation.getLatitude(), currentLocation.getLongitude()));

            } else {
                allRecords.add(new Record(winner, score, 0, 0));

            }
            Collections.sort(allRecords);
            MySpShareInformation.saveTheRecords(allRecords, this); //save all the records to the ShareInformation
        }

    }


    //show the winner and make victory sound
    private void showTheWinner(String winner, int score) {
        if (score > 0) {
            if (winner.equals("princess")) {
                //if princess is the winner
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.princess));

            } else {
                //if princess is the winner
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.unicorn));
            }
            MediaPlayer mpGameOver = MediaPlayer.create(this, R.raw.winner_sound);
            mpGameOver.start();
        }
        //NO one is winner
        else if (score == 0) {
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