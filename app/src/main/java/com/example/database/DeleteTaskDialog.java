package com.example.database;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by frederik290 on 02/05/2017.
 */

public class DeleteTaskDialog extends DialogFragment {
    private TaskDbHelper helper;
    private ArrayList<Task> tasks;
    private TaskAdapter taskAdapter;
    private View clickedTask;
    private int index;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.delete_task)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        tasks.remove(index);
                        taskAdapter.notifyDataSetChanged();
                        int idTag = (int) clickedTask.getTag(R.string.id_tag);
                        helper.deleteTask(idTag);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                    }
                });
        return builder.create();
    }

    public void initDialog(TaskDbHelper helper, ArrayList<Task> tasks, TaskAdapter taskAdapter, View clickedTask, int index){
        this.helper = helper;
        this.tasks = tasks;
        this.taskAdapter = taskAdapter;
        this.clickedTask = clickedTask;
        this.index = index;
    }
}
