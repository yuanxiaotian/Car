package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.NoticeBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.NoticeModel;
import com.xuliucar.xuli.xuliucar.mvp.view.NoticeView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/23.
 */

public class NoticePresenter extends BasePresenterImpl implements CallBackListener<NoticeBean> {
    private NoticeModel mModel;
    private NoticeView mNoticeView;

    public NoticePresenter(NoticeView noticeView) {
        this.mNoticeView = noticeView;
        mModel = new NoticeModel(this);
    }

    public void getNotice() {
        mModel.getNoticeList(mNoticeView.getLoginId(), mNoticeView.getCompanyId());
    }

    @Override
    public void OnSuccess(NoticeBean bean) {
        if (bean.getSuccess()) {
            mNoticeView.getdata(bean.getData());
        } else {
            mNoticeView.showToast(bean.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }


    @Override
    public void toLogin(Context context) {
    }

    @Override
    public void alreadyLogin(final Context context) {
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
