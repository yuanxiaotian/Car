package com.xuliucar.xuli.xuliucar.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.xuliucar.xuli.xuliucar.permission.PermissionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


public abstract class BaseFragment extends Fragment implements PermissionUtils.PermissionCallbacks {

    protected View mContentView;
    protected Activity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            setContentView();
            initRefresh();
            initView();
            setListener();
            processLogic(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(mActivity).inflate(layoutResID, null);

    }

    protected void initRefresh() {



    }

    protected abstract void setContentView();

    /**
     * 初始化View控件
     */
    protected abstract void initView();

    /**
     * 给View控件添加事件监听器
     */
    protected void setListener() {
    }

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected void processLogic(Bundle savedInstanceState) {
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }

    /**
     * 不带参数跳转
     */
    public void toIntent(final View view, final Class mClass) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ActivityOptionsCompat options = ActivityOptionsCompat
                                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                        Intent intent = new Intent(getActivity(), mClass);

                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {
        Log.d(TAG, perms.size() + " permissions granted.");
    }

    @Override
    public void onPermissionDenied(int requestCode, List<String> perms) {
        Log.e(TAG, perms.size() + " permissions denied.");

        //此处不处理"不在询问"的状态，如果处理了会导致弹出两个Dialog
        //统一在BaseActivity中做处理
    }

    /**
     * 让新的Activity从一个小的范围扩大到全屏
     * 跳转
     */
    public void toIntent(final View view, final Intent intent) {

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

    }

}