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
 * 自定义的ListView每个Item的显示布局
 */

public class MyRecyclerViewItem extends RelativeLayout {
    private ImageView shareImg;//分享的响应控件,爱心
    public MyRecyclerViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_recyclerview_item,this);
        findView(view);
    }

    private void findView(View view) {
        shareImg = (ImageView) view.findViewById(R.id.share_myStory);

    }

    //自动设置时间，故事主体

}
