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
 * 修改邮箱页面
 */
public class ModifyEmailActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private EditText editText;//修改邮箱的文本框
    private TextView textView;//提示修改的内容
    private MyActionBar myActionBar;//自定义的顶部显示框
    private String uid,userpass;//用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_email);
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
        editText = (EditText) findViewById(R.id.modifyEmailEDT);
        textView = (TextView) findViewById(R.id.modifyEmailTEV);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_email);
    }

    //设置自定义顶部显示框的图片和文字
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("修改邮箱");
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
    //修改邮箱并提交给服务器
    public void btnModify(View v){
        String email = editText.getText().toString().trim();
        String pattern_email = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(pattern_email);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            OkHttpUtils.post().url(Util.changeEmail)
                    .addParams("uid", uid)
                    .addParams("userpass", userpass)
                    .addParams("useremail", email)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ModifyEmailActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("info", "=====" + response);
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result == 1) {
                                    Toast.makeText(ModifyEmailActivity.this, "修改邮箱成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (result == 0) {
                                    Toast.makeText(ModifyEmailActivity.this, "修改邮箱失败", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }else {
            Toast.makeText(ModifyEmailActivity.this,"邮箱格式不正确，请重新输入，如：XXX@XXX.com",Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
    }
}

