package com.example.project.drawing;

import android.graphics.Color;

public class Circle {

   private float x;
   private float y;
   private int c;

    public Circle(float x, float y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getC() {
        return c;
    }
}
