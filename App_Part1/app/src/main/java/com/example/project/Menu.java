package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.db.Movies_MainActivity;
import com.example.project.drawing.Paint_MainActivity;
import com.example.project.grades.MA_Grades;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //ConstraintLayout cs_layout = (ConstraintLayout) findViewById(R.id.menu_layout);

        Button firstapp = (Button) findViewById(R.id.firstapp_btn);
        Button secondapp = (Button) findViewById(R.id.secapp_btn);
        Button thirdapp = (Button) findViewById(R.id.drawapp_btn);

        firstapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, MA_Grades.class));
            }
        });

        secondapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Menu.this, Movies_MainActivity.class));
            }
        });

        thirdapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Paint_MainActivity.class));
            }
        });


    }
}