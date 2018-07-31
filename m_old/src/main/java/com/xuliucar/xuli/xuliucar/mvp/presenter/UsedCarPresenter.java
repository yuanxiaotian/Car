package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.StockCarModel;
import com.xuliucar.xuli.xuliucar.mvp.view.StockCarView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

import java.util.List;

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

public class UsedCarPresenter extends BasePresenterImpl implements CallBackListener<StockCarBean> {
    private StockCarModel mModel;
    private StockCarView mView;

    public UsedCarPresenter(StockCarView view) {
        mView = view;
        mModel = new StockCarModel(this);
    }

    public void getData() {
        mModel.getStockCar(mView.getTagName(), mView.getLoginId(), mView.getCompanyId());
    }


    @Override
    public void OnSuccess(StockCarBean bean) {
        if (bean.isSuccess()) {
            List<StockCarBean.DataBean.InfoBean> info = bean.getData().getInfo();
            for (int i = 0; i < info.size(); i++) {
                int length = info.get(i).getPlates().length();
                if (!info.get(i).getPlates().isEmpty() && length <= 2) {
                    info.remove(i);
                }
            }
            mView.getStockCar(info, info.size());
            mView.getCarCount(String.valueOf(info.size()));
        } else {
            mView.toastMsg(bean.getMessage());
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
