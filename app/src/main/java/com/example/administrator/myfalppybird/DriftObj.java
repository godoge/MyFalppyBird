package com.example.administrator.myfalppybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/8.
 */
public class DriftObj extends DrawSurface {
    private final Bitmap bitmap;
    float posX;
    float posY;
    int type;
    int addX;
    private Random rand;
    private int bpWidth;

    private boolean isEnd = false;

    public DriftObj(Context context) {
        super(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
        rand = new Random();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.add_change);
        bpWidth = bitmap.getWidth();
        initObj();
    }

    public void remove(List list) {
        list.remove(this);
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public void draw(Canvas canvas) {
        posX = posX - addX;
        canvas.drawBitmap(bitmap, posX, posY, new Paint());
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isTouch(float center_l, float center_t) {
        if (center_l > posX & center_l < posX + bitmap.getWidth() & center_t > posY & center_t < posY + bitmap.getHeight())
            return true;
        return false;
    }

    @Override
    public void initObj() {
        addX = new Random().nextInt(3) + 2;
        posX = getSurfaceWidth();
        posY = (float) (Math.random() + 100) - 200;
        if (posX < -bpWidth)
            isEnd = true;
    }
}
