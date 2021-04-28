package com.example.project.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private DAOMovie daoMovie;
    private LiveData<List<Movie>> allMovies;

    MovieRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getDB(application);

        daoMovie = appDatabase.daoMovie();
        allMovies = daoMovie.getAll();
    }

    LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    void insert(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(()->{
            daoMovie.insert(movie);
        });
    }

    void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(()-> {
            daoMovie.deleteAll();
        });
    }

    void deleteMovie(Movie movie){
        AppDatabase.databaseWriteExecutor.execute(()->{
            daoMovie.delete(movie);
        });
    }

}
