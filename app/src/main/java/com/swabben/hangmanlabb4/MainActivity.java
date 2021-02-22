package com.swabben.hangmanlabb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Creates the main activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Creates an Action Bar
     * @return true to show the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mainmenuactionbar, menu);
        setTitle(getString(R.string.back_to_main_menu));
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Directs you to the about activity
     */
    public void aboutClicked(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    /**
     * Directs you to the game activity
     */
    public void startGameClicked(View view) {
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
    }
}