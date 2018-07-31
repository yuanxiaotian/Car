package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.StatisticsBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.StatisticsModel;
import com.xuliucar.xuli.xuliucar.mvp.view.StatisticsView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class StatisticsPresenter extends BasePresenterImpl implements CallBackListener<StatisticsBean>{
    private StatisticsModel mModel;
    private StatisticsView mView;
    public StatisticsPresenter(StatisticsView statisticsView){
        this.mView = statisticsView;
        mModel = new StatisticsModel(this,mView);
    }

    public void getStatistics(){
        mModel.getData();
    }

    @Override
    public void OnSuccess(StatisticsBean bean) {
        if(bean.isSuccess()){
            mView.getData(bean);
        }else {
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
                PreferencesUtils.clearSharePre(context,"userInfo","password");
                context.startActivity(i);
            }
        }, 1000);
    }
}
