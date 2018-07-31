package com.xuliucar.xuli.xuliucar.mvp.model;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseObserver;
import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.bean.HomeBanner;
import com.xuliucar.xuli.xuliucar.bean.HomePageBean;
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IHomePageModel;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by skyward on 2016/11/21.
 */

public class HomePageModel extends BasePresenterImpl implements IHomePageModel {

    private HomePageOnListener mOnListener;

    public HomePageModel(HomePageOnListener homePageOnListener) {
        this.mOnListener = homePageOnListener;
    }

    @Override
    public void homeRequest(String loginId, String companyId) {
        HttpManage manage = HttpManage.getInstance();
        ApiConfigTest config = manage.create(ApiConfigTest.class);
        manage.concat(config.getdata(loginId, companyId), config.getCounts(loginId), config.indexbanner(loginId),
                new BaseObserver("2") {
                    @Override
                    public void success(Object o) {
                        if (o instanceof HomePageBean) {
                            mOnListener.onDataSuccess((HomePageBean) o);
                        } else if (o instanceof Counts) {
                            mOnListener.OnCountSuccess((Counts) o);
                        } else if (o instanceof HomeBanner) {
                            mOnListener.OnHomeBanner((HomeBanner) o);
                        }
                    }

                    @Override
                    public void fail(String s) {
//                        mOnListener.OnFailure(s);
                    }
                });


    }

    public interface HomePageOnListener {
        void onDataSuccess(HomePageBean bean);

        void OnCountSuccess(Counts counts);

        void OnHomeBanner(HomeBanner homeBanner);

        void OnFailure(Throwable e);
    }


}
