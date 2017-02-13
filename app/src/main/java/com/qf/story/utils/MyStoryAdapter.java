package com.qf.story.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.story.R;
import com.qf.story.entity.MyStory;

import java.util.List;

/**
 * 我的故事页面RecyclerView适配器
 * Created by Galaxy on 2017/2/10.
 */

public class MyStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<MyStory> list;

    public MyStoryAdapter(Context context, List<MyStory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_item,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.txt_comment.setText(list.get(position).getComment());
        viewHolder.txt_readCount.setText(list.get(position).getReadCount());
        viewHolder.txt_mystory.setText(list.get(position).getStory_info());
        viewHolder.txt_time.setText(list.get(position).getStory_time());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_mystory,txt_readCount,txt_comment,txt_time;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_mystory = (TextView) itemView.findViewById(R.id.myStoryTEV);
            txt_readCount = (TextView) itemView.findViewById(R.id.heart_myStory_counts);
            txt_comment = (TextView) itemView.findViewById(R.id.talk_myStory_counts);
            txt_time = (TextView) itemView.findViewById(R.id.time_myStory);
        }

    }
}
