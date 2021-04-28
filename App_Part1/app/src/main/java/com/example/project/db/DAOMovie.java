package com.example.project.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DAOMovie {
    //dodanie rekordu
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Movie movie);

    //wyciagniecie wszystkeigo z bazy
    @Query("SELECT * FROM Movies")
    LiveData<List<Movie>> getAll();

    //usuniecie pojedynczej pozycji
    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM Movies")
    void deleteAll();


    //aktualizowanie bazy
//    @Query("UPDATE Movies SET title = :s_title, www = :s_www WHERE id = :s_id")
//    void update(int s_id, String s_title, String s_www);




}

