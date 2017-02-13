package com.qf.story.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.story.R;
import com.qf.story.entity.HotStory;

import java.util.List;

/**
 * 首页最热故事RecyclerView适配器
 * Created by Galaxy on 2017/2/12.
 */

public class HotStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HotStory> list;

    public HotStoryAdapter(Context context, List<HotStory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mymainpageitem,null);

        return new HotStoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotStoryAdapter.ViewHolder viewHolder = (HotStoryAdapter.ViewHolder) holder;


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);

        }

    }

}
