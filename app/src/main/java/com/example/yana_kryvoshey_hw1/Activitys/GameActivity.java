package com.example.yana_kryvoshey_hw1.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yana_kryvoshey_hw1.Objects.Card;
import com.example.yana_kryvoshey_hw1.R;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final int DELAY = 2000;
    private ImageView main_BTM_play;
    private TextView main_LBL_princess;
    private TextView main_LBL_unicorn;
    private ImageView image_main_princess_card;
    private ImageView image_main_unicorn_card;
    ArrayList<Card> allcards = new ArrayList<Card>();
    ArrayList<Card> copyCards = new ArrayList<Card>();
    private final Random randomindex = new Random();
    private int unicornScore = 0, princessScore = 0, loopCountr = 0;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handlerBar = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        main_BTM_play = findViewById(R.id.main_BTM_play);
        main_LBL_princess = findViewById(R.id.main_LBL_princess);
        main_LBL_unicorn = findViewById(R.id.main_LBL_unicorn);
        image_main_princess_card = findViewById(R.id.image_main_princess_card);
        image_main_unicorn_card = findViewById(R.id.image_main_unicorn_card);

        allcards = makeCardsArrayList(allcards);
        copyCards = allcards;

        main_BTM_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();             // switch cards
                startProgressBar();  // game bar progress
            }
        });



    }

    //game bar progress
    private void startProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 28) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handlerBar.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

//delaye between switch cards
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(runnable, 1000);

            startGame();
        }
    };
    private void start() {
        handler.postDelayed(runnable, 1000);
    }
    private void stop() {
        handler.removeCallbacks(runnable);
    }


    private void startGame() {

            if (copyCards.size() == 0){
                stop();
                openSecondActivity(GameActivity.this);
            }else {
                MediaPlayer mp = MediaPlayer.create(this, R.raw.cards_shuffling);
                mp.start();
                Card princessCard = copyCards.get(randomindex.nextInt(copyCards.size()));
                copyCards.remove(princessCard);
                Card unicornCard = copyCards.get(randomindex.nextInt(copyCards.size()));
                copyCards.remove(unicornCard);
                showCard(princessCard, unicornCard, image_main_princess_card, image_main_unicorn_card);
                int princessPoint = princessCard.getCardpoint();
                int unicornPoint = unicornCard.getCardpoint();
                setScore(princessPoint, unicornPoint);

            };
    }


    //show the card on the screem
    private void showCard(Card princessCard, Card unicornCard, ImageView image_main_princess_card, ImageView image_main_unicorn_card) {
        String princessHold = "" + princessCard.getName();
        String unicornHold = "" + unicornCard.getName();
        Resources res = getResources();
        int princessCardId = res.getIdentifier(princessHold, "drawable", getPackageName());
        int unicornCardId = res.getIdentifier(unicornHold, "drawable", getPackageName());
        image_main_princess_card.setImageResource(princessCardId);
        image_main_unicorn_card.setImageResource(unicornCardId);
    }

    // finish the first activity and transfer the winner to the second activity
    private void openSecondActivity(Activity activity) {

        Intent myIntent = new Intent(activity, GameOverActivity.class);
        if (princessScore > unicornScore) {
            myIntent.putExtra(GameOverActivity.NAME, "princess");
            myIntent.putExtra(GameOverActivity.SCORE, princessScore);
        } else if (princessScore < unicornScore) {
            myIntent.putExtra(GameOverActivity.NAME, "unicorn");
            myIntent.putExtra(GameOverActivity.SCORE, unicornScore);
        } else {
            myIntent.putExtra(GameOverActivity.NAME, "No one");
            myIntent.putExtra(GameOverActivity.SCORE, "" + 0);
        }
        startActivity(myIntent);
        finish();
    }

    //updating the score to the playr that won the round
    private void setScore(int princessPoint, int unicornPoint) {

        if (princessPoint > unicornPoint) {
            princessScore = princessScore + princessPoint;
            main_LBL_princess.setText("" + princessScore);
        } else if (princessPoint < unicornPoint) {
            unicornScore = unicornScore + unicornPoint;
            main_LBL_unicorn.setText("" + unicornScore);
        }

    }


    //make arraylist from all the cards
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
        Log.d("pttt", "GameActivityonStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "GameActivityonResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "GameActivityonPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "GameActivityonStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "GameActivityonDestroy");
        super.onDestroy();
    }
}