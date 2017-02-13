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
 * 自定义的评论区的显示布局
 */

public class MyTalk extends RelativeLayout {
    private TextView tv_talk,tv_nickname;//评论区的用户昵称，用户的评论的文本框
    private ImageView img_talk;//用户的头像
    public MyTalk(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_mytalk,this);
        findView(view);
    }

    private void findView(View view) {
        tv_talk = (TextView) view.findViewById(R.id.talkTEV);
        tv_nickname = (TextView) view.findViewById(R.id.nickname_talk);
        img_talk = (ImageView) view.findViewById(R.id.potrait_talk);
    }

    //以下全是设置自定义布局的具体文本和图片
    public void setTalk(String text){
        tv_talk.setText(text);
    }

    public void setNickname(String nickname){
        tv_nickname.setText(nickname);
    }

    public void setImg(int resId){
        img_talk.setImageResource(resId);
    }


}
