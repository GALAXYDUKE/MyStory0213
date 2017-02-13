package com.qf.story.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qf.story.R;

/**
 * Created by Galaxy on 2017/1/17.
 * 自定义的页面下方的综合输入框
 */

public class MyInputBox extends RelativeLayout{
    private ImageView imgLeft,imgRight;//表情包和发送输入的文字的控件
    public MyInputBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myinputbox,this);
        findView(view);
    }

    private void findView(View view) {
        imgLeft = (ImageView) view.findViewById(R.id.inputBoxImg_left);
        imgRight = (ImageView) view.findViewById(R.id.inputBoxImg_right);
    }


}
