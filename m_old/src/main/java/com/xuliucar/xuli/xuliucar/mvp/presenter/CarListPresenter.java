package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.CarsListBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.CarListModel;
import com.xuliucar.xuli.xuliucar.mvp.view.CarListView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/23.
 */

public class CarListPresenter extends BasePresenterImpl implements CallBackListener<CarsListBean> {
    private CarListModel mModel;
    private CarListView mCarListView;

    public CarListPresenter(CarListView carListView) {
        this.mCarListView = carListView;
        mModel = new CarListModel(this);

    }

    public void getCarListData() {
        mModel.getCarList(mCarListView.tagName(), mCarListView.loginId(), mCarListView.companyId());
    }

    @Override
    public void OnSuccess(CarsListBean bean) {
        if (bean.isSuccess()) {
            mCarListView.loadData(bean.getData().getInfo());
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
