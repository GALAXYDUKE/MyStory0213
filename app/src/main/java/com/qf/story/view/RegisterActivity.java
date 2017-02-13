package com.qf.story.view;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener{
    private EditText uesrnameEdit,passwordEdit,nicknameEdit;//输入帐号，密码，昵称的三个控件
    private MyActionBar myActionBar;//自定义的顶部显示框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        findView();
        setMyActionBar(myActionBar);
    }
    //找控件
    private void findView() {
        uesrnameEdit = (EditText) findViewById(R.id.registerUsernameEDT);
        nicknameEdit = (EditText) findViewById(R.id.registerNicknameEDT);
        passwordEdit = (EditText) findViewById(R.id.registerPasswordEDT);
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_register);
    }

    //设置自定义顶部显示框的图片和文字
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("用户注册");
    }

    //提交注册信息给服务器，成功就将页面跳转到主页面
    public void btnRegister1(View v){
        String nickname = nicknameEdit.getText().toString().trim();
        String username = uesrnameEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,12}");
        Matcher matcher = pattern.matcher(username);
        Matcher matcher1 = pattern.matcher(password);
        if(username.isEmpty()){
            Toast.makeText(RegisterActivity.this,"帐号不能为空",Toast.LENGTH_SHORT).show();
            passwordEdit.setText("");
        }else if (!matcher.find()){
            Toast.makeText(RegisterActivity.this, "帐号只能输入长度为6~12的小写字母，大写字母和数字", Toast.LENGTH_SHORT).show();
            uesrnameEdit.setText("");
            passwordEdit.setText("");
        }else if(nickname.isEmpty()){
            Toast.makeText(RegisterActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
        }else if (nickname.length()>16){
            Toast.makeText(RegisterActivity.this, "昵称最多只能输入16个字符，请重新输入", Toast.LENGTH_SHORT).show();
            nicknameEdit.setText("");
        } else if(password.isEmpty()){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }else if (!matcher1.find()){
            Toast.makeText(RegisterActivity.this, "密码只能输入长度为6~12的小写字母，大写字母和数字", Toast.LENGTH_SHORT).show();
            uesrnameEdit.setText("");
            passwordEdit.setText("");
        }else {
            OkHttpUtils.post().url(Util.register).addParams("nikename", nickname)
                    .addParams("username", username)
                    .addParams("password", password)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("error", "=====" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("info", "==========" + response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result == 1) {
                                    Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (result == 0) {
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    uesrnameEdit.setText("");
                                    passwordEdit.setText("");
                                    nicknameEdit.setText("");
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
