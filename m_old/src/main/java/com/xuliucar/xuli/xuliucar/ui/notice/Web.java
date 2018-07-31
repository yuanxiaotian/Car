package com.xuliucar.xuli.xuliucar.ui.notice;

import android.content.Intent;
import android.os.Bundle;

import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.widget.ProgressWebView;

import java.lang.reflect.InvocationTargetException;

import cn.sharesdk.framework.ShareSDK;

public class Web extends BaseActivity {

    private String url;
    private ProgressWebView mWebView;
    private String title;
    private String con;

    @Override
    protected void preInitView() {
        super.preInitView();
        getData();
        initViews();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(mWebView);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");
        title = bundle.getString("title");
        con = bundle.getString("con");
    }

    private void initViews() {
        mWebView = new ProgressWebView(this);
        mWebView.loadUrl(url);
        mWebView.setShare(title,url,con);


    }

    //再次进入继续播放
    @Override
    protected void onResume() {
        super.onResume();
        try {
            mWebView.getClass().getMethod("onResume").invoke(mWebView,(Object[])null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }

    //退出暂停播放如果有视频的话
    @Override
    protected void onPause() {
        super.onPause();
        try {
            mWebView.getClass().getMethod("onPause").invoke(mWebView,(Object[])null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
