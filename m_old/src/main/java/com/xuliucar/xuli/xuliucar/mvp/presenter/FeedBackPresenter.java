package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.text.TextUtils;

import com.cangmaomao.lib.config.ConfigKt;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.MessageBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.FeedBackModel;
import com.xuliucar.xuli.xuliucar.mvp.view.FeedBackView;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class FeedBackPresenter extends BasePresenterImpl implements CallBackListener<MessageBean> {
    private FeedBackModel mModel;
    private FeedBackView mView;

    public FeedBackPresenter(FeedBackView view) {
        this.mView = view;
        mModel = new FeedBackModel(this);
    }

    public void getResult() {
//        if (TextUtils.isEmpty(mView.getContent())) {
//            mView.feedBackEmpty("反馈内容为空呢！");
//        } else {
//            if (!TextUtils.isEmpty(mView.getPhone())) {
//                if (mView.getPhone().matches(ConfigKt.getPHPNE_REGULAR())) {
//                    mModel.getResutl(App.loginid, App.compid, mView.getName(), mView.getPhone(), mView.getContent());
//                } else {
//                    mView.phoneError("手机号格式不正确！");
//                }
//            } else {
//                mModel.getResutl(App.loginid, App.compid, mView.getName(), "", mView.getContent());//不填写手机号操作
//            }
//
//        }
    }

    @Override
    public void OnSuccess(MessageBean bean) {
//        if (bean.isSuccess()) {
//            mView.toastMsg(bean.getMessage());
//        } else {
//            mView.showError(bean.getMessage());
//        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }
}
