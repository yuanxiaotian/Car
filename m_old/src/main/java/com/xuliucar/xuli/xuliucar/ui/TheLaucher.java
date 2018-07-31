package com.xuliucar.xuli.xuliucar.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class TheLaucher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否从推送通知栏打开的
//        XGPushClickedResult click = XGPushManager.onActivityStarted(this);
//        if (click != null) {
//            //从推送通知栏打开-Service打开Activity会重新执行Laucher流程
//            //查看是不是全新打开的面板
//            if (isTaskRoot()) {
//                return;
//            }
//            //如果有面板存在则关闭当前的面板
//            finish();
//        }else {
//            Intent intent = new Intent(getApplicationContext(),StartPage.class);
//            startActivity(intent);
//            finish();
//        }

    }
}
