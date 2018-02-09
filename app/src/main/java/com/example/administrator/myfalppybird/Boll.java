package com.example.administrator.myfalppybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Boll extends DrawSurface {

    private int[] addy;
    private int addCursor;
    private float posX;
    private float posY;
    private Paint paint;
    private int fallSpeed;
    private int flyHeight;
    private Bitmap bitmap;
    private Context context;


    public Boll(Context context) {
        super(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.p1);
        flyHeight = 10;
        fallSpeed = 6;
        initObj();
    }


    public void initCursor() {
        addCursor = 0;

    }

    public float getHorizonCenter() {
        return posX + bitmap.getWidth() / 2;


    }

    public Position[] getSensitivePoint() {
        Position[] positions = new Position[]{
                new Position(getLeft(), getVerticalCenter()),
                new Position(getHorizonCenter(), getTopPos()),
                new Position(getRightPos(), getVerticalCenter()),
                new Position(getHorizonCenter(), getBottomPos())};
        return positions;
    }

    private float getRightPos() {
        return posX + bitmap.getWidth();
    }

    private float getTopPos() {
        return posY;
    }

    public float getLeft() {
        return posX;


    }

    public float getBottomPos() {
        return posY + bitmap.getHeight();
    }

    public float getVerticalCenter() {
        return posY + bitmap.getHeight() / 2;


    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, posX, posY, paint);
        if (addCursor < addy.length - 1)
            addCursor++;
        posY = posY + addy[addCursor];

    }

    public int getJumpHeight() {
        int total = 0;
        for (int i = 0; i < flyHeight; i++)
            total += Math.abs(addy[i]);
        return total;
    }

    public int getBollHeight() {
        return 40;
    }


    @Override
    public void initObj() {
        posX = getSurfaceWidth() / 2;
        posY = getSurfaceHeight() / 2;
        addCursor = flyHeight;
        addy = new int[flyHeight + fallSpeed];
        for (int i = 0; i < flyHeight; i++)
            addy[i] = -(i + 1) * 2;
        for (int i = 0; i < fallSpeed; i++)
            addy[i + flyHeight] = i + 2;
        paint = new Paint();
    }


    public void drawLose(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.lose), posX, posY, paint);
    }
}
