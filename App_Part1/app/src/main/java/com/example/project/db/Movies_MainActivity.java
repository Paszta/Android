package com.example.project.db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Movies_MainActivity extends AppCompatActivity {

    private static final String TAG = Movies_MainActivity.class.getSimpleName();
    public final static int ADD_MOVIE_REQUEST_CODE =1;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies__main);

        FloatingActionButton add_new_fabtn = (FloatingActionButton) findViewById(R.id.add_new);

        RecyclerView addedMovies = (RecyclerView) findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this);
        addedMovies.setAdapter(movieAdapter);

        addedMovies.setLayoutManager(new LinearLayoutManager(this));
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        movieViewModel.getAllMovies().observe(this, movies -> {
            movieAdapter.setMovieList(movies);
        });


        add_new_fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( Movies_MainActivity.this, AddNewMovie.class), ADD_MOVIE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MOVIE_REQUEST_CODE && resultCode == RESULT_OK){
           Bundle content = data.getExtras();
           String newTitle = content.getString(AddNewMovie.NEW_TITLE);
           String newWWW = content.getString(AddNewMovie.NEW_WWW);
           Movie newMovie = new Movie(newTitle, newWWW);
           movieViewModel.insert(newMovie);
        } else {
            Log.e(TAG, "Unknown result");
        }
    }
}