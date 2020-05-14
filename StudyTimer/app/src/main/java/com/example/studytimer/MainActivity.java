package com.example.studytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Create Chronometer, offset timer and running state
    private Chronometer chronometer;
    private long pauseOffset;
    private long time;
    private boolean running;

    //Initialise shared preference string
    public static final String lastPref = "LastPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Chronometer
        chronometer = findViewById(R.id.chronometer);

        //Restore values from saved instance
        if(savedInstanceState != null){
            running = savedInstanceState.getBoolean("keyRunning");
            time = savedInstanceState.getLong("keyTime");
            pauseOffset = savedInstanceState.getLong("keyPauseOffset");

            //Restart timer
            if(running = true)
            {
                chronometer.setBase(SystemClock.elapsedRealtime() - time);
                chronometer.start();
            }
        }

        //Display Chronometer as hours, minutes and seconds
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //long time
                time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h   = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                chronometer.setText(t);
            }
        });

        //Check for previous results
        SharedPreferences prefs = getSharedPreferences(lastPref, MODE_PRIVATE);
        long previousTime = prefs.getLong("keyTime", 0);

        //Create and set study result text
        TextView studyResults = (TextView) findViewById(R.id.textStudyResult);
        //Change long to string
        String finaltimestring = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(previousTime),
                TimeUnit.MILLISECONDS.toMinutes(previousTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(previousTime)),
                TimeUnit.MILLISECONDS.toSeconds(previousTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(previousTime)));
        studyResults.setText("You spent " + finaltimestring + " on study last time.");

        //Initialise buttons
        ImageButton buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        ImageButton buttonPause = (ImageButton) findViewById(R.id.buttonPause);
        ImageButton buttonStop = (ImageButton) findViewById(R.id.buttonStop);

        //Start timer
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    //Remove offset from elapsed time before starting
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    //Set running state to true
                    running = true;
                }
                //Display error
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"Timer already running",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Pause timer
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    chronometer.stop();
                    //Calculate offset
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    //Set running state to false
                    running = false;
                }
                //Display error
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"Timer must be running in order to pause it",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Stop timer
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                Toast toast=Toast.makeText(getApplicationContext(),"Timer stopped and time saved",Toast. LENGTH_SHORT);
                toast.show();

                //Create and initialise shared preferences and editor
                SharedPreferences.Editor editor = getSharedPreferences(lastPref, MODE_PRIVATE).edit();
                editor.putLong("keyTime", time);
                editor.apply();
            }
        });
    }

    //Values to save when rotating screen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("keyRunning", running);
        outState.putLong("keyTime", time);
        outState.putLong("keyPauseOffset", pauseOffset);
    }
}
