package com.example.project.drawing;

import android.graphics.Path;

public class CustomPath {

    private Path path;
    private int color;

    public CustomPath(Path path, int color) {
        this.path = path;
        this.color = color;
    }

    public Path getPath() {
        return path;
    }

    public int getColor() {
        return color;
    }
}
