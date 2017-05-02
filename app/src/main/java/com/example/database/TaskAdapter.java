package com.example.database;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class TaskAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task> taskList;
    int resource;
    private Context context;

    public TaskAdapter(Context context, int resource, ArrayList<Task> taskList) {
        super(context, resource, taskList);
        this.taskList = taskList;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Task task = taskList.get(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, null);

        TextView taskView = (TextView) view.findViewById(R.id.task_output);
        TextView placeView = (TextView) view.findViewById(R.id.place_output);

        taskView.setText(task.getTaskString());
        view.setTag(R.string.id_tag, task.getId());
        placeView.setText(task.getPlace());

        return view;
    }
}
