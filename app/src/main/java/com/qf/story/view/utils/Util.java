package com.qf.story.view.utils;

/**
 * 请求接口地址
 * Created by Galaxy on 2017/2/8.
 */

public class Util {
    private static  String basePath="http://139.129.19.51/story";
    private static String projectPath="/index.php/home/Interface/";
    private static String basePhoto="/Uploads/portrait/";

    /*访问头像的地址*/
    // http://139.129.19.51/story/Uploads/portrait/20170118/587ee56130c7a.jpg
    public static String protrit=basePath+basePhoto+"20170118/587ee56130c7a.jpg";
    /*具体的接口*/
    public static  String login=basePath+projectPath+"login";//登录请求地址
    public static String register=basePath+projectPath+"regist";//注册请求地址
    public static String sendStory=basePath+projectPath+"sendStory";//发表故事请求地址
    public static String changeSex=basePath+projectPath+"changeSex";//修改性别请求地址
    public static String changeNickname=basePath+projectPath+"changeNickName";//修改昵称请求地址
    public static String changeEmail=basePath+projectPath+"changeEmail";//修改邮箱请求地址
    public static String changeBirthday=basePath+projectPath+"changeBirthday";//修改生日请求地址
    public static String changePassword=basePath+projectPath+"changePassword";//修改生日请求地址
    public static String changeSignature=basePath+projectPath+"changeSignature";//修改签名请求地址
    public static String myStorys=basePath+projectPath+"myStorys";//我的故事请求地址
    public static String getStorys=basePath+projectPath+"getStorys";//首页新旧故事请求地址



}
