package com.example.project.drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.project.R;

import java.util.ArrayList;

public class DrawingSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder container;
    private Thread drawingThread;
    private boolean threadDraws = false;
    private Object block = new Object();

    public Paint paint;
    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Path path;
    int currentColor = orange;
    ArrayList<Circle> cicrclesPos = new ArrayList<Circle>();
    ArrayList<CustomPath> paths = new ArrayList<CustomPath>();



    static int orange = Color.rgb(255,159,28);


    public DrawingSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        container = getHolder();
        container.addCallback(this);

        paint = new Paint();
        paint.setColor(orange);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public void continueDrawing(){
        drawingThread = new Thread(this);
        threadDraws = true;
        drawingThread.start();
    }

    public void pauseDrawing(){
        threadDraws = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        performClick();
        synchronized (block){
            Canvas current_canvas = new Canvas(bitmap);

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path = new Path();
                    pauseDrawing();
                    continueDrawing();
                    path.moveTo(event.getX(), event.getY());
                    cicrclesPos.add(new Circle(event.getX(), event.getY(), currentColor));
                    paths.add(new CustomPath(path, currentColor));
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    cicrclesPos.add(new Circle(event.getX(), event.getY(), currentColor));
                    break;
            }
        }
        return true;
    }

    public boolean performClick(){
        return super.performClick();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        canvas = container.lockCanvas();
        if(canvas != null){
            canvas.drawARGB(255,255,255,255);
            canvas.drawBitmap(bitmap, 0,0,null);
            container.unlockCanvasAndPost(canvas);
        }
    }

    public void clearSurface(){
        paths.clear();
        cicrclesPos.clear();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        threadDraws = false;

    }

    @Override
    public void run() {
        while(threadDraws){
            Canvas new_canvas = null;
            try {
                synchronized (container){
                    if(!container.getSurface().isValid()) continue;
                    new_canvas = container.lockCanvas(null);
                    synchronized (block){
                        if(threadDraws){
                            canvas.drawARGB(255,255,255,255);
                            for (Circle c : cicrclesPos) {
                                paint.setColor(c.getC());
                                canvas.drawCircle(c.getX(), c.getY(), 10, paint );
                            }

                            for (CustomPath cp : paths ) {
                                paint.setColor(cp.getColor());
                                canvas.drawPath(cp.getPath(), paint);
                            }

                        }
                    }
                }
            } finally {
                if(new_canvas != null){
                    container.unlockCanvasAndPost(new_canvas);
                }
            }
            try {
                Thread.sleep(1000/25);
            } catch (InterruptedException e) { }
        }

    }

}
