package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity<counter> extends AppCompatActivity {
    boolean gameActive = true;
    public static int counter = 0;
    public static boolean flag = false;
    //Player representation:
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //state meanings:
    // 0 - X
    // 1 - O
    // 2 - Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    // this function will be called every time a players tap in an empty box of the grid
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        Button btn = (Button) findViewById(R.id.main_resetBoard_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameReset(view);
            }
        });

        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset(view);
        }
        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            counter++; // increase the counter after each tap
            if (counter == 9) // check if its the last box
                gameActive = false; //reset the game

            gameState[tappedImage] = activePlayer;
            img.setTranslationY((-1000f)); //motion effect to the image
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ((ImageView) findViewById(R.id.main_playTurn_imv)).setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ((ImageView) findViewById(R.id.main_playTurn_imv)).setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        flag = false;
        //check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[0]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = true;
                gameActive = false; // game reset function be called
                if (gameState[winPosition[0]] == 0) { //somebody has won! - find out who
                    ((ImageView) findViewById(R.id.main_result_imv)).setImageResource(R.drawable.xwin);
                } else {
                    ((ImageView) findViewById(R.id.main_result_imv)).setImageResource(R.drawable.owin);
                }

                ((ImageView) findViewById(R.id.main_playTurn_imv)).setImageResource(0);

                //draw the win line on the board
                if (winPosition[0] == 0) {
                    if (winPosition[1] == 1 && winPosition[2] == 2)
                        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark6);
                    else if (winPosition[1] == 3 && winPosition[2] == 6)
                        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark3);
                    else if (winPosition[1] == 4 && winPosition[2] == 8)
                        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark1);
                } else if (winPosition[0] == 1 && winPosition[1] == 4 && winPosition[2] == 7)
                    ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark4);
                else if (winPosition[0] == 2) {
                    if (winPosition[1] == 5 && winPosition[2] == 8)
                        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark5);
                    else if (winPosition[1] == 4 && winPosition[2] == 6)
                        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark2);
                } else if (winPosition[0] == 3 && winPosition[1] == 4 && winPosition[2] == 5)
                    ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark7);
                else if (winPosition[0] == 6 && winPosition[1] == 7 && winPosition[2] == 8)
                    ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(R.drawable.mark8);
            }
        }

        // set the status if the match draw
        if (counter == 9 && !flag) {
            ((ImageView) findViewById(R.id.main_result_imv)).setImageResource(R.drawable.nowin);
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        counter = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        ((ImageView) findViewById(R.id.main_result_imv)).setImageResource(0);
        ((ImageView) findViewById(R.id.main_winLine_imv)).setImageResource(0);
        ((ImageView) findViewById(R.id.main_playTurn_imv)).setImageResource(R.drawable.xplay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ImageView) findViewById(R.id.main_playTurn_imv)).setImageResource(R.drawable.xplay);
    }
}