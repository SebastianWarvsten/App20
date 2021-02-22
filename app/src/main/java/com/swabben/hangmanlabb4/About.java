package com.swabben.hangmanlabb4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class About extends AppCompatActivity {

    /**
     * Starts the about activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(getText(R.string.about));
    }

}