package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.view.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 修改性别的页面
 */
public class ModifySexActivity extends AppCompatActivity implements View.OnClickListener,MyActionBar.MyABImgClickListener{
    private MyActionBar myActionBar;//自定义的页面顶部栏
    private MyViewGroup myViewGroup1,myViewGroup2;//自定义的信息栏
    private String uid,userpass;//用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_sex);
        init();
    }

    private void init() {
        findView();
        setOnClick();
        setMyActionBar(myActionBar);
        setViewGroup1(myViewGroup1);
        setViewGroup2(myViewGroup2);
        getIntentExtra();
    }

    //获得上个页面传过来的值
    private void getIntentExtra() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        userpass = intent.getStringExtra("userpass");
    }

    //绑定监听
    private void setOnClick() {
        myViewGroup1.setOnClickListener(this);
        myViewGroup2.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_sex);
        myViewGroup1 = (MyViewGroup) findViewById(R.id.myViewGroup_sex1);
        myViewGroup2 = (MyViewGroup) findViewById(R.id.myViewGroup_sex2);
    }
    //以下都是对自定义布局设置文字和图片
    private void setViewGroup2(MyViewGroup myViewGroup2) {
        myViewGroup2.setText1("女");
        myViewGroup2.setImg2(R.drawable.icon_confirm);
        myViewGroup1.setImgVisibilityGone();
    }

    //以下是对自定义布局的设置
    private void setViewGroup1(MyViewGroup myViewGroup1) {
        myViewGroup1.setText1("男");
        myViewGroup1.setImg2(R.drawable.icon_confirm);
        myViewGroup2.setImgVisibilityGone();
    }

    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("修改性别");
    }

    //修改性别并提交给服务器
    @Override
    public void onClick(View v) {
        if(myViewGroup1.getId()==v.getId()){
            final String usersex ="1";
            myViewGroup2.setImgVisibilityGone();
            myViewGroup1.setImgVisibilityVisible();
            OkHttpUtils.post().url(Util.changeSex)
                            .addParams("uid",uid)
                            .addParams("userpass",userpass)
                            .addParams("usersex",usersex)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(ModifySexActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.i("info","====="+response);
                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int result = jsonObject.getInt("result");
                                        if (result==1){
                                            Toast.makeText(ModifySexActivity.this,"修改性别成功",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else if (result==0){
                                            Toast.makeText(ModifySexActivity.this,"修改性别失败",Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
        }else if (myViewGroup2.getId()==v.getId()){
            String usersex = "0";
            myViewGroup1.setImgVisibilityGone();
            myViewGroup2.setImgVisibilityVisible();
            OkHttpUtils.post().url(Util.changeSex)
                    .addParams("uid",uid)
                    .addParams("userpass",userpass)
                    .addParams("usersex",usersex)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ModifySexActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("info","====="+response);
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result==1){
                                    Toast.makeText(ModifySexActivity.this,"修改性别成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else if (result==0){
                                    Toast.makeText(ModifySexActivity.this,"修改性别失败",Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }
    }

    //返回键
    @Override
    public void onClick1(View v) {
        finish();
    }

    //该页面不需要执行
    @Override
    public void onClick2(View v) {

    }
}
