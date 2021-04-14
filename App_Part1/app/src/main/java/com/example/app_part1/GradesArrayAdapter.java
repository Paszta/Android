package com.example.app_part1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    public View getView(int row, View recycledView, ViewGroup parent){

        View view = null;

        if(recycledView == null){
            LayoutInflater flater = activity.getLayoutInflater();
            view = flater.inflate(R.layout.grade, parent, false);
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.gradesGroup);
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                //odczytanie taga
                Grade tag = (Grade) group.getTag();
                //przypisanie do oceny z tagiem zaznaczonego id
                tag.setCurrentGrade(checkedId);
                RadioButton checkedGrade = activity.findViewById(checkedId);
                try{
                    //przypisanie do obiektu oceny odpowiedniej oceny pobranej z zaznaczonego radiobuttona
                    tag.setGrade(Integer.parseInt(checkedGrade.getText().toString()));
                } // try-catch zapobiega wylaczeniu aplikacji gdy parsowane jest puste zaznaczenie
                catch (NullPointerException e) { }
            });
            //ustawienie taga przy odpowiednim rzedzie w tablicy
            radioGroup.setTag(gradesArray.get(row));
        } else {
            view = recycledView;
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.gradesGroup);
            radioGroup.setTag(gradesArray.get(row));
        }

        Grade grade = gradesArray.get(row);
        TextView tv = (TextView) view.findViewById(R.id.etykieta);
        tv.setText(grade.getName());
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.gradesGroup);
        Integer checkedFalse = ((Grade)radioGroup.getTag()).getCurrentGrade();
        //sprawdzenie czy zaznaczone buttony faktycznie zostaly zaznaczone (a nie z cache)
        if(checkedFalse != null) radioGroup.check(checkedFalse);
        //jezeli nie zostaly faktycznie zaznaczone to radiogroup jest czyszczony
        else radioGroup.clearCheck();

        return view;
    }




}
