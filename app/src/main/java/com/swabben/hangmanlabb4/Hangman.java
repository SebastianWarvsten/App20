package com.swabben.hangmanlabb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Collections;

public class Hangman extends AppCompatActivity {

    public static final String WORD_GUESSED_KEY = "WORD_GESSED_KEY";
    public static final String SECRET_WORD_KEY = "SECRET_WORD_KEY";
    public static final String ATTEMPTS_LEFT_KEY = "ATTEMPTS_LEFT_KEY";
    private int attempts = 10;
    public boolean wordIsGuessed = false;

    private StringBuilder characterUsed = new StringBuilder();
    private String secretWord = " ";
    private ArrayList<String> listOfWords = new ArrayList<String>();
    char[] wordDisplayedChar;
    private String wordDisplayedString;

    private TextView tv;
    private TextView tvHiddenWord;
    private EditText et;
    private ImageView iv;
    private int hangmanPicture = 0;
    private String charGuessed;
    private String url;

    /**
     * Starts the hangman activity which is the the main game code
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        tv = findViewById(R.id.attempts_guessed_letter);
        et = findViewById(R.id.guess_char);
        iv = findViewById(R.id.hangmanImageView);
        tvHiddenWord = findViewById(R.id.hidden_word_textview);
        startGame();
    }

    /**
     * Creates an Action Bar
     * @return true to show the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.hangmanactionbar, menu);
        setTitle(getString(R.string.hangman));
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
        if(id == R.id.info){
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }else if(id == R.id.back){
            Intent intent = new Intent(this, MainActivity.class);
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
     * Method for checking the character in the editText, comparing it to those in the secret word and taking appropriate action
     * @param letter the char that was passed into the editText field
     */
    public void checkIfLetterIsInWord(char letter){
        charGuessed = et.getText().toString();
        if(!characterUsed.toString().contains(charGuessed)){
            if(secretWord.indexOf(letter) >= 0){
                if(wordDisplayedString.indexOf(letter) < 0){
                    revealLetterInWord(letter);
                    displayWordOnScreen();
                    }
                }else{
                attempts--;
                hangmanPicture++;
                switch(hangmanPicture){
                    case 1:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman1.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 2:
                        url = "https://github.com/SebastianWarvsten/App20/blob/main/hangman2.jpg?raw=true";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 3:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman3.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 4:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman4.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 5:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman5.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 6:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman6.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 7:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman7.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 8:url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman8.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 9:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman9.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    case 10:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman10.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                    default:
                        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman0.jpg";
                        Glide.with(this).load(url).into(iv);
                        break;
                }
            }checkWinLose();
        }else{
            Toast.makeText(Hangman.this, R.string.charused, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method for checking if the game is won or lost
     */
    private void checkWinLose(){
        if(!wordDisplayedString.contains("-")){
            wordIsGuessed = true;
            Intent intent = new Intent(Hangman.this, Result.class);
            intent.putExtra(WORD_GUESSED_KEY, wordIsGuessed);
            intent.putExtra(SECRET_WORD_KEY,secretWord);
            intent.putExtra(ATTEMPTS_LEFT_KEY,attempts);
            startActivity(intent);
        }else if(attempts <= 0){
            Intent intent = new Intent(this, Result.class);
            intent.putExtra(WORD_GUESSED_KEY, wordIsGuessed);
            intent.putExtra(SECRET_WORD_KEY,secretWord);
            intent.putExtra(WORD_GUESSED_KEY,attempts);
            startActivity(intent);
         }else{
            characterUsed.append(charGuessed.charAt(0)+", ");
            tv.setText(""+attempts+" "+getString(R.string.attempts_left)+characterUsed);
         }
    }

    /**
     * Method that goes through the secret word and replaces all underscores with the letter
     * @param letter the char that was passed into the editText field
     */
    private void revealLetterInWord(char letter){
        int indexOfLetter = secretWord.indexOf(letter);
        while(indexOfLetter >= 0){
            wordDisplayedChar[indexOfLetter] = secretWord.charAt(indexOfLetter);
            indexOfLetter = secretWord.indexOf(letter, indexOfLetter +1);
        }
        wordDisplayedString = String.valueOf(wordDisplayedChar);
    }

    /**
     * Formating the characters in the HiddenWord textView with a space between each character and displaying it in the HiddenWord textview
     */
    private void displayWordOnScreen(){
        String formateString = "";
        for(char character: wordDisplayedChar){
            formateString+= character + " ";
        }
        tvHiddenWord.setText(formateString);
    }

    /**
     * Initiating the game, picking a random word from a String arraylist and inserting formatted underscores in the place of the letters in the secret word
     *
     */
    private void startGame(){
        listOfWords.add(getString(R.string.icecream));
        listOfWords.add(getString(R.string.library));
        listOfWords.add(getString(R.string.axe));
        listOfWords.add(getString(R.string.statue));
        listOfWords.add(getString(R.string.juggler));

        Collections.shuffle(listOfWords);
        secretWord = listOfWords.get(0);
        wordDisplayedChar = secretWord.toCharArray();

        for (int i = 0; i < wordDisplayedChar.length; i++) {
            wordDisplayedChar[i] = '-';
        }
        wordDisplayedString = String.valueOf(wordDisplayedChar);
        displayWordOnScreen();
        et.setText("");
        tv.setText(""+attempts+" "+getString(R.string.attempts_left)+characterUsed);
        url = "https://raw.githubusercontent.com/SebastianWarvsten/App20/main/hangman0.jpg";
        Glide.with(this).load(url).into(iv);
    }

    /**
     * Method that checks the first char in the editText when the guess button is clicked and clears the editText afterwards
     */
    public void guessClicked(View view) {
        try {
            char charGuessed = et.getText().charAt(0);
            checkIfLetterIsInWord(charGuessed);
        }catch (Exception e){
            Toast.makeText(Hangman.this, R.string.nocharacter, Toast.LENGTH_SHORT).show();
        }finally{
            et.setText("");
        }
    }
}