package com.example.app_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondScreen extends AppCompatActivity {

    private List<Grade> gradeList = new ArrayList<>();
    private ListView listView;
    public static final String AVERAGE = "average";

    public void backToMainScreen(){
        Intent intent = new Intent();
        intent.putExtra(AVERAGE,Grade.averageGrade(gradeList));
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Button btn_back = (Button) findViewById(R.id.back_btn);

        listView = (ListView)findViewById(R.id.listview);

        Bundle bndl = getIntent().getExtras();
        int grades = bndl.getInt(MainActivity.GRADES);

        for (int i =1; i<= grades; i++){
            gradeList.add(new Grade("ocena" + i));
        }

        GradesArrayAdapter adapter = new GradesArrayAdapter(gradeList,this );
        listView.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainScreen();
            }
        });

    }
}