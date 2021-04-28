package com.example.project.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Movies")
public class Movie implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    private int movieID;

    @NonNull
    @ColumnInfo(name= "title")
    private String title;

    @NonNull
    @ColumnInfo(name= "www")
    private String www;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getWww() {
        return www;
    }

    public void setWww(@NonNull String www) {
        this.www = www;
    }

    public Movie(@NonNull String title, @NonNull String www) {
        this.title = title;
        this.www = www;
    }
}
