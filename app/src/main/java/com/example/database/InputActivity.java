package com.example.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.AdapterView.*;

public class InputActivity extends AppCompatActivity {
    TaskDbHelper dbHelper;
    private int currentId = 0;
    private EditText taskView;
    private EditText placeView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> tasks;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        dbHelper = new TaskDbHelper(getApplicationContext());
        //db = dbHelper.getWritableDatabase();
        taskView = (EditText) findViewById(R.id.task_input);
        placeView = (EditText) findViewById(R.id.place_input);
        listView = (ListView) findViewById(R.id.task_view);
        loadListView();
        initLongClickListener();
    }

    private void initLongClickListener() {
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long l) {
                DeleteTaskDialog dialog = new DeleteTaskDialog();
                dialog.initDialog(dbHelper, tasks, taskAdapter, view, index);
                dialog.show(getFragmentManager(),"");
                return true;
            }
        });
    }

    public void insertTask(View view){
        String taskName = taskView.getText().toString();
        String taskPlace = placeView.getText().toString();

        if(taskName == "" || taskPlace == ""){
            Toast.makeText(this,"Please input task and place!", Toast.LENGTH_SHORT).show();
        }
        else{
            Task newTask = new Task(taskName, taskPlace);
            clearInputFields();
            taskAdapter.add(newTask);
            long id = dbHelper.addTask(newTask);
            Toast.makeText(this,"Task Inserted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputFields(){
        taskView.setText("");
        placeView.setText("");
    }

    private void loadListView(){
        Log.d("loadListView","called");
        tasks = dbHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, R.layout.list_view_item, tasks);
        listView.setAdapter(taskAdapter);
    }

}
