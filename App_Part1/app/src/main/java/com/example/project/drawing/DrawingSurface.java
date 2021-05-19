package com.example.project.drawing;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class DrawingSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder container;
    private Thread drawingThread;
    private boolean threadDraws = false;
    private Object block = new Object();

    static int orange = Color.rgb(255,159,28);
    static int bright_orange = Color.rgb(255,191,105);
    static int blue = Color.rgb(46,196,182);
    static int bright_blue = Color.rgb(203,243,240);

    public DrawingSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        container = getHolder();
        container.addCallback(this);

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void run() {

    }
}
