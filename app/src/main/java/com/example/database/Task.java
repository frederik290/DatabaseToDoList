package com.example.database;


public class Task {
    private int id;
    private String taskString;
    private String place;

    public Task(int id, String taskString, String place) {
        this.id = id;
        this.taskString = taskString;
        this.place = place;
    }

    public Task(String taskString, String place){
        this.taskString = taskString;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getTaskString() {
        return taskString;
    }

    public String getPlace() {
        return place;
    }
}
