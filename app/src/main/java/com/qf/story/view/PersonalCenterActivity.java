package com.qf.story.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * 个人中心页面,实时请求服务器
 */
public class PersonalCenterActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener,View.OnClickListener{
    private MyActionBar myActionBar;//自定义页面顶部栏
    private MyViewGroup myViewGroup1,myViewGroup2,myViewGroup3,myViewGroup4,myViewGroup5;//自定义信息栏，从上到下数字依次增大
    private String email,sex,birthday,username,nickname,password,uid,userpass;//用户邮箱，性别，生日，帐号，昵称，密码，用户序号，用户通行证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        init();
    }
    //刷新页面
    protected void onResume() {
        super.onResume();
        getServerInfo();
    }

    private void init() {
        findView();
        setMyActionBar(myActionBar);
        getIntentExtraInfo();
        setMyViewGroup1(myViewGroup1);
        setMyViewGroup2(myViewGroup2);
        setMyViewGroup3(myViewGroup3);
        setMyViewGroup4(myViewGroup4);
        setMyViewGroup5(myViewGroup5);
        setOnClickListener();
    }

    //获得用户帐号和密码
    private void getIntentExtraInfo() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        uid = intent.getStringExtra("uid");
        userpass = intent.getStringExtra("userpass");
    }

    //请求服务器获得用户信息
    private void getServerInfo() {
        OkHttpUtils.post().url(Util.login)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(PersonalCenterActivity.this,"请退出后重新登录",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("result");
                            if (result==1) {
                                JSONObject object = jsonObject.getJSONObject("data");
                                sex = object.getString("usersex");
                                email = object.getString("useremail");
                                birthday = object.getString("birthday");
                                nickname = object.getString("nickname");
                                myViewGroup2.setText2(nickname);
                                if (sex.equals("0")) {
                                    myViewGroup3.setText2("女");
                                    myViewGroup3.setImg1(R.drawable.icon_woman);
                                } else if (sex.equals("1")) {
                                    myViewGroup3.setText2("男");
                                    myViewGroup3.setImg1(R.drawable.icon_man);
                                }
                                if (email.equals("null")){
                                    myViewGroup4.setText2("");
                                }else {
                                    myViewGroup4.setText2(email);
                                }if (birthday.equals("null")){
                                    myViewGroup5.setText2("");
                                }else {
                                    myViewGroup5.setText2(birthday);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //绑定监听
    private void setOnClickListener() {
        myViewGroup2.setOnClickListener(this);
        myViewGroup3.setOnClickListener(this);
        myViewGroup4.setOnClickListener(this);
        myViewGroup5.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_PC);
        myViewGroup1 = (MyViewGroup) findViewById(R.id.myViewGroup_PC1);
        myViewGroup2 = (MyViewGroup) findViewById(R.id.myViewGroup_PC2);
        myViewGroup3 = (MyViewGroup) findViewById(R.id.myViewGroup_PC3);
        myViewGroup4 = (MyViewGroup) findViewById(R.id.myViewGroup_PC4);
        myViewGroup5 = (MyViewGroup) findViewById(R.id.myViewGroup_PC5);
    }
    //以下都是对自定义布局设置文字和图片
    public void setMyViewGroup5(MyViewGroup myViewGroup5) {
        myViewGroup5.setText1("生 日：");
        myViewGroup5.setImg2(R.drawable.right_arrow);

    }

    public void setMyViewGroup4(MyViewGroup myViewGroup4) {
        myViewGroup4.setText1("邮 箱：");
        myViewGroup4.setImg2(R.drawable.right_arrow);
    }

    public void setMyViewGroup3(MyViewGroup myViewGroup3) {
        myViewGroup3.setText1("性 别：");
        myViewGroup3.setImg2(R.drawable.right_arrow);
    }

    public void setMyViewGroup2(MyViewGroup myViewGroup2) {
        myViewGroup2.setText1("昵 称：");
        myViewGroup2.setImg2(R.drawable.right_arrow);
    }

    public void setMyViewGroup1(MyViewGroup myViewGroup1) {
        myViewGroup1.setText1("帐 号：");
        myViewGroup1.setText2(username);

    }

    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("个人中心");
    }

    //点击按钮，跳转到修改密码的页面
    public void btnModifyPassword(View v){
        Intent intent = new Intent(this,ModifyPasswordActivity.class);
        intent.putExtra("password",password);
        intent.putExtra("uid",uid);
        startActivity(intent);
    }

    //顶部返回键
    @Override
    public void onClick1(View v) {
        finish();
    }

    //该页面不需要执行
    @Override
    public void onClick2(View v) {

    }

    //每个信息的点击事件
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.myViewGroup_PC2:
                intent = new Intent(PersonalCenterActivity.this,ModifyNicknameActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("userpass",userpass);
                startActivity(intent);
                break;
            case R.id.myViewGroup_PC3:
                intent = new Intent(PersonalCenterActivity.this,ModifySexActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("userpass",userpass);
                startActivity(intent);
                break;
            case R.id.myViewGroup_PC4:
                intent = new Intent(PersonalCenterActivity.this,ModifyEmailActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("userpass",userpass);
                startActivity(intent);
                break;
            case R.id.myViewGroup_PC5:
                intent = new Intent(PersonalCenterActivity.this,ModifyBirthdayActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("userpass",userpass);
                startActivity(intent);
                break;
        }
    }

}
