package com.xuliucar.xuli.xuliucar.ui.homePage.doItems;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.DoCompanyAdapter;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.DoCompanyDocBean;
import com.xuliucar.xuli.xuliucar.mvp.presenter.DoCompanyPresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.DoCompanyDocView;
import com.xuliucar.xuli.xuliucar.ui.search.DoCompanyDocS;
import com.xuliucar.xuli.xuliucar.utils.Pull;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.xuliucar.xuli.xuliucar.R.id.DoCompany_toobar;
import static com.xuliucar.xuli.xuliucar.R.id.to_search;

public class DoCompanyDoc extends BaseActivity implements DoCompanyDocView {
    private ListView DoCompany_listView;
    private LinearLayout DoCompany_refresh_tip, DoCompany_refresh_tip_no;
    private ProgressBar progressBar;
    private List<DoCompanyDocBean.DataBean.InfoBean> commentBeanList;
    private DoCompanyAdapter adapter;
    private int starts = 10;
    private int ends = 10;
    private int size = 10;
    private DoCompanyPresenter mPresenter;
    private LinearLayout mTo_search;
    private Toolbar mDoCompany_toolbar;
    private int length;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.do_company_doc);
        mPresenter = new DoCompanyPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubcrible();
    }

    @SuppressWarnings("unchecked")
    protected void initView() {
        mDoCompany_toolbar = getViewByID(DoCompany_toobar);
        DoCompany_listView = getViewByID(R.id.DoCompany_listView);
        DoCompany_refresh_tip = getViewByID(R.id.DoCompany_refresh_tip);
        DoCompany_refresh_tip_no = getViewByID(R.id.DoCompany_refresh_tip_no);
        progressBar = getViewByID(R.id.do_company_progressBar);
        mTo_search = getViewByID(to_search);
        commentBeanList = new ArrayList<>();
        adapter = new DoCompanyAdapter(DoCompanyDoc.this, commentBeanList);
        DoCompany_listView.setAdapter(adapter);
        mDoCompany_toolbar.setNavigationIcon(R.drawable.back);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mDoCompany_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTo_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoCompanyDoc.this, DoCompanyDocS.class);
                startActivity(intent);
            }
        });
    }




    @Override
    public void startLoad(@NotNull List<DoCompanyDocBean.DataBean.InfoBean> been, final int size) {
        this.length = size;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                commentBeanList.addAll(been);
                adapter.notifyDataSetChanged();
            }
        }, 200);
    }

    @Override
    public void loadMore(@NotNull List<DoCompanyDocBean.DataBean.InfoBean> been, final int size) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                commentBeanList.addAll(been);
                adapter.notifyDataSetChanged();
            }
        }, 200);
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

        Snackbar.make(DoCompany_listView, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData();
            }
        }).show();
    }


}
