package com.qf.story.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.story.R;

/**
 * Created by Galaxy on 2017/1/17.
 * 自定义的显示每个故事详情的布局
 */

public class MyItem extends RelativeLayout {
    private TextView tv_storyDetails,talk_counts,hearts_counts;//详情的内容，讨论数，点赞数
    private ImageView img_storyDetails;//详情配的图片
    public MyItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myitem,this);
        findView(view);
    }
    //设置详情文字，图片，评论数，点赞数
    private void setStroyDetailsText(String text) {
        tv_storyDetails.setText(text);
    }

    private void setStroyDetailsImg(int resId) {
        img_storyDetails.setImageResource(resId);
    }

    private void setStroyDetailsTalk(int talkCounts) {
        talk_counts.setText(talkCounts);
    }

    private void setStroyDetailsHeart(int heartsCounts) {
        hearts_counts.setText(heartsCounts);
    }

    private void findView(View view) {
        tv_storyDetails = (TextView) view.findViewById(R.id.storyDetailsTEV);
        talk_counts = (TextView) view.findViewById(R.id.talk_storyDetails_counts);
        hearts_counts = (TextView) view.findViewById(R.id.heart_storyDetails_counts);
        img_storyDetails = (ImageView) view.findViewById(R.id.storyDetailsImg);
    }
}
