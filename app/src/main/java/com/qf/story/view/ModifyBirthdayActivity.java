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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

/**
 * Created by Galaxy on 2017/1/16.
 * 修改生日页面
 */

public class ModifyBirthdayActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private EditText editText;//修改生日的文本框
    private TextView textView;//提示修改的内容
    private MyActionBar myActionBar;//自定义的顶部显示框
    private String uid,userpass;//用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_birthday);
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
        editText = (EditText) findViewById(R.id.modifyBirthdayEDT);
        textView = (TextView) findViewById(R.id.modifyBirthdayTEV);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_birthday);
    }
    //设置自定义顶部显示框的图片和文字
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("修改生日");
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
    //修改生日并提交给服务器
    public void btnModify(View v){
        String birthday = editText.getText().toString().trim();
        String pattern_birthday = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$";
        Pattern pattern = Pattern.compile(pattern_birthday);
        Matcher matcher = pattern.matcher(birthday);
        if (matcher.find()) {
            OkHttpUtils.post().url(Util.changeBirthday)
                    .addParams("uid", uid)
                    .addParams("userpass", userpass)
                    .addParams("birthday", birthday)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ModifyBirthdayActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("info", "=====" + response);
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result == 1) {
                                    Toast.makeText(ModifyBirthdayActivity.this, "修改生日成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (result == 0) {
                                    Toast.makeText(ModifyBirthdayActivity.this, "修改生日失败", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }else {
            Toast.makeText(ModifyBirthdayActivity.this,"生日格式不正确，请重新输入，如：19XX-XX-XX",Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
    }
}
