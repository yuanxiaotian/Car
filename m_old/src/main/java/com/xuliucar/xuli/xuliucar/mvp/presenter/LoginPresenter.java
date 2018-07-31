package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.LoginInfo;
import com.xuliucar.xuli.xuliucar.config.AppPower;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.LoginModel;
import com.xuliucar.xuli.xuliucar.mvp.view.LoginView;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by skyward on 2016/11/22.
 *
 */

public class LoginPresenter extends BasePresenterImpl implements CallBackListener<LoginInfo>{
    private LoginModel mModel;
    private LoginView mLoginView;
    public LoginPresenter(LoginView loginView){
        this.mLoginView = loginView;
        mModel = new LoginModel(this);
    }

    public void doLogin(){
        if(TextUtils.isEmpty(mLoginView.getCompany()) || TextUtils.isEmpty(mLoginView.getUsername()) || TextUtils.isEmpty(mLoginView.getPassword())){
            mLoginView.showError("登录信息不能为空！");
        }else {
            if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
                mLoginView.showError();
            }else {
                mModel.postLogin(mLoginView.getCompany(),mLoginView.getUsername(),mLoginView.getPassword());
                //login(mLoginView.getCompany(),mLoginView.getUsername(),mLoginView.getPassword());
            }
        }
    }

    private void login(String company,String username,String password){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("compname",company)
                .add("loginid",username)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http://www.gzxlxx.com:8866/index.php/Home/App/app_login")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                LogUtil.LogPrint("myLog:登陆：  "+string);
            }
        });
    }




    public void autoLogin(){
//        String autoLoginValue = PreferencesUtils.getSharePreStr(App.getContext(), "userInfo", "autoLogin");
//        String username = PreferencesUtils.getSharePreStr(App.getContext(), "userInfo", "username");
//        String password = PreferencesUtils.getSharePreStr(App.getContext(), "userInfo", "password");
//        String companyName = PreferencesUtils.getSharePreStr(App.getContext(), "userInfo", "companyName");
//
//        if (!TextUtils.isEmpty(autoLoginValue)) {
//            if (autoLoginValue.equals("1")) {
//                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(companyName)) {
//                    mLoginView.showError("登录信息不能为空！");
//                } else {
//                   doLogin();
//                }
//            }
//        }
    }

    public void getPhoneMsg(){
        String sdk = String.valueOf(Build.VERSION.SDK_INT);
        App.sdkVsesion = Integer.parseInt(sdk);
        App.brand = android.os.Build.BRAND;
    }

    public void isSureExit(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认退出程序吗？");
        builder.setTitle("温馨提醒");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //  启动主屏幕
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                context.startActivity(intent);
                //System.exit(0);// 使虚拟机停止运行并退出程序
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void OnSuccess(LoginInfo loginInfo) {
        if(loginInfo.isSuccess()){
            LoginInfo.DataBean dataBean = loginInfo.getData();
            PreferencesUtils.putSharePre(App.getContext(), "userInfo", "companyName", mLoginView.getCompany());
            PreferencesUtils.putSharePre(App.getContext(), "userInfo", "username", mLoginView.getUsername());
            PreferencesUtils.putSharePre(App.getContext(), "userInfo", "password", mLoginView.getPassword());
            App.compid = dataBean.getCompid();
            App.loginid = mLoginView.getUsername();
            App.ctype = dataBean.getCtype();
            App.utype = dataBean.getUtype();
            App.pushCount = md5(mLoginView.getUsername() + dataBean.getCompid());//信鸽注册
            //Log.i("myLog","pushCount"+App.pushCount);
            String companyid = String.valueOf(dataBean.getCompid());
            String str1 = "comp_" + companyid;
            String str2 = "utype_" + companyid + dataBean.getUtype();
            App.tag1 = md5(str1);
            App.tag2 = md5(str2);

            AppPower.app_pow1 = loginInfo.getData().getApppower().getApp_pow1();
            AppPower.app_pow2 = loginInfo.getData().getApppower().getApp_pow2();
            AppPower.app_pow3 = loginInfo.getData().getApppower().getApp_pow3();
            AppPower.app_pow4 = loginInfo.getData().getApppower().getApp_pow4();
            AppPower.app_pow5 = loginInfo.getData().getApppower().getApp_pow5();
            AppPower.app_pow6 = loginInfo.getData().getApppower().getApp_pow6();
            AppPower.app_pow7 = loginInfo.getData().getApppower().getApp_pow7();
            AppPower.app_pow8 = loginInfo.getData().getApppower().getApp_pow8();
            AppPower.app_pow9 = loginInfo.getData().getApppower().getApp_pow9();
            AppPower.app_pow10 = loginInfo.getData().getApppower().getApp_pow10();
            AppPower.app_pow11 = loginInfo.getData().getApppower().getApp_pow11();
            AppPower.app_pow12 = loginInfo.getData().getApppower().getApp_pow12();
            AppPower.app_pow13 = loginInfo.getData().getApppower().getApp_pow13();
            AppPower.app_pow14 = loginInfo.getData().getApppower().getApp_pow14();
            AppPower.app_pow15 = loginInfo.getData().getApppower().getApp_pow15();
            AppPower.app_pow16 = loginInfo.getData().getApppower().getApp_pow16();
            AppPower.app_pow17 = loginInfo.getData().getApppower().getApp_pow17();
            AppPower.app_pow18 = loginInfo.getData().getApppower().getApp_pow18();
            AppPower.app_pow19 = loginInfo.getData().getApppower().getApp_pow19();
            AppPower.app_pow20 = loginInfo.getData().getApppower().getApp_pow20();
            AppPower.app_pow21 = loginInfo.getData().getApppower().getApp_pow21();
            AppPower.app_pow22 = loginInfo.getData().getApppower().getApp_pow22();
            AppPower.app_pow23 = loginInfo.getData().getApppower().getApp_pow23();
            AppPower.app_pow24 = loginInfo.getData().getApppower().getApp_pow24();
            AppPower.app_pow25 = loginInfo.getData().getApppower().getApp_pow25();
            AppPower.app_pow26 = loginInfo.getData().getApppower().getApp_pow26();
            AppPower.app_pow27 = loginInfo.getData().getApppower().getApp_pow27();
            AppPower.app_pow28 = loginInfo.getData().getApppower().getApp_pow28();
            AppPower.app_pow29 = loginInfo.getData().getApppower().getApp_pow29();
            AppPower.app_pow30 = loginInfo.getData().getApppower().getApp_pow30();
            AppPower.app_pow31 = loginInfo.getData().getApppower().getApp_pow31();
            AppPower.app_pow32 = loginInfo.getData().getApppower().getApp_pow32();
            AppPower.app_pow33 = loginInfo.getData().getApppower().getApp_pow33();
            AppPower.app_pow34 = loginInfo.getData().getApppower().getApp_pow34();
            AppPower.app_pow35 = loginInfo.getData().getApppower().getApp_pow35();
            AppPower.app_pow36 = loginInfo.getData().getApppower().getApp_pow36();
            AppPower.app_pow37 = loginInfo.getData().getApppower().getApp_pow37();
            AppPower.app_pow38 = loginInfo.getData().getApppower().getApp_pow38();


            mLoginView.showToast("登录成功!");
        }else {
            mLoginView.showError(loginInfo.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }

    private static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
