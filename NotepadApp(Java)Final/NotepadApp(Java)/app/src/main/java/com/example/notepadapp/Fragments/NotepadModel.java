package com.example.notepadapp.Fragments;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NotepadModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String name ;
    private String description ;
    private String day ;
    private String time ;
    private String date ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}