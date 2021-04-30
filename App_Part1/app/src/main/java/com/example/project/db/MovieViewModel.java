package com.example.project.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;
    private final LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        allMovies = movieRepository.getAllMovies();
    }

    LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

    public void insert(Movie movie){
        movieRepository.insert(movie);
    }

    public void deleteAll(){
        movieRepository.deleteAll();
    }

    public void deleteMovie(Movie movie){
        movieRepository.deleteMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.update(movie);
    }
}
