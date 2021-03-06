package com.example.project.grades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MA_Grades extends AppCompatActivity {

    public static final String GRADES = "grades";
    public static final int GRADES_CODE = 100;

    boolean valid_error_name = false;
    boolean valid_error_sname = false;
    boolean valid_error_grades = false;

    //Funkcje sprawdzające poprawność wpisywanych danych
    boolean nameRegex(String name){
        Pattern p = Pattern.compile("[A-Z][a-z]{1,}");
        Matcher m = p.matcher(name);
        boolean b = m.matches();
        return b;
    }
    boolean gradeRegex(String grade) {
        boolean b = false;
        int grades = 0;
        if (!grade.isEmpty()) {
            try {
                grades = Integer.parseInt(grade);
            } catch (NumberFormatException e) {
                grades = 0;
            }

            if (grades >= 5 && grades <= 15) b = true;
            else b = false;
        }
        return b;
    }

    //Funkcja przechodząca do drugiej aktywności
    private void startSecondActivity(){

        EditText quantity_grades = (EditText) findViewById(R.id.et_grades);
        int grades_amount = Integer.parseInt(quantity_grades.getText().toString());
        Intent intent = new Intent(this, SelectGardes.class);
        intent.putExtra(GRADES,grades_amount);
        startActivityForResult(intent,GRADES_CODE);

    }
    //Funkcja "odbierająca" dane z drugiej aktnosci,
    //Ustawia widocznosc odpowiednich buttonów i textview, w zależności od obliczonej średniej
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        String message;
        super.onActivityResult(requestCode, resultCode, data);
        Button old_btn = (Button) findViewById(R.id.b_grades);
        old_btn.setVisibility(View.INVISIBLE);
        Button result_btn = (Button) findViewById(R.id.b_result);
        TextView result = (TextView) findViewById(R.id.average);
        float res = data.getFloatExtra(SelectGardes.AVERAGE,0);
        result.setText("Your average grade is " + res);
        result.setVisibility(View.VISIBLE);
        if(res >=3  ){
            result_btn.setText("Classes passed!");
            result_btn.setVisibility(View.VISIBLE);
            message="Congrats!";
        } else {
            result_btn.setText("You must retake");
        result_btn.setVisibility(View.VISIBLE);
            message="Sending retake request...";
        }

    //Listener przyciskow pojawiajacych sie dopiero po otrzymaniu sredniej
        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MA_Grades.this,
                        message, Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_mainactivity);

        EditText et_name = (EditText) findViewById(R.id.et_firstname);
        EditText et_surname = (EditText) findViewById(R.id.et_lastname);
        EditText et_grades = (EditText) findViewById(R.id.et_grades);

        Button btn_grades = (Button) findViewById(R.id.b_grades);

        TextView name_err = (TextView) findViewById(R.id.name_err);
        TextView surname_err = (TextView) findViewById(R.id.surname_err);
        TextView grades_err = (TextView) findViewById(R.id.grades_err);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            
            @Override
            public void afterTextChanged(Editable s) {
                //sprawdzanie czy imie nie jest puste i czy spelnia wyrazenie regularne w swoim walidatorze
                if(!nameRegex(et_name.getText().toString()) && !et_name.getText().toString().isEmpty()){
                    name_err.setVisibility(View.VISIBLE);
                    valid_error_name = true;
                } else {
                    name_err.setVisibility(View.INVISIBLE);
                    valid_error_name = false;
                }
                //sprawdzanie czy nazwisko nie jest puste i czy spelnia wyrazenie regularne w swoim walidatorze
                if(!nameRegex(et_surname.getText().toString()) && !et_surname.getText().toString().isEmpty()){
                    surname_err.setVisibility(View.VISIBLE);
                    valid_error_sname = true;
                } else {
                    surname_err.setVisibility(View.INVISIBLE);
                    valid_error_sname = false;
                }
                //sprawdzanie czy ilosc ocen nie jest pusta i czy spelnia wyrazenie regularne w swoim walidatorze
                if(!gradeRegex(et_grades.getText().toString()) && !et_grades.getText().toString().isEmpty()){
                    grades_err.setVisibility(View.VISIBLE);
                    valid_error_grades = true;
                } else {
                    grades_err.setVisibility(View.INVISIBLE);
                    valid_error_grades = false;
                }
                //jezeli zadna z powyzszych petli warunkowych nie wskazala bledu to pojawia sie przycisk umozliwiajacy przejscie do kolejnej aktywnosci
                if(et_grades.getText().toString().isEmpty() || et_surname.getText().toString().isEmpty() || et_grades.getText().toString().isEmpty() ||
                        valid_error_name || valid_error_sname || valid_error_grades){
                    btn_grades.setVisibility(View.INVISIBLE);
                }
                else btn_grades.setVisibility(View.VISIBLE);
            }
        };

        et_name.addTextChangedListener(tw);
        et_surname.addTextChangedListener(tw);
        et_grades.addTextChangedListener(tw);



        btn_grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity();
            }
        });





    }
}