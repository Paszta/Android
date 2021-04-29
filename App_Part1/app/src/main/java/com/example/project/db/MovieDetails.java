package com.example.project.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;

public class MovieDetails extends AppCompatActivity {

    public static final String UPDATED_TITLE = "Updated title";
    public static final String UPDATED_WWW = "Updated www";

    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        EditText currentTitle =findViewById(R.id.et_updatedTitle);
        EditText currentWWW = findViewById(R.id.et_updatedWWW);
        Button directToWWW_btn = findViewById(R.id.www_btn);
        Button update_btn = findViewById(R.id.update_btn);


        //Odebranie informacji o pozycji na liscie
        Bundle bundle = getIntent().getExtras();
        Movie movie = (Movie) bundle.getSerializable(MovieAdapter.MOVIE_KEY);

        currentTitle.setText(movie.getTitle());
        currentWWW.setText(movie.getWww());

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //odczytanie z editTextów napisów
                String updatedTitle = currentTitle.getText().toString();
                String updatedWWW = currentWWW.getText().toString();

                movie.setTitle(updatedTitle);
                movie.setWww(updatedWWW);

                movieViewModel.updateMovie(movie);
                finish();
            }
        });

        directToWWW_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(movie.getWww()));
                startActivity(intent);
            }
        });



    }
}