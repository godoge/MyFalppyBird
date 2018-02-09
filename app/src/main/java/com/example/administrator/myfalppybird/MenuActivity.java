package com.example.administrator.myfalppybird;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Administrator on 2016/9/8.
 */
public class MenuActivity extends Activity {

    private ImageButton im_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        im_start = (ImageButton) findViewById(R.id.img_btn);
        im_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, GameActivity.class));
            }
        });

    }
}
