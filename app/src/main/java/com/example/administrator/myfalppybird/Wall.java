package com.example.administrator.myfalppybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Wall extends DrawSurface {
    float posX;
    float posY_UP;
    float dowmWall_PosY;
    float space;
    private Paint paint;
    int addX = 2;
    int wallWidth = 50;
    private float upWallHeight;
    private Random rand;
    private boolean calc;

    public boolean isCalc() {
        return calc;
    }

    public Wall(Context context, float space) {
        super(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
        rand = new Random();
        this.space = space;
        initObj();
        initWallPos();
    }

    public boolean isPass(float x) {
        if (calc && x > posX + wallWidth) {
            calc = false;
            return true;
        }
        return false;

    }

    public void setWallPos(float X, float posY_UP, float posY_DOWN) {
        posX = X;
        this.posY_UP = posY_UP;
        this.dowmWall_PosY = posY_DOWN;
    }

    public void initWallPos() {
        calc = true;
        posX = getSurfaceWidth();
        upWallHeight = (float) Math.random() * (getSurfaceHeight() - space - 100) + 50;
        Log.i("INFO_POS_INIT", posX + "");
        dowmWall_PosY = space + upWallHeight;
    }

    private void drawWall_UP(Canvas canvas) {
        Log.i("INFO_POS", posX + "");
        canvas.drawRect(posX, posY_UP, posX + wallWidth, upWallHeight, paint);

    }

    private void drawDownWall(Canvas canvas) {
        canvas.drawRect(posX, dowmWall_PosY, posX + wallWidth, getSurfaceHeight(), paint);
    }

    public boolean isTouched(Position[] positions) {
        for (Position pos : positions)
            if (pos.getX() > posX & pos.getX() < posX + wallWidth & (pos.getY() > dowmWall_PosY | pos.getY() < upWallHeight))
                return true;
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        if (posX < -wallWidth)
            initWallPos();
        drawWall_UP(canvas);
        drawDownWall(canvas);
        posX = posX - addX;

    }


    @Override
    public void initObj() {
        paint = new Paint();
        posY_UP = 0;
        paint.setColor(Color.BLACK);

    }


}
