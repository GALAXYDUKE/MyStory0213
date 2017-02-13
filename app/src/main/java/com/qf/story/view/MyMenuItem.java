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
 * Created by Galaxy on 2017/1/17.
 * 滑页里每个选项的自定义布局
 */

public class MyMenuItem extends RelativeLayout{
    private ImageView img;//滑页里单项菜单图标
    private TextView tv;//滑页里单项菜单文字
    public MyMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_mymenuitem,this);
        findView(view);
    }

    private void findView(View view) {
        img = (ImageView) view.findViewById(R.id.myMenuItemImg);
        tv = (TextView) view.findViewById(R.id.myMenuItemTEV);
    }

    //设置图标
    public void setIcon(int resId){
        img.setImageResource(resId);
    }

    //设置文字
    public void setText(String text){
        tv.setText(text);
    }

}
