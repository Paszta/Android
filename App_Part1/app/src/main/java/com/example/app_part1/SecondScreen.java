package com.example.app_part1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondScreen extends AppCompatActivity {

    private List<Grade> gradeList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        listView = (ListView)findViewById(R.id.listview);

        Bundle bndl = getIntent().getExtras();
        int grades = bndl.getInt(MainActivity.GRADES);

        for (int i =0; i< grades; i++){
            gradeList.add(new Grade("ocena" + i));
        }

        GradesArrayAdapter adapter = new GradesArrayAdapter(gradeList,this );
        listView.setAdapter(adapter);



    }
}