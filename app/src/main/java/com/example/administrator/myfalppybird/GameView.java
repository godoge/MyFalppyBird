package com.example.administrator.myfalppybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/9/8.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Boll boll;
    private Timer timer;
    private Wall wall;
    private Random rand;
    List<DriftObj> list;
    Context context;
    private Timer checkTimer;
    Handler handler;

    private boolean isAddObj() {
        return rand.nextInt(100) == 0;

    }

    public GameView(Context context, Handler handler) {
        super(context);
        this.context = context;
        this.handler = handler;
        list = new ArrayList<>();
        rand = new Random();
        boll = new Boll(context);
        wall = new Wall(context, boll.getBollHeight() * 1.8f + boll.getJumpHeight());
        timer = new Timer();
        getHolder().addCallback(this);
    }

    private void check() {
        checkTimer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isTouch(boll.getHorizonCenter(), boll.getVerticalCenter()))
                        list.get(i).setEnd(true);
                }
                if (wall.isTouched(boll.getSensitivePoint())) {
                    stop();
                    Canvas canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    for (DriftObj obj : list) {
                        obj.draw(canvas);
                    }
                    wall.draw(canvas);
                    boll.drawLose(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }
                if (wall.isPass(boll.getLeft())) {
                    handler.sendEmptyMessage(0);
                }
            }
        }, 100, 10);


    }


    public void initPos() {
        boll.initCursor();
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Canvas canvas = getHolder().lockCanvas();
            if (canvas == null)
                return;
            canvas.drawColor(Color.WHITE);
            if (isAddObj())
                list.add(new DriftObj(context));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isEnd())
                    list.get(i).remove(list);
                else
                    list.get(i).draw(canvas);
            }
            wall.draw(canvas);
            boll.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    };

    private void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (checkTimer != null) {
            checkTimer.cancel();
            checkTimer = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        timer.schedule(timerTask, 100, 10);
        check();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stop();
    }
}
