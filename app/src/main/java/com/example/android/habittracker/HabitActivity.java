package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.HabitContract.Habits;

public class HabitActivity extends AppCompatActivity {


    private EditText mRunDistance;

    private EditText mSports;

    private EditText mTvTime;

    private EditText mExerTime;

    private Button save_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        // Find all relevant views that we will need to read user input from
        mRunDistance = (EditText) findViewById(R.id.edit_run_distance);
        mSports = (EditText) findViewById(R.id.edit_sports);
        mTvTime = (EditText) findViewById(R.id.edit_tv_time);
        mExerTime = (EditText) findViewById(R.id.edit_exercise_time);
        save_data = (Button) findViewById(R.id.save_button);
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHabit();
                Intent intent = new Intent(HabitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void insertHabit() {
        String stringRun = mRunDistance.getText().toString().trim();
        int runDistance = Integer.parseInt(stringRun);
        String sports = mSports.getText().toString().trim();
        String stringTvTime = mTvTime.getText().toString().trim();
        int tvTime = Integer.parseInt(stringTvTime);
        String stringExer = mExerTime.getText().toString().trim();
        int exerTime = Integer.parseInt(stringExer);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Habits.RUN, runDistance);
        value.put(Habits.SPORTS, sports);
        value.put(Habits.TV, tvTime);
        value.put(Habits.EXERCISE, exerTime);

        long newRow = db.insert(Habits.TABLE_NAME, null, value);
        if (newRow == -1) {
            Toast.makeText(this, R.string.save_error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        }

    }

}
