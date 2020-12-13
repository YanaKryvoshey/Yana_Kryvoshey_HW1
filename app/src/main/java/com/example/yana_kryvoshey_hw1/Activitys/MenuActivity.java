package com.example.yana_kryvoshey_hw1.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yana_kryvoshey_hw1.MySp;
import com.example.yana_kryvoshey_hw1.R;

public class MenuActivity extends AppCompatActivity {
    private Button main_LBL_start;
    private Button main_LBL_records;



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