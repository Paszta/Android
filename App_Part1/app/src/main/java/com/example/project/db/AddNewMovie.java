package com.example.project.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class AddNewMovie extends AppCompatActivity {

    public static final String NEW_TITLE = "New title";
    public static final String NEW_WWW = "New www";

    List<Movie> moviesList = new ArrayList<>();
    AppDatabase db;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_movie);

        Button add_btn = findViewById(R.id.addToDB);
        EditText title_et = findViewById(R.id.et_title);
        EditText www_et = findViewById(R.id.et_www);

        add_btn.setOnClickListener(v -> {
            String title = title_et.getText().toString();
            String www = www_et.getText().toString();

            Intent reply = new Intent();
            Bundle replyContent = new Bundle();
            replyContent.putString(NEW_TITLE, title);
            replyContent.putString(NEW_WWW, www);

            reply.putExtras(replyContent);
            setResult(RESULT_OK, reply);
            finish();
        });

    }
}