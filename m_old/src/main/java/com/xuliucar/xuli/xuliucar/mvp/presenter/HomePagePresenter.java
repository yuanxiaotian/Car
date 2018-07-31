package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.bean.HomeBanner;
import com.xuliucar.xuli.xuliucar.bean.HomePageBean;
import com.xuliucar.xuli.xuliucar.mvp.model.HomePageModel;
import com.xuliucar.xuli.xuliucar.mvp.model.HomePageModel.HomePageOnListener;
import com.xuliucar.xuli.xuliucar.mvp.view.HomePageView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by skyward on 2016/11/22.
 */

public class HomePagePresenter extends BasePresenterImpl implements HomePageOnListener {
    private HomePageModel mModel;
    private HomePageView mHomePageView;

    public HomePagePresenter(HomePageView homePageView) {
        this.mHomePageView = homePageView;
        mModel = new HomePageModel(this);
    }

    public void getHomePageData() {
        mModel.homeRequest(mHomePageView.loginId(), mHomePageView.companyId());
    }

    @Override
    public void onDataSuccess(HomePageBean bean) {
        if (bean.isSuccess()) {
            mHomePageView.getData(bean);
            HomePageBean.DataBean dataBean = bean.getData();
            App.user = dataBean.getUser();
            App.userp = dataBean.getUserp();
        } else {
            String msg = bean.getMessage();
            mHomePageView.showToast(msg);
        }

    }

    @Override
    public void OnCountSuccess(Counts counts) {
        if (counts.isSuccess()) {
            Counts.DataBean dataBean = counts.getData();
            int od_count = dataBean.getOd_count();
            int odt_count = dataBean.getOdt_count();
            mHomePageView.odCount(od_count);
            mHomePageView.odtCount(odt_count);
        }
    }

    @Override
    public void OnHomeBanner(HomeBanner homeBanner) {
        if (homeBanner.getData() != null) {
            List<HomeBanner.DataBean> list = homeBanner.getData();
            List<String> bannerList = new ArrayList<>();
            for (HomeBanner.DataBean dataBean : list) {
                if (!TextUtils.isEmpty(dataBean.getB1())) {
                    bannerList.add(dataBean.getB1());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB2())) {
                    bannerList.add(dataBean.getB2());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB3())) {
                    bannerList.add(dataBean.getB3());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB4())) {
                    bannerList.add(dataBean.getB4());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB5())) {
                    bannerList.add(dataBean.getB5());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB6())) {
                    bannerList.add(dataBean.getB6());
                    continue;
                }
                if (!TextUtils.isEmpty(dataBean.getB7())) {
                    bannerList.add(dataBean.getB7());
                }
            }
            mHomePageView.showBanner(bannerList);
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
