package com.qf.story.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.story.R;

/**
 * Created by Galaxy on 2017/1/17.
 * 自定义的布局，用来显示个人中心的每个信息条
 */

public class MyViewGroup extends LinearLayout {
    TextView tv1,tv2;//显示信息的属性
    ImageView img1,img2;//性别图片，右键头
    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myviewgroup,this);
        findView(view);
    }
    //找控件
    private void findView(View view) {
        tv1= (TextView) findViewById(R.id.myViewGroupTEV1);
        img1 = (ImageView) findViewById(R.id.myViewGroupImg1);
        tv2= (TextView) findViewById(R.id.myViewGroupTEV2);
        img2 = (ImageView) findViewById(R.id.myViewGroupImg2);
    }
    //设置文字
    public void setText1(String text){
        tv1.setText(text);
    }
    //设置图片
    public void setImg1(int resId){
        img1.setImageResource(resId);
    }

    //设置文字
    public void setText2(String text){
        tv2.setText(text);
    }
    //设置图片
    public void setImg2(int resId){
        img2.setImageResource(resId);
    }
    //设置图片隐藏
    public void setImgVisibilityGone(){
        img2.setVisibility(View.GONE);
    }
    //设置图片显示
    public void setImgVisibilityVisible(){
        img2.setVisibility(View.VISIBLE);
    }

}
