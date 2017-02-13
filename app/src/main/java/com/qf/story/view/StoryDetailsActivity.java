package com.qf.story.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qf.story.R;

/**
 * 故事详情布局
 */
public class StoryDetailsActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private MyActionBar myActionBar;//自定义顶部信息框
    private MyItem myItem;//显示故事详情的布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);
        init();
    }

    private void init() {
        findView();
        setMyActionBar();
    }
    //自定义顶部布局的设置
    private void setMyActionBar() {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("故事详情");
        myActionBar.setImgRight(R.drawable.icon_dot);
    }
    //找控件
    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_storyDetails);
        myItem = (MyItem) findViewById(R.id.myItem);
    }
    //返回键
    @Override
    public void onClick1(View v) {
        finish();
    }
    //该页面不需要执行
    @Override
    public void onClick2(View v) {

    }
}
