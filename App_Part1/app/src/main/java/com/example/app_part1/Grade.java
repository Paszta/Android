package com.example.app_part1;

import java.util.List;

public class Grade {

    private String name;
    private int grade;
    private int currentGrade;

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

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

    public static float averageGrade(List<Grade> grades){
        float sum=0;
        for(Grade all: grades) {
            sum += all.getGrade();
        }
        return sum/grades.size();
    }

    public static boolean checkAllRows(List<Grade> grades){
        boolean isFine = true;
        for(Grade all: grades) {
            if(all.getGrade() == 0) isFine = false;
        }
        return isFine;
    }

}
