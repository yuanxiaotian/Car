package com.xuliucar.xuli.xuliucar.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.widget.ProgressWebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FileReadActivity extends BaseActivity {

    public WebView view;
    public int screenWidth;
    private String url;
    private String docName;//文档的名字
    private ProgressWebView mWebView;
    private String title;
    private String con;

    @Override
    protected void preInitView() {
        super.preInitView();
        initData();
        getdownload();
        initViews();
    }


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(mWebView);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");
        title = bundle.getString("title");
        con = bundle.getString("con");
        //String fileFormat = url.substring(url.length() - 3, url.length());

        docName = url.substring(url.length() - 8, url.length());
        // Log.i("myLog ", "fileFormat " + fileFormat);

    }

    private void getdownload() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网络连接异常！");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String savePath = "/mnt/sdcard/documents/";
                final File file = new File(savePath);
                if(!file.exists()){
                    file.mkdir();
                }
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(file+"/"+docName);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len=inputStream.read(buffer)) != -1){
                        fileOutputStream.write(buffer,0,len);
                    }
                    fileOutputStream.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final FR fr = new FR(savePath+docName);
                        mWebView.loadUrl(fr.returnPath);
                    }
                });
            }
        });
    }

    private void initViews() {
        //WebView加载显示本地html文件
        mWebView = new ProgressWebView(this);
        mWebView.setShare(title, url,con);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }
}
