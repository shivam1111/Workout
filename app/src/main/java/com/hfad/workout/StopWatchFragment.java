package com.hfad.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StopWatchFragment extends Fragment {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    public StopWatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if (wasRunning){
                running=true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        return layout;

    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning){
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    // Start the stop watch on click of Start button
    public void onClickStart(View view){
        running = true;
    }

    //Stop the stop watch on click of Stop button
    public void onClickStop(View view){
        running=false;
    }

    //Reset the stopwatch when the Reset button is clicked
    public void onClickReset(View view){
        running=false;
        seconds = 0;
    }

    public void runTimer(View view){
        final TextView timerView = view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timerView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}