package com.xuliucar.xuli.xuliucar.mvp.model;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IStockCar;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

public class StockCarModel extends BasePresenterImpl implements IStockCar {
    private CallBackListener<StockCarBean> mListener;

    public StockCarModel(CallBackListener<StockCarBean> listener) {
        mListener = listener;
    }

    @Override
    public void getStockCar(String tag, String loginId, String companyId) {
        HttpManage.getInstance().create(ApiConfigTest.class)
                .getStockCar(loginId, companyId)
                .compose(RxSchedulers.<StockCarBean>io_main())
                .subscribe(new BaseObserver<StockCarBean>(tag) {
                    @Override
                    public void success(StockCarBean stockCarBean) {
                        mListener.OnSuccess(stockCarBean);
                    }

                    @Override
                    public void fail(String s) {

                    }
                });
    }
}
