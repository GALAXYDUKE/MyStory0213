package com.qf.story.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
 * 登录页面
 *
 */
public class LoginActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private EditText usernameEdit,passwordEdit;//输入帐号，密码的控件
    private MyActionBar myActionBar;//自定义的顶部显示框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        findView();
        setMyActionBar(myActionBar);
    }


    //找到登录帐号和登录密码的控件
    private void findView() {
        usernameEdit = (EditText) findViewById(R.id.loginUsernameEDT);
        passwordEdit = (EditText) findViewById(R.id.loginPasswordEDT);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_login);
    }

    //设置自定义顶部显示框的图片和文字
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("用户登录");
    }

    //点击注册按钮，跳转页面到注册页面
    public void btnRegister(View v){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    //点击登录按钮，跳转页面到主页面
    public void btnLogin(View v){
        String username = usernameEdit.getText().toString().trim();
        final String password = passwordEdit.getText().toString().trim();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,12}");
        Matcher matcher = pattern.matcher(username);
        Matcher matcher1 = pattern.matcher(password);
        if (username.isEmpty()){
            Toast.makeText(LoginActivity.this,"帐号不能为空",Toast.LENGTH_SHORT).show();
            passwordEdit.setText("");
        }else if(!matcher.find()){
            Toast.makeText(LoginActivity.this,"帐号只能输入长度为6~12的小写字母，大写字母和数字",Toast.LENGTH_SHORT).show();
            usernameEdit.setText("");
            passwordEdit.setText("");
        }else if(password.isEmpty()){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }else if(!matcher1.find()){
            Toast.makeText(LoginActivity.this,"密码只能输入长度为6~12的小写字母，大写字母和数字",Toast.LENGTH_SHORT).show();
            usernameEdit.setText("");
            passwordEdit.setText("");
        }else {
            OkHttpUtils.post().url(Util.login)
                            .addParams("username",username)
                            .addParams("password",password)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(LoginActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int result = jsonObject.getInt("result");
                                        if (result==1){
                                            JSONObject object = jsonObject.getJSONObject("data");
                                            String username = object.getString("username");
                                            String uid = object.getString("id");
                                            String userpass = object.getString("userpass");
                                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                                            intent.putExtra("username",username);
                                            intent.putExtra("password",password);
                                            intent.putExtra("uid",uid);
                                            intent.putExtra("userpass",userpass);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(LoginActivity.this,"登录失败，请检查帐号或密码是否正确",Toast.LENGTH_SHORT).show();
                                            usernameEdit.setText("");
                                            passwordEdit.setText("");
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

        }
    }

    //退出键
    @Override
    public void onClick1(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("主人，你要离开我了吗？")
                .setIcon(R.drawable.cry)
                .setPositiveButton("狠心离开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("不离不弃", null)
                .setNeutralButton("我考虑一下",null);
        builder.create().show();
    }

    //该页面不需要执行
    @Override
    public void onClick2(View v) {

    }
}
