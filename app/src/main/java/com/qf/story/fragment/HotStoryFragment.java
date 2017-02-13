package com.qf.story.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.story.R;
import com.qf.story.utils.HotStoryAdapter;

/**
 * 主页的最热说说显示碎片
 * Created by Galaxy on 2017/2/12.
 */

public class HotStoryFragment extends Fragment {
    private RecyclerView recyclerView;//显示说说的控件
    private HotStoryAdapter adapter;//适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;
    }
}
