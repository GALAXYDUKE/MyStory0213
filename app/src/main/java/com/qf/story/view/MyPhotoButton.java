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
 * 自定义页面下方照相，照片的布局
 */

public class MyPhotoButton extends RelativeLayout{
    private ImageView img;//设置图标的控件
    public MyPhotoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myphotobutton,this);
        findView(view);
    }

    //找控件
    private void findView(View view) {
        img = (ImageView) view.findViewById(R.id.photoButtonImg);
    }
    //自定义图标
    public void setIcon(int resId){
        img.setImageResource(resId);
    }
}
