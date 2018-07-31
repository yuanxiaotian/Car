package com.xuliucar.xuli.xuliucar.mvp.model;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.InfoSumBean;
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IInfoSumModel;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class InfoSumModel extends BasePresenterImpl implements IInfoSumModel {
    private CallBackListener<InfoSumBean> mListener;

    public InfoSumModel(CallBackListener<InfoSumBean> listener) {
        this.mListener = listener;
    }

    @Override
    public void getInfoSum(String tag, String loginId, String companyId) {
        HttpManage.getInstance().create(ApiConfigTest.class)
                .getInfoSum(loginId, companyId)
                .compose(RxSchedulers.<InfoSumBean>io_main())
                .subscribe(new BaseObserver<InfoSumBean>(tag) {
                    @Override
                    public void success(InfoSumBean infoSumBean) {
                        mListener.OnSuccess(infoSumBean);
                    }

                    @Override
                    public void fail(String s) {

                    }
                });
    }

}
