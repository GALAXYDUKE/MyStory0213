package com.qf.story.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qf.story.R;

/**
 * 系统设置的页面
 */
public class SettingActivity extends AppCompatActivity implements MyActionBar.MyABImgClickListener,View.OnClickListener{
    private MyActionBar myActionBar;//自定义页面顶部信息框
    private MyViewGroup myViewGroup1,myViewGroup2,myViewGroup3,myViewGroup4,myViewGroup5,myViewGroup6;//自定义内容信息栏,由页面从上到下，数字依次增1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        findView();
        setMyActionBar(myActionBar);
        setMyViewGroup1(myViewGroup1);
        setMyViewGroup2(myViewGroup2);
        setMyViewGroup3(myViewGroup3);
        setMyViewGroup4(myViewGroup4);
        setMyViewGroup5(myViewGroup5);
        setMyViewGroup6(myViewGroup6);
        setOnClickListener();
    }

    //绑定监听
    private void setOnClickListener() {
        myViewGroup1.setOnClickListener(this);
        myViewGroup2.setOnClickListener(this);
        myViewGroup3.setOnClickListener(this);
        myViewGroup4.setOnClickListener(this);
        myViewGroup5.setOnClickListener(this);
        myViewGroup6.setOnClickListener(this);
    }

    //以下全是设置自定义布局的文字和图片
    private void setMyActionBar(MyActionBar myActionBar) {
        myActionBar.setImgLeft(R.drawable.icon_back);
        myActionBar.setText("设   置");
    }

    private void setMyViewGroup1(MyViewGroup myViewGroup1) {
        myViewGroup1.setText1("鼓励我们");
        myViewGroup1.setImg2(R.drawable.right_arrow);
    }

    private void setMyViewGroup2(MyViewGroup myViewGroup2) {
        myViewGroup2.setText1("捐助我们");
        myViewGroup2.setImg2(R.drawable.right_arrow);
    }

    private void setMyViewGroup3(MyViewGroup myViewGroup3) {
        myViewGroup3.setText1("系统消息");
        myViewGroup3.setImg2(R.drawable.right_arrow);
    }

    private void setMyViewGroup4(MyViewGroup myViewGroup4) {
        myViewGroup4.setText1("检查更新");
        myViewGroup4.setImg2(R.drawable.right_arrow);
    }

    private void setMyViewGroup5(MyViewGroup myViewGroup5) {
        myViewGroup5.setText1("常见问题");
        myViewGroup5.setImg2(R.drawable.right_arrow);
    }

    private void setMyViewGroup6(MyViewGroup myViewGroup6) {
        myViewGroup6.setText1("关于我们");
        myViewGroup6.setImg2(R.drawable.right_arrow);
    }
    //找到控件
    private void findView() {
        myActionBar = (MyActionBar) findViewById(R.id.myActionBar_setting);
        myViewGroup1 = (MyViewGroup) findViewById(R.id.myViewGroup_setting1);
        myViewGroup2 = (MyViewGroup) findViewById(R.id.myViewGroup_setting2);
        myViewGroup3 = (MyViewGroup) findViewById(R.id.myViewGroup_setting3);
        myViewGroup4 = (MyViewGroup) findViewById(R.id.myViewGroup_setting4);
        myViewGroup5 = (MyViewGroup) findViewById(R.id.myViewGroup_setting5);
        myViewGroup6 = (MyViewGroup) findViewById(R.id.myViewGroup_setting6);
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

    //每个选项的点击事件
    @Override
    public void onClick(View v) {
        if (myViewGroup1.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "请通过短信的形式发送到:18865505860，非常感谢你的鼓励，我们会继续努力的！", Toast.LENGTH_LONG).show();
        }else if (myViewGroup2.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "请通过支付宝转账到:635707218@qq.com，非常感谢你的捐助，我们会继续努力的！", Toast.LENGTH_LONG).show();
        }else if (myViewGroup3.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "暂时没有系统消息", Toast.LENGTH_SHORT).show();
        }else if (myViewGroup4.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
        }else if (myViewGroup5.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "如果在使用这款软件遇到任何问题，请联系技术支持，QQ：635707218，Email:635707218@qq.com,Tel:18865505860,谢谢你的使用！", Toast.LENGTH_SHORT).show();
        }else if (myViewGroup6.getId()==v.getId()){
            Toast.makeText(SettingActivity.this, "这是我们的第一款手机应用，非常感谢大家的支持！谢谢大家，Thanks！", Toast.LENGTH_LONG).show();
        }
    }
}
