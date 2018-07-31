package com.xuliucar.xuli.xuliucar.mvp.model;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.CarsListBean;
import com.xuliucar.xuli.xuliucar.config.ApiConfig;
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.ICarList;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import io.reactivex.Observer;


/**
 * Created by skyward on 2016/11/23.
 */

public class CarListModel extends BasePresenterImpl implements ICarList {
    private CallBackListener<CarsListBean> mListener;

    public CarListModel(CallBackListener<CarsListBean> listener) {
        this.mListener = listener;
    }

    @Override
    public void getCarList(String tag, String loginId, String companyId) {
        HttpManage.getInstance()
                .create(ApiConfigTest.class)
                .getCarList(loginId, companyId)
                .compose(RxSchedulers.<CarsListBean>io_main())
                .subscribe(new BaseObserver<CarsListBean>(tag) {
                    @Override
                    public void success(CarsListBean carsListBean) {
                        mListener.OnSuccess(carsListBean);
                    }

                    @Override
                    public void fail(String s) {

                    }
                });
    }

}
