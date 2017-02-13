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
 * 修改密码的页面
 */
public class ModifyPasswordActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private MyActionBar myActionBar;//自定义页面顶部信息栏
    private EditText previousPasswordEdit,newPasswordEdit,repeatedPasswordEdit;//原密码，新密码，重复密码的文本输入框
    private String uid;//用户序号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
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
    }

    //设置自定义顶部框的文本和图片
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("修改密码");
    }

    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_password);
        previousPasswordEdit = (EditText) findViewById(R.id.previousPasswordEDT);
        newPasswordEdit = (EditText) findViewById(R.id.newPasswordEDT);
        repeatedPasswordEdit = (EditText) findViewById(R.id.repeatedPasswordEDT);
    }


    //点击按钮，将页面跳转回个人中心
    public void btnModifyPassword1(View v){
        Intent intent = getIntent();
        String password = intent.getStringExtra("password");
        String previousPassword = previousPasswordEdit.getText().toString().trim();
        String newPassword = newPasswordEdit.getText().toString().trim();
        String repeatedPassword = repeatedPasswordEdit.getText().toString().trim();
        if (!password.equals(previousPassword)){
            Toast.makeText(ModifyPasswordActivity.this,"输入的原密码不正确，请输入正确的原密码",Toast.LENGTH_SHORT).show();
            previousPasswordEdit.setText("");
            newPasswordEdit.setText("");
            repeatedPasswordEdit.setText("");
        }else if (!newPassword.equals(repeatedPassword)){
            Toast.makeText(ModifyPasswordActivity.this,"重复新密码和新密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
            newPasswordEdit.setText("");
            repeatedPasswordEdit.setText("");
        }else if (password.equals(newPassword)||password.equals(repeatedPassword)){
            Toast.makeText(ModifyPasswordActivity.this,"新密码不能和原密码一样，请重新输入新密码",Toast.LENGTH_SHORT).show();
            previousPasswordEdit.setText("");
            newPasswordEdit.setText("");
            repeatedPasswordEdit.setText("");
        }else if (newPassword.length()>10){
            Toast.makeText(ModifyPasswordActivity.this,"输入的新密码太长，请输入小于10位的新密码",Toast.LENGTH_SHORT).show();
            previousPasswordEdit.setText("");
            newPasswordEdit.setText("");
            repeatedPasswordEdit.setText("");
        }else {
                OkHttpUtils.post().url(Util.changePassword)
                        .addParams("uid", uid)
                        .addParams("oldpass", previousPassword)
                        .addParams("newpass", newPassword)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Toast.makeText(ModifyPasswordActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("info", "=====" + response);
                                JSONObject jsonObject;
                                try {
                                    jsonObject = new JSONObject(response);
                                    int result = jsonObject.getInt("result");
                                    if (result == 1) {
                                        Toast.makeText(ModifyPasswordActivity.this, "修改密码成功，请重新登录", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ModifyPasswordActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    } else if (result == 0) {
                                        Toast.makeText(ModifyPasswordActivity.this, "修改密码失败", Toast.LENGTH_SHORT).show();
                                        previousPasswordEdit.setText("");
                                        newPasswordEdit.setText("");
                                        repeatedPasswordEdit.setText("");
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
