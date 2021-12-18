package com.hfad.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(this.getLocalClassName(),"Detail Activity onCreate() 1");
        super.onCreate(savedInstanceState);
        Log.d(this.getLocalClassName(),"Detail Activity onCreate() 2");
        setContentView(R.layout.activity_detail);
        Log.d(this.getLocalClassName(),"Detail Activity onCreate() 3");
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)getSupportFragmentManager()
                                                        .findFragmentById(R.id.detail_frag);

        int workoutId = (int)getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        Log.d(this.getLocalClassName(),"Detail Activity onCreate() 4");
        workoutDetailFragment.setWorkout(workoutId);
    }
}