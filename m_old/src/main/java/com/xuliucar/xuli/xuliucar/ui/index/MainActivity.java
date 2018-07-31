package com.xuliucar.xuli.xuliucar.ui.index;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView home_text, notice_text, me_text;
//    private HomePage homePageFragment;
//    private Notice noticeFragment;
//    private Me meFragment;
    private FragmentManager fragmentManager;
    private Context mContext;
    private Drawable home_img, notice_img, me_img;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        LinearLayout home = (LinearLayout) findViewById(R.id.home);
        LinearLayout notice = (LinearLayout) findViewById(R.id.notice);
        LinearLayout me = (LinearLayout) findViewById(R.id.me);
        home_text = (TextView) findViewById(R.id.home_text);
        notice_text = (TextView) findViewById(R.id.notice_text);
        me_text = (TextView) findViewById(R.id.me_text);
        home.setOnClickListener(this);
        notice.setOnClickListener(this);
        me.setOnClickListener(this);

        mContext = this;
        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String num = bundle.getString("num");
        if (!TextUtils.isEmpty(num)) {
            if (num.equals("0")) {
                selectMenu(0);//启动默认选中首页
            } else if (num.equals("1")) {
                selectMenu(1);
            }
        } else {
            selectMenu(0);//启动默认选中首页
        }

        Context context = getApplicationContext();
//        XGPushManager.registerPush(context, App.pushCount);//执行信鸽注册
//        XGPushManager.setTag(context, App.tag1);
//        XGPushManager.setTag(context, App.tag2);
        // XGPushConfig.enableDebug(this, true);

    }


    @Override
    protected void onResume() {
        super.onResume();
//        XGPushManager.onActivityStarted(this);
        // Log.i("myLog","main onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        XGPushManager.onActivityStoped(this);

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.home) {
            selectMenu(0);

        } else if (i == R.id.notice) {
            selectMenu(1);

        } else if (i == R.id.me) {
            selectMenu(2);

        }
    }

    /**
     * 根据传入的index参数来设置选中的菜单所对应的页面页。
     *
     * @param index 每个tab页对应的下标。0表示首页，1表示公告，2表示我。
     */
    private void selectMenu(int index) {
        //每次选中前要清除之前的选中状态
        clearSelect();
        //开启一个fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的fragment，以防有多个fragment显示在同一个页面
        hideFragment(transaction);
//        switch (index) {
//            case 0:
//
//                home_img = ContextCompat.getDrawable(mContext, R.drawable.shouye1_select);
//                home_img.setBounds(0, 0, home_img.getMinimumWidth(), home_img.getMinimumHeight());
//                home_text.setCompoundDrawables(null, home_img, null, null);
//                home_text.setTextColor(Color.parseColor("#2A6AF1"));
//
//                if (homePageFragment == null) {
//                    homePageFragment = new HomePage();
//                    transaction.add(R.id.content, homePageFragment);
//                } else {
//                    transaction.show(homePageFragment);
//                }
//                break;
//            case 1:
//                notice_img = ContextCompat.getDrawable(mContext, R.drawable.gonggao_select);
//                notice_img.setBounds(0, 0, notice_img.getMinimumWidth(), notice_img.getMinimumHeight());
//                notice_text.setCompoundDrawables(null, notice_img, null, null);
//                notice_text.setTextColor(Color.parseColor("#2A6AF1"));
//                if (noticeFragment == null) {
//                    noticeFragment = new Notice();
//                    transaction.add(R.id.content, noticeFragment);
//                } else {
//                    transaction.show(noticeFragment);
//                }
//                break;
//            case 2:
//            default:
//
//                me_img = ContextCompat.getDrawable(mContext, R.drawable.me_select);
//                me_img.setBounds(0, 0, me_img.getMinimumWidth(), me_img.getMinimumHeight());
//                me_text.setCompoundDrawables(null, me_img, null, null);
//                me_text.setTextColor(Color.parseColor("#2A6AF1"));
//                if (meFragment == null) {
//                    meFragment = new Me();
//                    transaction.add(R.id.content, meFragment);
//                } else {
//                    transaction.show(meFragment);
//                }
//                break;
//        }
        transaction.commit();//提交
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragment(FragmentTransaction transaction) {
//        if (homePageFragment != null) {
//            transaction.hide(homePageFragment);
//        }
//        if (noticeFragment != null) {
//            transaction.hide(noticeFragment);
//        }
//        if (meFragment != null) {
//            transaction.hide(meFragment);
//        }
    }

    private void clearSelect() {
        home_img = ContextCompat.getDrawable(mContext, R.drawable.shouye1);
        home_img.setBounds(0, 0, home_img.getMinimumWidth(), home_img.getMinimumHeight());
        home_text.setCompoundDrawables(null, home_img, null, null);
        notice_img = ContextCompat.getDrawable(mContext, R.drawable.gonggao);
        notice_img.setBounds(0, 0, notice_img.getMinimumWidth(), notice_img.getMinimumHeight());
        notice_text.setCompoundDrawables(null, notice_img, null, null);
        me_img = ContextCompat.getDrawable(mContext, R.drawable.me);
        me_img.setBounds(0, 0, me_img.getMinimumWidth(), me_img.getMinimumHeight());
        me_text.setCompoundDrawables(null, me_img, null, null);
        home_text.setTextColor(Color.parseColor("#7C7C7C"));
        notice_text.setTextColor(Color.parseColor("#7C7C7C"));
        me_text.setTextColor(Color.parseColor("#7C7C7C"));

    }

    private void isSureExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确认退出程序吗？");
        builder.setTitle("温馨提醒");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // ACTION_MAIN with category CATEGORY_HOME 启动主屏幕
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);// 使虚拟机停止运行并退出程序
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            isSureExit();
        }

        return false;

    }
}
