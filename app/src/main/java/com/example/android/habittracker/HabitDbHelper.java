package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.HabitContract.Habits;


public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String log_Tag = HabitDbHelper.class.getSimpleName();

    private static final String DataBase_Name = "habits.db";
    private static final int DataBase_version = 1;

    public HabitDbHelper(Context context) {

        super(context, DataBase_Name, null, DataBase_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String habitTable = "CREATE TABLE " + Habits.TABLE_NAME + " ("
                + Habits._ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + Habits.RUN + " INTEGER ,"
                + Habits.SPORTS + " TEXT,"
                + Habits.TV + " INTEGER,"
                + Habits.EXERCISE + " INTEGER);";
        db.execSQL(habitTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase Database, int old_version, int new_version) {

    }
}
