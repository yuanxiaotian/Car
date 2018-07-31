package com.xuliucar.xuli.xuliucar.ui.homePage.doItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.cangmaomao.lib.base.BaseNewFragment;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.TrusteeshipAdapter;
import com.xuliucar.xuli.xuliucar.bean.TrusteeshipBean;
import com.xuliucar.xuli.xuliucar.mvp.presenter.DoTrusteeshipPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.DoTrusteeshipView;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DoTrusteeship extends BaseNewFragment implements DoTrusteeshipView {
    private View  matter_select_all_layout;
    private CheckBox matter_select_all;
    private RecyclerView recyclerView;
    private TrusteeshipAdapter adapter;
    private String msg = "1";
    private List<TrusteeshipBean.DataBean.InfoBean> mInfoBeanList;
    private DoTrusteeshipPresenter mPreshenter;


    @Override
    public int layViewId() {
        return R.layout.matter_layout_new;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NotNull View view) {
        matter_select_all_layout = view.findViewById(R.id.matter_select_all_layout);
        matter_select_all = view.findViewById(R.id.matter_select_all);
        recyclerView = view.findViewById(R.id.recyclerView);
        mInfoBeanList = new ArrayList<>();
        initData(0);
        mPreshenter = new DoTrusteeshipPresenter(this);
        mPreshenter.getData();

//        matter_Group_text.setOnClickListener(this);
//        matter_Group_img.setOnClickListener(this);
//        matter_send_msg_layout.setOnClickListener(this);
        matter_select_all.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                adapter.selectAll();
            } else {
                adapter.disSelectAll();
            }
        });

//        mTo_search.setOnClickListener(view -> {
//            Intent intent = new Intent(_mActivity, DoTrusteeshipS.class);
//            startActivity(intent);
//        });
    }

    @Override
    public int addViewId() {
        return 0;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (msg.equals("")) {
            matter_select_all_layout.setVisibility(View.GONE);
            matter_select_all.setChecked(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 1002) {
            Bundle bundle = data.getExtras();
            msg = bundle.getString("msg");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPreshenter.unSubcrible();
    }

    private void initData(int index) {
        switch (index) {
            case 0:
                adapter = new TrusteeshipAdapter(_mActivity, mInfoBeanList, 0);
//                recyclerView.setAdapter(adapter);
                break;
            case 1:
                adapter = new TrusteeshipAdapter(_mActivity, mInfoBeanList, 1, count -> {

                });
//                recyclerView.setAdapter(adapter);
                break;
        }
    }


    @Override
    public void startLoad(List<TrusteeshipBean.DataBean.InfoBean> been, int size) {

    }

    @Override
    public void loadMore(List<TrusteeshipBean.DataBean.InfoBean> been, int size) {

    }

    @Override
    public void loadAll(List<TrusteeshipBean.DataBean.InfoBean> been) {
        mInfoBeanList.addAll(been);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toastMsg(String msg) {
        switch (msg) {
            case "already logout": {
                ToastUtil.showShortToast(_mActivity, msg);
                mPreshenter.toLogin(_mActivity);
                break;
            }
            case "未登陆": {
                ToastUtil.showShortToast(_mActivity, msg);
                mPreshenter.toLogin(_mActivity);
                break;
            }
            case "已登出,或在其它设备上登陆!":
                ToastUtil.showShortToast(_mActivity, msg);
                mPreshenter.alreadyLogin(_mActivity);
                break;
        }
    }

    @Override
    public void showError() {
    }

    //    @Override
//    protected void initLogic() {
//        super.initLogic();
//        matter_select_all_layout.setVisibility(View.GONE);
//        matter_Group_text.setVisibility(View.GONE);
//        matter_Group_img.setVisibility(View.VISIBLE);
//        matter_send_msg_layout.setVisibility(View.GONE);
//        matter_select_all.setChecked(false);
//    }


//    @Override
//    public void onClick(View view) {
//        int i1 = view.getId();
//        if (i1 == R.id.matter_Group_img) {
//            matter_Group_img.setVisibility(View.GONE);
//            matter_Group_text.setVisibility(View.VISIBLE);
//            matter_select_all_layout.setVisibility(View.VISIBLE);
//            matter_send_msg_layout.setVisibility(View.VISIBLE);
//            mInfoBeanList.clear();
//            mPreshenter.loadAll();
//            msg = "";
//            initData(1);
//            pullDown = false;
//
//        } else if (i1 == R.id.matter_Group_text) {
//            matter_Group_text.setVisibility(View.GONE);
//            matter_Group_img.setVisibility(View.VISIBLE);
//            matter_select_all_layout.setVisibility(View.GONE);
//            matter_send_msg_layout.setVisibility(View.GONE);
//            initData(0);
//            PreferencesUtils.clearSharePre(_mActivity, "GroupMsg"
//            );
//            matter_select_all.setChecked(false);
//            pullDown = true; //解除只支持下拉
//            msg = "1";
//
//        } else if (i1 == R.id.matter_send_msg_layout) {
//            StringBuilder sb = new StringBuilder();
//            List<String> phoeList = new ArrayList<>();
//            List<String> idList = new ArrayList<>();
//            for (int i = 0; i < mInfoBeanList.size(); i++) {
//                String phone = PreferencesUtils.getSharePreStr(_mActivity, "GroupMsg", "phone" + i);
//                if (!TextUtils.isEmpty(phone) || !phone.equals("null")) {
//                    sb.append(phone);
//                    phoeList.add(phone);
//                }
//
//            }
//            String gruopPhone = sb.toString();
//            for (int i = 0; i < mInfoBeanList.size(); i++) {
//                String id = PreferencesUtils.getSharePreStr(_mActivity, "GroupMsg", "id" + i);
//                idList.add(id);
//            }
//
//
//            if (gruopPhone.equals("") || gruopPhone.equals("null")) {
//                ToastUtil.showShortToast(_mActivity, "联系人号码不能为空");
//            } else {
//                PhoneMessage.selectSendStyle(_mActivity, phoeList, idList, "车主", "临期", StringConfig.ODT_INTRUST + StringConfig.COMPANYNAME, StringConfig.TYPE_INTRUST);
//            }
//
//        }
//    }


}
