package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Galaxy on 2017/1/16.
 * 修改昵称页面
 */
public class ModifyNicknameActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private EditText editText;//修改昵称文本框
    private TextView textView;//提示修改的内容
    private MyActionBar myActionBar;//自定义的顶部显示框
    private String uid,userpass;//用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nickname);
        init();
    }

    private void init() {
        findView();
        getIntentExtra();
        setMyActionBar(myActionBar);
    }

    //获得上个页面传过来的值
    private void getIntentExtra() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        userpass = intent.getStringExtra("userpass");
    }

    //找到该页面的所有控件
    private void findView() {
        editText = (EditText) findViewById(R.id.modifyNicknameEDT);
        textView = (TextView) findViewById(R.id.modifyNicknameTEV);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_nickname);
    }

    //设置自定义顶部显示框的图片和文字
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("修改昵称");
    }

    //返回键
    @Override
    public void onClick1(View v) {
        finish();
    }

    //该页面不要执行
    @Override
    public void onClick2(View v) {

    }
    //修改昵称并提交给服务器
    public void btnModify(View v){
        String nickname = editText.getText().toString().trim();
        OkHttpUtils.post().url(Util.changeNickname)
                .addParams("uid",uid)
                .addParams("userpass",userpass)
                .addParams("nickname",nickname)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ModifyNicknameActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("info","====="+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("result");
                            if (result==1){
                                Toast.makeText(ModifyNicknameActivity.this,"修改昵称成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else if (result==0){
                                Toast.makeText(ModifyNicknameActivity.this,"修改昵称失败",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
