package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import com.cangmaomao.lib.action.FragmentActionKt;
import com.cangmaomao.lib.event.AppEvent;
import com.cangmaomao.lib.event.MainEvent;
import com.cangmaomao.lib.utils.SPUtils;
import com.xuliucar.xuli.xuliucar.bean.MeBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.MeModel;
import com.xuliucar.xuli.xuliucar.mvp.view.MeView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by skyward on 2016/11/23.
 */

public class MePresenter extends BasePresenterImpl implements CallBackListener<MeBean> {
    private MeModel mModel;
    private MeView mMeView;

    public MePresenter(MeView meView) {
        this.mMeView = meView;
        mModel = new MeModel(this);
    }

    public void getMeData() {
        mModel.getMe(mMeView.getTagName(), mMeView.getLoginId(), mMeView.getCompanyId());
    }

    @Override
    public void OnSuccess(MeBean bean) {
        if (bean.isSuccess()) {
            mMeView.getData(bean.getData());
        } else {
            mMeView.toastMsg(bean.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }

    @Override
    public void toLogin(Context context) {
        super.toLogin(context);
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }

    @Override
    public void alreadyLogin(final Context context) {
        super.alreadyLogin(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(context, Login.class);
                PreferencesUtils.clearSharePre(context, "userInfo", "password");
                context.startActivity(i);
            }
        }, 1000);
    }

    public void isSureExitCount(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认退出登陆吗？");
        builder.setTitle("温馨提醒");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                EventBus.getDefault().post(new MainEvent(0));
                SPUtils.INSTANCE.create(context, "userInfo").removeAll();
                EventBus.getDefault().post(new AppEvent(FragmentActionKt.getF_login(),null));
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
}
