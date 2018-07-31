package com.xuliucar.xuli.xuliucar.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xuliucar.xuli.xuliucar.R;


public class StartPage extends Activity {
    private static final int TIME = 1500;
    private static final int TO_HOME = 100;
    private static final int TO_GUIDE = 101;
    private boolean isFirstIn = false;//false，是进入主界面,
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case TO_HOME:
                    toHome();
                    break;
                case TO_GUIDE:
                    toGuide();
                    break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences("gdmec", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            handler.sendEmptyMessageDelayed(TO_HOME, TIME);
        } else {
            handler.sendEmptyMessageDelayed(TO_GUIDE, TIME);
            //将进入过引导页的情况存储起来，以用来之后进入时做判断
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false).apply();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //跳到主界面
    private void toHome() {
        Intent intent = new Intent(StartPage.this, Login.class);
        startActivity(intent);
        finish();
    }

    //跳到引导界面
    private void toGuide() {
        Intent intent = new Intent(StartPage.this, GuidePage.class);
        startActivity(intent);
        finish();
    }


}
