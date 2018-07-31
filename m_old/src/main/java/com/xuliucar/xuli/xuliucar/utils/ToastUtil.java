package com.xuliucar.xuli.xuliucar.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xuliucar.xuli.xuliucar.R;

/**
 * Created by skyward on 2016/5/14.
 *
 */
public class ToastUtil {
    private static String oldMsg;
    private static Toast toast   = null;
    private static long oneTime=0;
    private static long twoTime=0;

    public static void showShortToast(Context context, String s){
        if(toast==null){
            toast =Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }


    public static void showShortToast(Context context, int resId){
        showShortToast(context, context.getString(resId));
    }



    public static void showLongToast(Context context, String s){
        if(toast==null){
            toast =Toast.makeText(context, s, Toast.LENGTH_LONG);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_LONG){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }


    public static void showLongToast(Context context, int resId){
        showLongToast(context, context.getString(resId));
    }


    public static void tips(final Context context, final LinearLayout linearLayout){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
                linearLayout.setVisibility(View.GONE);
                linearLayout.setAnimation(animation);
            }
        }, 1500);
    }

}
