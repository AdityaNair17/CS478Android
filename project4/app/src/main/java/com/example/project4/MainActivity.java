package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        homeLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView gopher = new TextView(this);
        gopher.setText("Gopher Detection");
        gopher.setGravity(Gravity.CENTER_HORIZONTAL);
        gopher.setTextSize(40);
        gopher.setPadding(25,25,25,25);
        gopher.setTypeface(null, Typeface.BOLD);
        homeLayout.addView(gopher);

        Intent intents = getIntent();
        if(intents != null){
            int lastGameWinner = intents.getIntExtra("Winner",0);
            String winnerName = lastGameWinner == 0 ? " " : lastGameWinner == 1 ? "Player 1 Won!!!" : "Player 2 Won!!!";
            TextView winner = new TextView(this);
            winner.setText(winnerName);
            winner.setGravity(Gravity.CENTER_HORIZONTAL);
            winner.setTextSize(25);
            winner.setPadding(30,30,30,30);
            winner.setTypeface(null, Typeface.BOLD);
            homeLayout.addView(winner);

        }

        Button startButton = new Button(this);
        startButton.setText("Start Game");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(getApplicationContext(), StartGame.class);
                startActivity(gameIntent);
            }
        });
        homeLayout.addView(startButton);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) startButton.getLayoutParams();
        params.width = 500;
        startButton.setLayoutParams(params);
    }
}