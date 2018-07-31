package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.InfoSumBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.InfoSumModel;
import com.xuliucar.xuli.xuliucar.mvp.view.InfoSumView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class InfoSumPresenter extends BasePresenterImpl implements CallBackListener<InfoSumBean> {
    private InfoSumModel mModel;
    private InfoSumView mInfoSumView;

    public InfoSumPresenter(InfoSumView infoSumView) {
        this.mInfoSumView = infoSumView;
        mModel = new InfoSumModel(this);
    }

    public void getData() {
        mModel.getInfoSum(mInfoSumView.getTagName(), mInfoSumView.getLoginId(), mInfoSumView.getCompanyId());
    }

    @Override
    public void OnSuccess(InfoSumBean bean) {
        if (bean.isSuccess()) {
            InfoSumBean.DataBean.CountBean countBean = bean.getData().getCount();
            mInfoSumView.getSumCount(countBean.getCount());
            mInfoSumView.getCarList(bean.getData().getInfo());
        } else {
            mInfoSumView.toastMsg(bean.getMessage());
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
}
