package com.example.app_part1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class GradesArrayAdapter  extends ArrayAdapter<Grade> {

    private List<Grade> gradesArray;
    private Activity activity;

    public GradesArrayAdapter(List<Grade> gradesArray, Activity activity) {
        super(activity, R.layout.grade, gradesArray);
        this.gradesArray = gradesArray;
        this.activity = activity;
    }

    @Override
    public View getView(int row, View view, ViewGroup parent){

        Grade grade = gradesArray.get(row);

        if(view == null){
            LayoutInflater flater = activity.getLayoutInflater();
            view = flater.inflate(R.layout.grade, parent, false);
        }

        TextView tv = (TextView) view.findViewById(R.id.etykieta);
        tv.setText(grade.getName());
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.gradesGroup);
        radioGroup.setOnCheckedChangeListener(readGrade(grade));
        return view;
    }

    public RadioGroup.OnCheckedChangeListener readGrade(final Grade grade){

        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) activity.findViewById(checkedId);
                grade.setGrade(Integer.parseInt(rb.getText().toString()));
            }
        };

    }




}
