package com.example.app_part1;

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
}
