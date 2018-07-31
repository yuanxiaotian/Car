package com.xuliucar.xuli.xuliucar.utils;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by skyward on 2016/7/29.
 *
 */
public class SavePic {
    private static final String PATH = Environment.getExternalStorageDirectory()
            .toString() + "/UCar/";



    public  void getDownload(String url) {
        // 系统时间
        long dateTaken = System.currentTimeMillis();
        // 图像名称
        final String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken)
                .toString() + ".jpg";
        final File file = new File(PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(file + "/" + filename);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    Log.i("myLog", "下载成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
