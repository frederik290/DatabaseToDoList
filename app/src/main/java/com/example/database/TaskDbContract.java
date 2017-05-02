package com.example.database;

import android.content.SharedPreferences;
import android.provider.BaseColumns;

/**
 * Created by frederik290 on 01/05/2017.
 */

public final class TaskDbContract {
    private TaskDbContract(){}

    //Define the table content
    public static class TaskEntry implements BaseColumns{
        public static final String TABLE_TASK = "table_task";
        public static final String COLUMN_TASK =  "task";
        public static final String COLUMN_PLACE = "place";
        public static final String _ID = "id";

    }
}
