package com.xuliucar.xuli.xuliucar.qrCode;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.xuliucar.xuli.xuliucar.R;

/**
 * Created by IBM on 2016/10/13.
 */

public class CustomScanAct extends Activity implements DecoratedBarcodeView.TorchListener { // 实现相关接口
    // 添加一个按钮用来控制闪光灯，同时添加两个按钮表示其他功能，先用Toast表示
   // Button swichLight;
    DecoratedBarcodeView mDBV;
    private CaptureManager captureManager;
    private boolean isLightOn = false;
    private Toolbar scan_toolbar;

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customscan);
        //swichLight = (Button) findViewById(R.id.btn_switch);
        mDBV= (DecoratedBarcodeView) findViewById(R.id.dbv_custom);

        mDBV.setTorchListener(this);

        // 如果没有闪光灯功能，就去掉相关按钮
//        if (!hasFlash()) {
//            swichLight.setVisibility(View.GONE);
//        }
        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
        //选择闪关灯
//        swichLight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isLightOn) {
//                    mDBV.setTorchOff();
//                } else {
//                    mDBV.setTorchOn();
//                }
//            }
//        });

        scan_toolbar = (Toolbar) findViewById(R.id.scan_toolbar);
        scan_toolbar.setNavigationIcon(R.drawable.back);
        scan_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        Toast.makeText(this, "torch on", Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this, "torch off", Toast.LENGTH_LONG).show();
        isLightOn = false;
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }



}
