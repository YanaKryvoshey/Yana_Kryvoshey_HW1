package com.example.yana_kryvoshey_hw1.Activitys;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;


import com.example.yana_kryvoshey_hw1.Fragments.Fragment_List;
import com.example.yana_kryvoshey_hw1.Fragments.Fragment_Map;
import com.example.yana_kryvoshey_hw1.Objects.Record;
import com.example.yana_kryvoshey_hw1.R;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity   {
    private Fragment_List fragment_list;
    private Fragment_Map fragment_Map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        fragment_list = new Fragment_List();
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_list, fragment_list).commit();

        fragment_Map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_Map,fragment_Map).commit();

    }

    @Override
    protected void onStart() {
        Log.d("pttt", "RecordActivityStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "RecordActivityResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "RecordActivityPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "RecordActivityStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "RecordActivityDestroy");
        super.onDestroy();
    }


}