package com.qf.story.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.story.R;
import com.qf.story.view.entity.NewStory;
import com.qf.story.view.utils.NewStoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页的最新说说显示碎片
 * Created by Galaxy on 2017/2/12.
 */

public class NewStoryFragment extends Fragment {
    private RecyclerView recyclerView;//显示说说的控件
    private NewStoryAdapter adapter;//适配器
    private List<NewStory> list;//新说说数据源

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        Bundle bundle = getArguments();
        ArrayList<Parcelable> arrayList = bundle.getParcelableArrayList("list");
        list = (List<NewStory>) arrayList.get(0);
        adapter = new NewStoryAdapter(getActivity().getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
