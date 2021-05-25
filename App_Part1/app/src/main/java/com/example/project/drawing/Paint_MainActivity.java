package com.example.project.drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class Paint_MainActivity extends AppCompatActivity {

    static int orange_paint = Color.rgb(255,159,28);
    static int bright_orange_paint = Color.rgb(255,191,105);
    static int blue_paint = Color.rgb(46,196,182);
    static int bright_blue_paint = Color.rgb(203,243,240);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_main);

        Button orange = findViewById(R.id.orange_btn);
        Button brightOrange = findViewById(R.id.br_orange_btn);
        Button brightBlue = findViewById(R.id.br_blue_btn);
        Button blue = findViewById(R.id.blue_btn);



        Button  clearCanvas = findViewById(R.id.button_clear);

        DrawingSurface canvasSurface = findViewById(R.id.ds_canvas);

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasSurface.setCurrentColor(orange_paint);
            }
        });

        brightOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasSurface.setCurrentColor(bright_orange_paint);
            }
        });

        brightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasSurface.setCurrentColor(bright_blue_paint);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasSurface.setCurrentColor(blue_paint);
            }
        });

        clearCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasSurface.clearSurface();
            }
        });

    }
}