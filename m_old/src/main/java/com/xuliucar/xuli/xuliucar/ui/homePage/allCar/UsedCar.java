package com.xuliucar.xuli.xuliucar.ui.homePage.allCar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cangmaomao.lib.base.BaseNewFragment;
import com.cangmaomao.lib.utils.SPUtils;
import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.mvp.presenter.UsedCarPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.StockCarView;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsedCar extends BaseNewFragment implements StockCarView {
    private TextView usedCarSum;
    private RecyclerView usedCar_listView;
    private CMMAdapter<StockCarBean.DataBean.InfoBean> adapter;
    private UsedCarPresenter mPresenter;

    @Override
    public int layViewId() {
        return R.layout.used_car;
    }

    @Override
    public void initView(@org.jetbrains.annotations.Nullable Bundle savedInstanceState, @NotNull View view) {
        usedCarSum = view.findViewById(R.id.usedCarSum);
        usedCar_listView = view.findViewById(R.id.usedCar_listView);

        usedCar_listView.setLayoutManager(new LinearLayoutManager(_mActivity));
        usedCar_listView.setAdapter(adapter = new CMMAdapter<StockCarBean.DataBean.InfoBean>(R.layout.used_car_item) {
            @Override
            protected void convert(CMMViewHolder cmmViewHolder, StockCarBean.DataBean.InfoBean infoBean, int i) {
                String index = String.valueOf(i + 1);
                ((TextView) cmmViewHolder.getView(R.id.usedCar_index)).setText(index);
                ((TextView) cmmViewHolder.getView(R.id.used_car_plate)).setText(infoBean.getPlates());
                ((TextView) cmmViewHolder.getView(R.id.used_car_fm)).setText(infoBean.getFnum());
            }
        });
        mPresenter = new UsedCarPresenter(this);
        mPresenter.getData();
    }

    @Override
    public int addViewId() {
        return 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubcrible();
    }


    @Override
    public void getCarCount(String count) {
        usedCarSum.setText(count);
    }

    @Override
    public void getStockCar(@NotNull List<StockCarBean.DataBean.InfoBean> bean, final int size) {
        adapter.addList(bean);

    }

    @Override
    public void toastMsg(String msg) {
        switch (msg) {
            case "already logout": {
                ToastUtil.showShortToast(getActivity(), msg);
                mPresenter.toLogin(getActivity());
                break;
            }
            case "未登陆": {
                ToastUtil.showShortToast(getActivity(), msg);
                mPresenter.toLogin(getActivity());
                break;
            }
            case "已登出,或在其它设备上登陆!":
                ToastUtil.showShortToast(getActivity(), msg);
                mPresenter.alreadyLogin(getActivity());
                break;
        }
    }

    @Override
    public void showError() {

    }

    @Override
    @SuppressWarnings("all")
    public String getLoginId() {
        return (String) SPUtils.INSTANCE.create(getActivity(), "userInfo").get("loginid", "");
    }

    @Override
    public String getTagName() {
        return "";
    }

    @Override
    @SuppressWarnings("all")
    public String getCompanyId() {
        return (String) SPUtils.INSTANCE.create(getActivity(), "userInfo").get("compid", "");
    }

}
