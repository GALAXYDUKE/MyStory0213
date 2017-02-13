package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.qf.story.R;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnTouchListener{
    private RelativeLayout relativeLayout;
    private int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        findView();
        setOnClick();
        startThread();
    }

    //自动跳转
    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if(flag==1){
                    Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //绑定监听
    private void setOnClick() {
        relativeLayout.setOnTouchListener(this);
    }
    //找控件
    private void findView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_welcome);
    }

    //用户点击就直接跳转，否则执行自动跳转
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId()==relativeLayout.getId()) {
            flag=2;
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return false;
    }
}
