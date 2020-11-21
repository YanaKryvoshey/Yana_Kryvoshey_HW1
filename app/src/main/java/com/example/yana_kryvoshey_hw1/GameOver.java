package com.example.yana_kryvoshey_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String SCORE = "SCORE";
    private TextView gameover_LBL_winner;
    private ImageView gameover_img_winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        gameover_LBL_winner = findViewById(R.id.gameover_LBL_winner);
        gameover_img_winner = findViewById(R.id.gameover_img_winner);

        Bundle bundle = getIntent().getExtras();
        String winner = bundle.getString(NAME);
         int score = getIntent().getIntExtra(SCORE, -1);

        if (score > 0){
            if(winner.equals("princess")){
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.princess));

            }else{
                gameover_LBL_winner.setText(winner + " with " + "" + score + " points");
                gameover_img_winner.setImageDrawable(getDrawable(R.drawable.unicorn));
            }
        }
        if (score == 0){
            gameover_LBL_winner.setText(winner);
        }

    }
}