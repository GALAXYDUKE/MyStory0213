package com.qf.story.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.story.R;

/**
 * Created by Galaxy on 2017/1/18.
 * 自定义的主页每个动态的显示布局
 */

public class MyMainPageItem extends RelativeLayout{
    private ImageView portrait,sex;//用户头像,性别
    private TextView nickname,time,place,content;//发动态的对象的昵称，时间，地址
    public MyMainPageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_mymainpageitem,this);
        findView(view);
    }

    private void findView(View view) {
        portrait = (ImageView) view.findViewById(R.id.portrait_mainPage);
        sex = (ImageView) view.findViewById(R.id.sex_mainPage);
        nickname = (TextView) view.findViewById(R.id.nickname_mainPage);
        time = (TextView) view.findViewById(R.id.time_mainPage);
        place = (TextView) view.findViewById(R.id.place_mainPage);
        content = (TextView) view.findViewById(R.id.mainPageTEV);
    }

    public void setContent(String s){
        content.setText(s);
    }
}
