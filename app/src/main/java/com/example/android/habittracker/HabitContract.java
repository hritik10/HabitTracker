package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by Hritik on 13-05-2017.
 */

public final class HabitContract {

    public static final class Habits implements BaseColumns {


        /**
         * Name of the database
         */
        public final static String TABLE_NAME = "HabitChecker";
        /**
         * Unique id for pets
         * type integer
         */
        public final static String _ID = BaseColumns._ID;

        public final static String RUN = "running_distance";

        public final static String SPORTS = "sports";

        public final static String TV = "tv";

        public final static String EXERCISE = "Exercise_time";
    }
}
