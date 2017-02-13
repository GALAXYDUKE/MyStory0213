package com.qf.story.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.story.R;
import com.qf.story.entity.NewStory;

import java.util.List;

/**
 * 首页新故事RecyclerView适配器
 * Created by Galaxy on 2017/2/12.
 */

public class NewStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewStory> list;

    public NewStoryAdapter(Context context, List<NewStory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mymainpageitem,null);

        return new NewStoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       NewStoryAdapter.ViewHolder viewHolder = (NewStoryAdapter.ViewHolder) holder;
        viewHolder.txt_newstory.setText(list.get(position).getStory_info());
        viewHolder.txt_nickname.setText(list.get(position).getNickname());
        viewHolder.txt_readCount.setText(list.get(position).getReadCount());
        viewHolder.txt_comment.setText(list.get(position).getComment());
        viewHolder.txt_time.setText(list.get(position).getStory_time());
        if (!(list.get(position).getCity().isEmpty())) {
            viewHolder.txt_city.setText(list.get(position).getCity());
        }
        if (!(list.get(position).getPortrait().isEmpty())){
            viewHolder.img_portrait.setImageBitmap(BitmapFactory.decodeFile(list.get(position).getPortrait()));
        }
        if (list.get(position).getSex().equals("0")) {
            viewHolder.img_sex.setImageResource(R.drawable.icon_woman);
        }else if (list.get(position).getSex().equals("1")) {
            viewHolder.img_sex.setImageResource(R.drawable.icon_man);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_newstory,txt_readCount,txt_comment,txt_time,txt_city,txt_nickname;
        private ImageView img_portrait,img_sex;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_newstory = (TextView) itemView.findViewById(R.id.mainPageTEV);
            txt_readCount = (TextView) itemView.findViewById(R.id.heart_mainPage_counts);
            txt_comment = (TextView) itemView.findViewById(R.id.talk_mainPage_counts);
            txt_time = (TextView) itemView.findViewById(R.id.time_mainPage);
            txt_city = (TextView) itemView.findViewById(R.id.place_mainPage);
            txt_nickname = (TextView) itemView.findViewById(R.id.nickname_mainPage);
            img_portrait = (ImageView) itemView.findViewById(R.id.portrait_mainPage);
            img_sex = (ImageView) itemView.findViewById(R.id.sex_mainPage);
        }

    }
}
