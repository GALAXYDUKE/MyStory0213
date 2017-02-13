package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.view.entity.MyStory;
import com.qf.story.view.utils.MyStoryAdapter;
import com.qf.story.view.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的故事的页面
 */
public class MyStoryActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener,View.OnClickListener{
    private MyActionBar myActionBar;//自定义页面顶部的信息框
    private String sex,nickname,signature;//用户性别和昵称
    private ImageView img_sex;//显示用户性别的图片
    private TextView txt_nickname;//显示用户昵称
    private EditText edt_signature;//修改用户签名
    private RelativeLayout relativeLayout;//图片父布局
    private List<MyStory> list = new ArrayList<>();//我的故事的数据
    private MyStory mystory;//我的故事实体
    private RecyclerView recyclerView;//显示我的故事的控件
    private MyStoryAdapter adapter;//我的故事的适配器
    private String uid,userpass;//用户序号，用户通行证

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_story);
        init();
    }

    //刷新界面
    @Override
    protected void onResume() {
        super.onResume();
        getMyStorys();
    }

    private void init() {
        findView();
        getIntentExtra();
        setMyActionBar(myActionBar);
        setRecyclerView();
        setOnClickListener();
    }

    //获得上一个页面传过来的值
    private void getIntentExtra() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        userpass = intent.getStringExtra("userpass");
    }

    //设置RecyclerView
    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MyStoryActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter = new MyStoryAdapter(MyStoryActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    //绑定监听
    private void setOnClickListener() {
        edt_signature.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        myActionBar.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
    }

    //修改签名并提交给服务器
    private void modifySignature(String s) {
        if (s.length() < 30) {
            OkHttpUtils.post().url(Util.changeSignature)
                    .addParams("uid", uid)
                    .addParams("userpass", userpass)
                    .addParams("signature", s)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result == 1) {
                                    Toast.makeText(MyStoryActivity.this, "修改签名成功", Toast.LENGTH_SHORT).show();
                                    getMyStorys();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            Toast.makeText(MyStoryActivity.this, "输入的签名的太长，请输入25个以内的", Toast.LENGTH_SHORT).show();
            edt_signature.setText("");
        }
    }

    //该页面自定义顶部框设置
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("我的故事");
        myActionBar.setImgRight(R.drawable.icon_edit);
    }

    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_myStory);
        img_sex = (ImageView) findViewById(R.id.myStorySexImg);
        txt_nickname = (TextView) findViewById(R.id.myStoryTEV);
        edt_signature = (EditText) findViewById(R.id.myStoryLayout_topEDT);
        relativeLayout = (RelativeLayout) findViewById(R.id.myStoryLayout_top);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }
    //返回键
    @Override
    public void onClick1(View v) {
        finish();
    }

    //发表新故事
    @Override
    public void onClick2(View v) {
        Intent intent = new Intent(this,NewStoryActivity.class);
        intent.putExtra("uid",uid);
        intent.putExtra("userpass",userpass);
        startActivity(intent);
    }
    //点击就输入修改的签名
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.myStoryLayout_topEDT) {
            edt_signature.setCursorVisible(true);
            edt_signature.setText("");
            edt_signature.setHint("请输入签名");
        }else {
            edt_signature.setCursorVisible(false);
            String s = edt_signature.getText().toString();
            modifySignature(s);
        }
    }

    public void getMyStorys() {
        OkHttpUtils.post().url(Util.myStorys)
                .addParams("uid",uid)
                .addParams("page","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("info",response.trim());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("result");
                            if (result==1) {
                                JSONObject object = jsonObject.getJSONObject("user");
                                sex = object.getString("usersex");
                                nickname = object.getString("nickname");
                                signature = object.getString("signature");
                                if (!signature.isEmpty()){
                                    edt_signature.setText(signature);
                                }else {
                                    edt_signature.setText("");
                                }
                                txt_nickname.setText(nickname);
                                if (sex.equals("0")) {
                                    img_sex.setImageResource(R.drawable.icon_woman);
                                } else if (sex.equals("1")) {
                                    img_sex.setImageResource(R.drawable.icon_man);
                                }

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i <jsonArray.length(); i++) {
                                    JSONObject object1 = jsonArray.getJSONObject(i);
                                    mystory = new MyStory();
                                    mystory.setStory_info(object1.getString("story_info"));
                                    mystory.setId(object1.getString("id"));
                                    mystory.setStory_time(object1.getString("story_time"));
                                    mystory.setReadCount(object1.getString("readcount"));
                                    mystory.setComment(object1.getString("comment"));
                                    if (i == 0) {
                                        list.add(mystory);
                                    } else {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (list.get(j).getId().equals(mystory.getId())) {//不添加已经获得的故事

                                            }else {
                                                list.add(mystory);
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
