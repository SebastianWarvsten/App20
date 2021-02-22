package com.swabben.hangmanlabb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {



    private TextView tvResult;
    private TextView tvCorrectWord;
    private TextView tvAttemptsLeft;

    /**
     * Receives a boolean, a String and an int from Hangman.java and writes a winning/losing message depending on what was received
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvResult = findViewById(R.id.result_message);
        tvCorrectWord = findViewById(R.id.correct_word);
        tvAttemptsLeft = findViewById(R.id.atempts_left);

        Intent intent = getIntent();
        Boolean correctWord = intent.getBooleanExtra(Hangman.WORD_GUESSED_KEY, false);
        String theWord = intent.getStringExtra(Hangman.SECRET_WORD_KEY);
        int attemptsLeft = intent.getIntExtra(Hangman.ATTEMPTS_LEFT_KEY, 0);
        String numberOfAttemptsLeft = getResources().getString(R.string.number_of_attempts_left);
        String theCorrectWordWas = getResources().getString(R.string.correct_word_was);

        if(correctWord){
            tvResult.setText(R.string.you_win);
            tvCorrectWord.setText(theCorrectWordWas+theWord);
            tvAttemptsLeft.setText(numberOfAttemptsLeft+attemptsLeft);
        }else{
            tvResult.setText(R.string.you_lose);
            tvCorrectWord.setText(theCorrectWordWas+theWord);
            tvAttemptsLeft.setText(numberOfAttemptsLeft+attemptsLeft);
        }
        setTitle(getText(R.string.result));
    }
    /**
     * Creates an Action Bar
     * @return true to show the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.resultactionbar, menu);
        return true;
    }

    /**
     * Activates the buttons in the Action Bar
     * @param item one of the buttons
     * @return true if the button has a function
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.start_game_action_bar){
            Intent intent = new Intent(this, Hangman.class);
            startActivity(intent);
        } else if(id == R.id.info){
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }else if(id == R.id.back){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Return to the main menu
     */
    public void mainMenuClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}