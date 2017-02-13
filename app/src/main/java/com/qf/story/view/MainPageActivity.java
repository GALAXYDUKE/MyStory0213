package com.qf.story.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qf.story.R;
import com.qf.story.view.entity.HotStory;
import com.qf.story.view.entity.NewStory;
import com.qf.story.view.fragment.HotStoryFragment;
import com.qf.story.view.fragment.NewStoryFragment;
import com.qf.story.view.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 登录成功后的主页面
 */
public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
    private MyMenuItem myMenuItem1,myMenuItem2,myMenuItem3,myMenuItem4,myMenuItem5;//每个菜单选项的布局
    private ImageView imgLeft,imgRight;//顶部左右的两个图片控件
    private DrawerLayout drawerLayout;//整体布局
    private RelativeLayout relativeLayout;//左侧滑动布局
    private Button button;//返回键
    private RadioButton buttonNew,buttonHot;//新故事按钮，旧故事按钮
    private FragmentManager manager;//碎片管理器
    private FragmentTransaction transaction;//碎片事务
    private String picture;//说说图片
    private NewStory newStory;//最新说说
    private HotStory hotStory;//最热说说
    private List<NewStory> list = new ArrayList<>();//最新说说数据源
    private List<HotStory> list1 = new ArrayList<>();//最热说说数据源
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init();
    }
    //刷新页面
    @Override
    protected void onResume() {
        super.onResume();
        if (buttonNew.isChecked()) {
            getServerInfo("new");
        }else if (buttonHot.isChecked()){
            getServerInfo("hot");
        }
    }

    private void getServerInfo(String type) {
        if (type.equals("new")){
            OkHttpUtils.post().url(Util.getStorys)
                            .addParams("type",type)
                            .addParams("page","1")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(MainPageActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int result = jsonObject.getInt("result");
                                        if (result==1){
                                            newStory = new NewStory();
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);
                                                newStory.setStory_time(object.getString("story_time"));
                                                newStory.setStory_info(object.getString("story_info"));
                                                Object json = new JSONTokener(object.getString("pics")).nextValue();
                                                if(json instanceof JSONObject){

                                                }else if (json instanceof JSONArray){
                                                    JSONArray picArray = (JSONArray) json;
                                                    for (int j = 0; j < picArray.length(); j++) {
                                                        picture = picArray.getString(j);
                                                        newStory.getPictureList().add(picture);
                                                    }
                                                }
                                                newStory.setCity(object.getString("city"));
                                                newStory.setReadCount(object.getString("readcount"));
                                                newStory.setComment(object.getString("comment"));
                                                JSONObject userObject = object.getJSONObject("user");
                                                newStory.setSex(userObject.getString("usersex"));
                                                newStory.setNickname(userObject.getString("nickname"));
                                                newStory.setPortrait(userObject.getString("portrait"));
                                                list.add(newStory);
                                            }
                                            Bundle bundle = new Bundle();
                                            ArrayList newList = new ArrayList();
                                            newList.add(list);
                                            bundle.putParcelableArrayList("list",newList);
                                            Fragment fragment = new NewStoryFragment();
                                            fragment.setArguments(bundle);
                                            manager = getFragmentManager();
                                            transaction = manager.beginTransaction();
                                            transaction.replace(R.id.fragment,fragment);
                                            transaction.commit();
                                        }else {
                                            Toast.makeText(MainPageActivity.this, "获取最新说说失败，请重新加载页面", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
        }else if (type.equals("hot")){
            OkHttpUtils.post().url(Util.getStorys)
                    .addParams("type",type)
                    .addParams("page","1")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(MainPageActivity.this,"服务器故障",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("result");
                                if (result==1){
                                    hotStory = new HotStory();
                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        hotStory.setStory_time(object.getString("story_time"));
                                        hotStory.setStory_info(object.getString("story_info"));
                                        JSONArray picArray = object.getJSONArray("pics");
                                        if (picArray.length()!=0){
                                            for (int j = 0; j < picArray.length(); j++) {
                                                picture = picArray.getString(j);
                                                hotStory.getPictureList().add(picture);
                                            }
                                        }
                                        hotStory.setCity(object.getString("city"));
                                        hotStory.setReadCount(object.getString("readcount"));
                                        hotStory.setComment(object.getString("comment"));
                                        JSONObject userObject = object.getJSONObject("user");
                                        hotStory.setSex(userObject.getString("usersex"));
                                        hotStory.setNickname(userObject.getString("nickname"));
                                        hotStory.setPortrait(userObject.getString("portrait"));
                                        list1.add(hotStory);
                                    }
                                    Bundle bundle = new Bundle();
                                    ArrayList hotList = new ArrayList();
                                    hotList.add(list);
                                    bundle.putParcelableArrayList("list",hotList);
                                    Fragment fragment = new HotStoryFragment();
                                    fragment.setArguments(bundle);
                                    manager = getFragmentManager();
                                    transaction = manager.beginTransaction();
                                    transaction.replace(R.id.fragment,fragment);
                                    transaction.commit();
                                }else {
                                    Toast.makeText(MainPageActivity.this, "获取最热说说失败，请重新加载页面", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

        }
    }

    private void init() {
        findView();
        setMyMenuItem1(myMenuItem1);
        setMyMenuItem2(myMenuItem2);
        setMyMenuItem3(myMenuItem3);
        setMyMenuItem4(myMenuItem4);
        setMyMenuItem5(myMenuItem5);
        setRadioButton();
        setOnClick();
    }



    //新说说默认选中后的设置
    private void setRadioButton() {
        if (buttonNew.isChecked()){
            buttonHot.setTextColor(getResources().getColor(R.color.white));
            buttonHot.setBackgroundColor(getResources().getColor(R.color.blue));
            buttonNew.setTextColor(getResources().getColor(R.color.blue));
            buttonNew.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    //以下都是对自定义布局的设置
    private void setMyMenuItem1(MyMenuItem myMenuItem1) {
        myMenuItem1.setIcon(R.drawable.icon_home);
        myMenuItem1.setText("往事杂谈");
    }

    private void setMyMenuItem2(MyMenuItem myMenuItem2) {
        myMenuItem2.setIcon(R.drawable.icon_mystory);
        myMenuItem2.setText("我的故事");
    }

    private void setMyMenuItem3(MyMenuItem myMenuItem3) {
        myMenuItem3.setIcon(R.drawable.icon_record);
        myMenuItem3.setText("浏览记录");
    }

    private void setMyMenuItem4(MyMenuItem myMenuItem4) {
        myMenuItem4.setIcon(R.drawable.icon_mine);
        myMenuItem4.setText("个人信息");
    }

    private void setMyMenuItem5(MyMenuItem myMenuItem5) {
        myMenuItem5.setIcon(R.drawable.icon_setting);
        myMenuItem5.setText("系统设置");
    }

    //绑定监听
    private void setOnClick() {
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
        button.setOnClickListener(this);
        buttonNew.setOnClickListener(this);
        buttonHot.setOnClickListener(this);
        myMenuItem2.setOnClickListener(this);
        myMenuItem1.setOnClickListener(this);
        myMenuItem3.setOnClickListener(this);
        myMenuItem4.setOnClickListener(this);
        myMenuItem5.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        myMenuItem1 = (MyMenuItem) findViewById(R.id.myMenuItem1);
        myMenuItem2 = (MyMenuItem) findViewById(R.id.myMenuItem2);
        myMenuItem3 = (MyMenuItem) findViewById(R.id.myMenuItem3);
        myMenuItem4 = (MyMenuItem) findViewById(R.id.myMenuItem4);
        myMenuItem5 = (MyMenuItem) findViewById(R.id.myMenuItem5);
        imgLeft = (ImageView) findViewById(R.id.myActionBarImgLeft1);
        imgRight = (ImageView) findViewById(R.id.myActionBarImgRight1);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainPageMenuLayout);
        button = (Button) findViewById(R.id.menuButton);
        buttonNew = (RadioButton) findViewById(R.id.btnNew);
        buttonHot = (RadioButton) findViewById(R.id.btnHot);
    }

    //顶部返回键、顶部右侧新故事发表键和左侧滑动布局的监听
    @Override
    public void onClick(View v) {
        if(imgLeft.getId()==v.getId()){
            drawerLayout.openDrawer(relativeLayout);
        }else if(imgRight.getId()==v.getId()){
            Intent intent = getIntent();
            String uid = intent.getStringExtra("uid");
            String userpass = intent.getStringExtra("userpass");
            intent = new Intent(this,NewStoryActivity.class);
            intent.putExtra("uid",uid);
            intent.putExtra("userpass",userpass);
            startActivity(intent);
        }else if(button.getId()==v.getId()){
            finish();
        }else if(myMenuItem2.getId()==v.getId()){
            Intent intent = getIntent();
            String uid = intent.getStringExtra("uid");
            String userpass = intent.getStringExtra("userpass");
            intent = new Intent(this,MyStoryActivity.class);
            intent.putExtra("uid",uid);
            intent.putExtra("userpass",userpass);
            startActivity(intent);
        }else if(myMenuItem4.getId()==v.getId()){
            Intent intent = getIntent();
            String username = intent.getStringExtra("username");
            String password = intent.getStringExtra("password");
            String uid = intent.getStringExtra("uid");
            String userpass = intent.getStringExtra("userpass");
            intent = new Intent(this,PersonalCenterActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            intent.putExtra("uid",uid);
            intent.putExtra("userpass",userpass);
            startActivity(intent);
        }else if(myMenuItem5.getId()==v.getId()){
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
        }else if (buttonNew.getId()==v.getId()){
            buttonHot.setTextColor(getResources().getColor(R.color.white));
            buttonHot.setBackgroundColor(getResources().getColor(R.color.blue));
            buttonNew.setTextColor(getResources().getColor(R.color.blue));
            buttonNew.setBackgroundColor(getResources().getColor(R.color.white));
        }else if (buttonHot.getId()==v.getId()){
            buttonNew.setTextColor(getResources().getColor(R.color.white));
            buttonNew.setBackgroundColor(getResources().getColor(R.color.blue));
            buttonHot.setTextColor(getResources().getColor(R.color.blue));
            buttonHot.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }



}
