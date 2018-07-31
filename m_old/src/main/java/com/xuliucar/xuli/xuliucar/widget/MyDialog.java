package com.xuliucar.xuli.xuliucar.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by skyward on 2016/12/14.
 * email：
 */

public class MyDialog extends Dialog {

    public MyDialog(Context context, int width, int height, View layout, int style) {

        super(context, style);

        setContentView(layout);

        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();


        params.gravity = Gravity.CENTER;

        window.setAttributes(params);
    }
}
