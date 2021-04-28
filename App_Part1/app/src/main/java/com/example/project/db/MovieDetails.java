package com.example.project.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project.R;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        edit

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        String title = bundle.getString(MovieAdapter.TITLE_KEY);
        String www = bundle.getString(MovieAdapter.WWW_KEY);





    }
}