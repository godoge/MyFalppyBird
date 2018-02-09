package com.example.administrator.myfalppybird;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/9/8.
 */
public abstract class DrawSurface {
    public abstract void draw(Canvas canvas);

    public abstract void initObj();

    private float surfaceHeight;
    private float surfaceWidth;

    public DrawSurface(int width, int height) {
        surfaceHeight = height;
        surfaceWidth = width;

    }

    public float getSurfaceWidth() {
        return surfaceWidth;
    }

    public float getSurfaceHeight() {

        return surfaceHeight;

    }

    ;
    ;
}
