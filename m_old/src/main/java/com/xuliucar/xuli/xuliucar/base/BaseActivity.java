package com.xuliucar.xuli.xuliucar.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import rx.functions.Action1;


public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate,PermissionUtils.PermissionCallbacks{
    private static final String PATH = Environment.getExternalStorageDirectory()
            .toString() + "/AndroidMedia/";
    protected BGASwipeBackHelper mSwipeBackHelper;
    private static final int PERMANENTLY_DENIED_REQUEST_CODE = 428;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        preInitView();
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        getDates();
        initView();
        setListener();
        initLogic();

    }
    protected void preInitView(){}
    protected abstract void initContentView(Bundle savedInstanceState);
    protected void getDates(){}
    protected  void initView(){}
    protected  void setListener(){}
    protected  void initLogic(){}

    /**
     *查找控件
     */

    protected  <VT extends  View > VT getViewByID(@IdRes int id){
        return (VT)findViewById(id);
    }

    /**
     *不带参数跳转
     */
    public  void toIntent(final View view, final Class mClass){
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(getApplicationContext(),mClass);
                        ActivityOptionsCompat options = ActivityOptionsCompat
                                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                        ActivityCompat.startActivity(BaseActivity.this, intent, options.toBundle());
                    }
                });
    }

    public void  toIntentWithNoClick(final View view,final Class mClass){
        Intent intent = new Intent(getApplicationContext(),mClass);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(BaseActivity.this, intent, options.toBundle());
    }

    @Override
    protected void onPause() {
        super.onPause();
        deleteDir();//删除裁剪的所有图片
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir() {
        File dir = new File(PATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }


    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 滑动关闭activity
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this,this);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
    }

    public void isOnlyTrackingLeftEdge(Boolean b){
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(b);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSwipeBackHelper.backward();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        if (PermissionUtils.somePermissionsPermanentlyDenied(this, perms)) {
            PermissionUtils.onPermissionsPermanentlyDenied(this,
                    getString(R.string.rationale),
                    getString(R.string.rationale_title),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel),
                    PERMANENTLY_DENIED_REQUEST_CODE);
        }
    }

    /**
     * 让新的Activity从一个小的范围扩大到全屏
     * 跳转
     */
    public void toIntent(final View view, final Intent intent,int requestCode) {
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityCompat.startActivityForResult(this, intent,requestCode,options.toBundle());
    }

}
