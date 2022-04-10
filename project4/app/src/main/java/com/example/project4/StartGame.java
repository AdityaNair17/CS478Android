package com.example.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.util.Random;

public class StartGame extends AppCompatActivity {

    Random random;
    public static final int UPDATE_TABLE_ONE_VIEW = 1;
    public static final int UPDATE_TABLE_TWO_VIEW = 2;
    public static final int SUCCESS = 1;
    public static final int NEAR_MISS = 2;
    public static final int CLOSE_MISS = 3;
    public static final int COMPLETE_MISS = 4;
    public static final int GAME_OVER = 0;
    public static final int PLAYER_ONE_WINS = 1;
    public static final int PLAYER_TWO_WINS = 2;
    public static final int STOPPED = 3;
    private int gopher_row;
    private int gopher_col;
    private LinearLayout mainLayout;
    protected PlayerThread player1;
    protected PlayerThread player2;

    private Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            int guess_number = msg.arg1;
//            Log.i("UI HANDLER","Guess = "+guess_number);
            int guess_loc = msg.arg2;
            int guess_row = guess_loc / 10;
            int guess_col = guess_loc % 10;
            switch (what) {
                case UPDATE_TABLE_ONE_VIEW:
                    try { player1.sleep(1500); }
                    catch (InterruptedException e) { System.out.println("Thread interrupted!") ; }
                    int accuracy = guessAccuracy(guess_row,guess_col);
                    if(accuracy == SUCCESS){
                        stop(PLAYER_ONE_WINS);
                    } else {
                        updateTextView("1", guess_row, guess_col, String.valueOf(guess_number));
                        Log.i("SEND MESSAGE","table = 1");
                        player1.addMessage(accuracy, 1);
                    }
                    break;
                case UPDATE_TABLE_TWO_VIEW:
                    try { player2.sleep(1500); }
                    catch (InterruptedException e) { System.out.println("Thread interrupted!") ; }
                    int accuracyForTableTwo = guessAccuracy(guess_row, guess_col);
                    if(accuracyForTableTwo == SUCCESS){
                        stop(PLAYER_TWO_WINS);
                    } else {
                        updateTextView("2", guess_row, guess_col, String.valueOf(guess_number));
                        Log.i("SEND MESSAGE","table = 2");
                        player2.addMessage(accuracyForTableTwo, 2);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mainLayout = findViewById(R.id.mainLayout);
        mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        TableLayout firstPlayer = new TableLayout(this);
        createTable(firstPlayer,"1");


        TableLayout secondPlayer = new TableLayout(this);
        createTable(secondPlayer, "2");

        mainLayout.addView(playerNameTextView("First Player"));
        mainLayout.addView(firstPlayer);
        mainLayout.addView(playerNameTextView("Second Player"));
        mainLayout.addView(secondPlayer);

        Button stopButton = new Button(this);
        stopButton.setText("Stop");
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop(STOPPED);
            }
        });
        stopButton.setGravity(Gravity.CENTER_HORIZONTAL);
        mainLayout.addView(stopButton);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) stopButton.getLayoutParams();
        params.width = 500;
        stopButton.setLayoutParams(params);

        random = new Random();
        gopher_row = random.nextInt(10);
        gopher_col = random.nextInt(10);
        updateTextView("1", gopher_row, gopher_col, "G");
        updateTextView("2", gopher_row, gopher_col, "G");

        player1 = new PlayerThread("Player 1", uiHandler);
        player2 = new PlayerThread("Player 2", uiHandler);
        player1.start();
        player2.start();

        makeFirstGuess();
    }

    private void createTable(TableLayout table, String tab){
        for(int row = 0; row < 10; row++){
            TableRow trow = new TableRow(this);
            for(int col = 0; col < 10; col++){
                TextView tView = new TextView(this);
                int id = Integer.parseInt(tab+row+col);
                tView.setTag(id);
//                tView.setText("1");
                tView.setTextSize(20);
                tView.setPadding(5,5,5,5);
                tView.setMinimumWidth(100);
                trow.addView(tView);
            }
            trow.setMinimumHeight(50);
            trow.setGravity(Gravity.CENTER_HORIZONTAL);
            table.addView(trow);
        }
    }

    private TextView playerNameTextView(String text){
        TextView txtView = new TextView(this);
        txtView.setText(text);
        txtView.setTextSize(25);
        txtView.setGravity(Gravity.CENTER_HORIZONTAL);
        txtView.setPadding(10,10,10,10);
        txtView.setTypeface(null, Typeface.BOLD);
        txtView.setBackgroundResource(R.drawable.border);
        return txtView;
    }

    private void updateTextView(String table, int row, int col, String text){
        String tag = table + row + col;
        Log.i("UPDATE GAME","tag = "+tag);
        TextView tv = (TextView) mainLayout.findViewWithTag(Integer.parseInt(tag));
        tv.setText(text);
    }

    private int guessAccuracy(int guess_row, int guess_col){
        int row_dist = Math.abs(guess_row - gopher_row);
        int col_dist = Math.abs(guess_col - gopher_col);
        if(row_dist == 0 && col_dist == 0){
            Toast.makeText(this,"FOUND GOPHER", Toast.LENGTH_SHORT).show();
            return SUCCESS;
        } else if (row_dist <= 2 && col_dist <= 2){
//            Toast.makeText(this,"You are close", Toast.LENGTH_SHORT).show();
            return CLOSE_MISS;
        } else if (row_dist <= 8 && col_dist <= 8){
//            Toast.makeText(this, "You are very close", Toast.LENGTH_SHORT).show();
            return NEAR_MISS;
        } else {
//            Toast.makeText(this,"You are very far", Toast.LENGTH_SHORT).show();
            return COMPLETE_MISS;
        }
    }

    private void makeFirstGuess(){
        Log.i("FIRST GUESS","called");
        try { Thread.sleep(1500); }
        catch (InterruptedException e) { System.out.println("Thread interrupted!") ; }
        player1.addRunnable(() -> {
            int guess_row = random.nextInt(10);
            int guess_col = random.nextInt(10);
            player1.visited[guess_row][guess_col] = true;
            player1.guess_number++;
            player1.current_row = guess_row;
            player1.current_col = guess_col;
            Log.i("RUNNABLE 1","called");

            Message msg = uiHandler.obtainMessage(UPDATE_TABLE_ONE_VIEW);
            msg.arg1 = player1.guess_number;
            msg.arg2 = guess_row*10 + guess_col;
            uiHandler.sendMessage(msg);
        });

        player2.addRunnable(() -> {
            int guess_row = random.nextInt(10);
            int guess_col = random.nextInt(10);
            player2.visited[guess_row][guess_col] = true;
            player2.guess_number++;
            player2.current_col = guess_col;
            player2.current_row = guess_row;
            Log.i("RUNNABLE 2","called");

            Message msg = uiHandler.obtainMessage(UPDATE_TABLE_TWO_VIEW);
            msg.arg1 = player2.guess_number;
            msg.arg2 = guess_row*10 + guess_col;
            uiHandler.sendMessage(msg);
        });
    }

    public void stop(int status){
        player1.getLooper().quitSafely();
        player2.getLooper().quitSafely();
        int winner = 0;
        if(status == STOPPED){
           Toast.makeText(StartGame.this, "Game Stopped", Toast.LENGTH_SHORT).show();
        } else if (status == PLAYER_ONE_WINS){
            winner = 1;
            Log.i("WINNER","PLAYER One");
            Toast.makeText(StartGame.this, "Player One Wins", Toast.LENGTH_SHORT).show();
        } else {
            winner = 2;
            Log.i("WINNER","PLAYER Two");
            Toast.makeText(StartGame.this, "Player Two Wins", Toast.LENGTH_SHORT).show();
        }
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        home.putExtra("Winner",winner);
        startActivity(home);
    }



    /////////////////////// THREAD CLASS /////////////////////
    public class PlayerThread extends HandlerThread {
        private Handler uiHandler;
        private Handler playerHandler;
        private Random random;
        public int current_row, current_col = 0, current_range = 10;
        public int guess_number = 0;
        private int max_possible_row, min_possible_row, max_possible_col, min_possible_col  = -1;
        private int guess_row, guess_col;
        public boolean visited[][] = new boolean[10][10];

        public PlayerThread(String name, Handler uiHandler) {
            super(name);
            this.uiHandler = uiHandler;
            this.random = new Random();
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            Log.i("PLAYER HANDLER","CREATED");
            playerHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    int what = msg.what;
                    int table = msg.arg1;
                    Log.i("PLAYER HANDLER MESSAGE", "what = "+what);
                    switch (what){
                        case NEAR_MISS:
                            makeNextGuess(8,table);
                            break;
                        case CLOSE_MISS:
                            makeNextGuess(2, table);
                            break;
                        case COMPLETE_MISS:
                            makeNextGuess(-1, table);
                            break;
                        case GAME_OVER:
                            Log.i("GAME OVER","Start New Game");
                            break;
                    }
                }
            };
        }

        public void addMessage(int message, int table){
            Log.i("ADD MESSAGE","Table = "+table+" "+message);
            if(playerHandler != null) {
                Message msg = playerHandler.obtainMessage(message);
                msg.arg1 = table;
                playerHandler.sendMessage(msg);
            }
        }

        public void addRunnable(Runnable runnable){
            Log.i("RUNNABLE","called"+playerHandler);
            if(playerHandler != null) {
                Log.i("RUNNABLE INSIDE HANDLER","called");
                playerHandler.post(runnable);
            }
        }

        public void makeNextGuess(int range, int table){
            if(range != -1 && current_range > range){
                min_possible_col = Math.max(0, current_col - range);
                min_possible_row = Math.max(0, current_row - range);
                max_possible_col = Math.min(9, current_col + range);
                max_possible_row = Math.min(9, current_row + range);
                current_range = range;
                Log.i("ROWS", "max = "+max_possible_row + " min = "+min_possible_row + "Current = "+current_row+" "+current_col + " Range = "+range+" Table = "+table);
                Log.i("ROWS", "max = "+max_possible_col + " min = "+min_possible_col+ " Table = "+table);
            }

            do {
                current_row = guess_row = max_possible_row == -1 ? random.nextInt(10) : random.nextInt((max_possible_row - min_possible_row) + 1) + min_possible_row;
                current_col = guess_col = max_possible_col == -1 ? random.nextInt(10) : random.nextInt((max_possible_col - min_possible_col) + 1) + min_possible_col;
            } while(visited[guess_row][guess_col]);

//            current_row = guess_row = max_possible_row == -1 ? random.nextInt(10) : random.nextInt((max_possible_row - min_possible_row) + 1) + min_possible_row;
//            current_col = guess_col = max_possible_col == -1 ? random.nextInt(10) : random.nextInt((max_possible_col - min_possible_col) + 1) + min_possible_col;

            visited[guess_row][guess_col] = true;
            guess_number++;
            Log.i("GUESSED","TABLE = "+table+" ROW = "+guess_row+" "+guess_col);
            Message msg = uiHandler.obtainMessage(table);
            msg.arg1 = guess_number;
            msg.arg2 = guess_row * 10 + guess_col;
            uiHandler.sendMessage(msg);
        }

    }
}