package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.HabitContract.Habits;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView add_data = (TextView) findViewById(R.id.add_data_button);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HabitActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new HabitDbHelper(this);

        TextView dummy_data = (TextView) findViewById(R.id.dummy_data);
        dummy_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHabit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private Cursor read() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Habits._ID,
                Habits.RUN,
                Habits.SPORTS,
                Habits.TV,
                Habits.EXERCISE};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                Habits.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }

    private void displayDatabaseInfo() {

        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.display_habit);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(Habits._ID + " - " +
                    Habits.RUN + " - " +
                    Habits.SPORTS + " - " +
                    Habits.TV + " - " +
                    Habits.EXERCISE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(Habits._ID);
            int runColumnIndex = cursor.getColumnIndex(Habits.RUN);
            int sportsColumnIndex = cursor.getColumnIndex(Habits.SPORTS);
            int tvColumnIndex = cursor.getColumnIndex(Habits.TV);
            int exerciseColumnIndex = cursor.getColumnIndex(Habits.EXERCISE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentRun = cursor.getString(runColumnIndex);
                String currentSports = cursor.getString(sportsColumnIndex);
                int currentTv = cursor.getInt(tvColumnIndex);
                int currentExercise = cursor.getInt(exerciseColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentRun + " - " +
                        currentSports + " - " +
                        currentTv + " - " +
                        currentExercise));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertHabit() {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Habits.RUN, 5);
        values.put(Habits.SPORTS, "Cricket");
        values.put(Habits.TV, 2);
        values.put(Habits.EXERCISE, 1);
        long Row = database.insert(Habits.TABLE_NAME, null, values);
        displayDatabaseInfo();
    }
}
