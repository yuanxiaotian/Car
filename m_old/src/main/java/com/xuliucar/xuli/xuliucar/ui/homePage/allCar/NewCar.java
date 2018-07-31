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
import com.xuliucar.xuli.xuliucar.mvp.presenter.NewCarPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.StockCarView;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCar extends BaseNewFragment implements StockCarView {
    private TextView newCarSum;
    private RecyclerView recyclerView;
    private CMMAdapter<StockCarBean.DataBean.InfoBean> adapter;
    private NewCarPresenter mPresenter;

    @Override
    public int layViewId() {
        return R.layout.new_car;
    }

    @Override
    public void initView(@org.jetbrains.annotations.Nullable Bundle savedInstanceState, @NotNull View view) {
        newCarSum = view.findViewById(R.id.newCarSum);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter = new CMMAdapter<StockCarBean.DataBean.InfoBean>(R.layout.new_car_item) {
            @Override
            protected void convert(CMMViewHolder cmmViewHolder, StockCarBean.DataBean.InfoBean infoBean, int i) {
                String index = String.valueOf(i + 1);
                cmmViewHolder.setText(R.id.newCar_index, index);
                cmmViewHolder.setText(R.id.Engine_model, infoBean.getEnumX());
                cmmViewHolder.setText(R.id.Frame_number, infoBean.getFnum());
            }

        });
        mPresenter = new NewCarPresenter(this);
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
        newCarSum.setText(count);
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
