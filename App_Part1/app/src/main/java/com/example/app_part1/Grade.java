package com.example.app_part1;

import java.util.List;

public class Grade {

    private String name;
    private int grade;

    public Grade(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public Grade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public static float averageGrade(List<Grade> grades){
        float sum=0;
        for(Grade all: grades){
            sum+=all.getGrade();
        }
        return sum/grades.size();
    }




}
