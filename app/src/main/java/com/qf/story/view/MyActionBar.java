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
 * Created by Galaxy on 2017/1/16.
 * 自定义的页面顶部的布局
 */

public class MyActionBar extends LinearLayout implements View.OnClickListener{
    private ImageView imgLeft,imgRight;//布局左右的两张图片
    private TextView tv;//布局中间的文字
    public MyActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof MyABImgClickListener){
            mABICListener = (MyABImgClickListener) context;
        }
        init(context);
        setLongClickable(true);
        imgLeft.setOnClickListener(this);
        imgLeft.setClickable(true);
        imgRight.setOnClickListener(this);
        imgRight.setClickable(true);
    }


    //加载页面
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myactionbar,this);
        findView(view);
    }

    private void findView(View view) {
        imgLeft = (ImageView) view.findViewById(R.id.myActionBarImgLeft);
        tv = (TextView) view.findViewById(R.id.myActionBarTEV);
        imgRight = (ImageView) view.findViewById(R.id.myActionBarImgRight);
    }

    //将布局的左图片显示为你想显示的图片
    public void setImgLeft(int resId){
        imgLeft.setImageResource(resId);
    }
    //将布局的文字显示为你想显示的文字
    public void setText(String text){
        tv.setText(text);
    }
    //将布局的右图片显示为你想显示的图片
    public void setImgRight(int resId){
        imgRight.setImageResource(resId);
    }

    //接口回调，将布局的左图片的点击事件回调
    public interface MyABImgClickListener{
        public void onClick1(View v);
        public void onClick2(View v);
    }

    MyABImgClickListener mABICListener = null;

    //自定义布局里的控件的点击监听
    @Override
    public void onClick(View v) {
        if (v.getId() == imgLeft.getId()) {
            mABICListener.onClick1(v);
        }else if (v.getId()==imgRight.getId()){
            mABICListener.onClick2(v);
        }
    }
}
