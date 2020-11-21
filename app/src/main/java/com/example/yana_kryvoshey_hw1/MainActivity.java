package com.example.yana_kryvoshey_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.UFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView main_BTM_play;
    private TextView main_LBL_princess;
    private TextView main_LBL_unicorn;
    private ImageView image_main_princess_card;
    private ImageView image_main_unicorn_card;
    ArrayList<Card> allcards = new ArrayList<Card>();
    private final Random randomindex = new Random();
    private int unicornScore = 0, princessScore = 0,loopCountr =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_BTM_play = findViewById(R.id.main_BTM_play);
        main_LBL_princess = findViewById(R.id.main_LBL_princess);
        main_LBL_unicorn = findViewById(R.id.main_LBL_unicorn);
        image_main_princess_card = findViewById(R.id.image_main_princess_card);
        image_main_unicorn_card = findViewById(R.id.image_main_unicorn_card);
        unicornScore = 0;
        princessScore = 0;
        allcards = makeCardsArrayList(allcards);


        main_BTM_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Card> copyCards = new ArrayList<Card>();
                copyCards = allcards;



                    startGame(copyCards);



            }
        });

    }


    private void startGame(ArrayList<Card> copyCards)  {

    loopCountr ++;
    int index = randomindex.nextInt(copyCards.size());
    showCard(index, copyCards, image_main_princess_card);
    int princessPoint = getPoint(index, copyCards);
    copyCards.remove(index);
    index = randomindex.nextInt(copyCards.size());
    showCard(index, copyCards, image_main_unicorn_card);
    int unicornPoint = getPoint(index, copyCards);
    setScore(princessPoint, unicornPoint);
    copyCards.remove(index);


if (loopCountr == 27){
    openSecondActivity(MainActivity.this);
}


    }
    private void openSecondActivity(Activity activity) {

        Intent myIntent = new Intent(activity, GameOver.class);
        if(princessScore > unicornScore){
            myIntent.putExtra(GameOver.NAME,"princess");
            myIntent.putExtra(GameOver.SCORE, princessScore);
        }else if(princessScore < unicornScore){
            myIntent.putExtra(GameOver.NAME,"unicorn");
            myIntent.putExtra(GameOver.SCORE, unicornScore);
        }else {
            myIntent.putExtra(GameOver.NAME,"No one");
            myIntent.putExtra(GameOver.SCORE, "" + 0);
        }
        startActivity(myIntent);
        finish();
    }

    private void setScore(int princessPoint, int unicornPoint) {

                if (princessPoint > unicornPoint) {
                    princessScore = princessScore + princessPoint;
                    main_LBL_princess.setText("" + princessScore);
                } else if (princessPoint < unicornPoint) {
                    unicornScore = unicornScore + unicornPoint;
                    main_LBL_unicorn.setText("" + unicornScore);
                }

    }


    private int getPoint(int index, ArrayList<Card> copyCards) {
        Card playrCard = copyCards.get(index);
        int point = playrCard.getCardpoint();
        return point;
    }

    private void showCard(int index, ArrayList<Card> copyCards, ImageView imageShow) {

        Card playrCard = copyCards.get(index);
        String playrHold = "" + playrCard.getName();
        Resources res = getResources();
        int id = res.getIdentifier(playrHold, "drawable", getPackageName());
        imageShow.setImageResource(id);


    }


    private int getResourcesID(String princeHold, String drawable, Context applicationContext) {
        int ID = applicationContext.getResources().getIdentifier(princeHold, drawable, applicationContext.getApplicationInfo().packageName);
        return ID;
    }

    private ArrayList<Card> makeCardsArrayList(ArrayList<Card> allcards) {
        for (int i = 1; i < 14; i++) {
            allcards.add(new Card('a' + "" + i));
        }
        for (int i = 1; i < 14; i++) {
            allcards.add(new Card('b' + "" + i));
        }
        for (int i = 1; i < 14; i++) {
            allcards.add(new Card('c' + "" + i));
        }
        for (int i = 1; i < 14; i++) {
            allcards.add(new Card('d' + "" + i));
        }
        allcards.add(new Card('e' + "" + 14));
        allcards.add(new Card('f' + "" + 14));
        return allcards;
    }


    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}