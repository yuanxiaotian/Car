package com.xuliucar.xuli.xuliucar.ui.homePage.doItems;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.EnvironmentAdapter;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.EnvironmentBean;
import com.xuliucar.xuli.xuliucar.config.StringConfig;
import com.xuliucar.xuli.xuliucar.mvp.presenter.DoEnvironmentPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.EnvironmentView;
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.CarDetail;
import com.xuliucar.xuli.xuliucar.ui.search.DoEnvironmentS;
import com.xuliucar.xuli.xuliucar.utils.PhoneMessage;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.Pull;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class DoEnvironment extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, EnvironmentView {
    private View matter_Group_img, matter_Group_text, matter_select_all_layout, matter_send_msg_layout;
    private CheckBox matter_select_all;
    private ListView matter_listView;
    private LinearLayout matter_refresh_tip, matter_refresh_tip_no;
    private ProgressBar progressBar;
    private List<EnvironmentBean.DataBean.InfoBean> mInfoBeanList;
    private EnvironmentAdapter adapter;
    private int starts = 10;
    private int ends = 10;
    private int size = 10;
    private String msg = "1";
    private DoEnvironmentPresenter mPresenter;
    private int length;
    private boolean pullDown = true;
    private Toolbar mMatter_toolbar;
    private LinearLayout mTo_search;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.matter_layout);
        mPresenter = new DoEnvironmentPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (msg.equals("")) {
            initView();
            matter_select_all_layout.setVisibility(View.GONE);
            matter_Group_text.setVisibility(View.GONE);
            matter_Group_img.setVisibility(View.VISIBLE);
            matter_send_msg_layout.setVisibility(View.GONE);
            matter_select_all.setChecked(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 1002) {
            Bundle bundle = data.getExtras();
            msg = bundle.getString("msg");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubcrible();
    }

    @SuppressWarnings("unchecked")
    protected void initView() {
        matter_select_all_layout = getViewByID(R.id.matter_select_all_layout);
        matter_select_all = getViewByID(R.id.matter_select_all);
        matter_listView = getViewByID(R.id.recyclerView);
        mTo_search = getViewByID(R.id.to_search);
        mMatter_toolbar.setNavigationIcon(R.drawable.back);
        mInfoBeanList = new ArrayList<>();
        initData(0);
    }

    @Override
    protected void setListener() {
        super.setListener();
        matter_Group_text.setOnClickListener(this);
        matter_Group_text.setOnClickListener(this);
        matter_Group_img.setOnClickListener(this);
        matter_send_msg_layout.setOnClickListener(this);
        matter_listView.setOnItemClickListener(this);
        mMatter_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        matter_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    adapter.selectAll();
                } else {
                    adapter.disSelectAll();
                }
            }
        });
        mTo_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoEnvironment.this, DoEnvironmentS.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initLogic() {
        super.initLogic();
        matter_select_all_layout.setVisibility(View.GONE);
        matter_Group_text.setVisibility(View.GONE);
        matter_Group_img.setVisibility(View.VISIBLE);
        matter_send_msg_layout.setVisibility(View.GONE);
        adapter.disSelectAll();
        matter_select_all.setChecked(false);
    }

    private void initData(int index) {
        switch (index) {
            case 0:
                adapter = new EnvironmentAdapter(DoEnvironment.this, mInfoBeanList, 0);
                matter_listView.setAdapter(adapter);
                break;
            case 1:
                adapter = new EnvironmentAdapter(DoEnvironment.this, mInfoBeanList, 1, new EnvironmentAdapter.OnSelectedItemChanged() {
                    @Override
                    public void selectedItemChange(int count) {

                    }
                });
                matter_listView.setAdapter(adapter);
                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        EnvironmentBean.DataBean.InfoBean eProtectBean = mInfoBeanList.get(i);
        Intent intent = new Intent(view.getContext(), CarDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enviromentData", eProtectBean);
        bundle.putString("page", "0");
        bundle.putString("pageName", "environment");
        intent.putExtras(bundle);
        toIntent(view, intent, 1001);
    }

    @Override
    public void onClick(View view) {
        int i1 = view.getId();
//        if (i1 == R.id.matter_send_msg_layout) {
//            StringBuilder sb = new StringBuilder();
//            List<String> phoeList = new ArrayList<>();
//            List<String> idList = new ArrayList<>();
//            for (int i = 0; i < mInfoBeanList.size(); i++) {
//                String phone = PreferencesUtils.getSharePreStr(getApplicationContext(), "GroupMsg", "phone" + i);
//                if (!TextUtils.isEmpty(phone) || !phone.equals("null")) {
//                    sb.append(phone);
//                    phoeList.add(phone);
//                }
//
//            }
//            String gruopPhone = sb.toString();
//            for (int i = 0; i < mInfoBeanList.size(); i++) {
//                String id = PreferencesUtils.getSharePreStr(getApplicationContext(), "GroupMsg", "id" + i);
//                idList.add(id);
//            }
//
//
//            if (gruopPhone.equals("") || gruopPhone.equals("null")) {
//                ToastUtil.showShortToast(getApplicationContext(), "联系人号码不能为空");
//            } else {
//                PhoneMessage.selectSendStyle(DoEnvironment.this, phoeList, idList, "车主", "临期", StringConfig.ODT_EPM + StringConfig.COMPANYNAME, StringConfig.TYPE_EPM);
//            }
//
//        }
    }

    @Override
    public void startLoad(final List<EnvironmentBean.DataBean.InfoBean> been, final int size) {
        this.length = size;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mInfoBeanList.addAll(been);
                adapter.notifyDataSetChanged();
//                Pull.pullToRefreshDown(getApplicationContext(), matter_refreshLayout, matter_refresh_tip_no, size);
            }
        }, 200);
    }

    @Override
    public void loadMore(final List<EnvironmentBean.DataBean.InfoBean> been, final int size) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mInfoBeanList.addAll(been);
                adapter.notifyDataSetChanged();
//                Pull.pullToRefreshUp(getApplicationContext(), matter_refreshLayout, matter_refresh_tip, size, starts, ends);
            }
        }, 200);
    }

    @Override
    public void loadAll(List<EnvironmentBean.DataBean.InfoBean> been) {
        mInfoBeanList.addAll(been);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toastMsg(String msg) {
        switch (msg) {
            case "already logout": {
                ToastUtil.showShortToast(getApplicationContext(), msg);
                mPresenter.toLogin(getApplicationContext());
                break;
            }
            case "未登陆": {
                ToastUtil.showShortToast(getApplicationContext(), msg);
                mPresenter.toLogin(getApplicationContext());
                break;
            }
            case "已登出,或在其它设备上登陆!":
                ToastUtil.showShortToast(getApplicationContext(), msg);
                mPresenter.alreadyLogin(getApplicationContext());
                break;
        }
    }

    @Override
    public void showError() {
        Snackbar.make(matter_listView, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData();
            }
        }).show();
    }


}
