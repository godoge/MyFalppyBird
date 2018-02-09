package com.example.administrator.myfalppybird;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
    private GameView gameView;
    private TextView tv;
    private int score = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    score++;
                    tv.setText(score + "");
                    break;
                case 1:
                    break;


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv = (TextView) findViewById(R.id.tv_info);
        LinearLayout fl = (LinearLayout) findViewById(R.id.layout);
        gameView = new GameView(this, handler);
        fl.addView(gameView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameView.initPos();
                break;


        }
        return super.onTouchEvent(event);
    }
}
