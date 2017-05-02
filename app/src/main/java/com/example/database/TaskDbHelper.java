package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.database.TaskDbContract.TaskEntry;

import java.util.ArrayList;
import java.util.List;


public class TaskDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Task.db";
    private SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskEntry.TABLE_TASK + " (" +
                    TaskEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskEntry.COLUMN_TASK + " TEXT," +
                    TaskEntry.COLUMN_PLACE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_TASK;

    private static final String SQL_GET_ALL_ENTRIES =
            "SELECT * FROM " + TaskEntry.TABLE_TASK;

    public TaskDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TaskHelper", "onCreate called");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //do nothing right now. Implement if necessary...
    }

    public long addTask(Task task){
        ContentValues values = new ContentValues();
        values.put(TaskEntry.COLUMN_TASK, task.getTaskString());
        values.put(TaskEntry.COLUMN_PLACE, task.getPlace());
        long rowId = db.insert(TaskEntry.TABLE_TASK, null, values);
        Log.d("insertTask", "rowId is: " + rowId);
        return rowId;
    }

    public Task getTask(int taskId){
        String[] projection = {
                TaskEntry._ID,
                TaskEntry.COLUMN_TASK,
                TaskEntry.COLUMN_PLACE
        };
        String selection = TaskEntry._ID + " = ?";
        String[] selectionArgs = {taskId + ""};

        Cursor cursor = db.query(
                TaskEntry.TABLE_TASK,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String taskString = cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_TASK));
        String placeString = cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_PLACE));

        return new Task(taskId, taskString, placeString);
    }

    public void deleteTask(int taskId){
        String selection = TaskEntry._ID + " LIKE ?";
        String[] selectionArgs = {taskId + ""};
        int n = db.delete(TaskEntry.TABLE_TASK, selection, selectionArgs);
        Log.d("deleteTask", "number of rows deleted: " + n );
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        Cursor cursor = db.rawQuery(SQL_GET_ALL_ENTRIES, null);
        String taskString;
        String placeString;
        int id;

        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(TaskEntry._ID));
            taskString = cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_TASK));
            placeString = cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_PLACE));
            taskList.add(new Task(id, taskString, placeString));
        }

        return taskList;
    }
}
