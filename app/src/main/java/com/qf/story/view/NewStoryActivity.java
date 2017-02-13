package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.view.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 新的故事的页面
 */
public class NewStoryActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private MyActionBar myActionBar;//自定义的页面顶部信息框
    private MyPhotoButton myPhotoButton_left,myPhotoButton_right;//自定义的页面下方的照片和照相响应布局
    private EditText editText;//故事内容输入
    private String uid,userpass;//用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        init();
    }

    private void init() {
        findView();
        getIntentExtra();
        setMyActionBar(myActionBar);
        setMyPhotoButtonLeft(myPhotoButton_left);
        setMyPhotoButtonRight(myPhotoButton_right);
    }

    //获得上一个页面传过来的值
    private void getIntentExtra() {
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        userpass = intent.getStringExtra("userpass");
    }

    //该页面下方的照相按钮设置图片
    private void setMyPhotoButtonRight(MyPhotoButton myPhotoButton_right) {
        myPhotoButton_right.setIcon(R.drawable.camera);
    }

    //该页面下方的相册按钮设置图片
    private void setMyPhotoButtonLeft(MyPhotoButton myPhotoButton_left) {
        myPhotoButton_left.setIcon(R.drawable.pic);
    }

    //设置自定义布局
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("新的故事");
        myActionBar.setImgRight(R.drawable.icon_send);
    }

    //找控件
    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_newStory);
        myPhotoButton_left = (MyPhotoButton) findViewById(R.id.myPhotoButton_left);
        myPhotoButton_right = (MyPhotoButton) findViewById(R.id.myPhotoButton_right);
        editText = (EditText) findViewById(R.id.newStoryEDT);
    }


    //返回键
    @Override
    public void onClick1(View v) {
        finish();
    }

    //发表新故事，提交给服务器
    @Override
    public void onClick2(View v) {
        Log.i("info","=======点击了发表");
        String content = editText.getText().toString().trim();
        double lng =  Math.random()*180;
        if (lng == 0){
            lng =  Math.random()*180;
        }
        double lat =  Math.random()*180;
        if (lat == 0){
            lat =  Math.random()*180;
        }
        if (!content.isEmpty()) {
            OkHttpUtils.post().url(Util.sendStory)
                    .addParams("uid", uid)
                    .addParams("story_info", content)
                    .addParams("photo[]", "http://c.hiphotos.baidu.com/zhidao/pic/item/6a600c338744ebf8f8b23843d9f9d72a6159a78f.jpg")
                    .addParams("userpass", userpass)
                    .addParams("lng", String.valueOf(lng))
                    .addParams("lat", String.valueOf(lat))
                    .addParams("city", "Galaxy")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(NewStoryActivity.this, "服务器故障或没有网络", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result == 1) {
                                    Log.i("info", "=======" + response);
                                    Toast.makeText(NewStoryActivity.this, "发表故事成功", Toast.LENGTH_SHORT).show();
                                    editText.setText("");
                                } else if (result == 0) {
                                    Toast.makeText(NewStoryActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }else {
            Toast.makeText(NewStoryActivity.this,"请输入你要发表的故事内容",Toast.LENGTH_SHORT).show();
        }
    }

}
