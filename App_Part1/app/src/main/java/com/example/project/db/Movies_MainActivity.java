package com.example.project.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
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

        ExtendedFloatingActionButton add_new_fabtn = (ExtendedFloatingActionButton) findViewById(R.id.add_new);
        Button truncate_btn = findViewById(R.id.truncate_btn);
        TextView emptyList = findViewById(R.id.tv_emptyList);

        //truncate_btn.setBackgroundColor(Color.BLACK);

        RecyclerView addedMovies = (RecyclerView) findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this);
        addedMovies.setAdapter(movieAdapter);

        addedMovies.setLayoutManager(new LinearLayoutManager(this));
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        movieViewModel.getAllMovies().observe(this, movies -> {
            movieAdapter.setMovieList(movies);

            if(movies.isEmpty()){
                emptyList.setVisibility(View.VISIBLE);
                truncate_btn.setClickable(false);
            } else {
                emptyList.setVisibility(View.INVISIBLE);
                truncate_btn.setClickable(true);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                movieViewModel.deleteMovie(movieAdapter.getMovieAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(addedMovies);


        add_new_fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent( Movies_MainActivity.this, AddNewMovie.class), ADD_MOVIE_REQUEST_CODE);
            }
        });

        truncate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieViewModel.deleteAll();
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